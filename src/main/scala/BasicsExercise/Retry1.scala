package BasicsExercise
import java.util.Random
import scala.annotation.tailrec
import scala.util.{Failure, Success, Try}
object Retry1 extends App{
  @tailrec
  def retryFunc[T](f:() => T, maxRetries: Int): T = {
    Try(f()) match {
      case Success(value) => value
      case Failure(ex) =>
        if (maxRetries > 0) {
          Thread.sleep(1000)
          retryFunc(f, maxRetries - 1)
        } else {
          throw ex
        }
    }
  }

  val random = new Random()
  // action function to retry
  def apply(): Int = {
    println("Executing apply function...")
    //  we are getting an random value and checking that if it is lesser than 6 then we are throwing the exception
    val randomValue = random.nextInt(10)
    if (randomValue < 6) {
      throw new RuntimeException("Failed attempt")
    }
   42
  }

  // Call the retry function with the action function
  val result = retryFunc(apply, maxRetries = 10)
  println(result)

}
