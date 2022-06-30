package BankaSistemi;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class BilgilerimiGuncelle extends JFrame {

	private JPanel contentPane;
	private JTextField textad;
	private JTextField textsoyad;
	private JTextField texteposta;
	private JTextField texttelefon;
	private JTextField texttc;
	private JTextField textsifre;
	private JTextField textadres;
	static Musteri musteri = new Musteri();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BilgilerimiGuncelle frame = new BilgilerimiGuncelle(musteri);
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
	public BilgilerimiGuncelle(Musteri musteri) {
		this.musteri = musteri;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 332, 434);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Ad");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 62, 119, 28);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Soyad");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(10, 100, 119, 28);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("E-posta");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setBounds(10, 138, 119, 28);
		contentPane.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("Telefon");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setBounds(10, 183, 119, 28);
		contentPane.add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_4 = new JLabel("TC");
		lblNewLabel_1_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_4.setBounds(10, 221, 119, 28);
		contentPane.add(lblNewLabel_1_4);

		JLabel lblNewLabel_1_5 = new JLabel("\u015Eifre");
		lblNewLabel_1_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_5.setBounds(10, 259, 119, 28);
		contentPane.add(lblNewLabel_1_5);

		JLabel lblNewLabel_1_6 = new JLabel("Adres");
		lblNewLabel_1_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_6.setBounds(10, 297, 119, 28);
		contentPane.add(lblNewLabel_1_6);

		textad = new JTextField();
		textad.setBounds(173, 62, 135, 33);
		contentPane.add(textad);
		textad.setColumns(10);
		textad.setText(musteri.getMusteri_ad());

		textsoyad = new JTextField();
		textsoyad.setColumns(10);
		textsoyad.setBounds(173, 105, 135, 33);
		contentPane.add(textsoyad);
		textsoyad.setText(musteri.getMusteri_soyad());

		texteposta = new JTextField();
		texteposta.setColumns(10);
		texteposta.setBounds(173, 143, 135, 33);
		contentPane.add(texteposta);
		texteposta.setText(musteri.getMusteri_eposta());

		texttelefon = new JTextField();
		texttelefon.setColumns(10);
		texttelefon.setBounds(173, 181, 135, 33);
		contentPane.add(texttelefon);
		texttelefon.setText(musteri.getMusteri_telefon());

		texttc = new JTextField();
		texttc.setColumns(10);
		texttc.setBounds(173, 219, 135, 33);
		contentPane.add(texttc);
		texttc.setText(musteri.getMusteri_tc());

		textsifre = new JTextField();
		textsifre.setColumns(10);
		textsifre.setBounds(173, 254, 135, 33);
		contentPane.add(textsifre);
		textsifre.setText(musteri.getMusteri_sifre());

		textadres = new JTextField();
		textadres.setColumns(10);
		textadres.setBounds(173, 292, 135, 33);
		contentPane.add(textadres);
		textadres.setText(musteri.getMusteri_adres());

		JLabel lblNewLabel = new JLabel("Bilgilerimi G\u00FCncelle");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(72, 11, 170, 41);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Guncelle");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String arama = "UPDATE musteri SET musteri_ad= '" + textad.getText() + "', musteri_soyad= '"
						+ textsoyad.getText() + "', musteri_eposta= '" + texteposta.getText() + "', musteri_telefon='"
						+ texttelefon.getText() + "', musteri_tc= '" + texttc.getText() + "', musteri_sifre= '"
						+ textsifre.getText() + "',musteri_adres= '" + textadres.getText() + "' WHERE musteri_id= "
						+ musteri.getMusteri_id();

				try {
					veritabani_baglanti.update(arama);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btnNewButton.setBounds(110, 354, 85, 21);
		contentPane.add(btnNewButton);

	}

}
