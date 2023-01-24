import spock.lang.Specification


class Day8Specification extends Specification {

    def "is visible"(List<List<Integer>> a, Integer b, Integer c, Boolean d) {
        expect:
        Forest.isVisible(a, b, c) == d

        where:
        a                                           | b   | c    | d
        [[0]]                                       | 0   | 0    | true
        [[0,0,0], [0,1,0], [0,0,0]]                 | 1   | 1    | true
        [[1,1,1], [1,1,1], [1,1,1]]                 | 1   | 1    | false
        [[1,1,1], [1,1,1], [1,1,0]]                 | 1   | 1    | false
        [[0,1,0], [1,1,1], [1,1,0]]                 | 1   | 1    | false
        [[1,1,1], [0,1,1], [0,1,0]]                 | 1   | 1    | true
    }

    def "parse data"(String a, List<List<Integer>> b) {
        expect:
        Forest.parse(a) == b

        where:
        a                                           | b
        "0"                                         | [[0]]
        "101\r\n101\r\n101"                         | [[1,0,1],[1,0,1],[1,0,1]]
    }

    def "get number of visible trees"(List<List<Integer>> a, Integer b) {
        expect:
        Forest.getNumVisibleTrees(a) == b

        where:
        a                                           | b
        [[0]]                                       | 1
        [[1,1,1],[1,0,1],[1,1,1]]                   | 8
    }

    def "is visible from direction"(List<List<Integer>> a, Integer b, Integer c, String d, Boolean e) {
        expect:
        Forest.isVisibleFromDirection(a, b, c, d) == e

        where:
        a                                           | b   | c    | d    | e
        [[1,1,1],[1,1,1],[1,1,1]]                   | 1   | 1    | 'N'  | false
        [[1,0,1],[1,1,1],[1,1,1]]                   | 1   | 1    | 'N'  | true
        [[1,1,1],[1,1,1],[1,0,1]]                   | 1   | 1    | 'S'  | true
        [[1,1,1],[1,1,1],[1,1,1]]                   | 1   | 1    | 'S'  | false
        [[1,0,1],[1,1,0],[1,1,1]]                   | 1   | 1    | 'E'  | true
    }

    def "get tree at position"(List<List<Integer>> a, Integer b, Integer c, Integer d) {
        expect:
        Forest.getTreeAtPosition(a, b, c) == d

        where:
        a                                           | b   | c    | d
        [[1,0,1],[1,1,1],[1,1,1]]                   | 1   | 1    | 1
        [[1,0,1],[1,1,1],[1,1,1]]                   | 0   | 1    | 0
        [[1,1,2],[1,3,1],[1,0,1]]                   | 1   | 2    | 1
        [[1,1,1],[1,1,1],[1,1,8]]                   | 2   | 2    | 8
        [[1,0,1],[1,1,8],[1,1,1]]                   | 1   | 2    | 8
    }
}