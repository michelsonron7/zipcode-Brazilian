package br.com.michelsonroncete.correios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.michelsonroncete.correios.exception.NoContentException;
import br.com.michelsonroncete.correios.exception.NotReadyException;
import br.com.michelsonroncete.correios.model.Address;
import br.com.michelsonroncete.correios.model.AddressStatus;
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
		return this.addressStatusRepository.findById(AddressStatus.DEFAUT_ID)
				.orElse(AddressStatus.builder().status(Status.NEED_SETUP).build())
				.getStatus();
				
	}
	public Address getAddressByZipcode(String zipcode) throws NoContentException, NotReadyException {
		if (!this.getStatus().equals(Status.READY))
			throw new NotReadyException();
		
		return addressRepository.findById(zipcode)
				.orElseThrow(NoContentException::new);
		
		
	}
	
	public void setup() {
		
	}
	
}
