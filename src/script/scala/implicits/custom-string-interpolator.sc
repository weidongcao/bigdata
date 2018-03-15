import scala.util.parsing.json._

object Interpolators{
	implicit class jsonForStringContext(val sc: StringContext){
		def json(values: Any*): JSONObject = {
			val keyRE = """^[\s{,]*(\S+):\s*""".r
			val keys = sc.parts.map{
				case keyRE(key) => key
					case str => str
			}
			val kvs = keys zip values
			JSONObject(kvs.toMap)
		}
	}
}

import Interpolators._

val name = "Dean Wampler"
val book = "Programming Scala, Second Edition"

val jsonobj = json"{name: $name, book: $book"
println(jsonobj)