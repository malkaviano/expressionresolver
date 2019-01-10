package malkaviano.resolvers

import malkaviano.operators.comparison.{EqualToOperator, LessThanOperator}
import malkaviano.operators.logical.{NotOperator, OrOperator}
import malkaviano.operators.property.ValueProperty
import malkaviano.operators.value.Literal
import org.joda.time.DateTime
import org.scalatest.{FunSpec, Matchers}

class BooleanExpressionResolverSpec extends FunSpec with Matchers {

  describe("Evaluation") {
    /*case class Example(age: Int, birthdate: DateTime)

    val fake = Example(age = 17, birthdate = DateTime.parse("2010-01-01"))

    val expression = OrOperator(
      LessThanOperator(
        NumericProperty("age", fake),
        Literal(18)
      ),
      NotOperator(
        EqualToOperator(
          DateProperty("birthdate", fake),
          DateLiteral(DateTime.parse("1990-01-01"))
        )
      )
    )

    val expected = true

    val result = BooleanExpressionResolver.resolve(expression)

    result shouldBe expected*/
  }
}
