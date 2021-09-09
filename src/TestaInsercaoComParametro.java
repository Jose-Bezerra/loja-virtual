import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercaoComParametro {
    public static void main(String[] args) throws SQLException {
        //Informações que simulam a entrada de dados pelo cliente
        String nome = "Mouse'";
        String descricao = "Logitech MX); delete from produto;"; //Perceba o SQL Injection
        // O prejuízo pode ser a exclusão da tabela

        //Inserindo elementos no banco
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.recuperarConexao();

        Statement stm = connection.createStatement();
        stm.execute("INSERT INTO PRODUTO (nome, descricao) VALUES ('" + nome + " ', '"+ descricao + "')",
                Statement.RETURN_GENERATED_KEYS);
        ResultSet rst = stm.getGeneratedKeys();
        while (rst.next()) {
            int id = rst.getInt(1);
            System.out.printf("O id gerado foi %d", id);
        }
    }
}
