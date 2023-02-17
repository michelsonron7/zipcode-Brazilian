package br.com.michelsonroncete.correios.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import br.com.michelsonroncete.correios.CorreiosApplication;
import br.com.michelsonroncete.correios.exception.NoContentException;
import br.com.michelsonroncete.correios.exception.NotReadyException;
import br.com.michelsonroncete.correios.model.Address;
import br.com.michelsonroncete.correios.model.AddressStatus;
import br.com.michelsonroncete.correios.model.Status;
import br.com.michelsonroncete.correios.repository.AddressRepository;
import br.com.michelsonroncete.correios.repository.AddressStatusRepository;
import br.com.michelsonroncete.correios.repository.SetupRepository;

@Service
public class CorreiosService {
	
	private static Logger logger = LoggerFactory.getLogger(CorreiosService.class);
	
	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private AddressStatusRepository addressStatusRepository;

	@Autowired
	private SetupRepository setupRepository;

	public Status getStatus() {
		return this.addressStatusRepository.findById(AddressStatus.DEFAUT_ID)
				.orElse(AddressStatus.builder().status(Status.NEED_SETUP).build()).getStatus();

	}

	public Address getAddressByZipcode(String zipcode) throws NoContentException, NotReadyException {
		if (!this.getStatus().equals(Status.READY))
			throw new NotReadyException();

		return addressRepository.findById(zipcode)
			   .orElseThrow(NoContentException::new);

	}

	private void saveStatus(Status status) {
		this.addressStatusRepository
		.save(AddressStatus.builder()
		.id(AddressStatus.DEFAUT_ID)
		.status(status).build());

	}
	
	@EventListener(ApplicationStartedEvent.class)
	protected void setupOnStartup() {
		try {
			this.setup();
		}catch(Exception exc) {
			CorreiosApplication.close(999);
			logger.error(".setupOnStartup() - Exception", exc);
		}
	}

	public void setup() throws Exception {
		logger.info("------");
		logger.info("------");
		logger.info("------ SETUP RUNNING ");
		logger.info("------");
		logger.info("------");

		if (this.getStatus().equals(Status.NEED_SETUP)) {
				this.saveStatus(Status.SETUP_RUNNING);
			try {
			this.setupRepository.getFromOrigin();
			}catch(Exception exc) {
				this.saveStatus(Status.NEED_SETUP);
				throw exc;
			}
			this.saveStatus(Status.READY);
		}
		logger.info("------");
		logger.info("------");
		logger.info("------ SERVICE READY ");
		logger.info("------");
		logger.info("------");
	}

}
