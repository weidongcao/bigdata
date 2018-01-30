val langs = Seq(
	("Scala", "Martin", "Odersky"),
	("Clojure", "Rich", "Hichey"),
	("Lisp", "John", "McCarthy")
)
for (tuple <- langs) {
	tuple match {
		case ("Scala", _, _) => println("found Scala")
		case (lang, first, last) =>
			println(s"Found other language: $lang ($first, $last)")
	}
}