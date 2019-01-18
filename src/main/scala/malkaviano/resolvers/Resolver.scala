package malkaviano.resolvers

import malkaviano.operators.BooleanOperator

trait Resolver {
  def tokens(json: String): Seq[Any]

  def expression(tokens: Seq[Any]): BooleanOperator

  def setProxiedObject(obj: Any): Unit
}