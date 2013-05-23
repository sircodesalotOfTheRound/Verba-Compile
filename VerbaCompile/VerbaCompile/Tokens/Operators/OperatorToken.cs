using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace VerbaCompile.Tokens.Operators
{
    internal class OperatorToken : Token 
    {
        public OperatorToken(Match match)
            : base(match.Value, match.Index)
        {

        }

        public static OperatorToken Parse(Match match)
        {
            String textValue = match.Value;

            if (textValue.Equals("="))
                return new AssignmentToken(match);
            
            if (textValue.Equals("+"))
                return new AdditionToken(match);

            if (textValue.Equals("-"))
                return new SubtractionToken(match);

            if (textValue.Equals("*"))
                return new MultiplicationToken(match);

            if (textValue.Equals("/"))
                return new DivisionToken(match);

            if (textValue.Equals("%"))
                return new ModulusToken(match);

            throw new NotImplementedException();
        }
    }
}
