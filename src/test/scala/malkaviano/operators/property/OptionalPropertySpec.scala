package malkaviano.operators.property

import malkaviano.proxies.Proxying
import org.joda.time.DateTime
import org.scalatest.{FunSpec, Matchers}

class OptionalPropertySpec extends FunSpec with Matchers  {

  describe("Evaluating") {
    case class Fake(date: Option[DateTime], str: String)

    val date = Some(DateTime.parse("1990-01-01"))


    describe("when value exists") {
      describe("when it is an Option field") {
        it("returns the Field") {
          val fake = new Proxying {
            override def valueOf[A](name: String): Option[A] = date.asInstanceOf[Option[A]]
          }

          val expected = date

          val result = OptionalProperty("date", fake).evaluate

          result shouldBe expected
        }
      }

      describe("when it is not an Option Field") {
        it("returns the Field") {
          val fake = new Proxying {
            override def valueOf[A](name: String): Option[A] = Option("wrong").asInstanceOf[Option[A]]
          }

          val expected = Option("wrong")

          val result =  OptionalProperty("str", fake).evaluate

          result shouldBe expected
        }
      }
    }

    describe("when value does not exist") {
      it("returns None") {
        val fake = new Proxying {
          override def valueOf[A](name: String): Option[A] = Option.empty[A]
        }

        val expected = Option.empty[DateTime]

        val result = OptionalProperty("nonexistant", fake).evaluate

        result shouldBe expected
      }
    }
  }
}
