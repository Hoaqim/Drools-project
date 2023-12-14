package com.sample;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
				int n = JOptionPane.showOptionDialog(
						this, question, "Riddle me this, batman!", 
						JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, 
						null, options, options[0]);
				
				if (n == -1) return;
				input = options[n];
			} else {
				JOptionPane.showMessageDialog(this, options);	
			}
		}
	}
	
}