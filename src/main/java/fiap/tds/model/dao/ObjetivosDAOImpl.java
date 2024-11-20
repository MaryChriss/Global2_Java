package fiap.tds.model.dao;

import fiap.tds.credenciais.Credenciais;
import fiap.tds.model.vo.Cliente;
import fiap.tds.model.vo.Objetivos;
import oracle.jdbc.datasource.impl.OracleDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ObjetivosDAOImpl implements ObjetivosDAO {

    private Connection conn;

    public ObjetivosDAOImpl() throws SQLException {
        OracleDataSource ods = new OracleDataSource();
        ods.setURL(Credenciais.url);
        ods.setUser(Credenciais.user);
        ods.setPassword(Credenciais.pwd);
        conn = ods.getConnection();
    }

    @Override
    public Objetivos inserirObjetivos(Objetivos objetivos) throws SQLException {
        String sql = "INSERT INTO tb_objetivos (id_cliente, meta_luz, meta_agua) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql,  new String[] { "id_objetivo" })) {
            pstmt.setInt(1, objetivos.getId_cliente());
            pstmt.setDouble(2, objetivos.getMeta_luz());
            pstmt.setDouble(3, objetivos.getMeta_agua());
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int generatedId = rs.getInt(1);
                    objetivos.setId_cliente(generatedId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return objetivos;
    }




    @Override
    public boolean excluirObjetivos(int id) throws SQLException {
        String sql = "DELETE FROM tb_objetivos WHERE id_objetivo = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    @Override
    public boolean atualizarObjetivos(Objetivos objetivos) throws SQLException {
        String sql = "UPDATE tb_objetivos SET id_cliente = ?, meta_luz = ?, meta_agua = ? WHERE id_objetivo = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, objetivos.getId_cliente());
            pstmt.setDouble(2, objetivos.getMeta_luz());
            pstmt.setDouble(3, objetivos.getMeta_agua());
            pstmt.setInt(4, objetivos.getId_objetivo());
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    @Override
    public List<Objetivos> listarObjetivos() throws SQLException {
        List<Objetivos> objetivosList = new ArrayList<>();
        String sql = "SELECT id_objetivo, id_cliente, meta_luz, meta_agua FROM tb_objetivos";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Objetivos objetivos = new Objetivos(
                        rs.getInt("id_cliente"),
                        rs.getInt("id_objetivo"),
                        rs.getDouble("meta_agua"),
                        rs.getDouble("meta_luz")
                );
                objetivosList.add(objetivos);
            }
        }
        return objetivosList;
    }

    @Override
    public Objetivos buscarObjetivosId(int id) throws SQLException {
        String sql = "SELECT id_objetivo, id_cliente, meta_luz, meta_agua FROM tb_objetivos WHERE id_objetivo = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Objetivos(
                        rs.getInt("id_cliente"),
                        rs.getInt("id_objetivo"),
                        rs.getDouble("meta_agua"),
                        rs.getDouble("meta_luz")
                );
            }
        }
        return null;
    }

    @Override
    public List<Objetivos> buscarObjetivosPorIdCliente(int idCliente) throws SQLException {
        String sql = "SELECT id_objetivo, id_cliente, meta_luz, meta_agua FROM tb_objetivos WHERE id_cliente = ?";
        List<Objetivos> objetivosList = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idCliente);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Objetivos objetivo = new Objetivos(
                            rs.getInt("id_cliente"),
                            rs.getInt("id_objetivo"),
                            rs.getDouble("meta_agua"),
                            rs.getDouble("meta_luz")
                    );
                    objetivosList.add(objetivo);
                }
            }
        }
        return objetivosList;
    }

}
