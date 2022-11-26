package com.formation.javaformation.services;

import com.formation.javaformation.dto.AddressDTO;
import com.formation.javaformation.dto.CustomerDTO;
import com.formation.javaformation.entities.Address;
import com.formation.javaformation.entities.Avis;
import com.formation.javaformation.entities.Customer;
import com.formation.javaformation.exceptions.CustomerNotFoundException;
import com.formation.javaformation.exceptions.CustomerRegistrationException;
import com.formation.javaformation.repositories.CustomerRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CustomerService {
	private final CustomerRepository customerRepository;
	private static Customer CUSTOMER_NOT_FOUND = null;

	public CustomerService(CustomerRepository customerRepository)  {
		this.customerRepository = customerRepository;
	}
	public CustomerDTO create(CustomerDTO customerDTO) throws CustomerRegistrationException{
		this.checkCustomerData(customerDTO);
		Customer customer = mappingCustomerDTOToCustomer(customerDTO); //pk customer 1?
		customer.setUsername(customerDTO.getUserName().toLowerCase());
		Customer customerCreated = this.customerRepository.save(customer);
		customerDTO.setId(customerCreated.getId());
		return customerDTO; //pk ne veut-il pas de customer comme nom de variable????
	}


	//exo faire en sorte que en mode création le Lastname le firstbname Username soit obligatoire.....
	public CustomerDTO update(Customer customer, CustomerDTO customerDTO)throws CustomerRegistrationException {
		this.checkCustomerData(customerDTO);
		customer.setLastname(customerDTO.getLastName());
		AddressDTO addressDTO = customerDTO.getAddressDTO();
		Address address = customer.getAddress();
		address.setCity(addressDTO.getCity());
		address.setRoad(addressDTO.getRoad());
		address.setZipCode(addressDTO.getZipCode());
		address.setCustomer(customer);
		customer.setAddress(address);
		Customer customerCreated = this.customerRepository.save(customer);
		return customerDTO;

	}

	private void checkCustomerData(CustomerDTO customerDTO) throws CustomerRegistrationException {
		if(!StringUtils.hasText(customerDTO.getFirstName()) || !StringUtils.hasText(customerDTO.getLastName())|| !StringUtils.hasText(customerDTO.getUserName()) ){
			throw new CustomerRegistrationException();
		}

	}


	private Customer mappingCustomerDTOToCustomer(CustomerDTO customerDTO) {
		Customer customer = new Customer();
		customer.setFirstname(customerDTO.getFirstName());
		customer.setLastname(customerDTO.getLastName());
		Address address = new Address();
		address.setCity(customerDTO.getAddressDTO().getCity());
		address.setRoad(customerDTO.getAddressDTO().getRoad());
		address.setZipCode(customerDTO.getAddressDTO().getZipCode());
		address.setCustomer(customer);
		customer.setAddress(address);
		return customer;
	}



	//Crud Find
	public Customer findUsername(String username){
		return customerRepository.findByUsername(username.toLowerCase())
				.orElse(CUSTOMER_NOT_FOUND);

	}

	public List<CustomerDTO> findall(){
		List<Customer> customers = customerRepository.findAll();
		return customers.stream().map(customer->{
			CustomerDTO customerDTO = new CustomerDTO();
			customerDTO.setFirstName(customer.getFirstname());
			customerDTO.setLastName(customer.getLastname());
			AddressDTO adressDTO = Optional.ofNullable(customer.getAddress())
			.map(address -> {
				AddressDTO addressDTO = new AddressDTO();
				addressDTO.setCity(address.getCity());
				addressDTO.setRoad(address.getRoad());
				addressDTO.setZipCode(address.getZipCode());
				return addressDTO;
			}).orElse(null);
			customerDTO.setAddressDTO(adressDTO);
			return customerDTO;




		}).collect(Collectors.toList());
		


	}



	public void create2(Customer customer){
		Avis avis = new Avis();
		avis.setDescription(customer.getAvis());
		customer.addAvis(avis);
		Customer customerCreated = this.customerRepository.save(customer);
	}
}
