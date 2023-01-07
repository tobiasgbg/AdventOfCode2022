static void main(String[] args) {

    File file = new File('../../../input/day5part1.txt')

    List<String> stacksDesc = CrateStack.getStackDescription(file.text)
    List<String> stacks = CrateStack.getStacks(stacksDesc)

    file.text.eachMatch("move.*") {stacks = CrateStack.moveCrates(stacks, it)}

    def result = ""
    for (stack in stacks) {
        result += stack.reverse()[0]
    }

    println result
}

class CrateStack {
    static def getStackDescription(String exerciseInput) {
        List<String> result = []
        for (line in exerciseInput.split("\\r\\n")) {
            if (line.trim().startsWith("1")) break
            result += line
        }
        result
    }

    static def moveCrates(List<String> stacks, String instruction) {
        def instructionList = instruction.split(" ")
        moveCrates(stacks, instructionList[1] as Integer, instructionList[3] as Integer, instructionList[5] as Integer)
    }

    static def moveCrates(List<String> stacks, Integer noCratesToMove, Integer sourceStack, Integer targetStack) {
        def stacksAfterMove= moveCrates(stacks[sourceStack - 1], stacks[targetStack - 1], noCratesToMove)
        stacks[sourceStack - 1] = stacksAfterMove[0]
        stacks[targetStack - 1] = stacksAfterMove[1]
        stacks
    }

    static def moveCrates(String sourceStack, String targetStack, Integer noCratesToMove) {
        [removeCrates(sourceStack, noCratesToMove),
                addCrates(targetStack, getTopCrates(sourceStack, noCratesToMove).reverse())]
    }

    static def addCrates(String stack, String crates) {
        stack + crates
    }

    static def addCrates(List<String> crates1, List<String> crates2) {
        List<String> result = []
        for (int i = 0; i < crates1.size(); i++) {
            result.add((crates1[i] ?: "") + (crates2[i] ?: ""))
        }
        result
    }

    static def getTopCrates(String stack, Integer noOfCrates) {
        stack.substring(stack.length() - noOfCrates, stack.length())
    }

    static def removeCrates(String stack, Integer noCratesToRemove) {
        stack.substring(0, stack.length() - (noCratesToRemove) )
    }

    static String getCrate(String crateDescription) {
        crateDescription.substring(1, 2).trim()
    }

    static def getCrates(String cratesDescriptionLine) {
        List<String> result = []
        for (int i = 0; i < cratesDescriptionLine.length(); i+=4) {
            result.add(getCrate(cratesDescriptionLine.substring(i, i+3)))
        }
        result
    }

    static List<String> getStacks(String lines) {
        getStacks(getStackDescription(lines))
    }

    static List<String> getStacks(List<String> lines) {
        List<String> result = []
        for (it in lines) {
            result = addCrates getCrates(it), result
        }
        result
    }
}