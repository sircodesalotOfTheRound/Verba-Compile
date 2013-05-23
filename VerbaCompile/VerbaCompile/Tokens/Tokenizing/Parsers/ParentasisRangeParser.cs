using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using VerbaCompile.Tokens.Range;
using VerbaCompile.Tokens.Range.Marker;

namespace VerbaCompile.Tokens.Tokenizing.Parsers
{
    internal class ParentasisRangeParser : 
        StackBasedRangeParser<ParentasisRangeToken, ParentasisMarkerToken, OpenParentasisToken, CloseParentasisToken>
    {
         public IEnumerable<ParentasisRangeToken> ParentasisRanges { get; private set; }

         public ParentasisRangeParser(String source, IEnumerable<Token> tokens)
             : base (source, tokens)
         {
             this.ParentasisRanges = base.Ranges.Cast<ParentasisRangeToken>();      
         }
    }
}
