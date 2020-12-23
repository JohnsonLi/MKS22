import java.util.*;

public class MyLinkedListImproved<T extends Comparable<T>> implements Iterable<T>{

    Node first, last;
    int size;

    public MyLinkedListImproved(){
        first = null;
        last = null;
        size = 0;
    }

    public void clear(){
        first = null;
        last = null;
        size = 0;
    }

    public int size(){
        return size;
    }

    public boolean add(T value){
        Node toAdd = new Node(value);
        if(size() == 0){
            first = toAdd;
            last = toAdd;
        } else {
            last.setNext(toAdd);
            toAdd.setPrev(last);
            last = toAdd;
        }

        size++;
        return true;
    }

    private Node getNode(int index){
        Node node = first;
        for(int i = 0; i < index; i++){
            node = node.getNext();
        }
        return node;
    }

    public T get(int index){
        if(index < 0 || index >= size()){
            throw new IndexOutOfBoundsException();
        }
        return getNode(index).getValue();
    }

    public T set(int index, T value){
        if(index < 0 || index >= size()){
            throw new IndexOutOfBoundsException();
        }
        Node node = getNode(index);
        T old = node.getValue();
        node.setValue(value);
        return old;
    }

    public int indexOf(T value){
        for(int i = 0; i < size(); i++){
            if(getNode(i).getValue().equals(value)){
                return i;
            }
        }
        return -1;
    }

    public void add(int index, T value){
        if(index < 0 || index > size()){
            throw new IndexOutOfBoundsException();
        }
        Node toAdd = new Node(value);
        if(index == size()){
            add(value);
            return;
        } else if (index == 0){
            first.setPrev(toAdd);
            toAdd.setNext(first);
            first = toAdd;
        } else {
            Node location = getNode(index);
            toAdd.setPrev(location.getPrev());
            location.getPrev().setNext(toAdd);
            location.setPrev(toAdd);
            toAdd.setNext(location);
        }
        size++;
    }

    public T remove(int index){
        if(index < 0 || index >= size()){
            throw new IndexOutOfBoundsException();
        }
        Node toRemove = getNode(index);
        T old = toRemove.getValue();
        if(index == 0){
            first = toRemove.getNext();
        } else if(index == size() - 1){
            last = toRemove.getPrev();
        } else {
            toRemove.getPrev().setNext(toRemove.getNext());
            toRemove.getNext().setPrev(toRemove.getPrev());
        }
        size--;
        return old;
    }

    public boolean remove(T value){
        if(indexOf(value) != -1){
            remove(indexOf(value));
            return true;
        }
        return false;
    }

    public String toString(){
        String list = "[";
        Node node = first;
        for(int i = 0; i < size(); i++){
            if(i == size() - 1){
                list += node;
            } else {
                list += node + ", ";
            }
            node = node.getNext();
        }
        list += "]";
        return list;
    }

    public String toStringReverse() {
        String list = "[";
        Node node = last;
        for (int i = size-1; i >= 0; i--) {
            if(i == size() - 1){
                list += node;
            } else {
                list += node + ", ";
            }
            node = node.getPrev();
        }
        list += "]";
        return list;
    }

    public Iterator<T> iterator(){
        return new LITerator(first);
    }

    public class LITerator implements Iterator<T>{
        private Node node;

        public LITerator(Node first){
            node = first;
        }

        public boolean hasNext(){
            return node != null;
        }

        public T next(){
            if(!hasNext()){
                throw new NoSuchElementException();
            } 
            T data = node.getValue();
            node = node.getNext();
            return data;
        }
    }

    public int max(){
        if(size() == 0){
            return -1;
        }
        T max = this.first.getValue();
        int indexCount = -1;
        int maxIndex = 0;
        for(T value : this){
            indexCount++;
            if(value.compareTo(max) > 0){
                max = value;
                maxIndex = indexCount;
            }
        }
        return maxIndex;
    }

    public int min(){
        if(size() == 0){
            return -1;
        }
        T min = this.first.getValue();
        int indexCount = -1;
        int minIndex = 0;
        for(T value : this){
            indexCount++;
            if(value.compareTo(min) < 0){
                min = value;
                minIndex = indexCount;
            }
        }
        return minIndex;
    }

