package BankaSistemi;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class TemsilciEkrani extends JFrame {

	private JPanel contentPane;
	private JTextField id;
	static Temsilci temsilci = new Temsilci();
	Musteri musteri = new Musteri();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TemsilciEkrani frame = new TemsilciEkrani(temsilci);
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
	public TemsilciEkrani(Temsilci temsilci) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 202, 386);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Temsilci Ekran\u0131");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(29, 19, 126, 34);
		contentPane.add(lblNewLabel);

		id = new JTextField();
		id.setBounds(70, 63, 96, 19);
		contentPane.add(id);
		id.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("musteri_id");
		lblNewLabel_1.setBounds(10, 66, 79, 13);
		contentPane.add(lblNewLabel_1);

		JButton musteriEkle = new JButton("Musteri Ekle");
		musteriEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MusteriEkleme mekle = new MusteriEkleme();
				mekle.setVisible(true);
			}
		});
		musteriEkle.setBounds(11, 92, 155, 41);
		contentPane.add(musteriEkle);

		JButton btnMusteriSil = new JButton("Musteri Sil");
		btnMusteriSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id2 = Integer.parseInt(id.getText());
				System.out.println(id2);
				String sql = "DELETE FROM kredi_musteri WHERE musteri_id=" + id2;
				veritabani_baglanti.sil(sql);
				sql = "DELETE FROM musteri_temsilci WHERE musteri_id=" + id2;
				veritabani_baglanti.sil(sql);
				sql = "DELETE FROM musteri WHERE musteri_id=" + id2;
				veritabani_baglanti.sil(sql);

			}
		});
		btnMusteriSil.setBounds(10, 296, 156, 41);
		contentPane.add(btnMusteriSil);

		JButton btnBilgiduzenleme = new JButton("BilgiDuzenleme");
		btnBilgiduzenleme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "select * from musteri_temsilci,musteri where musteri.musteri_id= " + id.getText()
						+ " and musteri_temsilci.temsilci_id=1"; //+ temsilci.getTemsilci_id();
				ResultSet rs = veritabani_baglanti.tabloyaBaglantiYap(sql);
				try {
					if (rs.next()) {
						

						musteri.setMusteri_id(rs.getInt("musteri_id"));
						musteri.setMusteri_ad(rs.getString("musteri_ad"));
						musteri.setMusteri_soyad(rs.getString("musteri_soyad"));
						musteri.setMusteri_eposta(rs.getString("musteri_eposta"));
						musteri.setMusteri_telefon(rs.getString("musteri_telefon"));
						musteri.setMusteri_tc(rs.getString("musteri_tc"));
						musteri.setMusteri_sifre(rs.getString("musteri_sifre"));
						musteri.setMusteri_adres(rs.getString("musteri_adres"));
						BilgilerimiGuncelle bGun = new BilgilerimiGuncelle(musteri);
						bGun.setVisible(true);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnBilgiduzenleme.setBounds(10, 143, 155, 41);
		contentPane.add(btnBilgiduzenleme);
		
		JButton btnIlemlerigrntle = new JButton("\u0130\u015FlemleriG\u00F6r\u00FCnt\u00FCle");
		btnIlemlerigrntle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Islemler islemler=new Islemler(musteri);
				islemler.setVisible(true);
			}
		});
		btnIlemlerigrntle.setBounds(11, 194, 155, 41);
		contentPane.add(btnIlemlerigrntle);
		
		JButton btnKredionay = new JButton("KrediOnay");
		btnKredionay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KrediOnay krediOnay=new KrediOnay(musteri);
				krediOnay.setVisible(true);
			}
		});
		btnKredionay.setBounds(11, 245, 155, 41);
		contentPane.add(btnKredionay);

	}

}
