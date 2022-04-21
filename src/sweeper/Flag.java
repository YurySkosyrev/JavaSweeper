package sweeper;
/*
класс уровеня флагов
 */

class Flag {
    private Matrix flagMap;

    void start(){
        flagMap = new Matrix(Box.CLOSED);
    }

    Box get (Coord coord){
        return flagMap.get(coord);
    }

    public void setOpenedToBox(Coord coord) {
        flagMap.set(coord, Box.OPENED);
    }

    public void setFlagedToBox(Coord coord) {
        flagMap.set(coord, Box.FLAGED);
    }

    public void toggleFlagedToBox(Coord coord) {
        switch (flagMap.get(coord)){
            case FLAGED : setCloseToBox(coord); break;
            case CLOSED : setFlagedToBox(coord); break;
        }
    }

    private void setCloseToBox(Coord coord) {
        flagMap.set(coord, Box.CLOSED);
    }
}
