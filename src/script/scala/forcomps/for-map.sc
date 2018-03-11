val states = List("Alabama", "Alaska", "Virginia", "Wyoming")
for {
	s <- states
} yield s.toUpperCase()