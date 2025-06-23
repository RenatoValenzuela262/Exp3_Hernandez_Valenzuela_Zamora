package com.proyecto.springboot.backend.springboot_backend.restcontrollers;

import static org.junit.jupiter.api.Assertions.fail;

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
import com.proyecto.springboot.backend.springboot_backend.entities.Usuario;
import com.proyecto.springboot.backend.springboot_backend.services.UsuarioServiceImpl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioRestControllersTest {

  @Autowired
  private MockMvc mockmvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockitoBean
  private UsuarioServiceImpl usuarioserviceimpl;
  private List<Usuario> usuariosLista;

  @Test
  public void verUsuariosTest() throws Exception{
    when(usuarioserviceimpl.findByAll()).thenReturn(usuariosLista);
    mockmvc.perform(get("/api/usuarios")
    .contentType(MediaType.APPLICATION_JSON))
    .andExpect(status().isOk());
  }

  @Test
  public void verunUsuarioTest(){
    Usuario unUsuario = new Usuario(Long.valueOf(203003009), "Usuario1", "usuariouno@duocuc.cl", "contraUsuariouno");
    try{
      when(usuarioserviceimpl.findByRut(Long.valueOf(203003009))).thenReturn(Optional.of(unUsuario));
      mockmvc.perform(get("/api/usuarios/203003009")
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk());
    }
    catch(Exception ex){
      fail("El Testing lanzo un Error"+ ex.getMessage());
    }
  }

  @Test
  public void usuarioNoExisteTest() throws Exception{
    when(usuarioserviceimpl.findByRut(Long.valueOf(203003009))).thenReturn(Optional.empty());
    mockmvc.perform(get("/api/usuarios/203003009")
    .contentType(MediaType.APPLICATION_JSON))
    .andExpect(status().isNotFound());
  }

  @Test
  public void crearUsuarioTest() throws Exception{
    Usuario unUsuario = new Usuario(Long.valueOf(203003009),"Usuario uno","usuariouno@duocuc.cl","contraUsuariouno");
    Usuario otroUsuario = new Usuario(Long.valueOf(204004008),"Usuario dos","usuariodos@duocuc.cl","contraUsuariodos");
    when(usuarioserviceimpl.save(any(Usuario.class))).thenReturn(otroUsuario);
    mockmvc.perform(post("/api/usuarios")
    .contentType(MediaType.APPLICATION_JSON)
    .content(objectMapper.writeValueAsString(unUsuario)))
    .andExpect(status().isCreated());
  }

  @Test
  public void modificarUsuarioTest() throws Exception {
      Usuario usuarioExistente = new Usuario(203003009L, "Usuario uno", "usuariouno@duocuc.cl", "contraUsuariouno");
      Usuario usuarioActualizado = new Usuario(203003009L, "Usuario Actualizado", "nuevo@duocuc.cl", "nuevaContrasenia");

      when(usuarioserviceimpl.findByRut(203003009L)).thenReturn(Optional.of(usuarioExistente));
      when(usuarioserviceimpl.save(any(Usuario.class))).thenReturn(usuarioActualizado);

      mockmvc.perform(put("/api/usuarios/203003009")
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(usuarioActualizado)))
          .andExpect(status().isOk());
  }

  @Test
  public void eliminarUsuarioTest() throws Exception {
      Usuario usuarioExistente = new Usuario(203003009L, "Usuario uno", "usuariouno@duocuc.cl", "contraUsuariouno");

      when(usuarioserviceimpl.findByRut(203003009L)).thenReturn(Optional.of(usuarioExistente));

      when(usuarioserviceimpl.delete(any(Usuario.class))).thenReturn(Optional.of(usuarioExistente));

      mockmvc.perform(delete("/api/usuarios/203003009"))
        .andExpect(status().isNoContent());
  }
}

