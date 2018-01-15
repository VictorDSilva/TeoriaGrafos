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
            lista += "Edge: " + aresta.getId() + " peso: " + aresta.getPeso() + "\n";
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

    public boolean isAdjacente(Grafo grafo, Node no1, Node no2) {
        int i;
        for (i = 0; i < grafo.edges.size(); i++) {
            if (grafo.getEdgedefault().equals("directed")) {
                if (grafo.edges.get(i).getOrigem().getId().equals(no1.getId()) && grafo.edges.get(i).getDestino().getId().equals(no2.getId())) {
                    return true;
                }
            } else {
                if (grafo.edges.get(i).getOrigem().getId().equals(no1.getId()) && grafo.edges.get(i).getDestino().getId().equals(no2.getId())
                        || grafo.edges.get(i).getOrigem().getId().equals(no2.getId()) && grafo.edges.get(i).getDestino().getId().equals(no1.getId())) {
                    return true;
                }
            }
        }
        return false;
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
    public int[][] getMatrizIncidencia(Grafo grafo) {
        int linha = grafo.getNodes().size();
        int coluna = grafo.getEdges().size();
        int[][] matrizIncidencia = new int[linha][coluna];
        int i, j;
        for (i = 0; i < linha; i++) {
            for (j = 0; j < coluna; j++) {
                if (grafo.getEdgedefault().equals("directed")) {

                    if (grafo.getNodes().get(i).getId().equals(grafo.getEdges().get(j).getOrigem().getId()) && grafo.getNodes().get(i).getId().equals(grafo.getEdges().get(j).getDestino().getId())) {
                        matrizIncidencia[i][j] = 2;
                    } else {
                        if (grafo.getNodes().get(i).getId().equals(grafo.getEdges().get(j).getOrigem().getId())) {
                            matrizIncidencia[i][j] = 1;
                        }
                        if (grafo.getNodes().get(i).getId().equals(grafo.getEdges().get(j).getDestino().getId())) {
                            matrizIncidencia[i][j] = -1;
                        }
                    }
                } else {
                    if (grafo.getNodes().get(i).getId().equals(grafo.getEdges().get(j).getOrigem().getId()) && grafo.getNodes().get(i).getId().equals(grafo.getEdges().get(j).getDestino().getId())) {
                        matrizIncidencia[i][j] = 2;
                    } else if ((grafo.getNodes().get(i).getId().equals(grafo.getEdges().get(j).getOrigem().getId()))
                            || (grafo.getNodes().get(i).getId().equals(this.getEdges().get(j).getDestino().getId()))) {
                        matrizIncidencia[i][j] = 1;

                    }
                }
            }
        }

        return matrizIncidencia;
    }

    public int[][] getMatrizAdjacencia(Grafo grafo) {

        int listaNos = grafo.getNodes().size();
        int listaArestas = grafo.getEdges().size();
        int[][] matrizAdjacencia = new int[listaNos][listaNos];
        int i, j, k;

        for (i = 0; i < listaNos; i++) {
            for (j = 0; j < listaNos; j++) {
                boolean adjacente = this.isAdjacente(grafo, grafo.getNodes().get(i), grafo.getNodes().get(j));
                if (adjacente) {
                    for (k = 0; k < listaArestas; k++) {
                        for (k = 0; k < listaArestas; k++) {
                            if (grafo.getEdgedefault().equals("directed")) {
                                if (grafo.edges.get(k).getOrigem().getId().equals(grafo.getNodes().get(i).getId())
                                        && grafo.edges.get(k).getDestino().getId().equals(grafo.getNodes().get(j).getId())) {
                                    matrizAdjacencia[i][j] = grafo.edges.get(k).getPeso();

                                }
                            } else {
                                matrizAdjacencia[i][j] = grafo.edges.get(j).getPeso();

                            }
                        }

                    }

                }
            }

        }
        return matrizAdjacencia;
    }

    public void listaAdjacencias(Grafo grafo) {
        HashMap<Node, ArrayList<Node>> nodes = new HashMap<>();

        for (Node n1 : (grafo.getNodes())) {
            ArrayList<Node> nodesAdjacentes = new ArrayList<>();
            for (Node n2 : grafo.getNodes()) {
                if (NodeAdjacenteLista(n1.getId(), n2.getId())) {
                    nodesAdjacentes.add(n2);
                }
            }
            nodes.put(n1, nodesAdjacentes);
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

    public void imprimeMatrizIncidencia(Grafo grafo) {
        String resultado = "";
        int matriz[][] = this.getMatrizIncidencia(grafo);
        for (int i = 0; i < this.nodes.size(); i++) {
            for (int j = 0; j < this.edges.size(); j++) {
                resultado += matriz[i][j] + "\t";
            }
            resultado += "\n";
        }
        System.out.println(resultado);
    }

    public void imprimeMatrizAdjacencia(Grafo grafo) {
        String resultado = "";
        int matriz[][] = this.getMatrizAdjacencia(grafo);
        for (int i = 0; i < this.nodes.size(); i++) {
            for (int j = 0; j < this.nodes.size(); j++) {
                resultado += matriz[i][j] + "\t";
            }
            resultado += "\n";
        }
        System.out.println(resultado);
    }

    public boolean caminho(String inicio, String destino) {
        ArrayList<Edge> edgesSource = new ArrayList<>();

        for (Edge aresta : this.edges) {
            if (aresta.getDestino() == buscaNode(inicio) && buscaNode(inicio) != aresta.getDestino()) {
                edgesSource.add(aresta);
            }
        }

        while (edgesSource.size() >= 1) {
            ArrayList<Edge> newEdgesSource = new ArrayList<>();
            for (Edge edgesSource1 : edgesSource) {
                for (Edge aresta : this.edges) {
                    if (edgesSource1.getDestino() == aresta.getOrigem() && edgesSource1.getDestino() != buscaNode(inicio)) {
                        newEdgesSource.add(aresta);
                    } else if (edgesSource1.getDestino() == buscaNode(destino)) {
                        return true;
                    }
                }
            }
            edgesSource = newEdgesSource;
        }
        return false;
    }

    public boolean cadeia(String inicio, String destino) {
        ArrayList<Edge> edgesFound = new ArrayList<>();
        ArrayList<Node> nodesFound = new ArrayList<>();
        ArrayList<Node> newNodesFound = new ArrayList<>();

        for (Edge aresta : edges) {
            if (!nodesFound.contains(aresta.getOrigem()) && !nodesFound.contains(aresta.getDestino())) {
                if (aresta.getOrigem() == buscaNode(inicio)) {
                    edgesFound.add(aresta);
                    newNodesFound.add(aresta.getDestino());
                }
                if (aresta.getDestino() == buscaNode(inicio)) {
                    edgesFound.add(aresta);
                    newNodesFound.add(aresta.getOrigem());
                }
            }
        }

        nodesFound.add(buscaNode(inicio));
        for (Node n : newNodesFound) {
            if (!nodesFound.contains(n)) {
                nodesFound.add(n);
            }
        }

        while (edgesFound.size() >= 1) {
            ArrayList<Edge> newEdgesFound = new ArrayList<>();
            newNodesFound = new ArrayList<>();
            int nodesFoundSize = nodesFound.size();
            for (Edge arestaFound : edgesFound) {
                for (Edge aresta : edges) {
                    if (arestaFound.getDestino() == buscaNode(destino) || arestaFound.getOrigem() == buscaNode(destino)) {
                        return true;
                    }
                    if (arestaFound.getDestino() == aresta.getOrigem()) {
                        newEdgesFound.add(aresta);
                        newNodesFound.add(aresta.getDestino());
                    }
                    if (arestaFound.getDestino() == aresta.getDestino()) {
                        newEdgesFound.add(aresta);
                        newNodesFound.add(aresta.getOrigem());
                    }
                    if (arestaFound.getOrigem() == aresta.getOrigem()) {
                        newEdgesFound.add(aresta);
                        newNodesFound.add(aresta.getDestino());
                    }
                    if (arestaFound.getOrigem() == aresta.getDestino()) {
                        newEdgesFound.add(aresta);
                        newNodesFound.add(aresta.getOrigem());
                    }

                }
            }
            for (Node n : newNodesFound) {
                if (!nodesFound.contains(n)) {
                    nodesFound.add(n);
                }
            }
            edgesFound = newEdgesFound;
            if (nodesFoundSize == nodesFound.size()) {
                return false;
            }
        }
        return false;
    }

}
