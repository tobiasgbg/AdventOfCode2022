static void main(String[] args) {

    String fileContents = new File('input').text

    Day1Part2SantaList santaList = new Day1Part2SantaList(fileContents)
    List<Integer> list = santaList.getSortedList()

    def sumValue = 0
    list.subList(0, 3).each {sumValue += it}
    println("Sum ${sumValue}")
}

class Day1Part2SantaList {
    private String Data

    Day1Part2SantaList(String data)
    {
        this.Data = data
    }

    private List<Integer> getCombinedList() {
        List<Integer> result = []
        this.Data.split("\\r\\n\\r\\n").each {result.add(getSum(it)) }
        return result
    }

    List<Integer> getSortedList() {
        List<Integer> result = getCombinedList().sort().reverse()
        return result
    }

    static int getSum(String input)
    {
        int sum = 0
        input.trim().split('\\r\\n').each {sum += !it.isInteger() ? 0 : it as Integer}
        return sum
    }
}