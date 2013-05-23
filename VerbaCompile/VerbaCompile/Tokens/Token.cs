using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using VerbaCompile.Tokens.Primitives;

namespace VerbaCompile.Tokens
{
    internal abstract class Token
    {
        public String TextValue { get; private set; }
        public Int32 Index { get; private set; }
        public TokenRange Range { get; private set; }

        public Token(String textValue, Int32 index)
        {
            this.TextValue = textValue;
            this.Index = index;

            this.Range = new TokenRange(this);
        }
    }
}
