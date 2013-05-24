using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using VerbaCompile.Tokens.Range;

namespace VerbaCompile.Tokens.Tokenizing
{
    internal class TokenizedSource
    {
        public IEnumerable<Token> Tokens { get; private set; }
        public IEnumerable<RangeToken> RangeTokens { get; private set; }

        public TokenizedSource(IEnumerable<Token> tokens)
        {
            this.Tokens = tokens.ToList();
            this.RangeTokens = tokens.OfType<RangeToken>().ToList();
        }
    }
}
