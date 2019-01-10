package malkaviano.builders

import malkaviano.tokens._
import org.scalatest.{FunSpec, Matchers}

class DefaultExpressionBuilderSpec extends FunSpec with Matchers {

    describe("Building an expression tree") {
      it("returns a tree of operators") {
        /*
            [
              AndToken
              Less
              Literal 4
              Property "age", "INT", "OPTIONAL"
              Eq
              Literal "xpto"
              Property "name", "TEXT", "REQUIRED"
            ]
         */
        val tokens = Seq(
          OperatorToken(Operators.AND),
          OperatorToken(Operators.LESS),
          LiteralToken(4),
          PropertyToken("age", Tags.NUMBER, Expectations.OPTIONAL),
          OperatorToken(Operators.EQ),
          LiteralToken("xpto"),
          PropertyToken("name", Tags.TEXT, Expectations.REQUIRED)
        )
      }
    }
}
