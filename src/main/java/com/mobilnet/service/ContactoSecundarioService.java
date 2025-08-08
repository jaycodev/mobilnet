package com.mobilnet.service;

import java.util.List;

import com.mobilnet.model.ContactoSecundario;

public interface ContactoSecundarioService {
    ContactoSecundario creaContactoSec (ContactoSecundario contactoSecundario);
    List<ContactoSecundario> lsitarContactoSec();
    ContactoSecundario buscarPorId(Integer idCOntacto);
    ContactoSecundario actualizarContactoSec(ContactoSecundario contactoSecundario);
    boolean eliminarContactoSec(Integer idContacto);
}
