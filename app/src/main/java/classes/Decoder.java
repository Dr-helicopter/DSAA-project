package classes;

import java.util.Arrays;

public class Decoder{
    private static final String[] operators ={
            "+", "-", "*", "×", "/", "÷", "**", "^",
    };

    public static double decodePrefix(String expression) throws Exception{
        expression = expression.replaceAll("[(){}]", "").trim();

        String[] tokens = expression.split("\\s+");
        Stack<Double> stack = new Stack<>();

        for (int i = tokens.length - 1; i >= 0; i--) {
            String token = tokens[i];

            if (token.matches("-?\\d+(\\.\\d+)?")) {  // if it's a number
                stack.push(Double.parseDouble(token));
            } else if (Arrays.asList(Decoder.operators).contains(token)) {  // if it's an operator
                double a = stack.pop();
                double b = stack.pop();

                double result;
                switch (token) {
                    case "+":
                        result = a + b;
                        break;
                    case "-":
                        result = a - b;
                        break;
                    case "*":
                    case "×":
                        result = a * b;
                        break;
                    case "/":
                    case "÷":
                        result = a / b;
                        break;
                    case "**":
                    case "^":
                        result = Math.pow(a, b);
                        break;
                    default:
                        result = 0;
                }
                stack.push(result);
            } else throw new MyExceptions.invalidExpressionException("Invalid Entry!");
        }

        if (stack.size() != 1)
            throw new MyExceptions.invalidExpressionException("Invalid expression: " +
                    "The number of operands and operators do not match.");


        return stack.pop();
    }

    public static double decodeInfix(String expression) throws Exception{
        expression = expression.trim();//delete starting and ending white-spaces
        Stack<Double> values = new Stack<>();
        Stack<String> operators = new Stack<>();

        String[] tokens = expression.split("(?<=[-+*/^(){}])|(?=[-+*/^(){}])|\\s+");//first any (0 or 1) before character then one after and white-spaces

        for (String token : tokens) {
            token = token.trim();
            if (token.isEmpty()) {
                continue;
            }

            if (token.matches("-?\\d+(\\.\\d+)?")) {  // if it's a number
                values.push(Double.parseDouble(token));
            } else if (token.equals("(") || token.equals("{")) {
                operators.push(token);
            } else if (token.equals(")") || token.equals("}")) {
                while (!operators.isEmpty() && !operators.top().equals("(") && !operators.top().equals("{")) {
                    values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
                }
                operators.pop(); // remove '(' or '{'
            } else if (Arrays.asList(Decoder.operators).contains(token)) {  // if it's an operator
                while (!operators.isEmpty() && precedence(operators.top()) >= precedence(token)) {
                    values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
                }
                operators.push(token);
            } else {
                throw new MyExceptions.invalidExpressionException("Invalid token: " + token);
            }
        }

        while (!operators.isEmpty()) {
            values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
        }

        if (values.size() != 1) {
            throw new MyExceptions.invalidExpressionException("Invalid expression: " +
                    "The number of operands and operators do not match.");
        }

        return values.pop();
    }

    private static int precedence(String operator) {
        switch (operator) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "×":
            case "/":
            case "÷":
                return 2;
            case "**":
            case "^":
                return 3;
            default:
                return 0;
        }
    }

    private static double applyOperation(String operator, double b, double a) {
        switch (operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
            case "×":
                return a * b;
            case "/":
            case "÷":
                if (b == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return a / b;
            case "**":
            case "^":
                return Math.pow(a, b);
            default:
                throw new MyExceptions.invalidExpressionException("Invalid operator: " + operator);
        }
    }

    public static double decodePostfix(String expression) throws Exception {
        expression = expression.replaceAll("[(){}]", "").trim();

        String[] tokens = expression.split("\\s+");
        Stack<Double> stack = new Stack<>();

        for (String token : tokens) {
            if (token.matches("-?\\d+(\\.\\d+)?")) {  // if it's a number
                stack.push(Double.parseDouble(token));
            } else if (Arrays.asList(Decoder.operators).contains(token)) {  // if it's an operator
                double b = stack.pop();
                double a = stack.pop();

                double result;
                switch (token) {
                    case "+":
                        result = a + b;
                        break;
                    case "-":
                        result = a - b;
                        break;
                    case "*":
                    case"×":
                        result = a * b;
                        break;
                    case "/":
                    case "÷":
                        result = a / b;
                        break;
                    case "**":
                    case "^":
                        result = Math.pow(a, b);
                        break;
                    default:
                        result = 0;
                }
                stack.push(result);
            } else throw new MyExceptions.invalidExpressionException("Invalid Entry!");
        }

        if (stack.size() != 1)
            throw new MyExceptions.invalidExpressionException("Invalid expression:" +
                    " The number of operands and operators do not match.");

        return stack.pop();
    }

}