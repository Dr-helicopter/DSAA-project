package classes;

import java.util.Arrays;

public class Decoder{
    private static final String[] operators ={
            "+", "-", "*", "×", "/", "÷", "**", "^",
    };

    public static double decodePrefix(String expression) {
        expression = expression.replaceAll("[()]", "").trim();

        String[] tokens = expression.split("\\s+");
        Stack<Double> stack = new Stack<>();

        for (int i = tokens.length - 1; i >= 0; i--) {
            String token = tokens[i];

            if (token.matches("-?\\d+(\\.\\d+)?")) {  // if it's a number
                stack.push(Double.parseDouble(token));
            } else if (Arrays.asList(Decoder.operators).contains(token)){  // if it's an operator
                double a = stack.pop();
                double b = stack.pop();

                double result;
                switch (token){
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
                    default :
                        result = 0;
                }
                stack.push(result);
            }else throw new IllegalArgumentException("bad argument");
        }

        if (stack.size() != 1)
            throw new IllegalArgumentException("Invalid expression: The number of operands and operators do not match.");


        return stack.pop();
    }
}