package malkaviano.operators.text

import org.joda.time.DateTime
import org.scalatest.{FunSpec, Matchers}

class ContainsOperatorSpec extends FunSpec with Matchers {

  describe("Evaluating") {
    val text = "this guy is cool"

    it("returns true") {
      val search = "guy is"

      val expected = true

      val result = ContainsOperator(text, search).evaluate

      result shouldBe expected
    }

    it("returns false") {
      val search = "nope"

      val expected = false

      val result = ContainsOperator(text, search).evaluate

      result shouldBe expected
    }

    describe("when operands are not text") {
      it("returns true") {
        val text = DateTime.parse("1990-01-01")
        val search = 9

        val expected = true

        val result = ContainsOperator(text, search).evaluate

        result shouldBe expected
      }
    }
  }
}
