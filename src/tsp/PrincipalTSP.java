package tsp;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author jjti
 */
public class PrincipalTSP {

    public static void main(String[] arg) {
        int number_of_nodes;
        Scanner scanner = null;
        try {
            System.out.print("Digite com Numero de VERTICES do Grafo: ");
            scanner = new Scanner(System.in);
            number_of_nodes = scanner.nextInt();

            int adjacency_matrix[][] = new int[number_of_nodes + 1][number_of_nodes + 1];
            System.out.println("Entre com a Matriz de Adjacencia");

            for (int i = 1; i <= number_of_nodes; i++) {
                for (int j = 1; j <= number_of_nodes; j++) {
                    adjacency_matrix[i][j] = scanner.nextInt();
                }
            }
            for (int i = 1; i <= number_of_nodes; i++) {
                for (int j = 1; j <= number_of_nodes; j++) {
                    if (adjacency_matrix[i][j] == 1 && adjacency_matrix[j][i] == 0) {
                        adjacency_matrix[j][i] = 1;
                    }
                }
            }

            System.out.println("\nA ordem para visita será: ");
            VizinhoMaisProximo algoritmoTSP = new VizinhoMaisProximo();
            algoritmoTSP.tsp(adjacency_matrix);
            
        } catch (InputMismatchException inputMismatch) {
            System.out.println("Formato Inválido!");
        }
        scanner.close();
    }
}
