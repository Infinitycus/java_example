package by.infinity.examples.jtable.bean;

import javax.swing.*;

/**
 * @author Eugene Myslovets
 */
public class Main {

    private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";

    public static void main(String[] args) {
        installLnF();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    BookFrame bookFrame = new BookFrame();
                    bookFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    bookFrame.setLocationRelativeTo(null);
                    bookFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static void installLnF() {
        try {
            UIManager.setLookAndFeel(PREFERRED_LOOK_AND_FEEL);
        } catch (Exception e) {
            System.out.println("Cannot install " + PREFERRED_LOOK_AND_FEEL + " on this platform:" + e.getMessage());
        }
    }
}
