using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VerbaCompile.ExpressionTrees.Expressions.Primitives.Numeric
{
    internal class Int32Expression : NumericExpression
    {
        public Int32 Value { get; private set; }

        public Int32Expression(Int32 value)
        {
            this.Value = value;
        }
    }
}
