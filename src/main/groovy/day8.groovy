static void main(String[] args) {

    File file = new File('../../../input/day8.txt')

    List<List<Integer>> forest = Forest.parse file.text
    println "Part 1 - Number of visible trees: " + Forest.getNumVisibleTrees(forest)
    println "Part 2 - Max scenic score: " + Forest.getMaxScenicScore(forest)

}
class Forest {

    static def isVisible(List<List<Integer>> forest, Integer row, Integer column) {
        boolean isVisible = false
        for (direction in ['N','W','E','S']) {
            if (isVisibleFromDirection(forest, row, column, direction)) {
                isVisible = true
                break
            }
        }
        isVisible
    }

    static def isVisibleFromDirection(List<List<Integer>> forest, Integer row, Integer column, String direction) {

        def nextRow = row
        def nextColumn = column
        def isVisible = true
        while (true) {
            if (direction == "N")
                nextRow--
            else if (direction == "S")
                nextRow++
            else if (direction == "W")
                nextColumn--
            else if (direction == "E")
                nextColumn++

            if (nextRow < 0 || nextRow >= forest.size() || nextColumn < 0 || nextColumn >= forest[0].size())
                break

            if (getTreeAtPosition(forest, row, column) <= getTreeAtPosition(forest, nextRow, nextColumn)) {
                isVisible = false
                break
            }
        }
        isVisible
    }

    static def getTreeAtPosition(List<List<Integer>> forest, Integer row, Integer column) {
        forest[row][column]
    }

    static List<List<Integer>> parse(String data) {
        List<List<Integer>> result = []
        data.split("\\r\\n").each {line ->
            def rowData = []
            for (character in line) {
                rowData.add(character as Integer)
            }
            result.add(rowData)
        }
        result
    }

    static Integer getNumVisibleTrees(List<List<Integer>> forest) {
        Integer result = 0
        for (int row = 0; row < forest.size(); row++) {
            for (int column = 0; column < forest[row].size(); column++) {
                if (isVisible(forest, row, column)) result++
            }
        }
        result
    }

    static Integer getScenicScoreOfDirection(List<List<Integer>> forest, int row, int column, String direction) {
        def nextRow = row
        def nextColumn = column
        def scenicScore = 0
        while (true) {
            if (direction == "N")
                nextRow--
            else if (direction == "S")
                nextRow++
            else if (direction == "W")
                nextColumn--
            else if (direction == "E")
                nextColumn++

            scenicScore++

            if (nextRow <= 0 || nextRow >= forest.size() - 1 || nextColumn <= 0 || nextColumn >= forest[0].size() - 1)
                break

            if (getTreeAtPosition(forest, row, column) <= getTreeAtPosition(forest, nextRow, nextColumn))
                break
        }
        scenicScore
    }

    static Integer getScenicScore(List<List<Integer>> forest, int row, int column) {
        Integer scenicScore = 1
        for (direction in ['N','W','E','S'])
            scenicScore *= getScenicScoreOfDirection(forest, row, column, direction)
        scenicScore
    }

    static Integer getMaxScenicScore(List<List<Integer>> forest) {
        Integer maxScenicScore = 0
        for (Integer row = 0; row < forest[0].size(); row++) {
            for (Integer column = 0; column < forest.size(); column++) {
                Integer scenicScore = getScenicScore(forest, row, column)
                if (scenicScore > maxScenicScore) {
                    maxScenicScore = scenicScore
                }
            }
        }

        maxScenicScore
    }
}