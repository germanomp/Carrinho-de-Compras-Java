package model.dao.impl;

import model.dao.CarrinhoDao;
import model.entities.CarrinhoItem;

import java.sql.Connection;
import java.util.List;

public class CarrinhoDaoJDBC implements CarrinhoDao {

    private Connection conn;

    public CarrinhoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void adicionarProduto(CarrinhoItem produto) {

    }

    @Override
    public void atualizarProduto(CarrinhoItem produto) {

    }

    @Override
    public void removerProduto(Integer id) {

    }

    @Override
    public List<CarrinhoItem> listarProdutos() {
        return List.of();
    }

    @Override
    public double calcularTotal() {
        return 0;
    }
}
