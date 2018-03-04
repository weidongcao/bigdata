List(1,2,3,4,5) foreach { i => println("Int: " + i)}

val stateCapitals = Map(
	"Alabama" -> "Montgomery",
	"Alaska" -> "Juneau",
	"Wyoming" -> "Cheyenne"
)
stateCapitals foreach { case (k, v) => println(k + ": " + v)}