package com.tcpip147.jcefbootstrap;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainFrame extends JFrame {

	@Autowired
	private JcefBrowserProvider browserProvider;

	public void open() {
		JcefBrowser browser = browserProvider.newComponent("WebContents/index.html");
		getContentPane().add(browser.getComponent(), BorderLayout.CENTER);
		pack();
		setSize(800, 600);
		setTitle("Jcef Bootstrap");
		setVisible(true);
	}
}
