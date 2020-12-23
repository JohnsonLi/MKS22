import java.util.*;

public class Merge{

    // public static void mergeOld(int[] ary, int left, int right){
    //     int[] temp = new int[ary.length];

    //     int mid = (left + right) / 2 + 1;
    //     int leftStart = left, start = left;
       
    //     while(leftStart < (left + right) / 2 + 1 && mid <= right){
    //         if(ary[leftStart] <= ary[mid]){
    //             temp[start] = ary[leftStart];
    //             leftStart++;
    //         } else {
    //             temp[start] = ary[mid];
    //             mid++;   
    //         }
    //         start++;
    //     }

    //     while(leftStart < (left + right) / 2 + 1) {
    //         temp[start] = ary[leftStart];
    //         leftStart++;
    //         start++;
    //     }
    //     while(mid <= right){
    //         temp[start] = ary[mid];
    //         mid++;
    //         start++;
    //     }

    //     for(int i = left; i <= right; i++){
    //         ary[i] = temp[i];
    //     }

    // }

    // public static void mergesortOld(int[] ary){
    //     mergesortH(ary, 0, ary.length - 1);
    // }

    // public static void mergesortHOld(int[] ary, int start, int end){
    //     // if(start >= end){
    //     //     return;
    //     // }
    //     int mid = (start + end) / 2;

    //     if(end > start){
    //         mergesortH(ary, start, mid);
    //         mergesortH(ary, mid + 1, end);
    //         merge(ary, start, end);
    //     }
    // }

    private static void insertionSort(int[] ary, int lo, int hi){
        for (int i = lo + 1; i <= hi - 1; i++){
            int j = i;
            while(j > 0 && ary[j] < ary[j - 1]){
                int temp = ary[j];
                ary[j] = ary[j - 1];
                ary[j - 1] = temp;
                j--;
            }
        }
    }

    public static void merge(int[] ary, int[] temp, int left, int right){

        int mid = (left + right) / 2 + 1;
        int leftStart = left, start = left;
       
        while(leftStart < (left + right) / 2 + 1 && mid <= right){
            if(ary[leftStart] <= ary[mid]){
                temp[start] = ary[leftStart];
                leftStart++;
            } else {
                temp[start] = ary[mid];
                mid++;   
            }
            start++;
        }

        while(leftStart < (left + right) / 2 + 1) {
            temp[start] = ary[leftStart];
            leftStart++;
            start++;
        }
        while(mid <= right){
            temp[start] = ary[mid];
            mid++;
            start++;
        }

        for(int i = left; i <= right; i++){
            ary[i] = temp[i];
        }

    }

    public static void mergesort(int[] ary){
        int[] temp = new int[ary.length];
        //why does this fix it
	    for(int i = 0; i < ary.length ; i ++){
	        temp[i] = ary[i];
        }
        mergesortH(ary, temp, 0, ary.length - 1);
    }

    public static void mergesortH(int[] ary, int[] temp, int start, int end){
        if(ary.length < 20){
            insertionSort(ary, 0, ary.length);
            return;
        }

        for(int i = start; i < end; i++){
            temp[i] = ary[i];
         }

        int mid = (start + end ) / 2;
        
        if(end > start){
            mergesortH(temp, ary, start, mid);
            mergesortH(temp, ary, mid + 1, end);
            merge(ary, temp, start, end);
        }
    }

    // public static void main(String[] args) {
    //     int[] ary = //{123,21313,21331,44,5467,5877588,123,123123,5,64545,67};
    //     //{213,3123123,124,54,3456,7,58,57,46363625,2,235,55,646,67};
    //     //{132111,111};
    //     //{12,-23213,-434,34,34,34,34,34,213123,21323,2323,-123,123,123,444444};
    //     //{2,1};
    //     //{-22,-1111};
    //     //{1};
    //     {1,6,7,2,6,6};

    //     System.out.println(Arrays.toString(ary));
    //     Merge.mergesort(ary);
    //     System.out.println(Arrays.toString(ary));
    // }

    // public static void main(String[] args) {
    //     int[] ary = {24234,234242,43434,1215,6,7,89,3};
    //     int[] ary1 = {-2,-5235,234,67,13,123123,23123,12313};
    //     System.out.println(Arrays.toString(ary));
    //     Merge.insertionSort(ary, 0, ary.length);
    //     System.out.println(Arrays.toString(ary));
    // }
}