
import java.util.Scanner;

/**
 *
 * @author oduzuk
 */
public class GeradorMenu {

    public static String geraMenu() {
        Scanner ler = new Scanner(System.in);
        String aux = "";
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
            System.out.println("12 - Ver matrizes Incidencia");
            System.out.println("13 - Ver matrizes Adjascencia");
            System.out.println("14 - Ver Lista Incidencia");
            System.out.println("15 - Ver Lista Adjascencia");
            System.out.println("16 - Ver Se é Multigrafo");
            System.out.println("17 - Ver Se é Completo");
            System.out.println("18 - Ver Se é Regular");
            System.out.println("19 - Remover Vértice");
            System.out.println("20 - Remover  Aresta");
            System.out.println("0 - Sair do programa");
            System.out.println("************************");
            System.out.print("Escolha uma opção: ");
            opt = ler.next();
            switch (opt) {
                case "1":
                    //Criar vertices                    
                    System.out.print("Digite a quantidade de vertices: ");
                    qtd = ler.nextInt();
                    for (int i = 0; i < qtd; i++) {
                        System.out.print("Digite o nome do vertice: ");
                        aux = ler.next();
                        grafo.criarVertice(aux);
                    }
                    aux = "";
                    break;
                case "2":
                    //criar arestas 
                    System.out.println(grafo.listarVertice());
                    System.out.print("Digite a quantidade de arestas: ");
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
                    //Grau Emissao
                    grafo.grauVerticeEmissao();
                    break;
                case "6":
                    //Grau Recepsao
                    grafo.grauVerticeRecepcao();
                    break;
                case "7":
                    //ver grafo
                    System.out.println(grafo);
                    break;
                case "8":
                    //Sumidouro
                    System.out.print("Digite o vertice: ");
                    aux = ler.next();
                    if (grafo.isSumidouro(aux) != true) {
                        System.out.println("É sumidouro");
                    } else {
                        System.out.println("Nao e sumidouro");
                    }
                    aux = "";
                    break;
                case "9":
                    //Fonte
                    System.out.print("Digite o vertice: ");
                    aux = ler.next();
                    if (grafo.isFonte(aux) != true) {
                        System.out.println("É fonte");
                    } else {
                        System.out.println("Nao e fonte");
                    }
                    aux = "";
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
                    System.out.println("Matriz de Incidencia");
                    grafo.imprimeMatrizIncidencia();
                    break;
                case "13":
                    System.out.println("Matriz de Adjacencia");
                    grafo.imprimiAdjacencia();
                    break;
                case "14":
                    //ver lista de incidencia
                    break;
                case "15":
                    //ver lista de adjacencia
                    break;
                case "16":
                    //ver se é multigrafo
                    grafo.imprimeMultigrafo();
                    break;
                case "17":
                    //ver se é completo
                    grafo.imprimeCompleto();
                    break;
                case "18":
                    //ver se é regular
                    grafo.imprimeRegular();
                    break;
                case "19":
                    //remover vértice
                    System.out.println("Escolha um vértice para remover: ");
                    System.out.println(grafo.listarVertice());
                    aux = ler.next();
                    grafo.removerVertice(aux);
                    aux = "";
                    break;
                case "20":
                    //remover aresta
                    System.out.println("Escolha uma aresta para remover: ");
                    System.out.println(grafo);
                    aux = ler.next();
                    grafo.removerAresta(aux);
                    aux = "";
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
