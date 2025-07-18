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
import com.proyecto.springboot.backend.springboot_backend.entities.Incidencia;
import com.proyecto.springboot.backend.springboot_backend.services.IncidenciaServiceImpl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.springframework.http.MediaType;

@SpringBootTest
@AutoConfigureMockMvc
public class IncidenciaRestControllersTest {

    @Autowired
    private MockMvc mockmvc;

    @Autowired 
    private ObjectMapper objectMapper;

    @MockitoBean
    private IncidenciaServiceImpl incidenciaserviceimpl;
    private List<Incidencia> incidenciaLista;

    @Test
    public void verIncidenciaTest() throws Exception{
        when(incidenciaserviceimpl.findByAll()).thenReturn(incidenciaLista);
        mockmvc.perform(get("/api/incidencia")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    }

    @Test
    public void verunaIncidenciaTest(){
        Incidencia unaIncidencia = new Incidencia(1L, "Incidencia de prueba", "Activo", "Alta");
        try{
            when(incidenciaserviceimpl.findById(1L)).thenReturn(Optional.of(unaIncidencia));
            mockmvc.perform(get("/api/incidencia/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
        }
        catch(Exception ex){
            fail("El testing lanzo un error" + ex.getMessage());
        }
    }

    @Test
    public void incidenciaNoExisteTest() throws Exception{
        when(incidenciaserviceimpl.findById(10L)).thenReturn(Optional.empty());
        mockmvc.perform(get("/api/incidencia/10")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
    }

    @Test
    public void crearIncidenciaTest() throws Exception{
        Incidencia unaIncidencia = new Incidencia(null, "Curso de matematicas con error en ver lista de alumnos", "Terminado", "Media");
        Incidencia otraIncidencia = new Incidencia(4L, "Error al ver los videos subidos a las clases de ingles", "En revision", "Media");
        when(incidenciaserviceimpl.save(any(Incidencia.class))).thenReturn(otraIncidencia);
        mockmvc.perform(post("/api/incidencia")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(unaIncidencia)))
        .andExpect(status().isCreated());
    }

    @Test
    public void modificarIncidenciaTest() throws Exception {
        Incidencia incidenciaExistente = new Incidencia(1L, "Curso de matematicas con error en ver lista de alumnos", "Terminado", "Media");
        Incidencia incidenciaActualizada = new Incidencia(1L, "Error corregido, revisión pendiente", "En Proceso", "Alta");

        when(incidenciaserviceimpl.findById(1L)).thenReturn(Optional.of(incidenciaExistente));
        when(incidenciaserviceimpl.save(any(Incidencia.class))).thenReturn(incidenciaActualizada);

        mockmvc.perform(put("/api/incidencia/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(incidenciaActualizada)))
            .andExpect(status().isOk());
    }

    @Test
    public void eliminarIncidenciaTest() throws Exception {
        Incidencia incidenciaExistente = new Incidencia(1L, "Curso de matemáticas con error en ver lista de alumnos", "Terminado", "Media");

        when(incidenciaserviceimpl.delete(any(Incidencia.class)))
            .thenReturn(Optional.of(incidenciaExistente));

        mockmvc.perform(delete("/api/incidencia/1")) // ← usa plural si así está en el controlador
            .andExpect(status().isNoContent()); // 204
    }
}
