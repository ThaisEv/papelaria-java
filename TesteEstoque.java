
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;

public class TesteEstoque {

    public static void main(String[] args) throws Exception {

        Estoque estoque = new Estoque();

        Formato csv = new FormatoCsv();
        Formato json = new FormatoJson();

        List<Produto> produtos = new ArrayList<>();

        produtos.add(new Produto("P001", "Caderno", 24.90, 40));
        produtos.add(new Produto("P002", "Caneta", 2.50, 300));

        Path arquivoCsv = Path.of("teste.csv");
        Path arquivoJson = Path.of("teste.json");

        // (a) Ida e volta em CSV
        estoque.salvar(produtos, arquivoCsv, csv);
        List<Produto> produtosCsv = estoque.carregar(arquivoCsv, csv);

        System.out.println("Quantidade: " + produtosCsv.size());
        System.out.println("Código 1: " + produtosCsv.get(0).getCodigo());
        System.out.println("Código 2: " + produtosCsv.get(1).getCodigo());
        System.out.println("Preço 1: " + produtosCsv.get(0).getPreco());

        assert produtosCsv.size() == produtos.size();
        assert produtosCsv.get(0).getCodigo().equals("P001");
        assert produtosCsv.get(1).getCodigo().equals("P002");
        assert Math.abs(produtosCsv.get(0).getPreco() - 24.90) < 0.001;

        // (a) Ida e volta em JSON
        estoque.salvar(produtos, arquivoJson, json);
        List<Produto> produtosJson = estoque.carregar(arquivoJson, json);

        assert produtosJson.size() == produtos.size();
        assert produtosJson.get(0).getCodigo().equals("P001");
        assert produtosJson.get(1).getCodigo().equals("P002");
        assert Math.abs(produtosJson.get(0).getPreco() - 24.90) < 0.001;

        // (b) Arquivo inexistente
        try {
            estoque.carregar(Path.of("inexistente.csv"), csv);
            assert false : "Deveria lançar NoSuchFileException";
        } catch (NoSuchFileException e) {
            // Teste passou
        }

        // (c) CSV com preço inválido
        Path csvInvalido = Path.of("teste_invalido.csv");

        Files.writeString(csvInvalido,
                "codigo,nome,preco,quantidade\n" +
                "P001,Caderno,abc,10");

        try {
            estoque.carregar(csvInvalido, csv);
            assert false : "Deveria lançar ArquivoInvalidoException";
        } catch (ArquivoInvalidoException e) {
            // Teste passou
        }

        // (d) JSON incompleto
        Path jsonInvalido = Path.of("teste_invalido.json");

        Files.writeString(jsonInvalido,
                "[\n" +
                "  {\n" +
                "    \"codigo\": \"P001\",\n" +
                "    \"nome\": \"Caderno\",\n" +
                "    \"preco\": 24.90\n" +
                "  }\n" +
                "]");

        try {
            estoque.carregar(jsonInvalido, json);
            assert false : "Deveria lançar ArquivoInvalidoException";
        } catch (ArquivoInvalidoException e) {
            // Teste passou
        }

        // Limpeza dos arquivos de teste
        Files.deleteIfExists(arquivoCsv);
        Files.deleteIfExists(arquivoJson);
        Files.deleteIfExists(csvInvalido);
        Files.deleteIfExists(jsonInvalido);

        System.out.println("Todos os testes passaram!");
    }
}
