package ProgramFilesPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ManagementView extends JFrame {
    public ManagementView(String title) {
        super(title);
        //set up frame
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = new Dimension();
        int width = screenSize.width;
        int height = screenSize.height;

        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(new ManagePanel());
    }

    private class ManagePanel extends JPanel {
        public ManagePanel() {
            JLabel title = new JLabel("Patient Management System");
            Font headingFont = new Font("Times New Roman", Font.BOLD, 28);
            title.setFont(headingFont);

            JButton startAppointment = new JButton("Start Appointment");
            startAppointment.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                }
            });


            //add to panel
            this.add(title);
            this.add(startAppointment);
        }
    }

    public static void main(String[] args) {
        ManagementView frame = new ManagementView("Patient Management System");
        frame.setVisible(true);
    }
}
