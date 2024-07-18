// Zadanie 2 lista 6 Kornel Orawczak 346010 - własna implementacja kolejki w oparciu o interfejs Collection<E>
import java.lang.reflect.Array;
import java.util.*;


class Queue<E> implements Collection<E>{
    private final int max_size;
    private int size = 0;
    private class Node{
        private Node next = null;
        private final E val;
        public Node(E val){
            this.val = val;
        }
    }

    private class QueueIterator implements Iterator<E>{
        private Node current = start;

        @Override
        public boolean hasNext(){
            return current!=null;
        }

        @Override
        public E next(){
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E element = current.val;
            current = current.next;
            return element;
        }
    }

    private Node start = null;
    private Node end = null;
    public Queue(int size){
        this.max_size = size;
    }
    @Override
    public boolean add(E e){
        if (this.size == 0){
            this.start = this.end = new Node(e);
        }
        else if (this.size == this.max_size) return false;
        else{
            Node new_node = new Node(e);
            this.end.next = new_node;
            this.end = new_node;
        }
        this.size+=1;
        return true;
    }
    @Override
    public boolean addAll(Collection<? extends E> c){
        boolean modified = false;
        for (E element : c){
            if (this.size == this.max_size) {
                break;
            }
            if (add(element)){
                modified = true;
            }
        }
        return modified;
    }
    @Override
    public void clear(){
        this.size = 0;
        this.start = this.end = null;
    }
    @Override
    public boolean contains(Object o){
        Node starter = this.start;
        while (starter!=null){
            if (o.equals(starter.val)) return true;
            starter = starter.next;
        }
        return false;
    }
    @Override
    public boolean containsAll(Collection<?> c){
        for (Object elem : c){
            if (!contains(elem)) return false;
        }
        return true;
    }
    @Override
    public Iterator<E> iterator(){
        return new QueueIterator();
    }

    public E pop(){
        if (isEmpty()){
            throw new InputMismatchException("Queue is empty, therefore it can't be popped!");
        }
        E popped = this.start.val;
        this.start = this.start.next;
        this.size-=1;
        return popped;
    }
    @Override
    public Object[] toArray(){
        Object[] array = new Object[this.size];
        int index = 0;
        Node starter = this.start;
        while (index < this.size){
            array[index] = starter.val;
            index+=1;
            starter = starter.next;
        }
        return array;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < this.size){
            // if given array is too small, we have to create a new one with good size
            a = (T[]) Arrays.copyOf(new Object[this.size], this.size, a.getClass());
        }
        else if (a.length > this.size){
            a[this.size] = null;
        }
        int index = 0;
        Node starter = this.start;
        while (index < this.size){
            a[index] = (T) starter.val;
            index+=1;
            starter = starter.next;
        }
        return a;
    }
    @Override
    public boolean remove(Object o){
        // this function removes only first instance of the object it founds
        Node starter = this.start;
        while (starter.next.next!=null){
            if (o.equals(starter.next.val)){
                starter.next = starter.next.next;
                this.size-=1;
                return true;
            }
            starter = starter.next;
        }
        if (o.equals(starter.next.val)){
            starter.next = null;
            this.size-=1;
            return true;
        }
        return false;
    }
    @Override
    public boolean removeAll(Collection<?> c){
        boolean modified = false;
        for (Object elem : c){
            if (remove(elem)){
                modified = true;
            }
        }
        return modified;
    }
    @Override
    public boolean retainAll(Collection<?> c){
        boolean modified = false;
        Node starter = this.start;
        while (starter!=null){
            if (!c.contains(starter.val)){
                remove(starter.val);
                modified = true;
            }
            starter = starter.next;
        }
        return modified;
    }
    public void print(){
        System.out.print("[");
        Node starter = this.start;
        while (starter.next!=null){
            System.out.print(starter.val + ", ");
            starter = starter.next;
        }
        System.out.println(starter.val + "]");
    }
    @Override
    public int size(){
        return this.size;
    }
    @Override
    public boolean isEmpty(){
        return this.size == 0;
    }

}


public class Main {
    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<Integer>(10);
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);
        queue.add(6);
        queue.pop();
        queue.print();
        System.out.println(queue.size());
        System.out.println(queue.contains(3));
        Collection<Integer> array = Arrays.asList(11,12,13,14);
        queue.addAll(array);
        queue.print();
        System.out.println(queue.size());
        Object[] array2 = queue.toArray();
        System.out.println(array2[4]);
        Collection<Integer> removal_array = Arrays.asList(3,13);
        queue.removeAll(removal_array);
        queue.print();
        Collection<Integer> retainer_array = Arrays.asList(2,6,12,15);
        queue.retainAll(retainer_array);
        System.out.println(queue.size());
        queue.print();
    }
}