using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using VerbaCompile.Tokens;
using VerbaCompile.Tokens.Range;

namespace VerbaCompileTest.Tools
{
    internal class Debugger
    {
        public static void DisplayTokens<T>(IEnumerable<Token> tokens)
            where T : Token
        {
            foreach (T token in tokens.OfType<T>())
            {
                Console.WriteLine("{0} \t\t\t ({1} - {2}) : ({3})", token.TextValue, token.Range.Start, token.Range.End, token.GetType().Name);
            }
        }

        public static void DisplayRanges<T>(IEnumerable<Token> tokens)
            where T : RangeToken
        {
            foreach (T quotationRange in tokens.OfType<T>())
            {
                Console.WriteLine("{0, -20} \t\t ({1}-{2})",
                    quotationRange.TextValue,
                    quotationRange.Range.Start,
                    quotationRange.Range.End);
            }
        }

    }
}
