using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using VerbaCompile.Tokens;
using VerbaCompile.Tokens.Range;
using VerbaCompile.TokenTree;

namespace VerbaCompileTest.Tools
{
    internal class Debugging
    {
        public static void DisplayTokens<T>(IEnumerable<Token> tokens)
            where T : Token
        {
            foreach (T token in tokens.OfType<T>())
            {
                Console.WriteLine("{0} \t\t\t ({1} - {2}) : ({3})", 
                    token.TextValue, 
                    token.Range.Start, 
                    token.Range.End, 
                    token.GetType().Name);
            }
        }

        public static void DisplayTokens(IEnumerable<Token> tokens)
        {
            DisplayTokens<Token>(tokens);
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

        public static void DisplayTree(TokenSourceTree tree)
        {
            TokenSourceNode root = tree.Root;
            Debugging.DisplayTreeNode(0, root);
        }

        private static void DisplayTreeNode(Int32 depth, TokenSourceNode node)
        {
            String tabs = new String('*', depth * 2);
            Console.WriteLine("{0}{1}: ({2})", tabs, 
                node.Token.GetType().Name,
                node.Token.TextValue);

            foreach (TokenSourceNode child in node.Children)
            {
                DisplayTreeNode(depth + 1, child);
            }
        }

    }
}
