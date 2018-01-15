package tsp;

import java.util.Stack;

public class VizinhoMaisProximo {

    private int numeroNos;
    private Stack<Integer> pilha;

    public VizinhoMaisProximo() {
        pilha = new Stack<Integer>();
    }

    public void tsp(int matrizAdjacencia[][]) {
        this.numeroNos = matrizAdjacencia[1].length - 1;
        this.pilha.push(1);
        
        int[] visitado = new int[numeroNos + 1];
        int elemento, distancia = 0, i;
        int min = Integer.MAX_VALUE;
        boolean minFlag = false;
        
        visitado[1] = 1;        
        
        System.out.print(1 + "\t");
        while (!pilha.isEmpty()) {
            elemento = pilha.peek();
            i = 1;
            min = Integer.MAX_VALUE;
            while (i <= numeroNos) {
                if (matrizAdjacencia[elemento][i] > 1 && visitado[i] == 0) {
                    if (min > matrizAdjacencia[elemento][i]) {
                        min = matrizAdjacencia[elemento][i];
                        distancia = i;
                        minFlag = true;
                    }
                }
                i++;
            }
            if (minFlag) {
                visitado[distancia] = 1;
                this.pilha.push(distancia);                
                System.out.print(distancia + "\t");
                
                minFlag = false;
                continue;
            }
            this.pilha.pop();
        }
    }

}
