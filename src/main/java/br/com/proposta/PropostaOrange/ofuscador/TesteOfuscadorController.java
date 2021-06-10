package br.com.proposta.PropostaOrange.ofuscador;


import br.com.proposta.PropostaOrange.carteira.CarteiraDTORequest;
import br.com.proposta.PropostaOrange.validateErrors.ErroAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/teste/ofuscador")
public class TesteOfuscadorController {

    @Autowired
    private TesteOfuscadorRepository ofuscadorRepository;

    @PostMapping
    public String cadastrar(@RequestBody @Valid OfuscadorDTORequest ofuscadorDTORequest){

        //System.out.println("valor original:" + ofuscadorDTORequest.getValor());
        Ofuscador ofuscador = new Ofuscador();
        String criptografado = ofuscador.convertToDatabaseColumn(ofuscadorDTORequest.getValor());
        String descriptografado = ofuscador.convertToEntityAttribute(criptografado);

        TesteOfuscador testeOfuscador = new TesteOfuscador(ofuscadorDTORequest.getValor());
        ofuscadorRepository.save(testeOfuscador);

        return "criptografado: " + criptografado + "\n Descriptografado: " + descriptografado;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity consultar(@PathVariable("id") Long idOfuscador){

        Optional<TesteOfuscador> testeOfuscadorOp = ofuscadorRepository.findById(idOfuscador);
        if(!testeOfuscadorOp.isPresent()){
            return ResponseEntity.status(404).body(new ErroAPI("OfuscadorTeste","Não foi encontrado este ofuscador teste."));
        }

        return ResponseEntity.ok().body(testeOfuscadorOp.get());
    }
}
