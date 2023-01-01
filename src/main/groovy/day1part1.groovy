static void main(String[] args) {

    String fileContents = new File('day1part1.txt').text

    Day1Part1SantaList santaList = new Day1Part1SantaList(fileContents)
    List list = santaList.getList()

    def maxValue = Collections.max(list)
    println("Maxvalue ${maxValue}")
}

import java.util.regex.Pattern

class Day1Part1SantaList {
    private String Data

    Day1Part1SantaList(String data)
    {
        this.Data = data
    }

    List<Integer> getList() {
        List<Integer> result = []
        this.Data.split("\\r\\n\\r\\n").each {result.add(getSum(it)) }
        return result
    }

    static int getSum(String input)
    {
        int sum = 0
        input.trim().split('\\r\\n').each {sum += !it.isInteger() ? 0 : it as Integer}
        return sum
    }
}