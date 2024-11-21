package fiap.tds.model.bo;

import fiap.tds.model.dao.EnderecoDAO;
import fiap.tds.model.dao.EnderecoDAOImpl;
import fiap.tds.model.vo.Endereco;

import java.sql.SQLException;
import java.util.List;

public class EnderecoBO {
    private EnderecoDAO enderecoDAO;

    public EnderecoBO() throws SQLException {
        this.enderecoDAO = new EnderecoDAOImpl();
    }

    public Endereco inserirEndereco(Endereco endereco) throws SQLException {
        if (endereco == null) {
            throw new IllegalArgumentException("O endereço não pode ser nulo.");
        }
        if (endereco.getCliente() == null || endereco.getCliente().getId_cliente() <= 0) {
            throw new IllegalArgumentException("Cliente inválido para o endereço.");
        }
        if (endereco.getEndereco_completo() == null || endereco.getEndereco_completo().isEmpty()) {
            throw new IllegalArgumentException("O endereço completo não pode estar vazio.");
        }

        return enderecoDAO.inserirEndereco(endereco);
    }

    public void excluirEndereco(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID de endereço inválido.");
        }

        Endereco endereco = enderecoDAO.buscarEnderecoId(id);
        if (endereco == null) {
            throw new IllegalArgumentException("Endereço não encontrado para o ID fornecido.");
        }

        enderecoDAO.excluirEndereco(id);
    }

    public void atualizarEndereco(Endereco endereco) throws SQLException {
        if (endereco == null || endereco.getId_endereco() <= 0) {
            throw new IllegalArgumentException("Endereço ou ID inválido.");
        }
        if (endereco.getCliente() == null || endereco.getCliente().getId_cliente() <= 0) {
            throw new IllegalArgumentException("Cliente inválido para o endereço.");
        }
        if (endereco.getEndereco_completo() == null || endereco.getEndereco_completo().isEmpty()) {
            throw new IllegalArgumentException("O endereço completo não pode estar vazio.");
        }

        Endereco enderecoExistente = enderecoDAO.buscarEnderecoId(endereco.getId_endereco());
        if (enderecoExistente == null) {
            throw new IllegalArgumentException("Endereço não encontrado para atualização.");
        }

        enderecoDAO.atualizarEndereco(endereco);
    }

    public List<Endereco> listarEnderecos() throws SQLException {
        return enderecoDAO.listarEndereco();
    }

    public Endereco buscarEnderecoPorId(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID de endereço inválido.");
        }

        Endereco endereco = enderecoDAO.buscarEnderecoId(id);
        if (endereco == null) {
            throw new IllegalArgumentException("Endereço não encontrado para o ID fornecido.");
        }
        return endereco;
    }

    public Endereco buscarEnderecoPorIdCliente(int idCliente) throws SQLException {
        if (idCliente <= 0) {
            throw new IllegalArgumentException("ID de cliente inválido.");
        }

        Endereco endereco = enderecoDAO.buscarEnderecoPorIdCliente(idCliente);
        if (endereco == null) {
            throw new IllegalArgumentException("Endereço não encontrado para o cliente fornecido.");
        }
        return endereco;
    }
}
