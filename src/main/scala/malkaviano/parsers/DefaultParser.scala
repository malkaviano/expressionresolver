package malkaviano.parsers

import malkaviano.operators.comparison.{EqualToOperator, LessThanOperator}
import malkaviano.operators.logical.{AndOperator, NotOperator, OrOperator}
import malkaviano.operators.property.OptionalProperty
import malkaviano.operators.{BooleanOperator, Operator}
import malkaviano.operators.value.Literal
import malkaviano.proxies.Proxying
import malkaviano.tokens._
import org.joda.time.{DateTime, DateTimeZone}
import org.joda.time.format.ISODateTimeFormat

import scala.collection.mutable

class DefaultParser (
                      proxy: Proxying,
                      dateParser: String => DateTime = ISODateTimeFormat.date.withZone(DateTimeZone.UTC).parseDateTime
                   ) extends Parsing {
  override def parse(tokens: Seq[Any]): BooleanOperator = {
    val reversed = tokens.reverse

    val operators = new mutable.ArrayStack[BooleanOperator]
    val textOperands = new mutable.ArrayStack[Operator[Option[String]]]
    val numericOperands = new mutable.ArrayStack[Operator[Option[Int]]]
    val decimalOperands = new mutable.ArrayStack[Operator[Option[Double]]]
    val dateOperands = new mutable.ArrayStack[Operator[Option[DateTime]]]

    reversed.foreach {
      case lt: LiteralToken => {
        lt.tag.toUpperCase match {
          case Tags.TEXT => textOperands.push(Literal(lt.value.toString))
          case Tags.NUMBER => numericOperands.push(Literal(lt.value.toString.toInt))
          case Tags.DECIMAL => decimalOperands.push(Literal(lt.value.toString.toDouble))
          case Tags.DATE => dateOperands.push(Literal(dateParser(lt.value.toString)))
        }
      }
      case prop: PropertyToken => {
        prop.tag.toUpperCase match {
          case Tags.TEXT => textOperands.push(OptionalProperty(prop.name, proxy))
          case Tags.NUMBER => numericOperands.push(OptionalProperty(prop.name, proxy))
          case Tags.DECIMAL => decimalOperands.push(OptionalProperty(prop.name, proxy))
          case Tags.DATE => dateOperands.push(OptionalProperty(prop.name, proxy))
        }
      }
      case oper: OperatorToken => {
        oper.name match {
          case Operators.AND => {
            operators.push(AndOperator(operators.pop, operators.pop))
          }
          case Operators.OR => {
            operators.push(OrOperator(operators.pop, operators.pop))
          }
          case Operators.NOT => {
            operators.push(NotOperator(operators.pop))
          }
          case Operators.EQ => {
            if (textOperands.nonEmpty) {
              operators.push(EqualToOperator(textOperands.pop, textOperands.pop))
            } else if (numericOperands.nonEmpty) {
              operators.push(EqualToOperator(numericOperands.pop, numericOperands.pop))
            } else if (decimalOperands.nonEmpty) {
              operators.push(EqualToOperator(decimalOperands.pop, decimalOperands.pop))
            } else {
              operators.push(EqualToOperator(dateOperands.pop, dateOperands.pop))
            }
          }
          case Operators.LESS => {
            if (textOperands.nonEmpty) {
              operators.push(LessThanOperator(textOperands.pop, textOperands.pop))
            } else if (numericOperands.nonEmpty) {
              operators.push(LessThanOperator(numericOperands.pop, numericOperands.pop))
            } else if (decimalOperands.nonEmpty) {
              operators.push(LessThanOperator(decimalOperands.pop, decimalOperands.pop))
            } else {
              import com.github.nscala_time.time.OrderingImplicits._

              operators.push(LessThanOperator(dateOperands.pop, dateOperands.pop))
            }
          }
        }
      }
    }

    operators.pop
  }

  def changeProxiedObject(obj: Any): Unit = proxy.setObject(obj)
}
