import java.sql.*;

public class TestaInsercaoComParametro {
    public static void main(String[] args) throws SQLException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.recuperarConexao();

        //PreparedStatement evita o uso intencional de SQL Injection
        PreparedStatement stm = connection.prepareStatement("INSERT INTO PRODUTO (nome, descricao) VALUES (?, ?)",
                Statement.RETURN_GENERATED_KEYS);
        adicionarVariavel("Smart TV", "LG", stm);
        adicionarVariavel("Ipad", "Apple", stm);
    }

    private static void adicionarVariavel(String nome, String descricao, PreparedStatement stm) throws SQLException {
        stm.setString(1, nome);
        stm.setString(2, descricao);

        if (nome.equals("Ipad")) {
            throw new RuntimeException("Não foi possível adicionar o produto!");
        }

        stm.execute();

        ResultSet rst = stm.getGeneratedKeys();

        while (rst.next()) {
            int id = rst.getInt(1);
            System.out.printf("O id gerado foi %d%n", id);
        }
    }
}
