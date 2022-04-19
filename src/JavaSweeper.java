import javax.swing.*;
import java.awt.*;
import sweeper.Box;
import sweeper.Coord;
import sweeper.Ranges;

public class JavaSweeper extends JFrame {

    private JPanel panel;
    private final int COLS = 15;
    private final int ROWS = 1;
    private final int IMAGE_SIZE = 50; // размер картинки

    public static void main(String[] args)
    {
        new JavaSweeper();
    }

    private JavaSweeper()
    {
        Ranges.setSize(new Coord(COLS, ROWS));
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
                for (Box box : Box.values())
                {
                    Coord coord = new Coord(box.ordinal() * IMAGE_SIZE, 0);
                    g.drawImage((Image) box.image, coord.x, coord.y, this);
                }
            }
        };
        panel.setPreferredSize(new Dimension(
                Ranges.getSize().x * IMAGE_SIZE,
                Ranges.getSize().y * IMAGE_SIZE)); // устанавливаем размеры панели, Dimension из модуля Java.awt
        add(panel); // добавление панели, метод наследуется из класса JFrame
    }

    private void  initFrame()
    {
        pack();
        setDefaultCloseOperation((WindowConstants.EXIT_ON_CLOSE)); // закрытие программы при нажатии на крестик
        setTitle("Java Sweeper"); // заголовок окна
        setLocationRelativeTo(null); // центрирование заголовка по центру
        setResizable(false); // форма не будет менять размер
        setVisible(true);
        setIconImage(getImage("icon"));
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
