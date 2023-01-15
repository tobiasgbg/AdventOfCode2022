static void main(String[] args) {

    File file = new File('../../../input/day7.txt')

    List<String> instructions = Instructions.getInstructions(file.text)
    List<Integer> directories = Instructions.getSizeOfDirectories(instructions)

    println directories
}

class Instructions {

    static getSizeOfDirectories(List<String> instructions) {
        List<Integer> result = []

        for (int i = 0;i < instructions.size(); i++) {
            if (instructions[i].startsWith("\$ cd") && !instructions[i].startsWith("\$ cd ..")) {
                result += getSizeOfDirectory instructions.subList(i+1, instructions.size())
            }
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
        String dir
        def dirListingStarted
        for (int i = 0; i < instructions.size(); i++) {
            def instruction = instructions[i]
            if (instruction.startsWith('cd')) {
                dir = instruction.split(' ')[1] as String
                println dir
            }
            else if (instruction.startsWith('ls'))
                dirListingStarted = true
            else if (dirListingStarted) {
                println i
                println instructions.subList(i, instructions.size())
                result.add([name: dir, size: getSizeOfFilesInDirectory(instructions.subList(i, instructions.size())) as Integer])
                dirListingStarted = false
            }
        }
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
            else
                break
        }
        files
    }

    static List<String> getInstructions(String rawInstructionsData) {
        rawInstructionsData.split("\\r\\n")
    }
}