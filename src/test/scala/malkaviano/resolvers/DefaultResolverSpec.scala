package malkaviano.resolvers

import org.joda.time.DateTime
import org.scalatest.{FunSpec, Matchers}

class DefaultResolverSpec extends FunSpec with Matchers {
  case class Something(
                        name: String,
                        birth: Option[DateTime],
                        commendations: Int
                      )

  describe("Filtering a collection") {
    it("should filter only Camilla record") {
      val collection = Seq(
        Something("Rafael", Option(DateTime.parse("1980-02-15")), 10),
        Something("Thiago", None, 0),
        Something("Camilla", Option(DateTime.parse("1900-12-30")), 5),
        Something("Juliana", None, 50),
      )

      val json =
        """
          |{
          | "oper" : "and",
          | "values" : [
          |   {
          |     "oper" : "less", "values" : [
          |       {"oper" : "prop", "values" : [ "commendations" ], "tag" : "number" },
          |       {"oper" : "literal", "values" : [ 10 ], "tag" : "number" }
          |     ]
          |   },
          |   {
          |     "oper" : "not", "values" : [
          |       { "oper" : "eq", "values" : [
          |           {"oper" : "literal", "values" : [ "Thiago" ], "tag" : "text" },
          |           {"oper" : "prop", "values" : [ "name" ], "tag" : "text" }
          |         ]
          |       }
          |     ]
          |   }
          | ]
          |}
        """.stripMargin

      val expected = Seq(
        Something("Camilla", Option(DateTime.parse("1900-12-30")), 5)
      )

      val resolver = ResolverBuilder.build.construct

      val expr = resolver.expression(resolver.tokens(json))

      val result = collection.filter(r => {
        resolver.setProxiedObject(r)

        expr.evaluate
      })

      result shouldBe expected
    }
  }
}
