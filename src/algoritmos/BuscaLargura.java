
package algoritmos;
import java.util.ArrayList;
import java.util.LinkedList;
import modelo.Grafo;

public class BuscaLargura{

    Grafo graph = null;

    public BuscaLargura(Grafo grafo) {
        this.graph = grafo;
    }


    public ArrayList<String> buscar(String root, ArrayList<String> visitados) {
//        ArrayList<String> verticesAdjacentesAnteriores = graph.gerarVerticesAdjacentes(root);
//        ArrayList<String> nosAdjacentes = new ArrayList<>();;
        visitados.add(root);
        LinkedList<String> filaDeNosAVisitar = new LinkedList();
        filaDeNosAVisitar.add(root);
        while (!filaDeNosAVisitar.isEmpty()) {
            String no = filaDeNosAVisitar.pop();
            this.graph.gerarVerticesAdjacentes(no).stream().filter((verticeAdjacente) -> (!visitados.contains(verticeAdjacente))).map((verticeAdjacente) -> {
                visitados.add(verticeAdjacente);
                return verticeAdjacente;
            }).forEachOrdered((verticeAdjacente) -> {
                filaDeNosAVisitar.add(verticeAdjacente);
            });
        }
        return visitados;
    }
}