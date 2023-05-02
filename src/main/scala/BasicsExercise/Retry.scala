package BasicsExercise

// import the necessary utilities
import scala.concurrent.duration._
import scala.util.Random

object Retry extends App{

  def retry[T](f: => T, maxRetries: Int, backoff: Option[FiniteDuration] = None): T = {
    var retries = 0 // initialized with zero
    var result: Option[T] = None // Option is type that represent value may or may not be present either Some or None

    while (retries <= maxRetries && result.isEmpty) {
      // using try and catch block
      try {
        result = Some(f) // if exception is not found than success return the result
      } catch {
        case e: Exception =>
          if (retries < maxRetries) {
            // it iterates over the each element of backoff sequence
            backoff.foreach(duration => Thread.sleep(duration.toMillis))
            // duration.toMillis => returns duration into milliseconds as a Long
            //FiniteDuration object is converted into a Long by using toMillis
            // before retrying the bock of code sleep for an appropriate amount of time
            retries += 1
          } else {
            // log the error message to the console
            println(s"Caught exception: ${e.getMessage}")
            throw e // reached max number of retries, rethrow the exception
          }
      }
    }
    result.get // return the result or throw an exception if none was obtained after max retries
  }

  val f1: () => Int = () => {    // anonymous function with no arguments
    if (math.random < 0.5) { // here take 50% of chance for failure
      throw new RuntimeException("Random failure")
    } else {
      42 // success
    }
  }

  val f2: () => String = () => {
    // generating a random number
    var v = new Random
    // putting a random number from 0 to 10
    var random_number = v.nextInt(10)
    // two conditions either fails or success
    if (random_number < 6) {
      throw new RuntimeException("Random failure")
    } else {
      "Run Successfully"
    }
  }

  def f3(): String = {
    if (math.random < 0.5) {
      throw new RuntimeException("Random failure")
    } else {
      "Success"
    }
  }

  // checking the block of code with retry functions
  val r1 = retry(f3(),2,Some(1.second)) //1-second backoff period between each retry
  println(r1)

  val result1 = retry(f2() , 1 , Some(2.second))
  println(result1)

  val result = retry(f1(),4) // it takes by default value None for the backoff
                                       //the function will retry the code block up to 4 times with no backoff period between retries.
  println(result)
}
