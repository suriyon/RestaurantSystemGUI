package view;

import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.EmployeeDAO;

import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;

public class ShowEmployeeJFrame extends JInternalFrame {
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel model;
	

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
					ShowEmployeeJFrame frame = new ShowEmployeeJFrame();
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
	public ShowEmployeeJFrame() {
		setFrameIcon(new ImageIcon(ShowEmployeeJFrame.class.getResource("/images32/employee_32.png")));
		setTitle("Show Employee JFrame");
		setClosable(true);
		setBounds(100, 100, 586, 460);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Show Employees", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 557, 409);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 25, 533, 373);
		panel.add(scrollPane);

		prepareTable();
		addDataToTable();
	}

	private void addDataToTable() {
		removeDataFromTable();
		
		EmployeeDAO dao = new EmployeeDAO();
		Vector employees = dao.selectAll();
		
		int row = employees.size();
		for(int i=0; i<row; i++){
			model.addRow((Vector) employees.get(i));
		}
	}

	private void removeDataFromTable() {
		int row = model.getRowCount();
		if(row > 0){
			for(int i=0; i<row; i++){
				model.removeRow(0);
			}
		}
	}

	private void prepareTable() {
		model = new DefaultTableModel(null, new Object[]{
				"รหัสพนักงาน","ชื่อ-นามสกุล","ตำแหน่ง","เงินเดือน"
		});
//		model.addColumn("รหัสพนักงาน");
//		model.addColumn("ชื่อ-นามสกุล");
//		model.addColumn("ตำแหน่ง");
//		model.addColumn("เงินเดือน");
		
		table = new JTable(model){

			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
			
		};
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setFillsViewportHeight(true);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(120);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(120);
		
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		
		scrollPane.add(table);
		scrollPane.setViewportView(table);
	}
}
