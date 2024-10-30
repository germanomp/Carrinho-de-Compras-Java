package application;

import model.dao.CarrinhoDao;
import model.dao.DaoLoja;
import model.dao.EstoqueDao;
import model.entities.Produto;

import java.util.List;
import java.util.Scanner;


public class Programa {
    public static void main(String[] args) {

        EstoqueDao estoqueDao = DaoLoja.criarEstoqueDao();

        CarrinhoDao carrinhoDao = DaoLoja.criarCarrinhoDao();

        Scanner sc = new Scanner(System.in);


        System.out.println("\n=== Test 3: Listar todo o estoque ===");
        List<Produto> lista = estoqueDao.listarEstoque();
        for (Produto p : lista) {
            System.out.println(p);
        }

        System.out.println("\n=== Test 3: Listar todo o carrinho ===");
        List<Produto> lista2 = carrinhoDao.listarCarrinho();
        for (Produto p : lista2) {
            System.out.println(p);
        }

        //System.out.println("teste inserir produto carrinho");
        //Produto novoProduto = new Produto(1, "Celular", "Eletrônicos", 1000.0, 1);
        //carrinhoDao.inserir(novoProduto);
        //System.out.println("Produto inserido. ID = " + novoProduto.getId());


        System.out.println("alterar produto carrinho");
        Produto produto = carrinhoDao.buscarPorId(3);
        if (produto != null) {
            produto.setQuantidade(1);
            carrinhoDao.atualizarQuantidade(produto);
            System.out.println("Produto alterado com sucesso.");
        } else {
            System.out.println("Produto não encontrado.");
        }
/*

        System.out.println("\n=== Test 6: deletar produto ===");
        System.out.print("Digite o Id do produto a deletar: ");
        int id = sc.nextInt();
        carrinhoDao.remover(id);
        System.out.println("removido");
*/




        //System.out.println("=== Test 1: produto buscaPorId ===");
        //Produto produto = carrinhoDao.buscarPorId(1);
        //System.out.println(produto);

        //estoqueDao.inserir(new Produto(null, "Produto A", "Categoria A", 10.0, 5));
        //estoqueDao.inserir(new Produto(null, "Produto B", "Categoria B", 15.0, 2));


        //System.out.println("=== Test 1: produto buscaPorId ===");
        //Produto produto = estoqueDao.buscarPorId(2);
        //System.out.println(produto);

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

        /*
        System.out.println("\n=== Test 6: deletar produto ===");
        System.out.print("Digite o Id do produto a deletar: ");
        int id = sc.nextInt();
        estoqueDao.remover(id);
        System.out.println("removido");
        */

        sc.close();

    }

}
