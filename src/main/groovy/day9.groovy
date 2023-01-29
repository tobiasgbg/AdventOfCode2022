static void main(String[] args) {

    File file = new File('../../../input/day9.txt')

    println "Part 1 - Number of visible trees: " + Rope.getNumPositionsTailVisited(file.text)
}


class Rope {

    static Map getPositionsTailVisited(Integer steps, String direction, List<Map<Integer, Integer>> visitedPos, Map headPos, Map tailPos) {

        Map newHeadPos = ["row": headPos.row, "column": headPos.column]
        Map newTailPos = ["row": tailPos.row, "column": tailPos.column]
        List<Map<Integer, Integer>> visitedPositionsByTail = visitedPos.collect()

        if (!(newTailPos in visitedPositionsByTail))
            visitedPositionsByTail.add(newTailPos)

        for (int i = 0; i < steps; i++) {
            newHeadPos = moveHead(direction, newHeadPos)
            newTailPos = moveTail(newHeadPos, newTailPos)

            if (!(newTailPos in visitedPositionsByTail))
                visitedPositionsByTail.add(newTailPos)
        }

        [visited: visitedPositionsByTail, headPos: newHeadPos, tailPos: newTailPos]
    }

    static Map<Integer, Integer> moveTail(Map headPos, Map tailPos) {
        Map newTailPos = ["row": tailPos.row, "column": tailPos.column]

        if (shouldTailMove(headPos, tailPos)) {
            // Move right/left
            if (headPos.row == tailPos.row){
                newTailPos.column += tailPos.column < headPos.column ? 1 : - 1
            }
            // Move up/down
            else if (headPos.column == tailPos.column) {
                newTailPos.row += tailPos.row < headPos.row ? 1 : - 1
            }
            // Move diagonally
            else {
                newTailPos.row += tailPos.row < headPos.row ? 1 : - 1
                newTailPos.column += tailPos.column < headPos.column ? 1 : - 1
            }
        }

        newTailPos
    }

    static boolean shouldTailMove(Map headPos, Map tailPos) {
        tailPos.row > headPos.row + 1 || tailPos.row < headPos.row - 1 ||
                tailPos.column > headPos.column + 1 || tailPos.column < headPos.column - 1
    }

    static Map<Integer, Integer> moveHead(String direction, Map<Integer, Integer> headPos) {
        Map<Integer, Integer> newHeadPos = ["row": headPos.row, "column": headPos.column]
        switch (direction) {
            case "U":
                newHeadPos.row--
                break
            case "D":
                newHeadPos.row++
                break
            case "R":
                newHeadPos.column++
                break
            case "L":
                newHeadPos.column--
                break
        }
        newHeadPos
    }

    static List<Map<String, Integer>> getDirections(String input) {
        List<Map> result = []
        input.split('\r\n').each {
            result.add([direction: it.split(" ")[0], steps: it.split(" ")[1] as Integer])
        }
        result
    }

    static List<Map> getPositionsTailVisited(String directionsInput) {
        List<Map> visited = []
        Map headPos = [row: 0, column: 0]
        Map tailPos = [row: 0, column: 0]

        List directions = getDirections(directionsInput)

        for (direction in directions) {
            def result = getPositionsTailVisited(direction.steps, direction.direction, visited, headPos, tailPos)
            visited = result.visited
            headPos = result.headPos
            tailPos = result.tailPos
        }

        visited
    }

    static Integer getNumPositionsTailVisited(String directions) {
        getPositionsTailVisited(directions).size()
    }
}