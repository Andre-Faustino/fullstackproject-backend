package com.andrefaustino.fullstackproject.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.andrefaustino.fullstackproject.domain.Address;
import com.andrefaustino.fullstackproject.domain.Category;
import com.andrefaustino.fullstackproject.domain.City;
import com.andrefaustino.fullstackproject.domain.Client;
import com.andrefaustino.fullstackproject.domain.enums.ClientType;
import com.andrefaustino.fullstackproject.domain.Client;
import com.andrefaustino.fullstackproject.dto.ClientDTO;
import com.andrefaustino.fullstackproject.dto.ClientNewDTO;
import com.andrefaustino.fullstackproject.repositories.AddressRepository;
import com.andrefaustino.fullstackproject.repositories.ClientRepository;
import com.andrefaustino.fullstackproject.services.exceptions.DataIntegrityException;
import com.andrefaustino.fullstackproject.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repo;
	
	@Autowired
	private AddressRepository addRepo;

	public List<Client> findAll() {
		List<Client> list = repo.findAll();
		return list;
	}

	public Client findById(Integer id) {
		Optional<Client> obj = repo.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Object Not Found! Id: " + id + ", type: " + Client.class.getName()));
	}

	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Client update(Client obj) {
		Client newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void deleteById(Integer id) {
		findById(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("It is NOT allowed to exclude client because of related entitys");
		}
	}

	@Transactional
	public Client insert(Client obj) {
		obj.setId(null);
		obj = repo.save(obj);
		addRepo.saveAll(obj.getAddresses());
		return obj; 
	}
	
	
	
	
	
	public Client fromDTO(ClientDTO objDto) {
		return new Client(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null);
	}
	
	public Client fromDTO(ClientNewDTO objDto) {
		Client cli =  new Client(null, objDto.getName(), objDto.getEmail(), objDto.getCpfcnpj(), ClientType.toEnum(objDto.getClientType()));
		City city = new City(objDto.getCityId(), null, null);
		Address add = new Address(null, objDto.getAddress(), objDto.getNumber(), objDto.getComplement(), objDto.getDistrict(), objDto.getZip(), cli, city);
		cli.getAddresses().add(add);
		cli.getPhoneNumbers().add(objDto.getPhone1());
		if (objDto.getPhone2() != null)      { cli.getPhoneNumbers().add(objDto.getPhone2()); };
		if (objDto.getPhone3() != null)      { cli.getPhoneNumbers().add(objDto.getPhone3()); };
		return cli;
	}
	
	
	private void updateData(Client newObj, Client obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}

}
