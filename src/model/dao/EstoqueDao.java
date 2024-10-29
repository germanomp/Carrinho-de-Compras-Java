package model.dao;

import model.entities.Produto;

import java.util.List;

public interface EstoqueDao {

    void inserir(Produto produto);
    void alterar(Produto produto);
    void remover(Produto produto);
    Produto buscarPorId(int id);
    List<Produto> listarEstoque();
}
