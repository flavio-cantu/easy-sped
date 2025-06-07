package com.cantuaria.feature.consolidate;

import com.cantuaria.bookkeeping.model.ConsolidateResponseBookkeeping;
import com.cantuaria.consolidate.ConsolidateService;
import com.cantuaria.feature.GenericSteps;
import com.cantuaria.sped.Bookkeeping;
import com.cantuaria.sped.BookkeepingRepository;
import com.cantuaria.sped.BookkeepingStatus;
import com.cantuaria.sped.block_0.Block0;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

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

    @Dado("que o usuário solicite ao sistema a consolidação da escrituração do cliente {string}")
    @Transactional
    public void prepareConsolidation(String businessName) {
        Bookkeeping entity = bookkeepingRepository.findAll().stream().filter(bookkeeping -> bookkeeping.getStatus() == null)
                .filter(bookkeeping -> bookkeeping.getClient().getBusinessName().equals(businessName))
                .findFirst().orElseThrow();
        this.idBookkeeping = entity.getId();
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
    @Transactional
    public void verifyBookkeeping() {
        Bookkeeping bookkeeping = bookkeepingRepository.findById(idBookkeeping)
                .orElseThrow();

        checkBlock0(bookkeeping);
    }

    @E("a irá iniciar um evento assíncrono de nome {string}")
    public void verifyAsyncMessage(String destination) {
        if (destination.equals("Consolidar escrituração")) {
            assertThat(stubFeature.getLastDestination()).isEqualTo(DESTINATION_QUEUE);
        } else {
            fail("Deve receber uma mensagem");
        }
        assertThat(stubFeature.getLastMessage()).isNotNull();
    }


    private static void checkBlock0(Bookkeeping bookkeeping) {
        Block0 block0 = bookkeeping.getBlock0();
        assertThat(block0.getId()).isNotNull();
        assertThat(block0.getRecord0000().getCnpj()).isNotNull();
        assertThat(block0.getRecord0005().getFantasia()).isNotNull();
        assertThat(block0.getRecord0100().getCnpj()).isNotNull();
        assertThat(block0.getRecords0150()).isNotEmpty();
        assertThat(block0.getRecords0190()).isNotEmpty();
        assertThat(block0.getRecords0200()).isNotEmpty();
        assertThat(block0.getRecords0200().stream().findFirst().get().getRecords0205()).isNotEmpty();
    }

}
