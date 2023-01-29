import spock.lang.Specification

class Day9Specification extends Specification {

    def "get number of positions tail has visited"(String a, Integer b) {
        expect:
        Rope.getNumPositionsTailVisited(a) == b

        where:
        a   | b
        "U 1"        | 1
        "U 2"        | 2
        'U 2\r\nD 2' | 2
    }

    def "get all positions tail has visited"(String a, List<Map> b) {
        expect:
        Rope.getPositionsTailVisited(a) == b

        where:
        a   | b
        "U 1" | [[row: 0, column: 0]]
        "U 2" | [[row: 0, column: 0], [row: -1, column: 0]]
        'U 2\r\nD 2' | [[row: 0, column: 0], [row: -1, column: 0]]
    }

    def "get positions tail has visited"(Integer a, String b, List<Map<Integer, Integer>> c, Map d, Map e, Map f) {
        expect:
        Rope.getPositionsTailVisited(a, b, c, d, e) == f

        where:
        a   | b   |  c      | d     | e | f
        1   | "U" | [] | [row: 0, column: 0]   | [row: 0, column: 0] | [visited: [[row: 0, column: 0]], headPos: [row: -1, column: 0], tailPos: [row: 0, column: 0]]
        2   | "U" | [] | [row: 0, column: 0]   | [row: 0, column: 0] | [visited:[[row:0, column:0], [row:-1, column:0]], headPos:[row:-2, column:0], tailPos:[row:-1, column:0]]
        2   | "R" | [] | [row: 0, column: 0]   | [row: 0, column: 0] | [visited:[[row:0, column:0], [row:0, column:1]], headPos:[row:0, column:2], tailPos:[row:0, column:1]]
        3   | "R" | [] | [row: 0, column: 0]   | [row: 0, column: 0] | [visited:[[row:0, column:0], [row:0, column:1], [row:0, column:2]], headPos:[row:0, column:3], tailPos:[row:0, column:2]]
        2   | "R" | [] | [row: 0, column: 0]   | [row: 1, column: 0] | [visited:[[row:1, column:0], [row:0, column:1]], headPos:[row:0, column:2], tailPos:[row:0, column:1]]
    }

    def "get directions"(String a, Map<String, Integer> b) {
        expect:
        Rope.getDirections(a) == b

        where:
        a              | b
        "U 2"          | [[direction: "U", steps: 2]]
        'U 2\r\nD 2'   | [[direction: "U", steps: 2], [direction: "D", steps: 2]]
    }

    def "move head one step"(String a, Map<Integer, Integer> b, Map<Integer, Integer> c) {
        expect:
        Rope.moveHead(a, b) == c

        where:
        a   | b                     |  c
        "U" | [row: 0, column: 0]   | [row: -1, column: 0]
        "D" | [row: 0, column: 0]   | [row: 1, column: 0]
        "L" | [row: 0, column: 0]   | [row: 0, column: -1]
        "R" | [row: 0, column: 0]   | [row: 0, column: 1]
    }

    def "get new tail pos"(Map a, Map b, Map<Integer, Integer> c) {
        expect:
        Rope.moveTail(a, b) == c

        where:
        a                     | b                   |  c
        [row: 2, column: 0]   | [row: 0, column: 0] | [row: 1, column: 0]
        [row: 1, column: 0]   | [row: 0, column: 0] | [row: 0, column: 0]
        [row: 2, column: 0]   | [row: 0, column: 0] | [row: 1, column: 0]
        [row: 2, column: 3]   | [row: 4, column: 2] | [row: 3, column: 3]
    }

    def "should tail move"(Map a, Map b, boolean c) {
        expect:
        Rope.shouldTailMove(a, b) == c

        where:
        a   | b   |  c
        [row: 1, column: 0]   | [row: 0, column: 0] | false
        [row: 2, column: 0]   | [row: 0, column: 0] | true
        [row: 1, column: 1]   | [row: 0, column: 0] | false
    }
}