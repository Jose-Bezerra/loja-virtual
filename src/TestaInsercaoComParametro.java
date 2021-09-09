import java.sql.*;

public class TestaInsercaoComParametro {
    public static void main(String[] args) throws SQLException {

        String nome = "Mouse'";
        String descricao = "Logitech MX); delete from produto;"; //Perceba o SQL Injection

        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.recuperarConexao();

        //PreparedStatement evita o uso intencional de SQL Injection
        PreparedStatement stm = connection.prepareStatement("INSERT INTO PRODUTO (nome, descricao) VALUES (?, ?)",
                Statement.RETURN_GENERATED_KEYS);
        stm.setString(1, nome);
        stm.setString(2, descricao);

        stm.execute();

        ResultSet rst = stm.getGeneratedKeys();

        while (rst.next()) {
            int id = rst.getInt(1);
            System.out.printf("O id gerado foi %d", id);
        }
    }
}
