package br.com.proposta.PropostaOrange.validateErrors;

import br.com.proposta.PropostaOrange.feignError.FeignErrorDecoder;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErroDeValidacaoHandler {
	
	@Autowired
	private MessageSource messageSource;

	@ResponseBody
	@ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
	public String handleHttpMediaTypeNotAcceptableException() {
		return "acceptable MIME type:" + MediaType.APPLICATION_JSON_VALUE;
	}


	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler( MethodArgumentNotValidException.class)
	public List<ErroAPI> handle(MethodArgumentNotValidException exception) {
		List<ErroAPI> dto = new ArrayList<>();
		
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErroAPI erro = new ErroAPI(e.getField(), mensagem);
			dto.add(erro);
		});
		
		return dto;
	}


	/*
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler( FeignException.class)
	public ErroAPI handle(FeignException exception) {
		//FeignErrorDecoder feignErrorDecoder = new FeignErrorDecoder();
		//feignErrorDecoder.decode(exception.get)
		return  new ErroAPI("Erro", exception.getMessage());
	}
	 */

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler( HttpMessageNotReadableException.class)
	public String handle(HttpMessageNotReadableException exception) {

		return "Erro: HttpMessageNotReadableException: mensagem" + exception.getMessage()+ "|| "+
				exception.getHttpInputMessage();
	}

}
