import java.awt.geom.NoninvertibleTransformException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

interface MilitaryRanks extends Comparable<MilitaryRanks>{
    String getData();
}
interface Cards extends Comparable<Cards>{
    String getSuit();
    String getValue();
    String toString();
}
interface Numbers extends Comparable<Numbers>{
    int getValue();
    String toString();
}
interface Dates extends Comparable<Dates>{
    int getDay();
    String getMonth();
    int getYear();
    String toString();
}
class Date implements Dates{
    private final int day;
    private final List<Integer> maxdays = Arrays.asList(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
    //for simplification, we don't include intercalary years
    private final List<String> months = Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
    private final String month;
    private final int year;
    public Date(int day, String month, int year){
        if (!months.contains(month) || day < 1 || day > maxdays.get(months.indexOf(month))){
            throw new InputMismatchException("Wrong date input!");
        }
        this.day = day;
        this.month = month;
        this.year = year;
    }
    public int compareTo(Dates other){
        int result1 = Integer.compare(this.year, other.getYear());
        if (result1 == 0){
            int result2 = Integer.compare(months.indexOf(this.month), months.indexOf(other.getMonth()));
            if (result2 == 0){
                return Integer.compare(this.day, other.getDay());
            }
            return result2;
        }
        return result1;
    }
    public int getDay(){
        return this.day;
    }
    public int getYear(){
        return this.year;
    }
    public String getMonth(){
        return this.month;
    }
    public String toString(){
        return Integer.toString(this.day) + " " + this.month + " " + Integer.toString(this.year);
    }
}

class Number implements Numbers{
    private final int value;
    public Number(int value){
        this.value = value;
    }
    public String toString(){
        return Integer.toString(this.value);
    }
    public int getValue(){
        return this.value;
    }
    public int compareTo(Numbers other){
        return Integer.compare(this.value, other.getValue());
    }
}
class Card implements Cards{
    private final String suit;
    public final List<String> valuesOrder = Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king", "ace");
    public final List<String> suitsOrder = Arrays.asList("spades", "clubs", "diamonds", "hearts");
    private final String value;
    public String getValue(){
        return this.value;
    }
    public String getSuit(){
        return this.suit;
    }
    public Card(String value, String suit){
        if (!valuesOrder.contains(value) || !suitsOrder.contains(suit)){
            throw new InputMismatchException("You gave wrong value or suit of a card!");
        }
        this.suit = suit;
        this.value = value;
    }

    @Override
    public int compareTo(Cards other){
        int result1 = Integer.compare(valuesOrder.indexOf(this.value), valuesOrder.indexOf(other.getValue()));
        if (result1 == 0){
            return Integer.compare(suitsOrder.indexOf(this.suit), suitsOrder.indexOf(other.getSuit()));
        }
        return result1;
    }
    @Override
    public String toString(){
        return (this.value + " of " + this.suit);
    }
}
class Private implements MilitaryRanks{

    public String getData(){
        return "Private";
    }

    @Override
    public int compareTo(MilitaryRanks o) {
        if (o instanceof Private){
            return 0;
        }
        else {
            return -1; //private is the lowest rank
        }
    }
    @Override
    public String toString() {
        return getData();
    }
}
class Corporal implements MilitaryRanks{
    public String getData(){
        return "Corporal";
    }
    @Override
    public int compareTo(MilitaryRanks o){
        if (o instanceof Private){
            return 1;
        }
        else if (o instanceof Corporal){
            return 0;
        }
        else{
            return -1;
        }
    }
    @Override
    public String toString() {
        return getData();
    }
}

class Sergeant implements MilitaryRanks{
    public String getData(){
        return "Sergeant";
    }
    @Override
    public int compareTo(MilitaryRanks o){
        if (o instanceof Private || o instanceof Corporal){
            return 1;
        }
        else if (o instanceof Sergeant){
            return 0;
        }
        else{
            return -1;
        }
    }
    @Override
    public String toString() {
        return getData();
    }
}
class Lieutenant implements MilitaryRanks{
    public String getData(){
        return "Lieutenant";
    }
    @Override
    public int compareTo(MilitaryRanks o){
        if (o instanceof Private || o instanceof  Corporal || o instanceof Sergeant){
            return 1;
        }
        else if (o instanceof Lieutenant){
            return 0;
        }
        else{
            return -1;
        }
    }
    @Override
    public String toString() {
        return getData();
    }
}
class Colonel implements MilitaryRanks{
    public String getData(){
        return "Colonel";
    }
    @Override
    public int compareTo(MilitaryRanks o){
        if (o instanceof General){
            return -1;
        }
        else if (o instanceof Colonel){
            return 0;
        }
        else{
            return 1;
        }
    }
    @Override
    public String toString() {
        return getData();
    }
}
class General implements MilitaryRanks{
    public String getData(){
        return "General";
    }
    @Override
    public int compareTo(MilitaryRanks o){
        if (o instanceof General){
            return 0;
        }
        else{
            return 1;
        }
    }
    @Override
    public String toString() {
        return getData();
    }
}


class OrderedList<T extends Comparable<T>> {
    private Node<T> head;
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
    public void add_element(T elem){
        Node<T> newNode = new Node<T>(elem);
        if (head == null || head.data.compareTo(elem) > 0){
            newNode.next = head;
            head = newNode;
        }
        else{
            Node<T> current = head;
            while (current.next != null && current.next.data.compareTo(elem) < 0){
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
    }
    public T get_first(){
        if(head == null){
            return null;
        }
        else{
            return head.data;
        }
    }
    public String toString(){
        String result = "";
        result += "[";
        Node<T> current = head;
        while(current.next != null){
            result += (current.data.toString() + ", ");
            current = current.next;
        }
        result += (current.data.toString() + "]");
        return result;
    }
}

public class Main {
    public static void main(String[] args) {
        OrderedList<MilitaryRanks> ranklist = new OrderedList<>();
        ranklist.add_element(new Sergeant());
        ranklist.add_element(new Private());
        ranklist.add_element(new General());
        System.out.println(ranklist.toString());
        OrderedList<Cards> cardlist = new OrderedList<>();
        cardlist.add_element(new Card("2", "diamonds"));
        cardlist.add_element(new Card("2", "spades"));
        cardlist.add_element(new Card("ace", "diamonds"));
        cardlist.add_element(new Card("jack", "diamonds"));
        System.out.println(cardlist.toString());
        System.out.println(cardlist.get_first());
        OrderedList<Numbers> numberlist = new OrderedList<>();
        numberlist.add_element(new Number(5));
        numberlist.add_element(new Number(-2));
        numberlist.add_element(new Number(5));
        numberlist.add_element(new Number(10));
        System.out.println(numberlist.toString());
        OrderedList<Dates> datelist = new OrderedList<>();
        datelist.add_element(new Date(27, "February", 2009));
        datelist.add_element(new Date(27, "March", 2009));
        datelist.add_element(new Date(27, "February", 2031));
        datelist.add_element(new Date(1, "April", 1990));
        datelist.add_element(new Date(2, "March", 1999));
        System.out.println(datelist.toString());
    }
}