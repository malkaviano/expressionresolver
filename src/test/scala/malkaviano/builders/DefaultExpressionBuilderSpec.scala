package malkaviano.builders

import malkaviano.operators.comparison.{EqualToOperator, LessThanOperator}
import malkaviano.operators.logical.{NotOperator, OrOperator}
import org.joda.time.DateTime
import org.scalatest.{FunSpec, Matchers}

class DefaultExpressionBuilderSpec extends FunSpec with Matchers {

    describe("Building an Expression Tree") {

      it("returns a tree of operators") {
        val json =
          """
            |{
            |	"#or": [
            |		{
            |			"#lt": [
            |				{ "#prop" : "age" },
            |				{ "#num" : 18 }
            |			]
            |		},
            |		{
            |			"#not": [
            |				{
            |					"#eq" : [
            |						{ "#prop" : "birthdate" },
            |						{ "#date" : "1990-01-01" }
            |					]
            |				}
            |			]
            |		}
            |	]
            |}
          """.stripMargin
      }
    }
}
