using System;
using System.Collections.Generic;
using VerbaCompile.Tokens.Tokenizing.Parsers;

namespace VerbaCompile.Tokens.Tokenizing
{
    internal class Tokenizer
    {
        public static TokenizedSource Tokenize(String source)
        {
            BasicTokenParser basicTokenParser = new BasicTokenParser(source);
            QuotationRangeParser quotationRanges = new QuotationRangeParser(source, basicTokenParser.Tokens);
            return new TokenizedSource(basicTokenParser.Tokens);
        }
    }
}
