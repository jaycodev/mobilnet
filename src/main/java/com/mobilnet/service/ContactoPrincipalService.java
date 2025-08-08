package com.mobilnet.service;

import java.util.List;

import com.mobilnet.model.ContactoPrincipal;

public interface ContactoPrincipalService {
    ContactoPrincipal crearContactoPrin(ContactoPrincipal contactoPrincipal);
    List<ContactoPrincipal> listarContactoPrin();
    ContactoPrincipal buscarPorId(Integer idCOntacto);
    ContactoPrincipal actualizarContactoPrin(ContactoPrincipal contactoPrincipal);
    boolean eliminarContactoPrin(Integer idContacto);
}
