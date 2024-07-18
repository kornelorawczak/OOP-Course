using System;
using System.Collections;
using System.Collections.Generic;
using System.ComponentModel;
using SlowaFibonacciego;
using Vertex;
using MatrixGraph;
using ListGraph;
using IGraph;
using GraphOperations;


public class Program
{
    public void print_list(List<string> list)
    {
        Console.Write("[");
        for (int i = 0; i < list.Count; i++)
        {
            if (i != list.Count - 1) Console.Write(list[i] + ", ");
            else Console.Write(list[i] + "]");
        }
    }
    
    static void Main(string[] args)
    {
        SlowaFibonacciego.SlowaFibonacciego sf = new SlowaFibonacciego.SlowaFibonacciego(6);
        foreach(string s in sf)
        {
            Console.WriteLine(s);
        }
        foreach (string s1 in sf)
            foreach (string s2 in sf)
                Console.WriteLine(s1, s2); 
        MatrixGraph.MatrixGraph mg = new MatrixGraph.MatrixGraph();
        mg.AddVertex("jeden");
        mg.AddVertex("dwa");
        mg.AddVertex("trzy");
        mg.AddVertex("cztery");
        mg.AddEdge("jeden", "trzy");
        mg.PrintGraph();
        mg.RemoveVertex("trzy");
        mg.AddEdge("dwa", "cztery");
        mg.RemoveEdge("dwa", "cztery");
        mg.PrintGraph();
        ListGraph.ListGraph lg = new ListGraph.ListGraph();
        lg.AddVertex("one");
        lg.AddVertex("two");
        lg.AddVertex("three");
        lg.AddVertex("four");
        lg.AddEdge("one", "three");
        lg.AddEdge("two", "one");
        lg.RemoveVertex("two");
        lg.AddEdge("four", "three");
        lg.RemoveEdge("three", "four");
        lg.PrintGraph();
        ListGraph.ListGraph lgr = new ListGraph.ListGraph();
        var operations = new GraphOperations.GraphOperations();
        operations.CreateRandom(lgr, 10, 9);
        lgr.PrintGraph();
        Program pg = new Program();
        pg.print_list(operations.ShortestPathByLabels(lgr, "1", "8"));
    }
}