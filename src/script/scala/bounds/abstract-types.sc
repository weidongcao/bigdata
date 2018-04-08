
import java.io._

abstract class BulkReader{
	type In
	val source: In
	ef read: String
}
class StringBulkReader(val source: String) extends BulkReader{
	type In = String
	def read: String
}

class FileBulkReader(val source: File) extends BulkReader{
	type In = File
	def read: String = {}
}