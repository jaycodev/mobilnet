package com.mobilnet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobilnet.model.SecondaryContact;
import com.mobilnet.repository.SecondaryContactRepository;

@Service
public class SecondaryContactService {

    @Autowired
    private SecondaryContactRepository secondaryContactRepository;

    public SecondaryContact create(SecondaryContact secondaryContact) {
        return secondaryContactRepository.save(secondaryContact);
    }

    public List<SecondaryContact> list() {
        return secondaryContactRepository.findAll();
    }

    public SecondaryContact findById(Integer contactId) {
        return secondaryContactRepository.findById(contactId).orElse(null);
    }

    public SecondaryContact update(SecondaryContact secondaryContact) {
        return secondaryContactRepository.save(secondaryContact);
    }

    public boolean delete(Integer contactId) {
        if (secondaryContactRepository.existsById(contactId)) {
            secondaryContactRepository.deleteById(contactId);
            return true;
        }
        return false;
    }
}
