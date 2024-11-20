package fiap.tds.model.bo;

import fiap.tds.model.dao.ObjetivosDAO;
import fiap.tds.model.dao.ObjetivosDAOImpl;
import fiap.tds.model.vo.Objetivos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ObjetivosBO {
    private ObjetivosDAO objetivosDAO;

    public ObjetivosBO() throws SQLException {
        this.objetivosDAO = new ObjetivosDAOImpl();
    }

    public Objetivos inserirObjetivo(Objetivos objetivo) throws SQLException {
        if (objetivo == null || objetivo.getMeta_luz() <= 0 || objetivo.getMeta_agua() <= 0) {
            throw new IllegalArgumentException("Metas de consumo inválidas. Certifique-se de que as metas sejam maiores que zero.");
        }

        if (objetivo.getId_cliente() <= 0) {
            throw new IllegalArgumentException("ID do cliente inválido.");
        }

        return objetivosDAO.inserirObjetivos(objetivo);
    }

    public boolean atualizarObjetivo(Objetivos objetivo) throws SQLException {
        if (objetivo == null || objetivo.getId_objetivo() <= 0) {
            throw new IllegalArgumentException("ID do objetivo inválido.");
        }

        return objetivosDAO.atualizarObjetivos(objetivo);
    }

    public boolean excluirObjetivo(int idObjetivo) throws SQLException {
        if (idObjetivo <= 0) {
            throw new IllegalArgumentException("ID do objetivo inválido.");
        }

        return objetivosDAO.excluirObjetivos(idObjetivo);
    }

    public List<Objetivos> listarObjetivos() throws SQLException {
        return objetivosDAO.listarObjetivos();
    }

    public Objetivos buscarObjetivoPorId(int idObjetivo) throws SQLException {
        if (idObjetivo <= 0) {
            throw new IllegalArgumentException("ID do objetivo inválido.");
        }

        return objetivosDAO.buscarObjetivosId(idObjetivo);
    }

    public List<Objetivos> buscarObjetivoPorIdCliente(int idCliente) throws SQLException {
        if (idCliente <= 0) {
            throw new IllegalArgumentException("ID do cliente inválido.");
        }

        List<Objetivos> objetivos = objetivosDAO.buscarObjetivosPorIdCliente(idCliente);
        if (objetivos == null) {
            return new ArrayList<>();
        }
        return objetivos;
    }

}
