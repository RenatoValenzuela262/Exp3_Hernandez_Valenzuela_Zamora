package com.proyecto.springboot.backend.springboot_backend.restcontrollers;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.springboot.backend.springboot_backend.entities.Contenido;
import com.proyecto.springboot.backend.springboot_backend.services.ContenidoServiceImpl;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
@SpringBootTest

@AutoConfigureMockMvc
public class ContenidoRestControllersTest {

  @Autowired
  private MockMvc mockmvc;
  @Autowired
  private ObjectMapper objectMapper;
  @MockitoBean
  private ContenidoServiceImpl contenidoserviceimpl;
  private List<Contenido> contenidosLista;
  @Test
  public void verContenidosTest() throws Exception{
    when(contenidoserviceimpl.findByAll()).thenReturn(contenidosLista);
    mockmvc.perform(get("/api/contenido")
    .contentType(MediaType.APPLICATION_JSON))
    .andExpect(status().isOk());
  }
  @Test
  public void verunContenidoTest(){
    Contenido unContenido = new Contenido(Long.valueOf(1), "Ingles", "Materia");
    try{
      when(contenidoserviceimpl.findById(Long.valueOf(1))).thenReturn(Optional.of(unContenido));
      mockmvc.perform(get("/api/contenido/1")
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk());
    }
    catch(Exception ex){
      fail("El Testing lanzo un Error"+ ex.getMessage());
    }
  }

  @Test
  public void contenidoNoExisteTest() throws Exception{
    when(contenidoserviceimpl.findById(Long.valueOf(1))).thenReturn(Optional.empty());
    mockmvc.perform(get("/api/contenido/1")
    .contentType(MediaType.APPLICATION_JSON))
    .andExpect(status().isNotFound());
  }

  @Test
  public void crearContenidoTest() throws Exception{
    Contenido unContenido = new Contenido(Long.valueOf(1), "Matematicas", "Materia");
    Contenido otroContenido = new Contenido(Long.valueOf(2), "Lenguaje", "Materia");
    when(contenidoserviceimpl.save(any(Contenido.class))).thenReturn(otroContenido);
    mockmvc.perform(post("/api/contenido")
    .contentType(MediaType.APPLICATION_JSON)
    .content(objectMapper.writeValueAsString(unContenido)))
    .andExpect(status().isCreated());
  }
}

 