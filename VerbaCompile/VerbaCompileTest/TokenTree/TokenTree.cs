using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using VerbaCompile.Tokens.Tokenizing;
using VerbaCompile.TokenTree;

namespace VerbaCompileTest.TokenTree
{
    [TestClass]
    public class TokenTree
    {
        [TestMethod]
        public void TokenTreeConstruction()
        {
            TokenizedSource tokenizedSource = Tokenizer.Tokenize(" { 123 [ 456 ( 789 ) A ] B } C");
            TokenSourceTree tree = TokenSourceTree.Build(tokenizedSource);
        }


    }
}
