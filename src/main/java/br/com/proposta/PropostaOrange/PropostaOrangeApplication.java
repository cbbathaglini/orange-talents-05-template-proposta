package br.com.proposta.PropostaOrange;

import br.com.proposta.PropostaOrange.feignError.FeignErrorDecoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients
@SpringBootApplication
public class PropostaOrangeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropostaOrangeApplication.class, args);
	}
	@Bean
	public FeignErrorDecoder errorDecoder() {
		return new FeignErrorDecoder();
	}
}
