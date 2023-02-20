import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class task_power {
    public static void main(String[] args) throws Exception {
        Integer a = getNumberByUser("Введите число а: ");
        Integer b = getNumberByUser("Введите число b: ");
        System.out.println("Число " + a + " в степени " + b + " -> " + power(a, b));
        String pathInput = "input.txt";
        String pathOutput = "output.txt";
        String saveStr = String.format("a %d\nb %d", a, b);
        writeInFile(saveStr, pathInput);
        System.out.printf("\nВходные данные записаны в файл %s\n", pathInput);

        outputTerminal(pathInput);
        int[] argAB = readFile(pathInput, "a", "b");
        int newA = argAB[0];
        int newB = argAB[1];

        if (a ==0 && b == 0) {
            saveStr = "не определено";
        }
        else {
            Double intResult = power(newA, newB);
            saveStr = String.valueOf(intResult);
        }
        writeInFile(saveStr, pathOutput);
        System.out.printf("\nРезультат записан в файл %s\n", pathOutput);
        outputTerminal(pathOutput);
    }

    // запись в файл
    public static void writeInFile(String data, String path) {
        try (FileWriter wfile = new FileWriter(path, false)) {
            wfile.write(data);
            wfile.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Чтение цифр из файла
    public static int[] readFile(String fileName, String a, String b) throws Exception {
        Scanner scan = new Scanner(new File(fileName));
        int[] intArr = new int[2];
        int index = 0;
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] strArr = line.split(" ");
            if (a.equalsIgnoreCase(strArr[0])) {
                intArr[0] = Integer.parseInt(strArr[1]);
            } else if (b.equalsIgnoreCase(strArr[0])) {
                intArr[1] = Integer.parseInt(strArr[1]);
            }
            index++;
        }
        scan.close();
        return intArr;
    }

    public static int getNumberByUser(String text) {
        int i;
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print(text);
            if (sc.hasNextInt()) {
                i = sc.nextInt();
                break;
            } else {
                System.out.println("Вы ввели не целое число");
            }
        }
        return i;
    }
  
    public static double power(int value, int powValue) {
        double result = 1;
        if (powValue > 0) {
            for (int i = 1; i <= powValue; i++) {
                result *= value;
            }
            return result;
        } else if (powValue < 0) {
            for (int i = -1; i > powValue; i--) {
                result *= value;
            }
            result = 1 / (result * value);
            return result;
        } else {
            return 1;
        }
    }
  
    public static void outputTerminal(String text) throws Exception {
        File f = new File(text);
        Scanner scan = new Scanner(f);
        while (scan.hasNextLine()) {
            String msg = scan.nextLine();
            System.out.println(msg);
        }
    }
}
