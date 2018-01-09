
import br.com.davesmartins.graphviewlib.ViewGraph;
import br.com.davesmartins.graphviewlib.erro.EGraphViewExcpetion;
import java.util.Scanner;

/**
 *
 * @author oduzuk
 */
public class GeradorMenu {

    public static Scanner ler = new Scanner(System.in);

    public static String geraMenu() {
        String aux = "";
        int qtd = 0;
        System.out.print("Desja criar um grafo orientado? [s/N] ");
        aux = ler.next();
        boolean orientado;

        orientado = aux.equals("s") || aux.equals("S");
        Grafo grafo = new Grafo(orientado);
        String opt = " "; //opt significa option
        aux = "";

        while (opt != "0") {
            System.out.println("************************");
            System.out.println("1 - Manipular Grafo");
            System.out.println("2 - Vizualizar Grafo");
            System.out.println("3 - Opcoees XML");
            System.out.println("4 - Gerar Imagem do Grafo");
            System.out.println("5 - Grafo de Kruskal");
            System.out.println("0 - Sair do programa");
            System.out.println("************************");
            System.out.print("Escolha uma opção: ");
            opt = ler.next();
            switch (opt) {
                case "1":
                    manipularGrafo(grafo, "1");
                    break;

                case "2":
                    visualizarGrafo(grafo, "2");
                    break;

                case "3":
                    opcoesXML(grafo, "grafo");
                    break;

                case "4":
                    gerarImagem("grafo");
                    break;

                case "5":
                    if (!grafo.getVertices().isEmpty()) {
                        Kruskal kruskal = new Kruskal(grafo.getArestas(), grafo.getVertices(), grafo);
                        Grafo grafoKruskal = kruskal.getKruskal();

                        System.out.println("Gerenciar XML KRUSKAL");
                        opcoesXML(grafoKruskal, "kruskal");

                        String opt3 = " ";
                        System.out.print("Deseja vizualizar imagem [s/N]? ");
                        opt3 = ler.next();
                        boolean geraImg;

                        geraImg = opt3.equals("s") || opt3.equals("S");
                        if(geraImg){                            
                            gerarImagem("kruskal");
                        } else {
                            break;
                        }
                    } else {
                        System.out.println("\nE necessario criar um grafo!\n");
                    }
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

    public static void manipularGrafo(Grafo grafo, String opt2) {
        String aux = "";
        int qtd = 0;
        int pesoAresta = 0;

        System.out.println("************************");
        System.out.println("1 - Criar Vértice");
        System.out.println("2 - Criar Aresta");
        System.out.println("3 - Remover Vértice");
        System.out.println("4 - Remover Aresta");
        System.out.println("************************");
        System.out.print("Escolha uma opção: ");
        opt2 = ler.next();
        switch (opt2) {
            case "1":
                //Criar vertices 
                qtd = 0;
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
                    System.out.print("Digite seu peso da aresta: ");
                    pesoAresta = ler.nextInt();
                    int peso = pesoAresta;
                    if (peso > 0) {
                        grafo.criarAresta(origem, destino, peso);
                    } else {
                        System.out.println("Numero invalido!");
                        break;
                    }

                }
                break;
            case "3":
                //remover vértice
                System.out.println("Escolha um vértice para remover: ");
                System.out.println(grafo.listarVertice());
                System.out.print("Digite nome do vertice: ");
                aux = ler.next();
                grafo.removerVertice(aux);
                aux = "";
                break;
            case "4":
                //remover aresta
                System.out.println("Escolha uma aresta para remover: ");
                System.out.println(grafo.listarAresta());
                System.out.print("Digite o nome da Aresta: ");
                aux = ler.next();
                grafo.removerAresta(aux);
                aux = "";
                break;
        }
    }

    public static void visualizarGrafo(Grafo grafo, String opt2) {
        String aux = "";
        int qtd = 0;
        System.out.println("************************");
        System.out.println("1 -  Ver Vértices");
        System.out.println("2 -  Ver Aresta");
        System.out.println("3 -  Ver Se é Sumidouro");
        System.out.println("4 -  Ver Se é Fonte");
        System.out.println("5 -  Ver Ordem do Grafo");
        System.out.println("6 -  Ver Grau de Emissão");
        System.out.println("7 -  Ver Grau de Recepção");
        System.out.println("8 -  Ver Se é Multigrafo");
        System.out.println("9 -  Ver Se é Grafo Completo");
        System.out.println("10 - Ver Se é Grafo Regular");
        System.out.println("11 - Ver Matrizes Incidencia");
        System.out.println("12 - Ver Matrizes Adjascencia");
        System.out.println("13 - Ver Lista de Adjascencia");
        System.out.println("************************");
        System.out.print("Escolha uma opção: ");
        opt2 = ler.next();
        switch (opt2) {
            case "1":
                //ver lista de vértices 
                System.out.println(grafo.listarVertice());
                break;
            case "2":
                //ver grafo
                System.out.println(grafo.listarAresta());
                break;
            case "3":
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
            case "4":
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
            case "5":
                //Ordem do Grafo
                grafo.getOrdem();
                break;
            case "6":
                //Grau Emissao
                grafo.grauVerticeEmissao();
                break;
            case "7":
                //Grau Recepcao
                grafo.grauVerticeRecepcao();
                break;
            case "8":
                //ver se é multigrafo
                grafo.imprimeMultigrafo();
                break;
            case "9":
                //ver se é completo
                grafo.imprimeCompleto();
                break;
            case "10":
                //ver se é regular
                grafo.imprimeRegular();
                break;
            case "11":
                System.out.println("Matriz de Incidencia");
                grafo.imprimeMatrizIncidencia();
                break;
            case "12":
                System.out.println("Matriz de Adjacencia");
                grafo.imprimeMatrizAdjacencia();
                break;
            case "13":
                //ver lista de adjacencia dos vertices           
                grafo.listaAdjacencias();
                break;
        }
    }

    public static void opcoesXML(Grafo grafo, String nome) {
        System.out.println("************************");
        System.out.println("1 - Ler XML");
        System.out.println("2 - Gravar XML");
        System.out.println("************************");
        System.out.print("Escolha uma opção: ");
        String opt2 = ler.next();
        switch (opt2) {
            case "1":
                try {
                    grafo.lerXML(nome);
                } catch (Exception ex) {
                    System.out.print("Banco não encontrado, tente novamente: ");
                }
                break;
            case "2":
                //gravar xml
                grafo.gravarXML(nome);
                break;
        }
    }

    public static void gerarImagem(String nome) {
        try {
            ViewGraph.generateViewGraphByFrame(nome + ".xml");
        } catch (EGraphViewExcpetion ex) {
            System.out.println("-->" + ex.getMensagem());
        }
        System.out.println("Imagem gerada!\n");
    }
}
