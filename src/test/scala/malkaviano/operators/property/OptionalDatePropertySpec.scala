package malkaviano.operators.property

import org.joda.time.DateTime
import org.scalatest.{FunSpec, Matchers}

class OptionalDatePropertySpec extends FunSpec with Matchers  {

  describe("Evaluating") {
    case class Fake(date: Option[DateTime])

    describe("when value exists") {
      describe("when it is a Date") {
        val date = Some(DateTime.parse("1990-01-01"))

        val fake = Fake(date)

        val expected = date

        val result = OptionalDateProperty("date", fake).evaluate

        result shouldBe expected
      }
    }
  }
}
