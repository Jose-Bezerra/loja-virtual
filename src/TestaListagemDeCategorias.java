import dao.ProdutoDAO;
import dao.categoriaDAO;
import factory.ConnectionFactory;
import modelo.Categoria;
import modelo.Produto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TestaListagemDeCategorias {
    public static void main(String[] args) throws SQLException {
        try (Connection connection = new ConnectionFactory().recuperarConexao()){
            categoriaDAO categoriaDAO = new categoriaDAO(connection);
            List<Categoria> listaDeCategorias =  categoriaDAO.listar();
            listaDeCategorias.forEach(ct -> {
                        System.out.println(ct.getNome());//lista categorias
                        try {
                            for (Produto produto : new ProdutoDAO(connection).buscar(ct)) {
                                System.out.println(ct.getNome() + " - " + produto.getNome());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

            });

        }
    }
}
