package jdbcBack.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoJDBC {

    private Connection conexao;

    public void conectar() throws SQLException {
 try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/Registro", "root", "3003");
        } catch (ClassNotFoundException ex) {
            System.out.println("driver não está disponível para acesso ou não existe");
        } catch (SQLException ex) {
            System.out.println("Sintaxe de comando invalida ");
            ex.printStackTrace();
        }
    }

    public Connection getConexao() {
        return conexao;
    }
    public void desconectar() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
                System.out.println("DESCONECTADO COM SUCESSO!");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao desconectar");
        }
    }
}
