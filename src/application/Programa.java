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

        boolean running = true;
        while (running) {
            System.out.println("=== Menu ===");
            System.out.println("1. Listar produtos em estoque");
            System.out.println("2. Listar produtos no carrinho");
            System.out.println("3. Adicionar produto ao carrinho");
            System.out.println("4. Atualizar quantidade no carrinho");
            System.out.println("5. Remover produto do carrinho");
            System.out.println("6. Adicionar produto ao estoque");
            System.out.println("7. Alterar produto no estoque");
            System.out.println("8. Remover produto do estoque");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int option = sc.nextInt();
            switch (option) {
                case 1:
                    List<Produto> produtosEmEstoque = estoqueDao.listarEstoque();
                    System.out.println("=== Produtos em Estoque ===");
                    for (Produto produto : produtosEmEstoque) {
                        System.out.println(produto.toStringSemValorTotal());
                    }
                    break;

                case 2:
                    List<Produto> carrinho = carrinhoDao.listarCarrinho();
                    System.out.println("Produtos no carrinho:");
                    double valorTotalCarrinho = 0;

                    for (Produto p : carrinho) {
                        System.out.println(p);
                        valorTotalCarrinho += p.getValorTotal();
                    }

                    System.out.printf("Valor total do carrinho: R$ %.2f%n", valorTotalCarrinho);
                    break;

                case 3:
                    System.out.print("Digite o ID do produto: ");
                    int idProdutoAdicionar = sc.nextInt();
                    System.out.print("Digite a quantidade: ");
                    int quantidadeAdicionar = sc.nextInt();
                    Produto produtoAdicionar = estoqueDao.buscarPorId(idProdutoAdicionar);
                    if (produtoAdicionar != null) {
                        produtoAdicionar.setQuantidade(quantidadeAdicionar);
                        carrinhoDao.inserir(produtoAdicionar);
                        System.out.println("Produto adicionado ao carrinho.");
                    } else {
                        System.out.println("Produto não encontrado no estoque.");
                    }
                    break;

                case 4:
                    System.out.print("Digite o ID do produto no carrinho: ");
                    int idProdutoAtualizar = sc.nextInt();
                    System.out.print("Digite a nova quantidade: ");
                    int novaQuantidade = sc.nextInt();
                    Produto produtoAtualizar = new Produto();
                    produtoAtualizar.setId(idProdutoAtualizar);
                    produtoAtualizar.setQuantidade(novaQuantidade);
                    carrinhoDao.atualizarQuantidade(produtoAtualizar);
                    System.out.println("Quantidade atualizada.");
                    break;

                case 5:
                    System.out.print("Digite o ID do produto a remover: ");
                    int idProdutoRemover = sc.nextInt();
                    carrinhoDao.remover(idProdutoRemover);
                    System.out.println("Produto removido do carrinho.");
                    break;

                case 6:
                    System.out.print("Digite o nome do produto: ");
                    sc.nextLine();
                    String nomeProdutoAdicionar = sc.nextLine();
                    System.out.print("Digite a categoria do produto: ");
                    String categoriaProdutoAdicionar = sc.nextLine();
                    System.out.print("Digite o valor do produto: ");
                    double valorProdutoAdicionar = sc.nextDouble();
                    System.out.print("Digite a quantidade do produto: ");
                    int quantidadeProdutoAdicionar = sc.nextInt();

                    Produto novoProduto = new Produto(null, nomeProdutoAdicionar, categoriaProdutoAdicionar, valorProdutoAdicionar, quantidadeProdutoAdicionar);
                    estoqueDao.inserir(novoProduto);
                    System.out.println("Produto adicionado ao estoque.");
                    break;

                case 7:
                    System.out.print("Digite o ID do produto a atualizar: ");
                    int idProdutoAtualizarEstoque = sc.nextInt();
                    Produto produtoParaAtualizar = estoqueDao.buscarPorId(idProdutoAtualizarEstoque);
                    if (produtoParaAtualizar != null) {
                        System.out.print("Digite o novo nome do produto: ");
                        sc.nextLine();
                        String novoNome = sc.nextLine();
                        System.out.print("Digite a nova categoria do produto: ");
                        String novaCategoria = sc.nextLine();
                        System.out.print("Digite o novo valor do produto: ");
                        double novoValor = sc.nextDouble();
                        System.out.print("Digite a nova quantidade do produto: ");
                        int novaQuantidadeEstoque = sc.nextInt();

                        produtoParaAtualizar.setNome(novoNome);
                        produtoParaAtualizar.setCategoria(novaCategoria);
                        produtoParaAtualizar.setValor(novoValor);
                        produtoParaAtualizar.setQuantidade(novaQuantidadeEstoque);
                        estoqueDao.alterar(produtoParaAtualizar);
                        System.out.println("Produto atualizado no estoque.");
                    } else {
                        System.out.println("Produto não encontrado no estoque.");
                    }
                    break;

                case 8:
                    System.out.print("Digite o ID do produto a remover: ");
                    int idProdutoRemoverEstoque = sc.nextInt();
                    estoqueDao.remover(idProdutoRemoverEstoque);
                    System.out.println("Produto removido do estoque.");
                    break;

                case 0:
                    running = false;
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
            System.out.println();
        }


        sc.close();
        System.out.println("Programa encerrado.");
    }

}