    public void extend(MyLinkedListImproved<T> list){
        if(size() == 0 && list.size() != 0){
            first = list.first;
            last = list.last;
            size = list.size();
        } else if (list.size() != 0){
            this.last.setNext(list.first);
            list.first.setPrev(this.last);
            this.last = list.last;
            size += list.size();
            list.clear();
        }
    }

    private class Node{
        Node prev, next;
        T data;

        public Node(T data){
            this.prev = null;
            this.next = null;
            this.data = data;
        }

        public Node getNext(){return next;}
        public Node getPrev(){return prev;}
        public T getValue(){return data;}

        public void setNext(Node node){this.next = node;}
        public void setPrev(Node node){this.prev = node;}
        public void setValue(T value){this.data = value;}

        public String toString(){
            return "" + data;
        }
    }

    // public static void main(String[] args) {
    //     MyLinkedListImproved<Integer> list = new MyLinkedListImproved<>();
    //     System.out.println(list);
    //     list.add(0);
    //     list.add(1);
    //     list.add(2);
    //     list.add(3);
    //     list.add(4);
    //     list.add(5);
    //     list.add(-1);
    //     list.add(-2);
    //     list.add(-3);
    //     list.add(0, 100000);    //adding in the front
    //     list.add(10, 1939238);  //adding in the back
    //     list.add(6, 384723);    //adding to the middle
    //     //list.add(-1, 121938);
    //     //list.add(123, 121938);
    //     System.out.println(list);
    //     System.out.println(list.size());
    //     System.out.println(list.remove(0));                   //removing first thing
    //     System.out.println(list.remove(list.size() - 1));     //removing last thing
    //     System.out.println(list.remove(5));                   //removing in the middle
    //     System.out.println(list.remove(new Integer(-3)));     //removing by value
    //     System.out.println(list.size());
    //     // list.remove(-1);
    //     // list.remove(2312);
    //     //System.out.println(list.indexOf(4));
    //     //System.out.println(list.set(5, 55));
    //     //System.out.println(list.get(5));
    //     System.out.println(list);
    //     System.out.println(list.reverseToString());
    // }

    // public static void main(String[] args) {
    //     MyLinkedListImproved<String> n = new MyLinkedListImproved<>();
    //     n.add("fwefew");
    //     n.add("efwef0");
    //     n.add("efwef1");
    //     n.add("efwef2");
    //     n.add("efwef3");
    //     // n.remove(0);
    //     // System.out.println(n);

    //     for(String value : n){
    //         System.out.println(value);
    //     }
    // }

    // public static void main(String[] args) {
    //     MyLinkedListImproved<Integer> n = new MyLinkedListImproved<>();
    //     // n.add(-21312123);
    //     // n.add(33242342);
    //     // n.add(3);
    //     // n.add(3);
    //     // n.add(3);
    //     // n.add(3);
    //     // n.add(1);
    //     // n.add(99);
    //     // n.add(3);
    //     // n.add(1293120);
    //     // n.add(-21312);
    //     System.out.println(n.min());
    //     System.out.println(n);
    // }

    // public static void main(String[] args) {
    //     // MyLinkedListImproved<String> n = new MyLinkedListImproved<>();
    //     // n.add("z");
    //     // n.add("a");
    //     // n.add("b");
    //     // n.add("c");
    //     // n.add("d");
    //     // System.out.println(n);
    //     // System.out.println(n.min());
    //     // System.out.println(n.max());

    //     MyLinkedListImproved<Integer> n = new MyLinkedListImproved<>();
    //     n.add(0);
    //     n.add(1);
    //     n.add(2);
    //     n.add(3);
    //     n.add(-1111);
    //     n.add(999);
    //     n.add(4);
    //     n.add(5);
    //     n.add(391238);
    //     System.out.println(n.min());
    //     System.out.println(n.max());
    // }

    // public static void main(String[] args) {
    //     MyLinkedListImproved<Integer> a = new MyLinkedListImproved<>();
    //     MyLinkedListImproved<Integer> b = new MyLinkedListImproved<>();

    //     a.add(-1);
    //     a.add(0);
    //     a.add(1);
    //     a.add(2);
    //     b.add(3);
    //     b.add(4);
    //     b.add(5);
    //     b.add(6);

    //     a.extend(b);
    //     System.out.println(a);
    // }
}