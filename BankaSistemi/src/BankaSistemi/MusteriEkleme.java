package BankaSistemi;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MusteriEkleme extends JFrame {

	private JPanel contentPane;
	private JTextField ad;
	private JTextField soyad;
	private JTextField eposta;
	private JTextField telefon;
	private JTextField tc;
	private JTextField sifre;
	private JTextField adres;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MusteriEkleme frame = new MusteriEkleme();
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
	public MusteriEkleme() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 354, 403);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Musteri Ekleme Ekran\u0131");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(28, 10, 276, 49);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Ad");
		lblNewLabel_1.setBounds(51, 69, 90, 23);
		contentPane.add(lblNewLabel_1);

		ad = new JTextField();
		ad.setBounds(163, 69, 114, 19);
		contentPane.add(ad);
		ad.setColumns(10);

		JLabel soyad2 = new JLabel("Soyad");
		soyad2.setBounds(51, 102, 90, 23);
		contentPane.add(soyad2);

		JLabel lblNewLabel_1_2 = new JLabel("Eposta");
		lblNewLabel_1_2.setBounds(51, 135, 90, 23);
		contentPane.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("Telefon");
		lblNewLabel_1_3.setBounds(51, 168, 90, 23);
		contentPane.add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_4 = new JLabel("Tc");
		lblNewLabel_1_4.setBounds(51, 201, 90, 23);
		contentPane.add(lblNewLabel_1_4);

		JLabel lblNewLabel_1_5 = new JLabel("Sifre");
		lblNewLabel_1_5.setBounds(51, 234, 90, 23);
		contentPane.add(lblNewLabel_1_5);

		JLabel lblNewLabel_1_6 = new JLabel("Adres");
		lblNewLabel_1_6.setBounds(51, 267, 90, 23);
		contentPane.add(lblNewLabel_1_6);

		soyad = new JTextField();
		soyad.setColumns(10);
		soyad.setBounds(163, 104, 114, 19);
		contentPane.add(soyad);

		eposta = new JTextField();
		eposta.setColumns(10);
		eposta.setBounds(163, 137, 114, 19);
		contentPane.add(eposta);

		telefon = new JTextField();
		telefon.setColumns(10);
		telefon.setBounds(163, 170, 114, 19);
		contentPane.add(telefon);

		tc = new JTextField();
		tc.setColumns(10);
		tc.setBounds(163, 203, 114, 19);
		contentPane.add(tc);

		sifre = new JTextField();
		sifre.setColumns(10);
		sifre.setBounds(163, 236, 114, 19);
		contentPane.add(sifre);

		adres = new JTextField();
		adres.setColumns(10);
		adres.setBounds(163, 269, 114, 19);
		contentPane.add(adres);

		JButton ekle = new JButton("EKLE");
		ekle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sqlsorgu = "INSERT INTO musteri(musteri_ad,musteri_soyad,musteri_eposta,musteri_telefon,musteri_tc,musteri_sifre,musteri_adres) VALUES (  '"
						+ ad.getText() + "' ," + "'" + soyad.getText() + "','" + eposta.getText() + "','"
						+ telefon.getText() + "','" + tc.getText() + "','" + sifre.getText() + "','" + adres.getText()
						+ "');";
				System.out.println(sqlsorgu);
				veritabani_baglanti.ekle(sqlsorgu);
				JOptionPane.showMessageDialog(null, "Ekleme Başarılı", "", 2);
			}
		});
		ekle.setBounds(119, 314, 85, 21);
		contentPane.add(ekle);
	}}

