package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.UIManager;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import dao.EmployeeDAO;
import model.Employee;
import util.ValidationData;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.ImageIcon;

public class InsertEmployeeJFrame extends JInternalFrame {
	private JTextField txtId;
	private JTextField txtName;
	private JTextField txtSalary;
	private JComboBox cmbPosition;
	private JButton btnInsert;
	private JLabel lblId;
	private JLabel lblSalary;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertEmployeeJFrame frame = new InsertEmployeeJFrame();
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
	public InsertEmployeeJFrame() {
		setFrameIcon(new ImageIcon(InsertEmployeeJFrame.class.getResource("/images32/user_add_32.png")));
		setTitle("Insert Employee JFrame");
		setClosable(true);
		setBounds(100, 100, 493, 310);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Employee Data", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 457, 171);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("รหัสพนักงาน");
		label.setBounds(38, 30, 90, 14);
		panel.add(label);
		
		JLabel label_1 = new JLabel("ชื่อ-นามสกุล");
		label_1.setBounds(38, 61, 90, 14);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("ตำแหน่ง");
		label_2.setBounds(38, 97, 90, 14);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("เงินเดือน");
		label_3.setBounds(38, 134, 90, 14);
		panel.add(label_3);
		
		txtId = new JTextField();
		txtId.setBounds(118, 27, 90, 20);
		panel.add(txtId);
		txtId.setColumns(10);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(118, 58, 287, 20);
		panel.add(txtName);
		
		txtSalary = new JTextField();
		txtSalary.setColumns(10);
		txtSalary.setBounds(118, 131, 90, 20);
		panel.add(txtSalary);
		
		lblSalary = new JLabel("* กรอกเป็นตัวเลขเท่านั้น");
		lblSalary.setForeground(Color.RED);
		lblSalary.setBounds(218, 134, 212, 14);
		panel.add(lblSalary);
		
		cmbPosition = new JComboBox();
		cmbPosition.setModel(new DefaultComboBoxModel(new String[] {"1: ผู้จัดการร้านอาหาร", "2: พนักงานต้อนรับ", "3: พนักงานฝ่ายบัญชี", "4: พนักงานขับรถยนต์"}));
		cmbPosition.setBounds(118, 94, 151, 20);
		panel.add(cmbPosition);
		
		lblId = new JLabel("* ขึันต้นด้วย S และตามด้วยตัวเลข 4 หลัก");
		lblId.setForeground(Color.RED);
		lblId.setBounds(218, 30, 229, 14);
		panel.add(lblId);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Commands", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 193, 457, 77);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		btnInsert = new JButton("เพิ่มข้อมูลพนักงาน");
		btnInsert.setIcon(new ImageIcon(InsertEmployeeJFrame.class.getResource("/images32/list-add-user_32.png")));
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblId.setVisible(false);
				lblSalary.setVisible(false);
				String id = txtId.getText().trim();
				String name = txtName.getText().trim();
				int position = cmbPosition.getSelectedIndex() + 1;
				String ssalary = txtSalary.getText();
				int salary;
				
				if(id.equals("") && name.equals("")){
					JOptionPane.showMessageDialog(null, "กรุณากรอกรหัสพนักงาน พร้อมชื่อ-นามสกุลด้วย");
					return;
				}else if(id.equals("")){
					JOptionPane.showMessageDialog(null, "กรุณากรอกรหัสพนักงานด้วย");	
					return;
				}else if(name.equals("")){
					JOptionPane.showMessageDialog(null, "กรุณากรอกชื่อ-นามสกุลด้วย");
					return;
				}else{
					lblId.setText("* ขึันต้นด้วย S และตามด้วยตัวเลข 4 หลัก");
					if(ssalary.equals("")){
						if(!ValidationData.checkPatternId(id)){
							lblId.setVisible(true);							
						}else{
							Employee employee = new Employee();
							employee.setId(id);
							employee.setName(name);
							employee.setPosition(position);
							
							EmployeeDAO dao = new EmployeeDAO();
							if(!dao.validId(id)){
								lblId.setText("* รหัสซ้ำ กรอกรหัสใหม่อีกครั้ง");
								lblId.setVisible(true);
								return;
							}else{
								boolean result = dao.insert(employee);
								if(result){
									JOptionPane.showMessageDialog(null, "เพิ่มข้อมูลพนักงานสำเร็จ");
								}else{
									
								}
							}
							clearData();
						}
						
					}else{
						if(!ValidationData.checkPatternId(id)){
							lblId.setText("* ขึันต้นด้วย S และตามด้วยตัวเลข 4 หลัก");
							lblId.setVisible(true);
						}
						if(!ValidationData.checkNumber(ssalary)){
							lblSalary.setVisible(true);
						}
						if(ValidationData.checkPatternId(id) && ValidationData.checkNumber(ssalary)){
							salary = Integer.parseInt(ssalary);
							Employee employee = new Employee(id, name, position, salary);
							//JOptionPane.showMessageDialog(null, "Validation");
							EmployeeDAO dao = new EmployeeDAO();
							if(!dao.validId(id)){
								lblId.setText("* รหัสซ้ำ กรอกรหัสใหม่อีกครั้ง");
								lblId.setVisible(true);
								return;
							}else{
								boolean result = dao.insert(employee);
								if(result){
									JOptionPane.showMessageDialog(null, "เพิ่มข้อมูลพนักงานสำเร็จ");
								}else{
									
								}
							}
							clearData();
						}
					}
					
				}
			}
		});
		btnInsert.setBounds(151, 22, 161, 44);
		panel_1.add(btnInsert);
		lblId.setVisible(false);
		lblSalary.setVisible(false);
	}

	protected void clearData() {
		txtId.setText("");
		txtName.setText("");
		txtSalary.setText("");
		cmbPosition.setSelectedIndex(0);
	}
	
}
