package com.cantuaria.feature.bookkeeping;

import com.cantuaria.bookkeeping.model.ResponseBookkeeping;
import com.cantuaria.bookkeeping.model.SaveBookkeeping;
import com.cantuaria.sped.BookkeepingRepository;
import com.cantuaria.sped.domain.Profile;
import com.cantuaria.sped.domain.Purpose;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;

public class BookkeepingSteps {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private BookkeepingRepository repository;

    private SaveBookkeeping request;
    private ResponseBookkeeping response;

    @Dado("que usuário envie uma solicitação de escrituração")
    public void createBookkeeping(){
        request = new SaveBookkeeping(
                1L,
                LocalDate.of(2025,1,1),
                LocalDate.of(2025,1,30),
                1L,
                Purpose.ORIGINAL.getCode(),
                Profile.A.getCode()
        );
    }

    @Quando("sistema cadastrar nova escrituração")
    public void sendPost() throws Exception {
        MvcResult result = this.mockMvc.perform(post("/bookkeeping")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(request)))
                .andExpect(status().isOk())
                .andReturn();
        String json = result.getResponse().getContentAsString();
        response = mapper.readValue(json, ResponseBookkeeping.class);
    }

    @Entao("a solicitação será cadastrada")
    public void verifyEntity(){
        repository.findById(response.id())
                .orElseThrow();
    }

}
