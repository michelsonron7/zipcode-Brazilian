package br.com.michelsonroncete.correios.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.michelsonroncete.correios.model.Address;

public interface AddressStatusRepository extends CrudRepository<Address, Integer>{

}
