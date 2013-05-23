using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VerbaCompile.Tokens.Operators
{
    public class AssignmentToken : OperatorToken
    {
        public AssignmentToken(String textValue)
            : base(textValue)
        {

        }
    }
}
