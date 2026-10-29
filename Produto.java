public class Produto {
    private String codigo;
    private String nome;
    private double preco;
    private int quantidade;

    public Produto(String codigo, String nome, double preco, int quantidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public String getCodigo() { return codigo; }
    public String getNome() { return nome; }
    public double getPreco() { return preco; }
    public int getQuantidade() { return quantidade; }

    public double valorEmEstoque() {
        return preco * quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto)) return false;
        Produto outro = (Produto) o;
        return codigo.equals(outro.codigo) && nome.equals(outro.nome)
            && Double.compare(preco, outro.preco) == 0 && quantidade == outro.quantidade;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(codigo, nome, preco, quantidade);
    }

    @Override
    public String toString() {
        return codigo + " - " + nome + " (" + quantidade + " un. a R$ " + preco + ")";
    }
}
