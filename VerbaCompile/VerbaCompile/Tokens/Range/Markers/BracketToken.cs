using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VerbaCompile.Tokens.Range.Marker
{
    internal abstract class BracketMarkerToken : RangeMarkerToken
    {
        public BracketMarkerToken(String textValue, Int32 index)
            : base(textValue, index)
        {

        }
    }

    internal class OpenBracketToken : BracketMarkerToken
    {
        public OpenBracketToken(Int32 index)
            : base(RangeMarkerToken.OPEN_BRACKET, index)
        {

        }
    }

    internal class CloseBracketToken : BracketMarkerToken
    {
        public CloseBracketToken(Int32 index)
            : base(RangeMarkerToken.CLOSE_BRACKET, index)
        {

        }
    }
}
