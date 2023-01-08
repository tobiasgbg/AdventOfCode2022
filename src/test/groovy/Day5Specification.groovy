import spock.lang.Specification

class Day5Specification extends Specification {

    def "get crates"(String a, List<String> b) {
        expect:
        CrateStack.getCrates(a) == b

        where:
        a         |  b
        "[A]"     | ["A"]
        "    [B]" | ["", "B"]
        "[A] [B]" | ["A", "B"]
    }

    def "get crate"(String a, String b) {
        expect:
        CrateStack.getCrate(a) == b

        where:
        a         |  b
        "[A]"     | "A"
        "   "     | ""
    }

    def "get stacks full string"(String a, List<String> b) {
        expect:
        CrateStack.getStacks(a) == b

        where:
        a               |  b
        "[A]"           | ["A"]
        "[A]\r\n[B]"    | ["BA"]
    }

    def "get stack description"(String a, List<String> b) {
        expect:
        CrateStack.getStackDescription(a) == b

        where:
        a               |  b
        "[A]\r\n 1"   | ["[A]"]
    }

    def "get stacks"(List<String> a, List<String> b) {
        expect:
        CrateStack.getStacks(a) == b

        where:
        a               |  b
        ["[A]"]         | ["A"]
        ["[A]","[B]"]   | ["BA"]
    }

    def "move crates"(String a, String b, Integer c, String d, String e) {
        expect:
        CrateStack.moveCrates(a, b, c) == [d, e]

        where:
        a     |  b     |  c | d   |  e
        "A"   | ""     |  1 | ""  | "A"
        "AB"  | "A"    |  1 | "A" | "AB"
        "ABC" | "F"    |  2 | "A" | "FCB"
    }

    def "move crates full instruction"(List<String> a, String b) {
        expect:
        CrateStack.moveCrates(a, b) == c

        where:
        a          |  b                     |  c
        ["A",""]   | "move 1 from 1 to 2"   | ["","A"]
        ["AB",""]  | "move 1 from 1 to 2"   | ["A","B"]
        ["AB",""]  | "move 2 from 1 to 2"   | ["","BA"]
    }

    def "move multiple crates full instruction"(List<String> a, String b) {
        expect:
        CrateStack.moveCrates(a, b, true) == c

        where:
        a          |  b                     |  c
        ["A",""]   | "move 1 from 1 to 2"   | ["","A"]
        ["AB",""]  | "move 1 from 1 to 2"   | ["A","B"]
        ["AB",""]  | "move 2 from 1 to 2"   | ["","AB"]
    }

    def "move crates arguments"(List<String> a, Integer b, Integer c, Integer d, List<String> e) {
        expect:
        CrateStack.moveCrates(a, b, c, d) == e

        where:
        a          |  b     |  c | d   |  e
        ["A",""]   | 1      |  1 | 2   | ["","A"]
    }

    def "get top crates"(String a, Integer b, String c) {
        expect:
        CrateStack.getTopCrates(a, b) == c

        where:
        a      | b |  c
        "AB"   | 1 | "B"
        "JPZY" | 1 | "Y"
        "JPZY" | 3 | "PZY"
    }

    def "add crates list"(List<String> a, List<String> b, List<String> c) {
        expect:
        CrateStack.addCrates(a, b) == c

        where:
        a         | b          |  c
        ["A"]     | []         | ["A"]
        ["",""]   | ["A","B"]  | ["A", "B"]
        ["A","B"] | ["B", "A"] | ["AB", "BA"]
    }

    def "remove crates"(String a, Integer b, String c) {
        expect:
        CrateStack.removeCrates(a, b) == c

        where:
        a      | b |  c
        "AB"   | 1 | "A"
        "JPZY" | 1 | "JPZ"
        "JPZY" | 3 | "J"
    }

    def "add crates"(String a, String b, String c) {
        expect:
        CrateStack.addCrates(a, b) == c

        where:
        a      |  b     |  c
        "AB"   | "AT"   |  "ABAT"
        "JPZY" | "T"    |  "JPZYT"
        "JPZY" | ""     |  "JPZY"
    }
}
