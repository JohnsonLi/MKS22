import java.util.*;

public class MyDeque<E> {

    public E[] deck;
    public int front = 0, back = 0, size = 0;

    @SuppressWarnings("unchecked")
    public MyDeque() {
        deck = (E[]) new Object[10];
    }

    @SuppressWarnings("unchecked")
    public MyDeque(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException();
        }
        deck = (E[]) new Object[initialCapacity];
    }

    public int size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    public void resize() {
        if (size() == deck.length) {
            E[] temp = (E[]) new Object[deck.length * 2];
            for (int i = 0; i < size(); i++) {
                temp[i] = deck[(front + i) % size()];
            }
            // System.out.println(Arrays.toString(temp));
            front = 0;
            back = size() - 1;
            deck = temp;
        }
    }

    public void addFirst(E value) {
        if (value == null) {
            throw new NullPointerException();
        }
        resize();
        if (front == 0 && deck[front] == null) {
            deck[front] = value;
        } else if (front - 1 < 0) {
            deck[deck.length - 1] = value;
            front = deck.length - 1;
        } else {
            deck[front - 1] = value;
            front--;
        }
        size++;
    }

    public E removeFirst() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        E temp = deck[front];
        deck[front] = null;
        front = (front + deck.length + 1) % deck.length;
        size--;
        return temp;
    }

    public void addLast(E value) {
        if (value == null) {
            throw new NullPointerException();
        }
        resize();
        if (back == 0 && deck[back] == null) {
            deck[back] = value;
        } else if (back + 1 > deck.length - 1) {
            deck[0] = value;
            back = 0;
        } else {
            deck[back + 1] = value;
            back++;
        }
        size++;
    }

    public E removeLast() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        E temp = deck[back];
        if (back == 0) {
            deck[back] = null;
            back = deck.length - 1;
        } else {
            deck[back] = null;
            back--;
        }
        return temp;
    }

    public E getFirst() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        return deck[front];
    }

    public E getLast() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        return deck[back];
    }

    public String toString() {
        String deckStr = "[";
        for (int i = 0; i < deck.length; i++) {
            if (i == deck.length - 1) {
                deckStr += deck[i] + "";
            } else {
                deckStr += deck[i] + ", ";
            }
        }
        return deckStr += "]";
    }

    // public static void main(String[] args) {
    //     MyDeque<Integer> n = new MyDeque<Integer>();
    //     // System.out.println(n.size());
    //     // System.out.println(n.front);
    //     // n.addFirst(4);
    //     // n.addFirst(4);
    //     // n.addFirst(4);
    //     // n.addFirst(4);
    //     // n.addFirst(4);
    //     // n.addFirst(4);
    //     // n.addFirst(4);
    //     // n.addFirst(4);
    //     // n.addFirst(4);
    //     // n.addFirst(999);
    //     // n.addLast(4);
    //     // n.addLast(4);
    //     // n.addLast(4);
    //     // n.addLast(4);
    //     // n.addLast(4);
    //     // n.addLast(4);
    //     // n.addLast(4);
    //     // n.addLast(4);
    //     // n.addLast(4);
    //     // n.addLast(4);
    //     // n.addLast(5);
    //     n.addLast(1);
    //     n.addLast(5);
    //     n.addLast(5);
    //     n.addLast(5);
    //     n.addLast(5);
    //     n.addLast(5);
    //     n.addLast(5);
    //     n.addLast(5);
    //     n.addLast(5);
    //     n.addLast(10);
    //     n.addFirst(13123912);
    //     n.addLast(23123);
    //     System.out.println(n.getFirst() + " first");
    //     System.out.println(n.getLast() + " last");
    //     // n.addFirst(100);
    //     // n.removeFirst();
    //     // n.removeFirst();
    //     // n.removeFirst();
    //     // n.removeFirst();
    //     // n.removeFirst();
    //     // n.addFirst(100);
    //     // n.addLast(6);
    //     // n.addLast(6);
    //     // System.out.println(n.removeFirst());
    //     // System.out.println(n.removeFirst());
    //     // System.out.println(n.removeFirst());
    //     // System.out.println(n.removeFirst());
    //     // System.out.println(n.removeFirst());
    //     // System.out.println(n.removeFirst());
    //     // System.out.println(n.removeFirst());
    //     // System.out.println(n.removeFirst());
    //     // System.out.println(n.removeFirst());
    //     // System.out.println(n.removeFirst());
    //     // System.out.println(n.removeFirst());
    //     // n.addFirst(5);
    //     // n.addFirst(5);
    //     // n.addFirst(5);
    //     // n.addFirst(5);
    //     // n.addFirst(5);
    //     // n.addFirst(5);
    //     // n.addFirst(5);
    //     // n.addFirst(5);
    //     // n.addFirst(5);
    //     // n.addFirst(5);
    //     // n.addFirst(6);
    //     // System.out.println(n.size());
    //     // n.removeFirst();
    //     // n.removeFirst();
    //     // n.removeFirst();
    //     // n.removeFirst();
    //     System.out.println(n.size());
    //     System.out.println(n.front);
    //     System.out.println(n);
    // }

}
