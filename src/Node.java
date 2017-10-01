/**
 *
 * @author straby
 */
public class Node {
    private String nome;
    private Aresta aresta;
    
    public Node(){
        
    }

    public Node(String nome) {
        this.nome = nome;
    }
    
    
    public Node(String nome, Aresta aresta) {
        this.nome = nome;
        this.aresta = aresta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Aresta getAresta() {
        return aresta;
    }

    public void setAresta(Aresta aresta) {
        this.aresta = aresta;
    }

    
   
}