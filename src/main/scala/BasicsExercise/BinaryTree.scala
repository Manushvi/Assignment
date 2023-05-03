package BasicsExercise

trait BinaryTree[+T] // we make two classes one is leaf and one is node
case object Leaf extends BinaryTree[Nothing] //singleton object and has no parameters.It represents an empty binary tree, i.e., a tree with no values and no children.
case class Node[T](value: T, left: BinaryTree[T], right: BinaryTree[T]) extends BinaryTree[T] // it represent the internal node

object BinaryTree extends App{

  // function which calculate the height of tree recursively
  def height[T](tree: BinaryTree[T]): Int = tree match {
    case Leaf => 0
    case Node(_, left, right) => 1 + math.max(height(left), height(right))
  }
  // function which calculate the leaf nodes
  def leafCount[T](tree: BinaryTree[T]): Int = tree match {
    case Leaf => 0
    case Node(_,Leaf,Leaf) => 1
    case Node(_, left, right) => leafCount(left) + leafCount(right)
  }

  def PreorderTraversal[A](tree: BinaryTree[A]): Unit = tree match {
    // using pattern matching
    case Leaf => // we left it as it is represent a null
    case Node(value, left, right) =>
      println(value)
      // recursive calls (left & right)
      PreorderTraversal(left)
      PreorderTraversal(right)
  }

  val tree = Node(9,Node(7,Node(5,Leaf,Leaf),Leaf),Node(2,Node(6,Node(1,Leaf,Leaf),Node(4,Leaf,Leaf)),Node(3,Node(8,Leaf ,Leaf),Node(10,Leaf,Leaf))))

  println("Height of the tree: " + height(tree)) // returns 4
  println("No of leafCount: " + leafCount(tree)/2) // return 5
  // here divide the leaf count by 2 because function returns the double count as it is returning the empty node not leaf node
  PreorderTraversal(tree) // returns List(9,7,5,2,6,1,4,3,8,10)

}
