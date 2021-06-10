package br.com.proposta.PropostaOrange.ofuscador;

import org.springframework.security.crypto.encrypt.Encryptors;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class Ofuscador implements AttributeConverter<String, String> {

    /*
        Disponível em: https://docs.spring.io/spring-security/site/docs/4.2.20.RELEASE/apidocs/org/springframework/security/crypto/encrypt/Encryptors.html
        delux(CharSequence password, CharSequence salt)
        Creates a text encryptor that uses "stronger" password-based encryption.
    */
    @SuppressWarnings("deprecation")
    @Override
    public String convertToDatabaseColumn(String valor) {
        try {
            return Encryptors.delux("${encrypt.key}", "52563076325333427168425861306378373030544A727637646157").encrypt(valor);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
    @SuppressWarnings("deprecation")
    @Override
    public String convertToEntityAttribute(String valor) {
        try {
            //Creates a text encryptor that uses "standard" password-based encryption. Encrypted text is hex-encoded.
            return Encryptors.delux("${encrypt.key}", "52563076325333427168425861306378373030544A727637646157").decrypt(valor);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
