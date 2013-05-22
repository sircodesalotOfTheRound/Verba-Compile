using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using VerbaCompile.Tokens;

namespace VerbaCompile
{
    class Program
    {
        static void Main(string[] args)
        {
            var tokens = Tokenizer.Tokenize("A b 1 2 41.13 v 3  4");

            foreach (var token in tokens)
                Console.WriteLine("{0} {1}", token.GetType(), token.TextValue);


            Console.ReadLine();
        }
    }
}
