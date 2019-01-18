package malkaviano.resolvers

import malkaviano.operators.BooleanOperator
import malkaviano.parsers.{Parsing}
import malkaviano.tokenizers.{Tokenizer}

class DefaultResolver(tokenizer: Tokenizer, parser: Parsing) extends Resolver {
  override def expression(json: String): BooleanOperator = parser.parse(tokenizer.generate(json))

  override def setProxiedObject(obj: Any): Unit = parser.changeProxiedObject(obj)
}