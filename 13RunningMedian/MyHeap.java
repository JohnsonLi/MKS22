import java.util.*;

public class MyHeap<T extends Comparable<T>>{
    public boolean max = true;
    private T[] heap;
    public int size = 0;

    @SuppressWarnings("unchecked")
    public MyHeap(){
        heap = (T[])new Comparable[10];
    }

    @SuppressWarnings("unchecked")
    public MyHeap(boolean e){
        max = e;
        heap = (T[])new Comparable[10];
    }

    public int size(){return size;}

    private void swap(int a, int b){
        T temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }

    @SuppressWarnings("unchecked")
    public void resize(){
        if(size() == heap.length){
            T[] temp = (T[])new Comparable[heap.length * 2];;
            for(int i = 0; i < heap.length; i++){
                temp[i] = heap[i];
            }
            heap = temp;
        }
    }

    private boolean hasParent(int index){
        if(index == 0){
            return false;
        }
        return (index - 1) / 2 >= 0;
    }

    private int getParent(int index){
        return (index - 1) / 2;
    }

    private int getLChild(int index){
        return 2 * index + 1;
    }

    private boolean hasLChild(int index){
        return 2 * index + 1 < size() && heap[2 * index + 1] != null;
    }

    private int getRChild(int index){
        return 2 * index + 2;
    }

    private boolean hasRChild(int index){
        return 2 * index + 2 < size() && heap[2 * index + 2] != null;
    }    

    public T peek(){
        return heap[0];
    }

    public void add(T s){
        resize();
        heap[size] = s;
        if(max){
            pushUpMax(size);
        } else {
            pushUpMin(size);
        }
        size++;
    }

    private void pushUpMax(int index){
        if(!hasParent(index) || heap[index].compareTo(heap[getParent(index)]) < 0){
            return;
        } 
        swap(index, getParent(index));
        pushUpMax(getParent(index));
    }    

    private void pushUpMin(int index){
        if(!hasParent(index) || heap[index].compareTo(heap[getParent(index)]) > 0){
            return;
        } 
        swap(index, getParent(index));
        pushUpMin(getParent(index));
    }  

    public T remove(){
        T removed = heap[0];
        // System.out.println(removed);
        swap(0, size() - 1);
        heap[size() - 1] = null;
        // System.out.println(heap[0]);
        // System.out.println(heap[size() - 1]);
        // System.out.println(Arrays.toString(heap));
        if(max){
            // System.out.println("hello");
            pushDownMax(0);
        } else {
            pushDownMin(0);
        }
        size--;
        return removed;
    }

    public void pushDownMax(int index){
        if(!(hasLChild(index) || hasRChild(index))){
            return;
        }
        if(hasLChild(index) && hasRChild(index)){
            if(heap[index].compareTo(heap[getLChild(index)]) > 0 && heap[index].compareTo(heap[getRChild(index)]) > 0){
                return;
            } else {
                if(heap[getLChild(index)].compareTo(heap[getRChild(index)]) > 0){
                    swap(index, getLChild(index));
                    pushDownMax(getLChild(index));
                } else {
                    swap(index, getRChild(index));
                    pushDownMax(getRChild(index));
                }
            }
        }
        if(heap[index].compareTo(heap[getLChild(index)]) < 0){
            swap(index, getLChild(index));
            return;
        }
    }

    public void pushDownMin(int index){
        if(!(hasLChild(index) || hasRChild(index))){
            return;
        }
        if(hasLChild(index) && hasRChild(index)){
            if(heap[index].compareTo(heap[getLChild(index)]) < 0 && heap[index].compareTo(heap[getRChild(index)]) < 0){
                // System.out.println("Dqwdw");
                return;
            } else {
                if(heap[getLChild(index)].compareTo(heap[getRChild(index)]) < 0){
                    swap(index, getLChild(index));
                    pushDownMin(getLChild(index));
                } else {
                    swap(index, getRChild(index));
                    pushDownMin(getRChild(index));
                }
            }
        }
        if(heap[index].compareTo(heap[getLChild(index)]) > 0){
            swap(index, getLChild(index));
            return;
        }
    }

    public String toString(){
        String array = "[";
        for(int i = 0; i < size(); i++){
            if(i == size() - 1){
                array += heap[i];
            } else {
                array += heap[i] + ", ";
            }
        }
        return array + "]";
    }

    // public static void main(String[] args) {
    //     MyHeap<Integer> n = new MyHeap<>(false);
    //     // n.add(24);
    //     // n.add(30);
    //     // n.add(54);
    //     // n.add(16);
    //     // n.add(3);
    //     // n.add(7);
    //     n.add(3);
    //     n.add(5);
    //     n.add(387);
    //     System.out.println(n);
    //     n.remove();
    //     System.out.println(n);
    //     // n.add(100);
    //     // n.add("a");
    //     // n.add("b");
    //     // n.add("c");
    //     // n.add("d");
    //     // n.add("e");
    // }

}

