// Try me
val str = "HelloWorld!"

var tmp = ""
for (i in 0..str.length - 1) {
  println(tmp + str.get(i))
  tmp += "   "
}