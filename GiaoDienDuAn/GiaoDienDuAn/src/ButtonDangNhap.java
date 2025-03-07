///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//   import java.awt.Color;
//import java.awt.Cursor;
//import java.awt.Font;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.RenderingHints;
//import javax.swing.JButton;
///**
// *
// * @author ADMIN
// */
//public class ButtonDangNhap extends JButton{
//
//
//
//
//    private int cornerRadius = 20;
//
//    public ButtonDangNhap() {
//        setContentAreaFilled(false);
//        setCursor(new Cursor(Cursor.HAND_CURSOR));
//        setFont(getFont().deriveFont(Font.BOLD));
//    }
//
//    @Override
//    protected void paintComponent(Graphics g) {
//        Graphics2D g2 = (Graphics2D) g;
//        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        g2.setColor(getBackground());
//        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
//
//        super.paintComponent(g);
//    }
//} 
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;

/**
 * ButtonDangNhap class represents a custom JButton with rounded corners.
 * It uses Times New Roman font with a size of 13.
 * 
 * @author ADMIN
 */
public class ButtonDangNhap extends JButton {

    private int cornerRadius = 20;

    public ButtonDangNhap() {
        setContentAreaFilled(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Set Times New Roman font with size 13
        setFont(new Font("Times New Roman", Font.BOLD, 14));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        super.paintComponent(g);
    }
}

