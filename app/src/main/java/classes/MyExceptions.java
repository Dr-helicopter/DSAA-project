package classes;

public class MyExceptions {
    //checked exception!
     public static class stackUnderFlow extends Exception{
        public stackUnderFlow(){}
        public stackUnderFlow(String message){
            super(message);
        }
    }
    //checked exception!
    public static class emptyQueueException extends Exception{
        public emptyQueueException(){}
        public emptyQueueException(String message){
            super(message);
        }
    }
    //unchecked exception! due to user entry dependency
    public static class invalidExpressionException extends RuntimeException{
        public invalidExpressionException(){}
        public invalidExpressionException(String message){
             super(message);
        }
    }
    
}
