import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TCourseFrame extends JFrame{
	private JTable tableshow;
	private JTextField tfName;
	public TCourseFrame() {
		setTitle("课程列表");
		String s = "SELECT * FROM  course ORDER BY cno ASC;";
		DefaultTableModel model = getTabelModel(s);
		
		tableshow = new JTable(model);
		tableshow.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPane = new JScrollPane(tableshow);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel_update = new JPanel();
		getContentPane().add(panel_update, BorderLayout.NORTH);
		
		JButton btnAdd = new JButton("增加");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertCourseFrame ic = new InsertCourseFrame();				
				ic.setSize(500,300);
			    ic.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			    ic.setVisible(true);	
			}
		});
		btnAdd.setActionCommand("Add");
		panel_update.add(btnAdd);
		
		JButton btnModify = new JButton("保存修改");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection(
		                    "jdbc:mysql://127.0.0.1:33061/s_c_sc",
		                    "root",
		                    "db123456");
					//String sql = "UPDATE Employees SET age = 30 WHERE id = 103";
					String[] clist = {"cno","cname","ccredit","cpno"};
					int srow = tableshow.getSelectedRow();
					int scolumn = tableshow.getSelectedColumn();
					String selectedcno = tableshow.getValueAt(srow, 0).toString();
					String sql = "update course set "+clist[scolumn] +"= '"+ tableshow.getValueAt(srow, scolumn) +"' where cno ="+selectedcno ;
				    PreparedStatement stm = conn.prepareStatement(sql);
					System.out.println(sql);
					int affectedRows = stm.executeUpdate();
					if(affectedRows == 1) {
						JOptionPane.showMessageDialog(null, "修改成功！");						
					}
						
					else {
						JOptionPane.showMessageDialog(null, "修改失败！");	
						
					}						
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
			
		});
		panel_update.add(btnModify);
		
		JButton btnDelete = new JButton("删除");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedrow = tableshow.getSelectedRow();
			    String selectedcno = tableshow.getValueAt(selectedrow, 0).toString();
			    try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection(
		                    "jdbc:mysql://127.0.0.1:33061/s_c_sc",
		                    "root",
		                    "db123456");
					String insertSQL = "delete from Course where cno = "+ selectedcno;
				    PreparedStatement stm = conn.prepareStatement(insertSQL);
					
					int affectedRows = stm.executeUpdate();
					if(affectedRows == 1) {
						JOptionPane.showMessageDialog(null, "删除成功！");	
						tableshow.setModel(getTabelModel(s));
					}
						
					else {
						JOptionPane.showMessageDialog(null, "删除失败！");	
						
					}						
				}catch(Exception ex) {
					
				}
			    
			}
		});
		panel_update.add(btnDelete);
		
		JPanel panel_search = new JPanel();
		getContentPane().add(panel_search, BorderLayout.SOUTH);
		panel_search.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		String[] items = {"课程号","课程名","学分","先修课程"};
		String[] cols = {"cno","cname","ccredit","cpno"};
		JComboBox cbb = new JComboBox(items);
		panel_search.add(cbb);
		
		tfName = new JTextField();
		panel_search.add(tfName);
		tfName.setColumns(25);
		
		JButton btnSearch = new JButton("查找");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = "SELECT * FROM  course where "+ cols[cbb.getSelectedIndex()]+ " like '%"+tfName.getText()+"%'";
				DefaultTableModel model  = getTabelModel(s);
				tableshow.setModel(model);
				/*DefaultTableModel model  = new DefaultTableModel();
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection(
		                    "jdbc:mysql://127.0.0.1:33061/dsdb",
		                    "root",
		                    "db123456");
					Statement stm = conn.createStatement();
					ResultSet rs = stm.executeQuery("SELECT * FROM  person where name = '"+tfName.getText()+"'");			
							 
		            // 添加列名
		            ResultSetMetaData rsmd = rs.getMetaData();
		            int columnsCount = rsmd.getColumnCount();
		            for (int i = 1; i <= columnsCount; i++) {
		                model.addColumn(rsmd.getColumnName(i));
		            } 
		            // 添加数据行
		            while (rs.next()) {
		                Object[] rowData = new Object[columnsCount];
		                for (int i = 1; i <= columnsCount; i++) {
		                    rowData[i - 1] = rs.getObject(i);
		                }
		                model.addRow(rowData);
		            } 
		            tableshow.setModel(model);	
		            
			}catch(SQLException se) {
				se.printStackTrace();
			}catch(Exception e1) {
				e1.printStackTrace();
			}*/
			}});
		
		panel_search.add(btnSearch);
		
		JButton btnRefresh = new JButton("刷新");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 tableshow.setModel(getTabelModel(s));
			}
		});
		panel_search.add(btnRefresh);
	}
	@SuppressWarnings("finally")
	public DefaultTableModel getTabelModel(String s) {
		DefaultTableModel model  = new DefaultTableModel();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:33061/s_c_sc",
                    "root",
                    "db123456");
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(s);			
					 
            // 添加列名
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsCount = rsmd.getColumnCount();
            for (int i = 1; i <= columnsCount; i++) {
                model.addColumn(rsmd.getColumnName(i));
            } 
            // 添加数据行
            while (rs.next()) {
                Object[] rowData = new Object[columnsCount];
                for (int i = 1; i <= columnsCount; i++) {
                    rowData[i - 1] = rs.getObject(i);
                }
                model.addRow(rowData);
            } 
			rs.close();
			stm.close();
			conn.close();
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e1) {
			e1.printStackTrace();
		}finally {
			return model;
		}
	}
}
