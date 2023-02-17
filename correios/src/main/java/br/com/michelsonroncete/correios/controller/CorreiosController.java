package br.com.michelsonroncete.correios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.michelsonroncete.correios.exception.NoContentException;
import br.com.michelsonroncete.correios.model.Address;
import br.com.michelsonroncete.correios.service.CorreiosService;

@RestController
public class CorreiosController {
	
	@Autowired
	private CorreiosService service;

	@GetMapping("/status")
	public String getStatus() {
		return "Service status: " + this.service.getStatus();
	}

	@GetMapping("/zipcode/{zipcode}")
	public Address getAddressByZipcode(@PathVariable("zipcode") String zipcode) throws NoContentException {
		return this.service.getAddressByZipcode(zipcode);
	}
}
