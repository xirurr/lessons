package Lesson_8;

import javax.swing.*;
import java.awt.*;
class GameWindow extends JFrame {

    // 1 данные основного окна
    private static final int WIN_HEIGHT = 555;
    private static final int WIN_WIDTH = 507;
    private static final int WIN_POS_X = 800;
    private static final int WIN_POS_Y = 300;

    private static Map field;
    private static StartNewGameWindow startNewGameWindow;

    GameWindow() {
        setTitle("TicTacToe");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(WIN_POS_X, WIN_POS_Y, WIN_WIDTH, WIN_HEIGHT);
        setResizable(false);

        JPanel bottomPanel = new JPanel(new GridLayout(1, 2));

        JButton btnNewGame = new JButton("Start new game");
        JButton btnExit = new JButton("Exit");

        startNewGameWindow = new StartNewGameWindow(this);

        bottomPanel.add(btnNewGame);
        bottomPanel.add(btnExit);

        btnNewGame.addActionListener(e -> {
            //System.out.println("Start new game");
            startNewGameWindow.setVisible(true);
        });

        btnExit.addActionListener(e -> System.exit(0));

        field = new Map();
        add(field, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        setVisible(true);
    }


    void startNewGame(int mode, int filedSizeX, int filedSizeY, int winLen) {
        field.startNewGame(mode, filedSizeX, filedSizeY, winLen);
    }

}
