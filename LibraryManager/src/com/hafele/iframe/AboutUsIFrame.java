package com.hafele.iframe;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
* @author Dragon Wen E-mail:18475536452@163.com
* @version 创建时间：2017年10月12日 下午2:40:33
* 类说明
*/
@SuppressWarnings("serial")
public class AboutUsIFrame extends JInternalFrame {

	/**
	 * Create the frame.
	 */
	public AboutUsIFrame() {
		setTitle("关于我们");
		setIconifiable(true);
		setFrameIcon(new ImageIcon(AboutUsIFrame.class.getResource("/icon/aboutus_16px.png")));
		setClosable(true);
		setBounds(100, 100, 250, 250);
		
		JPanel logoPanel = new JPanel();
		getContentPane().add(logoPanel, BorderLayout.CENTER);
		
		JLabel authorLabel = new JLabel("作者：Dragon Wen");
		authorLabel.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		
		JLabel emailLabel = new JLabel("邮箱：18475536452@163.com");
		emailLabel.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		
		JLabel logoLabel = new JLabel("");
		logoLabel.setIcon(new ImageIcon(AboutUsIFrame.class.getResource("/icon/logo.PNG")));
		GroupLayout gl_logoPanel = new GroupLayout(logoPanel);
		gl_logoPanel.setHorizontalGroup(
			gl_logoPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_logoPanel.createSequentialGroup()
					.addGroup(gl_logoPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_logoPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(logoLabel, GroupLayout.PREFERRED_SIZE, 214, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_logoPanel.createSequentialGroup()
							.addGap(60)
							.addComponent(authorLabel, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_logoPanel.createSequentialGroup()
							.addGap(20)
							.addComponent(emailLabel, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_logoPanel.setVerticalGroup(
			gl_logoPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_logoPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(logoLabel, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(authorLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(emailLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addGap(28))
		);
		logoPanel.setLayout(gl_logoPanel);
		
		JPanel buttonPanel = new JPanel();
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		
		JButton closeButton = new JButton("关闭");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doDefaultCloseAction();
			}
		});
		closeButton.setIcon(new ImageIcon(AboutUsIFrame.class.getResource("/icon/logoff_16px.png")));
		buttonPanel.add(closeButton);

		//设置窗体可见
		this.setVisible(true);
	}
}
