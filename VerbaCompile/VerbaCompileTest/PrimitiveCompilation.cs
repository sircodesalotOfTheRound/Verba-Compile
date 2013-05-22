using System;
using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using VerbaCompile.Tokens;
using System.Collections.Generic;
using System.Diagnostics;
using VerbaCompile.Tokens.Primitives.Numeric;
using VerbaCompile.Tokens.Primitives;

namespace VerbaCompileTest
{
    [TestClass]
    public class NumericCompilation
    {
        [TestMethod]
        public void SimpleInt32Test()
        {
            IEnumerable<Token> tokens = Tokenizer.Tokenize("1 2 3 4 5 6 7 8");
            Assert.IsTrue(tokens.Count() == 8);
        }

        [TestMethod]
        public void SimpleDoubleTest()
        {
            IEnumerable<Token> tokens = Tokenizer.Tokenize("1.1 2.2 3.333 4.444")
                .OfType<DoubleToken>();

            Assert.IsTrue(tokens.Count() == 4);
        }

        [TestMethod]
        public void SimpleIdentifierTest()
        {
            IEnumerable<Token> tokens = Tokenizer.Tokenize("a 1 b 4.567 c  d e")
                .OfType<IdentifierToken>();

            foreach (Token token in tokens)
            {
                Console.WriteLine(token.TextValue);
            }

            Assert.IsTrue(tokens.Count() == 5);
        }
    }
}
