package BasicsExercise
import scala.concurrent.Future
import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global

object future1 extends App{

  val future1: Future[Int] = Future.successful(1)
  val future2: Future[Int] = Future.failed(new RuntimeException("Failed future")) // I passed the error here intentionally so
  // that we have to get an error during combining..


  // use zip to combine both future
  val combinedFuture: Future[(Int, Int)] = future1.zip(future2)
    .recoverWith{
      case ex: Exception =>
        println(s"Error: ${ex.getMessage}")

        Future.successful((0,0)) // success the code even after failure of future
    }

  combinedFuture.onComplete {
    case Success((result1, result2)) => println(s"Result 1: $result1, Result 2: $result2")
    case Failure(ex) => println(s"Error: ${ex.getMessage}")
  }

}
