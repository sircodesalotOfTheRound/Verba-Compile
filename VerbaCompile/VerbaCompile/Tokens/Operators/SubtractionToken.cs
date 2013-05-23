using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace VerbaCompile.Tokens.Operators
{
    internal class SubtractionToken : OperatorToken
    {
        public SubtractionToken(Match match)
            : base(match)
        {

        }
    }
}
