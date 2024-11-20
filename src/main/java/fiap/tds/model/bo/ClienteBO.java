package fiap.tds.model.bo;

import fiap.tds.model.dao.ClienteDAO;
import fiap.tds.model.dao.ClienteDAOImpl;
import fiap.tds.model.vo.Cliente;

import java.sql.SQLException;
import java.util.List;

public class ClienteBO {
    private ClienteDAO clienteDAO;

    public ClienteBO() throws SQLException {
        this.clienteDAO = new ClienteDAOImpl();
    }

    public Cliente inserirCliente(Cliente cliente) throws SQLException {
        if (cliente == null || cliente.getNome_cliente() == null || cliente.getNome_cliente().isEmpty()) {
            throw new IllegalArgumentException("Nome do cliente inválido.");
        }

        return clienteDAO.inserirCliente(cliente);
    }

    public boolean atualizarCliente(Cliente cliente) throws SQLException {
        if (cliente == null || cliente.getId_cliente() <= 0) {
            throw new IllegalArgumentException("ID do cliente inválido.");
        } else if (cliente.getNome_cliente() == null || cliente.getNome_cliente().isEmpty()) {
            throw new IllegalArgumentException("Nome do cliente não pode estar vazio.");
        }
        return clienteDAO.atualizarCliente(cliente);
    }


    public Cliente buscarClientePorId(int idCliente) throws SQLException {
        Cliente cliente = clienteDAO.buscarClienteId(idCliente);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não encontrado.");
        }
        return cliente;
    }

    public List<Cliente> listarClientes() throws SQLException {
        return clienteDAO.listarCliente();
    }

    public boolean excluir(int id) throws SQLException {
        Cliente cliente = clienteDAO.buscarClienteId(id);
        if (cliente == null) {
            System.out.println("Cliente não encontrado para exclusão.");
            return false;
        }
        return clienteDAO.excluirCliente(id);
    }
}
