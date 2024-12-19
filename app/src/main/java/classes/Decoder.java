package classes;

public class Decoder {
    public static String decodePrefix(String expression) {
        Stack<String> stack = new Stack<>();
        String[] tokens = expression.split(" ");

        try {
            for (int i = tokens.length - 1; i >= 0; i--) {
                String token = tokens[i];

                if (token.matches("-?\\d+")) { // If the token is a number
                    stack.push(token);
                } else if ("+-*/×÷".contains(token)) { // If the token is an operator
                    if (stack.size() < 2) {
                        return "Syntax error";
                    }
                    int operand1 = Integer.parseInt(stack.pop());
                    int operand2 = Integer.parseInt(stack.pop());

                    switch (token) {
                        case "+":
                            stack.push(String.valueOf(operand1 + operand2));
                            break;
                        case "-":
                            stack.push(String.valueOf(operand1 - operand2));
                            break;
                        case "*":
                        case "×":
                            stack.push(String.valueOf(operand1 * operand2));
                            break;
                        case "/":
                        case "÷":
                            if (operand2 == 0) {
                                return "Syntax error";
                            }
                            stack.push(String.valueOf(operand1 / operand2));
                            break;
                    }
                } else {
                    return "Syntax error"; // Invalid token
                }
            }

            if (stack.size() != 1) {
                return "Syntax error";
            }

            return stack.pop();
        } catch (Exception e) {
            return "Syntax error";
        }
    }
}
