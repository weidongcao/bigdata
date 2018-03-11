val states = List("Alabama", "Alaska", "Virginia", "Wyoming")

/**
  * for推导式的flatmap实现
  */
for {
	s <- states
	c <-s
} yield s"$c-${c.toUpper}"

/**
  * for推导式添加保护式
  */
for {
	s <- states
	c <-s
	if c.isLower
} yield s"$c-${c.toUpper}"

states flatMap (_.toSeq withFilter (_.isLower) map (c => s"$c-${c.toUpper}"))