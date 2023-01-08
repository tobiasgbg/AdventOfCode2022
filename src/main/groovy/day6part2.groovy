static void main(String[] args) {

    File file = new File('../../../input/day6part1.txt')

    def result = DataStream.getPosOfFirstMarker(file.text, 14)

    println result
}