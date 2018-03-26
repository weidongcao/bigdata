import com.book.scala.basicoop.Zipcode

Zipcode(12345)
Zipcode(12345, Some(6789))
Zipcode(12345, 6789)
try{
	Zipcode(0, 6789)
} catch {
	case e: java.lang.IllegalArgumentException => e
}

try {
	Zipcode(12345, 0)
} catch {
	case e: java.lang.IllegalArgumentException => 3
}
