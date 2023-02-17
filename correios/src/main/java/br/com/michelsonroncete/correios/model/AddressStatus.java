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
public class AddressStatus {
	
	public static final int DEFAUT_ID = 1;
	
	@Id
	private int id;
	private Status status;

}
