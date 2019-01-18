package malkaviano.resolvers

import malkaviano.parsers.{DefaultParser, Parsing}
import malkaviano.proxies.DefaultProxy
import malkaviano.tokenizers.{DefaultTokenizer, Tokenizer}

class ResolverBuilder private(
                               tokenizer: Tokenizer,
                               parser: Parsing
                             ) {
  def withParser(parser: Parsing): ResolverBuilder = new ResolverBuilder(this.tokenizer, parser)

  def withTokenizer(tokenizer: Tokenizer): ResolverBuilder = new ResolverBuilder(tokenizer, this.parser)

  def construct: Resolver = new DefaultResolver(tokenizer, parser)
}

object ResolverBuilder {
  def build: ResolverBuilder = {
    new ResolverBuilder(new DefaultTokenizer, new DefaultParser(new DefaultProxy))
  }
}