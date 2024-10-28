package model.dao;

import model.entities.Produto;

import java.util.List;

public interface ProdutoDao {
    public void inserir(Produto produto);
    public void alterar(Produto produto);
    public void remover(Produto produto);
    Produto buscarPorId(int id);
    List<Produto> listarProdutos();
    double calcularTotal();
}
