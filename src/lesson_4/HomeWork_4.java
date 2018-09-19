package lesson_4;

import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.stream.Collectors;

public class HomeWork_4 {

    int size;
    button[][] map;
    GridBagConstraints c;
    JPanel center;
    JFrame frame;
    JLabel messageField;
    JTextField sizeField;
    JButton jb1;
    ArrayList<button> buttonX = new ArrayList<>();
    ArrayList<button> buttonO = new ArrayList<>();
    public static void main(String[] args) {
        new HomeWork_4().start();
    }
    void start(){
        generateField();
    }


    void generateField(){
        frame = new JFrame();
        center = new JPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridBagLayout gbl = new GridBagLayout();
        center.setLayout(gbl);
        frame.add(center);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        messageField = new JLabel("введите размер поля");
        sizeField = new JTextField(10);
        jb1 = new JButton("OK");
        center.add(messageField);
        center.add(sizeField);
        center.add(jb1);
        frame.setVisible(true);
        frame.pack();

        jb1.addActionListener( (e) -> createButtonsMap());
        sizeField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==10) {
                    try {
                        createButtonsMap();
                    }catch(Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
        });
    }
    private void createButtonsMap() {
        map = new button[Integer.parseInt(sizeField.getText())][Integer.parseInt(sizeField.getText())];
        drawButton(Integer.parseInt(sizeField.getText()),"---");
        size = Integer.parseInt(sizeField.getText());
        center.remove(messageField);
        center.remove(sizeField);
        center.remove(jb1);

    }
    void drawButton(int size, String text){
        int var = 0;
        for (int g=0; g<size;g++){
            for (int g1=0;g1<size;g1++) {
                map[g][g1]=new button(String.valueOf(var),g1,g);
                var++;
            }
        }
        frame.pack();
    }


    void AIturn(){
        int r[] =aiClever();
        while (!hod(map[r[0]][r[1]],"O"))
        {
        hod(map[r[0]][r[1]],"O");
            r =aiClever();
        }
        if (checkWin(buttonO).getKey()){
            for (button var:checkWin(buttonO).getValue()){
                setWinColor(var, Color.BLUE);
            }
            System.out.println("компьютер выйграл");
        }
    }
    int[] aiClever(){
        for (int i=0;i<size;i++){
            for (int i1=0;i1<size;i1++){
                ArrayList<button> var77 = new ArrayList<>(buttonX);
                if (!buttonX.contains(map[i][i1])&&!buttonO.contains(map[i][i1])) {
                    var77.add(map[i][i1]);
                    if (checkWin(var77).getKey()) {
                        int r[] = {i, i1};
                        System.out.println(Arrays.toString(r));
                        return r;
                    }
                }
            }
        }
        return aiRandom();
    }
    int[] aiRandom(){
        int[]i=new int[2];

        i[0] = (int)(Math.random()*size);
        i[1] = (int)(Math.random()*size);
        return i;
    }


    boolean hod(button varbt, String txt) {
        if (!varbt.getText().equals("X") && !varbt.getText().equals("O")) {
            varbt.setText(txt);
        //    System.out.println(txt);
            c.gridx = varbt.getposx();
            c.gridy = varbt.getposy();
            repaintButton(varbt);
            switch (txt) {
                case "X":
                    buttonX.add(map[varbt.getposy()][varbt.getposx()]);
                    break;
                case "O":
                    buttonO.add(map[varbt.getposy()][varbt.getposx()]);
                    break;
            }
            if (checkWin(buttonX).getKey()){
                for (button var:checkWin(buttonX).getValue()){
                    setWinColor(var, Color.RED);
                }
                System.out.println("Ты выйграл");
            }
            return true;
        }
        else {
            return false;
        }
    }

    Pair<Boolean,ArrayList<button>> checkWin(ArrayList<button> buttonXO) {
        int needToWin = 3;
        Boolean win = false;
        ArrayList<button> var2=null;
        Pair<Boolean,ArrayList<button>> pairWin = new Pair<>(win,var2);
        if (buttonXO.size()<needToWin){
            return pairWin;
        }

        for (button var3 : buttonXO) {
            var2 = buttonXO.stream().filter(o -> o.getposx() == var3.getposx()
                    && o.getposy() >= var3.getposy()
                    && o.getposy() < var3.getposy() + needToWin)
                    .collect(Collectors.toCollection(ArrayList::new));
            if (var2.size() == needToWin) {
                System.out.println("победа1");
                win = true;
                pairWin = new Pair<>(win,var2);
                return pairWin;
            }
        }

        for (button var3 : buttonXO) {
            var2 = buttonXO.stream().filter(o -> o.getposy() == var3.getposy()
                    && o.getposx() >= var3.getposx()
                    && o.getposx() < var3.getposx() + needToWin)
                    .collect(Collectors.toCollection(ArrayList::new));
            if (var2.size() == needToWin) {
                System.out.println("победа2");
                win = true;
                pairWin = new Pair<>(win,var2);
                return pairWin;
            }
        }
        for (button var3 : buttonXO) {
            var2 = buttonXO.stream().filter(o -> o.getposy()-o.getposx() == var3.getposy()-var3.getposx()
                    && o.getposy() >= var3.getposy()
                    && o.getposy() < var3.getposy() + needToWin)
                    .collect(Collectors.toCollection(ArrayList::new));
            if (var2.size() == needToWin) {
                System.out.println("победа3");
                win = true;
                pairWin = new Pair<>(win,var2);
                return pairWin;
            }
        }

        for (button var3 : buttonXO) {
            var2 = buttonXO.stream().filter(o -> o.getposy()+o.getposx() == var3.getposy()+var3.getposx()
                    && o.getposx() >= var3.getposx()
                    && o.getposx() < var3.getposx() + needToWin)
                    .collect(Collectors.toCollection(ArrayList::new));
            if (var2.size()==needToWin) {
                System.out.println("победа4");
                win = true;
                pairWin = new Pair<>(win,var2);
                return pairWin;

            }
            }
        win = true;
        pairWin = new Pair<>(win,var2);
        return pairWin;
        }


    class button {
        JButton bt = new JButton();
        button(String txt, int g1, int g) {
          //     bt.setText(String.valueOf(txt));
           bt.setText(g1+" "+g);
            posx = g1;
            posy = g;
            c.gridx = posx;
            c.gridy = posy;
            bt.setPreferredSize(new Dimension(60, 50));
            center.add(bt, c);
            text = txt;
            bt.setEnabled(true);
            bt.addActionListener(new turn());
        }
        String text;
        int posx;
        int posy;
        int getposx() {
            return posx;
        }
        int getposy() {
            return posy;
        }
        String getText() {
            return text;
        }
        void setText(String vartext) {
            bt.setText(vartext);
            text = vartext;
        }

        class turn implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                hod(button.this, "X");
                AIturn();
            }
        }
    }
    void repaintButton(button varbt){
        center.remove(varbt.bt);
        center.updateUI();
        frame.repaint();
        c.gridy = varbt.getposy();
        c.gridx = varbt.getposx();
        center.add(varbt.bt, c);
        varbt.bt.setEnabled(false);
        frame.pack();
    }
    void setWinColor(button varbt, Color color){
        varbt.bt.setBackground(color);
        varbt.bt.setBorder(null);
        repaintButton(varbt);

    }
}

