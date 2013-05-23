using Microsoft.VisualStudio.TestTools.UnitTesting;
using System.Collections.Generic;
using VerbaCompile.Tokens.Range;
using VerbaCompile.Tokens.Tokenizing;
using System.Linq;
using System;
using VerbaCompileTest.Tools;

namespace VerbaCompileTest.Tokenizing
{
    [TestClass]
    public class RangeCompilation
    {
        [TestMethod]
        public void BraceRangeTest()
        {
            TokenizedSource source = Tokenizer.Tokenize(@" Out {One[Two{Three[4.0(5 + 0)]}]}");
            IEnumerable<BraceRangeToken> parentasisRanges = source.Tokens.OfType<BraceRangeToken>();

            Debugger.DisplayRanges<BraceRangeToken>(parentasisRanges);
        }

        [TestMethod]
        public void BracketRangeTest()
        {
            TokenizedSource source = Tokenizer.Tokenize(@" Out [One[Two[Three[4.0(5 + 0)]]]]");
            IEnumerable<BracketRangeToken> parentasisRanges = source.Tokens.OfType<BracketRangeToken>();

            Debugger.DisplayRanges<BracketRangeToken>(parentasisRanges);
        }


        [TestMethod]
        public void ParentasisRangeTest()
        {
            TokenizedSource source = Tokenizer.Tokenize(@" Out (One(Two(Three(4.0(5 + 0)))))");
            IEnumerable<ParentasisRangeToken> parentasisRanges = source.Tokens.OfType<ParentasisRangeToken>();

            Debugger.DisplayRanges<ParentasisRangeToken>(parentasisRanges);
        }


        [TestMethod]
        public void QuotationRangeTest()
        {
            TokenizedSource source = Tokenizer.Tokenize(@" Block 1: ""Quoted text 123"" Block 2: ""Second Region 1.5"" End");
            IEnumerable<QuotationRangeToken> quotationRanges = source.Tokens.OfType<QuotationRangeToken>();

            Debugger.DisplayRanges<QuotationRangeToken>(quotationRanges);
        }
    }
}
