
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class Login extends JFrame {
	private JTextField txtName;
	private JPasswordField pwdfield;
	private final JLabel lblTitle = new JLabel("欢迎进入学生成绩管理系统");
	public Login() {
		setTitle("登录");
		
		JPanel panelCenter = new JPanel();
		getContentPane().add(panelCenter, BorderLayout.CENTER);
		panelCenter.setPreferredSize(new Dimension(300, 200));
		panelCenter.setLayout(new GridLayout(3, 2, 0, 0));
	
		
		JLabel lblName = new JLabel("        账号:");
		lblName.setPreferredSize(new Dimension(100, 50));
		lblName.setSize(new Dimension(200, 50));
		lblName.setLocation(new Point(50, 100));
		lblName.setFont(new Font("宋体", Font.PLAIN, 20));	
		panelCenter.add(lblName);		
		
		txtName = new JTextField();
		txtName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtName.setMaximumSize(new Dimension(200, 50));
		txtName.setMinimumSize(new Dimension(200, 50));
		txtName.setPreferredSize(new Dimension(200, 50));
		txtName.setSize(new Dimension(200, 50));
		panelCenter.add(txtName);		
		txtName.setColumns(10);
		
		JLabel lblpwd = new JLabel("        密码:");
		lblpwd.setFont(new Font("宋体", Font.PLAIN, 20));
		panelCenter.add(lblpwd);
		
		pwdfield = new JPasswordField();
		pwdfield.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		panelCenter.add(pwdfield);
		
		JRadioButton rdstu = new JRadioButton("Student         ");
		rdstu.setFont(new Font("Arial", Font.PLAIN, 12));
		rdstu.setHorizontalAlignment(SwingConstants.RIGHT);
		panelCenter.add(rdstu);
		
		JRadioButton rdteacher = new JRadioButton("Teacher");
		rdteacher.setFont(new Font("Arial", Font.PLAIN, 12));
		rdteacher.setHorizontalAlignment(SwingConstants.LEFT);
		panelCenter.add(rdteacher);
		
		lblTitle.setPreferredSize(new Dimension(132, 50));
		lblTitle.setFont(new Font("宋体", Font.PLAIN, 20));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setHorizontalTextPosition(SwingConstants.CENTER);
		
		getContentPane().add(lblTitle, BorderLayout.NORTH);
		
		JPanel panelSouth = new JPanel();
		panelSouth.setPreferredSize(new Dimension(10, 50));
		getContentPane().add(panelSouth, BorderLayout.SOUTH);
		
		JButton btnOK = new JButton("登录");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection(
		                    "jdbc:mysql://127.0.0.1:33061/s_c_sc",
		                    "root",
		                    "db123456");
					Statement stm = conn.createStatement();
					ResultSet rs = stm.executeQuery("select * from login");
					while(rs.next()) {						
						if(rs.getString(1).equals(txtName.getText())&&rs.getString(2).equals(new String(pwdfield.getPassword()))) {
							System.out.println("Welcome");	
							if(rdstu.isSelected()) {
							   StudentMainFrame mf = new StudentMainFrame(txtName.getText());
							   mf.setSize(500,300);
						       mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						       mf.setVisible(true);		
							}else if(rdteacher.isSelected()) {
								TeacherMainFrame mf = new TeacherMainFrame(txtName.getText());
								mf.setSize(500,300);
							    mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							    mf.setVisible(true);		
							}								
						} 						    
					}					
					rs.close();
					stm.close();
					conn.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}catch(Exception e1) {
					e1.printStackTrace();
				}finally {
					
				}
			}
		});
		btnOK.setFont(new Font("宋体", Font.PLAIN, 20));
		panelSouth.add(btnOK);
		
		JButton btnCancel = new JButton("取消");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtName.setText("");
				pwdfield.setText("");
			}
		});{
			
		}
		btnCancel.setFont(new Font("宋体", Font.PLAIN, 20));
		panelSouth.add(btnCancel);		
	}
}
