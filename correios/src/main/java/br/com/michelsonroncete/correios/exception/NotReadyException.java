package br.com.michelsonroncete.correios.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.SERVICE_UNAVAILABLE, reason="Service is installing, please wait 3-5 minutes") //503
public class NotReadyException extends Exception {

}
