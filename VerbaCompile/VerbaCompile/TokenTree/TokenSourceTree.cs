using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using VerbaCompile.Tokens;
using VerbaCompile.Tokens.Modules;
using VerbaCompile.Tokens.Range;
using VerbaCompile.Tokens.Range.Marker;
using VerbaCompile.Tokens.Tokenizing;

namespace VerbaCompile.TokenTree
{
    internal class TokenSourceTree
    {
        public TokenSourceNode Root { get; private set; }

        private TokenSourceTree(IEnumerable<Token> tokens)
        {
            AssemblyToken asmToken = new AssemblyToken();
            TokenSourceNode root = new TokenSourceNode(asmToken);

            // (1) Filter marking tokens
            // (2) Order tokens by if they are range tokens
            // (3) Order tokens by their range length
            // (3) Then by index
            IEnumerable<TokenSourceNode> orderedTokens = tokens
                .Where(token => (token is RangeMarkerToken) == false)
                .OrderBy(token => token.Index)
                .Select(token => new TokenSourceNode(token))
                .ToList();

            this.Root = BuildNode(root, orderedTokens);
        }

        private TokenSourceNode BuildNode(TokenSourceNode parent, IEnumerable<TokenSourceNode> nodes)
        {
            foreach (TokenSourceNode node in nodes)
            {
                List<TokenSourceNode> childNodes = nodes
                    .SkipWhile(tokenNode => node.Token.Range.Contains(tokenNode.Token) == false)
                    .TakeWhile(tokenNode => node.Token.Range.Contains(tokenNode.Token))
                    .OrderBy(tokenNode => tokenNode.Token.Index)
                    .ToList();

                if (childNodes.Count > 0)
                {
                    BuildNode(node, childNodes);
                }

                if (node.Parent == null)
                    parent.AddChild(node);
            }

            return parent;
        }

        public static TokenSourceTree Build(TokenizedSource tokenizedSource)
        {
            // Build a source tree after having already sorted the tokens by start position
            return new TokenSourceTree(tokenizedSource.Tokens);
        }
    }
}
