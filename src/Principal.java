
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author straby
 */
public class Principal {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        
        Node no1 = new Node("A");
        Node no2 = new Node("B");
        Node no3 = new Node("C");

        Aresta aresta1 = new Aresta();
        Aresta aresta2 = new Aresta();
        
        aresta1.setSource(no1);
        aresta1.setTarget(no3);
        aresta2.setSource(no2);
        aresta2.setTarget(no3);
        
        ArrayList<Node> nos = new ArrayList();
        ArrayList<Aresta> arestas = new ArrayList();
        
        Grafo grafo = new Grafo(nos,arestas);
        
        aresta2.setIdaVolta(true);
        
        //funcaoXML(aresta1);
        funcaoXML(aresta2);
    }

    public static void funcaoXML(Aresta aresta) {
        XStream xml = new XStream(new DomDriver());
        xml.alias("no", Node.class);
        xml.alias("aresta", Aresta.class);

        File xmlFile = new File("nos.xml");
        try {
            xml.toXML(aresta, new FileWriter(xmlFile));
            System.out.println("Arquivo Criado");
        } catch (IOException ex) {
            System.out.println("Erro ao criar o arquivo");
        }
    }

}
