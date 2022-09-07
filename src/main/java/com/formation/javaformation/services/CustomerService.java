package com.formation.javaformation.services;

import com.formation.javaformation.dto.AddressDTO;
import com.formation.javaformation.dto.CustomerDTO;
import com.formation.javaformation.entities.Address;
import com.formation.javaformation.entities.Avis;
import com.formation.javaformation.entities.Customer;
import com.formation.javaformation.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    public CustomerDTO create(CustomerDTO customerDTO){
        Customer customer = new Customer();
        customer.setFirstname(customerDTO.getFirstName());
        customer.setLastname(customerDTO.getLastName());
        AddressDTO addressDTO = customerDTO.getAddressDTO();
        Avis avis = new Avis();
        avis.setDescription(customerDTO.getAvis());
        Address address = new Address();
        address.setCity(addressDTO.getCity());
        address.setRoad(addressDTO.getRoad());
        address.setZipCode(addressDTO.getZipCode());
        address.setCustomer(customer);
        customer.setAddress(address);
        customer.addAvis(avis);
        Customer customerCreated = this.customerRepository.save(customer);
        customerDTO.setId(customerCreated.getId());
        return customerDTO;
    }




    public void create2(Customer customer){
        Avis avis = new Avis();
        avis.setDescription(customer.getAvis());
        customer.addAvis(avis);
        Customer customerCreated = this.customerRepository.save(customer);
    }
}
