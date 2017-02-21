package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import dao.EmployeeDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class DeleteEmployeeJFrame extends JInternalFrame {
	private JTextField txtSearch;
	private JTextField txtId;
	private JTextField txtName;
	private JTextField txtSalary;
	private JComboBox cmbPosition;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteEmployeeJFrame frame = new DeleteEmployeeJFrame();
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
	public DeleteEmployeeJFrame() {
		setFrameIcon(new ImageIcon(DeleteEmployeeJFrame.class.getResource("/images32/user_delete_32.png")));
		setTitle("Delete Employee JFrame");
		setClosable(true);
		setBounds(100, 100, 498, 407);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "Search Employee", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 458, 76);
		getContentPane().add(panel);
		
		JLabel label = new JLabel("กรอกรหัสพนักงาน");
		label.setBounds(26, 36, 131, 14);
		panel.add(label);
		
		txtSearch = new JTextField();
		txtSearch.setColumns(10);
		txtSearch.setBounds(120, 33, 217, 20);
		panel.add(txtSearch);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setIcon(new ImageIcon(DeleteEmployeeJFrame.class.getResource("/images16/magnifyingglass_16.png")));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String search = txtSearch.getText().trim();
				
				EmployeeDAO dao = new EmployeeDAO();
				Vector employee = dao.selectById(search);
				
				int size = employee.size();
				if(size == 0){
					JOptionPane.showMessageDialog(null, "ไม่มีข้อมูลพนักงานที่ค้นหา");
					clearData();
				}else{
					txtId.setText((String) employee.get(0));
					txtName.setText((String) employee.get(1));
					String sposition = (String) employee.get(2);
					int position = Integer.parseInt(sposition);
					cmbPosition.setSelectedIndex(position - 1);
					txtSalary.setText((String) employee.get(3));
				}
			}
		});
		btnSearch.setBounds(347, 27, 94, 33);
		panel.add(btnSearch);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Employee Informations", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 96, 458, 182);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label_1 = new JLabel("รหัสพนักงาน");
		label_1.setBounds(27, 36, 90, 14);
		panel_1.add(label_1);
		
		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setColumns(10);
		txtId.setBounds(107, 33, 90, 20);
		panel_1.add(txtId);
		
		JLabel label_2 = new JLabel("ชื่อ-นามสกุล");
		label_2.setBounds(27, 67, 90, 14);
		panel_1.add(label_2);
		
		txtName = new JTextField();
		txtName.setEnabled(false);
		txtName.setColumns(10);
		txtName.setBounds(107, 64, 287, 20);
		panel_1.add(txtName);
		
		JLabel label_3 = new JLabel("ตำแหน่ง");
		label_3.setBounds(27, 103, 90, 14);
		panel_1.add(label_3);
		
		cmbPosition = new JComboBox();
		cmbPosition.setEnabled(false);
		cmbPosition.setModel(new DefaultComboBoxModel(new String[] {"1: ผู้จัดการร้านอาหาร", "2: พนักงานต้อนรับ", "3: พนักงานฝ่ายบัญชี", "4: พนักงานขับรถยนต์"}));
		cmbPosition.setBounds(107, 100, 151, 20);
		panel_1.add(cmbPosition);
		
		JLabel label_4 = new JLabel("เงินเดือน");
		label_4.setBounds(27, 140, 90, 14);
		panel_1.add(label_4);
		
		txtSalary = new JTextField();
		txtSalary.setEnabled(false);
		txtSalary.setColumns(10);
		txtSalary.setBounds(107, 137, 90, 20);
		panel_1.add(txtSalary);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(null, "Commands", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 289, 457, 76);
		getContentPane().add(panel_2);
		
		JButton btnDelete = new JButton("ลบข้อมูลพนักงาน");
		btnDelete.setIcon(new ImageIcon(DeleteEmployeeJFrame.class.getResource("/images32/Close_Icon_Dark_32.png")));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txtId.getText();
				int answer = JOptionPane.showConfirmDialog(null, 
						"คุณต้องการลบข้อมูลพนักงานใช่หรือไม่?", 
						"WARNING", 
						JOptionPane.YES_NO_OPTION);
				
				if(answer == JOptionPane.YES_OPTION){
					EmployeeDAO dao = new EmployeeDAO();
					
					boolean result = dao.delete(id);
					if(result){
						JOptionPane.showMessageDialog(null, "ลบข้อมูลพนักงานสำเร็จ");
						clearData();
					}else{
						
					}
				}else{
					
				}			
				
			}
		});
		btnDelete.setBounds(151, 22, 161, 34);
		panel_2.add(btnDelete);

	}

	protected void clearData() {
		// TODO Auto-generated method stub
		txtId.setText("");
		txtSalary.setText("");
		txtSearch.setText("");
		txtName.setText("");
		cmbPosition.setSelectedIndex(0);
	}

}
