//Kornel Orawczak 346010 Visual Studio
using System;

namespace Lista3
{
    public class Node<T>
    {
        public T Value;
        public Node<T> Next;
        public Node<T> Previous;

        public Node(T val)
        {
            this.Value = val;
            this.Next = null;
            this.Previous = null;
        }
    }
    public class Lista<T>
    {
        private Node<T> head = null;
        private Node<T> tail = null;

        public void push_front(T elem)
        {
            Node<T> node = new Node<T>(elem);
            if (head == null)
            {
                head = node;
                tail = node;
            }
            else
            {
                node.Next = head;
                head.Previous = node;
                head = node;
            }
        }
        public void push_back(T elem)
        {
            Node<T> node = new Node<T>(elem);
            if (head == null)
            {
                head = node;
                tail = node;
            }
            else
            {
                node.Previous = tail;
                tail.Next = node;
                tail = node;
            }
        }
        public T pop_front()
        {
            if (is_empty())
            {
                throw new InvalidOperationException("List is empty! Nothing to pop");
            }
            T val = head.Value;
            if (head == tail)
            {
                head = tail = null;
            }
            else
            {
                head = head.Next;
                head.Previous = null;
            }
            return val;
        }
        public T pop_back()
        {
            if (is_empty())
            {
                throw new InvalidOperationException("List is empty! Nothing to pop");
            }
            T val = tail.Value;
            if (head == tail)
            {
                head = tail = null;
            }
            else
            {
                tail = tail.Previous;
                tail.Next = null;
            }
            return val;
        }
        public bool is_empty()
        {
            return head == null;
        }
        public void print_list()
        {
            if (is_empty())
            {
                Console.WriteLine("List is empty!");
            }
            else
            {
                Node<T> starter = head;
                Console.Write("[");
                while (starter.Next != null)
                {
                    Console.Write(starter.Value + ", ");
                    starter = starter.Next;
                }
                Console.WriteLine(starter.Value + "]");
            }
            
        }
    }
}
