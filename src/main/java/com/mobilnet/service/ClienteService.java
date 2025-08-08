package com.mobilnet.service;

import java.util.List;

import com.mobilnet.dto.ClienteFilter;
import com.mobilnet.model.Cliente;
import com.mobilnet.utils.ResultadoResponse;

public interface ClienteService {
    Cliente crearClientenew(Cliente cliente);
    ResultadoResponse crearCliente(Cliente cliente);
    List<Cliente> listarClientes();
    List<Cliente> listarFiltros(ClienteFilter filtro);
    Cliente buscarPorId(String id);
    Cliente modificarClientenew(Cliente cliente);
    ResultadoResponse modificarCliente(Cliente cliente);
    ResultadoResponse cambiarEstado(String id);
}
