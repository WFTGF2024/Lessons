import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class TeacherMainFrame extends JFrame{
	public static String id;
	public TeacherMainFrame(String id1) {
		setTitle("教师主界面");
		id = id1;
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(3, 1, 0, 0));
		
		JButton btnCourse = new JButton("课程查询");
		btnCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TCourseFrame pf = new TCourseFrame();
				pf.setSize(800,500);
			    pf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			    pf.setVisible(true);
			}
		});
		panel.add(btnCourse);
		
		JButton btnStudent = new JButton("学生查询");
		btnStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TStudentFrame sf = new TStudentFrame();
				sf.setSize(800,500);
			    sf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			    sf.setVisible(true);
			}
		});
		panel.add(btnStudent);
		
		JButton btnSelected = new JButton("选课查询");
		btnSelected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TSelectedFrame sf = new TSelectedFrame();
				sf.setSize(800,500);
			    sf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			    sf.setVisible(true);
			}
		});
		panel.add(btnSelected);
		
		JLabel lblNewLabel = new JLabel("              ");
		getContentPane().add(lblNewLabel, BorderLayout.WEST);
		
		JLabel lblNewLabel_1 = new JLabel("              ");
		getContentPane().add(lblNewLabel_1, BorderLayout.EAST);
	}

}
