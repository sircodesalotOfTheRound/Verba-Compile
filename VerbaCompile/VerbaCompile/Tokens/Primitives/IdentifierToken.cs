using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VerbaCompile.Tokens.Primitives
{
    public class IdentifierToken : Token
    {
        public IdentifierToken(String textValue)
            : base(textValue)
        {
            
        }

        public static IdentifierToken Parse(String textValue)
        {
            return new IdentifierToken(textValue);
        }
    }
}
