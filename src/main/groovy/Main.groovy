static void main(String[] args) {

  String fileContents = new File('input').text

  SantaList santaList = new SantaList(fileContents)
  List<Integer> list = santaList.getSortedList()

  def sumValue = 0
  list.subList(0, 3).each {sumValue += it}
  println("Sum ${sumValue}")
}