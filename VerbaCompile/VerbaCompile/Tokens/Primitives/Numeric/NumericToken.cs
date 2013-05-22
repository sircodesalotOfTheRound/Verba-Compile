using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace VerbaCompile.Tokens.Primitives.Numeric
{
    public abstract class NumericToken : PrimitiveToken
    {
        public NumericToken(String textValue)
            : base(textValue)
        {

        }

        public static NumericToken Parse(String text)
        {
            if (Regex.Match(text, @"\d+\.\d+").Success)
            {
                return new DoubleToken(text);
            }

            return new Int32Token(text);
        }
    }
}
