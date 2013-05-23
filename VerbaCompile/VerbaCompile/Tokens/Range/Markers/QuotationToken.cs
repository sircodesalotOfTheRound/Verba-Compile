using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VerbaCompile.Tokens.Range.Marker
{
    internal class QuotationMarkerToken : RangeMarkerToken
    {
        public QuotationMarkerToken(Int32 index)
            : base(RangeMarkerToken.QUOTATION_MARK, index)
        {

        }
    }
}
