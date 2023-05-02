package AdvanceExercise


object Main extends App{

  // made a implicit class and use their function to print the list
  implicit class ListOp[T](list: List[T]) {
    def print(): Unit = println(list.toString())
  }

  val list = List(1,2,3,4,5)
  list.print() // print the list

}
