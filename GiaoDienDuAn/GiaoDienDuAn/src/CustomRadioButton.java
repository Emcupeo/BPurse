import java.awt.Font;
import javax.swing.JRadioButton;

/**
 * CustomRadioButton class represents a custom JRadioButton with Times New Roman font.
 * It uses anti-aliased rendering for smoother edges.
 * 
 * @author ADMIN
 */
public class CustomRadioButton extends JRadioButton {

    public CustomRadioButton(String text) {
        super(text);
        
        // Set Times New Roman font with size 10
        setFont(new Font("Times New Roman", Font.PLAIN, 10));
    }
}
