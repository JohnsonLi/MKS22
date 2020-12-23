import java.util.*;

public class Sorts{

    public static void heapSort(Integer[] data){
        MyHeap<Integer> n = new MyHeap<>();

        for(int i = 0; i < data.length; i++){
            n.add(data[i]);
        }
        while(n.size() != 0){
            data[n.size() - 1] = n.remove();
        }
    }

    // public static void main(String[] args) {
    //     Integer[]  aa = {1,4,5,63,43,2,3,4,346,-34,324, -355};
    //     heapSort(aa);
    //     System.out.println(Arrays.toString(aa));
    // }
}