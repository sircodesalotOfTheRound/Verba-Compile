using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using VerbaCompile.ExpressionTrees.Expressions;
using VerbaCompile.TokenTree;

namespace VerbaCompile.ExpressionTrees
{
    internal class ExpressionTree
    {
        public Expression Root { get; private set; }

        public ExpressionTree(Expression root)
        {
            this.Root = root;
        }

        public static ExpressionTree Build(TokenSourceTree sourceTree)
        {
            ExpressionTreeBuilder builder = new ExpressionTreeBuilder(sourceTree);
            return builder.Tree;
        }
    }
}
