import javax.swing.*;
import java.awt.*;
import sweeper.Box;
import sweeper.Coord;
import sweeper.Game;
import sweeper.Ranges;

public class JavaSweeper extends JFrame
{
    private Game game;

    private JPanel panel;
    private final int COLS = 9;
    private final int ROWS = 9;
    private final int BOMBS = 10; // количество бомб
    private final int IMAGE_SIZE = 50; // размер картинки

    public static void main(String[] args)
    {
        new JavaSweeper();
    }

    private JavaSweeper()
    {
        game = new Game(COLS, ROWS, BOMBS); // фасадный класс
        game.start();
        setImages();
        initPanel();
        initFrame();
    }

    private void initPanel()
    {
        panel = new JPanel() // создается анонимный класс
        {
            @Override
            protected void paintComponent(Graphics g)  // данная функция вызываетя при отрисовке формы
            {
                super.paintComponent(g);
                for (Coord coord : Ranges.getAllCoords())
                    g.drawImage((Image) game.getBox(coord).image, coord.x * IMAGE_SIZE, coord.y * IMAGE_SIZE, this);
            }
        };
        panel.setPreferredSize(new Dimension(
                Ranges.getSize().x * IMAGE_SIZE,
                Ranges.getSize().y * IMAGE_SIZE)); // устанавливаем размеры панели, Dimension из модуля Java.awt
        add(panel); // добавление панели, метод наследуется из класса JFrame
    }

    private void  initFrame()
    {
        setDefaultCloseOperation((WindowConstants.EXIT_ON_CLOSE)); // закрытие программы при нажатии на крестик
        setTitle("Java Sweeper"); // заголовок окна
        setLocationRelativeTo(null); // центрирование заголовка по центру
        setResizable(false); // форма не будет менять размер
        setVisible(true);
        setIconImage(getImage("icon"));
        pack();
    }

    private void setImages() // установка всех картинок
    {
        for (Box box : Box.values())
            box.image = getImage(box.name().toLowerCase());
    }

    private Image getImage (String name) // отображение картинки по названию
    {
        String filename = "img/" + name + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(filename)); // нужно указать в какой папке находятся ресурсы,
        // для этого щелкаем по папке Mark Directory As  Resources root
        return icon.getImage();

    }
}
