package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.UIManager;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.EmployeeDAO;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class SearchEmployeeJFrame extends JInternalFrame {
	private JTextField txtSearch;
	private JScrollPane scrollPane;
	private JButton btnSearch;
	
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
					SearchEmployeeJFrame frame = new SearchEmployeeJFrame();
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
	public SearchEmployeeJFrame() {
		setFrameIcon(new ImageIcon(SearchEmployeeJFrame.class.getResource("/images32/magnifyingglass_32.png")));
		setTitle("Search Employee JFrame");
		setClosable(true);
		setBounds(100, 100, 593, 488);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "Show Employees", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 98, 557, 350);
		getContentPane().add(panel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 25, 533, 314);
		panel.add(scrollPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search Employee", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 11, 557, 76);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("กรอกรหัส หรือชื่อพนักงาน");
		lblNewLabel.setBounds(26, 36, 131, 14);
		panel_1.add(lblNewLabel);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(167, 33, 221, 20);
		panel_1.add(txtSearch);
		txtSearch.setColumns(10);
		
		btnSearch = new JButton("Search");
		btnSearch.setIcon(new ImageIcon(SearchEmployeeJFrame.class.getResource("/images16/magnifyingglass_16.png")));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String search = txtSearch.getText().trim();				
				addDataToTable(search);
			}
		});
		btnSearch.setBounds(416, 27, 110, 33);
		panel_1.add(btnSearch);

		prepareTable();		
	}

	protected void addDataToTable(String search) {
		removeDataFromTable();
		EmployeeDAO dao = new EmployeeDAO();
		Vector employees = dao.selectByNameOrId(search); 
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
		// TODO Auto-generated method stub
		model = new DefaultTableModel(null, new Object[]{
			"รหัสพนักงาน","ชื่อ-นามสกุล","ตำแหน่ง","เงินเดือน"	
		});
		
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
