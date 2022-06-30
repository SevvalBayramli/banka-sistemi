package BankaSistemi;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class MusteriEkrani extends JFrame {

	
	private JPanel contentPane;
	 static Musteri musteri=new Musteri();


	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MusteriEkrani frame = new MusteriEkrani(musteri);
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
	public MusteriEkrani(Musteri musteri) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 235, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hoşgeldin "+musteri.getMusteri_ad()+" !");
		lblNewLabel.setBounds(35, 39, 135, 28);
		contentPane.add(lblNewLabel);
		
		JButton bilgilerimiGuncelle = new JButton("Bilgilerimi Guncelle");
		bilgilerimiGuncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BilgilerimiGuncelle bil_gun=new BilgilerimiGuncelle(musteri);
				bil_gun.setVisible(true);
			}
		});
		bilgilerimiGuncelle.setBounds(35, 80, 135, 33);
		contentPane.add(bilgilerimiGuncelle);
		
		
		
		
		JButton btnHesapSil = new JButton("Hesaplarim");
		btnHesapSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Hesaplarim hesaplarim =new Hesaplarim(musteri);
				hesaplarim.setVisible(true);
			}
		});
		btnHesapSil.setBounds(35, 134, 135, 33);
		contentPane.add(btnHesapSil);
		
		JButton btnislemlerim = new JButton("İşlemlerim");
		btnislemlerim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Islemler islemler=new Islemler(musteri);
				islemler.setVisible(true);
			}
		});
		btnislemlerim.setBounds(35, 185, 135, 33);
		contentPane.add(btnislemlerim);
		
		JButton btnKrediTalep = new JButton("Kredi Talep");
		btnKrediTalep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KrediTalep krediT =new KrediTalep(musteri);
				krediT.setVisible(true);
						
			}
		});
		btnKrediTalep.setBounds(35, 238, 135, 33);
		contentPane.add(btnKrediTalep);
	}
}
