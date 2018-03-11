val states = List("Dong", "Poem", "Wei", "Meng")

for {
	s <- states
	c <- s
	if c.isLower
	c2 = s"$c-${c.toUpper}"
} yield c2

states flatMap (_.toSeq withFilter (_.isLower) map { c=>
	val c2 = s"$c-${c.toUpper}"
	c2
})