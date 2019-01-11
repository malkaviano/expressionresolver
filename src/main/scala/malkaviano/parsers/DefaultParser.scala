package malkaviano.parsers

import malkaviano.operators.comparison.{EqualToOperator, LessThanOperator}
import malkaviano.operators.logical.{AndOperator, NotOperator, OrOperator}
import malkaviano.operators.property.OptionalProperty
import malkaviano.operators.{BooleanOperator, Operator}
import malkaviano.operators.value.Literal
import malkaviano.tokens._
import org.joda.time.DateTime

import scala.collection.mutable

class DefaultParser(obj: Any) {
  def parse(tokens: Seq[Any]): BooleanOperator = {
    val reversed = tokens.reverse

    val operators = new mutable.ArrayStack[BooleanOperator]
    val textOperands = new mutable.ArrayStack[Operator[Option[String]]]
    val numericOperands = new mutable.ArrayStack[Operator[Option[Int]]]
    val decimalOperands = new mutable.ArrayStack[Operator[Option[Double]]]
    val dateOperands = new mutable.ArrayStack[Operator[Option[DateTime]]]

    reversed.map(token => {
      token match {
        case lt: LiteralToken[_] => {
          lt.tag match {
            case Tags.TEXT => textOperands.push(Literal(lt.value.toString))
            case Tags.NUMBER => numericOperands.push(Literal(lt.value.toString.toInt))
            case Tags.DECIMAL => decimalOperands.push(Literal(lt.value.toString.toDouble))
            case Tags.DATE => dateOperands.push(Literal(DateTime.parse(lt.value.toString)))
          }
        }
        case pt: PropertyToken => {
          pt.tag match {
            case Tags.TEXT => textOperands.push(OptionalProperty[String](pt.name, obj))
            case Tags.NUMBER => numericOperands.push(OptionalProperty[Int](pt.name, obj))
            case Tags.DECIMAL => decimalOperands.push(OptionalProperty[Double](pt.name, obj))
            case Tags.DATE => dateOperands.push(OptionalProperty[DateTime](pt.name, obj))
          }
        }
        case bOper: OperatorToken => {
          bOper.name match {
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
              println(operators.head)
              println(operators.head.evaluate)
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
    })

    operators.pop
  }
}
