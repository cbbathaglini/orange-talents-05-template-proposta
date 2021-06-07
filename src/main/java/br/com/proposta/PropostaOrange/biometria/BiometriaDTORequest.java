package br.com.proposta.PropostaOrange.biometria;

import br.com.proposta.PropostaOrange.upload.UploadImages;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BiometriaDTORequest {

    @NotEmpty @NotNull
    private List<String> biometriaBase64;

    @JsonCreator
    public BiometriaDTORequest(List<String> biometriaBase64) {
        this.biometriaBase64 = biometriaBase64;
    }


    public List<String> getBiometriaBase64() {
        return biometriaBase64;
    }

    public List<Biometria> converterLista(String numcartao) {
        List<Biometria> biometriaList = new ArrayList<>();
        biometriaList.addAll(this.biometriaBase64.stream()
                .map(b-> new Biometria(numcartao,b))
                .collect(Collectors.toList()));
        return biometriaList;
    }

}
