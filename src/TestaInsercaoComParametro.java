import java.sql.*;

public class TestaInsercaoComParametro {
    public static void main(String[] args) throws SQLException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        try (Connection connection = connectionFactory.recuperarConexao()) {
            //Assumindo o controle das transações no JDBC
            connection.setAutoCommit(false);

            //try com recurso, elimina o close()
            try (PreparedStatement stm = connection.prepareStatement("INSERT INTO PRODUTO (nome, descricao) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS)) {
                adicionarVariavel("Smart TV", "LG", stm);
                adicionarVariavel("Ipad", "Apple", stm);
                //Como o controle do banco é do desenvolvedor, o commit é essencial
                connection.commit();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Rollback Executado");
                //Rollback é essencial também
                connection.rollback();
            }
        }
    }

    private static void adicionarVariavel(String nome, String descricao, PreparedStatement stm) throws SQLException {
        stm.setString(1, nome);
        stm.setString(2, descricao);

        if (nome.equals("Ipad")) {
            throw new RuntimeException("Não foi possível adicionar o produto!");
        }

        stm.execute();

        try (ResultSet rst = stm.getGeneratedKeys()) {

            while (rst.next()) {
                int id = rst.getInt(1);
                System.out.printf("O id gerado foi %d%n", id);
            }
        }
    }
}
