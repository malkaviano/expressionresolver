package malkaviano.resolvers

import malkaviano.operators.BooleanOperator

object BooleanExpressionResolver {

  def resolve(expression: BooleanOperator): Boolean = expression.evaluate
}
