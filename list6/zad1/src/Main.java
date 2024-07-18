import java.io.*;
import java.util.InputMismatchException;
//zadanie 1 Kornel Orawczak 346010
class List<T> implements Serializable{
    private class Node implements Serializable{
        Node prev;
        Node next;
        T val;
        public Node(T val){
            this.prev = null;
            this.next = null;
            this.val = val;
        }
    }
    private Node head;
    private Node tail;
    private int size;
    public List(T elem){
        Node new_node = new Node(elem);
        this.head = this.tail = new_node;
        this.size = 1;
    }

    public void append(T elem){
        Node new_node = new Node(elem);
        new_node.prev = this.tail;
        this.tail.next = new_node;
        this.tail = new_node;
        this.size+=1;
    }

    public void push(T elem){
        Node new_node = new Node(elem);
        new_node.next = this.head;
        this.head.prev = new_node;
        this.head = new_node;
        this.size+=1;
    }

    public T pop_front(){
        if (this.size == 0){
            throw new InputMismatchException("List is empty!");
        }
        else{
            T popped = this.head.val;
            this.head = this.head.next;
            this.head.prev = null;
            this.size-=1;
            return popped;
        }
    }

    public T pop_back(){
        if (this.size == 0){
            throw new InputMismatchException("List is empty!");
        }
        else{
            T popped = this.tail.val;
            this.tail = this.tail.prev;
            this.tail.next = null;
            this.size-=1;
            return popped;
        }
    }

    public T get(int index){
        if (index < 0 || index > this.size - 1){
            throw new InputMismatchException("Index given is wrong!");
        }
        else{
            if (index <= this.size/2){
                Node starter = this.head;
                int current = 0;
                while (current != index){
                    starter = starter.next;
                    current+=1;
                }
                return starter.val;
            }
            else{
                Node starter = this.tail;
                int current = this.size - 1;
                while (current != index){
                    starter = starter.prev;
                    current-=1;
                }
                return starter.val;
            }
        }
    }

    public void print(){
        Node starter = this.head;
        System.out.print("[");
        while (starter.next != null){
            System.out.print(starter.val + ", ");
            starter = starter.next;
        }
        System.out.println(starter.val + "]");
    }

    public int size(){
        return size;
    }
}





public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<Integer> test_list = new List<Integer>(1);
        test_list.push(0);
        test_list.append(2);
        test_list.append(3);
        test_list.append(4);
        test_list.append(5);
        try {
            FileOutputStream fileOut = new FileOutputStream("list.txt");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(test_list);
            objectOut.close();
            System.out.println("My list has been written into file list.txt");
        } catch (IOException e) {
            System.out.print("Writing into file has failed!");
        }
        test_list.print();
        // Below I tested functionality of a List and changed it
        System.out.println(test_list.get(2));
        System.out.println(test_list.get(5));
        test_list.pop_back();
        test_list.pop_front();
        test_list.print();

        try {
            FileInputStream fileIn = new FileInputStream("list.txt");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            List<Integer> readList =  (List<Integer>) objectIn.readObject();
            objectIn.close();

            System.out.println("List has been read from file list.txt");
            readList.print();
        } catch (IOException | ClassNotFoundException e) {
            System.out.print("Reading from file has failed!");
        }

    }
}