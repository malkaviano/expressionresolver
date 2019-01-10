package malkaviano.operators.property

import org.scalatest.{FunSpec, Matchers}

class OptionalNumericPropertySpec extends FunSpec with Matchers {
  describe("Evaluating") {
    case class Fake(number: Option[Int], str: String)

    val fake = Fake(Some(10), "wrong")

    describe("when value exists") {

      describe("when it is a Number") {
        val expected = Some(10)

        val result = OptionalNumericProperty("number", fake).evaluate

        result shouldBe expected
      }

      describe("when it is not a Number") {
        an [ClassCastException] should be thrownBy OptionalNumericProperty("str", fake).evaluate
      }
    }

    describe("when value does not exist") {
      val expected = Option.empty[Int]

      val result = OptionalNumericProperty("nonexistant", fake).evaluate

      result shouldBe expected
    }
  }

}
