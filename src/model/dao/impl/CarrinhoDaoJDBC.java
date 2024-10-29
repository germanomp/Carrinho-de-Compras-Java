package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.CarrinhoDao;
import model.entities.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarrinhoDaoJDBC implements CarrinhoDao {

    private Connection conn;

    public CarrinhoDaoJDBC(Connection conn) {
        this.conn = conn;
    }


    @Override
    public void inserir(Produto produto) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("INSERT INTO carrinho (nome, categoria, valor, quantidade) VALUES (?,?,?,?)",
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
                throw new DbException("Erro ao inserir no carrinho");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void atualizarQuantidade(Produto produto) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("UPDATE carrinho SET quantidade = ? WHERE id = ?");
            st.setInt(1, produto.getQuantidade());
            st.setInt(2, produto.getId());

            int colunasAfetadas = st.executeUpdate();
            if (colunasAfetadas == 0) {
                throw new DbException("Produto não encontrado no carrinho");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void remover(Integer id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM carrinho WHERE id = ?");
            st.setInt(1, id);

            int colunasAfetadas = st.executeUpdate();
            if (colunasAfetadas == 0) {
                throw new DbException("Produto não encontrado no carrinho");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Produto buscarPorId(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM carrinho WHERE id = ?");
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
    public List<Produto> listarCarrinho() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM carrinho");
            rs = st.executeQuery();

            List<Produto> lista = new ArrayList<>();

            while (rs.next()) {
                Produto produto = instanciarProduto(rs);
                lista.add(produto);
            }
            return lista;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
}
