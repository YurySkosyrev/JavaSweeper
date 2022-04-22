import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import sweeper.Box;
import sweeper.Coord;
import sweeper.Game;
import sweeper.Ranges;

public class JavaSweeper extends JFrame {
    private Game game;

    private JPanel panel;
    private JLabel label;
    private final int COLS = 9;
    private final int ROWS = 9;
    private final int BOMBS = 10;
    private final int IMAGE_SIZE = 50;

    public static void main(String[] args) {
        new JavaSweeper();
    }

    private JavaSweeper() {
        game = new Game(COLS, ROWS, BOMBS); // фасадный класс
        game.start();
        setImages();
        initLabel();
        initPanel();
        initFrame();
    }

    private void initLabel() {
        label = new JLabel("Welcome!");
        add(label, BorderLayout.SOUTH);
    }

    private void initPanel() {
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

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / IMAGE_SIZE; // определяем в какой клетке произошло нажатие клавиши мыши
                int y = e.getY() / IMAGE_SIZE;
                Coord coord = new Coord(x, y);
                if (e.getButton() == MouseEvent.BUTTON1)
                    game.pressLeftButton(coord);
                if (e.getButton() == MouseEvent.BUTTON2)
                    game.start();
                if (e.getButton() == MouseEvent.BUTTON3)
                    game.pressRightButton(coord);
                label.setText(getMasasge());
                panel.repaint();
            }
        });
        panel.setPreferredSize(new Dimension(
                Ranges.getSize().x * IMAGE_SIZE,
                Ranges.getSize().y * IMAGE_SIZE));
        add(panel);
    }

    private String getMasasge() {
        switch (game.getState()) {
            case PLAYED:
                return "Think twice!";
            case BOMBED:
                return "You lose";
            case WINNER:
                return "You win";
            default:
                return "";
        }
    }

    private void initFrame() {
        setDefaultCloseOperation((WindowConstants.EXIT_ON_CLOSE)); // закрытие программы при нажатии на крестик
        setTitle("Java Sweeper"); // заголовок окна
        setResizable(false); // форма не будет менять размер
        setVisible(true);
        setIconImage(getImage("icon"));
        pack();
        setLocationRelativeTo(null); // центрирование заголовка по центру
    }

    private void setImages() // установка всех картинок
    {
        for (Box box : Box.values())
            box.image = getImage(box.name().toLowerCase());
    }

    private Image getImage(String name) // отображение картинки по названию
    {
        String filename = "img/" + name + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(filename)); // нужно указать в какой папке находятся ресурсы,
        // для этого щелкаем по папке Mark Directory As  Resources root
        return icon.getImage();

    }
}
