import spock.lang.Specification

class Day1Specification extends Specification {
    def 'make sure we can parse a string with one object'() {
        given:
        def input = """111\r\n111"""

        when:
        Day1Part1SantaList santaList = new Day1Part1SantaList(input)
        List<Integer> list = santaList.getList()

        then:
        list.size() == 1
    }
    def 'make sure we can parse a string with two objects'() {
        given:
        def input = """111\r\n111\r\n\r\n111\r\n111"""

        when:
        Day1Part1SantaList santaList = new Day1Part1SantaList(input)
        List<Integer> list = santaList.getList()

        then:
        list.size() == 2
    }
    def 'make sure we can get the sum'() {
        given:
        def input = """111\r\n111"""

        when:
        int sum = Day1Part1SantaList.getSum(input)

        then:
        sum == 222
    }
    def 'make sure we can parse a string with one object'() {
        given:
        def input = """111\r\n111"""

        when:
        Day1Part2SantaList santaList = new Day1Part2SantaList(input)
        List<Integer> list = santaList.getSortedList()

        then:
        list.size() == 1
    }
    def 'make sure we can parse a string with two objects'() {
        given:
        def input = """111\r\n111\r\n\r\n\111\r\n111"""

        when:
        Day1Part2SantaList santaList = new Day1Part2SantaList(input)
        List<Integer> list = santaList.getSortedList()

        then:
        list.size() == 2
    }
    def 'make sure we can get the sum'() {
        given:
        def input = """111\r\n111"""

        when:
        int sum = Day1Part2SantaList.getSum(input)

        then:
        sum == 222
    }

    def 'make sure we can get a sorted list'() {
        given:
        def input = """1\r\n111\r\n\r\n\111\r\n114\r\n\r\n110"""

        when:
        Day1Part2SantaList santaList = new Day1Part2SantaList(input)
        List<Integer> list = santaList.getSortedList()

        then:
        list == [114,112,110]
    }
}