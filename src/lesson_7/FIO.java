package lesson_7;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FIO {
    public static void main(String[] args) {

        JPanel buttonPanel1 = new JPanel();
        JPanel textpanel = new JPanel();
        textpanel.setLayout(new BoxLayout(textpanel,BoxLayout.Y_AXIS));
        
        JFrameN mainWindow = new JFrameN("output Windows");
        JFrameN secondWindow = new JFrameN("imput Windows");
        secondWindow.setDefaultCloseOperation(2);


        JButton button1 = new JButton("открыть окно добавления инфы");
        JButton button2 = new JButton("добавить");


        JTextFieldN jtext1 = new JTextFieldN("введите ФАМИЛИЮ",35);
        JTextFieldN jtext2 = new JTextFieldN("введите ИМЯ",35);
        JTextFieldN jtext3 = new JTextFieldN("введите ОТЧЕСТВО",35);



        JPanel buttonpanel2 = new JPanel();
        buttonpanel2.add(button2);
        textpanel.add(jtext1);
        textpanel.add(jtext2);
        textpanel.add(jtext3);
        secondWindow.add(textpanel,BorderLayout.WEST);
        secondWindow.add(buttonpanel2,BorderLayout.SOUTH);

        JLabel FIOlable = new JLabel("ФАМИЛИЯ ИМЯ ОТЧЕСТВО");

        button1.addActionListener(e -> secondWindow.setVisible(true));

        buttonPanel1.add(button1);
        mainWindow.add(buttonPanel1,BorderLayout.SOUTH);
        mainWindow.add(FIOlable);
        mainWindow.setVisible(true);

        button2.addActionListener(e ->{
            FIOlable.setText(jtext1.getText()+" "+jtext2.getText()+" "+jtext3.getText());
            secondWindow.setVisible(false);
        });
    }
}
class JFrameN extends javax.swing.JFrame{
    public JFrameN(String name){
        setTitle(name);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(false);
        setLayout(new BorderLayout());
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        size.setSize(size.getWidth()/4,size.getHeight()/3);
        setSize(size);
    }
}
class JTextFieldN extends JTextField implements MouseListener{
    int count=0;
    @Override
    public void mouseClicked(MouseEvent e) {
        if(count==0){
        this.setText("");
        count++;}
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(count==0){
            this.setText("");
            count++;}
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public  JTextFieldN(String text, int cols){
        this.setText(text);
        this.setColumns(cols);
        this.addMouseListener(this);
        }
    }



