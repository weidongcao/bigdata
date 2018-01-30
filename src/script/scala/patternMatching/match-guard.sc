for (i <- Seq(1, 2, 3, 4)) {
	i match {
		case _ if i % 2 == 0 => println(s"even: $i")
		case _ => println(s"odd: $i")
	}
}