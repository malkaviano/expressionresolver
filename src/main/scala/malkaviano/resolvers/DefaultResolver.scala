package malkaviano.resolvers

import malkaviano.parsers.DefaultParser
import malkaviano.proxies.{DefaultProxy}
import malkaviano.tokenizers.DefaultTokenizer

class DefaultResolver(json: String) {
  val tokens = new DefaultTokenizer().generate(json)
  val proxy = new DefaultProxy
  val parser = new DefaultParser(proxy).parse(tokens)

  def resolve: Boolean = parser.evaluate

  def changeProxied(obj: Any): Unit = proxy.setObject(obj)
}
