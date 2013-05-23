using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using VerbaCompile.Tokens.Range;
using VerbaCompile.Tokens.Range.Marker;

namespace VerbaCompile.Tokens.Tokenizing.Parsers
{
    internal class StackBasedRangeParser<TRangeType, TMarkerBaseType, TMarkerOpenType, TMarkerCloseType>
        where TRangeType : RangeToken
        where TMarkerBaseType : RangeMarkerToken
        where TMarkerOpenType : TMarkerBaseType
        where TMarkerCloseType : TMarkerBaseType
    {
        protected IEnumerable<TRangeType> Ranges { get; private set; }

        public StackBasedRangeParser(String source, IEnumerable<Token> tokens)
        {
            List<TRangeType> ranges = new List<TRangeType>();
            TMarkerBaseType[] markers = tokens
                .OfType<TMarkerBaseType>()
                .ToArray();

            Stack<TMarkerOpenType> openMarkerStack = new Stack<TMarkerOpenType>();

            foreach (TMarkerBaseType marker in markers)
            {
                if (marker is TMarkerOpenType)
                {
                    openMarkerStack.Push((TMarkerOpenType)marker);
                }

                if (marker is TMarkerCloseType)
                {
                    TMarkerOpenType openParentasis = openMarkerStack.Pop();
                    TMarkerCloseType closeParentasis = (TMarkerCloseType)marker;

                    String innerText = source
                        .Substring(openParentasis.Index + 1, (closeParentasis.Index - openParentasis.Index - 1));

                    TRangeType parentasisRange = (TRangeType)Activator
                        .CreateInstance(typeof(TRangeType), new Object[] { openParentasis, closeParentasis, innerText });

                    ranges.Add(parentasisRange);
                }
            }

            this.Ranges = ranges;
        }
    }
}
