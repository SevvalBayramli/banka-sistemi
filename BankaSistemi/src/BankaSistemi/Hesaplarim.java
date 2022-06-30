package BankaSistemi;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;



public class Hesaplarim extends JFrame {

	private JPanel contentPane;
	private JTable table;
	DefaultTableModel modelim = new DefaultTableModel();
	static Musteri musteri = new Musteri();
	String id  ;
	String bakiye;

	Object[] kolonlar = { "hesap_id", "parabirimi_id", "musteri_id","bakiye" };
	Object[] satirlar = new Object[4];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Hesaplarim frame = new Hesaplarim(musteri);
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
	public Hesaplarim(Musteri musteri) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 643, 386);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setBounds(21, 19, 443, 308);
		contentPane.add(scrollPane);

		table = new JTable();
		modelim.setColumnIdentifiers(kolonlar);

		table.setBounds(226, 225, 230, 128);
		scrollPane.setViewportView(table);// scrollPane ile tabloyu bağladık ve kolon bilgileri gözükmeye başladı

		JButton btn1 = new JButton("Listele");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				modelim.setRowCount(0);

				ResultSet myRs = veritabani_baglanti.tabloyaBaglantiYap("SELECT * FROM hesap WHERE musteri_id="+musteri.getMusteri_id());
				
				try {
					while (myRs.next()) {
						satirlar[0]=myRs.getString("hesap_id");
						satirlar[1]=myRs.getString("parabirimi_id");
						satirlar[2]=myRs.getString("musteri_id");
						satirlar[3]=myRs.getString("bakiye");
						modelim.addRow(satirlar);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				table.setModel(modelim);

			}
		});
		btn1.setBounds(474, 19, 127, 31);
		contentPane.add(btn1);
		
		JButton btnNewButton = new JButton("Hesap Ac");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HesapAc hesapAc;
				try {
					hesapAc = new HesapAc(musteri);
					hesapAc.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(474, 60, 127, 34);
		contentPane.add(btnNewButton);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				id=(String) modelim.getValueAt(table.getSelectedRow(), 0);
				bakiye=(String) modelim.getValueAt(table.getSelectedRow(), 3);
				System.out.println(bakiye);
				
				
			}
		});
		JButton btnHesapSil = new JButton("Hesap Sil");
		btnHesapSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//DELETE FROM hesap WHERE hesap_id=id
				if(Double.parseDouble(bakiye)==0.0) {
					String sql_sorgu="DELETE FROM hesap WHERE hesap_id="+id;
					veritabani_baglanti.sil(sql_sorgu);
				}else {
					JOptionPane.showMessageDialog(null, "Bakiyesi 0 olmayan bir hesap silinemez", "HATA", 2);
				}
				
			}
		});
		btnHesapSil.setBounds(474, 104, 127, 34);
		contentPane.add(btnHesapSil);
		
		JButton btnParaGonderme = new JButton("Para Gonderme");
		btnParaGonderme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ParaGonderme paraGonderme=new ParaGonderme(musteri);
				paraGonderme.setVisible(true);
			}
		});
		btnParaGonderme.setBounds(474, 149, 127, 34);
		contentPane.add(btnParaGonderme);
		
		JButton btnParaCekme = new JButton("Para Çekme");
		btnParaCekme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ParaCekme paracekme=new ParaCekme(musteri);
				paracekme.setVisible(true);
			}
		});
		btnParaCekme.setBounds(474, 193, 127, 34);
		contentPane.add(btnParaCekme);
		
		JButton btnParaYatirma = new JButton("Para Yatirma");
		btnParaYatirma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ParaYatirma parayatirma=new ParaYatirma(musteri);
				parayatirma.setVisible(true);
			}
		});
		btnParaYatirma.setBounds(474, 238, 127, 34);
		contentPane.add(btnParaYatirma);
		
		JButton btnNewButton_1 = new JButton("Kredi Borcu Öde");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				krediBorcuOde k=new krediBorcuOde(musteri);
				k.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(474, 284, 127, 34);
		contentPane.add(btnNewButton_1);
		
		
		
		

		// contentPane.add(table);
	}
}
