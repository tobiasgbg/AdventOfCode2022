static void main(String[] args) {

  File file = new File('input')
  Integer result = 0

  file.eachLine {result += Game.getScore(it.split(" ")[0], it.split(" ")[1])}

  println("Sum ${result}")
}