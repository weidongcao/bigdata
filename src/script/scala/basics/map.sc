//Scala支持对映射表采用以下特殊的初始化语法。
val stateCapitals = Map(
	"Alabama" -> "Montgomery",
	"Alaska" -> "Juneau",
	"Wyoming" -> "Cheyenne"
)

val lengths = stateCapitals map {
	kv => (kv._1, kv._2.length)
}

val caps = stateCapitals map {
	case (k, v) => (k, v.toUpperCase)
}

val stateCapitals2 = stateCapitals + (
	"Virginia" -> "Richmond"
)

val stateCapitals3 = stateCapitals2 + (
	"New York" -> "Albany",
	"Illinois" -> "Springfield"
)