using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VerbaCompile.Tokens
{
    internal class TokenRange
    {
        public Int32 Start { get; private set; }
        public Int32 End { get; private set; }
        public Int32 Length { get { return (this.End - this.Start); } }

        public TokenRange(Token token)
        {
            this.Start = token.Index;
            this.End = (token.Index + token.TextValue.Length);
        }

        private TokenRange(Int32 start, Int32 end)
        {
            this.Start = start;
            this.End = end;
        }

        public Boolean Contains(Int32 index)
        {
            return (index > this.Start)
                && (index < this.End);
        }

        public Boolean Contains(Int32 start, Int32 end)
        {
            return (start > this.Start)
                && (end < this.End);
        }

        public Boolean Contains(TokenRange range)
        {
            return this.Contains(range.Start)
                && this.Contains(range.End);
        }

        public Boolean Contains(Token token)
        {
            return this.Contains(token.Range);
        }

        // Static versions
        public static Boolean Contains(Int32 rangeStart, Int32 rangeEnd, Token token)
        {
            TokenRange range = new TokenRange(rangeStart, rangeEnd);
            return range.Contains(token);
        }

        public static Boolean Contains(Token startToken, Token endToken, Token test)
        {
            return TokenRange.Contains(startToken.Range.Start, endToken.Range.End, test);
        }
    }
}
