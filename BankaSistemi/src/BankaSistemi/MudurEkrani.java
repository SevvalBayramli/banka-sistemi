package BankaSistemi;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class MudurEkrani extends JFrame {

	private JPanel contentPane;
	private JTextField bankaBakiyesi;
	private JTextField gelir;
	private JTextField gider2;
	private JTextField kar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MudurEkrani frame = new MudurEkrani();
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
	public MudurEkrani() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 416, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Banka Muduru Ekran\u0131");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 27, 146, 32);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Para birimi \u0130\u015Flemleri");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ParaBirimiIslemleri birimiIslemleri = new ParaBirimiIslemleri();
				birimiIslemleri.setVisible(true);
			}
		});
		btnNewButton.setBounds(10, 84, 134, 37);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel_1 = new JLabel("Banka Bakiyesi");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(166, 34, 89, 21);
		contentPane.add(lblNewLabel_1);

		bankaBakiyesi = new JTextField();
		bankaBakiyesi.setEditable(false);
		bankaBakiyesi.setBounds(265, 35, 96, 19);
		contentPane.add(bankaBakiyesi);
		bankaBakiyesi.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Gelir");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_1.setBounds(166, 65, 89, 21);
		contentPane.add(lblNewLabel_1_1);

		gelir = new JTextField();
		gelir.setText("0");
		gelir.setEditable(false);
		gelir.setColumns(10);
		gelir.setBounds(265, 66, 96, 19);
		contentPane.add(gelir);

		JLabel lblNewLabel_1_1_1 = new JLabel("Gider");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_1_1.setBounds(166, 92, 89, 21);
		contentPane.add(lblNewLabel_1_1_1);
		
		gider2 = new JTextField();
		gider2.setText("0");
		gider2.setEditable(false);
		gider2.setColumns(10);
		gider2.setBounds(265, 93, 96, 19);
		contentPane.add(gider2);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Kar");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_1_1_1.setBounds(166, 123, 89, 21);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		kar = new JTextField();
		kar.setText("0");
		kar.setEditable(false);
		kar.setColumns(10);
		kar.setBounds(265, 122, 96, 19);
		contentPane.add(kar);
		
		double bankaBakiye = 0;
		double gelir = 0,toplamgelir=0,gider=0,toplamgider=0,kar2=0;

		String sql = "Select * from hesap where hesap_id=123";
		ResultSet rs = veritabani_baglanti.tabloyaBaglantiYap(sql);
		try {
			if (rs.next())
				bankaBakiye = rs.getDouble("bakiye");

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		bankaBakiyesi.setText("" + bankaBakiye);

		sql = "select * from islem where islem_adi='krediOdeme'";
		ResultSet rs1 = veritabani_baglanti.tabloyaBaglantiYap(sql);
		try {
			while (rs1.next()) {
				gelir = rs1.getDouble("tutar");
				toplamgelir=gelir+toplamgelir;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.gelir.setText(""+toplamgelir);
		sql = "select * from islem where islem_adi='kredi«ekme'";
		ResultSet rs2 = veritabani_baglanti.tabloyaBaglantiYap(sql);
		try {
			while (rs2.next()) {
				gider = rs2.getDouble("tutar");
				toplamgider=gider+toplamgider;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//		gider2.setText(""+gider);
		gider2.setText(""+toplamgider);
		
		kar.setText(""+(toplamgelir-toplamgider));
		

		JButton btnMusteriEkle = new JButton("Musteri Ekle");
		btnMusteriEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MusteriEkleme musteriEkleme = new MusteriEkleme();
				musteriEkleme.setVisible(true);
			}
		});
		btnMusteriEkle.setBounds(10, 131, 134, 37);
		contentPane.add(btnMusteriEkle);

		JButton btnNewButton_1 = new JButton("Tarihi \u0130leri Sar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyDate.TarihiIleriSar();
			}
		});
		btnNewButton_1.setBounds(216, 180, 160, 32);
		contentPane.add(btnNewButton_1);

		JButton btnIlemler = new JButton("\u0130\u015Flemler");
		btnIlemler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BankaIslemleri bankaIslemleri = new BankaIslemleri();
				bankaIslemleri.setVisible(true);
			}
		});
		btnIlemler.setBounds(10, 178, 134, 37);
		contentPane.add(btnIlemler);

		JButton btnFaizOraniBelirle = new JButton("Faiz Orani Belirle");
		btnFaizOraniBelirle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FaizOrani faizOrani = new FaizOrani();
				faizOrani.setVisible(true);
			}
		});
		btnFaizOraniBelirle.setBounds(10, 225, 134, 37);
		contentPane.add(btnFaizOraniBelirle);
		
		
		
		

	}
}
