package application;

import model.dao.DaoLoja;
import model.dao.EstoqueDao;
import model.dao.ProdutoDao;
import model.dao.impl.EstoqueDaoJDBC;
import model.entities.Produto;

public class Programa {
    public static void main(String[] args) {

        EstoqueDao estoqueDao = DaoLoja.criarEstoqueDao();

        System.out.println("teste inserir produto estoque");
        Produto novoProduto = new Produto(null, "teste", "categoriateste", 200.0, 5);
        estoqueDao.inserir(novoProduto);
        System.out.println("Produto inserido. ID = " + novoProduto.getId());

    }

}
