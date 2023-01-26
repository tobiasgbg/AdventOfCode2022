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

    def "get scenic score of direction"(List<List<Integer>> a, Integer b, Integer c, String d, Integer e) {
        expect:
        Forest.getScenicScoreOfDirection(a, b, c, d) == e

        where:
        a                                           | b   | c    | d     | e
        [[3,0,3,7,3], [2,5,5,1,2], [6,5,3,3,2], [3,3,5,4,9], [3,5,3,9,0]] | 3 | 2 | "N" | 2
        [[3,0,3,7,3], [2,5,5,1,2], [6,5,3,3,2], [3,3,5,4,9], [3,5,3,9,0]] | 3 | 2 | "S" | 1
        [[3,0,3,7,3], [2,5,5,1,2], [6,5,3,3,2], [3,3,5,4,9], [3,5,3,9,0]] | 3 | 2 | "E" | 2
        [[3,0,3,7,3], [2,5,5,1,2], [6,5,3,3,2], [3,3,5,4,9], [3,5,3,9,0]] | 3 | 2 | "W" | 2
        [[0]]                                       | 0   | 0    | "E"   | 1
        [[0,0,0], [0,1,0], [0,0,0]]                 | 1   | 1    | "S"   | 1
        [[1,1,1], [1,1,1], [1,1,1]]                 | 1   | 2    | "N"   | 1
        [[1,1,1], [1,1,1], [1,1,0]]                 | 1   | 2    | "S"   | 1
        [[0,1,1], [1,1,0], [1,1,0]]                 | 0   | 2    | "S"   | 1
        [[1,1,1], [0,1,1], [0,1,0]]                 | 1   | 1    | "W"   | 1
    }

    def "get max score"(List<List<Integer>> a, Integer b) {
        expect:
        Forest.getMaxScenicScore(a) == b

        where:
        a                                           | b
        [[0]]                                       | 1
        [[0,0,0], [0,1,0], [0,0,0]]                 | 1
        [[1,1,1], [1,1,1], [1,1,1]]                 | 1
        [[1,1,1], [1,1,1], [1,1,0]]                 | 1
        [[0,1,1], [1,1,0], [1,1,0]]                 | 1
        [[1,1,1], [0,1,1], [0,1,0]]                 | 1
        [[3,0,3,7,3], [2,5,5,1,2], [6,5,3,3,2], [3,3,5,4,9], [3,5,3,9,0]] | 8
    }

    def "get total scenic score"(List<List<Integer>> a, Integer b, Integer c, Integer d) {
        expect:
        Forest.getScenicScore(a, b, c) == d

        where:
        a                                           | b   | c    | d
        [[0]]                                       | 0   | 0    | 1
        [[0,0,0], [0,1,0], [0,0,0]]                 | 1   | 1    | 1
        [[1,1,1], [1,1,1], [1,1,1]]                 | 1   | 2    | 1
        [[1,1,1], [1,1,1], [1,1,0]]                 | 1   | 2    | 1
        [[0,1,1], [1,1,0], [1,1,0]]                 | 0   | 2    | 1
        [[1,1,1], [0,1,1], [0,1,0]]                 | 1   | 1    | 1
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