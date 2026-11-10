import java.util.ArrayList;
import java.util.List;

public class FormatoCsv implements Formato {

    @Override
    public String serializar(List<Produto> produtos) {
        StringBuilder sb = new StringBuilder();

        sb.append("codigo,nome,preco,quantidade\n");

        for (Produto p : produtos) {
            sb.append(p.getCodigo()).append(",");
            sb.append(p.getNome()).append(",");
            sb.append(p.getPreco()).append(",");
            sb.append(p.getQuantidade()).append("\n");
        }

        return sb.toString();
    }

    @Override
    public List<Produto> desserializar(String conteudo) throws ArquivoInvalidoException {
        List<Produto> produtos = new ArrayList<>();

    try {
        String[] linhas = conteudo.split("\n");

        for (int i = 1; i < linhas.length; i++) {
            String linha = linhas[i];
            if (linha.isBlank()) continue;

            String[] dados = linha.split(",");

            String codigo = dados[0];
            String nome = dados[1];
            double preco = Double.parseDouble(dados[2]);
            int quantidade = Integer.parseInt(dados[3]);

            produtos.add(new Produto(codigo, nome, preco, quantidade));
        }

    } catch (Exception e) {
        throw new ArquivoInvalidoException("Arquivo CSV inválido");
    }

    return produtos;
    }
}