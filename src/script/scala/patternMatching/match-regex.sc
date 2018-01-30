val BookExtractorRE = """Book: title=([^,]+),\s+author=(.+)""".r
val MagazineExtractorRE = """Magazine: title=([^,]+),\s+issue=(.+)""".r

val catalog = Seq(
	"Book: title=Programming Scala Second Edition, author=Dean Wampler",
	"Magazine: title=The New Yorker, issue=January 2014",
	"Unknown: text = Who put this here?"
)

for (item <- catalog) {
	item match {
		case BookExtractorRE(title, author) =>
			println(s"""Book "$title", written by $author""")
		case MagazineExtractorRE(title, issue) =>
			println(s"""Magazine "title", issue $issue """)
		case entry => println(s"Unrecognized entry: $entry")
	}
}