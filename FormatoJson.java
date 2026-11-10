import java.util.List;
import java.util.ArrayList;

public class FormatoJson implements Formato {

    @Override
    public String serializar(List<Produto> produtos) {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");

        for (int i = 0; i < produtos.size(); i++) {
            Produto produto = produtos.get(i);
            sb.append("  {\n");
            sb.append("    \"codigo\": \"").append(produto.getCodigo()).append("\",\n");
            sb.append("    \"nome\": \"").append(produto.getNome()).append("\",\n");
            sb.append("    \"preco\": ").append(produto.getPreco()).append(",\n");
            sb.append("    \"quantidade\": ").append(produto.getQuantidade()).append("\n");
            sb.append("  }");

            if (i < produtos.size() - 1) {
                sb.append(",");
            }

            sb.append("\n");
        }
        sb.append("]\n");
        return sb.toString();
    }

    
    @Override
    public List<Produto> desserializar(String conteudo)
            throws ArquivoInvalidoException {

        List<Produto> produtos = new ArrayList<>();

        try {
            String json = conteudo.trim();

            if (!json.startsWith("[") || !json.endsWith("]")) {
                throw new Exception();
            }

            json = json.substring(1, json.length() - 1).trim();

            if (json.isEmpty()) {
                return produtos;
            }

            String[] objetos = json.split("\\},\\s*\\{");

            for (String objeto : objetos) {

                objeto = objeto.replace("{", "").replace("}", "").trim();

                String[] linhas = objeto.split("\\R");

                String codigo = linhas[0].split(":", 2)[1].trim()
                        .replace("\"", "").replace(",", "");

                String nome = linhas[1].split(":", 2)[1].trim()
                        .replace("\"", "").replace(",", "");

                double preco = Double.parseDouble(
                        linhas[2].split(":", 2)[1].trim().replace(",", ""));

                int quantidade = Integer.parseInt(
                        linhas[3].split(":", 2)[1].trim());

                produtos.add(new Produto(codigo, nome, preco, quantidade));
            }

        } catch (Exception e) {
            throw new ArquivoInvalidoException("Arquivo JSON inválido");
        }

        return produtos;
    }

}