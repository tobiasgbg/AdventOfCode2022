static void main(String[] args) {

    File file = new File('day2part1.txt')
    Integer result = 0

    file.eachLine {result += Game.getScore(it.split(" ")[0], it.split(" ")[1])}

    println("Sum ${result}")
}

class Day2Part2Game {
    static Integer getScore(String opponent, String you)
    {
        return ["X": 1, "Y": 2, "Z":3][you]+["AX": 3,"AY": 6, "AZ": 0, "BX": 0, "BY": 3, "BZ": 6, "CX": 6, "CY": 0, "CZ": 3][opponent+you]
    }
}
