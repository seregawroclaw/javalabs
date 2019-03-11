package service;

import java.awt.Button;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Frame;
import java.awt.RenderingHints.Key;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.MalformedInputException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.nio.file.Path;

public class MainService extends Frame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Button bex = new Button("Exit");
	Button sea = new Button("Search");
	TextArea txa = new TextArea();

	public MainService() {
		super("my window");
		setLayout(null);
		setBackground(new Color(150, 200, 100));
		setSize(450, 250);
		add(bex);
		add(sea);
		add(txa);
		bex.setBounds(110, 190, 100, 20);
		bex.addActionListener(this);
		sea.setBounds(110, 165, 100, 20);
		sea.addActionListener(this);
		txa.setBounds(20, 50, 300, 100);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Map<File, Integer> result = new HashMap<>();
		if (arg0.getSource() == bex)
			System.exit(0);
		else if (arg0.getSource() == sea) {
			String[] keywords = txa.getText().split(",");
			for (int j = 0; j < keywords.length; j++) {
				System.out.println(keywords[j]);
			}
			File f = new File("D:/javalabs/lab1/lab1/src/resources");
			ArrayList<File> files = new ArrayList<File>(Arrays.asList(f.listFiles()));
			txa.setText("");
			for (File elem : files) {
				int zcoincidence = test_url(elem, keywords);
				txa.append("\n" + elem + "  :" + zcoincidence);
				result.put(elem, zcoincidence);
			}
			URI fileToOpen = getRelevance(result).toURI();
			try {
				Desktop.getDesktop().browse(fileToOpen);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	private File getRelevance(Map<File, Integer> result) {
		int maxValueInMap = (Collections.max(result.values()));
		for (Entry<File, Integer> entry : result.entrySet()) {
			if (entry.getValue() == maxValueInMap) {
				return entry.getKey();
			}
		}
		return null;
	}

	public static int test_url(File elem, String[] keywords) {
		int res = 0;
		URL url = null;
		URLConnection con = null;
		int i;
		try {
			String ffele = "" + elem;
			url = new URL("file:/" + ffele.trim());
			con = url.openConnection();
			File file = new File("D:/javalabs/lab1/lab1/result.html");
			BufferedInputStream bis = new BufferedInputStream(con.getInputStream());
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
			String bhtml = ""; // file content in byte array
			while ((i = bis.read()) != -1) {
				bos.write(i);
				bhtml += (char) i;

			}
			bos.flush();
			bis.close();
			String htmlcontent = (new String(bhtml)).toLowerCase(); // file content in string
			System.out.println("New url content is: " + htmlcontent);
			for (int j = 0; j < keywords.length; j++) {
				int currentSymbol = 0;
				while(htmlcontent.indexOf(keywords[j].trim().toLowerCase(),currentSymbol) >= 0) {
						res++;
						currentSymbol = htmlcontent.indexOf(keywords[j].trim().toLowerCase(),currentSymbol)+keywords[j].trim().length();
				}
			}
			bos.close();
		} catch (MalformedInputException malformedInputException) {
			System.out.println("error " + malformedInputException.getMessage());
			return -1;
		} catch (IOException ioException) {
			System.out.println("error " + ioException.getMessage());
			return -1;
		} catch (Exception e)

		{
			System.out.println("error " + e.getMessage());
			return -1;
		}
		return res;
	}

	public static void main(String[] args) {
		new MainService();
	}
}
