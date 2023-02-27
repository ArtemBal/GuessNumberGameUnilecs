package balan.artem;

import java.util.Random;
import java.util.Scanner;

public class GuessNumber {

    public static int getRandomInt() {
        Random random = new Random();
        return random.nextInt(899) + 100;
    }

    public static int[] intToArray(int k) {
        String temp = Integer.toString(k);
        int[] intArray = new int[3];
        for(int i = 0; i < 3; i++){
            intArray[i] = Character.getNumericValue(temp.charAt(i));
        }
        return intArray;
    }

    public static int[] check(int[] rand, int[] input) {
        int[] result = new int[]{0, 0};
        boolean flag;
        for (int i = 0; i < rand.length; i++){
            flag = false;
            for (int j = 0; j < input.length; j++) {
                if(rand[i] == input[j]) {
                    if(i == j) {
                        result[0]++;
                        if(flag) result[1]--;
                        break;
                    }
                    if(flag) {
                        continue;
                    }
                    result[1]++;
                    flag = true;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int randomInt = getRandomInt();
        int[] randArray = intToArray(randomInt);
        System.out.println("""
                Угадайте трехзначное число (цифры могут поыторятся)
                        Программа дает следующие подсказки:
                                  
                                  Если цифра присутствует в загаданном числе, и она стоит на своем месте, то программа выдаст значение "Right"
                                  Если цифра присутствует в загаданном числе, но она стоит НЕ на своем месте, то программа выдаст значение "Almost"
                                  Если нет ни одной цифры в загаданном числе, то программа выдаст значение "No".
                У вас 10 попыток""");
        Scanner scanner = new Scanner(System.in);
        int[] inputArray;
        int[] result;
        System.out.println("-------------------" + randomInt + "-------------------");
        for (int i = 0; i < 10; i++){
            System.out.println(i + " попытка:");
            int in = scanner.nextInt();
            if(in > 999 || in <= 99) {
                System.out.println("Ты тупой? Сказали же введите ТРЕХЗНАЧНОЕ число");
                i--;
                continue;
            }
            inputArray = intToArray(in);
            result = check(randArray, inputArray);
            if(result[0] == 3) {
                System.out.println("Правильно!");
                break;
            }
            System.out.println(result[0] + "-Right | " + result[1] + "-Almost");
        }

    }
}
