using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using VerbaCompile.Tokens.Range;
using VerbaCompile.Tokens.Range.Marker;

namespace VerbaCompile.Tokens.Tokenizing.Parsers
{
    internal class BraceRangeParser : 
        StackBasedRangeParser<BraceRangeToken, BraceMarkerToken, OpenBraceToken, CloseBraceToken>
    {
        public IEnumerable<BraceRangeToken> BraceRanges { get; private set; }

         public BraceRangeParser(String source, IEnumerable<Token> tokens)
             : base (source, tokens)
         {
             this.BraceRanges = base.Ranges.Cast<BraceRangeToken>();      
         }
    }
}
