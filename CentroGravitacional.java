
/**
 * avaliacao
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class avaliacao_v1 {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        

        FileReader arquivo = new FileReader("entrada.txt");
        BufferedReader ler = new BufferedReader(arquivo);

        String entradas = ler.readLine();
        
        int linhas = Integer.parseInt(entradas.split(" ")[0]); //recebe a quantidade de linhas de acordo com o arquivo txt
        int colunas = Integer.parseInt(entradas.split(" ")[1]); //recebe a quantidade de colunas de acordo com o arquivo txt
        // double[][] m = new double[linhas][colunas];

        // for (int i = 0; i < m.length; i++) { //preenche a matriz randomicamente com valores de uma casa decimal
        //     for (int j = 0; j < m[0].length; j++) {
        //         m[i][j] = Math.ceil(Math.random() * 10) / 10;
        //     }
        // } 

        double[][] m = {{0.1, 0.2, 0.1, 0.2, 0.1},
                        {0.1, 0.2, 0.3, 0.1, 0.1},
                        {0.2, 0.3, 0.1, 0.1, 0.3},
                        {0.4, 0.1, 0.1, 0.1, 0.2},
                        {0.2, 0.2, 0.3, 0.3, 0.1}};
        
        double[] somasLinhas = new double[linhas - 1]; //cada item recebera a soma de cada linha
        double[] somasColunas = new double[colunas - 1]; //cada item recebera a soma de cada coluna
        double total1 = 0; //primeira e determinante soma
        double total2 = 0; //segunda soma em diante, caso resulte em um numero menor que a primeira, sera atribuida a primeira soma
        boolean pri = true; //se for a primeira execucao
        int centroGravidade = 0; //determinar o centro de gravidade entre as linhas e coluans

        //reutilizando a variavel linhas para determinar qual das linhas sera o centro durante a diferenciacao entre
        //as porcoes acima e abaixo dela
        for (linhas = 1; linhas < m.length - 1; linhas++) {
            for (int i = 0; i < m.length; i++) {
                //se i for igual a linha correspondente ao centro, executar o segundo for
                if (i == linhas) {
                    break;
                }
                for (int j = 0; j < m[0].length; j++) {
                    somasLinhas[i] += m[i][j];
                }
            } 

            for (int i = linhas + 1; i < m.length; i++) {
                for (int j = 0; j < m[0].length; j++) {
                    somasLinhas[i - 1] -= m[i][j]; //subtraindo para facilitar na diferenciacao entre as porcoes
                }
            }

            if (pri) {
                for (int i = 0; i < somasLinhas.length; i++) {
                    total1 += somasLinhas[i]; //recebe o valor da diferenca entre as somas das porcoes acima e abaixo do centro de gravidade
                }

                if (total1 < 0) {
                    total1 *= -1;
                }

                total2 = total1; //necessario atribuir um valor ao total2 que nao 0 ou qualquer aleatorio
                pri = false;
            } else { //executado a partir do segun do loop
                total2 = 0;

                for (int i = 0; i < somasLinhas.length; i++) {
                    total2 += somasLinhas[i];
                }

                if (total2 < 0) {
                    total2 *= -1;
                }
            }

            if (total2 < total1) { //determinar o centro de gravidade
                total1 = total2;
                centroGravidade = linhas;
            }

            for (int i = 0; i < somasLinhas.length; i++) {
                somasLinhas[i] = 0;
            }
        }

        System.out.println(centroGravidade + 1);
        pri = true;

        //mesmo procedimento feito com as linhas, agora com as colunas
        for (colunas = 1; colunas < m[0].length - 1; colunas++) {
            for (int i = 0; i < m[0].length; i++) {
                //se i for igual a coluna correspondente ao centro, executar o segundo for
                if (i == colunas) {
                    break;
                }
                for (int j = 0; j < m.length; j++) {
                    somasColunas[i] += m[j][i];
                }
            } 

            for (int i = colunas + 1; i < m[0].length; i++) {
                for (int j = 0; j < m.length; j++) {
                    somasColunas[i - 1] -= m[j][i]; //subtraindo para facilitar na diferenciacao entre as porcoes
                }
            }

            if (pri) {
                for (int i = 0; i < somasColunas.length; i++) {
                    total1 += somasColunas[i]; //recebe o valor da diferenca entre as somas das porcoes a esquerda e a direita do centro de gravidade
                }

                if (total1 < 0) {
                    total1 *= -1;
                }

                total2 = total1; //necessario atribuir um valor ao total2 que nao 0 ou qualquer aleatorio
                pri = false;
            } else { //executado a partir do segun do loop
                total2 = 0;

                for (int i = 0; i < somasColunas.length; i++) {
                    total2 += somasColunas[i];
                }

                if (total2 < 0) {
                    total2 *= -1;
                }
            }

            if (total2 < total1) { //determinar o centro de gravidade
                total1 = total2;
                centroGravidade = colunas;
            }

            for (int i = 0; i < somasColunas.length; i++) {
                somasColunas[i] = 0;
            }
        }

        System.out.println(centroGravidade + 1);
    }

    /**
     * @deprecated
     * @
     */
    public void teste(){

    }
}