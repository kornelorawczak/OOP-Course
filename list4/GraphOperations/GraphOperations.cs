using IGraph;
using Vertex;
namespace GraphOperations
{
    public class GraphOperations
    {
        private Random rand = new Random();

        public void CreateRandom(IGraph.IGraph g, int vert, int edges)
        {
            if (edges > vert * (vert - 1) / 2)
            {
                throw new ArgumentException("There can't be that many edges in that small graph");
            }
            for (int i = 0; i < vert; i++)
            {
                g.AddVertex(i.ToString());
            }
            while (edges > 0)
            {
                string ver1 = rand.Next(vert).ToString();
                string ver2 = rand.Next(vert).ToString();
                if (ver1 != ver2 && !g.HasEdge(ver1, ver2))
                {
                    g.AddEdge(ver1, ver2);
                    edges--;
                }
            }
        }
        public List<Vertex<string>> ShortestPath(IGraph.IGraph g, Vertex<string> a, Vertex<string> b)
        {
            var visited = new HashSet<Vertex<string>>();
            var queue = new Queue<List<Vertex<string>>>();
            queue.Enqueue(new List<Vertex<string>>() { a });

            while (queue.Count > 0)
            {
                var path = queue.Dequeue(); //we take off first list from queue (when starting we take a list [a]
                var last = path.Last();
                if (last.Equals(b)) //checking if current path is already alright
                {
                    return path;
                }
                if (!visited.Contains(last))
                {
                    visited.Add(last);
                    foreach (var n in g.Neighbours(last.get()))
                    {
                        var newPath = new List<Vertex<string>>(path);
                        Vertex.Vertex<string> newV = new Vertex<string>(n);
                        newPath.Add(newV);
                        queue.Enqueue(newPath); //we create a new possible path with every neighbour and add them to the end of the queue
                    }
                }
            }
            return new List<Vertex<string>>();
        }
        public List<string> ShortestPathByLabels(IGraph.IGraph g, string a, string b)
        {
            var visited = new HashSet<string>();
            var queue = new Queue<List<string>>();
            queue.Enqueue(new List<string>() { a });

            while (queue.Count > 0)
            {
                var path = queue.Dequeue(); //we take off first list from queue (when starting we take a list [a]
                var last = path.Last();
                if (last == b) //checking if current path is already alright
                {
                    return path;
                }
                if (!visited.Contains(last))
                {
                    visited.Add(last);
                    foreach (var n in g.Neighbours(last))
                    {
                        var newPath = new List<string>(path);
                        newPath.Add(n);
                        queue.Enqueue(newPath); //we create a new possible path with every neighbour and add them to the end of the queue
                    }
                }
            }
            return new List<string>();
        }
    }
}