package com.proyecto.springboot.backend.springboot_backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.springboot.backend.springboot_backend.entities.Cliente;
import com.proyecto.springboot.backend.springboot_backend.repository.ClienteRepository;


@Service
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    private ClienteRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findByAll(){
        return (List<Cliente>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cliente> findById(Long id){
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Cliente save(Cliente unCliente){
        return repository.save(unCliente);
    }
    
    @Override
    @Transactional
    public Optional<Cliente> delete(Cliente unCliente){
        Optional<Cliente> clienteOptional = repository.findById(unCliente.getId());
        clienteOptional.ifPresent(clienteDb ->{
            repository.delete(unCliente);
        });
        return clienteOptional;
    }
}