using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using VerbaCompile.ExpressionTrees.Builder.ExpressionBuilders;
using VerbaCompile.ExpressionTrees.Expressions;
using VerbaCompile.Tokens;
using VerbaCompile.Tokens.Primitives.Numeric;
using VerbaCompile.TokenTree;

namespace VerbaCompile.ExpressionTrees
{
    internal class ExpressionTreeBuilder
    {
        public ExpressionTree Tree { get; private set; }

        public ExpressionTreeBuilder(TokenSourceTree sourceTree)
        {
            foreach (TokenSourceNode node in sourceTree.Root.Children)
            {
                BuildExpression((dynamic)node.Token);
            }
        }

        public Expression BuildExpression(NumericToken token)
        {
            NumericExpressionBuilder builder = new NumericExpressionBuilder(token);
            return builder.Expression;
        }
    }
}
