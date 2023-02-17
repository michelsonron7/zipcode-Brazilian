package br.com.michelsonroncete.correios.service;

import org.springframework.stereotype.Service;

import br.com.michelsonroncete.correios.model.Address;
import br.com.michelsonroncete.correios.model.Status;

@Service
public class CorreiosService{
	
	public Status getStatus() {
		return Status.READY;
	}
	
	public Address getAddressByZipcode(String zipcpode) {
		return null;
	}
	
	public void setup() {
		
	}
}
