static void main(String[] args) {

    File file = new File('../../../input/day7.txt')

    List<String> instructions = Instructions.getInstructions file.text
    List<Integer> directories = Instructions.getTotalSizeOfDirectories instructions
    List<Integer> sortedDirectories = directories.sort()
    println sortedDirectories.findAll{it < 100000}.sum()
}

class Instructions {
    static getSizeOfDirectories(List<String> instructions) {
        List<Integer> result = []

        for (int i = 0;i < instructions.size(); i++) {
            if (instructions[i].startsWith("\$ cd") && !instructions[i].startsWith("\$ cd ..")) {
                result += getSizeOfFilesInDirectory instructions.subList(i+1, instructions.size())
            }
        }
        result
    }

    static getTotalSizeOfDirectories(List<String> instructions) {
        List<Integer> result = []
        List<Map> directoryInformation = getDirectoryInformation instructions

        directoryInformation.each { directory ->
            Integer sizeOfSubDir = getSizeOfSubDirectories directory.name, directoryInformation
            result += directory.size + sizeOfSubDir
        }
        result
    }

    static getSizeOfSubDirectories(String rootDir, List<Map> directories) {
        def result = 0
        for (dir in directories) {
            if (dir.name.startsWith(rootDir) && dir.name != rootDir)
                result += dir.size
        }
        result
    }

    static getSizeOfDirectory(List<String> files) {
        Integer result = 0
        files.each {result += it.split(" ").first() as Integer}
        result
    }

    static getDirectoryInformation(List<String> instructions) {
        def result = []
        List<String> path = []
        def dirListingStarted
        for (int i = 0; i < instructions.size(); i++) {
            def instruction = instructions[i]
            if (instruction.startsWith('cd ..'))
                path.removeLast()
            else if (instruction.startsWith('cd')) {
                path.add(instruction.split(' ')[1] as String)
            }
            else if (instruction.startsWith('ls'))
                dirListingStarted = true
            else if (dirListingStarted) {
                result.add([name: getPathAsString(path), size: getSizeOfFilesInDirectory(instructions.subList(i, instructions.size())) as Integer])
                dirListingStarted = false
            }
        }
        result
    }

    static getPathAsString(List<String> path) {
        String result = ""
        for (dir in path)
            result += result == "/" || result == "" ? dir : "/" + dir
        result
    }

    static getSizeOfFilesInDirectory(List<String> instructions) {
        def filesInDirectory = getFilesInDirectory(instructions)
        getSizeOfDirectory(filesInDirectory)
    }

    static getFilesInDirectory(List<String> instructions) {
        List<String> files = []
        for (instruction in instructions) {
            def first= instruction.split(" ").first()
            if (first.isInteger())
                files += instruction
            else if (instruction.startsWith('dir') || instruction.startsWith("\$ ls"))
                continue
            else
                break
        }
        files
    }

    static List<String> getInstructions(String rawInstructionsData) {
        List<String> result = []
        rawInstructionsData.split("\\r\\n").each {
            result += it.startsWith('\$') ? it.substring(2, it.size()) : it
        }
        result
    }
}