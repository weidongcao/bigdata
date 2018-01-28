object Breed extends Enumeration{
	type Breed = Value
	val doberman = Value("Doberman Pinscher")
	val yorkie = Value("Yorkshire Terrier")
	val scottie = Value("Scottish Terrier")
	val dane = Value("Great Dane")
	val portie = Value("Portuguese Water Dog")
}

import Breed._

print(("ID\tBreed"))

//打印所有犬种及其ID列表
for(breed <- Breed.values)
	println(s"${breed.id} \t $breed")

//打印更犬列表
println("\nJust Terriers: ")
Breed.values filter(_.toString.endsWith("Terrier")) foreach println

def isTerrier(b: Breed) = b.toString.endsWith("Terrier")

println("\nTerriers Again??")
Breed.values filter isTerrier foreach println