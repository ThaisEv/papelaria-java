import java.io.IOException;
import java.nio.file.Files;
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
        String conteudo = formato.serializar(produtos);

        Files.writeString(arquivo, conteudo);
    }

    public List<Produto> carregar (Path arquivo, Formato formato) throws IOException, ArquivoInvalidoException {
        if (arquivo.toString().endsWith(".csv") || arquivo.toString().endsWith(".json")) {
            String conteudo = Files.readString(arquivo);

            return formato.desserializar(conteudo);
        }

        throw new IOException("Formato de arquivo não suportado");
    }

    public double valorTotal() {
        double total = 0;

        for (Produto p : produtos) {
            total += p.valorEmEstoque();
        }

        return total;
    }
}