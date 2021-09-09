import java.sql.*;

public class TestaListagem {
    public static void main(String[] args) throws SQLException {
        //recuperando a conexão com o banco
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/loja_virtual?" +
                "useTimezone=true&serverTimezone=UTC", "root", "");

        //Claúsula SELECT no banco equivale a um Statement no JAVA
        Statement stm = con.createStatement();
        //Essa expressão resulta um boolean. Se true é uma lista, senão não devolve nada
        stm.execute("SELECT ID, NOME, DESCRICAO FROM PRODUTO");
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
