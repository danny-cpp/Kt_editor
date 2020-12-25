// Try me
val str = "HELLOWORLD!"

var tmp = ""
for (i in 0..str.length - 1) {
  println(tmp + str.get(i))
  tmp += "   "
}