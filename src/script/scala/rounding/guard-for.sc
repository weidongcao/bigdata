val dogBredads = List(
	"Doberman",
	"Yorkshire Terrier",
	"Dachshund",
	"Scottish Terrier",
	"Great Dane",
	"Portuguese Water Dog"
)
for (breed <- dogBredads
	if breed.contains("Terrier"))
	println(breed)

for (breed <- dogBredads
	if breed.contains("Terrier")
	if !breed.startsWith("Yorkshire")) {
	println(breed)
}
