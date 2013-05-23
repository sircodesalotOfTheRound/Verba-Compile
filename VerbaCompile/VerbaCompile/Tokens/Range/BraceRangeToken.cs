using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using VerbaCompile.Tokens.Range.Marker;

namespace VerbaCompile.Tokens.Range
{
    internal class BraceRangeToken : RangeToken
    {
        public BraceRangeToken(BraceMarkerToken start, BraceMarkerToken end, String innerText)
            : base(innerText, start.Index)
        {

        }
    }
}
