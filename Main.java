
import java.nio.file.Path;
import java.util.List;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        try {
            Estoque estoque = new Estoque();

            // 1. Carregar produtos do CSV
            Path arquivoCsv = Path.of("produtos.csv");
            FormatoCsv formatoCsv = new FormatoCsv();

            List<Produto> produtos = estoque.carregar(arquivoCsv, formatoCsv);

            estoque.setProdutos(produtos);

            // 2. Listar produtos
            System.out.println("=== PRODUTOS DO ESTOQUE ===");

            for (Produto p : produtos) {
                System.out.println(p);
            }

            // 3. Calcular valor total
            System.out.printf("\nValor total em estoque: R$ %.2f%n",
                    estoque.valorTotal());

            // 4. Exportar para JSON
            Path arquivoJson = Path.of("produtos.json");
            FormatoJson formatoJson = new FormatoJson();

            estoque.salvar(produtos, arquivoJson, formatoJson);


            System.out.println("\nArquivo JSON gerado com sucesso!");

            // 5. Carregar JSON novamente
            List<Produto> produtosJson =
                    estoque.carregar(arquivoJson, formatoJson);

            estoque.setProdutos(produtosJson);
            System.out.println("\n=== PRODUTOS DO ESTOQUE (APÓS CARREGAR JSON) ===");

            for (Produto p : produtos) {
                System.out.println(p);
            }

            System.out.printf("Valor total após carregar JSON: R$ %.2f%n",
                    estoque.valorTotal());

        } catch (IOException | ArquivoInvalidoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
