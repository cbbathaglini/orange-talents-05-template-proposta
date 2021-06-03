package br.com.proposta.PropostaOrange.proposta;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import java.math.BigDecimal;
import java.util.Optional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureDataJpa
class PropostaTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    @Rollback
    @Transactional
    void deveRetornarProposta() throws Exception {


        String json = CriacaoJson(new PropostaDTORequest("41.173.861/0001-80", "cbbathaglini@gmail.com", "Carine Bertagnolli Bathaglini", "avenida coronel marcos", new BigDecimal("4500.0")));

        mockMvc.perform(post("/propostas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());

        Optional<Proposta> propostaOptional = propostaRepository.findByDocumento("41.173.861/0001-80");


        Assertions.assertAll(
                () -> Assertions.assertEquals(propostaOptional.get().getId(), propostaRepository.getLastId()),
                () -> Assertions.assertEquals(propostaOptional.get().getNome(), "Carine Bertagnolli Bathaglini"),
                () -> Assertions.assertEquals(propostaOptional.get().getEmail(), "cbbathaglini@gmail.com")
        );

    }


    @Test
    @Rollback
    @Transactional
    void deveRetornarExcecaoJaTemProposta() throws Exception {

        String json = CriacaoJson(new PropostaDTORequest("65.834.187/0001-74", "email@gmail.com", "teste nome", "endereceo pessoa z", new BigDecimal("4900.0")));

        mockMvc.perform(post("/propostas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(  status().is4xxClientError());

    }

    /*
    @Test
    void deveRetornarCategoria() throws Exception {

        String json = CriacaoJson(new CategoriaRequest("Terrorhuhuhuu"));

        mockMvc.perform(post("/categorias")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());

    }

    @Test
    void deveRetornarUmLivro() throws Exception {

        String json = CriacaoJson(new LivroRequest("Livro teste", "Este é um livro de teste", "Olá" , "983493989yeihfifu89w84", 200, 30.0, new Date(), 1L, 1L));

        mockMvc.perform(post("/livros")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());

    }
     */


    private String CriacaoJson(Object autorRequest) throws JsonProcessingException {
        return objectMapper.writeValueAsString(autorRequest);
    }
}