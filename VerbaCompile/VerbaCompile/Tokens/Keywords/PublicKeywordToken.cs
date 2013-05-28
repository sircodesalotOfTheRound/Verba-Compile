using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace VerbaCompile.Tokens.Keywords
{
    internal class PublicKeywordToken : KeywordToken
    {
        public PublicKeywordToken(Match match)
            : base(KeywordToken.PUBLIC_KEYWORD, match.Index)
        {

        }

    }
}
