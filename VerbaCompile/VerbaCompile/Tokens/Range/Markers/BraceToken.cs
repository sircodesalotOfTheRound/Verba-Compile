using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VerbaCompile.Tokens.Range.Marker
{
    internal abstract class BraceMarkerToken : RangeMarkerToken
    {
        public BraceMarkerToken(String textValue, Int32 index)
            : base(textValue, index)
        {

        }
    }

    internal class OpenBraceToken : BraceMarkerToken
    {
        public OpenBraceToken(Int32 index)
            : base(RangeMarkerToken.OPEN_BRACE, index)
        {

        }
    }

    internal class CloseBraceToken : BraceMarkerToken
    {
        public CloseBraceToken(Int32 index)
            : base(RangeMarkerToken.CLOSE_BRACE, index)
        {

        }
    }
}
