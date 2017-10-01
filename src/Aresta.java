/**
 *
 * @author straby
 */
public class Aresta {       
    private Node source;
    private Node target;
    private boolean idaVolta;
    
    
    public Aresta() {
    }

    public Node getSource() {
        return source;
    }

    public void setSource(Node source) {
        this.source = source;
    }

    public Node getTarget() {
        return target;
    }

    public void setTarget(Node target) {
        this.target = target;
    }

    public boolean isIdaVolta() {
        return idaVolta;
    }

    public void setIdaVolta(boolean idaVolta) {
        this.idaVolta = idaVolta;
    }
    
    
}