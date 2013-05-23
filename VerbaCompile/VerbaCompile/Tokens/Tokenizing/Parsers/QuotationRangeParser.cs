using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using VerbaCompile.Tokens.Range;
using VerbaCompile.Tokens.Range.Marker;

namespace VerbaCompile.Tokens.Tokenizing.Parsers
{
    internal class QuotationRangeParser
    {
        public IEnumerable<QuotationRangeToken> QuotationRanges { get; private set; }

        public QuotationRangeParser(String source, IEnumerable<Token> tokens)
        {
            List<QuotationRangeToken> quotationRanges = new List<QuotationRangeToken>();
            QuotationMarkerToken[] quotationMarkers = tokens
                .OfType<QuotationMarkerToken>()
                .ToArray();

            Int32 totalQuotationMarkers = quotationMarkers.Count();
            
            // If there are an odd number of quotations,
            // throw an exception for now.
            if (totalQuotationMarkers % 2 != 0)
                throw new ArgumentOutOfRangeException();

            for (Int32 index = 0; index < totalQuotationMarkers; index += 2)
            {
                QuotationMarkerToken beginQuote = quotationMarkers[index];
                QuotationMarkerToken endQuote = quotationMarkers[index+1];

                String innerText = source.Substring(beginQuote.Index + 1, (endQuote.Index - beginQuote.Index - 1));

                QuotationRangeToken quotationRange = new QuotationRangeToken(beginQuote, endQuote, innerText);
                
                quotationRanges.Add(quotationRange);
            }

            this.QuotationRanges = quotationRanges;
        }
    }
}
