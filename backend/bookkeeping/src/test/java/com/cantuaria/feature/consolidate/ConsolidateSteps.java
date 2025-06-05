package com.cantuaria.feature.consolidate;

import com.cantuaria.bookkeeping.model.ConsolidateResponseBookkeeping;
import com.cantuaria.consolidate.ConsolidateService;
import com.cantuaria.feature.GenericSteps;
import com.cantuaria.sped.Bookkeeping;
import com.cantuaria.sped.BookkeepingRepository;
import com.cantuaria.sped.BookkeepingStatus;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;

import static com.cantuaria.message.model.ConsolidateMessage.DESTINATION_QUEUE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ConsolidateSteps extends GenericSteps {

    @Autowired
    private BookkeepingRepository bookkeepingRepository;
    @Autowired
    private ConsolidateService consolidateService;

    private Long idBookkeeping;
    private ConsolidateResponseBookkeeping response;

    @Dado("que o usuário solicite ao sistema a consolidação da escrituração de ID {string}")
    public void prepareConsolidation(String idBookkeeping) {
        this.idBookkeeping = Long.parseLong(idBookkeeping);
    }

    @Dado("que exista uma solicitação com status de {string}")
    public void prepareBookkeeping(String status) {
        Bookkeeping entity = bookkeepingRepository.findAll().stream().filter(bookkeeping -> bookkeeping.getStatus() == null)
                .findFirst().orElseThrow();
        idBookkeeping = entity.getId();
        BookkeepingStatus bookkeepingStatus = BookkeepingStatus.findByDescription(status);
        entity.setStatus(bookkeepingStatus);
        bookkeepingRepository.save(entity);
    }

    @Quando("sistema receber a solicitação de consolidação")
    public void requestConsolidation() throws Exception {
        MvcResult result = this.mockMvc.perform(post("/bookkeeping/consolidate/" + idBookkeeping))
                .andExpect(status().isOk())
                .andReturn();
        String json = result.getResponse().getContentAsString();
        response = mapper.readValue(json, ConsolidateResponseBookkeeping.class);
    }

    @Quando("o sistema processar a consolidação")
    public void processConsolidation() throws Exception {
        consolidateService.processBookkeeping(idBookkeeping);
    }

    @Entao("o sistema deverá marcar escrituração como {string}")
    public void verifyEntity(String status) {
        BookkeepingStatus responseStatus = BookkeepingStatus.findByCode(response.currentStatus());
        assertThat(responseStatus.getDescription()).isEqualTo(status);
    }

    @Entao("a escrituração estará gerada e validada no sistema")
    public void verifyBookkeeping() {
        Bookkeeping bookkeeping = bookkeepingRepository.findById(idBookkeeping)
                .orElseThrow();

        assertThat(bookkeeping.getBlock0().getId()).isNotNull();
    }

    @E("a irá iniciar um evento assíncrono de nome {string}")
    public void verifyAsyncMessage(String destination) {
        switch (destination) {
            case "Consolidar escrituração" -> assertThat(stubFeature.getLastDestination()).isEqualTo(DESTINATION_QUEUE);
            default -> fail("Deve receber uma mensagem");
        }

        assertThat(stubFeature.getLastMessage()).isNotNull();
    }

}
