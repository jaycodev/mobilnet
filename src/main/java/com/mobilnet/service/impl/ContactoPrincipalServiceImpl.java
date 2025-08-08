package com.mobilnet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobilnet.model.ContactoPrincipal;
import com.mobilnet.repository.ContactoPrimarioRepository;
import com.mobilnet.service.ContactoPrincipalService;

import java.util.List;

@Service
public class ContactoPrincipalServiceImpl implements ContactoPrincipalService {

    @Autowired
    private ContactoPrimarioRepository _contactoPrimarioRepository;

    @Override
    public ContactoPrincipal crearContactoPrin(ContactoPrincipal contactoPrincipal) {
        return _contactoPrimarioRepository.save(contactoPrincipal);
    }

    @Override
    public List<ContactoPrincipal> listarContactoPrin() {
        return _contactoPrimarioRepository.findAll();
    }

    @Override
    public ContactoPrincipal buscarPorId(Integer idContacto) {
        return _contactoPrimarioRepository.findById(idContacto).orElse(null)    ;
    }

    @Override
    public ContactoPrincipal actualizarContactoPrin(ContactoPrincipal contactoPrincipal) {
        return _contactoPrimarioRepository.save(contactoPrincipal);
    }

    @Override
    public boolean eliminarContactoPrin(Integer idContacto) {
        if (_contactoPrimarioRepository.existsById(idContacto)) {
            _contactoPrimarioRepository.deleteById(idContacto);
            return true;
        }
        return false;
    }
}

