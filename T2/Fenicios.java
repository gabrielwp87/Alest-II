/*
 * Aluno: Gabriel Wagner Piazenski
 * Disciplina: Algoritmos e Estrutura de Dados 2
 * Professor: Edson Ifarraguirre Moreno
 * Data: 13/06/2023
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
// import java.util.Scanner;

public class Fenicios {

    public static int rows;
    public static int columns;
    public static char[][] graph;
    public static int[] rowElem;
    public static int[] columnElem;
    public static int totalCost;
    private static double runTime;
    private static long iniTime, finalTime;

    /**
     * Inicia variaveis para contar o tempo
     */
    private static void iniTime() {
        iniTime = finalTime = System.nanoTime();
    }

    /**
     * Calcula o tempo decorrido desde iniTime()
     */
    private static void stopTime() {
        finalTime = System.nanoTime();
        runTime = ((double) finalTime - (double) iniTime) / 1000000000;
    }

    /**
     * Chama o método de leitura de arquivo e cria o grafo a partir de um array
     * bidimensional
     * 
     * @param fileName
     * @throws IOException
     */
    public static void createGraph(String fileName) throws IOException {
        ReadFile reading = new ReadFile(fileName + ".map");
        ArrayList<String> file = reading.read();
        rowElem = new int[10];
        columnElem = new int[10];
        rows = 0;
        columns = 0;

        // guarda os values da primeira linha e atualiza o número de columns e rows
        String[] values = file.get(0).split(" ");
        rows = Integer.parseInt(values[0]);
        columns = Integer.parseInt(values[1]);

        // cria o array bidimensional de chars com o tamanho do graph
        graph = new char[rows][columns];

        System.out.println("rows: " + rows + "\n" + "columns: " + columns);

        for (int i = 0; i < rows; i++) {
            // separa os elementos da string recebida pela row
            String[] row = file.get(i + 1).split("(?<=.)");
            for (int j = 0; j < columns; j++) {
                // transforma em char
                graph[i][j] = row[j].charAt(0);
                // verifica se o char é um dígito
                if (Character.isDigit(graph[i][j])) {
                    // coloca as coordinates do dígito no index correnpondente ao próprio dígito
                    int number = Character.getNumericValue(graph[i][j]);
                    rowElem[number] = i;
                    columnElem[number] = j;
                }
            }
        }
    }

    /**
     * Retorna as coordenadas dos valores numéricos do grafo, que correspondem aos
     * portos que devem ser visitados
     * 
     * @param number número (porto) a ser visitado pelo barco
     * @return um array cuja primeira posição é a linha e a segunda é a coluna onde
     *         se encontra o número passado por parâmetro
     */
    public static int[] getCoordenadinate(int number) {
        int[] coordinates = new int[2];
        int li = rowElem[number];
        int col = columnElem[number];
        coordinates[0] = li;
        coordinates[1] = col;
        return coordinates;
    }

    /**
     * Encontra o melhor caminho para que o barco percorra o grafo com o menor custo
     * possível
     * 
     * @param elemStart elemento de inicio do caminhamento
     * @param elemEnd   elemento do fim do caminhamento
     * @return a distância percorrida
     */
    public static int BFS(int elemStart, int elemEnd) {
        int[] start = getCoordenadinate(elemStart);
        int[] end = getCoordenadinate(elemEnd);

        // cima, baixo, esquerda, direita
        int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[rows][columns];
        int[][] distances = new int[rows][columns];

        // começa a adicionar as coordenadas na fila
        queue.offer(start);
        visited[start[0]][start[1]] = true;
        distances[start[0]][start[1]] = 0;

        while (!queue.isEmpty()) {
            // pega o primeiro da fila e transforma no nodo atual
            int[] current = queue.poll();

            // se o atual for o destino, sai do loop
            if (current[0] == end[0] && current[1] == end[1]) {
                break;
            }

            // caminha em todas as direções possíveis
            for (int[] direction : directions) {
                int newRow = current[0] + direction[0];
                int newCol = current[1] + direction[1];

                // checa se é válido e se não foi visitado
                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < columns && !visited[newRow][newCol]
                        && graph[newRow][newCol] != '*') {
                    // marca como visitado
                    visited[newRow][newCol] = true;
                    // adiciona na fila
                    queue.offer(new int[] { newRow, newCol });
                    // calcula a distancia
                    distances[newRow][newCol] = distances[current[0]][current[1]] + 1;
                }
            }
        }

        // se o fim não foi visitado significa que é inacessível
        if (!visited[end[0]][end[1]]) {
            return 0;
        }

        // distancia total percorrida até o último elemento
        int cost = distances[end[0]][end[1]];
        return cost;
    }

    /**
     * Realiza a rota completa, ignorando os portos que não estão acessíveis
     */
    private static void doesRoute() {
        int origin = 1;
        int destination = 2;

        while (true) {

            int cost = BFS(origin, destination);

            if (cost > 0) {
                totalCost += cost;

                System.out.println("Caminho do porto " + origin + " ao porto " + destination
                        + " o que custou: " + cost);
                System.out.println();
                origin = destination;
                destination++;

                // completando a volta,
                if (destination == 2) {
                    break;
                }

            } else if (cost < 0) {
                System.out.println("Porto " + destination + " deu erro");
                System.out.println("cost = " + cost);
                System.out.println();
                destination++;
                if (destination == 2) {
                    break;
                }
            } else {

                System.out.println("Porto " + destination + " inacessivel");
                System.out.println();
                destination++;

            }

            if (destination > 9) {
                destination = 1;
            }
        }

    }

    public static void main(String[] args) throws IOException {

        // Scanner sc = new Scanner(System.in);
        // String caseName = sc.nextLine();
        // sc.close();

        iniTime();

        // createGraph(caseName);
        createGraph("case0");
        doesRoute();

        stopTime();

        System.out.println("total path size: " + totalCost);

        System.out.printf("Tempo de execucao: %.2fs\n\n", runTime);

    }
}