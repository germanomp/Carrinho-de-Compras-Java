package model.dao;

import model.entities.Produto;

import java.util.List;

public interface CarrinhoDao {

    void inserir(Produto produto);
    void atualizarQuantidade(Produto produto);
    void remover(Integer id);
    Produto buscarPorId(Integer id);
    List<Produto> listarCarrinho();
}
