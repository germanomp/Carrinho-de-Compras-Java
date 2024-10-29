package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.CarrinhoDao;
import model.entities.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    }

    @Override
    public void remover(Integer id) {

    }

    @Override
    public List<Produto> listarCarrinho() {
        return List.of();
    }
}
