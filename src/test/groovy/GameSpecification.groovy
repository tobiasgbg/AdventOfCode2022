import spock.lang.Specification

class GameSpecification extends Specification {
    def "score of two strings"(String a, String b, int c) {
        expect:
        Game.getScore(a, b) == c

        where:
        a   |  b  | c
        "A" | "X" | 4
        "A" | "Y" | 8
        "A" | "Z" | 3
        "B" | "X" | 1
        "B" | "Y" | 6
        "B" | "Z" | 9
        "C" | "X" | 7
        "C" | "Y" | 8
        "C" | "Z" | 6
    }
}