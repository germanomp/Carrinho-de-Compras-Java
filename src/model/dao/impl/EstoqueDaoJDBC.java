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
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("UPDATE estoque SET nome = ?, categoria = ?, valor = ?, quantidade = ? WHERE id = ?",
                    PreparedStatement.RETURN_GENERATED_KEYS);

            st.setString(1, produto.getNome());
            st.setString(2, produto.getCategoria());
            st.setDouble(3, produto.getValor());
            st.setInt(4, produto.getQuantidade());
            st.setInt(5, produto.getId());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void remover(Produto produto) {

    }

    @Override
    public Produto buscarPorId(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM estoque WHERE id = ?");
            st.setInt(1, id);

            rs = st.executeQuery();
            if (rs.next()) {
                Produto produto = instanciarProduto(rs);
                return produto;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Produto instanciarProduto(ResultSet rs) throws SQLException {
        Produto produto = new Produto();
        produto.setId(rs.getInt("id"));
        produto.setNome(rs.getString("nome"));
        produto.setCategoria(rs.getString("categoria"));
        produto.setValor(rs.getDouble("valor"));
        produto.setQuantidade(rs.getInt("quantidade"));
        return produto;
    }

    @Override
    public List<Produto> listarEstoque() {
        return List.of();
    }


}
