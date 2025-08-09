package com.mobilnet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobilnet.model.MainContact;
import com.mobilnet.repository.MainContactRepository;

@Service
public class MainContactService {
    @Autowired
    private MainContactRepository mainContactRepository;

    public MainContact create(MainContact mainContact) {
        return mainContactRepository.save(mainContact);
    }

    public List<MainContact> list() {
        return mainContactRepository.findAll();
    }

    public MainContact findById(Integer id) {
        return mainContactRepository.findById(id).orElse(null);
    }

    public MainContact update(MainContact contactoPrincipal) {
        return mainContactRepository.save(contactoPrincipal);
    }

    public boolean delete(Integer id) {
        if (mainContactRepository.existsById(id)) {
            mainContactRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
