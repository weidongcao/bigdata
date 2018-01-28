/**
  * 每次执行for表达式时，过滤后的结果将生成Breed值，代码的执行，
  * 这些结果值逐渐积累起来，累计而成的结果值集合被峓了filteredBreeds对象
  * for-yield表达式所生成的集合类型将根据被遍历的集合类型推导而出。
  */
val dogBredads = List(
	"Doberman",
	"Yorkshire Terrier",
	"Dachshund",
	"Scottish Terrier",
	"Great Dane",
	"Portuguese Water Dog"
)

val filteredBreeds = for {
	breed <- dogBredads
	if breed.contains("Terrier") &&
	!breed.startsWith("Yorkshire")
} yield breed
