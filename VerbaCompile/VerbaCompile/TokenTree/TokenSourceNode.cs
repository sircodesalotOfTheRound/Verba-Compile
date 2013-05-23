using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using VerbaCompile.Tokens;

namespace VerbaCompile.TokenTree
{
    internal class TokenSourceNode
    {
        public Token Token { get; private set; }
        public TokenSourceNode Parent {get; private set; }
     
        public IEnumerable<TokenSourceNode> Children { get; private set; }

        public TokenSourceNode(Token token)
        {
            this.Token = token;
            this.Children = new List<TokenSourceNode>();
        }

        public TokenSourceNode AddChild(TokenSourceNode child)
        {
            child.Parent = child;
            ((List<TokenSourceNode>)this.Children).Add(child);

            return child;
        }

        public TokenSourceNode AddChild(Token token)
        {
            TokenSourceNode childNode = new TokenSourceNode(token);
            
            return AddChild(childNode);
        }
    }
}
