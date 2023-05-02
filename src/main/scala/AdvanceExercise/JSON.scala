package AdvanceExercise

import spray.json._

// case class to be serialized
case class Person(name: String, age: Int)

// type class for serializing the Person case class to JSON and from JSON to Person
trait JsonSerializable[T] {
  def toJson(t: T): JsValue
  def fromJson(json: JsValue): T
}

// implicit JSON format for the Person case class
trait PersonJsonProtocol extends DefaultJsonProtocol {
  implicit val personFormat = jsonFormat2(Person)
}

object JSON extends App with PersonJsonProtocol {

   val personJsonSerializable = new JsonSerializable[Person] {
     def toJson(person: Person): JsValue = JsObject( // returns the jsObject with the two fields name and age
      "name" -> JsString(person.name),
      "age" -> JsNumber(person.age)
    )
    def fromJson(json: JsValue): Person = json match {
      // using match pattern
      case JsObject(fields) =>
        val name = fields("name").convertTo[String]
        val age = fields("age").convertTo[Int]
        Person(name, age)
      case _ => throw DeserializationException("Person expected")
    }
  }
  // Using JSON format to serialize and deserialize the Person case class
  val person = Person("Alice", 30)
  val json = personJsonSerializable.toJson(person)
  println(json)
  val deserializedPerson = personJsonSerializable.fromJson(json)
  println(deserializedPerson)

}
