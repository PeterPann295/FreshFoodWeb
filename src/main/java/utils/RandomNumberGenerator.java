package utils;

import java.util.Random;

public class RandomNumberGenerator {

    public static String generateRandomNumbersString() {
        Random random = new Random();
        StringBuilder randomNumbersString = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            int randomNumber = random.nextInt(10);
            randomNumbersString.append(randomNumber);
        }
        return randomNumbersString.toString(
);
    }

    public static void main(String[] args) {
        int count = 6;
        String randomNumbersString = generateRandomNumbersString();

        System.out.println("Generated Random Numbers:");
        System.out.println(randomNumbersString);
    }
}

