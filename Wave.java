import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class waveLi {

    public static void main(String[] args) {
        int[][] labirint = getLabirint(10, 15, 40);
        printArr2D(labirint);
        System.out.println();
        int[] start = {6, 9};
        wave(labirint, start);
        printArr2D(labirint);
        int[] exit = {0, 5};
        System.out.println();
        System.out.println(Arrays.deepToString(getExitWay(labirint, exit)));
    }

    public static int[][] getLabirint(int x, int y, int wall) {
        int[][] lab = new int[x][y];
        int[] wallArr = new int[wall * 2];
        Random rand = new Random();
        for (int i = 0; i < wallArr.length; i += 2) {
            int wallX = rand.nextInt(x);
            int wallY = rand.nextInt(y);
            // проверяем, что на данной позиции нет уже стены
            if (lab[wallX][wallY] != -1) {
                lab[wallX][wallY] = -1;
                wallArr[i] = wallX;
                wallArr[i+1] = wallY;
            } else {
                i -= 2; // повторяем итерацию, чтобы сгенерировать новые координаты
            }
        }
        return lab;
    }

    public static void printArr2D(int[][] arr) {
        for (int[] item : arr) {
            for (int temp : item) {
                System.out.printf("%3d ", temp);
            }
            System.out.println();
        }
    }
   
    public static void wave(int[][] matrix, int[] start) {
        int count = 1;
        Queue<int[]> way = new LinkedList<>();
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        way.add(start);
        visited[start[0]][start[1]] = true;
        matrix[start[0]][start[1]] = count;
        while (!way.isEmpty()) {
            int size = way.size();
            for (int i = 0; i < size; i++) {
                int[] step = way.remove();
                count = matrix[step[0]][step[1]];
                int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
                for (int[] dir : directions) {
                    int row = step[0] + dir[0];
                    int col = step[1] + dir[1];
    
                    if (row >= 0 && row < matrix.length && 
                        col >= 0 && col < matrix[0].length &&
                        !visited[row][col] && matrix[row][col] == 0) {
                        int[] temp = {row, col};
                        way.add(temp);
                        visited[row][col] = true;
                        matrix[row][col] = count + 1;
                    }
                }
            }
        }
    }
    
    public static int[][] getExitWay(int[][] matrix, int[] exit) {
        int count = matrix[exit[0]][exit[1]];
        if (count < 1) {
            System.out.println("Выхода нет!! Тефтелька умрет от голода((");
            return new int[0][0];
        } else {
            int[][] exitWay = new int[count][2];
            exitWay[0] = exit;
            int[] step = Arrays.copyOf(exit, 2);
            for (int i = 1; i < count; i++) {
                if (step[0] != 0 && matrix[step[0] - 1][step[1]] == count - i) {
                    step[0] -= 1;
                    exitWay[i] = Arrays.copyOf(step, 2);
                } else if (step[1] != matrix[0].length - 1 && matrix[step[0]][step[1] + 1] == count - i) {
                    step[1] += 1;
                    exitWay[i] = Arrays.copyOf(step, 2);
                } else if (step[0] != matrix.length - 1 && matrix[step[0] + 1][step[1]] == count - i) {
                    step[0] += 1;
                    exitWay[i] = Arrays.copyOf(step, 2);
                } else if (step[1] != 0 && matrix[step[0]][step[1] - 1] == count - i) {
                    step[1] -= 1;
                    exitWay[i] = Arrays.copyOf(step, 2);
                }
            }
            return exitWay;
        }
    }
}
