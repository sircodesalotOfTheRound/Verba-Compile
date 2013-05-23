using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VerbaCompile.Tokens.Tokenizing
{
    internal class TokenizedSource
    {
        public IEnumerable<Token> Tokens { get; private set; }

        public TokenizedSource(IEnumerable<Token> tokens)
        {
            this.Tokens = tokens.ToList();
        }
    }
}
