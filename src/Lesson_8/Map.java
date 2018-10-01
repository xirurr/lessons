package Lesson_8;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

class Map extends JPanel {

    private int MODE_H_V_A;
    private int MODE_H_V_H;
    private theGame newGame;
    private oneObject[][] field;
    private int fieldSizeX;
    private int fieldSizeY;
    private int winLenght;
    private GridLayout gridLayout;
    private boolean isInitialized = false;
    private boolean isSmbdyWin = false;

    void startNewGame(int mode, int filedSizeX, int filedSizeY, int winLen) {
        isSmbdyWin = false;
        if (mode == 0) {
            MODE_H_V_A = 1;
        } else {
            MODE_H_V_H = 1;
        }
        this.fieldSizeX = filedSizeX;
        this.fieldSizeY = filedSizeY;
        gridLayout = new GridLayout(fieldSizeY, fieldSizeX);
        setLayout(gridLayout);
        this.winLenght = winLen;
        field = new oneObject[filedSizeY][filedSizeX];
        isInitialized = true;
        newGame = new theGame(fieldSizeY, fieldSizeX, winLenght);

        renderAll();
        reTakeStatus();
        revalidate();
        repaint();
    }

    private void reTakeStatus() {
        String[][] var;
        var = newGame.getFieldg();
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                field[i][j].setState(var[i][j]);
            }
        }
    }

    private void renderAll() {
        if (!isInitialized) {
        } else {
            this.removeAll();
            for (int i = 0; i < fieldSizeY; i++) {
                for (int j = 0; j < fieldSizeX; j++) {
                    oneObject point = new oneObject(i, j);
                    field[i][j] = point;
                    this.add(point);
                }
            }
        }
    }

    private boolean firstplayer = true;

    public class oneObject extends JPanel {
        int posy;
        int posx;
        String state;

        @Override
        public void paintComponent(Graphics gg) {
            super.paintComponent(gg);
            Graphics2D g = (Graphics2D) gg;
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            drawGrid(g);
        }

        private void drawGrid(Graphics2D g) {
            g.setColor(getForeground());
            g.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 25, 25);
            g.setColor(Color.blue.darker());
            g.drawRoundRect(0, 0, this.getWidth(), this.getHeight(), 25, 25);
            g.setColor(getBackground());
            String var = this.getState();
            drawCenteredString(g, var, 0, 0);

        }

        private void drawCenteredString(Graphics2D g, String s, int x, int y) {
            FontMetrics fm = g.getFontMetrics();
            int asc = fm.getAscent();
            int des = fm.getDescent();
            setFont(new Font("SansSerif", Font.BOLD, getSize().height));
            x = x + (this.getHeight() - fm.stringWidth(s)) / 2;
            y = y + (asc + (this.getHeight() - (asc + des)) / 2);
            g.drawString(s, x, y);
        }


        oneObject(int posy, int posx) {
            //   setBorder(BorderFactory.createLineBorder(Color.BLUE));
            setBackground(Color.WHITE);
            setForeground(Color.ORANGE);

            this.posy = posy;
            this.posx = posx;
            this.state = "";

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    if (!isSmbdyWin) {
                        if (MODE_H_V_A == 1) {
                            gameVsAI();
                        } else
                            gamePvP();
                    }
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    setBackground(Color.WHITE.darker());
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    setBackground(Color.WHITE);
                }
            });
        }

        private void gameVsAI() {
            if (state.equals(newGame.getEMPTY_DOT())) {
                newGame.playerMove(getPosy(), getPosx(), newGame.player_DOT);
            }
            reTakeStatus();
            repaint();

            if (!(newGame.checkWin(field[getPosy()][getPosx()].getState()) == null)) {
                drawWin(newGame.checkWin(field[getPosy()][getPosx()].getState()));

                reTakeStatus();
                repaint();
                isSmbdyWin = true;
                System.out.println("Вы выиграли");
            } else if (newGame.fullField()) {
                reTakeStatus();
                repaint();
                System.out.println("поле заполнено");
            } else {
                newGame.AiMove();
                reTakeStatus();
                repaint();
                if (!(newGame.checkWin(newGame.Ai_DOT) == null)) {
                    drawWin(newGame.checkWin(newGame.Ai_DOT));
                    System.out.println("КОМП ВЫЙГРАЛ");
                    reTakeStatus();
                    repaint();
                    isSmbdyWin = true;
                }
            }
        }

        private void gamePvP() {
            if (firstplayer) {
                if (state.equals(newGame.getEMPTY_DOT())) {
                    newGame.playerMove(getPosy(), getPosx(), newGame.player_DOT);
                }
                reTakeStatus();
                repaint();
                firstplayer = false;
                if (!(newGame.checkWin(field[getPosy()][getPosx()].getState()) == null)) {
                    drawWin(newGame.checkWin(field[getPosy()][getPosx()].getState()));
                    reTakeStatus();
                    repaint();
                    System.out.println("Игрок 1 выйграл");
                    isSmbdyWin = true;
                } else if (newGame.fullField()) {
                    reTakeStatus();
                    repaint();
                    System.out.println("поле заполнено");
                    isSmbdyWin = true;
                }
            } else {
                if (state.equals(newGame.getEMPTY_DOT())) {
                    newGame.playerMove(getPosy(), getPosx(), newGame.Ai_DOT);
                }
                reTakeStatus();
                repaint();
                firstplayer = true;
                if (!(newGame.checkWin(field[getPosy()][getPosx()].getState()) == null)) {
                    drawWin(newGame.checkWin(field[getPosy()][getPosx()].getState()));
                    reTakeStatus();
                    repaint();
                    System.out.println("Игрок 2 выйграл");
                    isSmbdyWin = true;
                } else if (newGame.fullField()) {
                    reTakeStatus();
                    repaint();
                    System.out.println("поле заполнено");
                    isSmbdyWin = true;
                }
            }
        }

        int getPosy() {
            return posy;
        }

        int getPosx() {
            return posx;
        }

        String getState() {
            return state;
        }

        void setState(String state) {
            this.state = state;
            repaint();
        }

        void drawWin(ArrayList<theGame.coords> WinCoord) {
            for (theGame.coords aWinCoord : WinCoord)
                field[aWinCoord.getY()][aWinCoord.getX()].setForeground(Color.BLACK);
        }


    }


}
