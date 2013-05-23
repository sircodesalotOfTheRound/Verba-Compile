using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace VerbaCompile.Tokens.Operators
{
    public class OperatorToken : Token 
    {
        public OperatorToken(String textValue)
            : base(textValue)
        {

        }

        public static OperatorToken Parse(String textValue)
        {
            if (textValue.Equals("="))
                return new AssignmentToken(textValue);
            
            if (textValue.Equals("+"))
                return new AdditionToken(textValue);

            if (textValue.Equals("-"))
                return new SubtractionToken(textValue);

            if (textValue.Equals("*"))
                return new MultiplicationToken(textValue);

            if (textValue.Equals("/"))
                return new DivisionToken(textValue);

            if (textValue.Equals("%"))
                return new ModulusToken(textValue);

            throw new NotImplementedException();
        }
    }
}
