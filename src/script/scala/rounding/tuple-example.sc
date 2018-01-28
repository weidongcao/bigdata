val t = ("Hello", 1, 2.3)

println("Print the whole tuple: " + t)
println("Print the first tem: " + t._1)
println("Print the second item: " + t._2)
println("Print the third item: " + t._3)

val (t1, t2, t3) = ("World", "!", 0x22)

println(t1 + ", " + t2 + "," + t3)

val (t4, t5, t6) = Tuple3("World", '!', 0x22)
println(t4 + ", " + t5 + ", " + t6)