package br.com.proposta.PropostaOrange.biometria;

import br.com.proposta.PropostaOrange.upload.UploadImages;
import br.com.proposta.PropostaOrange.upload.UploaderImage;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BiometriaDTOResponse {
    @NotEmpty
    private List<String> URLS = new ArrayList<>();

    public BiometriaDTOResponse() {
    }

    public BiometriaDTOResponse(List<String> URLS) {
        this.URLS = URLS;
    }

    public BiometriaDTOResponse converterList(List<Biometria> biometriaList, UploadImages uploadImages){
        for (Biometria b: biometriaList) {
            this.URLS.add(uploadImages.gerarURL(b.getId()));
        }
        return this;
    }

    public List<String> getURLS() {
        return URLS;
    }
}
