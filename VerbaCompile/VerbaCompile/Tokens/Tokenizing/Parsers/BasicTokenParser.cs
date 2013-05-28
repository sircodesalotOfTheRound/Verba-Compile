using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using VerbaCompile.Tokens.Keywords;
using VerbaCompile.Tokens.Operators;
using VerbaCompile.Tokens.Primitives;
using VerbaCompile.Tokens.Primitives.Numeric;
using VerbaCompile.Tokens.Range.Marker;

namespace VerbaCompile.Tokens.Tokenizing.Parsers
{
    internal class BasicTokenParser
    {
        public IEnumerable<Token> Tokens { get; private set; }

        public BasicTokenParser(String source)
        {
            List<Token> tokens = new List<Token>();

            // Parse keywords
            IEnumerable<String> keywordsList = KeywordToken.Keywords
                .Select(keyword => String.Format("({0})", keyword));

            String keywordList = String.Format(@"\b({0})\b",
                String.Join("|", keywordsList));

            IEnumerable<Token> keywordTokens = ParseTokenset(source, keywordList, match => KeywordToken.Parse(match))
                    .ToArray();

            // Parse range tokens
            IEnumerable<Token> rangeTokens = ParseTokenset(source, @"[""{}()\[\]]", match => RangeMarkerToken.Parse(match))
                    .ToArray();

            // Parse the numeric tokens
            IEnumerable<Token> numericTokens = ParseTokenset(source, @"\b\d+(\.\d+)?\b", match => NumericToken.Parse(match))
                .ToArray();

            // Parse identifier tokens
            IEnumerable<Token> identifierTokens = ParseTokenset(source, @"\b[a-zA-Z]+([\.\d+])?\b", match => IdentifierToken.Parse(match))
                    .Where(identifierToken => numericTokens.Any(numericToken => numericToken.TextValue == identifierToken.TextValue) == false)
                    .Where(identifierToken => KeywordToken.Keywords.Contains(identifierToken.TextValue) == false)
                    .ToArray();

            // Parse operator tokens
            IEnumerable<Token> operatorTokens = ParseTokenset(source, @"[=\+\-/*%]", match => OperatorToken.Parse(match))
                    .ToArray();


            tokens.AddRange(keywordTokens);
            tokens.AddRange(numericTokens);
            tokens.AddRange(identifierTokens);
            tokens.AddRange(operatorTokens);
            tokens.AddRange(rangeTokens);

            this.Tokens = tokens;
        }

        private IEnumerable<Token> ParseTokenset(String text, String pattern, Func<Match, Token> projection)
        {
            return new Regex(pattern)
                .Matches(text)
                .Cast<Match>()
                .Select(projection);
        }
    }
}
