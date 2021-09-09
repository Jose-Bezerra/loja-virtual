import java.sql.*;

public class TestaListagem {
    public static void main(String[] args) throws SQLException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection con = connectionFactory.recuperarConexao();

        //Claúsula SELECT no banco equivale a um Statement no JAVA
        PreparedStatement stm = con.prepareStatement("SELECT ID, NOME, DESCRICAO FROM PRODUTO");
        //Essa expressão resulta um boolean. Se true é uma lista, senão não devolve nada
        stm.execute();
        ResultSet rst = stm.getResultSet();

        while (rst.next()) {
            int id = rst.getInt("ID");
            System.out.println(id);
            String nome = rst.getString("NOME");
            System.out.println(nome);
            String descricao = rst.getString("DESCRICAO");
            System.out.println(descricao);
        }

        con.close();
    }
}
