using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VerbaCompile.Tokens.Range
{
    internal class RangeToken : Token
    {
        public RangeToken(String textValue, Int32 index)
            : base(textValue, index)
        {

        }
    }
}
