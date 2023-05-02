package AdvanceExercise

object Implicit extends App{

  implicit val y: Int = 8

  def add(x:Int)(implicit y: Int): Int = x + y
  val result = add(3) // take y value 8 as passed it as a implicitly
  println("Sum: " + result)

  def sub(x:Int)(implicit y: Int): Int = y - x
  val result2 = sub(4)
  println("Sub: " + result2)

  def multiply(x: Int)(implicit y: Int): Int = x * y
  val result3 = multiply(4)
  println("multiply: " + result3)

}
