using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using VerbaCompile.Tokens.Keywords;
using VerbaCompile.Tokens.Tokenizing;
using System.Diagnostics;
using VerbaCompileTest.Tools;

namespace VerbaCompileTest.Tokenizing
{
    [TestClass]
    public class BasicKeywordTesting
    {
        [TestMethod]
        public void TestProtectionLevels()
        {
            TokenizedSource tokenizedSource = Tokenizer.Tokenize("public protected private");

            Debugging.DisplayTokens(tokenizedSource.Tokens);
        }
    }
}
