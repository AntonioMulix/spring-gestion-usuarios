package com.example.demo.serviceImpl;

import com.example.demo.DTO.ClienteDTO;
import com.example.demo.entity.Cliente;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.service.ClienteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author marco-romero
 */
@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // ********************** Clientes **************************
    //Listar todos los clientes
    @Transactional(readOnly = true)
    @Override
    public List<Cliente> findAllClientes() {
        return clienteRepository.listarClientes();
    }

    //Guardar Nuevo Cliente
    @Transactional
    @Override
    public Cliente saveCliente(ClienteDTO clienteDTO) {
        Cliente guardarCliente = new Cliente();
        guardarCliente.setNombre(clienteDTO.getNombre());
        guardarCliente.setApellido(clienteDTO.getApellido());
        guardarCliente.setEmail(clienteDTO.getEmail());
        clienteRepository.save(guardarCliente); //Guardamos los datos en la tabla
        Cliente clienteGuardado = guardarCliente;
        return clienteGuardado; //Retornar el objeto guardado
    }

    //Buscar cliente
    @Transactional(readOnly = true)
    @Override
    public Cliente findClienteById(Integer id) {
        return clienteRepository.findClienteById(id);
    }

    //Actualizar cliente
    @Transactional
    @Override
    public Integer updateCliente(Integer id, ClienteDTO clienteDTO) {
        Cliente findCliente = clienteRepository.findClienteById(id);
        if (findCliente != null) {
            findCliente.setNombre(clienteDTO.getNombre());
            findCliente.setApellido(clienteDTO.getApellido());
            findCliente.setEmail(clienteDTO.getEmail());
            clienteRepository.save(findCliente);
            return 1; //Retornar un entero 1 en caso de exito;
        } else {
            return 2; //Retornar un entero 2 en caso que no existe el id en la tabla cliente
        }

    }

    //Eliminar cliente
    @Transactional
    @Override
    public Integer deleteCliente(Integer id) {
        Cliente eliminarCliente = clienteRepository.findClienteById(id);
        clienteRepository.delete(eliminarCliente);
        return 1; // Retornar un 1, en caso de eliminacion exitosa
    }

}
