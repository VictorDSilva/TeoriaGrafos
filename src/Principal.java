<<<<<<< HEAD

import java.util.Scanner;

/**
 *
 * @author straby as Jonas Gomes (github.com/blackstraby) 
 *
 */
public class Principal {

    static Scanner ler = new Scanner(System.in);

    public static void main(String[] args) {
        Grafo grafo = new Grafo();

        if (grafo.getVertices().isEmpty()) {
            System.out.println("Nao existem vertices!");
        }

        Vertice A = grafo.addVertice("A");
        Vertice B = grafo.addVertice("B");
        System.out.println(listaVertice(grafo));

        System.out.print("Digite o nome do vertice: ");
        Vertice vertice = grafo.addVertice(ler.next());

        if (grafo.getVertices().size() > 0) {

            System.out.print("Quer ligar o vertice " + vertice.getNome() + " com: ");
            String origem = ler.next();
            System.out.print("E ida e volta? [0 u 1]: ");
            int idaVolta = ler.nextInt();

            for (Vertice v : grafo.getVertices()) {
                if (v.getNome() == null ? origem == null : v.getNome().equals(origem)) {
                    Aresta origDestin = grafo.addAresta(vertice, v, (idaVolta == 1 ? true:false));
                }
            }

            Aresta AB = grafo.addAresta(A, B, true);

            System.out.println("Grafo: ");
            System.out.println(grafo);

        }

    }

    public static int geraMenu() {
        int opcao = 0;

        System.out.println("************************");
        System.out.println("1 - Adicionar Vértice");
        System.out.println("2 - Adicionar Aresta");
        System.out.println("3 - Ver Grafo");
        System.out.println("0 - Sair do programa");
        System.out.println("************************");
        System.out.print("Escolha uma opção: ");

        opcao = ler.nextInt();
        return opcao;
    }

    public static String listaVertice(Grafo grafo) {

        String r = "";

        for (Vertice u : grafo.getVertices()) {
            r += u.getNome();
            r += "\n";
        }
        return r;
    }

}
=======
/**
 *
 * @author straby as Jonas Gomes (github.com/blackstraby)
 *
 */
public class Principal {
    public static void main(String[] args) {
        GeradorMenu.geraMenu();
    }

}
>>>>>>> e567f4f0a7c76d25411caa31568a621cad793fce
