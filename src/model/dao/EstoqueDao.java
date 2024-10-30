package model.dao;

import model.entities.Produto;

import java.util.List;

public interface EstoqueDao {

    void inserir(Produto produto);
    void alterar(Produto produto);
    void remover(Integer id);
    Produto buscarPorId(Integer id);
    Produto buscarPorNomeECategoria(String nome, String categoria);
    List<Produto> listarEstoque();
}
