using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace VerbaCompile.Tokens.Range.Marker
{
    internal abstract class RangeMarkerToken : Token
    {
        public const String QUOTATION_MARK = @"""";
        public const String OPEN_PARENTASIS = @"(";
        public const String CLOSE_PARENTASIS = @")";
        public const String OPEN_BRACKET = @"[";
        public const String CLOSE_BRACKET = @"]";
        public const String OPEN_BRACE = @"{";
        public const String CLOSE_BRACE = @"}";
        
        public RangeMarkerToken(String textValue, Int32 index)
            : base(textValue, index)
        {

        }

        public static RangeMarkerToken Parse(Match match)
        {
            String textValue = match.Value;
            Int32 index = match.Index;

            // Quotations
            if (textValue.Equals(RangeMarkerToken.QUOTATION_MARK))
                return new QuotationMarkerToken(index);

            // Parentasis
            if (textValue.Equals(RangeMarkerToken.OPEN_PARENTASIS))
                return new OpenParentasisToken(index);

            if (textValue.Equals(RangeMarkerToken.CLOSE_PARENTASIS))
                return new CloseParentasisToken(index);

            // Brackets
            if (textValue.Equals(RangeMarkerToken.OPEN_BRACKET))
                return new OpenBracketToken(index);

            if (textValue.Equals(RangeMarkerToken.CLOSE_BRACKET))
                return new CloseBracketToken(index);

            // Braces
            if (textValue.Equals(RangeMarkerToken.OPEN_BRACE))
                return new OpenBraceToken(index);

            if (textValue.Equals(RangeMarkerToken.CLOSE_BRACE))
                return new CloseBraceToken(index);

            
            

            throw new NotImplementedException();
        }
    }
}
