using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

public class LazyIntList
{
    public List<int> list = new List<int>();
    public int size;
    public LazyIntList()
    {
        size = 0;
    }
    public int Element(int index)
    {
        if (index>size)
        {
            for (int i = size; i < index; i++)
            {
                list.Add(i);
            }
            size = index;
        }
        return list[index-1];
    }
    public int Size()
    {
        return size;
    }
}

public class LazyPrimeList : LazyIntList
{
    int currentprime = 1;
    public LazyPrimeList()
    {
        size = 0;
    }
    public new int Element(int index)
    {
        if (index>size)
        {
            for (int i = size; i < index; ++i)
            {
                list.Add(NextPrime());
            }
            size = index;
        }
        return list[index-1];
    }
    public int NextPrime()
    {
        while (true)
        {
            currentprime++;
            if (IsPrime(currentprime))
            {
                return currentprime;
            }
        }
    }
    public bool IsPrime(int n)
    {
        for (int i = 2; i <= Math.Sqrt(n); i++)
        {
            if (n % i == 0) return false;
        }
        return true;
    }
}



namespace Lista2
{
    internal class zad4
    {
    }
}
