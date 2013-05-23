using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace VerbaCompile.Tokens.Primitives
{
    internal class IdentifierToken : Token
    {
        public IdentifierToken(Match match)
            : base(match.Value, match.Index)
        {
            
        }

        public static IdentifierToken Parse(Match match)
        {
            return new IdentifierToken(match);
        }
    }
}
