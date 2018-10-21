package lesson1.Lesson_8;

import java.util.ArrayList;
import java.util.Random;


class theGame {
    //1. параметры игрового поля
    private int SIZE_Y; //размер поля по вертикале
    private int SIZE_X; //расчет поля по горизонтале
    private int SIZE_WIN; //кол-во заполненных подряж полей для победы

    String[][] getFieldg() {
        return fieldg;
    }

    private String[][] fieldg;
    // игровые элемент
    final String player_DOT;
    final String Ai_DOT;
    private final String EMPTY_DOT;
    private Random rnd;

    theGame(int y, int x, int win) {
        SIZE_Y = y;
        SIZE_X = x;
        SIZE_WIN = win;
        fieldg = new String[SIZE_Y][SIZE_X];


        EMPTY_DOT = "~";
        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < SIZE_X; j++) {
                fieldg[i][j] = EMPTY_DOT;
            }
        }
        player_DOT = "X";
        Ai_DOT = "O";
        rnd = new Random();
    }

    // обявляется классов ввода и случайного числа для игры


    String getEMPTY_DOT() {
        return EMPTY_DOT;
    }

    //запись хода игрока на поле
    private void dotField(int y, int x, String dot) {
        fieldg[y][x] = dot;
    }

    //Ход человева
    void playerMove(int gy, int gx, String dot) {
        int x, y;
        do {
            y = gy;
            x = gx;
        } while (!checkMove(y, x));
        dotField(y, x, dot);
    }

    //Ход компьютера
    void AiMove() {
        int x, y;
        //блокировка ходов человека
        for (int v = 0; v < SIZE_Y; v++) {
            for (int h = 0; h < SIZE_X; h++) {
                //анализ наличие поля для проверки
                if (h + SIZE_WIN <= SIZE_X) {                           //по горизонтале
                    if (checkLineHorisont(v, h, player_DOT).size() == SIZE_WIN - 1) {
                        if (MoveAiLineHorisont(v, h, Ai_DOT)) return;
                    }

                    if (v - SIZE_WIN > -2) {                            //вверх по диагонале
                        if (checkDiaUp(v, h, player_DOT).size() == SIZE_WIN - 1) {
                            if (MoveAiDiaUp(v, h, Ai_DOT)) return;
                        }
                    }
                    if (v + SIZE_WIN <= SIZE_Y) {                       //вниз по диагонале
                        if (checkDiaDown(v, h, player_DOT).size() == SIZE_WIN - 1) {
                            if (MoveAiDiaDown(v, h, Ai_DOT)) return;
                        }
                    }
                }
                if (v + SIZE_WIN <= SIZE_Y) {                       //по вертикале
                    if (checkLineVertical(v, h, player_DOT).size() == SIZE_WIN - 1) {
                        if (MoveAiLineVertical(v, h, Ai_DOT)) return;
                    }
                }
            }
        }
        //игра на победу
        for (int v = 0; v < SIZE_Y; v++) {
            for (int h = 0; h < SIZE_X; h++) {
                //анализ наличие поля для проверки
                if (h + SIZE_WIN <= SIZE_X) {                           //по горизонтале
                    if (checkLineHorisont(v, h, Ai_DOT).size() == SIZE_WIN - 1) {
                        if (MoveAiLineHorisont(v, h, Ai_DOT)) return;
                    }

                    if (v - SIZE_WIN > -2) {                            //вверх по диагонале
                        if (checkDiaUp(v, h, Ai_DOT).size() == SIZE_WIN - 1) {
                            if (MoveAiDiaUp(v, h, Ai_DOT)) return;
                        }
                    }
                    if (v + SIZE_WIN <= SIZE_Y) {                       //вниз по диагонале
                        if (checkDiaDown(v, h, Ai_DOT).size() == SIZE_WIN - 1) {
                            if (MoveAiDiaDown(v, h, Ai_DOT)) return;
                        }
                    }

                }
                if (v + SIZE_WIN <= SIZE_Y) {                       //по вертикале
                    if (checkLineVertical(v, h, Ai_DOT).size() == SIZE_WIN - 1) {
                        if (MoveAiLineVertical(v, h, Ai_DOT)) return;
                    }
                }
            }
        }

        //случайный ход
        do {
            y = rnd.nextInt(SIZE_Y);
            x = rnd.nextInt(SIZE_X);
        } while (!checkMove(y, x));
        dotField(y, x, Ai_DOT);
    }

    private boolean MoveAiLineHorisont(int v, int h, String dot) {
        for (int j = h; j < SIZE_WIN; j++)
            if ((fieldg[v][j].equals(EMPTY_DOT))) {
                fieldg[v][j] = dot;
                return true;
            }
        return false;
    }

    //ход компьютера по вертикале
    private boolean MoveAiLineVertical(int v, int h, String dot) {
        for (int i = v; i < SIZE_WIN; i++)
            if ((fieldg[i][h].equals(EMPTY_DOT))) {
                fieldg[i][h] = dot;
                return true;
            }
        return false;
    }
    //проверка заполнения всей линии по диагонале вверх

    private boolean MoveAiDiaUp(int v, int h, String dot) {
        for (int i = 0, j = 0; j < SIZE_WIN; i--, j++) {
            if ((fieldg[v + i][h + j].equals(EMPTY_DOT))) {
                fieldg[v + i][h + j] = dot;
                return true;
            }
        }
        return false;
    }
    //проверка заполнения всей линии по диагонале вниз

    private boolean MoveAiDiaDown(int v, int h, String dot) {

        for (int i = 0; i < SIZE_WIN; i++) {
            if ((fieldg[i + v][i + h].equals(EMPTY_DOT))) {
                fieldg[i + v][i + h] = dot;
                return true;
            }
        }
        return false;
    }

    //проверка заполнения выбранного для хода игроком
    private boolean checkMove(int y, int x) {
        if (x < 0 || x >= SIZE_X || y < 0 || y >= SIZE_Y) return false;
        else return fieldg[y][x].equals(EMPTY_DOT);
    }


    //проверка на ничью (все  ячейки поля заполнены ходами)
    boolean fullField() {
        for (int i = 0; i < SIZE_Y; i++)
            for (int j = 0; j < SIZE_X; j++) {
                if (fieldg[i][j].equals(EMPTY_DOT)) return false;
            }
        System.out.println("Игра закончилась в ничью");
        return true;
    }


    //проверка победы
    ArrayList<coords> checkWin(String dot) {
        ArrayList<coords> winCoords ;
        for (int v = 0; v < SIZE_Y; v++) {
            for (int h = 0; h < SIZE_X; h++) {
                //анализ наличие поля для проверки
                if (h + SIZE_WIN <= SIZE_X) {                           //по горизонтале
                    if (checkLineHorisont(v, h, dot).size() >= SIZE_WIN) {
                        winCoords = checkLineHorisont(v, h, dot);
                        return winCoords;
                    }
                    if (v - SIZE_WIN > -2) {                            //вверх по диагонале
                        if (checkDiaUp(v, h, dot).size() >= SIZE_WIN) {
                            winCoords = checkDiaUp(v, h, dot);
                            return winCoords;
                        }
                    }
                    if (v + SIZE_WIN <= SIZE_Y) {                       //вниз по диагонале
                        if (checkDiaDown(v, h, dot).size() >= SIZE_WIN) {
                            winCoords = checkDiaDown(v, h, dot);
                            return winCoords;
                        }
                    }
                }
                if (v + SIZE_WIN <= SIZE_Y) {                       //по вертикале
                    if (checkLineVertical(v, h, dot).size() >= SIZE_WIN) {
                        winCoords = checkLineVertical(v, h, dot);
                        return winCoords;
                    }
                }
            }
        }
        return null;
    }

    //проверка заполнения всей линии по диагонале вверх
    class coords {
        coords(int y, int x) {
            this.y = y;
            this.x = x;
        }

        int getY() {
            return y;
        }

        int getX() {
            return x;
        }

        int y;
        int x;
    }

    private ArrayList<coords> checkDiaUp(int v, int h, String dot) {
        ArrayList<coords> coord = new ArrayList<>();
        for (int i = 0, j = 0; j < SIZE_WIN; i--, j++) {
            if ((fieldg[v + i][h + j].equals(dot) )) coord.add(new coords(v + i, h + j));
        }
        return coord;
    }
    //проверка заполнения всей линии по диагонале вниз

    private ArrayList<coords> checkDiaDown(int v, int h, String dot) {
        ArrayList<coords> coord = new ArrayList<>();
        for (int i = 0; i < SIZE_WIN; i++) {
            if ((fieldg[i + v][i + h].equals(dot) ))
                coord.add(new coords(i + v, i + h));
        }
        return coord;
    }

    private ArrayList<coords> checkLineHorisont(int v, int h, String dot) {

        ArrayList<coords> coord = new ArrayList<>();
        for (int j = h; j < SIZE_WIN + h; j++) {
            if ((fieldg[v][j].equals(dot) )) coord.add(new coords(v, j));
        }
        return coord;
    }

    //проверка заполнения всей линии по вертикале
    private ArrayList<coords> checkLineVertical(int v, int h, String dot) {
        ArrayList<coords> coord = new ArrayList<>();

        for (int i = v; i < SIZE_WIN + v; i++) {
            if ((fieldg[i][h].equals(dot) )) coord.add(new coords(i, h));
        }
        return coord;
    }
}












