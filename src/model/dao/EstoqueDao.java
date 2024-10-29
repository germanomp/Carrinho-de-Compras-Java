package model.dao;

import model.entities.Estoque;

import java.util.List;

public interface EstoqueDao {
    void inserir(Estoque estoque);
    void alterar(Estoque estoque);
    void remover(Estoque estoque);
    Estoque buscarPorId(int id);
    List<Estoque> listarEstoque();
}
