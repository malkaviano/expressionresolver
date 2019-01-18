package malkaviano.resolvers

import malkaviano.operators.BooleanOperator
import malkaviano.parsers.{Parsing}
import malkaviano.tokenizers.{Tokenizer}

class DefaultResolver(tokenizer: Tokenizer, parser: Parsing) extends Resolver {

  override def tokens(json: String): Seq[Any] = tokenizer.generate(json)

  override def expression(tokens: Seq[Any]): BooleanOperator = parser.parse(tokens)

  override def setProxiedObject(obj: Any): Unit = parser.changeProxiedObject(obj)
}