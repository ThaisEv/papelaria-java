import java.util.List;


public interface Formato {
    
    public String serializar(List<Produto> produtos);

    public List<Produto> desserializar(String conteudo);
            //throws ArquivoInvalidoException;
}