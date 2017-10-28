
/**
 *
 * @author straby
 */
public class Aresta {
    
    private String nome;
    private Vertice origem;
    private Vertice destino;
    private boolean idaVolta;

    public Aresta(Vertice origem, Vertice destino) {
        
        this.origem = origem;
        this.destino = destino;
        this.nome = nome;
    }

    
    public Aresta(Vertice origem, Vertice destino, boolean idaVolta) {
        this.origem = origem;
        this.destino = destino;
        this.idaVolta = idaVolta;
        
    }
    
    public Vertice getOrigem() {
        return origem;
    }

    public void setOrigem(Vertice origem) {
        this.origem = origem;
    }

    public Vertice getDestino() {
        return destino;
    }

    public void setDestino(Vertice destino) {
        this.destino = destino;
    }

    public boolean isIdaVolta() {
        return idaVolta;
    }

    public void setIdaVolta(boolean idaVolta) {
        this.idaVolta = idaVolta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}
