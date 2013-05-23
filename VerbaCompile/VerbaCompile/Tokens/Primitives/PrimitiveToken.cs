using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace VerbaCompile.Tokens.Primitives
{
    internal abstract class PrimitiveToken : Token
    {
        public PrimitiveToken(String textValue, Int32 index)
            : base (textValue, index) { }
    }
}
