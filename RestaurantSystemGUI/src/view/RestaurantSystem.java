package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import java.awt.Frame;
import javax.swing.UIManager;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import java.awt.Font;

public class RestaurantSystem extends JFrame {

	private JPanel contentPane;
	private JButton toolbarShow;
	private JButton toolbarSearch;
	private JButton toolbarInsert;
	private JButton toolbarUpdate;
	private JButton toolbarDelete;
	private JButton toolbarExit;
	private JDesktopPane desktopPane;

	
	private InsertEmployeeJFrame insertEmployeeJFrame;
	private ShowEmployeeJFrame showEmployeeJFrame;
	private SearchEmployeeJFrame searchEmployeeJFrame;
	private UpdateEmployeeJFrame updateEmployeeJFrame;
	private DeleteEmployeeJFrame deleteEmployeeJFrame;
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
					RestaurantSystem frame = new RestaurantSystem();
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
	public RestaurantSystem() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RestaurantSystem.class.getResource("/images32/restaurant_32.png")));
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setTitle("Restaurant System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 651, 319);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("Employee Management");
		mnFile.setIcon(new ImageIcon(RestaurantSystem.class.getResource("/images16/employee_16.png")));
		mnFile.setFont(new Font("Tahoma", Font.PLAIN, 12));
		menuBar.add(mnFile);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Show Employee");
		mntmNewMenuItem_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnFile.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Find Employee");
		mntmNewMenuItem_3.setIcon(new ImageIcon(RestaurantSystem.class.getResource("/images16/magnifyingglass_16.png")));
		mntmNewMenuItem_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnFile.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Add Employee");
		mntmNewMenuItem_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnFile.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Edit Employee");
		mntmNewMenuItem_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnFile.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Delete Employee");
		mntmNewMenuItem_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnFile.add(mntmNewMenuItem_5);
		
		JMenu mnHelp = new JMenu("Help");
		mnHelp.setIcon(new ImageIcon(RestaurantSystem.class.getResource("/images16/system-help.png")));
		mnHelp.setFont(new Font("Tahoma", Font.PLAIN, 12));
		menuBar.add(mnHelp);
		
		JMenuItem mntmAboutUs = new JMenuItem("About Us");
		mntmAboutUs.setIcon(new ImageIcon(RestaurantSystem.class.getResource("/images16/about.png")));
		mntmAboutUs.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnHelp.add(mntmAboutUs);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		toolbarShow = new JButton("แสดงข้อมูลพนักงาน");
		toolbarShow.setIcon(new ImageIcon(RestaurantSystem.class.getResource("/images32/employee_32.png")));
		toolbarShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(showEmployeeJFrame == null || showEmployeeJFrame.isClosed()){
					showEmployeeJFrame = new ShowEmployeeJFrame();
					showEmployeeJFrame.setVisible(true);
					desktopPane.add(showEmployeeJFrame);
					
					try {
						showEmployeeJFrame.setMaximum(true);
					} catch (PropertyVetoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		toolBar.add(toolbarShow);
		
		toolbarSearch = new JButton("ค้นหาข้อมูลพนักงาน");
		toolbarSearch.setIcon(new ImageIcon(RestaurantSystem.class.getResource("/images32/magnifyingglass_32.png")));
		toolbarSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(searchEmployeeJFrame == null || searchEmployeeJFrame.isClosed()){
					searchEmployeeJFrame = new SearchEmployeeJFrame();
					searchEmployeeJFrame.setVisible(true);
					
					desktopPane.add(searchEmployeeJFrame);
					
					try {
						searchEmployeeJFrame.setMaximum(true);
					} catch (PropertyVetoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		toolBar.add(toolbarSearch);
		
		toolbarInsert = new JButton("เพิ่มข้อมูลพนักงาน");
		toolbarInsert.setIcon(new ImageIcon(RestaurantSystem.class.getResource("/images32/list-add-user_32.png")));
		toolbarInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(insertEmployeeJFrame == null || insertEmployeeJFrame.isClosed()){
					insertEmployeeJFrame = new InsertEmployeeJFrame();
					insertEmployeeJFrame.setVisible(true);
					desktopPane.add(insertEmployeeJFrame);
					
					try {
						insertEmployeeJFrame.setMaximum(true);
					} catch (PropertyVetoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		toolBar.add(toolbarInsert);
		
		toolbarUpdate = new JButton("แก้ไขข้อมูลพนักงาน");
		toolbarUpdate.setIcon(new ImageIcon(RestaurantSystem.class.getResource("/images32/compose_32.png")));
		toolbarUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(updateEmployeeJFrame == null || updateEmployeeJFrame.isClosed()){
					updateEmployeeJFrame = new UpdateEmployeeJFrame();
					updateEmployeeJFrame.setVisible(true);
					
					desktopPane.add(updateEmployeeJFrame);
					
					try {
						updateEmployeeJFrame.setMaximum(true);
					} catch (PropertyVetoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		toolBar.add(toolbarUpdate);
		
		toolbarDelete = new JButton("ลบข้อมูลพนักงาน");
		toolbarDelete.setIcon(new ImageIcon(RestaurantSystem.class.getResource("/images32/Remove-Male-User_32.png")));
		toolbarDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(deleteEmployeeJFrame == null || deleteEmployeeJFrame.isClosed()){
					deleteEmployeeJFrame = new DeleteEmployeeJFrame();
					deleteEmployeeJFrame.setVisible(true);
					
					desktopPane.add(deleteEmployeeJFrame);
					
					try {
						deleteEmployeeJFrame.setMaximum(true);
					} catch (PropertyVetoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		toolBar.add(toolbarDelete);
		
		toolbarExit = new JButton("ปิดโปรแกรม");
		toolbarExit.setIcon(new ImageIcon(RestaurantSystem.class.getResource("/images32/Close_Icon_Dark_32.png")));
		toolbarExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		toolBar.add(toolbarExit);
		
		desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);
	}
}
