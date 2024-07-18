using System;
using System.Collections.Generic;

public class IntStream
{
    public int current;

    public IntStream()
    {
        current = 0;
    }
    public int Next()
    {
        current += 1;
        return current-1;
    }
    public bool Eos()
    {
        return current<0;
    }
    public void Reset()
    {
        current = 0;
    }
}

public class FibStream : IntStream
{
    int previous;

    public FibStream()
    {
        previous = 1;
        current = 1;
    }
    public new int Next()
    {
        int temp = current;
        current = current + previous;
        previous = temp;
        return previous;
    }
    public new bool Eos() 
    {
        return current <= 0;
    }
    public new void Reset()
    {
        previous = current = 1;
    }
}

public class RandomStream : IntStream
{
    public Random rand;
    public RandomStream() 
    {
        rand = new Random();
        current = 1;
    }
    public new string Next()
    {
        int lenght = NextPrime();
        char[] randomChars = new char[lenght];

        for (int i=0; i<lenght; i++)
        {
            randomChars[i] = (char)('a' + rand.Next() % 26);
        }
        return new string(randomChars);
    }

    public int NextPrime()
    {
        while(true)
        {
            current++;
            if (IsPrime(current))
            {
                return current;
            }
        }
    }
    
    public bool IsPrime(int n)
    {
        for (int i=2; i<=Math.Sqrt(n); i++)
        {
            if (n % i == 0) return false;
        }
        return true;
    }

    public new void Reset()
    {
        current = 1;
    }
}


namespace Lista2
{
    public class zad1
    {

    }
}
