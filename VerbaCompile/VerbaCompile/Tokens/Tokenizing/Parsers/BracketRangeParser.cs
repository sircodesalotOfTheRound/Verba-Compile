using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using VerbaCompile.Tokens.Range;
using VerbaCompile.Tokens.Range.Marker;

namespace VerbaCompile.Tokens.Tokenizing.Parsers
{
    internal class BracketRangeParser : 
        StackBasedRangeParser<BracketRangeToken, BracketMarkerToken, OpenBracketToken, CloseBracketToken>
    {
        public IEnumerable<BracketRangeToken> BracketRanges { get; private set; }

         public BracketRangeParser(String source, IEnumerable<Token> tokens)
             : base (source, tokens)
         {
             this.BracketRanges = base.Ranges.Cast<BracketRangeToken>();      
         }
    }
}
