import math.*;


static private final Scanner sc = new Scanner(System.in);

/**
 * Находит минимальное расстояние между парой точек из списка.
 * @param points список точек.
 * @param method способ определения расстояния.
 * @return  расстояние между парой ближайших точек из числа тех, что в списке.
 */
public static double findMinDistance(List<Point> points, Rangefinder method) {
    double min = Double.POSITIVE_INFINITY;
    for (int i = 0; i < points.size() - 1; i++)
        for (int j = i + 1; j < points.size(); j++) {
            double distance = method.findDistance(points.get(i), points.get(j));
            if (distance < min) min = distance;
        }
    return min;
}

private static Point getPoint(int i) {
    IO.println("Введи координаты %d-й точки через пробел:".formatted(i + 1));
    int[] coor = Arrays.stream(sc.nextLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();
    return new Point(coor[0], coor[1]);
}



void main() {
    List<Point> points = new ArrayList<>();
    for (int i = 0; i < 5; i++)
        points.add(getPoint(i));
    IO.print("Кратчайшее здесь расстояние по Эвклиду: ");
    IO.println(findMinDistance(points, new Euclidean()));
    IO.print("Кратчайшее здесь расстояние по Нойманну: ");
    IO.println(findMinDistance(points, new Neumannean()));
    IO.print("Кратчайшее здесь расстояние по Муру: ");
    IO.println(findMinDistance(points, new Moorean()));

}

