//import java.awt.Color;
//import java.awt.Cursor;
//import java.awt.Font;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.RenderingHints;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import javax.swing.JButton;
//
///**
// * Custom button class with rounded rectangle and color change on hover.
// */
//public class RoundedButton extends JButton {
//
//    private Color defaultColor = new Color(240, 240, 240);
//    private Color hoverColor = new Color(255, 0, 0);
//    private int cornerRadius = 30;
//
//    public RoundedButton() {
//        setContentAreaFilled(false);
//        setCursor(new Cursor(Cursor.HAND_CURSOR));
//        setFont(getFont().deriveFont(Font.BOLD));
//
//        // Add a mouse listener to handle color change on hover
//        addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseEntered(MouseEvent e) {
//                setOpaque(true);
//                //setBackground(hoverColor);
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//                setOpaque(false);
//               // setBackground(defaultColor);
//            }
//        });
//    }
//
//    @Override
//    protected void paintComponent(Graphics g) {
//        Graphics2D g2 = (Graphics2D) g;
//        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        g2.setColor(getBackground());
//        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
////        if (getModel().isRollover()) {
////            g2.setColor(defaultColor);
////        } else {
////            g2.setColor(hoverColor);
////        }
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
 * Custom button class with rounded rectangle.
 */
public class RoundedButton extends JButton {

    private int cornerRadius = 30;

    public RoundedButton() {
        setContentAreaFilled(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setFont(getFont().deriveFont(Font.BOLD));
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
