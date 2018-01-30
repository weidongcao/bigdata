/**
  * 4.12 模式匹配的其他用法
  * 在for表达式中使用模式匹配
  */
val dogBreeds = Seq(
	Some("Doberman"),
	None,
	Some("Yorkshire Terrier"),
	Some("Dachshund"),
	None,
	Some("Scottish Terrier"),
	None,
	Some("Great Dane"),
	Some("Portuguese Water Dog")
)

println("second pass:")
for {
	Some(breed) <- dogBreeds
	upcasedBreed = breed.toUpperCase()
} println(upcasedBreed)