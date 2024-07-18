namespace IGraph
{
    public interface IGraph
    {
        void AddVertex(string vertex);
        void RemoveVertex(string vertex);
        void AddEdge(string ver1, string ver2);
        void RemoveEdge(string ver1, string ver2);
        bool HasEdge(string ver1, string ver2);
        IEnumerable<string> Neighbours(string vertex);
        void PrintGraph();
    }
}