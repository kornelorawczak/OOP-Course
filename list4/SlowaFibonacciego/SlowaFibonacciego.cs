using System.Collections;

namespace SlowaFibonacciego
{
    public class SlowaFibonacciego : IEnumerable<string>
    {
        private int count;
        public SlowaFibonacciego(int num)
        {
            if (num <= 0) throw new ArgumentException("Number passed into object contructor has to be positive");
            count = num;
        }
        public IEnumerator<string> GetEnumerator()
        {
            yield return "b";
            if (count > 1)
            {
                yield return "a";
            }
            string prev = "a";
            string preprev = "b";
            string current;
            for (int i = 3; i <= count; i++)
            {
                current = prev + preprev;
                yield return current;
                preprev = prev;
                prev = current;
            }
        }

        IEnumerator IEnumerable.GetEnumerator()
        {
            return GetEnumerator();
        }
    }

}