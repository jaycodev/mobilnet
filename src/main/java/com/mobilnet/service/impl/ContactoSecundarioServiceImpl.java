package com.mobilnet.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobilnet.model.ContactoSecundario;
import com.mobilnet.repository.ContactoSecundarioRepository;
import com.mobilnet.service.ContactoSecundarioService;

@Service
public class ContactoSecundarioServiceImpl implements ContactoSecundarioService {

    @Autowired
    private ContactoSecundarioRepository contactoSecundarioRepository;

    @Override
    public ContactoSecundario creaContactoSec(ContactoSecundario contactoSecundario) {
        return contactoSecundarioRepository.save(contactoSecundario);
    }

    @Override
    public List<ContactoSecundario> lsitarContactoSec() {
        return contactoSecundarioRepository.findAll();
    }

    @Override
    public ContactoSecundario buscarPorId(Integer idContacto) {
        return contactoSecundarioRepository.findById(idContacto).orElse(null);
    }

    @Override
    public ContactoSecundario actualizarContactoSec(ContactoSecundario contactoSecundario) {
        return contactoSecundarioRepository.save(contactoSecundario);
    }

    @Override
    public boolean eliminarContactoSec(Integer idContacto) {
        if (contactoSecundarioRepository.existsById(idContacto)) {
            contactoSecundarioRepository.deleteById(idContacto);
            return true;
        }
        return false;
    }
}
