static void main(String[] args) {

    File file = new File('../../../input/day4part1.txt')
    Integer result = 0

    file.eachLine {if (CampCleanup.isIntersecting(it)){result++}}

    println("Sum ${result}")
}

class CampCleanup {
    static def isIntersecting(String a) {
        return containsOther(a.split(",")[0],a.split(",")[1])
                || containsOther(a.split(",")[1],a.split(",")[0])
    }

    static def isIntersecting(String a, String b) {
        return containsOther(a,b) || containsOther(b,a)
    }

    static def containsOther(String a, String b) {
        return (((a.split("-")[0] as Integer) <= (b.split("-")[0] as Integer))
                && ((b.split("-")[1] as Integer) <= (a.split("-")[1]) as Integer))
    }
}