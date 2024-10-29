package model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Carrinho implements Serializable {

    private List<CarrinhoItem> itens;

    public Carrinho() {
        this.itens = new ArrayList<>();
    }

    public List<CarrinhoItem> getItens() {
        return itens;
    }

    public void adicionarItem(CarrinhoItem item) {
        this.itens.add(item);
    }

    public void removerItem(Integer id) {
        itens.removeIf(i -> i.getProduto().getId().equals(id));
    }

    public void atualizarItem(CarrinhoItem item) {
        for (CarrinhoItem i : itens) {
            if (i.getProduto().getId().equals(item.getProduto().getId())) {
                i.setQuantidade(item.getQuantidade());
                return;
            }
        }
    }

    public double calcularTotal() {
        double total = 0;
        for (CarrinhoItem i : itens) {
            total += i.getProduto().getValor() * i.getQuantidade();
        }
        return total;
    }
}
