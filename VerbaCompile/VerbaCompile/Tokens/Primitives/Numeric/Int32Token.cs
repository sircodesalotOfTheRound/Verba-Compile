using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace VerbaCompile.Tokens.Primitives.Numeric
{
    internal class Int32Token : NumericToken
    {
        public Int32 Value { get; private set; }

        public Int32Token(Match match)
            : base(match.Value, match.Index)
        {
            this.Value = Int32.Parse(base.TextValue);
        }
    }
}
