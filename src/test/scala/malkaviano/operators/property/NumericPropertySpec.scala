package malkaviano.operators.property

import org.scalatest.{FunSpec, Matchers}

class NumericPropertySpec extends FunSpec with Matchers {
  describe("Evaluating") {
    case class Fake(number: Int, str: String)

    val fake = Fake(10, "wrong")

    describe("when value exists") {

      describe("when it is a Number") {
        val expected = 10

        val result = NumericProperty("number", fake).evaluate

        result shouldBe expected
      }

      describe("when it is not a Number") {
        an [ClassCastException] should be thrownBy NumericProperty("str", fake).evaluate
      }
    }

    describe("when value does not exist") {
      an [ClassCastException] should be thrownBy NumericProperty("nonexistant", fake).evaluate
    }
  }
}
