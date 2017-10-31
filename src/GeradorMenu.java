
import java.util.Scanner;

/**
 *
 * @author oduzuk
 */
public class GeradorMenu {

    public static String geraMenu() {
        Scanner ler = new Scanner(System.in);

        Grafo grafo = new Grafo();

        String opt = " "; //opt significa option!!!!!!!!!
        while (opt != "0") {
            System.out.println("************************");
            System.out.println("1 - Criar Vértice");
            System.out.println("2 - Criar Aresta");
            System.out.println("3 - Ver Vértices");
            System.out.println("4 - Ver Grafo");
            System.out.println("5 - Ler XML");
            System.out.println("6 - Gravar XML");
            System.out.println("0 - Sair do programa");
            System.out.println("************************");
            System.out.print("Escolha uma opção: ");
            opt = ler.next();
            switch (opt) {
                case "1":
                    //Criar vertices
                    System.out.print("Digite a quantidade de vertices: ");
                    int qt = ler.nextInt();
                    for (int i = 0; i < qt; i++) {
                        System.out.println("Nome Vertice: ");
                        grafo.criarVertice(ler.nextLine());
                    }
                    
                    break;
                case "2":
                    //criar arestas 
                    System.out.println(grafo.listarVertice());
                    System.out.println("Digite a quantidade de arestas: ");
                    qt = ler.nextInt();

                    for (int i = 0; i < qt; i++) {
                        System.out.print("Escolha um vertice de Origem: ");
                        int origem = ler.nextInt();
                        System.out.print("Escolha um vertice de Destino: ");
                        int destino = ler.nextInt();
                        grafo.criarAresta(origem, destino);
                    }

                    break;
                case "3":
                    //ver vértices 
                    System.out.println(grafo.listarVertice());
                    break;
                case "4":
                    //ver grafo
                    System.out.println(grafo);
                    break;
                case "5":
                    Boolean menu = true;
                    Scanner input = new Scanner(System.in);
                    System.out.print("Qual o caminho do banco de palavras que deseja utilizar?: ");

                    do {
                        try {
                            grafo.lerXML(input.nextLine());
                            menu = false;

                        } catch (Exception ex) {
                            System.out.print("Banco não encontrado, tente novamente: ");
                        }

                    } while (menu);
                    //grafo.lerXML();
                    break;
                case "6":
                    //gravar xml
                    grafo.gravarXML();
                    break;
                case "0":
                    System.out.println("Saindo do programa...");
                    opt = "0";
                    break;
                default:
                    System.out.println("**Opção inválida**");

            }
        }

        return opt;

    }

}
