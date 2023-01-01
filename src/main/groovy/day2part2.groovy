/*
--- Part Two ---
The Elf finishes helping with the tent and sneaks back over to you. "Anyway, the second column says how the round needs to end: X means you need to lose, Y means you need to end the round in a draw, and Z means you need to win. Good luck!"

The total score is still calculated in the same way, but now you need to figure out what shape to choose so the round ends as indicated. The example above now goes like this:

In the first round, your opponent will choose Rock (A), and you need the round to end in a draw (Y), so you also choose Rock. This gives you a score of 1 + 3 = 4.
In the second round, your opponent will choose Paper (B), and you choose Rock so you lose (X) with a score of 1 + 0 = 1.
In the third round, you will defeat your opponent's Scissors with Rock for a score of 1 + 6 = 7.
Now that you're correctly decrypting the ultra top secret strategy guide, you would get a total score of 12.

Following the Elf's instructions for the second column, what would your total score be if everything goes exactly according to your strategy guide?
 */

static void main(String[] args) {

    File file = new File('../../../input/day2part2.txt')
    Integer result = 0

    file.eachLine {result += Day2Part2Game.getTotalScore(it.split(" ")[0], it.split(" ")[1])}

    println("Sum ${result}")
}

class Day2Part2Game {
    static Integer getTotalScore(String opponent, String you) {
        return getGameScore(you) + getDerivedSelectionScore(opponent, you)
    }

    static Integer getGameScore(String you) {
        if (you == "X")
            return 0
        else if (you == "Y")
            return 3
        else if (you == "Z")
            return 6
    }

    static Integer getDerivedSelectionScore(String opponent, String you) {
        if (you == "X")
            return getSelectionScore(getLosingSelection(opponent))
        else if (you == "Y")
            return getSelectionScore(opponent)
        else if (you == "Z")
            return getSelectionScore(getWinningSelection(opponent))
    }

    static Integer getSelectionScore(String selection) {
        if (selection == "A")
            return 1
        else if (selection == "B")
            return 2
        else if (selection == "C")
            return 3
    }

    static String getWinningSelection(String opponent) {
        if (opponent == "A")
            return "B"
        else if (opponent == "B")
            return "C"
        else if (opponent == "C")
            return "A"
    }

    static String getLosingSelection(String opponent) {
        if (opponent == "A")
            return "C"
        else if (opponent == "B")
            return "A"
        else if (opponent == "C")
            return "B"
    }
}
