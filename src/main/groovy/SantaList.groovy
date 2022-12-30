class SantaList {
    private String Data

    SantaList(String data)
    {
        this.Data = data
    }

    private List<Integer> getCombinedList() {
        List<Integer> result = []
        this.Data.split("\\r\\n\\r\\n").each {result.add(getSum(it)) }
        return result
    }

    List<Integer> getSortedList() {
        List<Integer> result = getCombinedList().sort().reverse()
        return result
    }

    static int getSum(String input)
    {
        int sum = 0
        input.trim().split('\\r\\n').each {sum += !it.isInteger() ? 0 : it as Integer}
        return sum
    }
}
