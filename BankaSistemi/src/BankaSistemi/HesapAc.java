package BankaSistemi;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HesapAc extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel_1;
	private JTextField textField;
	String HesabinParaBirimi;
	private JButton btnNewButton;
	static Musteri musteri = new Musteri();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HesapAc frame = new HesapAc(musteri);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param musteri 
	 * 
	 * @throws SQLException
	 */
	public HesapAc(Musteri musteri) throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 381, 177);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("HESAP A\u00C7MA");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(91, 10, 181, 42);
		contentPane.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("Hesab\u0131n Para Birimi T\u00FCr\u00FCn\u00FC Giriniz");
		lblNewLabel_1.setBounds(29, 51, 156, 42);
		contentPane.add(lblNewLabel_1);

		textField = new JTextField();
		textField.setBounds(195, 62, 96, 19);
		contentPane.add(textField);
		textField.setColumns(10);

		ResultSet rs;

		String sql_sorgu = "SELECT * from para_birimi";
		rs = veritabani_baglanti.tabloyaBaglantiYap(sql_sorgu);
		// System.out.println(rs.getString("para_birimi_adi"));
		ArrayList paraBirimleri = new ArrayList();
		try {
			while (rs.next()) {
				paraBirimleri.add(rs.getString("para_birimi_adi"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Object[] parabirimleriDizi = paraBirimleri.toArray();

		for (int i = 0; i < paraBirimleri.size(); i++) {
			if (textField.getText() == parabirimleriDizi[i]) {
				HesabinParaBirimi = textField.getText();
			}
		}

		btnNewButton = new JButton("Hesap Ac");
		btnNewButton.addActionListener(new ActionListener() {
			boolean flag = false;

			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < paraBirimleri.size(); i++) {
					if (textField.getText().toString().equals(parabirimleriDizi[i])) {
						HesabinParaBirimi = textField.getText().toString();
						flag = true;
						String sqlsorgu="INSERT INTO hesap(parabirimi_id,musteri_id,bakiye) VALUES ("+(i+1) + ", " +musteri.getMusteri_id() +" ,"+0+" )";
						System.out.println(sqlsorgu);
						veritabani_baglanti.ekle(sqlsorgu);
						JOptionPane.showMessageDialog(null, "Hesabınız Oluşturuldu", "Hatalı Giriş", 2);
					}
				}
				if (flag == false) {
					JOptionPane.showMessageDialog(null, "Geçerli bir parabirimi giriniz", "Hatalı Giriş", 2);
				}
				
				
			}
		});
		btnNewButton.setBounds(120, 103, 85, 21);
		contentPane.add(btnNewButton);

	}



}
