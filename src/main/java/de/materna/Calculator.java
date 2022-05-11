package de.materna;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.HashMap;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        startCalculator();
    }

    static void startCalculator() {

        HashMap<String, Double> tempStorage= new HashMap<>();

        while (true) {
            
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            if (input.contains("exit")) {
                break;
            }



            if (input.contains("=")) {
                int postion = input.indexOf("=");
                input = input.substring(postion+1);
                Expression expression = new ExpressionBuilder(input)
                        .build();

                double value = expression.evaluate();
                tempStorage.put(input.substring(0, postion),value);
                System.out.println(value);
                continue;

            }

            Expression expression = new ExpressionBuilder(input)
                    .variables(tempStorage.keySet())
                    .build()
                    .setVariables(tempStorage);

            System.out.println(expression.evaluate());

        }
    }
}
