using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using VerbaCompile.Tokens.Tokenizing;
using VerbaCompile.TokenTree;
using VerbaCompileTest.Tools;

namespace VerbaCompileTest.TokenTree
{
    [TestClass]
    public class TokenTree
    {
        [TestMethod]
        public void TokenTreeConstruction()
        {
            String source = "1.0{123[456(789)A]B}C";
            TokenizedSource tokenizedSource = Tokenizer.Tokenize(source);
            TokenSourceTree tree = TokenSourceTree.Build(tokenizedSource);

            Console.WriteLine(source);
            
            Debugging.DisplayTree(tree);
        }


    }
}
