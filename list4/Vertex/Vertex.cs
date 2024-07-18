namespace Vertex
{
    public class Vertex<T>
    {
        T val;
        public Vertex(T val)
        {
            this.val = val;
        }
        public T get() 
        {
            return val;
        }
            
    }
}