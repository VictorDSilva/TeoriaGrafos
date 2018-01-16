package tsp;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author jjti
 */
public class PrincipalTSP {

    public static void main(String[] arg) {
        int numeroNodes;
        Scanner scanner = null;
        try {
            System.out.print("Digite com Numero de VERTICES do Grafo: ");
            scanner = new Scanner(System.in);
            numeroNodes = scanner.nextInt();

            int matrizAdj[][] = new int[numeroNodes + 1][numeroNodes + 1];
            System.out.println("Entre com a Matriz de Adjacencia");

            for (int i = 1; i <= numeroNodes; i++) {
                for (int j = 1; j <= numeroNodes; j++) {
                    matrizAdj[i][j] = scanner.nextInt();
                }
            }
            for (int i = 1; i <= numeroNodes; i++) {
                for (int j = 1; j <= numeroNodes; j++) {
                    if (matrizAdj[i][j] == 1 && matrizAdj[j][i] == 0) {
                        matrizAdj[j][i] = 1;
                    }
                }
            }

            System.out.println("\nA ordem para visita será: ");
            VizinhoMaisProximo algoritmoTSP = new VizinhoMaisProximo();
            algoritmoTSP.tsp(matrizAdj);
            
        } catch (InputMismatchException inputMismatch) {
            System.out.println("Formato Inválido!");
        }
        scanner.close();
    }
}
