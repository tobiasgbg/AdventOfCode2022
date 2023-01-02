import spock.lang.Specification

class Day3Specification extends Specification {
    def "split string"(String a, String b, String c) {
        expect:
        Backpack.getCompartments(a) == [b, c]

        where:
        a    |  b  |  c
        "AB" | "A" | "B"
        "acbc" | "ac" | "bc"
    }

    def "get character value"(String a, int b) {
        expect:
        Backpack.getValue(a) == b

        where:
        a   | b
        "p" | 16
        "L" | 38
        "P" | 42
        "v" | 22
        "t" | 20
        "s" | 19
    }
}
