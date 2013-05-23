using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VerbaCompile.Tokens.Range.Marker
{
    internal abstract class ParentasisMarkerToken : RangeMarkerToken
    {
        public ParentasisMarkerToken(String textValue, Int32 index)
            : base (textValue, index)
        {

        }
    }

    internal class OpenParentasisToken : ParentasisMarkerToken
    {
        public OpenParentasisToken(Int32 index)
            : base(RangeMarkerToken.OPEN_PARENTASIS, index)
        {

        }
    }

    internal class CloseParentasisToken : ParentasisMarkerToken
    {
        public CloseParentasisToken(Int32 index)
            : base(RangeMarkerToken.CLOSE_PARENTASIS, index)
        {

        }
    }
}
