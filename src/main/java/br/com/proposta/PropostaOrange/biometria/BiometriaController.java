package br.com.proposta.PropostaOrange.biometria;

import br.com.proposta.PropostaOrange.cartao.Cartao;
import br.com.proposta.PropostaOrange.cartao.CartaoRepository;
import br.com.proposta.PropostaOrange.upload.UploadImages;
import br.com.proposta.PropostaOrange.validateErrors.ErroAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/biometrias")
public class BiometriaController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @PostMapping(value = "/{numCartao}")
    @Transactional
    public ResponseEntity adicionaImagens(@PathVariable("numCartao") String numCartao,
                                          @Valid @RequestBody BiometriaDTORequest biometriaDTORequest,
                                          @Autowired UploadImages uploaderImage){

        System.out.println("here");
        Cartao cartaoEncontrado = cartaoRepository.findByNumCartao(numCartao);
        System.out.println("cartao:" + cartaoEncontrado);
        if( cartaoEncontrado == null){
            return ResponseEntity.status(404).body(new ErroAPI("Cartão","O cartão não foi encontrado na base de dados."));
        }

        List<Biometria> biometriaList = biometriaDTORequest.converterLista(numCartao);
        if(!cartaoEncontrado.validaImgs(uploaderImage)){
            return ResponseEntity.status(400).body(new ErroAPI("Biometria","A biometria informada é inválida."));
        }

        if (biometriaList.size() > 0) {
            cartaoEncontrado.setBiometria(biometriaList);
            cartaoRepository.save(cartaoEncontrado); //merge com as novas infos, com as imagens
            BiometriaDTOResponse listaBiometriaDTOResponse = new BiometriaDTOResponse();
            listaBiometriaDTOResponse.converterList(biometriaList,uploaderImage);
            return ResponseEntity.status(201).body(listaBiometriaDTOResponse.converterList(biometriaList,uploaderImage));
        }

        return ResponseEntity.status(404).body(new ErroAPI("Biometria","Nenhuma biometria foi informada"));
    }

}
