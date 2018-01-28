val stateCapitals = Map(
	"Alabama" -> "Montgomery",
	"Alaska" -> "Juneau",
	"Wyoming" -> "Cheyenne"
)

println("Get the capitals wrapped in Options:" )
println("Alabama: " + stateCapitals.get("Alabama"))
println("Wyoming: " + stateCapitals.get("Wyoming"))
println("Unknown: " + stateCapitals.get("Unknown"))

println("Gen the Capitals themselves out of the Options: ")
println("Alabama: " + stateCapitals.get("Alabama").get)
println("Alabama: " + stateCapitals.get("Wyoming").getOrElse("Oops!"))
println("Unknown: " + stateCapitals.get("Unkown").getOrElse("Oops2!"))