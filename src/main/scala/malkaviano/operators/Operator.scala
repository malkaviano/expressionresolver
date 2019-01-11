package malkaviano.operators

trait Operator[+T] {
  def evaluate: T
}
