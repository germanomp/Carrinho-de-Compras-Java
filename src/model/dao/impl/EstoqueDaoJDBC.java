package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.EstoqueDao;
import model.entities.Estoque;
import model.entities.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EstoqueDaoJDBC implements EstoqueDao {

    private Connection conn;

    public EstoqueDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void inserir(Produto produto) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("INSERT INTO estoque (nome, categoria, valor, quantidade) VALUES (?,?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);

            st.setString(1, produto.getNome());
            st.setString(2, produto.getCategoria());
            st.setDouble(3, produto.getValor());
            st.setInt(4, produto.getQuantidade());

            int colunasAfetadas = st.executeUpdate();
            if (colunasAfetadas > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()) {
                    int id = rs.getInt(1);
                    produto.setId(id);
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("Erro ao inserir no estoque");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void alterar(Produto produto) {

    }

    @Override
    public void remover(Produto produto) {

    }

    @Override
    public Produto buscarPorId(int id) {
        return null;
    }

    @Override
    public List<Produto> listarEstoque() {
        return List.of();
    }


}
