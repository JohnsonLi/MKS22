import java.util.*;

public class Quick{

    private static void swap(int[] data, int a, int b){
        int temp = data[a];
        data[a] = data[b];
        data[b] = temp;
    }

    // public static int partitionOld(int[] data, int start, int end){
    //     int pivInd = (int)(Math.random() * (end - start)) + start;
    //     //System.out.println(pivInd);
        
    //     swap(data, pivInd, start);
    //     int i = start + 1;
    //     int j = end;

    //     while(i <= j){
    //         if(data[i] > data[start]){
    //             swap(data, i, j);
    //             j--;
    //         } else {
    //             i++;
    //         }
    //     }
    //     swap(data, j, start);
    //     return j;
    // }

    // public static int quickselectOld(int[] data, int k){
    //     int start = 0;
    //     int end = data.length - 1;
    //     // I have no idea why index has to be set at data.length - 1; other values make it go forever :\\\\\
    //     int index = data.length - 1;

    //     k -= 1;
    //     while(index != k){
    //         if(index > k){
    //             end = index;
    //             index = partition(data, start, end);
    //         } else {
    //             start = index;
    //             index = partition(data, start, end);
    //         }
    //     }
    //     return data[index];
    // }

    // public static void quicksortOld(int[] ary){
    //     quickHOld(ary, 0, ary.length - 1);
    // }

    // public static void quickHOld(int[] ary, int start, int end){
    //     if(end >= start){
    //         int index = partition(ary, start, end);
    //         quickH(ary, start, index - 1);
    //         quickH(ary, index + 1, end);
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

    private static int[] partition(int[] ary, int lo, int hi){
        int lt = lo, gt = hi - 1;
        int i = lo;

        int pivI = (int)(Math.random() * (hi - lo)) + lo;
        int pivNum = ary[pivI];
        //System.out.println(pivNum);

        while(i <= gt){
            if(ary[i] < pivNum){
                swap(ary, i, lt);
                i++;
                lt++;
            } else if(ary[i] > pivNum){
                swap(ary, i, gt);
                gt--;
            } else {
                i++;
            }
        }
        // System.out.println(lo);
        // System.out.println(hi);
        // Arrays.toString(ary);
        return new int[] {lt, gt};
    }

    public static int quickselect(int ary[], int k){
        int lo = 0, hi = ary.length;
        int[] lohi = partition(ary, lo, hi);
        
        while(!(k >= lohi[0] && k <= lohi[1])){
            if(lohi[0] > k){
                hi = lohi[0];
                lohi = partition(ary, lo, hi);
            } else {
                lo = lohi[1];
                lohi = partition(ary, lo, hi);
            }
        }

        return ary[lohi[0]];
    }

    public static void quicksort(int[] ary){
        if(ary.length != 0){
            quicksortH(ary, 0, ary.length);
        }
    }

    public static void quicksortH(int[] ary, int lo, int hi){
        if(ary.length < 20){
            insertionSort(ary, 0, ary.length);
            return;
        }

        int lt = lo, gt = hi - 1;
        int i = lo;

        int pivI = (int)(Math.random() * (hi - lo)) + lo;
        int pivNum = ary[pivI];

        while(i <= gt){
            if(ary[i] < pivNum){
                swap(ary, i, lt);
                i++;
                lt++;
            } else if(ary[i] > pivNum){
                swap(ary, i, gt);
                gt--;
            } else {
                i++;
            }
        }

        if(hi > lo + 2){
            quicksortH(ary, lo, lt);
            quicksortH(ary, gt, hi);
        }
    }

    // public static void main(String[] args) {
    //     int[] ary = //{999,999,999,4,1,0,3,2,999,999,999};
    //     //{12,23213,434,34,34,34,34,34,213123,21323,2323,123,123,123,444444};
    //     /*{1,1,1,1,1,1,2,2,1,1,2,2,2,0,0,0,0,1,2,2};*/
    //     {10,9,8,7,6,5,4,4,4,4,44,3,2,1,55,66,77,33,22,111111,55345,75};
    //     /*{1,2,3,4,5,6,7,8,9};*/
    //     // {-123,-34,-123,244324,-3,4442342,-3535,312313};
    //     System.out.println(Arrays.toString(ary));
    //     Quick.quicksort(ary);
    //     System.out.println(Arrays.toString(ary));

    //     // System.out.println(Arrays.toString(ary));
    //     // System.out.println(Quick.partition(ary, 0, ary.length - 1));
    //     // System.out.println(Arrays.toString(ary));
    //    // System.out.println(Arrays.toString(ary));
    //     //Quick.dutch(ary, 0, ary.length);
    //     //System.out.println(Arrays.toString(Quick.partition(ary, 0, ary.length)));
    //     //Quick.quicksort(ary);
    //     //System.out.println(Quick.quickselect(ary, 5));
    //     //System.out.println(Arrays.toString(ary));
    // }
}