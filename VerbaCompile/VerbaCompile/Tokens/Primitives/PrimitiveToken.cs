using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VerbaCompile.Tokens.Primitives
{
    public abstract class PrimitiveToken : Token
    {
        public PrimitiveToken(String textValue)
            : base (textValue) { }
    }
}
