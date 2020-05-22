package packCentroGravitacional;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class centroGravitacionalMain {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        FileReader arquivo = new FileReader("centro_gravitacional_ap2/packCentroGravitacional/entrada.txt");
        double matriz[][] = lerMatrizDoArquivo(arquivo);

        mostrarCentroGravitacional(matriz);
    }

    public static double[][] lerMatrizDoArquivo(FileReader arquivo) throws IOException {
        BufferedReader leitor = new BufferedReader(arquivo);
        Scanner sc = new Scanner(leitor);

        String entrada = leitor.readLine();
        int linhas = Integer.parseInt(entrada.trim().split(" ")[0]);
        int colunas = Integer.parseInt(entrada.trim().split(" ")[1]);
        double[][] matriz = new double[linhas][colunas];

        while (sc.hasNextLine()) {
            for (int i = 0; i < matriz.length; i++) {
                String[] linhaArquivo = sc.nextLine().trim().split(" ");
                for (int j = 0; j < linhaArquivo.length; j++) {
                    matriz[i][j] = Double.parseDouble(linhaArquivo[j]);
                }
            }
        }
        sc.close();

        return matriz;
    }

    public static void mostrarCentroGravitacional(double[][] matriz) {
        int numeroLinhas = matriz.length;
        int numeroColunas = matriz[0].length;

        int linhaCentro = centroGravitacional(matriz, numeroColunas, numeroLinhas, "linha");
        int colunaCentro = centroGravitacional(matriz, numeroLinhas, numeroColunas, "coluna");
        System.out.printf("Centro: (%d, %d)", linhaCentro + 1, colunaCentro + 1);
    }

    public static int centroGravitacional(double[][] matriz, int dimensao1, int dimensao2, String modo) {
        double menorDiferenca = 0, diferenca = 0;
        int centro = 0;
        /*
         * O método abaixo foi desenvolvido de forma a ser ultilizado para encontrar o
         * centro das linhas e também das colunas da matriz.
         * 
         * O laço com variável K diz repeito a coluna ou linha que está sendo analizada
         * em comparação com as colunas a esquerda e direita ou as linhas acima ou
         * abaixo. Ele inicia a iteração da coluna 1 (e não da coluna 0) e vai até a
         * penultima coluna ou da linha 1 e vai até a penultima linha.
         * 
         * O Laço dimensao2 verifica quais são as somas dos valores de todas as linhas
         * das colunas a esquerda e a direita da coluna que está sendo analisada, ou a
         * soma dos valores das colunas das linhas acima e abaixo da linha que está
         * sendo análisada.
         * 
         * o Laço for dentro da condicional if complementa a operação iterando as linhas
         * da coluna ou colunas da linha.
         */
        for (int k = 1; k < dimensao2 - 1; k++) {
            double soma1 = 0, soma2 = 0;

            dimensao2: for (int i = 0; i < dimensao2; i++) {
                if (i < k) {
                    for (int l = 0; l < dimensao1; l++) {
                        soma1 += (modo == "coluna") ? matriz[l][i] : matriz[i][l];
                    }
                } else if (i == k) {
                    continue dimensao2;
                } else {
                    for (int l = 0; l < dimensao1; l++) {
                        soma2 += (modo == "coluna") ? matriz[l][i] : matriz[i][l];
                    }
                }
            }
            /*
             * Para calcular a diferança o maior valor valor é comparado ao menor então
             * realizada a subtração do maior pelo menor
             */

            diferenca = (soma1 > soma2) ? soma1 - soma2 : soma2 - soma1;

            /*
             * K == 1 garante que na primeira vez que o laço for passar por aqui um valor
             * será agregado para menorDiferenca para que possa ser porteriormente
             * comparado. a Comparação diferenca < MenorDiferenca garante que o menor centro
             * de gravidade seja encontrado e que apenas o primeiro a ser encontrado com
             * esse menor valor seja considerado
             */
            if ((k == 1) || (diferenca < menorDiferenca)) {
                menorDiferenca = diferenca;
                centro = k;
            }
        }
        return centro;
    }
}
