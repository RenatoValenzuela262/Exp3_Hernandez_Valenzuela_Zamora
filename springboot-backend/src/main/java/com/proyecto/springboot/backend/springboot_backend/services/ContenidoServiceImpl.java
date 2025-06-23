package com.proyecto.springboot.backend.springboot_backend.services;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.springboot.backend.springboot_backend.entities.Contenido;
import com.proyecto.springboot.backend.springboot_backend.repository.ContenidoRepository;

@Service
public class ContenidoServiceImpl implements ContenidoService {

    @Autowired
    private ContenidoRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Contenido> findByAll(){
        return (List<Contenido>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Contenido> findById(Long id){
        return repository.findById(id);
        
    }


    @Override
    @Transactional
    public Contenido save(Contenido unContenido) {
        return repository.save(unContenido);
    }

    @Override
    @Transactional
    public Optional<Contenido> delete(Contenido unContenido) {
        Optional<Contenido> contenidoOptional = repository.findById(unContenido.getId());
        contenidoOptional.ifPresent(cursoDb -> {
            repository.delete(unContenido);
        });
        return contenidoOptional;
    }

}
