package tsp;

import java.util.Stack;
/****** IMPLEMENTAÇÃO DO ALGORITMO “VIZINHO MAIS PRÓXIMO” (NEAREST NEIGHBOR)******
*Estes são os passos do algoritmo:
*    1. Escolha um vértice arbitrário como vértice atual.
*    2. Descubra a aresta de menor peso que seja conectada ao vértice atual e a um vértice não visitado V.
*    3. Faça o vértice atual ser V.
*    4. Marque V como visitado.
*    5. Se todos os vértices no domínio estiverem visitados, encerre o algoritmo.
*    6. Se não vá para o passo 2.
*/

public class VizinhoMaisProximo {

    private int numeroNos;
    private Stack<Integer> pilha;

    public VizinhoMaisProximo() {
        pilha = new Stack<Integer>();
    }

    public void tsp(int matrizAdjacencia[][]) {
        //Inicializando Variaveis
        this.numeroNos = matrizAdjacencia[1].length - 1;
        this.pilha.push(1);

        int[] visitado = new int[numeroNos + 1];
        int elemento, distancia = 0, i;
        int min = Integer.MAX_VALUE;
        boolean minFlag = false;

        visitado[1] = 1;

        System.out.print(1 + "\t");
        while (!pilha.isEmpty()) {
            //Pega elemento no topo da lista (sem remover) Passo 1
            elemento = pilha.peek();
            i = 1;
            min = Integer.MAX_VALUE;
            while (i <= this.numeroNos) { // Passo 2
                if (matrizAdjacencia[elemento][i] > 1 && visitado[i] == 0) {
                    if (min > matrizAdjacencia[elemento][i]) {
                        min = matrizAdjacencia[elemento][i]; // Passo 3
                        distancia = i;
                        minFlag = true;                     // Passo 4
                    }
                }
                i++;
            }
            if (minFlag) {                              // Passo 5
                visitado[distancia] = 1;
                this.pilha.push(distancia);
                System.out.print(distancia + "\t");

                minFlag = false;
                continue;
            }
            //Remove da pilha
            this.pilha.pop();
        }
    }

}
