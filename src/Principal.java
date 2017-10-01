
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
        ArrayList<Node> nos = new ArrayList();
        ArrayList<Aresta> arestas = new ArrayList();

        System.out.println("Digite as informacoes");
        Node node = new Node();
        Node node2 = new Node();

        System.out.print("Nome Node 1: ");
        String nome = entrada.next();
        node.setId(nome);

        System.out.print("Nome Node 2: ");
        nome = entrada.next();
        node2.setId(nome);

        Aresta aresta = new Aresta();
        aresta.setSource(node.getId());
        aresta.setTarget(node2.getId());

        nos.add(node);
        nos.add(node2);
        arestas.add(aresta);

        System.out.println("Aresta " + aresta.getSource() + aresta.getTarget());

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
