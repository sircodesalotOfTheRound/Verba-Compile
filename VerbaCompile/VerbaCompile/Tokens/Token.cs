using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using VerbaCompile.Tokens.Primitives;

namespace VerbaCompile.Tokens
{
    public abstract class Token
    {
        public String TextValue { get; private set; }

        public Token(String textValue)
        {
            this.TextValue = textValue;
        }
    }
}
