
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author straby
 */
public class Grafo {

    private ArrayList<Vertice> listaVertices;
    private ArrayList<Aresta> arestas;
    private boolean orientado;
    private String id;
    private String arestaPadrao;

    public Grafo() {
        this.listaVertices = new ArrayList<Vertice>();
        this.arestas = new ArrayList<Aresta>();
        this.orientado = false;
    }

    public Grafo(boolean orientado) {
        this.listaVertices = new ArrayList<Vertice>();
        this.arestas = new ArrayList<Aresta>();
        this.orientado = orientado;
    }

    public Grafo(String id, String arestaPadrao, ArrayList<Vertice> listaVertices, ArrayList<Aresta> arestas) {
        this.id = id;
        this.arestaPadrao = arestaPadrao;
        this.listaVertices = listaVertices;
        this.arestas = arestas;
    }

    public String getArestaPadrao() {
        return arestaPadrao;
    }

    public void setArestaPadrao(String arestaPadrao) {
        this.arestaPadrao = arestaPadrao;
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

    public ArrayList<Vertice> getVertices() {
        return listaVertices;
    }

    public Vertice buscaVertice(String id) {
        for (Vertice v : listaVertices) {
            if (v.getId().equals(id)) {
                return v;
            }
        }
        return null;
    }

    public Aresta buscaAresta(String id) {
        for (Aresta a : arestas) {
            if (a.getNome().equals(id)) {
                return a;
            }
        }
        return null;
    }

    public void setVertices(ArrayList<Vertice> listaVertices) {
        this.listaVertices = listaVertices;
    }

    public ArrayList<Aresta> getArestas() {
        return arestas;
    }

    public void setArestas(ArrayList<Aresta> arestas) {
        this.arestas = arestas;
    }

    /* METODOS DE MANIPULACAO DO GRAFO */
    public Vertice addVertice(String id) {
        Vertice v = new Vertice(id);
        this.listaVertices.add(v);
        return v;
    }

    public void removerVertice(String nome) {
        Vertice v = this.buscaVertice(nome);
        for (int i = 0; i < this.getArestas().size(); i++) {
            if (v.getId().equals(this.getArestas().get(i).getDestino().getId()) || v.getId().equals(this.getArestas().get(i).getOrigem().getId())) {
                this.removerAresta(this.getArestas().get(i).getNome());
                i--;
            }
        }
        this.listaVertices.remove(v);
    }

    public Aresta addAresta(Vertice origem, Vertice destino, int peso) {
        if (this.isOrientado()) {
            Aresta e = new Aresta(origem, destino, peso);
            origem.addAdj(e);
            destino.addAdj(e);
            this.arestas.add(e);
            return e;
        } else {
            Aresta e = new Aresta(origem, destino, peso);
            origem.addAdj(e);
            this.arestas.add(e);
            return e;
        }
    }

    public void removerAresta(String nome) {
        Aresta aresta = this.buscaAresta(nome);
        this.arestas.remove(aresta);
    }

    @Override
    public String toString() {
        String r = "";

        for (Vertice u : this.listaVertices) {
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
        int m = this.getVertices().size();
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
        ArrayList<Vertice> verticesFound = new ArrayList<>();
        ArrayList<Vertice> newVerticesFound = new ArrayList<>();

        for (Aresta aresta : arestas) {
            if (!verticesFound.contains(aresta.getOrigem()) && !verticesFound.contains(aresta.getDestino())) {
                if (aresta.getOrigem() == buscaVertice(inicio)) {
                    arestasFound.add(aresta);
                    newVerticesFound.add(aresta.getDestino());
                }
                if (aresta.getDestino() == buscaVertice(inicio)) {
                    arestasFound.add(aresta);
                    newVerticesFound.add(aresta.getOrigem());
                }
            }
        }

        verticesFound.add(buscaVertice(inicio));
        for (Vertice n : newVerticesFound) {
            if (!verticesFound.contains(n)) {
                verticesFound.add(n);
            }
        }

        while (arestasFound.size() >= 1) {
            ArrayList<Aresta> newArestasFound = new ArrayList<>();
            newVerticesFound = new ArrayList<>();
            int verticesFoundSize = verticesFound.size();
            for (Aresta arestaFound : arestasFound) {
                for (Aresta aresta : arestas) {
                    if (arestaFound.getDestino() == buscaVertice(destino) || arestaFound.getOrigem() == buscaVertice(destino)) {
                        return true;
                    }
                    if (arestaFound.getDestino() == aresta.getOrigem()) {
                        newArestasFound.add(aresta);
                        newVerticesFound.add(aresta.getDestino());
                    }
                    if (arestaFound.getDestino() == aresta.getDestino()) {
                        newArestasFound.add(aresta);
                        newVerticesFound.add(aresta.getOrigem());
                    }
                    if (arestaFound.getOrigem() == aresta.getOrigem()) {
                        newArestasFound.add(aresta);
                        newVerticesFound.add(aresta.getDestino());
                    }
                    if (arestaFound.getOrigem() == aresta.getDestino()) {
                        newArestasFound.add(aresta);
                        newVerticesFound.add(aresta.getOrigem());
                    }

                }
            }
            for (Vertice n : newVerticesFound) {
                if (!verticesFound.contains(n)) {
                    verticesFound.add(n);
                }
            }
            arestasFound = newArestasFound;
            if (verticesFoundSize == verticesFound.size()) {
                return false;
            }
        }
        return false;
    }


    /* METODOS DE MANIPULACAO DE VERTICE */
    public void criarVertice(String id) {
        this.addVertice(id);
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
            System.out.println("Os listaVertices: " + getArestas().get(i).getOrigem().getId()
                    + " e " + getArestas().get(i).getDestino().getId() + " são incidentes a aresta: "
                    + getArestas().get(i).getNome());
        }
    }

    public ArrayList<Vertice> getListaAdjacencia(Vertice no) {
        //Nao tenho certeza se é a implementacao mais ideal 
        //para pegar a lista de apenas um vertice
        ArrayList<Vertice> verticesAdjacentes = new ArrayList<>();

        for (Vertice n2 : this.listaVertices) {
            if (verticeAdjacenteLista(no.getId(), n2.getId())) {
                verticesAdjacentes.add(n2);
            }
        }
        return verticesAdjacentes;
    }

    public void listaAdjacencias() {
        HashMap<Vertice, ArrayList<Vertice>> listaVertices = new HashMap<>();

        for (Vertice n1 : this.listaVertices) {
            ArrayList<Vertice> verticesAdjacentes = new ArrayList<>();
            for (Vertice n2 : this.listaVertices) {
                if (verticeAdjacenteLista(n1.getId(), n2.getId())) {
                    verticesAdjacentes.add(n2);
                }
            }
            listaVertices.put(n1, verticesAdjacentes);
        }

        Set<Vertice> keys = listaVertices.keySet();

        for (Vertice key : keys) {
            System.out.print(key.getId() + " -> ");
            for (int j = 0; j < listaVertices.get(key).size(); j++) {

                System.out.print(listaVertices.get(key).get(j).getId() + " ");
            }
            System.out.println();
        }
    }

    public boolean verticeAdjacenteLista(String source, String target) {
        for (Aresta a : arestas) {
            if ((a.getOrigem() == buscaVertice(source) && a.getDestino() == buscaVertice(target))) {
                return true;
            }
        }
        return false;
    }

    public void imprimeMatrizIncidencia() {
        String resultado = "";
        int matriz[][] = this.getMatrizIncidencia();
        for (int i = 0; i < listaVertices.size(); i++) {
            for (int j = 0; j < arestas.size(); j++) {
                resultado += matriz[i][j] + "\t";
            }
            resultado += "\n";
        }
        System.out.println(resultado);
    }

    public void imprimeMatrizAdjacencia() {
        String resultado = "";
        int matriz[][] = this.getMatrizAdjacencia();
        for (int i = 0; i < listaVertices.size(); i++) {
            for (int j = 0; j < listaVertices.size(); j++) {
                resultado += matriz[i][j] + "\t";
            }
            resultado += "\n";
        }
        System.out.println(resultado);
    }

    public void grauVerticeRecepcao() {
        int cont = 0;
        for (Vertice v : this.listaVertices) {
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
        int cont;
        for (Vertice v : this.listaVertices) {
            cont = 0;
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

    public boolean isFonte(String node) {
        if (arestas.isEmpty()) {
            return false;
        }
        for (Aresta aresta : arestas) {
            if (aresta.getDestino() == this.buscaVertice(node)) {
                return true;
            }
        }
        return false;
    }

    public boolean isSumidouro(String node) {
        if (arestas.isEmpty()) {
            return false;
        }
        for (Aresta aresta : arestas) {
            if (aresta.getOrigem() == this.buscaVertice(node)) {
                return true;
            }
        }
        return false;
    }

    /* METODOS DE MANIPULAÇÃO DE ARESTA */
    public void criarAresta(String idOrigem, String idDestino, int peso) {
        if (this.getVertices().isEmpty()) {
            System.out.println("Nao existem listaVertices");
        } else {
            Vertice v1 = this.buscaVertice(idOrigem);
            Vertice v2 = this.buscaVertice(idDestino);
            Aresta aresta = this.addAresta(v1, v2, peso);
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

    public String listarAresta() {
        String r = "";
        for (Aresta u : this.getArestas()) {
            r += "Aresta: " + u.getNome();
            r += "\n";
        }
        return r;
    }

    /* MANIPULACAO DO GRAFO */
    public int isRegular() {
        int grau = -1;
        for (Vertice v : this.listaVertices) {
            if (grau == -1) {
                grau = grauVertice(v);
            } else if (grau != grauVertice(v)) {
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
        if (isRegular() == listaVertices.size() - 1) {
            return listaVertices.size();
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
        for (Vertice v1 : listaVertices) {
            for (Vertice v2 : listaVertices) {
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

    /* METODOS DE XML */
    public void gravarXML(String nome) {
        try {
            FileWriter arquivo = new FileWriter(nome + ".xml");
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
                gravarArquivo.printf("      <edge source='" + aresta.getOrigem().getId() + "' target='" + aresta.getDestino().getId() + "'/>\n");
            }

            gravarArquivo.printf("  </graph>\n");
            gravarArquivo.printf("</graphml>");

            arquivo.close();

            System.out.println("XML salvo.\n");
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
