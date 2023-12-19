package com.sample;

import javax.swing.*;
import java.awt.*;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class BuyingClassicCar {
	
	public static final void main(String[] args) {
		
        try {
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	KieSession kSession = kContainer.newKieSession("ksession-rules");
        	
        	UI gui = new UI();
        	kSession.setGlobal("ui", gui);
        	
            kSession.fireAllRules();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
	
	public static class UI extends JFrame {
		private static final long serialVersionUID = 1L;
		private String input;
		
		public String getUserAnswer() { return this.input; }
		
		public void update(String question, String[] options, boolean isLastQuestion) {
			if (!isLastQuestion) {
				CustomDialog dialog = new CustomDialog(this, "Riddle me this, batman!", question, options);
				dialog.setVisible(true);
				input = dialog.getSelectedOption();
			} else {
				JOptionPane.showMessageDialog(this, options);	
			}
		}
	}
	
	public static class CustomDialog extends JDialog {
	    private JList<String> list;

	    public CustomDialog(JFrame parent, String title, String message, String[] options) {
	        super(parent, title, true);

	        JPanel panel = new JPanel(new BorderLayout());
	        JLabel lbl = new JLabel(message);
	        panel.add(lbl, BorderLayout.NORTH);

	        list = new JList<>(options);
	        panel.add(list, BorderLayout.CENTER);

	        JButton btn = new JButton("OK");
	        btn.addActionListener(e -> setVisible(false));
	        panel.add(btn, BorderLayout.SOUTH);

	        getContentPane().add(panel);
	        pack();
	        setLocationRelativeTo(parent);
	    }

	    public String getSelectedOption() {
	        return list.getSelectedValue();
	    }
	}
}
