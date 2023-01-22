import spock.lang.Specification


class Day7Specification extends Specification {
    def "check that we can read raw instruction data"() {
    given:
        String rawInstructionsData = """\$ cd /\r\n\$ ls\r\n14848514 b.txt\r\n"""

    when:
        List<String> instructions = Instructions.getInstructions(rawInstructionsData)
        println "instructions = " + instructions
        List<Integer> directories = Instructions.getTotalSizeOfDirectories(instructions)
        println "directories = " + directories

    then:
        directories == [14848514]
    }

    def "get list of instructions"(String a, List<String> b) {
        expect:
        Instructions.getInstructions(a) == b

        where:
        a                                      | b
        '$ cd /\r\n$ ls\r\n14848514 b.txt\r\n' | ['cd /', 'ls', '14848514 b.txt']
    }

    def "size of dir"(List<String> a, Integer b) {
        expect:
        Instructions.getSizeOfDirectory(a) == b

        where:
        a                                 | b
        ["122 b"]                         | 122
        ["122 b", "111 a"]                | 233
    }

    def "get total size on drive"(List<String> a, Integer b) {
        expect:
        Instructions.getSizeOnDrive(a) == b

        where:
        a                                 | b
        [12]                              | 12
        [13, 12]                          | 13
    }

    def "get size of sub dirs"(String a, List<Map> b, Integer c) {
        expect:
        Instructions.getSizeOfSubDirectories(a, b) == c

        where:
        a    | b                                                             | c
        "/"  | [[name:'/', size:122], [name:'/a', size: 111]]                | 111
    }

    def "get dir information"(List<String> a, List<Map> b) {
        expect:
        Instructions.getDirectoryInformation(a) == b

        where:
        a                                                             | b
        ['cd /', 'ls','122 b']                                        | [[name:'/', size:122]]
        ['cd /', 'ls','dir a','122 b']                                | [[name:'/', size:122]]
        ['cd /', 'ls','dir a','122 b', 'cd a', 'ls', '111 g']         | [[name:'/', size:122], [name:'/a', size: 111]]
    }

    def "get size of dirs"(List<String> a, List<Map> b) {
        expect:
        Instructions.getSizeOfDirectories(a) == b

        where:
        a                                                                                        | b
        ["\$ cd /", "\$ ls", "14848514 b.txt"]                                                   | [14848514]
        ["\$ cd /", "\$ ls", "14848514 b.txt", "dir b", "\$ cd b", "\$ ls", "14848514 a.txt"]    | [14848514, 14848514]
    }

    def "get total size of dirs"(List<String> a, List<Map> b) {
        expect:
        Instructions.getTotalSizeOfDirectories(a) == b

        where:
        a                                                                                        | b
        ["cd /", "ls", "14848514 b.txt"]                                                   | [14848514]
        ["cd /", "ls", "14848514 b.txt", "dir b", "cd b", "ls", "14848514 a.txt"]    | [29697028, 14848514]
    }

    def "get files in directory"(List<String> a, List<String> b) {
        expect:
        Instructions.getFilesInDirectory(a) == b

        where:
        a                                           | b
        ["122 b"]                                   | ["122 b"]
        ["122 b", "111 a"]                          | ["122 b", "111 a"]
        ["122 b", "111 a", "\$ cd a"]               | ["122 b", "111 a"]
        ["122 b", "111 a", "\$ cd a", "\$ ls", "123 c"]      | ["122 b", "111 a"]
    }

    def "join path"(List<String> a, String b) {
        expect:
        Instructions.getPathAsString(a) == b

        where:
        a                                           | b
        ["/"]                                       | "/"
        ["/", "a"]                                  | "/a"
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