package BankaSistemi;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class girisEkrani extends JFrame {

	private JPanel contentPane;
	private JTextField kullaniciadi;
	private JTextField sifre;
	static LocalDate date = LocalDate.now();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					girisEkrani frame = new girisEkrani();
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
	public girisEkrani() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("kullanici adi");
		lblNewLabel.setBounds(75, 62, 45, 13);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("sifre");
		lblNewLabel_1.setBounds(75, 127, 45, 13);
		contentPane.add(lblNewLabel_1);

		kullaniciadi = new JTextField();
		kullaniciadi.setBounds(160, 59, 96, 19);
		contentPane.add(kullaniciadi);
		kullaniciadi.setColumns(10);

		sifre = new JTextField();
		sifre.setBounds(160, 124, 96, 19);
		contentPane.add(sifre);
		sifre.setColumns(10);

		JButton btnNewButton_1 = new JButton("Musteri Girisi");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PreparedStatement ps;
				ResultSet rs;
				//ResultSet rs1;
				String kullaniciadi1 = kullaniciadi.getText();
				String sifre1 = sifre.getText();
				Connection myCnt;
				//String arama = "SELECT * FROM musteri WHERE musteri_tc = ? AND musteri_sifre = ?";
				try {
					myCnt = DriverManager.getConnection("jdbc:mysql://localhost:3306/banka", "root", "1234");
					//ps = myCnt.prepareStatement(arama);
					//ps.setString(1, kullaniciadi1);
					//ps.setString(2, sifre1);
					//rs = ps.executeQuery();
					rs = veritabani_baglanti.tabloyaBaglantiYap("SELECT * FROM musteri WHERE musteri_tc = "
							+ kullaniciadi1 + " AND musteri_sifre =" + sifre1);
					if (rs.next()) {
						Musteri musteri = new Musteri();

						musteri.setMusteri_id(rs.getInt("musteri_id"));
						musteri.setMusteri_ad(rs.getString("musteri_ad"));
						musteri.setMusteri_soyad(rs.getString("musteri_soyad"));
						musteri.setMusteri_eposta(rs.getString("musteri_eposta"));
						musteri.setMusteri_telefon(rs.getString("musteri_telefon"));
						musteri.setMusteri_tc(rs.getString("musteri_tc"));
						musteri.setMusteri_sifre(rs.getString("musteri_sifre"));
						musteri.setMusteri_adres(rs.getString("musteri_adres"));

						JOptionPane.showMessageDialog(null, "Giriş Başarılı", "Başarılı giriş", 2);
						MusteriEkrani mEkrani = new MusteriEkrani(musteri);
						mEkrani.setVisible(true);
					}
					
					else {
						JOptionPane.showMessageDialog(null, "Şifre Veya Kullanıcı Adı Hatalı", "Hatalı Giriş", 2);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnNewButton_1.setBounds(35, 179, 111, 43);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_1_1 = new JButton("Temsilci Girisi");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PreparedStatement ps;
				ResultSet rs;
				String kullaniciadi1 = kullaniciadi.getText();
				String sifre1 = sifre.getText();
				Connection myCnt;
				String arama = "SELECT * FROM temsilci WHERE temsilci_tc = ? AND temsilci_sifre = ?";
				try {
					myCnt = DriverManager.getConnection("jdbc:mysql://localhost:3306/banka", "root", "1234");
					ps = myCnt.prepareStatement(arama);
					ps.setString(1, kullaniciadi1);
					ps.setString(2, sifre1);
					rs = ps.executeQuery();
					if (rs.next()) {
						rs = veritabani_baglanti.tabloyaBaglantiYap("SELECT * FROM temsilci WHERE temsilci_tc = "
								+ kullaniciadi1 + " AND temsilci_sifre =" + sifre1);
						if (rs.next()) {
							Temsilci temsilci = new Temsilci();

							temsilci.setTemsilci_id(rs.getInt("temsilci_id"));
							temsilci.setTemsilci_tc(rs.getString("temsilci_tc"));
							temsilci.setTemsilci_sifre(rs.getString("temsilci_sifre"));
							temsilci.setTemsilci_sifre(rs.getString("temsilci_maas"));
							

						
						
						JOptionPane.showMessageDialog(null, "Giriş Başarılı", "Başarılı giriş", 2);
						TemsilciEkrani ekrani =new TemsilciEkrani(temsilci);
						ekrani.setVisible(true);
						// KAPATILACAK
					} else {
						JOptionPane.showMessageDialog(null, "Şifre Veya Kullanıcı Adı Hatalı", "Hatalı Giriş", 2);
					}}}
				 catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnNewButton_1_1.setBounds(160, 179, 111, 43);
		contentPane.add(btnNewButton_1_1);

		JButton btnNewButton_1_2 = new JButton("Mudur Girisi");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet rs;
				String kullaniciadi1 = kullaniciadi.getText();
				String sifre1 = sifre.getText();
				Connection myCnt;
				try {
					myCnt = DriverManager.getConnection("jdbc:mysql://localhost:3306/deneme", "root", "1234");
					rs = veritabani_baglanti.tabloyaBaglantiYap("SELECT * FROM bankamuduru WHERE tc = "
							+ kullaniciadi1 + " AND sifre =" + sifre1);
					
					if (rs.next()) {
						BankaMuduru mudur = new BankaMuduru();

						mudur.setIdbankaMuduru(rs.getInt("idbankaMuduru"));
						mudur.setTc(rs.getString("tc"));
						mudur.setSifre(rs.getString("sifre"));
						JOptionPane.showMessageDialog(null, "Giriş Başarılı", "Başarılı giriş", 2);
						MudurEkrani mudurEkrani=new MudurEkrani();
						mudurEkrani.setVisible(true);
						// KAPATILACAK
					} else {
						JOptionPane.showMessageDialog(null, "Şifre Veya Kullanıcı Adı Hatalı", "Hatalı Giriş", 2);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnNewButton_1_2.setBounds(281, 179, 111, 43);
		contentPane.add(btnNewButton_1_2);
	}
}
