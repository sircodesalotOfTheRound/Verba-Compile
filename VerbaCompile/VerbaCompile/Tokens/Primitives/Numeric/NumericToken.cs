using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace VerbaCompile.Tokens.Primitives.Numeric
{
    internal abstract class NumericToken : PrimitiveToken
    {
        public NumericToken(String textValue, Int32 index)
            : base(textValue, index)
        {

        }

        public static NumericToken Parse(Match match)
        {
            String textValue = match.Value;

            if (Regex.Match(textValue, @"\d+\.\d+").Success)
            {
                return new DoubleToken(match);
            }

            return new Int32Token(match);
        }
    }
}
