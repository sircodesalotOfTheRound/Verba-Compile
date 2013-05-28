using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace VerbaCompile.Tokens.Keywords
{
    internal abstract class KeywordToken : Token
    {
        public const String PUBLIC_KEYWORD = "public";
        public const String PROTECTED_KEYWORD = "protected";
        public const String PRIVATE_KEYWORD = "private";
        
        public KeywordToken(String textValue, Int32 index)
            : base (textValue, index)
        {

        }

        private static IEnumerable<String> keywords = typeof(KeywordToken).GetFields()
            .Where(field => field.FieldType.Equals(typeof(String)))
            .Where(field => field.IsStatic)
            .Select(field => (String)field.GetValue(null))
            .ToArray();

        public static IEnumerable<String> Keywords
        {
            get
            {
                return keywords;
            }
        }

        public static KeywordToken Parse(Match match)
        {
            switch(match.Value)
            {
                case PUBLIC_KEYWORD:
                    return new PublicKeywordToken(match);

                case PROTECTED_KEYWORD:
                    return new ProtectedKeywordToken(match);

                case PRIVATE_KEYWORD:
                    return new PrivateKeywordToken(match);
            }

            throw new NotImplementedException();
        }
    }
}
