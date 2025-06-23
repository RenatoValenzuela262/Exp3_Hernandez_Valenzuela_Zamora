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
import com.proyecto.springboot.backend.springboot_backend.entities.Curso;
import com.proyecto.springboot.backend.springboot_backend.services.CursoServiceImpl;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@AutoConfigureMockMvc
public class CursoRestControllersTest {

  @Autowired
  private MockMvc mockmvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockitoBean
  private CursoServiceImpl cursoserviceimpl;
  private List<Curso> cursosLista;
  @Test
  public void verProductosTest() throws Exception{
    when(cursoserviceimpl.findByAll()).thenReturn(cursosLista);
    mockmvc.perform(get("/api/cursos")
    .contentType(MediaType.APPLICATION_JSON))
    .andExpect(status().isOk());
  }
  @Test
  public void verunCursoTest(){
    Curso unCurso = new Curso(Long.valueOf(1), "Ingles", "Nivelacion", true);

    try{
      when(cursoserviceimpl.findById(Long.valueOf(1))).thenReturn(Optional.of(unCurso));
      mockmvc.perform(get("/api/cursos/1")
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk());
    }
    catch(Exception ex){
      fail("El Testing lanzo un Error"+ ex.getMessage());
    }

  }
  @Test
  public void cursoNoExisteTest() throws Exception{
    when(cursoserviceimpl.findById(Long.valueOf(1))).thenReturn(Optional.empty());
    mockmvc.perform(get("/api/productos/10")
    .contentType(MediaType.APPLICATION_JSON))
    .andExpect(status().isNotFound());

  }
  @Test
  public void crearProductoTest() throws Exception{
    Curso unCurso = new Curso(Long.valueOf(1), "Ingles", "Nivelacion", true);
    Curso otroCurso = new Curso(Long.valueOf(2), "Lenguaje", "Nivelacion", true);
    when(cursoserviceimpl.save(any(Curso.class))).thenReturn(otroCurso);
    mockmvc.perform(post("/api/cursos")
    .contentType(MediaType.APPLICATION_JSON)
    .content(objectMapper.writeValueAsString(unCurso)))
    .andExpect(status().isCreated());
  }
}

 
