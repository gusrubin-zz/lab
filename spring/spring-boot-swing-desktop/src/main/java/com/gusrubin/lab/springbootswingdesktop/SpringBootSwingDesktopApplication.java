package com.gusrubin.lab.springbootswingdesktop;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SpringBootSwingDesktopApplication {

	public static void main(String[] args) {
//		SpringApplication.run(SpringBootSwingDesktopApplication.class, args);
		new SpringApplicationBuilder(SpringBootSwingDesktopApplication.class).headless(false).run(args);
	}

	public void run(String... args) {
		JFrame frame = new JFrame("Spring Boot Swing App");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		JPanel panel = new JPanel(new BorderLayout());
		JTextField text = new JTextField("Spring Boot can be used with Swing apps");
		panel.add(text, BorderLayout.CENTER);
		frame.setContentPane(panel);
		frame.setVisible(true);
	}

}
