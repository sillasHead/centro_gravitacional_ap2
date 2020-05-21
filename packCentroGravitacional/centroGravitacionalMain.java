package packCentroGravitacional;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class centroGravitacionalMain {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        LerArquivoEImprimirResultado();

    }

    // OS BENDITOS DOS MÉTODOS!!!

    public static void LerArquivoEImprimirResultado() throws IOException {
        FileReader arquivo = new FileReader("packCentroGravitacional/entrada.txt");
        BufferedReader ler = new BufferedReader(arquivo);

        String entradas = ler.readLine();
        
        int linhas = Integer.parseInt(entradas.split(" ")[0]); //recebe a quantidade de linhas de acordo com o arquivo txt
        int colunas = Integer.parseInt(entradas.split(" ")[1]); //recebe a quantidade de colunas de acordo com o arquivo txt
         
        /*
         * Apesar de a Matriz estar perfeitamente bem representada aqui
         * é preciso que ela seja captuda do arquivo de texto
        */

        double[][] matriz = {
                                   {0.0, 0.0, 0.5, 0.0, 0.0},
                                   {0.5, 0.5, 0.5, 0.5, 0.5},
                                   {0.0, 0.0, 0.5, 0.0, 0.0},
                                   {0.0, 0.0, 0.5, 0.0, 0.0},
                                   {0.0, 0.0, 0.5, 0.0, 0.0}};

        ler.close();

        /*
         * Os Laços aqui geram arrays com os números das colunas e os números
         * e os números das linhas que vão ser ultilizadas no processo seguinte
         * como forma de nomear e identificar as linhas e colunas que serão
         * manipuladas.
         * 
         * Esse forma de fazer DEVE SER SUBSTITUIDA por alguma que não se
         * assemelhe tanto a uma GAMBIARRA. Isso provávelmente vai implicar uma
         * mudança na forma como o método CentroDeMassa é chamado e a forma como
         * ele funciona, mas não deve deixar o código mais complicado.
         * um exemplo de como vai ficar esse array para uma matriz 4 x 4:
         * matrizNumeroDasLinhas = [0, 1, 2, 3, 4]
         * matrizNumeroDasColunas
        */
        int[] matrizNumeroDasLinhas = new int[linhas];
        for (int i = 0; i < matrizNumeroDasLinhas.length; i++) {
            matrizNumeroDasLinhas[i] = i;
        }
    
        int[] matrizNumeroDasColunas = new int[colunas];
        for (int i = 0; i < matrizNumeroDasColunas.length; i++) {
            matrizNumeroDasColunas[i] = i;
        }

        int colunaCentro = CentroDeMassa(matriz, matrizNumeroDasLinhas, matrizNumeroDasColunas, "coluna");
        int linhaCentro = CentroDeMassa(matriz, matrizNumeroDasColunas, matrizNumeroDasLinhas, "linha");
        System.out.println("A Coluna central é a " + colunaCentro + "\nE a Linha Central é a " + linhaCentro);
    }

    public static int CentroDeMassa(double[][] matriz, int[] dimensao1, int[] dimensao2, String modo){

        /*
         * O método abaixo foi desenvolvido de forma a ser ultilizado para
         * encontrar o centro das linhas e também das colunas da matriz.
         * 
         * O laço com variável K diz repeito a coluna ou linha que está sendo 
         * analizada em comparação com as colunas a esqueda e direita ou as 
         * linhas acima ou abaixo. Ele inicia a iteração da coluna 1 (e não 
         * da coluna 0) e vai até a penultima coluna ou da linha 1 e vai 
         * até a penultima linha.
         * 
         * O Laço dimensao2 verifica quais são as somas dos valores de todas as 
         * linhas das colunas a esqueda e a direita da coluna que está sendo 
         * análizada, ou a soma dos valores das colunas das linhas acima e 
         * abaixo da linha que está sendo análisada.
         * 
         * o Laço for dentro da condicional if complementa a operação iterando as 
         * linhas da coluna ou colunas da linha.
         */

        double MenorDiferenca = 0;
        double diferenca = 0;
        int centro = 0;

        for (int k = 1; k < (dimensao2.length - 1); k++) {
            double soma1 = 0;
            double soma2 = 0;
            dimensao2: for (int i = 0; i < (dimensao2.length); i++) {
                if (i < k) {
                    for (int l = 0; l < dimensao1.length; l++) {
                        /* Isso aqui pode ser SUBSTITUIDO por uma função, pois se repete logo
                         * abaixo, mas com o termo soma 2
                         * a função pode retornar uma soma que será atribuida a variável 
                         * soma 1 ou soma 2
                        */
                        if (modo == "coluna") {
                            soma1 = soma1 + matriz[l][i];
                            }
                        else{
                            soma1 = soma1 + matriz[i][l];
                            }
                        }
                    } 
                else if (i == k) {
                    continue dimensao2;
                    }
                else {
                    for (int l = 0; l < dimensao1.length; l++) {
                        if (modo == "coluna") {
                            soma2 = soma2 + matriz[l][i];
                            }
                        else{
                            soma2 = soma2 + matriz[i][l];
                            }
                    
                    }
             }
            }
        /*
         * Para calcular a diferança o maior valor valor é comparado ao menor
         * então realizada a subtração do maior pelo menor
         */

        if (soma1 > soma2){
            diferenca = soma1 - soma2;
            }
        else{
            diferenca = soma2 - soma1;
            }
        /*
         * K == 1 garante que na primeira vez que o laço for passar por aqui um valor será agregado
         * para menorDiferenca para que possa ser porteriormente comparado.
         * a Comparação difrenca < MenorDiferenca garante que o menor centro de gravidade seja encontrado
         * e que apenas o primeiro a ser encontrado com esse menor valor seja considerado
         */
        if ((k == 1) || (diferenca < MenorDiferenca)) {
            MenorDiferenca = diferenca;
            centro = k;
            }
        }
        return centro;
    }
}