import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class InsertCourseFrame extends JFrame {
	public TCourseFrame tcf;
	private JTextField tfcno;
	private JTextField tfcname;
	private JTextField tfccredit;
	private JTextField tfcpno;
	public InsertCourseFrame() {
		setTitle("添加课程");
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(5, 2, 0, 0));
		
		JLabel lblNewLabel = new JLabel("课程号：");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		
		tfcno = new JTextField();
		tfcno.setPreferredSize(new Dimension(this.getWidth()*2/3, tfcno.getPreferredSize().height));
		panel.add(tfcno);
		//textField.setColumns(20);
		
		JLabel lblNewLabel_1 = new JLabel("课程名：");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_1);
		
		tfcname = new JTextField();
		tfcname.setPreferredSize(new Dimension(this.getWidth()*2/3, tfcname.getPreferredSize().height));
		panel.add(tfcname);
		//textField_1.setColumns(20);
		
		
		JLabel lblNewLabel_2 = new JLabel("学  分：");		
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_2);
		
		tfccredit = new JTextField();
		tfccredit.setPreferredSize(tfcname.getPreferredSize());
		panel.add(tfccredit);
		
		
		
		JLabel lblNewLabel_3 = new JLabel("先修课程：");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_3);
		
		tfcpno = new JTextField();
		tfcpno.setPreferredSize(tfcname.getPreferredSize());
		panel.add(tfcpno);
		
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection(
		                    "jdbc:mysql://127.0.0.1:33061/s_c_sc",
		                    "root",
		                    "db123456");
					String insertSQL = "insert into Course(cno,cname,ccredit,cpno) values(?,?,?,?)";
				    PreparedStatement stm = conn.prepareStatement(insertSQL);
					stm.setString(1,tfcno.getText());
					stm.setString(2, tfcname.getText());
					stm.setInt(3, Integer.parseInt(tfccredit.getText()));
					stm.setString(4, tfcpno.getText());
					int affectedRows = stm.executeUpdate();
					if(affectedRows == 1) {
						JOptionPane.showMessageDialog(null, "插入成功！");
						dispose();//关闭当前窗口
					}
						
					else {
						JOptionPane.showMessageDialog(null, "插入失败！请重新录入数据！");	
						tfcno.setText("");
						tfcname.setText("");
						tfccredit.setText("");
						tfcpno.setText("");
					}						
				}catch(Exception ex) {
					tfcno.setText("");
					tfcname.setText("");
					tfccredit.setText("");
					tfcpno.setText("");
				}
			}
		});
		btnOK.setFont(new Font("Arial", Font.PLAIN, 12));
		panel.add(btnOK);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Arial", Font.PLAIN, 12));
		panel.add(btnCancel);
		
	}

}
