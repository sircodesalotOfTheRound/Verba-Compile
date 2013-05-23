using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VerbaCompile.Tokens.Modules
{
    internal class ModuleToken : Token
    {
        public ModuleToken(String textValue, Int32 index)
            : base(textValue, index)
        {

        }
    }
}
