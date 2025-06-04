package UserInterfaces;

import UserInterfaces.GameInterface;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardAdapter implements KeyListener {

    private volatile InputInfo info;

    public KeyboardAdapter() {
        JTextField textField = new JTextField(1);
        textField.addKeyListener(this);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(textField, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);

        this.info = new InputInfo();
    }

    public InputInfo getInfo() {
        InputInfo ans = this.info;
        this.info = new InputInfo();
        return ans;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        char key = e.getKeyChar();
        //System.out.println("Отпущена клавиша - " + key);
        switch (key) {
            case 'q':
                this.info.setQ(true);
                break;
            case 'w':
                this.info.setW(true);
                break;
            case 'e':
                this.info.setE(true);
                break;
            case 'r':
                this.info.setR(true);
                break;
            case 'a':
                this.info.setA(true);
                break;
            case 's':
                this.info.setS(true);
                break;
            case 'd':
                this.info.setD(true);
                break;
            case 'f':
                this.info.setF(true);
                break;
            case 'z':
                this.info.setZ(true);
                break;
            case 'x':
                this.info.setX(true);
                break;
            case 'c':
                this.info.setC(true);
                break;
            case 'v':
                this.info.setV(true);
                break;
            case '1':
                this.info.setK1(true);
                break;
            case '2':
                this.info.setK2(true);
                break;
            case '3':
                this.info.setK3(true);
                break;
            case '4':
                this.info.setK4(true);
                break;
            case '5':
                this.info.setK5(true);
                break;
            case 'E':
                this.info.setShiftE(true);
                break;
            default:
                break;
        }
    }
}
