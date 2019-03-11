package dataObjects;

import java.awt.Button;
import java.awt.Color;
import java.awt.Event;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class Server extends Frame implements ActionListener{
	 /**
	 * 
	 */
	private Account acc;
	private clientThread Cl1;
	private clientThread Cl2;
	Button bex = new Button("Open");
	Button stp = new Button("Stop");
	Label lbl = new Label("Client 1 balance");
	Label lb2 = new Label("Client 2 balance");
	TextArea tv1 = new TextArea();
	TextArea tv2 = new TextArea();
	private static final long serialVersionUID = 9039219725960248682L;
	public static void main(String args[])
	   {
			new Server();
	   }
	public Server() {
		super("my window");
		setLayout(null);
		add(bex);
		add(stp);
		add(tv1);
		add(tv2);
		add(lbl);
		add(lb2);
		setBackground(new Color(255, 255, 255));
		setSize(450, 250);
		this.setVisible(true);
		bex.setBounds(170, 40, 100, 20);
		stp.setBounds(170, 70, 100, 20);
		tv1.setBounds(130, 100, 200, 20);
		tv2.setBounds(130, 140, 200, 20);
		lbl.setBounds(20,100,120,20);
		lb2.setBounds(20,140,120,20);
		this.setLocationRelativeTo(null);
		bex.addActionListener(this);
		stp.addActionListener(this);
	}
	public boolean handleEvent(Event evt)
    {
	       if (evt.id==Event.WINDOW_DESTROY)
	         {
	    	   System.exit(0);
	    	 }
	       
		return false;
     }
		@Override
		public void actionPerformed(ActionEvent evt) {
	    	if(evt.getSource()==bex) {
		    	   acc = new Account();
		    	   acc.start();
		    	   Cl1 = new clientThread();
		    	   Cl2 = new clientThread();
		    	   Cl1.start();
		    	   try {
					Cl1.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	   Cl2.start();
		       }
	    	if(evt.getSource()==stp) {
	    		try {
					acc.stopServer();
					Cl1.interrupt();
					Cl2.interrupt();
					tv1.setText(Cl1.getActualBalance());
					tv2.setText(Cl2.getActualBalance());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
		}
}
