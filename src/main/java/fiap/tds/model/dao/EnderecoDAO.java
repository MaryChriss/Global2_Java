package fiap.tds.model.dao;

import fiap.tds.model.vo.Endereco;

import java.sql.SQLException;
import java.util.List;

public interface EnderecoDAO {

    void inserirEndereco (Endereco endereco) throws SQLException;
    void excluirEndereco(int id) throws SQLException;
    void atualizarEndereco(Endereco endereco) throws  SQLException;
    public List<Endereco> listarEndereco() throws SQLException;
    public Endereco buscarEnderecoId(int id) throws SQLException;
    public Endereco buscarEnderecoPorIdCliente(int idCliente) throws SQLException;
}
