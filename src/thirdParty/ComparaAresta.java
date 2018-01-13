package thirdyparty;

import java.util.Comparator;
import modelo.Edge;

public class ComparaAresta implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        Edge aresta1 = (Edge) o1;
        Edge aresta2 = (Edge) o2;
        if (aresta1.getPeso() > aresta2.getPeso()) {
            return 1;
        }
        if (aresta1.getPeso() < aresta2.getPeso()) {
            return -1;
        }
        return 0;
    }

}
