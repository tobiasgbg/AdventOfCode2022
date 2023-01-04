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

    def "getRepeatedChar"(String a, String b, String c) {
        expect:
        Backpack.getRepeatedChar(a,b) == c

        where:
        a     |  b    |  c
        "A"   | "A"   | "A"
        "bLw" | "wMf" | "w"
        "kfO" | "OUg" | "O"
    }

    def "get value of repeated char"(String a, int b) {
        expect:
        Backpack.getValueOfRepeatedChar(a) == b

        where:
        a            | b
        "LirfoLe"    | 38
        "pp"         | 16
        "ascdefghs"  | 19
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

    def "get common char"(String a, String b, String c, String d) {
        expect:
        Backpack.getCommonChar(a, b, c) == d

        where:
        a     |  b    |  c   |  d
        "A"   | "A"   | "A"  | "A"
        "bLw" | "wMf" | "wgd"| "w"
    }

    def "get value of common char"(String a, String b, String c, Integer d) {
        expect:
        Backpack.getValueOfCommonChar(a, b, c) == d

        where:
        a     |  b    |  c   |  d
        "p"   | "p"   | "p"  | 16
        "bLv" | "vMf" | "vgd"| 22
    }
}
