package BasicsExercise
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object future extends App{

  //Future is defined as an asynchronous computation means it does not worrying about the thread management
  //Future in Scala enables us to write non-blocking and concurrent code
  // It allow to run multiple task in parallel instead blocking the set of code .

  val future1: Future[Int] = Future{
    40
  }
  val future2: Future[String] = Future{
    "hello!!!!"
  }

  // using a for comprehension for combining both the future into a single future result
  val result: Future[(Int,String)] = for {
    r1 <- future1
    r2 <- future2
  }yield ( r1,r2)


  result.onComplete{ // if success it return the result which is basically a tuple of (int , String)
    case Success((r1,r2)) =>
      println(s"Result: $r1, $r2")
    case Failure(ex) =>
      println(s"Error: ${ex.getMessage}") //getting the error if we got an exception
  }

}
