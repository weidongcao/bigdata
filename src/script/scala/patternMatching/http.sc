
sealed abstract class HttpMethod() {
	def body: String

	def bodyLength = body.length
}

case class Connect(body: String) extends HttpMethod

case class Delete(body: String) extends HttpMethod

case class Get(body: String) extends HttpMethod

case class Head(body: String) extends HttpMethod

case class Options(body: String) extends HttpMethod

case class Post(body: String) extends HttpMethod

case class Put(body: String) extends HttpMethod

case class Trace(body: String) extends HttpMethod

def handle(method: HttpMethod) = method match {
	case Connect(body) => s"connect:  (length: ${method.bodyLength}) $body"
	case Delete(body)  => s"delete:   (length: ${method.bodyLength}) $body"
	case Get(body)     => s"get:      (length: ${method.bodyLength}) $body"
	case Head(body)    => s"head:     (length: ${method.bodyLength}) $body"
	case Options(body) => s"options:  (length: ${method.bodyLength}) $body"
	case Post(body)    => s"post:     (length: ${method.bodyLength}) $body"
	case Put(body)     => s"put:      (length: ${method.bodyLength}) $body"
	case Trace(body)   => s"trace:    (length: ${method.bodyLength}) $body"
}

val methods = Seq(
	Connect("connect body ..."),
	Delete ("delete  body ..."),
	Get    ("get     body ..."),
	Head   ("head    body ..."),
	Options("Options body ..."),
	Post   ("Post    body ..."),
	Put    ("Put     body ..."),
	Trace  ("Trace   body ...")
)

methods foreach (method => println(handle(method)))