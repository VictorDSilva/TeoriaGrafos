package modelo;

public class Edge {

    private String id;
    private Node origem;
    private Node destino;
    private int peso;
    private Boolean directed;

    public Edge(String id) {
        this.id = id;
        this.directed = false;
    }

    public Edge(String id, Node origem, Node destino) {
        this.id = id;
        this.origem = origem;
        this.destino = destino;
        this.directed = false;
    }

    public Edge(String id, Node origem, Node destino, String peso, Boolean directed) {
        this.id = id;
        this.origem = origem;
        this.destino = destino;
        this.peso = Integer.parseInt(peso);
        this.directed = directed;
    }

    public Edge(String id, Node origem, Node destino, String peso) {
        this.id = id;
        this.origem = origem;
        this.destino = destino;
        this.peso = Integer.parseInt(peso);
    }

    public Edge(Node origem, Node destino, String peso) {
        this.id = origem.getId() + destino.getId();
        this.origem = origem;
        this.destino = destino;
        this.peso = Integer.parseInt(peso);
        this.directed = false;
    }

    public Edge(String id, Node origem, Node destino, Boolean directed) {
        this.id = id;
        this.origem = origem;
        this.destino = destino;
        this.directed = directed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getDirected() {
        return directed;
    }

    public void setDirected(Boolean directed) {
        this.directed = directed;
    }

    public Node getOrigem() {
        return origem;
    }

    public void setOrigem(Node origem) {
        this.origem = origem;
    }

    public Node getDestino() {
        return destino;
    }

    public void setDestino(Node destino) {
        this.destino = destino;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

}
