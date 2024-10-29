package application;

import model.dao.DaoLoja;
import model.dao.EstoqueDao;
import model.dao.ProdutoDao;
import model.dao.impl.EstoqueDaoJDBC;
import model.entities.Produto;

public class Programa {
    public static void main(String[] args) {

        EstoqueDao estoqueDao = DaoLoja.criarEstoqueDao();

        //System.out.println("teste inserir produto estoque");
        // novoProduto = new Produto(null, "teste", "categoriateste", 200.0, 5);
        //estoqueDao.inserir(novoProduto);
        //System.out.println("Produto inserido. ID = " + novoProduto.getId());

        System.out.println("alterar produto estoque");
        Produto produto = estoqueDao.buscarPorId(2);
        if (produto != null) {
            produto.setValor(2000.0);
            estoqueDao.alterar(produto);
            System.out.println("Produto alterado com sucesso.");
        } else {
            System.out.println("Produto não encontrado.");
        }

    }

}
