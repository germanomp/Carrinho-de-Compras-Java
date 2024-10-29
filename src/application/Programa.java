package application;

import model.dao.DaoLoja;
import model.dao.EstoqueDao;
import model.dao.ProdutoDao;
import model.dao.impl.EstoqueDaoJDBC;
import model.entities.Produto;

import java.util.Scanner;

public class Programa {
    public static void main(String[] args) {

        EstoqueDao estoqueDao = DaoLoja.criarEstoqueDao();

        Scanner sc = new Scanner(System.in);

        /*
        System.out.println("teste inserir produto estoque");
        Produto novoProduto = new Produto(null, "teste", "categoriateste", 200.0, 5);
        estoqueDao.inserir(novoProduto);
        System.out.println("Produto inserido. ID = " + novoProduto.getId());
        */
        /*
        System.out.println("alterar produto estoque");
        Produto produto = estoqueDao.buscarPorId(2);
        if (produto != null) {
            produto.setValor(2500.0);
            estoqueDao.alterar(produto);
            System.out.println("Produto alterado com sucesso.");
        } else {
            System.out.println("Produto não encontrado.");
        }
         */

        System.out.println("=== Test 1: produto buscaPorId ===");
        Produto produto = estoqueDao.buscarPorId(2);
        System.out.println(produto);

        System.out.println("\n=== Test 6: deletar produto ===");
        System.out.print("Digite o Id do produto a deletar: ");
        int id = sc.nextInt();
        estoqueDao.remover(id);
        System.out.println("removido");

        sc.close();

    }

}
