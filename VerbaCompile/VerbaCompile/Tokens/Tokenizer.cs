using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using VerbaCompile.Tokens.Primitives;
using VerbaCompile.Tokens.Primitives.Numeric;

namespace VerbaCompile.Tokens
{
    public class Tokenizer
    {
        public static IEnumerable<Token> Tokenize(String text)
        {
            List<Token> tokens = new List<Token>();

            // Parse the numeric tokens
            IEnumerable<Token> numericTokens = ParseTokenset(text, @"\d+(\.\d+)?", match => NumericToken.Parse(match.Value))
                .ToArray();

            IEnumerable<Token> identifierTokens = ParseTokenset(text, @"\b(?'TOKEN'[a-zA-Z]+([\.\d+])?)\b", match => IdentifierToken.Parse(match.Groups["TOKEN"].Value))
                    .Where(identifierToken => numericTokens.Any(numericToken => numericToken.TextValue == identifierToken.TextValue) == false)
                    .ToArray();

            tokens.AddRange(numericTokens);
            tokens.AddRange(identifierTokens);

            return tokens;
        }

        private static IEnumerable<Token> ParseTokenset(String text, String pattern, Func<Match, Token> projection)
        {
            return new Regex(pattern)
                .Matches(text)
                .Cast<Match>()
                .Select(projection);
        }
    }
}
