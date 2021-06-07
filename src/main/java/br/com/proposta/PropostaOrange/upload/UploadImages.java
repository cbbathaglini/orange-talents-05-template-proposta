package br.com.proposta.PropostaOrange.upload;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class UploadImages implements UploaderImage{


    @Override
    public boolean validarBase64(String img) {
        try {
            Base64.decodeBase64(img);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }


    @Override
    public String imgEmBase64(MultipartFile file) throws IOException {
        return new String(Base64.encodeBase64(file.getBytes()), "UTF-8");
    }


    public String gerarURL(Long idBiometria)  {
        return "https://imgbb.com/"+ idBiometria;
    }

}

