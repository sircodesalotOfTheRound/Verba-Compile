using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using VerbaCompile.Tokens.Range.Marker;

namespace VerbaCompile.Tokens.Range
{
    internal class ParentasisRangeToken : RangeToken
    {
        public ParentasisRangeToken(ParentasisMarkerToken start, ParentasisMarkerToken end, String innerText)
            : base(innerText, start.Index)
        {

        }
    }
}
