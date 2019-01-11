package malkaviano.tokens

import scala.reflect.ClassTag

case class LiteralToken[A : ClassTag](value: A, tag: String)
