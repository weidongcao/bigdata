import scala.collection.immutable.{StringOps, WrappedString}
import scala.collection.mutable

@inline implicit def byteWrapper(x: Byte)       = new runtime.RichByte(x)
@inline implicit def shortWrapper(x: Short)     = new runtime.RichShort(x)
@inline implicit def intWrapper(x: Int)         = new runtime.RichInt(x)
@inline implicit def charWrapper(x: Char)       = new runtime.RichChar(x)
@inline implicit def longWrapper(x: Long)       = new runtime.RichLong(x)
@inline implicit def floatWrapper(x: Float)     = new runtime.RichFloat(x)
@inline implicit def doubleWrapper(x: Double)   = new runtime.RichDouble(x)
@inline implicit def booleanWrapper(x: Boolean) = new runtime.RichBoolean(x)

//在scala.collection.mutable.WrappedArray中还存在用于包装Java的可变数组的方法，
// 为数组添加了许多我们在第8章中讨论的集合方法。
implicit def wrapIntArray(xs: Array[Int]): mutable.WrappedArray[Int]
implicit def wrapDoubleArray(xs: Array[Double]):mutable.WrappedArray[Double]
implicit def wrapLongArray(xs: Array[Long]): mutable.WrappedArray[Long]
implicit def wrapFloatArray(xs: Array[Float]): mutable.WrappedArray[Float]
implicit def wrapCharArray(xs: Array[Char]): mutable.WrappedArray[Char]
implicit def wrapByteArray(xs: Array[Byte]): mutable.WrappedArray[Byte]
implicit def wrapShortArray[xs: Array[Short]]: mutable.WrappedArray[Short]
implicit def wrapBooleanArray(xs: Array[Boolean]): mutable.WrappedArray[Boolean]
implicit def wrapUnitArray(xs: Array[Unit]): mutable.WrappedArray[Unit]

//类似WrappedArray与WrappedOps，String也有相应的包装类型：
//scala.collection.immutable.WrappedString
//scala.collection.immutable.StringOps
//它们给String增加了集合方法，将基准视为Char元素的集合。所以，Predef定义了String和以上类型的相互转化
implicit def wrapString(s: String): WrappedString
implicit def unwrapString(ws: WrappedString): String

implicit def argmentString(x: String): StringOps
implicit def unaugemntString(x: StringOps): String

//还有其他很多方法可以实现Java包装的原生类型和Scala的AnyVal类型之间的转换。
implicit def byte2Byte(x: Byte)         = java.lang.Byte.valueOf(x)
implicit def short2Short(x: Short)      = java.lang.Short.valueOf(x)
implicit def char2Character(x: Char)    = java.lang.Character.valueOf(x)
implicit def int2Integer(x: Int)        = java.lang.Integer.valueOf(x)
implicit def long2Long(x: Long)         = java.lang.Long.valueOf(x)
implicit def float2Float(x: Float)      = java.lang.Float.valueOf(x)
implicit def double2Double(x: Double)   = java.lang.Double.valueOf(x)

implicit def Byte2Byte(x: java.lang.Byte): Byte     = x.byteValue()
implicit def Short2short(x: java.lang.Short): Short  = x.shortValue()
implicit def Character2char(x: java.lang.Character): Char   = x.charValue()
implicit def Integer2int(x: java.lang.Integer): Int = x.intValue()
implicit def Long2long(x:java.lang.Long):Long       =x.longValue()
implicit def Float2float(x: java.lang.Float): Float = x.floatValue()
implicit def Double2double(x: java.lang.Double): Double = x.doubleValue()
implicit def Boolean2boolean(x: java.lang.Boolean): Boolean     = x.booleanValue()

implicit def Byte2byteNullConflict(x: Null): Byte = sys.error("value error")

//为了鼓励使用不可变集合，Predef为最常用的不可变集合定义了别名
type Map[A, +B]         = collection.immutable.Map[A, B]
type Set[A]             = collection.immutable.Set[A]
type Function[-A, +B]   = Function[A, B]

//final class Arrawssoc[A] extends AnyVal
