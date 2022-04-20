package sweeper;
//пакетный класс для хранения двух слоёв: бомб и флагов

class Matrix
{
    private Box [] [] matrix;

    Matrix (Box defaultBox)
    {
        matrix = new Box[Ranges.getSize().x][Ranges.getSize().y];
        for (Coord coord : Ranges.getAllCoords())
            matrix [coord.x] [coord.y] = defaultBox;
    }

    Box get (Coord coord)
    {
        if (Ranges.inRange (coord)) // проверяем находится ли координата в пределах экрана
            return matrix [coord.x] [coord.y];
        return null;
    }

    void set (Coord coord, Box box)
    {
        if (Ranges.inRange (coord))
            matrix [coord.x] [coord.y] = box;
    }
}