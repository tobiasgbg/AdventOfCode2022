import spock.lang.Specification


class Day7Specification extends Specification {
    def "check that we can read raw instruction data"() {
    given:
        String rawInstructionsData = """\$ cd /\r\n\$ls\r\n14848514 b.txt\r\n"""

    when:
        List<String> instructions = Instructions.getInstructions(rawInstructionsData)
        print instructions
        List<Integer> directories = Instructions.getSizeOfDirectories(instructions)

    then:
        directories == [14848514]
    }

    def "get list of instructions"(String a, List<String> b) {
        expect:
        Instructions.getInstructions(a) == b

        where:
        a                                      | b
        '$ cd /\r\n$ ls\r\n14848514 b.txt\r\n' | ['$ cd /', '$ ls', '14848514 b.txt']
    }

    def "size of dir"(List<String> a, Integer b) {
        expect:
        Instructions.getSizeOfDirectory(a) == b

        where:
        a                                 | b
        ["122 b"]                         | 122
        ["122 b", "111 a"]                | 233
    }

    def "get dir information"(List<String> a, List<Map> b) {
        expect:
        Instructions.getDirectoryInformation(a) == b

        where:
        a                              | b
        ['cd /', 'ls','122 b']         | [[name:'/', size:122]]
    }

    def "get files in directory"(List<String> a, List<String> b) {
        expect:
        Instructions.getFilesInDirectory(a) == b

        where:
        a                                           | b
        ["122 b"]                                   | ["122 b"]
        ["122 b", "111 a"]                          | ["122 b", "111 a"]
        ["122 b", "111 a", "\$ cd a"]               | ["122 b", "111 a"]
        ["122 b", "111 a", "\$ cd a", "123 c"]      | ["122 b", "111 a"]
    }

    def "get size of files in directory"(List<String> a, Integer b) {
        expect:
        Instructions.getSizeOfFilesInDirectory(a) == b

        where:
        a                                           | b
        ["122 b"]                                   | 122
        ["122 b", "111 a"]                          | 233
        ["122 b", "111 a", "\$ cd a"]               | 233
        ["122 b", "111 a", "\$ cd a", "123 c"]      | 233
    }
}