package br.com.michelsonroncete.correios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.michelsonroncete.correios.exception.NoContentException;
import br.com.michelsonroncete.correios.model.Address;
import br.com.michelsonroncete.correios.model.Status;
import br.com.michelsonroncete.correios.repository.AddressRepository;
import br.com.michelsonroncete.correios.repository.AddressStatusRepository;

@Service
public class CorreiosService{
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private AddressStatusRepository addressStatusRepository;
	
	public Status getStatus() {
		return Status.READY;
	}
	
	public Address getAddressByZipcode(String zipcpode) throws NoContentException{
		return addressRepository.findById(zipcpode)
				.orElseThrow(NoContentException::new);
	}
	
	public void setup() {
		
	}
}
