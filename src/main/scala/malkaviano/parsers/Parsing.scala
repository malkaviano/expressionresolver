package malkaviano.parsers

import malkaviano.operators.BooleanOperator

trait Parsing {
  def parse(tokens: Seq[Any]): BooleanOperator

  def changeProxiedObject(obj: Any): Unit
}
