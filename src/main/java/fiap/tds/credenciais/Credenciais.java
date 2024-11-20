package fiap.tds.credenciais;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Credenciais {
    public static final String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
    public static final String user = "RM554773";
    public static final String pwd = "050506";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC do Oracle não encontrado!", e);
        }
        return DriverManager.getConnection(url, user, pwd);
    }
}
