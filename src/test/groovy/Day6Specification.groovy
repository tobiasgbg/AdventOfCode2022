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

    def "is marker"(String a, boolean b) {
        expect:
        DataStream.isMarker(a) == b

        where:
        a      | b
        "bvwb" | false
        "pdvj" | true
    }
}
