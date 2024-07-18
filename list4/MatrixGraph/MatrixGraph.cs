using IGraph;
namespace MatrixGraph
{
    public class MatrixGraph : IGraph.IGraph
    {
        private List<List<int>> matrix; //2-dimensional List representing a neighbouring matrix of a graph 
        private List<string> vertices; //list of vertices names

        public MatrixGraph()
        {
            matrix = new List<List<int>>();
            vertices = new List<string>();
        }

        public void AddVertex(string ver)
        {
            if (vertices.Contains(ver))
            {
                throw new ArgumentException("This vertex already exists!");
            }
            else
            {
                vertices.Add(ver);
                for (int i = 0; i < vertices.Count - 1; i++)
                {
                    matrix[i].Add(0);
                }
                matrix.Add(new List<int>(new int[vertices.Count])); //adding a list of zeros 
            }
        }
        public void RemoveVertex(string ver)
        {
            if (!vertices.Contains(ver))
            {
                throw new ArgumentException("This vertex doesn't exists!");
            }
            else
            {
                int id = vertices.IndexOf(ver);
                vertices.Remove(ver);
                matrix.Remove(matrix[id]);
                for (int i = 0; i < vertices.Count; i++)
                {
                    matrix[i].Remove(matrix[i][id]);
                }
            }
        }
        public void AddEdge(string ver1, string ver2)
        {
            int id1 = vertices.IndexOf(ver1);
            int id2 = vertices.IndexOf(ver2);
            if (id1 != -1 && id2 != -1) //these vertices exist in the vertices list
            {
                matrix[id1][id2] = 1;
                matrix[id2][id1] = 1;
            }
            else
            {
                throw new ArgumentException("Vertex passed isn't yet added to the matrix!");
            }
        }
        public void RemoveEdge(string ver1, string ver2)
        {
            int id1 = vertices.IndexOf(ver1);
            int id2 = vertices.IndexOf(ver2);
            if (id1 != -1 && id2 != -1) //these vertices exist in the vertices list
            {
                matrix[id1][id2] = 0;
                matrix[id2][id1] = 0;
            }
            else
            {
                throw new ArgumentException("Vertex passed isn't yet added to the matrix!");
            }
        }
        public bool HasEdge(string ver1, string ver2)
        {
            int id1 = vertices.IndexOf(ver1);
            int id2 = vertices.IndexOf(ver2);
            if (id1 != -1 && id2 != -1)
            {
                return matrix[id1][id2] == 1;
            }
            return false;
        }
        public IEnumerable<string> Neighbours(string ver)
        {
            int id = vertices.IndexOf(ver);
            if (id != -1)
            {
                for (int i = 0; i < vertices.Count; i++)
                {
                    if (matrix[id][i] == 1)
                    {
                        yield return vertices[i];
                    }
                }
            }
            else
            {
                throw new ArgumentException("That vertex doesn't exist!");
            }
        }
        public void PrintGraph()
        {
            Console.Write("[");
            for (int i = 0; i < vertices.Count; i++)
            {
                if (i != 0) Console.Write(" ");
                for (int j = 0; j < vertices.Count; j++)
                {
                    if (i == vertices.Count - 1 && j == vertices.Count - 1) Console.Write(matrix[i][j]);
                    else Console.Write(matrix[i][j] + ", ");
                }
                if (i != vertices.Count - 1) Console.WriteLine();
            }
            Console.WriteLine("]");
        }

    }
}