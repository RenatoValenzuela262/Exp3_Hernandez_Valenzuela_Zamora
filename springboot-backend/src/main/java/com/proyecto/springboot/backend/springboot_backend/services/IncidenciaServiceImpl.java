package com.proyecto.springboot.backend.springboot_backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.springboot.backend.springboot_backend.entities.Incidencia;
import com.proyecto.springboot.backend.springboot_backend.repository.IncidenciaRepository;

@Service
public class IncidenciaServiceImpl implements IncidenciaService {

    @Autowired
    private IncidenciaRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Incidencia> findByAll(){
        return (List<Incidencia>) repository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Incidencia> findById(Long id){
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Incidencia save(Incidencia unaIncidencia){
        return repository.save(unaIncidencia);
    }

    @Override
    @Transactional
    public Optional<Incidencia> delete(Incidencia unaIncidencia){
        Optional<Incidencia> incidenciaOptional = repository.findById(unaIncidencia.getId());
        incidenciaOptional.ifPresent(incidenciaDb ->{
            repository.delete(unaIncidencia);
        });
        return incidenciaOptional;
    }
}
