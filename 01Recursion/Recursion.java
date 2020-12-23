public class Recursion{

    //1.1a
    public int fact(int n){
        if (n < 0){
            throw new IllegalArgumentException();
        }
        if (n == 0){
            return 1;
        }
        return n * fact(n - 1);
    }
 
    //1.1b
    public int fib(int n){
        if (n < 0){
            throw new IllegalArgumentException();
        }
        return fibHelp(1, 0, n);
    }

    public int fibHelp(int startNum, int sumSoFar, int numTimes){
        if (numTimes == 0){
            return sumSoFar;
        }
        return fibHelp(startNum + sumSoFar, startNum, numTimes - 1);
    }

    //1.1c
    public double sqrt(double n){
        if (n < 0){
            throw new IllegalArgumentException();
        }
        if (n == 0){
            return 0;
        }
        return guess(n , n / 2.);
    }

    public double guess(double n, double guess){
        double currentGuess = (n / guess + guess) / 2;
        if (goodGuess(n, currentGuess)){
            return guess;
        } else {
            return guess(n, currentGuess);
        }

    }

    public boolean goodGuess(double n, double m){
        double okGuess = 0.000000000000001;
        if ((Math.abs((m * m)  - n) / n) < okGuess){
            return true;
        }
        return false;
    }


    /*public static void main(String[] args) {
        Recursion recurse = new Recursion();
        System.out.println(recurse.fact(10));
        System.out.println(recurse.fib(5));
        System.out.println(recurse.sqrt(0));
    }*/

}
