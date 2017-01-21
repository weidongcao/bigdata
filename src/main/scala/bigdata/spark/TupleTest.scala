package bigdata.scala

/**
  * 北风网，中华石杉，Tuple拉链操作
  */
object TupleTest {
  def main(args: Array[String]): Unit = {
    val student = Array("Leo", "Jack", "Jen")
    val scores  = Array(80, 90, 100)

    val stuScores = student.zip(scores)

    for ((stu, score) <- stuScores) {
      println(stu + " -> " + score)
    }
    val stuMap = stuScores.toMap
    println(stuMap("Leo"))
  }
}
