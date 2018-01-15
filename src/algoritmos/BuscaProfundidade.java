package algoritmos;

import java.util.ArrayList;
import modelo.Grafo;

public class BuscaProfundidade {

    Grafo graph = null;

    public BuscaProfundidade(Grafo grafo) {
        this.graph = grafo;
    }

    public ArrayList<String> buscar(String root, ArrayList<String> visitados) {
        ArrayList<String> verticesAdjacentesAnterior = this.graph.gerarVerticesAdjacentes(root);
        ArrayList<String> nosAdjacentes = new ArrayList();
        visitados.add(root);

        verticesAdjacentesAnterior.stream().filter((noAdj) -> (!visitados.contains(noAdj))).forEachOrdered((noAdj) -> {
            nosAdjacentes.add(noAdj);
        });

        nosAdjacentes.stream().filter((noAdj) -> (!visitados.contains(noAdj))).forEachOrdered((String noAdj) -> {
            buscar(noAdj, visitados);
        });
        return visitados;
    }
}
