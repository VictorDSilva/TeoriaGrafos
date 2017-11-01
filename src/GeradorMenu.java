
import java.util.Scanner;

/**
 *
 * @author oduzuk
 */
public class GeradorMenu {

    public static String geraMenu() {
        Scanner ler = new Scanner(System.in);
        int qtd = 0;
        Grafo grafo = new Grafo();

        String opt = " "; //opt significa option!!!!!!!!!
        while (opt != "0") {
            System.out.println("************************");
            System.out.println("1 - Criar Vértice");
            System.out.println("2 - Criar Aresta");
            System.out.println("3 - Ver Vértices");
            System.out.println("4 - Ordem do Grafo");
            System.out.println("5 - Grau de Emissão");
            System.out.println("6 - Grau de Recepção");
            System.out.println("7 - Ver Grafo");
            System.out.println("8 - Verificar se é Sumidouro");
            System.out.println("9 - Veririficar se é Fonte");
            System.out.println("10 - Ler XML");
            System.out.println("11 - Gravar XML");
            System.out.println("12 - Ver matrizes Adjascencia/Incidencia");
            System.out.println("0 - Sair do programa");
            System.out.println("************************");
            System.out.print("Escolha uma opção: ");
            opt = ler.next();
            switch (opt) {
                case "1":
                    //Criar vertices
                    String variavel = " ";
                    System.out.print("Digite a quantidade de vertices: ");
                    qtd = ler.nextInt();
                    for (int i = 0; i < qtd; i++) {
                        System.out.print("Digite o nome do vertice: ");
                        variavel = ler.next();
                        grafo.criarVertice(variavel);
                    }
                    break;
                case "2":
                    //criar arestas 
                    System.out.println(grafo.listarVertice());
                    System.out.println("Digite a quantidade de arestas: ");
                    qtd = ler.nextInt();
                    for (int i = 0; i < qtd; i++) {
                        System.out.print("Escolha um vertice de Origem: ");
                        String origem = ler.next();
                        System.out.print("Escolha um vertice de Destino: ");
                        String destino = ler.next();
                        grafo.criarAresta(origem, destino);
                    }
                    break;
                case "3":
                    //ver vértices 
                    System.out.println(grafo.listarVertice());
                    break;
                case "4":
                    //Ordem do Grafo
                    grafo.getOrdem();
                    break;
                case "5":
                    //Grau
                    grafo.grauVerticeEmissao();
                    break;
                case "6":
                    //Grau
                    grafo.grauVerticeRecepcao();
                    break;
                case "7":
                    //ver grafo
                    System.out.println(grafo);
                    break;
                case "8":
                    //Sumidouro
                    System.out.print("Digite o vertice: ");
                    int x = ler.nextInt();
                    grafo.getSumidouro(x);
                    break;
                case "9":
                    //Fonte
                    System.out.print("Digite o vertice: ");
                    int y = ler.nextInt();
                    grafo.getFonte(y);
                    break;
                case "10":
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
                case "11":
                    //gravar xml
                    grafo.gravarXML();
                    break;
                case "12":
                    grafo.imprimeMatrizAdj();
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
