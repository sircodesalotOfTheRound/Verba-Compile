using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;
using System.Collections.Generic;
using System.Linq;
using VerbaCompile.Tokens;
using VerbaCompile.Tokens.Operators;
using VerbaCompile.Tokens.Primitives;
using VerbaCompile.Tokens.Primitives.Numeric;
using VerbaCompile.Tokens.Range.Marker;
using VerbaCompile.Tokens.Tokenizing;
using VerbaCompileTest.Tools;

namespace VerbaCompileTest
{
    [TestClass]
    public class SimpleTesting
    {
        [TestMethod]
        public void SimpleInt32Test()
        {
            TokenizedSource tokenizedSource = Tokenizer.Tokenize("1 2 3 4 5 6 7 8");

            Debugging.DisplayTokens<Token>(tokenizedSource.Tokens);

            Assert.IsTrue(tokenizedSource.Tokens.Count() == 8);
        }

        [TestMethod]
        public void SimpleDoubleTest()
        {
            IEnumerable<Token> tokens = Tokenizer.Tokenize("1.1 2.2 3.333 4.444")
                .Tokens
                .OfType<DoubleToken>();

            Debugging.DisplayTokens<Token>(tokens);

            Assert.IsTrue(tokens.Count() == 4);
        }

        [TestMethod]
        public void SimpleIdentifierTest()
        {
            IEnumerable<Token> tokens = Tokenizer.Tokenize("a 1 b 4.567 c  d e")
                .Tokens
                .OfType<IdentifierToken>();

            Debugging.DisplayTokens<Token>(tokens);

            Assert.IsTrue(tokens.Count() == 5);
        }

        [TestMethod]
        public void SimpleOperatorTest()
        {
            IEnumerable<Token> tokens = Tokenizer.Tokenize("1 a *=+-/= 3").Tokens;

            Debugging.DisplayTokens<Token>(tokens);

            Assert.IsTrue(tokens.OfType<OperatorToken>().Count() == 6);
            Assert.IsTrue(tokens.OfType<Int32Token>().Count() == 2);
        }

        [TestMethod]
        public void SimpleRangeTokenTest()
        {
            // Three qutotations
            IEnumerable<Token> tokens = Tokenizer.Tokenize(@" [brackets] ""quotes"" {braces} (parentasis)").Tokens;

            Debugging.DisplayTokens<Token>(tokens);

            Assert.IsTrue(tokens.OfType<RangeMarkerToken>().Count() == 8);
        }

        [TestMethod]
        public void SimpleRangeContainmentTest()
        {
            // Three qutotations
            IEnumerable<Token> tokens = Tokenizer.Tokenize(@" [These 8.0 tokens are inside of the range] this 5 tokens are not")
                .Tokens;

            Console.WriteLine("All Tokens");
            Debugging.DisplayTokens<Token>(tokens);

            Console.WriteLine(Environment.NewLine);
            Console.WriteLine("Primitive Tokens");
            Debugging.DisplayTokens<Token>(tokens.OfType<PrimitiveToken>());

            // There should only be one range token [...]
            OpenBracketToken openBrace = tokens.OfType<OpenBracketToken>().First();
            CloseBracketToken closeBrace = tokens.OfType<CloseBracketToken>().First();

            IEnumerable<Token> containedPrimitiveTokens = tokens
                .OfType<PrimitiveToken>()
                .Where(token => TokenRange.Contains(openBrace, closeBrace, token));

            IEnumerable<Token> uncontainedPrimitiveTokens = tokens
                .OfType<PrimitiveToken>()
                .Where(token => !TokenRange.Contains(openBrace, closeBrace, token));

            IEnumerable<Token> containedIdentifierTokens = tokens
                .OfType<IdentifierToken>()
                .Where(token => TokenRange.Contains(openBrace, closeBrace, token));

            IEnumerable<Token> uncontainedIdentifierTokens = tokens
                .OfType<IdentifierToken>()
                .Where(token => !TokenRange.Contains(openBrace, closeBrace, token));

            Console.WriteLine(Environment.NewLine);
            Console.WriteLine("Contained Primitive Tokens");
            Debugging.DisplayTokens<Token>(containedPrimitiveTokens);

            Console.WriteLine(Environment.NewLine);
            Console.WriteLine("Uncontained Primitive Tokens");
            Debugging.DisplayTokens<Token>(uncontainedPrimitiveTokens);
            
            Console.WriteLine(Environment.NewLine);
            Console.WriteLine("Contained Identifiers");
            Debugging.DisplayTokens<Token>(containedIdentifierTokens);

            Console.WriteLine(Environment.NewLine);
            Console.WriteLine("Uncontained Identifiers");
            Debugging.DisplayTokens<Token>(uncontainedIdentifierTokens);

            Assert.IsTrue(containedPrimitiveTokens.Count() == 1);
            Assert.IsTrue(uncontainedPrimitiveTokens.Count() == 1);
            Assert.IsTrue(containedIdentifierTokens.Count() == 7);
            Assert.IsTrue(uncontainedIdentifierTokens.Count() == 4);

        }

        [TestMethod]
        public void SimpleNumericTest()
        {
            IEnumerable<Token> tokens = Tokenizer.Tokenize("a 1 2 2.567 3.890 b 4.567 c  d e")
                .Tokens
                .OfType<NumericToken>();

            Debugging.DisplayTokens<Token>(tokens);

            Assert.IsTrue(tokens.OfType<NumericToken>().Count() == 5);
            Assert.IsTrue(tokens.OfType<Int32Token>().Count() == 2);
            Assert.IsTrue(tokens.OfType<DoubleToken>().Count() == 3);
        }
    }
}
