import dao.ProdutoDAO;
import factory.ConnectionFactory;
import modelo.Produto;

import java.sql.*;
import java.util.List;

public class TestaInsercaoEListagemComProduto {
    public static void main(String[] args) throws SQLException {
        Produto comoda = new Produto("Cômoda", "Cômoda Vertical");

        try (Connection connection = new ConnectionFactory().recuperarConexao()){
            ProdutoDAO produtoDAO = new ProdutoDAO(connection);
            produtoDAO.salvar(comoda);
            List<Produto> listaProdutos = produtoDAO.listar();
            listaProdutos.stream().forEach(lp -> System.out.println(lp));
        }

    }
}
