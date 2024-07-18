using System;
using System.Collections.Generic;

class Program
{
    static void Main(string[] args)
    {

        IntStream stream = new IntStream();
        Console.WriteLine(stream.Next());
        Console.WriteLine(stream.Next());
        Console.WriteLine(stream.Next());
        stream.Reset();
        Console.WriteLine(stream.Next());

        FibStream fs = new FibStream();
        Console.WriteLine(fs.Next());
        Console.WriteLine(fs.Next());
        Console.WriteLine(fs.Next());
        Console.WriteLine(fs.Next());
        fs.Reset();
        Console.WriteLine(fs.Next());

        RandomStream rs = new RandomStream();
        Console.WriteLine(rs.Next());
        Console.WriteLine(rs.Next());
        Console.WriteLine(rs.Next());
        Console.WriteLine(rs.Next());
        rs.Reset();
        Console.WriteLine(rs.Next());

        LazyIntList list = new LazyIntList();
        Console.WriteLine(list.Element(40));
        Console.WriteLine(list.Element(100));
        Console.WriteLine(list.Element(102));
        Console.WriteLine(list.Element(40));
        Console.WriteLine(list.Size());

        LazyPrimeList list2 = new LazyPrimeList();
        Console.WriteLine(list2.Element(40));
        Console.WriteLine(list2.Element(102));
        Console.WriteLine(list2.Element(40));
        Console.WriteLine(list2.Size());
    }
}
