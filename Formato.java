import java.util.List;

/**
 * Um formato de arquivo sabe converter uma lista de produtos
 * em texto (serializar) e o texto de volta em produtos (desserializar).
 */
public interface Formato {

    String serializar(List<Produto> produtos);

    List<Produto> desserializar(String conteudo) throws ArquivoInvalidoException;
}
