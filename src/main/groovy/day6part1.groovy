static void main(String[] args) {

    File file = new File('../../../input/day6part1.txt')

    def result = DataStream.getPosOfFirstMarker(file.text)

    println result
}

class DataStream {
    static def getPosOfFirstMarker(String data, int repCharCount = 4) {
        int index
        for (index = repCharCount; index < data.length(); index++) {
            if (isMarker(data.substring(index-repCharCount, index))) break
        }
        index
    }

    static def isMarker(String data) {
        for (character in data) {
            if (data.count(character) > 1) return false
        }
        true
    }
}