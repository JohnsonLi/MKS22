import java.util.*;

public class Calculator{

    public static Double eval(String operations){
        class Stack<E> extends LinkedList<E>{
            public void push(E data){
                add(data);
            }
    
            public E pop(){
                return remove(size() - 1);
            }
        }

        Stack<Double> stack = new Stack<>();
       
        String[] operationsArr = operations.split(" ");
        // for(String value : operationsArr){
        //     stack.push(value);   
        // }
        // System.out.println(stack);

        for(int i = 0; i < operationsArr.length; i++){
            String currentThing = operationsArr[i];
            // System.out.println(currentThing);

            if(!(currentThing.equals("+") || currentThing.equals("*") || currentThing.equals("/") || currentThing.equals("-") || currentThing.equals("%"))){
                stack.push(Double.parseDouble(currentThing));
            } else {
                switch(currentThing){
                    case "+":
                        stack.push(stack.pop() + stack.pop());
                        break;
                    case "*":
                        stack.push(stack.pop() * stack.pop());
                        break;
                    case "/":
                        stack.push(1 / stack.pop() * stack.pop());
                        break;
                    case "-":
                        stack.push( - stack.pop() + stack.pop());
                        break;
                    case "%":
                        Double val1 = stack.pop();
                        Double val2 = stack.pop();
                        stack.push(val2 % val1);
                        break;
                }
            }
        }

        if(stack.size() == 0){
            return 0.0;
        }
        return stack.pop();
    }

    // public static void main(String[] args) {
    //    System.out.println(eval("10 2.0 +"));
    //    System.out.println(eval("11 3 - 4 + 2.5 *"));
    //    System.out.println(eval("8 2 + 99 9 - * 2 + 9 -"));
    //    System.out.println(eval("8 7 %"));
    // }
    
}