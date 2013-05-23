using System;
using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using VerbaCompile.Tokens;
using System.Collections.Generic;
using System.Diagnostics;
using VerbaCompile.Tokens.Primitives.Numeric;
using VerbaCompile.Tokens.Primitives;
using VerbaCompile.Tokens.Operators;

namespace VerbaCompileTest
{
    [TestClass]
    public class NumericCompilation
    {
        [TestMethod]
        public void SimpleInt32Test()
        {
            IEnumerable<Token> tokens = Tokenizer.Tokenize("1 2 3 4 5 6 7 8");
            
            DisplayTokens(tokens);

            Assert.IsTrue(tokens.Count() == 8);
        }

        [TestMethod]
        public void SimpleDoubleTest()
        {
            IEnumerable<Token> tokens = Tokenizer.Tokenize("1.1 2.2 3.333 4.444")
                .OfType<DoubleToken>();

            DisplayTokens(tokens);

            Assert.IsTrue(tokens.Count() == 4);
        }

        [TestMethod]
        public void SimpleIdentifierTest()
        {
            IEnumerable<Token> tokens = Tokenizer.Tokenize("a 1 b 4.567 c  d e")
                .OfType<IdentifierToken>();

            DisplayTokens(tokens);

            Assert.IsTrue(tokens.Count() == 5);
        }

        [TestMethod]
        public void SimpleOperatorTest()
        {
            IEnumerable<Token> tokens = Tokenizer.Tokenize("1 a *=+-/= 3");

            DisplayTokens(tokens);

            Assert.IsTrue(tokens.OfType<OperatorToken>().Count() == 6);
            Assert.IsTrue(tokens.OfType<Int32Token>().Count() == 2);
        }

        [TestMethod]
        public void SimpleNumericTest()
        {
            IEnumerable<Token> tokens = Tokenizer.Tokenize("a 1 2 2.567 3.890 b 4.567 c  d e")
                .OfType<NumericToken>();

            DisplayTokens(tokens);

            Assert.IsTrue(tokens.OfType<NumericToken>().Count() == 5);
            Assert.IsTrue(tokens.OfType<Int32Token>().Count() == 2);
            Assert.IsTrue(tokens.OfType<DoubleToken>().Count() == 3);
        }


        private void DisplayTokens(IEnumerable<Token> tokens)
        {
            foreach (Token token in tokens)
            {
                Console.WriteLine(token.TextValue);
            }
        }
    }
}
