using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using VerbaCompile.Tokens;
using VerbaCompile.Tokens.Modules;
using VerbaCompile.Tokens.Range;
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

            // Put the tokens in order
            IEnumerable<Token> orderedTokens = tokens.OrderBy(token => token.Index);

            this.Root = BuildNode(root, tokens);
        }

        private TokenSourceNode BuildNode(TokenSourceNode parent, IEnumerable<Token> tokens)
        {
            IEnumerable<TokenSourceNode> nodes = tokens.Select(token =>
            {
                if (token is RangeToken)
                {
                    RangeToken rangeToken = (RangeToken)token;
                    TokenSourceNode rangeTokenNode = new TokenSourceNode(rangeToken);

                    // Take the following subset where the tokens are in range
                    IEnumerable<Token> tokensWithinRange = tokens
                        .SkipWhile(nextToken => rangeToken.Range.Contains(nextToken) == false )
                        .TakeWhile(nextToken => rangeToken.Range.Contains(nextToken))
                        .ToList();

                    return BuildNode(rangeTokenNode, tokensWithinRange); 
                }

                return new TokenSourceNode(token);
            });

            foreach (TokenSourceNode node in nodes)
            {
                // If this node is not already part of a tree
                if (node.Parent == null)
                {
                    parent.AddChild(node);
                }
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
