using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace VerbaCompile.Tokens.Keywords
{
    internal class PrivateKeywordToken : KeywordToken
    {
        public PrivateKeywordToken(Match match)
            : base(KeywordToken.PRIVATE_KEYWORD, match.Index)
        {

        }

    }
}
