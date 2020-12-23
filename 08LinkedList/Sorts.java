public class Sort{

    public static void radixSort(MyLinkedListImproved<Integer> list){
        if(list.size() == 0){
            return;
        }

        int maxDigitsPos = (int)(Math.log10(list.get(list.max())) + 1);
        int maxDigitsNeg = (int)(Math.log10(Math.abs(list.get(list.min()))) + 1);
        int numDigits = maxDigitsPos > maxDigitsNeg ? maxDigitsPos : maxDigitsNeg;
        int currentDigit = 0;

        while(numDigits > 0){
            @SuppressWarnings("unchecked") MyLinkedListImproved<Integer>[] cup = new MyLinkedListImproved[10];
            for(int i = 0; i < 10; i++){
                cup[i] = new MyLinkedListImproved<Integer>(); 
            }

            @SuppressWarnings("unchecked") MyLinkedListImproved<Integer>[] cupNeg = new MyLinkedListImproved[10];
            for(int i = 0; i < 10; i++){
                cupNeg[i] = new MyLinkedListImproved<Integer>(); 
            }

            for(Integer value : list){
                if(value >= 0){
                    cup[(int)(Math.abs(value) / Math.pow(10, currentDigit) % 10)].add(value);
                }   
            }

            for(Integer value : list){
                if(value < 0){
                    cupNeg[9 - ((int)(Math.abs(value) / Math.pow(10, currentDigit) % 10))].add(value);
                }   
            }

            list.clear();
            for(int i = 0; i < cupNeg.length; i++){
                list.extend(cupNeg[i]);
            }
            for(int i = 0; i < cup.length; i++){
                list.extend(cup[i]);
            }

            currentDigit++;
            numDigits--;
        }
    }
    
    public static void radixsortIncludingNegatives(MyLinkedListImproved<Integer> data){ 
        radixSort(data);
    }
    
    // public static void main(String[] args) {
    //     MyLinkedListImproved<Integer> list = new MyLinkedListImproved<>();
    //     list.add(-2391);
    //     list.add(11231);
    //     list.add(-2);
    //     list.add(231);
    //     list.add(1213);
    //     list.add(-23423331);
    //     list.add(-234233321);
    //     list.add(-234234331);
    //     list.add(154);
    //     list.add(1890);
    //     list.add(-1000000000);
    //     System.out.println(list);
    //     Sort.radixSort(list);
    //     System.out.println(list);
    //     // int a = 21393;
    //     // System.out.println(Math.floor(a / Math.pow(10, 5) % 10));
    //     // System.out.println(Integer.toString(a).length());
    // }
}