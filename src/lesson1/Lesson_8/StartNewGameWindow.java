package Lesson_8;

import javax.swing.*;
import java.awt.*;

class StartNewGameWindow extends JFrame {

    private final GameWindow gameWindow;
    private static final int WIN_HEIGHT = 230;
    private static final int WIN_WIDTH = 350;
    private static final int MIN_WIN_LEN = 3;
    private static final int MIN_FIELD_SIZE = 3;
    private static final int MAX_WIN_LEN = 10;
    private static final int MAX_FIELD_SIZE = 10;
    private static final String STR_WIN_LEN = "Winning Len: ";
    private static final String STR_FILED_SIZE = "Field Size: ";

    private JRadioButton jrbHumVsAi = new JRadioButton("Human vs Ai", true);
    private JRadioButton jrbHumVsHum = new JRadioButton("Human vs Human");
    private ButtonGroup gameMode = new ButtonGroup();

    private JSlider slFieldSize;
    private JSlider slWinLeght;


    StartNewGameWindow(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        setTitle("new game param!");
        setSize(WIN_WIDTH, WIN_HEIGHT);

        Rectangle gameWindowBounds = gameWindow.getBounds();
        int posX = (int) (gameWindowBounds.getCenterX() - WIN_WIDTH / 2);
        int posY = (int) (gameWindowBounds.getCenterY() - WIN_HEIGHT / 2);

        setLocation(posX, posY);
        setLayout(new GridLayout(10, 1));

        addGameControlsMode();
        addGameControlsFieldWinLen();

        JButton btnStartGame = new JButton("Start a game");
        btnStartGame.addActionListener(e -> btnStartGame());
        add(btnStartGame);

    }

    private void btnStartGame() {
        int gameMode;
        if (jrbHumVsAi.isSelected()) {
            gameMode = 0;
        } else {
            gameMode = 1;
        }

        int fieldSize = slFieldSize.getValue();
        int winLen = slWinLeght.getValue();
        gameWindow.startNewGame(gameMode, fieldSize, fieldSize, winLen);
        setVisible(false);
    }

    private void addGameControlsMode() {
        add(new JLabel("Choose gaming mode"));
        gameMode.add(jrbHumVsAi);
        gameMode.add(jrbHumVsHum);
        add(jrbHumVsAi);
        add(jrbHumVsHum);
    }

    private void addGameControlsFieldWinLen() {
        add(new JLabel("Choose field size"));
        final JLabel lblFieldSize = new JLabel(STR_FILED_SIZE + MIN_FIELD_SIZE);
        add(lblFieldSize);

        slFieldSize = new JSlider(MIN_FIELD_SIZE, MAX_FIELD_SIZE, MIN_WIN_LEN);
        slFieldSize.addChangeListener(e -> {
            int currentFieldSize = slFieldSize.getValue();
            lblFieldSize.setText(STR_FILED_SIZE + currentFieldSize);
            //    slWinLeght.setMaximum(currentFieldSize);
        });
        add(slFieldSize);

        add(new JLabel("Choose win len: "));
        final JLabel lblWinLen = new JLabel(STR_WIN_LEN + MIN_WIN_LEN);
        add(lblWinLen);

        slWinLeght = new JSlider(MIN_WIN_LEN, MAX_WIN_LEN, MIN_WIN_LEN);
        slWinLeght.addChangeListener(e -> {
            slWinLeght.setMaximum(slFieldSize.getValue());
            lblWinLen.setText(STR_WIN_LEN + slWinLeght.getValue());
        });
        add(slWinLeght);

    }


}
