// Try me
val str = "abc!"

var tmp = ""
for (i in 0..str.length - 1) {
  println(tmp + str.get(i))
  tmp += "   "
}