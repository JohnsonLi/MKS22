public class MyLinkedList{

    Node first, last;
    int size;

    public MyLinkedList(){
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

    public boolean add(Integer value){
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

    public Integer get(int index){
        if(index < 0 || index >= size()){
            throw new IndexOutOfBoundsException();
        }
        return getNode(index).getValue();
    }

    public Integer set(int index, Integer value){
        if(index < 0 || index >= size()){
            throw new IndexOutOfBoundsException();
        }
        Node node = getNode(index);
        Integer old = node.getValue();
        node.setValue(value);
        return old;
    }

    public int indexOf(Integer value){
        for(int i = 0; i < size(); i++){
            if(getNode(i).getValue().equals(value)){
                return i;
            }
        }
        return -1;
    }

    public void add(int index, Integer value){
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

    public Integer remove(int index){
        if(index < 0 || index >= size()){
            throw new IndexOutOfBoundsException();
        }
        Node toRemove = getNode(index);
        Integer old = toRemove.getValue();
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

    public boolean remove(Integer value){
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

    private class Node{
        Node prev, next;
        Integer data;

        public Node(Integer data){
            this.prev = null;
            this.next = null;
            this.data = data;
        }

        public Node getNext(){return next;}
        public Node getPrev(){return prev;}
        public Integer getValue(){return data;}

        public void setNext(Node node){this.next = node;}
        public void setPrev(Node node){this.prev = node;}
        public void setValue(Integer value){this.data = value;}

        public String toString(){
            return "" + data;
        }
    }

    // public static void main(String[] args) {
    //     MyLinkedList list = new MyLinkedList();
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
    // }

}