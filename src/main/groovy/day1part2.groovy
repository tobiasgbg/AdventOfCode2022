/*
--- Part Two ---
By the time you calculate the answer to the Elves' question, they've already realized that the Elf carrying the most Calories of food might eventually run out of snacks.

To avoid this unacceptable situation, the Elves would instead like to know the total Calories carried by the top three Elves carrying the most Calories. That way, even if one of those Elves runs out of snacks, they still have two backups.

In the example above, the top three Elves are the fourth Elf (with 24000 Calories), then the third Elf (with 11000 Calories), then the fifth Elf (with 10000 Calories). The sum of the Calories carried by these three elves is 45000.

Find the top three Elves carrying the most Calories. How many Calories are those Elves carrying in total?
 */

static void main(String[] args) {

    String fileContents = new File('../../../input/day1part2.txt').text

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