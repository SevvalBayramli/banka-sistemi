package BankaSistemi;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class FaizOrani extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FaizOrani frame = new FaizOrani();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FaizOrani() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 223, 192);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Faiz Oran\u0131 Belirleme");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 10, 175, 47);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("faiz");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(10, 54, 62, 27);
		contentPane.add(lblNewLabel_1);

		textField = new JTextField();
		textField.setBounds(103, 67, 95, 16);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("gecikme faiz");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(10, 91, 95, 27);
		contentPane.add(lblNewLabel_1_1);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(103, 93, 95, 16);
		contentPane.add(textField_1);

		JButton btnNewButton = new JButton("Belirle");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String sql = "UPDATE faiz SET faiz=" + textField.getText() + " ,gecikme_faizi=" + textField_1.getText()
						+ " where idfaiz=1";
				String sql1 = "select * from faiz";
				// veritabani_baglanti.update2(sql);

				ResultSet rs = veritabani_baglanti.tabloyaBaglantiYap(sql1);

				veritabani_baglanti.update(sql);

			}
		});
		btnNewButton.setBounds(58, 128, 85, 21);
		contentPane.add(btnNewButton);
	}

}
