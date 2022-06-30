package BankaSistemi;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class Islemler extends JFrame {

	private JPanel contentPane;
	static Musteri musteri = new Musteri();
	DefaultTableModel modelim = new DefaultTableModel();

	Object[] kolonlar = { "islem_id", "hedef_id", "kaynak_id", "islem_adi", "tarih", "kaynak_bakiye", "hedef_bakiye",
			"tutar" };
	Object[] satirlar = new Object[8];
	private JTextField hesapId;
	private JTextField bas_tarih;
	private JTextField bit_tarih;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Islemler frame = new Islemler(musteri);
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
	public Islemler(Musteri musteri) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 643, 391);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setBounds(21, 10, 443, 326);
		contentPane.add(scrollPane);

		JTable table = new JTable();
		modelim.setColumnIdentifiers(kolonlar);

		table.setBounds(226, 225, 230, 128);
		scrollPane.setViewportView(table);
		
		
		hesapId = new JTextField();
		hesapId.setBounds(491, 67, 96, 19);
		contentPane.add(hesapId);
		hesapId.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Hesap ID");
		lblNewLabel.setBounds(515, 44, 45, 13);
		contentPane.add(lblNewLabel);
		
		hesapId.getText();

		JLabel lblNewLabel_1 = new JLabel("ba\u015Flang\u0131\u00E7 tarihi");
		lblNewLabel_1.setBounds(491, 112, 96, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("biti\u015F tarihi");
		lblNewLabel_1_1.setBounds(491, 160, 96, 13);
		contentPane.add(lblNewLabel_1_1);
		
		bas_tarih = new JTextField();
		bas_tarih.setBounds(491, 131, 96, 19);
		contentPane.add(bas_tarih);
		bas_tarih.setColumns(10);
		
		bit_tarih = new JTextField();
		bit_tarih.setColumns(10);
		bit_tarih.setBounds(491, 183, 96, 19);
		contentPane.add(bit_tarih);
		
		
		
		
		JButton btn1 = new JButton("Listele");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql_sorgu = null;
				modelim.setRowCount(0);
				if("-".equals(bit_tarih.getText()) && "-".equals(bas_tarih.getText() ) )
					sql_sorgu="SELECT islem_id,hedef_id,kaynak_id,islem_adi,tarih,kaynak_bakiye,hedef_bakiye,tutar FROM banka.islem, banka.hesap where musteri_id="+musteri.getMusteri_id()+" and (hedef_id="+hesapId.getText()+" or kaynak_id="+hesapId.getText()+") and hesap_id="+hesapId.getText()+";";
				else if("-".equals(bas_tarih.getText()) && !("-".equals(bit_tarih.getText()))) {
					
					sql_sorgu="SELECT islem_id,hedef_id,kaynak_id,islem_adi,tarih,kaynak_bakiye,hedef_bakiye,tutar FROM banka.islem, banka.hesap where musteri_id="+musteri.getMusteri_id()+" and (hedef_id="+hesapId.getText()+" or kaynak_id="+hesapId.getText()+") and hesap_id="+hesapId.getText()+" and tarih <= '"+bit_tarih.getText()+"';";
				}else if("-".equals(bit_tarih.getText()) && !("-".equals(bas_tarih.getText()))){
					
					sql_sorgu="SELECT islem_id,hedef_id,kaynak_id,islem_adi,tarih,kaynak_bakiye,hedef_bakiye,tutar FROM banka.islem, banka.hesap where musteri_id="+musteri.getMusteri_id()+" and (hedef_id="+hesapId.getText()+" or kaynak_id="+hesapId.getText()+") and hesap_id="+hesapId.getText()+" and tarih >= '"+bas_tarih.getText()+"';";
				}else {
					
					sql_sorgu="SELECT islem_id,hedef_id,kaynak_id,islem_adi,tarih,kaynak_bakiye,hedef_bakiye,tutar FROM banka.islem, banka.hesap where musteri_id="+musteri.getMusteri_id()+" and (hedef_id="+hesapId.getText()+" or kaynak_id="+hesapId.getText()+") and hesap_id="+hesapId.getText()+" and tarih between '"+bas_tarih.getText()+"' and '"
							+bit_tarih.getText()+"';";
				}
				ResultSet myRs = veritabani_baglanti.tabloyaBaglantiYap(sql_sorgu);
				
				try {
					while (myRs.next()) {
						satirlar[0]=myRs.getString("islem_id");
						satirlar[1]=myRs.getString("hedef_id");
						satirlar[2]=myRs.getString("kaynak_id");
						satirlar[3]=myRs.getString("islem_adi");
						satirlar[4]=myRs.getString("tarih");
						satirlar[5]=myRs.getString("kaynak_bakiye");
						satirlar[6]=myRs.getString("hedef_bakiye");
						satirlar[7]=myRs.getString("tutar");
						
						modelim.addRow(satirlar);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				table.setModel(modelim);

			}
		});
		btn1.setBounds(474, 233, 127, 31);
		contentPane.add(btn1);
		
	
	}
}
