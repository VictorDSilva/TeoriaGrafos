
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author straby
 */
public class Grafo {

    private List<Vertice> vertices;
    private List<Aresta> arestas;
    private boolean orientado;
    private String id;

    public Grafo() {
        this.vertices = new ArrayList<Vertice>();
        this.arestas = new ArrayList<Aresta>();
        this.orientado = false;

    }

    public Grafo(boolean orientado) {
        this.vertices = new ArrayList<Vertice>();
        this.arestas = new ArrayList<Aresta>();
        this.orientado = orientado;
    }

    public boolean isOrientado() {
        return orientado;
    }

    public void setOrientado(boolean orientado) {
        this.orientado = orientado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Vertice> getVertices() {
        return vertices;
    }

    public Vertice buscaVertice(String id) {
        for (Vertice v : vertices) {
            if (v.getId().equals(id)) {
                return v;
            }
        }
        return null;
    }

    public void setVertices(List<Vertice> vertices) {
        this.vertices = vertices;
    }

    public List<Aresta> getArestas() {
        return arestas;
    }

    public void setArestas(List<Aresta> arestas) {
        this.arestas = arestas;
    }

    /* METODOS DE MANIPULACAO DO GRAFO */
    public Vertice addVertice(String id) {
        Vertice v = new Vertice(id);
        this.vertices.add(v);
        return v;
    }

    public void removerVertice(Vertice v) {
        for (int i = 0; i < this.getArestas().size(); i++) {

            if (v.getId().equals(this.getArestas().get(i).getDestino().getId())
                    || v.getId().equals(this.getArestas().get(i).getOrigem().getId())) {

                this.removerAresta(this.getArestas().get(i));
                i--;
            }
        }
        this.vertices.remove(v);
    }

    public Aresta addAresta(Vertice origem, Vertice destino) {
        if (this.isOrientado()) {
            Aresta e = new Aresta(origem, destino);
            origem.addAdj(e);
            destino.addAdj(e);
            this.arestas.add(e);
            return e;
        } else {
            Aresta e = new Aresta(origem, destino);
            origem.addAdj(e);
            this.arestas.add(e);
            return e;
        }
    }

    public void removerAresta(Aresta aresta) {
        this.arestas.remove(aresta);
    }

    @Override
    public String toString() {
        String r = "";

        for (Vertice u : this.vertices) {
            r += u.getId() + " -> ";

            for (Aresta e : u.getAdj()) {
                Vertice v = e.getDestino();
                r += v.getId() + ", ";
            }
            r += "\n";
        }
        return r;
    }

    public int[][] getMatrizIncidencia() {
        int linha = this.getVertices().size();
        int coluna = this.getArestas().size();
        int[][] matrizIncidencia = new int[linha][coluna];
        int i, j;
        for (i = 0; i < linha; i++) {
            for (j = 0; j < coluna; j++) {
                if (this.isOrientado()) {

                    if (this.getVertices().get(i).getId().equals(this.getArestas().get(j).getOrigem().getId()) && this.getVertices().get(i).getId().equals(this.getArestas().get(j).getDestino().getId())) {
                        matrizIncidencia[i][j] = 2;
                    } else {
                        if (this.getVertices().get(i).getId().equals(this.getArestas().get(j).getOrigem().getId())) {
                            matrizIncidencia[i][j] = 1;
                        }
                        if (this.getVertices().get(i).getId().equals(this.getArestas().get(j).getDestino().getId())) {
                            matrizIncidencia[i][j] = -1;
                        }
                    }
                } else {
                    if (this.getVertices().get(i).getId().equals(this.getArestas().get(j).getOrigem().getId()) && this.getVertices().get(i).getId().equals(this.getArestas().get(j).getDestino().getId())) {
                        matrizIncidencia[i][j] = 2;
                    } else if ((this.getVertices().get(i).getId().equals(this.getArestas().get(j).getOrigem().getId()))
                            || (this.getVertices().get(i).getId().equals(this.getArestas().get(j).getDestino().getId()))) {
                        matrizIncidencia[i][j] = 1;

                    }
                }
            }
        }

        return matrizIncidencia;
    }

    public int[][] getMatrizAdjacencia() {

        int n = this.getVertices().size();
        int m = this.getArestas().size();
        int[][] matrizAdjacencia = new int[n][n];
        int i, j, k;

        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                int teste = this.getAdjacentes(this.getVertices().get(i), this.getVertices().get(j));
                if (teste > 0) {
                    for (k = 0; k < m; k++) {
                        for (k = 0; k < m; k++) {
                            if (this.isOrientado()) {
                                if (this.arestas.get(k).getOrigem().getId().equals(this.getVertices().get(i).getId())
                                        && this.arestas.get(k).getDestino().getId().equals(this.getVertices().get(j).getId())) {
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

    public boolean caminho(String inicio, String destino) {
        //if (existeTarget(destino)) {
        ArrayList<Aresta> arestasSource = new ArrayList<>();

        for (Aresta aresta : arestas) {
            if (aresta.getDestino() == buscaVertice(inicio) && buscaVertice(inicio) != aresta.getDestino()) {
                arestasSource.add(aresta);
            }
        }

        while (arestasSource.size() >= 1) {
            ArrayList<Aresta> newArestasSource = new ArrayList<>();
            for (Aresta arestasSource1 : arestasSource) {
                for (Aresta aresta : arestas) {
                    if (arestasSource1.getDestino() == aresta.getOrigem() && arestasSource1.getDestino() != buscaVertice(inicio)) {
                        newArestasSource.add(aresta);
                    } else if (arestasSource1.getDestino() == buscaVertice(destino)) {
                        return true;
                    }
                }
            }
            arestasSource = newArestasSource;
        }
        //}
        return false;
    }

    public boolean cadeia(String inicio, String destino) {
        ArrayList<Aresta> arestasFound = new ArrayList<>();
        ArrayList<Vertice> nodesFound = new ArrayList<>();
        ArrayList<Vertice> newNodesFound = new ArrayList<>();

        for (Aresta aresta : arestas) {
            if (!nodesFound.contains(aresta.getOrigem()) && !nodesFound.contains(aresta.getDestino())) {
                if (aresta.getOrigem() == buscaVertice(inicio)) {
                    arestasFound.add(aresta);
                    newNodesFound.add(aresta.getDestino());
                }
                if (aresta.getDestino() == buscaVertice(inicio)) {
                    arestasFound.add(aresta);
                    newNodesFound.add(aresta.getOrigem());
                }
            }
        }

        nodesFound.add(buscaVertice(inicio));
        for (Vertice n : newNodesFound) {
            if (!nodesFound.contains(n)) {
                nodesFound.add(n);
            }
        }

        while (arestasFound.size() >= 1) {
            ArrayList<Aresta> newArestasFound = new ArrayList<>();
            newNodesFound = new ArrayList<>();
            int nodesFoundSize = nodesFound.size();
            for (Aresta arestaFound : arestasFound) {
                for (Aresta aresta : arestas) {
                    if (arestaFound.getDestino() == buscaVertice(destino) || arestaFound.getOrigem() == buscaVertice(destino)) {
                        return true;
                    }
                    if (arestaFound.getDestino() == aresta.getOrigem()) {
                        newArestasFound.add(aresta);
                        newNodesFound.add(aresta.getDestino());
                    }
                    if (arestaFound.getDestino() == aresta.getDestino()) {
                        newArestasFound.add(aresta);
                        newNodesFound.add(aresta.getOrigem());
                    }
                    if (arestaFound.getOrigem() == aresta.getOrigem()) {
                        newArestasFound.add(aresta);
                        newNodesFound.add(aresta.getDestino());
                    }
                    if (arestaFound.getOrigem() == aresta.getDestino()) {
                        newArestasFound.add(aresta);
                        newNodesFound.add(aresta.getOrigem());
                    }

                }
            }
            for (Vertice n : newNodesFound) {
                if (!nodesFound.contains(n)) {
                    nodesFound.add(n);
                }
            }
            arestasFound = newArestasFound;
            if (nodesFoundSize == nodesFound.size()) {
                return false;
            }
        }
        return false;
    }


    /*   V[G] é o conjunto de vértices(v) que formam o Grafo G. d[v] é o vetor de distâncias de s até cada v. 
    Admitindo-se a pior estimativa possível, o caminho infinito. π[v] identifica o vértice de onde se origina 
    uma conexão até v de maneira a formar um caminho mínimo.
        
    2º passo: temos que usar o conjunto Q, cujos vértices ainda não contém o custo do menor caminho d[v] determinado.
        Q ← V[G]
        
    3º passo: realizamos uma série de relaxamentos das arestas, de acordo com o código:
        enquanto Q ≠ ø
         u ← extrair-mín(Q)                     //Q ← Q - {u}
         para cada v adjacente a u
              se d[v] > d[u] + w(u, v)          //relaxe (u, v)
                 então d[v] ← d[u] + w(u, v)
                       π[v] ← u
    
     */
 /* METODOS DE MANIPULACAO DE VERTICE */
    public void criarVertice(String id) {
        Vertice v = this.addVertice(id);

    }

    public String listarVertice() {
        String r = "";
        for (Vertice u : this.getVertices()) {
            r += "V: " + u.getId();
            r += "\n";
        }
        return r;
    }

    public void getOrdem() {
        System.out.println(" A ordem do grafo é: " + getVertices().size());
    }

    public void getIncidencia() {
        for (int i = 0; i <= getArestas().size() - 1; i++) {
            System.out.println("Os vertices: " + getArestas().get(i).getOrigem().getId()
                    + " e " + getArestas().get(i).getDestino().getId() + " são incidentes a aresta: "
                    + getArestas().get(i).getNome());
        }
    }

    public void grauVerticeRecepcao() {
        int cont = 0;
        for (Vertice v : this.vertices) {
            cont = 0;
            for (int i = 0; i < arestas.size(); i++) {
                if (arestas.get(i).getDestino() == v) {
                    cont++;
                }
            }
            System.out.println(v.getId() + " tem grau: " + cont);            
        }
    }

    public void grauVerticeEmissao() {
        int cont = 0;
        for (Vertice v : this.vertices) {           
            for (int i = 0; i < arestas.size(); i++) {
                if (arestas.get(i).getOrigem() == v) {
                    cont++;
                }
            }
            System.out.println(v.getId() + " tem grau: " + cont);
        }        
    }

    public int grauVertice(Vertice node) {
        int cont = 0;
        for (int i = 0; i < arestas.size(); i++) {
            if (arestas.get(i).getOrigem() == node) {
                cont++;
            }
            if (arestas.get(i).getDestino() == node) {
                cont++;
            }
        }
        return cont;
    }
//arrumar o buscavertive string

    public boolean isFonte(String node) {
        for (Aresta aresta : arestas) {
            if (aresta.getDestino() == this.buscaVertice(node)) {
                return false;
            }
        }
        return true;
    }

    public boolean isSumidouro(String node) {
        for (Aresta aresta : arestas) {
            if (aresta.getOrigem() == this.buscaVertice(node)) {
                return false;
            }
        }
        return true;
    }

    /* METODOS DE MANIPULAÇÃO DE ARESTA */
    public void criarAresta(String idOrigem, String idDestino) {
        if (this.getVertices().isEmpty()) {
            System.out.println("Nao existem vertices");
        } else {
            Vertice v1 = this.buscaVertice(idOrigem);
            Vertice v2 = this.buscaVertice(idDestino);
            Aresta aresta = this.addAresta(v1, v2);
        }
    }

    public int getAdjacentes(Vertice v1, Vertice v2) {
        int cont = 0;

        for (int i = 0; i < this.arestas.size(); i++) {
            if (this.arestas.get(i).getDestino().getId().equals(v1.getId())
                    && this.arestas.get(i).getOrigem().getId().equals(v2.getId())
                    || this.arestas.get(i).getOrigem().getId().equals(v1.getId())
                    && this.arestas.get(i).getDestino().getId().equals(v2.getId())) {
                cont++;

            }
        }
        return cont;
    }

    /* MANIPULACAO DO GRAFO */
    public int isRegular() {
        int grau = -1;
        for (Vertice v : this.vertices) {
            if (grau == -1) {
                grau = grauVertice(v);
            } else if (grau != grauVertice(v)) {
                return 0;
            }
        }
        return grau;
    }

    public int isCompleto() {
        if (isRegular() == vertices.size() - 1) {
            return vertices.size();
        }
        return 0;
    }

    public boolean isMultigrafo() {
        for (Vertice v1 : vertices) {
            for (Vertice v2 : vertices) {
                if (getAdjacentes(v1, v2) > 1) {
                    return true;
                }
            }
        }
        return false;
    }


    /* METODOS DE XML */
    public void gravarXML() {
        try {
            FileWriter arquivo = new FileWriter("grafo.xml");
            PrintWriter gravarArquivo = new PrintWriter(arquivo);

            gravarArquivo.printf("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            gravarArquivo.printf("<graphml xmlns=\"http://graphml.graphdrawing.org/xmlns\"  \n"
                    + "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
                    + "    xsi:schemaLocation=\"http://graphml.graphdrawing.org/xmlns\n"
                    + "     http://graphml.graphdrawing.org/xmlns/1.0/graphml.xsd\">\n");

            gravarArquivo.printf("  <graph id='1' arestadefault='direcao'>\n");

            for (Vertice v : this.getVertices()) {
                gravarArquivo.printf("      <node id='" + v.getId() + "'/>\n");
            }

            for (Aresta aresta : this.getArestas()) {
                gravarArquivo.printf("      <aresta source='" + aresta.getOrigem().getId() + "' target='" + aresta.getDestino().getId() + "'/>\n");
            }

            gravarArquivo.printf("  </graph>\n");
            gravarArquivo.printf("</graphml>");

            arquivo.close();
        } catch (IOException ex) {
            System.out.println("Erro ao gerar XML!");
        }
    }

    public void lerXML(String caminhoArquivo) {
        try {
            BufferedReader arquivo = new BufferedReader(new FileReader(caminhoArquivo));

            while (arquivo.ready()) {
                System.out.println(arquivo.readLine());
            }
        } catch (IOException ex) {
            System.out.println("Erro ao ler XML!");
        }
    }
}
