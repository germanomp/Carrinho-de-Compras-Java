package model.entities;

public class Carrinho {

    private Integer id;
    private String nome;
    private String categoria;
    private Double valor;
    private Integer quantidade;
    private Double valorTotal;

    public Carrinho() {}

    public Carrinho(Integer id, String nome, String categoria, Double valor, Integer quantidade) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.valor = valor;
        this.quantidade = quantidade;
        this.valorTotal = calcularValorTotal();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
        this.valorTotal = calcularValorTotal();
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
        this.valorTotal = calcularValorTotal();
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    private Double calcularValorTotal() {
        return this.valor * this.quantidade;
    }

    @Override
    public String toString() {
        return "Carrinho{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", categoria='" + categoria + '\'' +
                ", valor=" + valor +
                ", quantidade=" + quantidade +
                ", valorTotal=" + valorTotal +
                '}';
    }
}
