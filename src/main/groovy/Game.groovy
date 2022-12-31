class Game {
    static Integer getScore(String opponent, String you)
    {
        return ["X": 1, "Y": 2, "Z":3][you]+["AX": 3,"AY": 6, "AZ": 0, "BX": 0, "BY": 3, "BZ": 6, "CX": 6, "CY": 0, "CZ": 3][opponent+you]
    }
}
