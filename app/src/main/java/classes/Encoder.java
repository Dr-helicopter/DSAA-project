package classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Encoder {

    public static String encodePrefix(String infixExpression) {
        Queue<String> outputQueue = new Queue<>();
        Stack<String> operatorStack = new Stack<>();

        String[] tokens = infixExpression.split("(?<=[-+*/^()])|(?=[-+*/^()])|\\s+");

        for (String token : tokens) {
            token = token.trim();
            if (token.isEmpty()) {
                continue;
            }

            if (token.matches("-?\\d+(\\.\\d+)?")) {  // if it's a number
                outputQueue.add(token);
            } else if (token.equals("(")) {
                operatorStack.push(token);
            } else if (token.equals(")")) {
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")) {
                    outputQueue.add(operatorStack.pop());
                }
                operatorStack.pop(); // remove '('
            } else {  // if it's an operator
                while (!operatorStack.isEmpty() && precedence(operatorStack.peek()) >= precedence(token)) {
                    outputQueue.add(operatorStack.pop());
                }
                operatorStack.push(token);
            }
        }

        while (!operatorStack.isEmpty()) {
            outputQueue.add(operatorStack.pop());
        }

        List<String> result = new ArrayList<>();
        while (!outputQueue.isEmpty()) {
            result.add(outputQueue.remove());
        }
        Collections.reverse(result); // Reverse for prefix notation
        return String.join(" ", result);
    }

    public static String encodePostfix(String infixExpression) {
        Queue<String> outputQueue = new Queue<>();
        Stack<String> operatorStack = new Stack<>();

        String[] tokens = infixExpression.split("(?<=[-+*/^()])|(?=[-+*/^()])|\\s+");

        for (String token : tokens) {
            token = token.trim();
            if (token.isEmpty()) {
                continue;
            }

            if (token.matches("-?\\d+(\\.\\d+)?")) {  // if it's a number
                outputQueue.add(token);
            } else if (token.equals("(")) {
                operatorStack.push(token);
            } else if (token.equals(")")) {
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")) {
                    outputQueue.add(operatorStack.pop());
                }
                operatorStack.pop(); // remove '('
            } else {  // if it's an operator
                while (!operatorStack.isEmpty() && precedence(operatorStack.peek()) >= precedence(token)) {
                    outputQueue.add(operatorStack.pop());
                }
                operatorStack.push(token);
            }
        }

        while (!operatorStack.isEmpty()) {
            outputQueue.add(operatorStack.pop());
        }

        StringBuilder result = new StringBuilder();
        while (!outputQueue.isEmpty()) {
            result.append(outputQueue.remove()).append(" ");
        }

        return result.toString().trim();
    }

    public static String encodeInfix(String infixExpression) {
        Stack<String> values = new Stack<>();
        Stack<String> operators = new Stack<>();

        String[] tokens = infixExpression.split("(?<=[-+*/^()])|(?=[-+*/^()])|\\s+");

        for (String token : tokens) {
            token = token.trim();
            if (token.isEmpty()) {
                continue;
            }

            if (token.matches("-?\\d+(\\.\\d+)?")) {  // if it's a number
                values.push(token);
            } else if (token.equals("(")) {
                operators.push(token);
            } else if (token.equals(")")) {
                while (!operators.isEmpty() && !operators.peek().equals("(")) {
                    String b = values.pop();
                    String a = values.pop();
                    String op = operators.pop();
                    values.push("(" + a + " " + op + " " + b + ")");
                }
                operators.pop(); // remove '('
            } else {  // if it's an operator
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(token)) {
                    String b = values.pop();
                    String a = values.pop();
                    String op = operators.pop();
                    values.push("(" + a + " " + op + " " + b + ")");
                }
                operators.push(token);
            }
        }

        while (!operators.isEmpty()) {
            String b = values.pop();
            String a = values.pop();
            String op = operators.pop();
            values.push("(" + a + " " + op + " " + b + ")");
        }

        return values.pop();
    }

    private static int precedence(String operator) {
        switch (operator) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            case "**":
            case "^":
                return 3;
            default:
                return 0;
        }
    }
}

