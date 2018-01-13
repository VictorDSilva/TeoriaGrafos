package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Grafo {

    //Lista de Nodes e Edges
    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;

    private String id;
    private String edgedefault;

    public Grafo() {
        this.nodes = new ArrayList();
        this.edges = new ArrayList();

    }

    public Grafo(String id) {
        this.id = id;
        this.nodes = new ArrayList();
        this.edges = new ArrayList();
        this.edgedefault = "directed";

    }

    public Grafo(String id, String edgedefault) {
        this.id = id;
        this.nodes = new ArrayList();
        this.edges = new ArrayList();
        this.edgedefault = edgedefault;

    }

    public Grafo(String id, String edgedefault, ArrayList<Node> nodes, ArrayList<Edge> edges) {
        this.id = id;
        this.edgedefault = edgedefault;
        this.nodes = nodes;
        this.edges = edges;
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void setEdges(ArrayList<Edge> edges) {
        this.edges = edges;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEdgedefault() {
        return edgedefault;
    }

    public void setEdgedefault(String edgedefault) {
        this.edgedefault = edgedefault;
    }

    //ADICIONAR E REMOVER - VERTICES E ARESTAS
    public void addNodebyId(String id) {
        Node v = new Node(id);
        this.nodes.add(v);
    }

    public void addNode(Node no) {
        this.nodes.add(no);
    }

    public void removerNode(Node no) {
        int i;
        for (i = 0; i < this.edges.size(); i++) {
            if ((no.getId().equals(this.edges.get(i).getOrigem().getId())) || (no.getId().equals(this.edges.get(i).getDestino().getId()))) {
                this.removerEdge(edges.get(i));
                i--;
            }
        }
        this.nodes.remove(no);
    }

    public void addEdge(Edge edge) {
        if (this.getNodes().indexOf(edge.getOrigem()) != -1 && this.getNodes().indexOf(edge.getDestino()) != -1) {
            this.edges.add(edge);
        }
    }

    public void removerEdgebyId(String id) {
        Edge aresta = this.buscaEdge(id);
        this.edges.remove(aresta);
    }

    public void removerEdge(Edge aresta) {
        this.edges.remove(aresta);
    }

    //BUSCAR VERTICE e ARESTA POR ID
    public Node buscaNode(String id) {
        for (Node no : nodes) {
            if (no.getId().equals(id)) {
                return no;
            }
        }
        return null;
    }

    public int getNodeIndice(String id) {
        int i;
        for (i = 0; i < this.nodes.size(); i++) {
            if (id.equals(this.nodes.get(i).getId())) {
                return i;
            }
        }
        return -1;
    }

    public Edge buscaEdge(String id) {
        for (Edge a : edges) {
            if (a.getId().equals(id)) {
                return a;
            }
        }
        return null;
    }

    public String listarNodes() {
        String lista = "";
        for (Node no : this.getNodes()) {
            lista += "No: " + no.getId() + "\n";
        }
        return lista;
    }

    public String listarEdges() {
        String lista = "";
        for (Edge aresta : this.getEdges()) {
            lista += "Edge: " + aresta.getId() + "\n";
        }
        return lista;
    }

    //Caracteristicas do GRAFO
    public int getOrdem() {
        return this.getNodes().size();
        //System.out.println(" A ordem do grafo é: " + getNodes().size());
    }

    public void getIncidencia() {
        for (int i = 0; i <= this.getEdges().size() - 1; i++) {
            System.out.println("Os Nodes: " + this.getEdges().get(i).getOrigem().getId()
                    + " e " + this.getEdges().get(i).getDestino().getId() + " são incidentes a aresta: "
                    + this.getEdges().get(i).getId());
        }
    }

    public ArrayList<Node> getNodeAdjacentes(Node no1) {
        ArrayList<Node> adjacentes = new ArrayList<>();
        int i;
        for (i = 0; i < this.getNodes().size(); i++) {
            if (this.isAdjacente(no1, this.getNodes().get(i))) {
                adjacentes.add(this.getNodes().get(i));
            }
        }

        return adjacentes;
    }

    public int getAdjacentes(Node v1, Node v2) {
        int cont = 0;

        for (int i = 0; i < this.edges.size(); i++) {
            if (this.edges.get(i).getDestino().getId().equals(v1.getId())
                    && this.edges.get(i).getOrigem().getId().equals(v2.getId())
                    || this.edges.get(i).getOrigem().getId().equals(v1.getId())
                    && this.edges.get(i).getDestino().getId().equals(v2.getId())) {
                cont++;
            }
        }
        return cont;
    }

    public boolean isAdjacente(Node no1, Node no2) {
        int i;
        for (i = 0; i < this.edges.size(); i++) {
            if (this.edges.get(i).getDirected()) {
                if (this.edges.get(i).getOrigem().getId().equals(no1.getId()) && this.edges.get(i).getDestino().getId().equals(no2.getId())) {
                    return true;
                }
            } else {
                if (this.edges.get(i).getOrigem().getId().equals(no1.getId()) && this.edges.get(i).getDestino().getId().equals(no2.getId())
                        || this.edges.get(i).getOrigem().getId().equals(no2.getId()) && this.edges.get(i).getDestino().getId().equals(no1.getId())) {
                    return true;
                }
            }
        }
        return false;
    }

    public int isRegular() {
        int grau = -1;
        for (Node v : this.nodes) {
            if (grau == -1) {
                grau = grauNode(v);
            } else if (grau != grauNode(v)) {
                return 0;
            }
        }
        return grau;
    }

    public void imprimeRegular() {
        if (this.isRegular() == 0) {
            System.out.println("Nao é Regular");
        } else {
            System.out.println("É Regular");
        }
    }

    public int isCompleto() {
        if (isRegular() == this.nodes.size() - 1) {
            return this.nodes.size();
        }
        return 0;
    }

    public void imprimeCompleto() {
        if (this.isCompleto() == 0) {
            System.out.println("Nao é Completo");
        } else {
            System.out.println("É Completo");
        }
    }

    public boolean isMultigrafo() {
        for (Node v1 : this.nodes) {
            for (Node v2 : this.nodes) {
                if (getAdjacentes(v1, v2) > 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public void imprimeMultigrafo() {
        if (this.isMultigrafo() == true) {
            System.out.println("É um Multigrafo");
        } else {
            System.out.println("Nao é um Multigrafo");
        }
    }

    //Caracteristicas do Node
    //Emissão e Recepção de Cada Node
    public void grauNodeRecepcao() {
        int cont = 0;
        for (Node v : this.nodes) {
            cont = 0;
            for (int i = 0; i < this.edges.size(); i++) {
                if (this.edges.get(i).getDestino() == v) {
                    cont++;
                }
            }
            System.out.println(v.getId() + " tem grau de RECEPÇÃO: " + cont);
        }
    }

    public void grauNodeEmissao() {
        int cont;
        for (Node v : this.nodes) {
            cont = 0;
            for (int i = 0; i < this.edges.size(); i++) {
                if (this.edges.get(i).getOrigem() == v) {
                    cont++;
                }
            }
            System.out.println(v.getId() + " tem grau de EMISSÃO: " + cont);
        }
    }

    public int grauNode(Node node) {
        int cont = 0;
        for (int i = 0; i < this.edges.size(); i++) {
            if (this.edges.get(i).getOrigem() == node) {
                cont++;
            }
            if (this.edges.get(i).getDestino() == node) {
                cont++;
            }
        }
        return cont;
    }

    public boolean isFonte(String node) {
        if (this.edges.isEmpty()) {
            return false;
        }
        for (Edge aresta : this.edges) {
            if (aresta.getDestino() == this.buscaNode(node)) {
                return true;
            }
        }
        return false;
    }

    public boolean isSumidouro(String node) {
        if (this.edges.isEmpty()) {
            return false;
        }
        for (Edge aresta : this.edges) {
            if (aresta.getOrigem() == this.buscaNode(node)) {
                return true;
            }
        }
        return false;
    }

    //MATRIZ DE INCIDENCIA E ADJACENCIA
    public int[][] getMatrizIncidencia() {
        int linha = this.getNodes().size();
        int coluna = this.getEdges().size();
        int[][] matrizIncidencia = new int[linha][coluna];
        int i, j;
        for (i = 0; i < linha; i++) {
            for (j = 0; j < coluna; j++) {
                if (this.getEdges().get(j).getDirected()) {

                    if (this.getNodes().get(i).getId().equals(this.getEdges().get(j).getOrigem().getId()) && this.getNodes().get(i).getId().equals(this.getEdges().get(j).getDestino().getId())) {
                        matrizIncidencia[i][j] = 2;
                    } else {
                        if (this.getNodes().get(i).getId().equals(this.getEdges().get(j).getOrigem().getId())) {
                            matrizIncidencia[i][j] = 1;
                        }
                        if (this.getNodes().get(i).getId().equals(this.getEdges().get(j).getDestino().getId())) {
                            matrizIncidencia[i][j] = -1;
                        }
                    }
                } else {
                    if (this.getNodes().get(i).getId().equals(this.getEdges().get(j).getOrigem().getId()) && this.getNodes().get(i).getId().equals(this.getEdges().get(j).getDestino().getId())) {
                        matrizIncidencia[i][j] = 2;
                    } else if ((this.getNodes().get(i).getId().equals(this.getEdges().get(j).getOrigem().getId()))
                            || (this.getNodes().get(i).getId().equals(this.getEdges().get(j).getDestino().getId()))) {
                        matrizIncidencia[i][j] = 1;

                    }
                }
            }
        }

        return matrizIncidencia;
    }

    public int[][] getMatrizAdjacencia() {

        int n = this.getNodes().size();
        int m = this.getEdges().size();
        int[][] matrizAdjacencia = new int[n][n];
        int i, j, k;

        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                boolean adjacente = this.isAdjacente(this.getNodes().get(i), this.getNodes().get(j));
                if (adjacente) {
                    for (k = 0; k < m; k++) {
                        for (k = 0; k < m; k++) {
                            if (this.getEdges().get(k).getDirected()) {
                                if (this.edges.get(k).getOrigem().getId().equals(this.getNodes().get(i).getId())
                                        && this.edges.get(k).getDestino().getId().equals(this.getNodes().get(j).getId())) {
                                    matrizAdjacencia[i][j] = 1;

                                }
                            } else {
                                matrizAdjacencia[i][j] = 1;

                            }
                        }

                    }

                }
            }

        }
        return matrizAdjacencia;
    }

    public void listaAdjacencias() {
        HashMap<Node, ArrayList<Node>> nodes = new HashMap<>();

        for (Node n1 : this.nodes) {
            ArrayList<Node> verticesAdjacentes = new ArrayList<>();
            for (Node n2 : this.nodes) {
                if (NodeAdjacenteLista(n1.getId(), n2.getId())) {
                    verticesAdjacentes.add(n2);
                }
            }
            nodes.put(n1, verticesAdjacentes);
        }

        Set<Node> keys = nodes.keySet();

        for (Node key : keys) {
            System.out.print(key.getId() + " -> ");
            for (int j = 0; j < nodes.get(key).size(); j++) {

                System.out.print(nodes.get(key).get(j).getId() + " ");
            }
            System.out.println();
        }
    }

    public boolean NodeAdjacenteLista(String origem, String destino) {
        for (Edge a : this.edges) {
            if ((a.getOrigem() == buscaNode(origem) && a.getDestino() == buscaNode(destino))) {
                return true;
            }
        }
        return false;
    }

    public void imprimeMatrizIncidencia() {
        String resultado = "";
        int matriz[][] = this.getMatrizIncidencia();
        for (int i = 0; i < this.nodes.size(); i++) {
            for (int j = 0; j < this.edges.size(); j++) {
                resultado += matriz[i][j] + "\t";
            }
            resultado += "\n";
        }
        System.out.println(resultado);
    }

    public void imprimeMatrizAdjacencia() {
        String resultado = "";
        int matriz[][] = this.getMatrizAdjacencia();
        for (int i = 0; i < this.nodes.size(); i++) {
            for (int j = 0; j < this.nodes.size(); j++) {
                resultado += matriz[i][j] + "\t";
            }
            resultado += "\n";
        }
        System.out.println(resultado);
    }

}
