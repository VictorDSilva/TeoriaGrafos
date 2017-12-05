
import com.sun.corba.se.impl.orbutil.graph.Graph;
import java.util.ArrayList;
import java.util.Collections;

public class Kruskal {

    private ArrayList<ArrayList<String>> listaPais;
    private ArrayList<String> nosId;
    private ArrayList<Aresta> arestas;
    private ArrayList<Vertice> nos;
    private Grafo graphml;
    public Kruskal() {
        this.listaPais = new ArrayList();
        this.arestas = new ArrayList();
        this.nos = new ArrayList();
        this.nosId = new ArrayList();
        
    }

    public Grafo getKruskal(Grafo grafoml) {
        this.graphml = grafoml;
        
        nos = graphml.getGrafo().getVertices();
        for (int i = 0; i < nos.size(); i++) {
            nosId.add(graphml.getGrafo().getVertices().get(i).getId());
        }

        ArrayList<Aresta> arvore = new ArrayList<Aresta>();

        for (int i = 0; i < nos.size(); i++) {
            ArrayList<String> listaAux = new ArrayList<String>();
            listaAux.add(graphml.getGrafo().getVertices().get(i).getId());
            listaPais.add(listaAux);
        }
        ComparaArestas c = new ComparaArestas();
        arestas = (ArrayList<Aresta>) graphml.getGrafo().getArestas().clone();
        Collections.sort(arestas, c);
        for (int i = 0; i < arestas.size(); i++) {

            if (comparaPais(arestas.get(i).getOrigem().getId(), arestas.get(i).getDestino().getId())) {
                arvore.add(arestas.get(i));
                unir(arestas.get(i).getOrigem(), arestas.get(i).getDestino());
                unir(arestas.get(i).getDestino(), arestas.get(i).getOrigem());
            }

        }

        graphml.setGrafo(arestasParaArvore(arvore));
        return graphml;

    }

    private boolean comparaPais(String source, String target) {

        return Collections.disjoint(pais(source), pais(target));
    }

    private ArrayList<String> pais(String idNo) {

        return listaPais.get(nosId.indexOf(idNo));

    }

    private void unir(Vertice source, Vertice target) {
        int m = listaPais.get(nosId.indexOf(target.getId())).size();

        for (int i = 0; i < m; i++) {
            String x = listaPais.get(nosId.indexOf(target.getId())).get(i);
            if (!(listaPais.get(nosId.indexOf(source.getId())).contains(x))) {
                listaPais.get(nosId.indexOf(source.getId())).add(x);
            }
        }
    }


    private Graph arestasParaArvore(ArrayList<Aresta> arvore) {
//        ArrayList<Vertice> nosArvore = new ArrayList<>();
//        ArrayList<String> nosIdArvore = new ArrayList<>();
//        Vertice noAux;
//        String noAuxId;
//        for (int i = 0; i < arvore.size(); i++) {
//            noAux = arvore.get(i).getOrigem();
//            noAuxId = arvore.get(i).getOrigem().getId();
//            if (!(nosIdArvore.contains(noAuxId))) {
//                nosIdArvore.add(noAuxId);
//                nosArvore.add(noAux);
//            }
//            noAux = arvore.get(i).getDestino();
//            noAuxId = arvore.get(i).getDestino().getId();
//            if (!(nosIdArvore.contains(noAuxId))) {
//                nosIdArvore.add(noAuxId);
//                nosArvore.add(noAux);
//            }
//
//        }
        Grafo grafo = new Grafo(graphml.getGrafo().getId(), graphml.getGrafo().getArestadefault(), this.nos, arvore);
        return grafo;
    }

}
