package sweeper;

public enum Box
{
    ZERO,
    NUM1,
    NUM2,
    NUM3,
    NUM4,
    NUM5,
    NUM6,
    NUM7,
    NUM8,
    BOMB,
    OPENED,
    CLOSED,
    FLAGED,
    BOMBED,
    NOBOMB;

    public Object image; // здесь хранится картинка, Object показывает, что хранится может что угодно, в зависимости от реализации

    Box getNextNumberBox(){
        return Box.values()[this.ordinal() + 1];
    }
}
