package malkaviano.tokenizers

import malkaviano.tokens._
import org.scalatest.{FunSpec, Matchers}

class DefaultTokenizerSpec extends FunSpec with Matchers {

  describe("Tokenizing") {
    it("returns a sequence of tokens") {
      val json =
        """
          |{
          |   "oper" : "and",
          |   "values" : [
          |     {
          |       "oper" : "less",
          |       "values" : [
          |         { "oper" : "literal", "values": [ 4 ], "tag" : "number" },
          |         { "oper" : "prop", "values": [ "age" ], "tag" : "number" }
          |       ]
          |     },
          |     {
          |       "oper" : "not",
          |       "values" : [
          |         {
          |           "oper" : "eq",
          |           "values" : [
          |             { "oper" : "literal", "values": [ "1990-01-01" ], "tag" : "date" },
          |             { "oper" : "prop", "values": [ "birth" ], "tag" : "date" }
          |           ]
          |         }
          |       ]
          |     }
          |   ]
          |}
        """.stripMargin

      val expected: Seq[Any] = Seq(
        OperatorToken(Operators.AND),
        OperatorToken(Operators.LESS),
        LiteralToken(4, "number"),
        PropertyToken("age", "number"),
        OperatorToken(Operators.NOT),
        OperatorToken(Operators.EQ),
        LiteralToken("1990-01-01", "date"),
        PropertyToken("birth", "date")
      )

      val result = DefaultTokenizer().generate(json)

      result shouldBe expected
    }
  }
}
