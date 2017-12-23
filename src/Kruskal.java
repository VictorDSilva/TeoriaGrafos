
import java.util.ArrayList;
import java.util.Collections;

public class Kruskal {

    private ArrayList<ArrayList<String>> listaPais;
    private ArrayList<Aresta> arestas;
    private ArrayList<Vertice> vertices;
    private ArrayList<String> verticesId;
    private Grafo grafo;

    public ArrayList<Aresta> getArestas() {
        return arestas;
    }

    public void setArestas(ArrayList<Aresta> arestas) {
        this.arestas = arestas;
    }

    public ArrayList<Vertice> getVertices() {
        return vertices;
    }

    public void setVertices(ArrayList<Vertice> vertices) {
        this.vertices = vertices;
    }

    public Grafo getKruskal(Grafo grafoml) {
        this.grafo = grafoml;

        //Varre a lista de vertices e armazena os ids na lista de verticesId
        vertices = grafo.getVertices();
        for (int i = 0; i < vertices.size(); i++) {
            verticesId.add(grafo.getVertices().get(i).getId());
        }

        ArrayList<Aresta> arvore = new ArrayList<Aresta>();

        for (int i = 0; i < vertices.size(); i++) {
            ArrayList<String> listaAux = new ArrayList<String>();
            listaAux.add(grafo.getVertices().get(i).getId());
            listaPais.add(listaAux);
        }

        ComparaAresta comparador = new ComparaAresta();

        arestas = (ArrayList<Aresta>) grafo.getArestas().clone();
        Collections.sort(arestas, comparador);

        for (int i = 0; i < arestas.size(); i++) {

            if (comparaPais(arestas.get(i).getOrigem().getId(), arestas.get(i).getDestino().getId())) {
                arvore.add(arestas.get(i));
                unir(arestas.get(i).getOrigem(), arestas.get(i).getDestino());
                unir(arestas.get(i).getDestino(), arestas.get(i).getOrigem());
            }
        }

        return  arestasParaArvore(arvore);
    }

    private boolean comparaPais(String origem, String destino) {
        return Collections.disjoint(pais(origem), pais(destino));
    }

    private ArrayList<String> pais(String idNo) {
        return listaPais.get(verticesId.indexOf(idNo));
    }

    private void unir(Vertice origem, Vertice destino) {
        int m = listaPais.get(verticesId.indexOf(destino.getId())).size();

        for (int i = 0; i < m; i++) {
            String x = listaPais.get(verticesId.indexOf(destino.getId())).get(i);
            if (!(listaPais.get(verticesId.indexOf(origem.getId())).contains(x))) {
                listaPais.get(verticesId.indexOf(origem.getId())).add(x);
            }
        }
    }

    private Grafo arestasParaArvore(ArrayList<Aresta> arvore) {
        Grafo grafo = new Grafo(this.grafo.getId(), this.grafo.getArestaPadrao(), this.vertices, arvore);
        return grafo;
    }

}
