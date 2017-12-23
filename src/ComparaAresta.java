
import java.util.Comparator;

public class ComparaAresta implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        Aresta aresta1 = (Aresta) o1;
        Aresta aresta2 = (Aresta) o2;
        if (aresta1.getPeso() > aresta2.getPeso()) {
            return 1;
        }
        if (aresta1.getPeso() > aresta2.getPeso()) {
            return -1;
        }
        return 0;
    }
}
