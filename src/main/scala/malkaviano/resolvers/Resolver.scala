package malkaviano.resolvers

import malkaviano.operators.BooleanOperator

trait Resolver {
  def expression(json: String): BooleanOperator

  def setProxiedObject(obj: Any): Unit
}