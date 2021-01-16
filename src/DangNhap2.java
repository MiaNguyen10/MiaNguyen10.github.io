
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

// import java.util.regex.*; no use of this

import javax.swing.JPasswordField;
import javax.swing.JTextField;

/*
 Promp for login
 @author team 4
*/
public class DangNhap2 {

	private JFrame frmLoginForm;
	private JTextField txtUserName;
	private JPasswordField txtPassword;


	public DangNhap2() throws IOException {
		//connect();
		initialize();
	} 

	/*
	 Create the application.
	*/
	private void initialize() throws IOException {
		frmLoginForm = new JFrame();
		frmLoginForm.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("D:/Java/App/image/login.jpg")))));
		frmLoginForm.getContentPane().setLayout(null);

		// render login content
		// Login input field
		Font font= new Font("Serif", Font.PLAIN, 20);
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(font);
		lblNewLabel.setForeground(new Color(0xFFFFDC));
		lblNewLabel.setBounds(57, 53, 100, 33);
		frmLoginForm.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(57, 112, 100, 28);
		lblNewLabel_1.setFont(new Font("Serif", Font.PLAIN, 20));
		lblNewLabel_1.setForeground(new Color(0xFFFFDC));


		frmLoginForm.getContentPane().add(lblNewLabel_1);

		txtUserName = new JTextField();
		txtUserName.setBounds(180, 53, 143, 33);
		txtUserName.setFont(new Font("Serif", Font.PLAIN, 20));

		frmLoginForm.getContentPane().add(txtUserName);
		txtUserName.setColumns(20);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(180, 112, 143, 28);
		frmLoginForm.getContentPane().add(txtPassword);
		
		// Login button
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Serif", Font.BOLD, 20));
		btnLogin.setBackground(new Color(0xD3D75E));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/game","root","10062000ha");
					Statement stmt=con.createStatement();
					String sql="select * from login where acc='"+txtUserName.getText()+"' and pass='"+txtPassword.getText().toString()+"'";
					ResultSet rs =stmt.executeQuery(sql);
					if(rs.next())
					{
						//new
						Game g = new Game(txtUserName.getText());
						frmLoginForm.setVisible(false);
					}
					else 
					{
						JOptionPane.showMessageDialog(null, "incorrect user ");
					}
					con.close();
				} catch (Exception e2) { 
					System.out.println(e2);
				}
			}
		});
		btnLogin.setBounds(50, 175, 89, 30);
		frmLoginForm.getContentPane().add(btnLogin);

		// Reset button
		JButton btnReset = new JButton("Reset");
		btnReset.setFont(new Font("Serif", Font.BOLD, 20));
		btnReset.setBackground(new Color(0xD3D75E));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtUserName.setText(null);
				txtPassword.setText(null);
			}

		});
		btnReset.setBounds(150, 175, 89, 30);
		frmLoginForm.getContentPane().add(btnReset);

		// Register button
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dangky form = new Dangky();
			}

		});
		btnRegister.setBounds(250, 175, 110, 30);
		btnRegister.setFont(new Font("Serif", Font.BOLD, 20));
		btnRegister.setBackground(new Color(0xD3D75E));
		frmLoginForm.getContentPane().add(btnRegister);

		// render window
		frmLoginForm.setTitle("Login Form");
		frmLoginForm.setSize(420,300);
		frmLoginForm.setLocationRelativeTo(null);
		frmLoginForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	// Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DangNhap2 promp = new DangNhap2();
					promp.frmLoginForm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	

}

