using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using VerbaCompile.ExpressionTrees.Expressions.Primitives.Numeric;
using VerbaCompile.Tokens.Primitives.Numeric;

namespace VerbaCompile.ExpressionTrees.Builder.ExpressionBuilders
{
    internal class NumericExpressionBuilder : ExpressionBuilder
    {
        public NumericExpression Expression { get; private set; }

        public NumericExpressionBuilder(NumericToken token)
        {
            this.Expression = new Int32Expression(Int32.Parse(token.TextValue));
        }
    }
}
