using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VerbaCompile.Tokens.Primitives.Numeric
{
    public class Int32Token : NumericToken
    {
        public Int32 Value { get; private set; }
        public Int32Token(String textValue)
            : base(textValue)
        {
            this.Value = Int32.Parse(textValue);
        }
    }
}
