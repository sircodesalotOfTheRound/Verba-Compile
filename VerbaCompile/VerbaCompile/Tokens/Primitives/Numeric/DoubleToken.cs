using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace VerbaCompile.Tokens.Primitives.Numeric
{
    internal class DoubleToken : NumericToken
    {
        public DoubleToken(Match match)
            : base(match.Value, match.Index)
        {

        }
    }
}
