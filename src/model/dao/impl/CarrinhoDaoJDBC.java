package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.CarrinhoDao;
import model.dao.DaoLoja;
import model.dao.EstoqueDao;
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
        EstoqueDao estoqueDao = DaoLoja.criarEstoqueDao();
        Produto produtoEstoque = estoqueDao.buscarPorId(produto.getId());

        if (produtoEstoque == null || produtoEstoque.getQuantidade() < produto.getQuantidade()) {
            throw new DbException("Produto inexistente no estoque ou quantidade insuficiente.");
        }

        PreparedStatement st = null;
        try {
            Produto produtoCarrinho = buscarPorNomeECategoria(produto.getNome(), produto.getCategoria());

            if (produtoCarrinho != null) {
                int novaQuantidade = produtoCarrinho.getQuantidade() + produto.getQuantidade();
                produtoCarrinho.setQuantidade(novaQuantidade);
                atualizarQuantidade(produtoCarrinho);
            } else {
                st = conn.prepareStatement(
                        "INSERT INTO carrinho (nome, categoria, valor, quantidade) VALUES (?,?,?,?)",
                        PreparedStatement.RETURN_GENERATED_KEYS
                );

                st.setString(1, produto.getNome());
                st.setString(2, produto.getCategoria());
                st.setDouble(3, produto.getValor());
                st.setInt(4, produto.getQuantidade());

                int rowsAffected = st.executeUpdate();
                if (rowsAffected > 0) {
                    ResultSet rs = st.getGeneratedKeys();
                    if (rs.next()) {
                        produto.setId(rs.getInt(1));
                    }
                    DB.closeResultSet(rs);
                } else {
                    throw new DbException("Erro ao inserir produto no carrinho");
                }
            }

            produtoEstoque.setQuantidade(produtoEstoque.getQuantidade() - produto.getQuantidade());
            estoqueDao.alterar(produtoEstoque);

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    private Produto buscarPorNomeECategoria(String nome, String categoria) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM carrinho WHERE nome = ? AND categoria = ?");
            st.setString(1, nome);
            st.setString(2, categoria);

            rs = st.executeQuery();
            if (rs.next()) {
                return instanciarProduto(rs);
            }
            return null;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public void atualizarQuantidade(Produto produto) {
        EstoqueDao estoqueDao = DaoLoja.criarEstoqueDao();
        Produto produtoCarrinho = buscarPorId(produto.getId());
        if (produtoCarrinho == null) {
            throw new DbException("Produto não encontrado no carrinho");
        }

        Produto produtoEstoque = estoqueDao.buscarPorNomeECategoria(produtoCarrinho.getNome(), produtoCarrinho.getCategoria());
        if (produtoEstoque == null) {
            throw new DbException("Produto não encontrado no estoque");
        }

        int quantidadeAtual = produtoCarrinho.getQuantidade();
        int novaQuantidade = produto.getQuantidade();
        int diferencaQuantidade = novaQuantidade - quantidadeAtual;

        if (diferencaQuantidade > 0) {
            if (produtoEstoque.getQuantidade() < diferencaQuantidade) {
                throw new DbException("Estoque insuficiente para a quantidade desejada");
            }
            produtoEstoque.setQuantidade(produtoEstoque.getQuantidade() - diferencaQuantidade);
        } else if (diferencaQuantidade < 0) {
            produtoEstoque.setQuantidade(produtoEstoque.getQuantidade() - diferencaQuantidade);
        }

        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("UPDATE carrinho SET quantidade = ? WHERE id = ?");
            st.setInt(1, novaQuantidade);
            st.setInt(2, produto.getId());

            int colunasAfetadas = st.executeUpdate();
            if (colunasAfetadas == 0) {
                throw new DbException("Erro ao atualizar quantidade no carrinho");
            }

            estoqueDao.alterar(produtoEstoque);

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void remover(Integer id) {
        EstoqueDao estoqueDao = DaoLoja.criarEstoqueDao();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM carrinho WHERE id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("nome");
                String categoria = rs.getString("categoria");
                int quantidade = rs.getInt("quantidade");

                st = conn.prepareStatement("DELETE FROM carrinho WHERE id = ?");
                st.setInt(1, id);

                int colunasAfetadas = st.executeUpdate();
                if (colunasAfetadas == 0) {
                    throw new DbException("Produto não encontrado no carrinho");
                }

                Produto produtoEstoque = estoqueDao.buscarPorNomeECategoria(nome, categoria);
                if (produtoEstoque != null) {
                    produtoEstoque.setQuantidade(produtoEstoque.getQuantidade() + quantidade);
                    estoqueDao.alterar(produtoEstoque);
                } else {
                    throw new DbException("Produto não encontrado no estoque");
                }

            } else {
                throw new DbException("Produto não encontrado no carrinho");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
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
        List<Produto> lista = new ArrayList<>();
        try {
            st = conn.prepareStatement("SELECT id, nome, categoria, valor, quantidade, (valor * quantidade) AS valor_total FROM carrinho");
            rs = st.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("categoria"),
                        rs.getDouble("valor"),
                        rs.getInt("quantidade")
                );
                produto.setValorTotal(rs.getDouble("valor_total"));
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
