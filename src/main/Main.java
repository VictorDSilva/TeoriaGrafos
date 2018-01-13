package main;
//BIBLIOTECAS JAVA

import algoritmos.Dijkstra;
import java.io.IOException;
import java.util.Scanner;
//PACOTES DO MODELO
import modelo.Edge;
import modelo.Grafo;
//PACOTE ALGORTIMOS
import algoritmos.Kruskal;
import algoritmos.Prim;
import java.util.logging.Level;
import java.util.logging.Logger;
//PACOTES EXTERNOS
import thirdyparty.GeradorImagem;
import thirdyparty.GeradorXml;

public class Main {

    /**
     * @param args the command line arguments
     */
    static Scanner ler = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        System.out.println("TRABALHO DE GRAFOS");
        Grafo grafo = null;

        String aux = "";
        System.out.print("Desja criar um grafo direcionado? [s/N] ");
        aux = ler.next();

        if (aux.equals("s") || aux.equals("S")) {
            grafo = new Grafo("1");
        } else {
            grafo = new Grafo("1", "undirected");
        }

        String opt = " "; //variavel para guardar as opcoes do menu

        while (!opt.equals("0")) {
            System.out.println("************************");
            System.out.println("1 - Manipular Grafo");
            System.out.println("2 - Vizualizar Grafo");
            System.out.println("3 - Opções XML");
            System.out.println("4 - Gerar Imagem do Grafo");
            System.out.println("5 - Gerar Grafo de Dijkstra");
            System.out.println("6 - Gerar Grafo de Kruskal");
            System.out.println("7 - Gerar Grafo de Prim");

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
                    opcoesXML(grafo);
                    break;

                case "4":
                    String nome = "";
                    System.out.print("Digite o nome do XML: ");
                    nome = ler.next();

                    if (GeradorImagem.gerarImagem(nome + ".xml")) {
                        System.out.println("Imagem lida com sucesso!\n\n");
                    } else {
                        System.out.println("Erro ao Gerar Imagem :S\n\n");
                    }
                    break;

                case "5":
                    if (!grafo.getNodes().isEmpty()) {
                        Dijkstra dijkstra = new Dijkstra(grafo);
                        System.out.println("Escolha um vertice para informar menor caminho");
                        System.out.println(grafo.listarNodes());

                        System.out.print("ID do vertice 1: ");
                        String noRaiz = ler.next();
                        int i = dijkstra.getGrafo().getNodeIndice(noRaiz);
                        dijkstra.execute(dijkstra.getGrafo().getNodes().get(i));

                        System.out.print("ID do vertice 2: ");
                        String noMenor = ler.next();
                        int j = dijkstra.getGrafo().getNodeIndice(noMenor);

                        float menor = dijkstra.menorDistancia(dijkstra.getGrafo().getNodes().get(j));
                        if (menor == Float.MAX_VALUE) {
                            System.out.println("infinito");
                        } else {
                            System.out.println("Menor caminho sera: " + Float.toString(menor));
                        }

                        break;
                    } else {
                        System.out.println("\nE necessario criar um grafo!\n");
                    }
                    break;
                case "6":
                    if (!grafo.getNodes().isEmpty()) {
                        Kruskal kruskal = new Kruskal(grafo.getEdges(), grafo.getNodes(), grafo);
                        Grafo grafoKruskal = kruskal.getKruskal();
                        gerarXML(grafoKruskal);
                        break;
                    } else {
                        System.out.println("\nE necessario criar um grafo!\n");
                    }
                    break;

                case "7":
                    if (!grafo.getNodes().isEmpty()) {
                        Prim prim = new Prim();
                        Grafo grafoPrim = prim.getPrim(grafo);
                        gerarXML(grafoPrim);
                        break;
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
    }

    public static void manipularGrafo(Grafo grafo, String opt2) {
        String aux = "";
        int qtd = 0;

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
                System.out.print("Digite a quantidade de vertices: ");
                qtd = ler.nextInt();
                for (int i = 0; i < qtd; i++) {
                    System.out.print("Digite o ID do Node: ");
                    aux = ler.next();
                    grafo.addNodebyId(aux);
                }
                aux = "";
                break;
            case "2":
                //Criar arestas 
                if (grafo.getNodes().isEmpty()) {
                    System.out.println("É necessário criar vertices antes!");
                } else {
                    qtd = 0;
                    String pesoAresta = "";
                    System.out.println(grafo.listarNodes());
                    System.out.print("Digite a QUANTIDADE de arestas: ");
                    qtd = ler.nextInt();

                    for (int i = 0; i < qtd; i++) {
                        System.out.print("\nEscolha um vertice de Origem: ");
                        String origem = ler.next();

                        System.out.print("Escolha um vertice de Destino: ");
                        String destino = ler.next();

                        System.out.print("Digite o PESO da Aresta: ");
                        pesoAresta = ler.next();

                        Edge aresta = new Edge(grafo.buscaNode(origem), grafo.buscaNode(destino), pesoAresta);
                        grafo.addEdge(aresta);
                    }
                }
                break;
            case "3":
                //remover vértice
                System.out.println("Escolha um vértice para remover: ");
                System.out.println(grafo.listarNodes());
                System.out.print("Digite nome do vertice: ");
                aux = ler.next();
                if (aux.equals("0")) {
                    break;
                } else {
                    grafo.removerNode(grafo.buscaNode(aux));
                    System.out.println("Node Removido!");
                }
                aux = "";
                break;
            case "4":
                //remover aresta
                System.out.println("Escolha uma aresta para remover: ");
                System.out.println(grafo.listarEdges());
                System.out.print("Digite o ID da Aresta: ");
                aux = ler.next();
                if (aux.equals("0")) {
                    break;
                } else {
                    grafo.removerEdge(grafo.buscaEdge(aux));
                    System.out.println("Aresta Removida!");
                }
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
                System.out.println(grafo.listarNodes());
                break;
            case "2":
                //ver lista de aresta
                System.out.println(grafo.listarEdges());
                break;
            case "3":
                //Sumidouro
                System.out.print("Digite o ID do Node: ");
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
                System.out.print("Digite o ID do Node: ");
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
                System.out.println("A ORDEM do Grafo é: " + grafo.getOrdem());
                break;
            case "6":
                //Grau Emissao
                grafo.grauNodeEmissao();
                break;
            case "7":
                //Grau Recepcao
                grafo.grauNodeRecepcao();
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

    public static void opcoesXML(Grafo grafo) {

        System.out.println("************************");
        System.out.println("1 - Ler XML");
        System.out.println("2 - Gravar XML");
        System.out.println("************************");
        System.out.print("Escolha uma opção: ");
        String opt2 = ler.next();
        switch (opt2) {
            case "1": {
                try {
                    //ler grafo de arquivo XML
                    lerXML();
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            case "2": {
                try {
                    //gravar xml
                    gerarXML(grafo);
                } catch (IOException ex) {
                    System.out.println("Erro no Grafo");
                }
            }
            break;
        }
    }

    private static void gerarXML(Grafo grafo) throws IOException {
        System.out.print("Deseja gerar o XML do Grafo? SIM [S] / NÃO [n]: ");
        String resposta = ler.next();
        if (resposta.equals("S") || resposta.equals("s")) {
            System.out.print("Digite o nome do XML: ");
            String nome = ler.next();
            GeradorXml.gerarXmlGrafo(nome, grafo);
            System.out.println("XML GERADO!\n");
        } else {
            System.out.println("ERRO GERAR XML\n");
        }
    }

    private static void lerXML() throws IOException {
        System.out.print("Digite o nome do XML que será lido: ");
        String nome = ler.next();
        Grafo grafoXml = GeradorXml.lerXmlGrafo(nome);

        if (grafoXml == null) {
            System.out.println("Arquivo não encontrado!\n");
        } else {
            String opt = " "; //variavel para guardar as opcoes do menu

            while (!opt.equals("0")) {
                System.out.println("************************");
                System.out.println("INFORMAÇÕES DO GRAFO VIA XML");

                System.out.println("1 - Manipular Grafo");
                System.out.println("2 - Vizualizar Grafo");
                System.out.println("3 - Opções XML");
                System.out.println("4 - Gerar Imagem do Grafo");
                System.out.println("5 - Gerar Grafo de Dijkstra");
                System.out.println("6 - Gerar Grafo de Kruskal");
                System.out.println("7 - Gerar Grafo de Prim");

                System.out.println("0 - Sair do GrafoXML");
                System.out.println("************************");
                System.out.print("Escolha uma opção: ");
                opt = ler.next();
                switch (opt) {
                    case "1":
                        manipularGrafo(grafoXml, "1");
                        break;

                    case "2":
                        visualizarGrafo(grafoXml, "2");
                        break;

                    case "3":
                        opcoesXML(grafoXml);
                        break;

                    case "4":
                        if (GeradorImagem.gerarImagem(nome + ".xml")) {
                            System.out.println("Imagem lida com sucesso!\n\n");
                        } else {
                            System.out.println("Erro ao Gerar Imagem :S\n\n");
                        }
                        break;

                    case "5":
                        if (!grafoXml.getNodes().isEmpty()) {
                            Dijkstra dijkstra = new Dijkstra(grafoXml);
                            
                            System.out.println("Escolha um vertice para informar menor caminho");
                            System.out.println(grafoXml.listarNodes());

                            System.out.print("ID do vertice 1: ");
                            String noRaiz = ler.next();
                            int i = dijkstra.getGrafo().getNodeIndice(noRaiz);
                            dijkstra.execute(dijkstra.getGrafo().getNodes().get(i));

                            System.out.print("ID do vertice 2: ");
                            String noMenor = ler.next();
                            int j = dijkstra.getGrafo().getNodeIndice(noMenor);

                            float menor = dijkstra.menorDistancia(dijkstra.getGrafo().getNodes().get(j));
                            if (menor == Float.MAX_VALUE) {
                                System.out.println("infinito");
                            } else {
                                System.out.println("Menor caminho sera: " + Float.toString(menor));
                            }

                            break;
                        } else {
                            System.out.println("\nE necessario criar um grafo!\n");
                        }
                        break;
                    case "6":
                        if (!grafoXml.getNodes().isEmpty()) {
                            Kruskal kruskal = new Kruskal(grafoXml.getEdges(), grafoXml.getNodes(), grafoXml);
                            Grafo grafoKruskal = kruskal.getKruskal();
                            gerarXML(grafoKruskal);
                            break;
                        } else {
                            System.out.println("\nE necessario criar um grafo!\n");
                        }
                        break;

                    case "7":
                        if (!grafoXml.getNodes().isEmpty()) {
                            Prim prim = new Prim();
                            Grafo grafoPrim = prim.getPrim(grafoXml);
                            gerarXML(grafoPrim);
                            break;
                        } else {
                            System.out.println("\nE necessario criar um grafo!\n");
                        }
                        break;

                    case "0":
                        System.out.println("Saindo do GrafoXml...");
                        opt = "0";
                        break;
                    default:
                        System.out.println("**Opção inválida**");
                }
            }

            System.out.println("XML LIDO!\n");
            if (GeradorImagem.gerarImagem(nome + ".xml")) {
                System.out.println("Imagem lida com sucesso!\n\n");
            } else {
                System.out.println("Erro ao Gerar Imagem :S\n\n");
            }

        }
    }

}
