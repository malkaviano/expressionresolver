package malkaviano.resolvers

import malkaviano.parsers.DefaultParser
import malkaviano.tokenizers.DefaultTokenizer

class DefaultResolver(json: String) {
  val tokens = new DefaultTokenizer().generate(json)

  def result(obj: Any): Boolean = {
    val parser = new DefaultParser(obj).parse(tokens)

    parser.evaluate
  }
}
