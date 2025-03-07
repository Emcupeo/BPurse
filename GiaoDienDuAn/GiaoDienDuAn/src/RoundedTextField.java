import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * RoundedTextField class represents a custom rounded JTextField.
 * It uses anti-aliased rendering for smoother edges.
 * 
 * @author ADMIN
 */
public class RoundedTextField extends JTextField {

    private int cornerRadius = 20;

    public RoundedTextField() {
        setOpaque(false);
        setBorder(new EmptyBorder(0, cornerRadius, 0, cornerRadius));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);

        super.paintComponent(g);
    }
}
