package br.com.proposta.PropostaOrange.upload;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploaderImage{
    boolean validarBase64(String img);
    String  imgEmBase64(MultipartFile file) throws IOException;
    String  gerarURL(Long idBiometria);
}
