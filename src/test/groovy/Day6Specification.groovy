import spock.lang.Specification

class Day6Specification extends Specification {
    def "get pos of first marker"(String a, Integer b) {
        expect:
        DataStream.getPosOfFirstMarker(a) == b

        where:
        a      | b
        "bvwbjplbgvbhsrlpgdmjqwftvncz"      | 5
        "nppdvjthqldpwncqszvftbrmjlhg"      | 6
        "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg" | 10
        "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"  | 11
    }

    def "get pos of first marker long seq"(String a, Integer b) {
        expect:
        DataStream.getPosOfFirstMarker(a, 14) == b

        where:
        a      | b
        "mjqjpqmgbljsphdztnvjfqwrcgsmlb"     | 19
        "bvwbjplbgvbhsrlpgdmjqwftvncz"       | 23
        "nppdvjthqldpwncqszvftbrmjlhg"       | 23
        "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"  | 29
    }

    def "is marker"(String a, boolean b) {
        expect:
        DataStream.isMarker(a) == b

        where:
        a      | b
        "bvwb" | false
        "pdvj" | true
    }

    def "is marker"(String a, boolean b) {
        expect:
        DataStream.isMarker(a) == b

        where:
        a      | b
        "bvwb" | false
        "pdvj" | true
    }
}
