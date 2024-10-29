package model.dao;

import model.entities.CarrinhoItem;

import java.util.List;

public interface CarrinhoDao {

    void adicionarProduto(CarrinhoItem produto);
    void atualizarProduto(CarrinhoItem produto);
    void removerProduto(Integer id);
    List<CarrinhoItem> listarProdutos();
    double calcularTotal();
}
