package com.andrefaustino.fullstackproject.services.validation;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.andrefaustino.fullstackproject.domain.Client;
import com.andrefaustino.fullstackproject.domain.enums.ClientType;
import com.andrefaustino.fullstackproject.dto.ClientNewDTO;
import com.andrefaustino.fullstackproject.repositories.ClientRepository;
import com.andrefaustino.fullstackproject.resources.exceptions.FieldMessage;
import com.andrefaustino.fullstackproject.services.validation.utils.BR;



public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO> {
	
	@Autowired
	private ClientRepository repo;
	
	@Override
	public void initialize(ClientInsert ann) {
	}

	@Override
	public boolean isValid(ClientNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (objDto.getClientType().equals(ClientType.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfcnpj())) 
			list.add(new FieldMessage("cpfOuCnpj", "CPF Inválido"));
		
	
		if (objDto.getClientType().equals(ClientType.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfcnpj())) 
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ Inválido"));
		
		
		Client aux = repo.findByEmail(objDto.getEmail());
		if (aux != null)  
			list.add(new FieldMessage("Email", "Email is already been used!"));
		
		// inclua os testes aqui, inserindo erros na lista

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}