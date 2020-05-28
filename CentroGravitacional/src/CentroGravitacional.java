import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/*         INTEGRANTES
 * Paulo Henrique Nogueira Costa
 * Pedro Rafael Santos Gomes
 * Sillas Cavalcante Lopes Pinto 
 */

public class CentroGravitacional {

	public static void main(String[] args) throws IOException {
		FileReader arquivo = new FileReader("entrada.txt");
		double matriz[][] = lerMatrizDoArquivo(arquivo);

		mostrarCentroGravitacional(matriz);
	}

	/*
	 * O método abaixo foi desenvolvido de forma a ser ultilizado para encontrar o
	 * centro das linhas e das colunas da matriz.
	 * 
	 * O laço com variável K diz repeito à coluna ou linha que está sendo analisada
	 * em comparação com as colunas à esquerda e à direita, ou as linhas acima ou
	 * abaixo. Ele inicia a iteração da coluna 1 (e não da coluna 0) e vai até a
	 * penúltima coluna ou linha 1, e assim percorre até a penúltima linha.
	 * 
	 * O laço verificarSomas verifica quais são as somas dos valores de todas as
	 * linhas das colunas à esquerda e à direita da coluna que está sendo
	 * percorrida, ou a soma dos valores das colunas das linhas acima e abaixo da
	 * linha que está sendo percorrida.
	 * 
	 */
	public static int centroGravitacional(double[][] matriz, int dimensao1, int dimensao2, String modo) {
		double menorDiferenca = 0, diferenca = 0;
		int centro = 0;

		for (int k = 1; k < dimensao2 - 1; k++) {
			double soma1 = 0, soma2 = 0;

			verificarSomas: for (int i = 0; i < dimensao2; i++) {
				if (i < k) {
					for (int l = 0; l < dimensao1; l++) {
						soma1 += (modo == "coluna") ? matriz[l][i] : matriz[i][l];
					}
				} else if (i == k) {
					continue verificarSomas;
				} else {
					for (int l = 0; l < dimensao1; l++) {
						soma2 += (modo == "coluna") ? matriz[l][i] : matriz[i][l];
					}
				}
			}

			diferenca = (soma1 > soma2) ? soma1 - soma2 : soma2 - soma1;

			/*
			 * K == 1 garante que na primeira vez que o laço for passar por aqui um valor
			 * será atribuido para menorDiferenca, sendo esse valor posteriormente
			 * comparado. A comparação diferenca < menorDiferenca garante que o menor centro
			 * de gravidade seja encontrado e que apenas o primeiro a ser encontrado com
			 * esse menor valor seja o valor considerado.
			 */

			if ((k == 1) || (diferenca < menorDiferenca)) {
				menorDiferenca = diferenca;
				centro = k;
			}
		}
		return centro;
	}

	public static void mostrarCentroGravitacional(double[][] matriz) {
		int numeroLinhas = matriz.length;
		int numeroColunas = matriz[0].length;

		int linhaCentro = centroGravitacional(matriz, numeroColunas, numeroLinhas, "linha");
		int colunaCentro = centroGravitacional(matriz, numeroLinhas, numeroColunas, "coluna");

		System.out.printf("Centro: (%d, %d)", linhaCentro + 1, colunaCentro + 1);
	}

	public static double[][] lerMatrizDoArquivo(FileReader arquivo) throws IOException {
		BufferedReader leitor = new BufferedReader(arquivo);
		Scanner sc = new Scanner(leitor);

		String entrada = leitor.readLine();
		// Split correspondente aos valores da primeira linha - matriz [MxN]
		int linhas = Integer.parseInt(entrada.trim().split(" ")[0]);
		int colunas = Integer.parseInt(entrada.trim().split(" ")[1]);
		double[][] matriz = new double[linhas][colunas];

		while (sc.hasNextLine()) { // Percorre linhas subsequentes do arquivo de texto
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
}