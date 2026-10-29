import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class Estoque {

    private List<Produto> produtos;

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Estoque() {
        this.produtos = new ArrayList<>();
    }

    public void salvar (List<Produto> produtos, Path arquivo, Formato formato) throws IOException { 
        formato.serializar(this.produtos);
    }

    public List<Produto> carregar (Path arquivo, Formato formato) throws IOException {
        return null;
    }

    public double valorToral(List<Produto> produtos) {
        double total = 0;
        for (Produto p: this.produtos) {
            total += p.valorEmEstoque();
        }
        return total;
    }
}