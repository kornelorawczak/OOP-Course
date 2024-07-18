using IGraph;
namespace ListGraph
{
    public class ListGraph : IGraph.IGraph
    {
        private Dictionary<string, List<string>> dict;
        public ListGraph()
        {
            dict = new Dictionary<string, List<string>>();
        }
        public void AddVertex(string ver)
        {
            if (dict.ContainsKey(ver))
            {
                throw new ArgumentException("This vertex already exists!");
            }
            else
            {
                dict.Add(ver, new List<string>());
            }
        }
        public void RemoveVertex(string ver)
        {
            if (dict.ContainsKey(ver))
            {
                dict.Remove(ver);
                foreach (var k in dict.Keys)
                {
                    if (dict[k].Contains(ver))
                    {
                        dict[k].Remove(ver);
                    }
                }
            }
            else
            {
                throw new ArgumentException("This vertex doesn't exists!");
            }
        }
        public void AddEdge(string ver1, string ver2)
        {
            if (dict.ContainsKey(ver1) && dict.ContainsKey(ver2))
            {
                dict[ver1].Add(ver2);
                dict[ver2].Add(ver1);
            }
            else
            {
                throw new ArgumentException("Both the vertices has to exist!");
            }
        }
        public void RemoveEdge(string ver1, string ver2)
        {
            if (dict.ContainsKey(ver1) && dict.ContainsKey(ver2) && dict[ver1].Contains(ver2) && dict[ver2].Contains(ver1))
            {
                dict[ver1].Remove(ver2);
                dict[ver2].Remove(ver1);
            }
            else
            {
                throw new ArgumentException("Both the vertices have to exist and have to have an edge!");
            }
        }
        public bool HasEdge(string ver1, string ver2)
        {
            if (dict.ContainsKey(ver1) && dict.ContainsKey(ver2))
            {
                return dict[ver1].Contains(ver2);
            }
            return false;
        }
        public IEnumerable<string> Neighbours(string ver)
        {
            if (dict.ContainsKey(ver))
            {
                return dict[ver];
            }
            else
            {
                return Enumerable.Empty<string>();
            }
        }
        public void PrintGraph()
        {
            foreach (var k in dict.Keys)
            {
                Console.Write(k + ": ");
                foreach (var v in dict[k])
                {
                    Console.Write(v + ", ");
                }
                Console.WriteLine();
            }
        }
    }
}