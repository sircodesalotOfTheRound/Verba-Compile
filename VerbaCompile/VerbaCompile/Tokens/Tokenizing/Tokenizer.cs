using System;
using System.Collections.Generic;
using VerbaCompile.Tokens.Tokenizing.Parsers;

namespace VerbaCompile.Tokens.Tokenizing
{
    internal class Tokenizer
    {
        public static TokenizedSource Tokenize(String source)
        {
            List<Token> totalTokens = new List<Token>();
            BasicTokenParser basicTokenParser = new BasicTokenParser(source);

            QuotationRangeParser quotationRanges = new QuotationRangeParser(source, basicTokenParser.Tokens);
            ParentasisRangeParser parentasisRanges = new ParentasisRangeParser(source, basicTokenParser.Tokens);
            BracketRangeParser bracketRanges = new BracketRangeParser(source, basicTokenParser.Tokens);
            BraceRangeParser braceRanges = new BraceRangeParser(source, basicTokenParser.Tokens);

            totalTokens.AddRange(basicTokenParser.Tokens);
            totalTokens.AddRange(quotationRanges.QuotationRanges);
            totalTokens.AddRange(parentasisRanges.ParentasisRanges);
            totalTokens.AddRange(bracketRanges.BracketRanges);
            totalTokens.AddRange(braceRanges.BraceRanges);

            return new TokenizedSource(totalTokens);
        }
    }
}
