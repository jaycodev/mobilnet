package com.mobilnet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobilnet.dto.ClientFilter;
import com.mobilnet.model.Client;
import com.mobilnet.repository.ClientRepository;
import com.mobilnet.utils.ResultResponse;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client createNew(Client client) {
        return clientRepository.save(client);
    }

    public ResultResponse create(Client client) {
        try {
            client.setIsActive(true);

            Client registered = clientRepository.save(client);

            String fullName = registered.getFirstName() + " " + registered.getLastName();
            String message = String.format("El cliente %s (DNI %s) ha sido registrado exitosamente.",
                    fullName, registered.getDni());

            return new ResultResponse(true, message);

        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResultResponse(false, "Ocurrió un error al registrar el cliente: " + ex.getMessage());
        }
    }

    public List<Client> list() {
        return clientRepository.findAllByOrderByDniDesc();
    }

    public List<Client> listWithFilters(ClientFilter filter) {
        return clientRepository.findAllWithFilter(filter.getActivo());
    }

    public Client findById(String clientId) {
        return clientRepository.findById(clientId).orElseThrow(null);
    }

    public Client updateNew(Client client) {
        return clientRepository.save(client);
    }

    public ResultResponse update(Client client) {
        try {
            Client updated = clientRepository.save(client);

            String fullName = updated.getFirstName() + " " + updated.getLastName();
            String message = String.format("Los datos del cliente %s (DNI %s) han sido actualizados correctamente.",
                    fullName, updated.getDni());

            return new ResultResponse(true, message);

        } catch (Exception ex) {
            return new ResultResponse(false, "Ocurrió un error al actualizar el cliente: " + ex.getMessage());
        }
    }

    public ResultResponse changeStatus(String clientId) {
        Client client = this.findById(clientId);
        boolean action = !client.getIsActive();

        String actionText = action ? "activado" : "desactivado";

        client.setIsActive(action);

        try {
            Client registered = clientRepository.save(client);

            String fullName = registered.getFirstName() + " " + registered.getLastName();
            String message = String.format("El cliente %s (DNI %s) ha sido %s correctamente.",
                    fullName, registered.getDni(), actionText);

            return new ResultResponse(true, message);

        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResultResponse(false,
                    "Ocurrió un error al cambiar el estado del cliente: " + ex.getMessage());
        }
    }
}
