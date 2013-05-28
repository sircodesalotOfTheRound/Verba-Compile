using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace VerbaCompile.Tokens.Keywords
{
    internal class ProtectedKeywordToken : KeywordToken
    {
        public ProtectedKeywordToken(Match match)
            : base(KeywordToken.PROTECTED_KEYWORD, match.Index)
        {

        }
    }
}
