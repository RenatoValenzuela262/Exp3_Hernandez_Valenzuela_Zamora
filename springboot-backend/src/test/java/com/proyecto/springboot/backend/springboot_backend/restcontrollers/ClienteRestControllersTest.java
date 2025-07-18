package com.proyecto.springboot.backend.springboot_backend.restcontrollers;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


import static org.junit.jupiter.api.Assertions.fail;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.springboot.backend.springboot_backend.entities.Cliente;
import com.proyecto.springboot.backend.springboot_backend.services.ClienteServiceImpl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.springframework.http.MediaType;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteRestControllersTest {

    @Autowired
    private MockMvc mockmvc;

    @Autowired 
    private ObjectMapper objectMapper;

    @MockitoBean
    private ClienteServiceImpl clienteserviceimpl;
    private List<Cliente> clienteLista;

    @Test
    public void verIncidenciaTest() throws Exception{
        when(clienteserviceimpl.findByAll()).thenReturn(clienteLista);
        mockmvc.perform(get("/api/cliente")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    }

    @Test
    public void verunClienteTest(){
        Cliente unCliente = new Cliente(1L, "Cristiano Ronaldo", "Cr7@gmail.com", "Bajos de mena", "Visa");
        try{
            when(clienteserviceimpl.findById(1L)).thenReturn(Optional.of(unCliente));
            mockmvc.perform(get("/api/cliente/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
        }
        catch(Exception ex){
            fail("El testing lanzo un error" + ex.getMessage());
        }
    }

    @Test
    public void clienteNoExisteTest() throws Exception{
        when(clienteserviceimpl.findById(10L)).thenReturn(Optional.empty());
        mockmvc.perform(get("/api/cliente/10")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
    }

    @Test
    public void crearClienteTest() throws Exception{
        Cliente unCliente = new Cliente(null, "Alexis Sanchez", "ninomaravilla@gmail.com", "Tocopilla", "Mastercard");
        Cliente otroCliente = new Cliente(4L, "Kylian Mbappe", "km@hotmail.com", "La pintana", "Visa");
        when(clienteserviceimpl.save(any(Cliente.class))).thenReturn(otroCliente);
        mockmvc.perform(post("/api/cliente")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(unCliente)))
        .andExpect(status().isCreated());
    }

    @Test
    public void modificarClienteTest() throws Exception {
        Cliente clienteExistente = new Cliente(1L, "Cristiano Ronaldo", "cr7@gmail.com", "Bajos de Mena", "Visa");
        Cliente clienteActualizado = new Cliente(1L, "Cristiano Ronaldo", "cr7nuevo@gmail.com", "La Florida", "Mastercard");

        when(clienteserviceimpl.findById(1L)).thenReturn(Optional.of(clienteExistente));
        when(clienteserviceimpl.save(any(Cliente.class))).thenReturn(clienteActualizado);
        mockmvc.perform(put("/api/cliente/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(clienteActualizado)))
            .andExpect(status().isOk());
    }


    @Test
    public void eliminarClienteTest() throws Exception {
        Cliente clienteExistente = new Cliente(1L, "Cristiano Ronaldo", "cr7@gmail.com", "Bajos de Mena", "Visa");

        when(clienteserviceimpl.findById(1L)).thenReturn(Optional.of(clienteExistente));

        when(clienteserviceimpl.delete(any(Cliente.class))).thenReturn(Optional.of(clienteExistente));

        mockmvc.perform(delete("/api/cliente/1")).andExpect(status().isNoContent());
    } 
}
