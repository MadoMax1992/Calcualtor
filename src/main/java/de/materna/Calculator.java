package de.materna;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.HashMap;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        startCalculator(getInput());
    }

    static String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    static String startCalculator(String input) {

        HashMap<String, Double> tempStorage= new HashMap<>();

        while (true) {
            
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

            return Double.toString(expression.evaluate());


        }
        return "";
    }
}
