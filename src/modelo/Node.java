package modelo;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import java.util.ArrayList;

/**
 *
 * @author jjti
 */
public class Node {

    @XStreamAsAttribute
    private String id;
    private String label;
    private ArrayList<Edge> adjacencias;

    public Node(String id) {
        this.id = id;
    }

    public Node(String id, String label) {
        this.id = id;
        this.label = label;
    }
        

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Edge> getAdjacencias() {
        return adjacencias;
    }

    public void setAdjacencias(ArrayList<Edge> adjacencias) {
        this.adjacencias = adjacencias;
    }

    public void addAdjacencia(Edge e) {
        adjacencias.add(e);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
    

}
