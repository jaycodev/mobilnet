package com.mobilnet.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobilnet.dto.ClienteFilter;
import com.mobilnet.model.Cliente;
import com.mobilnet.repository.ClienteRepository;
import com.mobilnet.service.ClienteService;
import com.mobilnet.utils.ResultadoResponse;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente crearClientenew(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public ResultadoResponse crearCliente(Cliente cliente) {
        try {
            cliente.setActivo(true);

            Cliente registrado = clienteRepository.save(cliente);

            String nombreCompleto = registrado.getNombre() + " " + registrado.getApellido();
            String mensaje = String.format("El cliente %s (DNI %s) ha sido registrado exitosamente.",
                    nombreCompleto, registrado.getDniCliente());

            return new ResultadoResponse(true, mensaje);

        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResultadoResponse(false, "Ocurrió un error al registrar el cliente: " + ex.getMessage());
        }
    }

    @Override
    public List<Cliente> listarClientes() {
        return clienteRepository.findAllByOrderByDniClienteDesc();
    }

    @Override
    public List<Cliente> listarFiltros(ClienteFilter filtro) {
        return clienteRepository.findAllWithFilter(filtro.getActivo());
    }

    @Override
    public Cliente buscarPorId(String dni) {
        return clienteRepository.findById(dni).orElseThrow(null);
    }

    @Override
    public Cliente modificarClientenew(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public ResultadoResponse modificarCliente(Cliente cliente) {
        try {
            Cliente actualizado = clienteRepository.save(cliente);

            String nombreCompleto = actualizado.getNombre() + " " + actualizado.getApellido();
            String mensaje = String.format("Los datos del cliente %s (DNI %s) han sido actualizados correctamente.",
                    nombreCompleto, actualizado.getDniCliente());

            return new ResultadoResponse(true, mensaje);

        } catch (Exception ex) {
            return new ResultadoResponse(false, "Ocurrió un error al actualizar el cliente: " + ex.getMessage());
        }
    }

    @Override
    public ResultadoResponse cambiarEstado(String id) {
        Cliente cliente = this.buscarPorId(id);
        boolean accion = !cliente.getActivo();

        String texto = accion ? "activado" : "desactivado";

        cliente.setActivo(accion);

        try {
            Cliente registrado = clienteRepository.save(cliente);

            String nombreCompleto = registrado.getNombre() + " " + registrado.getApellido();
            String mensaje = String.format("El cliente %s (DNI %s) ha sido %s correctamente.",
                    nombreCompleto, registrado.getDniCliente(), texto);
            return new ResultadoResponse(true, mensaje);

        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResultadoResponse(false, "Ocurrió un error al cambiar el estado del cliente: " + ex.getMessage());
        }
    }
}
