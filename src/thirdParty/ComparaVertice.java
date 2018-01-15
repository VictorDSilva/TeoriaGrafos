package thirdParty;

import java.util.Comparator;
import modelo.Node;


public class ComparaVertice implements Comparator<Node> {

    @Override
    public int compare(Node no1, Node no2) {
        return no1.getId().compareToIgnoreCase(no2.getId());
    }
}
