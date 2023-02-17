package br.com.michelsonroncete.correios.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {
	@Id
	private String zipcode;
	private String street;
	private String district;
	private String city;
	private String state;

}
