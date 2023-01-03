import spock.lang.Specification

class Day2Specification extends Specification {
    def "score of two strings according to part 1 rules"(String a, String b, int c) {
        expect:
        Day2Part1Game.getScore(a, b) == c

        where:
        a   |  b  | c
        "A" | "X" | 4
        "A" | "Y" | 8
        "A" | "Z" | 3
        "B" | "X" | 1
        "B" | "Y" | 5
        "B" | "Z" | 9
        "C" | "X" | 7
        "C" | "Y" | 2
        "C" | "Z" | 6
    }
    def "score of two strings according to part 2 rules"(String a, String b, int c) {
        expect:
        Day2Part2Game.getTotalScore(a, b) == c

        where:
        a   |  b  | c
        "A" | "X" | 3
        "A" | "Y" | 4
        "A" | "Z" | 8
        "B" | "X" | 1
        "B" | "Y" | 5
        "B" | "Z" | 9
        "C" | "X" | 2
        "C" | "Y" | 6
        "C" | "Z" | 7
    }

    def "score of selection"(String a, int b) {
        expect:
        Day2Part2Game.getSelectionScore(a) == b

        where:
        a   |  b
        "A" | 1
        "B" | 2
        "C" | 3
    }

    def "score of outcome"(String a, int b) {
        expect:
        Day2Part2Game.getGameScore(a) == b

        where:
        a   |  b
        "X" | 0
        "Y" | 3
        "Z" | 6
    }

    def "winning selection"(String a, String b) {
        expect:
        Day2Part2Game.getWinningSelection(a) == b

        where:
        a   |  b
        "A" | "B"
        "B" | "C"
        "C" | "A"
    }

    def "losing selection"(String a, String b) {
        expect:
        Day2Part2Game.getLosingSelection(a) == b

        where:
        a   |  b
        "A" | "C"
        "B" | "A"
        "C" | "B"
    }
}