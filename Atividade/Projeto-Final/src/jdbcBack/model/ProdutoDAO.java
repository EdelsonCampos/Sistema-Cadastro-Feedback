package jdbcBack.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    // TO DO : CRUD!

    public static boolean cadastrar(Cadastro p) throws SQLException {
        try {
            ConexaoJDBC conexao = new ConexaoJDBC();
            conexao.conectar();

            String sql = "INSERT INTO cadastro(Funcionario, Registro, Cargo) VALUES (?, ?, ?);"; // Alterado para novos nomes de colunas

            PreparedStatement query = conexao.getConexao().prepareStatement(sql);
            query.setString(1, p.getFuncionario()); // Alterado para 'getFuncionario'
            query.setDate(2, java.sql.Date.valueOf(p.getRegistro())); // Alterado para tipo de dado DATE
            query.setString(3, p.getCargo()); // Alterado para 'getCargo'

            // Executar a instrução SQL
            query.execute();

            conexao.desconectar();
            return true;
        } catch (SQLException se) {
            System.out.println("Erro ao cadastrar registro no banco de dados");
            return false;
        }
    }

    public static List<Cadastro> listarTodos() {
        // Declaração da variável lista que será retornada
        List<Cadastro> lista = new ArrayList<>();

        try {
            ConexaoJDBC conexao = new ConexaoJDBC();
            conexao.conectar();

            String sql = "SELECT * FROM cadastro";
            PreparedStatement consulta = conexao.getConexao().prepareStatement(sql);

            ResultSet resposta = consulta.executeQuery();

            while (resposta.next()) {
                Cadastro p = new Cadastro();
                p.setId(resposta.getInt("id"));
                p.setFuncionario(resposta.getString("Funcionario")); // Alterado para 'setFuncionario'
                p.setRegistro(resposta.getDate("Registro").toLocalDate()); // Alterado para tipo de dado DATE
                p.setCargo(resposta.getString("Cargo")); // Alterado para 'setCargo'

                lista.add(p);
            }

            conexao.desconectar();
            return lista;
        } catch (SQLException e) {
            System.out.println("Erro ao listar os registros do banco de dados!");
            e.printStackTrace();
        }
        return lista;
    }

    public static boolean atualizar(Cadastro p) {
        try {
            ConexaoJDBC conexao = new ConexaoJDBC();
            conexao.conectar();

            // Instrução SQL
            String sql = "UPDATE cadastro SET Funcionario=?, Registro=?, Cargo=? WHERE id=?;"; // Alterado para novos nomes de colunas
            PreparedStatement consulta = conexao.getConexao().prepareStatement(sql);

            // Passar as informações do objeto para a consulta
            consulta.setString(1, p.getFuncionario()); // Alterado para 'getFuncionario'
            consulta.setDate(2, java.sql.Date.valueOf(p.getRegistro())); // Alterado para tipo de dado DATE
            consulta.setString(3, p.getCargo()); // Alterado para 'getCargo'
            consulta.setInt(4, p.getId());

            // Executar a instrução
            consulta.execute();

            // Desconectar do Banco
            conexao.desconectar();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar o registro no banco de dados");
            return false;
        }
    }

    public static boolean excluir(int id) {
        try {
            ConexaoJDBC conexao = new ConexaoJDBC();
            conexao.conectar();

            String sql = "DELETE FROM cadastro WHERE id=?;";
            PreparedStatement consulta = conexao.getConexao().prepareStatement(sql);

            consulta.setInt(1, id);

            // Executa a instrução SQL
            consulta.execute();

            // Desconectar do banco
            conexao.desconectar();
            return true;
        } catch (SQLException s) {
            System.out.println("Erro ao deletar registro no banco de dados!");
            return false;
        }
    }
}
