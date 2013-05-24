using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using VerbaCompile.Tokens.Tokenizing;
using VerbaCompile.TokenTree;
using VerbaCompile.ExpressionTrees;

namespace VerbaCompileTest.Expressions
{
    [TestClass]
    public class SimpleExpressionTests
    {
        [TestMethod]
        public void SimpleNumericExpressionTest()
        {
            TokenizedSource tokenizedSource = Tokenizer.Tokenize("5 3");
            TokenSourceTree tokenSourceTree = TokenSourceTree.Build(tokenizedSource);
            ExpressionTree expressionTree = ExpressionTree.Build(tokenSourceTree);
        }
    }
}
