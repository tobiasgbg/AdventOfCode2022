static void main(String[] args) {

    File file = new File('../../../input/day5part1.txt')

    List<String> stacksDesc = CrateStack.getStackDescription(file.text)
    List<String> stacks = CrateStack.getStacks(stacksDesc)

    file.text.eachMatch("move.*") {stacks = CrateStack.moveCrates(stacks, it, true)}

    def result = ""
    for (stack in stacks) {
        result += stack.reverse()[0]
    }

    println result
}