package database;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Database extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField dNameText;
	private JTextField dNumberText;
	private JTextField mgr_ssnText;
	private JTextField mgr_start_dateText;
	private JTable table;
	DatabaseServices db = new DatabaseServices();
	private JTextField dnumberdeptText;
	private JTextField dlocationdeptText;
	private JTable table_1;
	private JTable table_2;
	private JTextField eIDText;
	private JTextField eNameText;
	private JTextField eDeptNoText;
	private JTextField eDOBText;
	private JTextField eJobText;
	private JTextField eSalaryText;
	private JTextField eSsnText;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Database frame = new Database();
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
	public Database() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 500);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 514, 439);
		contentPane.add(tabbedPane);
		
		JPanel employee = new JPanel();
		tabbedPane.addTab("EMPLOYEE", null, employee, null);
		
		JPanel department = new JPanel();
		tabbedPane.addTab("DEPARTMENT", null, department, null);
		
		JPanel dept_locations = new JPanel();
		tabbedPane.addTab("DEPT_LOCATIONS", null, dept_locations, null);
		
		employee.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 228, 489, 172);
		employee.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"EID", "Name", "Dept No", "DOB", "Job Title", "Salary", "SSN"
			}
		));
		
		scrollPane.setViewportView(table);
		db.loadEmployeeData(table, contentPane);
		
		JLabel eID = new JLabel("EID : ");
		eID.setBounds(10, 6, 58, 17);
		eID.setFont(new Font("Times New Roman", Font.BOLD, 14));
		employee.add(eID);
		
		eIDText = new JTextField();
		eIDText.setBounds(97, 5, 159, 20);
		employee.add(eIDText);
		eIDText.setColumns(10);
		
		JLabel eName = new JLabel("Name :");
		eName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		eName.setBounds(10, 34, 58, 14);
		employee.add(eName);
		
		eNameText = new JTextField();
		eNameText.setBounds(97, 32, 159, 20);
		employee.add(eNameText);
		eNameText.setColumns(10);
		
		JLabel eDeptNo = new JLabel("Dept No :");
		eDeptNo.setFont(new Font("Times New Roman", Font.BOLD, 14));
		eDeptNo.setBounds(10, 59, 72, 14);
		employee.add(eDeptNo);
		
		eDeptNoText = new JTextField();
		eDeptNoText.setBounds(97, 57, 159, 20);
		employee.add(eDeptNoText);
		eDeptNoText.setColumns(10);
		
		JLabel eDOB = new JLabel("DOB : ");
		eDOB.setFont(new Font("Times New Roman", Font.BOLD, 14));
		eDOB.setBounds(10, 84, 46, 14);
		employee.add(eDOB);
		
		eDOBText = new JTextField();
		eDOBText.setBounds(97, 82, 159, 20);
		employee.add(eDOBText);
		eDOBText.setColumns(10);
		
		JLabel eJob = new JLabel("Job Title : ");
		eJob.setFont(new Font("Times New Roman", Font.BOLD, 14));
		eJob.setBounds(10, 109, 79, 14);
		employee.add(eJob);
		
		eJobText = new JTextField();
		eJobText.setBounds(97, 107, 159, 20);
		employee.add(eJobText);
		eJobText.setColumns(10);
		
		JLabel eSalary = new JLabel("Salary : ");
		eSalary.setFont(new Font("Times New Roman", Font.BOLD, 14));
		eSalary.setBounds(10, 134, 58, 14);
		employee.add(eSalary);
		
		eSalaryText = new JTextField();
		eSalaryText.setBounds(97, 132, 159, 20);
		employee.add(eSalaryText);
		eSalaryText.setColumns(10);
		
		JLabel eSsn = new JLabel("SSN : ");
		eSsn.setFont(new Font("Times New Roman", Font.BOLD, 14));
		eSsn.setBounds(10, 159, 46, 14);
		employee.add(eSsn);
		
		eSsnText = new JTextField();
		eSsnText.setBounds(97, 157, 159, 20);
		employee.add(eSsnText);
		eSsnText.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 184, 489, 33);
		employee.add(panel_2);
		
		JButton btnNewButton_6 = new JButton("Add Employee");
		btnNewButton_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String id = eIDText.getText();
                String name = eNameText.getText();
                String deptNo = eDeptNoText.getText();
                String dob = eDOBText.getText();
                String jobTitle = eJobText.getText();
                String salary = eSalaryText.getText();
                String ssn = eSsnText.getText();
                
                if (id.isEmpty() || name.isEmpty() || deptNo.isEmpty() || dob.isEmpty() || jobTitle.isEmpty() || salary.isEmpty() || ssn.isEmpty()) {
                	JOptionPane.showMessageDialog(panel_2, "All fields must be filled out.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                db.insertEmployee(id, name, deptNo, dob, jobTitle, salary, ssn, contentPane);
				db.loadEmployeeData(table, contentPane);
				eIDText.setText("");
				eNameText.setText("");
				eDeptNoText.setText("");
				eDOBText.setText("");
				eJobText.setText("");
				eSalaryText.setText("");
				eSsnText.setText("");
			}
		});
		panel_2.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("Update");
		btnNewButton_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String id = eIDText.getText();
				if (id.isEmpty()) {
					JOptionPane.showMessageDialog(panel_2, "EID field is required.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
				}
				String eName = db.getValueEmployee(id, "Name", contentPane);
				String eDeptNo = db.getValueEmployee(id, "DeptNumber", contentPane);
				String eDOB = db.getValueEmployee(id, "DOB", contentPane);
				String eJob = db.getValueEmployee(id, "JobTitle", contentPane);
				String eSalary = db.getValueEmployee(id, "Salary", contentPane);
				String eSsn = db.getValueEmployee(id, "SSN", contentPane);
				if (!eNameText.getText().isEmpty()) {
					eName = eNameText.getText();
				}
				if (!eDeptNoText.getText().isEmpty()) {
					eDeptNo = eDeptNoText.getText();
				}
				if (!eDOBText.getText().isEmpty()) {
					eDOB = eDOBText.getText();
				}
				if (!eJobText.getText().isEmpty()) {
					eJob = eJobText.getText();
				}
				if (!eSalaryText.getText().isEmpty()) {
					eSalary = eSalaryText.getText();
				}
				if (!eSsnText.getText().isEmpty()) {
					eSsn = eSsnText.getText();
				}
				db.updateEmployee(id, eName, eDeptNo, eDOB, eJob, eSalary, eSsn, contentPane);
				db.loadEmployeeData(table, contentPane);
				eIDText.setText("");
				eNameText.setText("");
				eDeptNoText.setText("");
				eDOBText.setText("");
				eJobText.setText("");
				eSalaryText.setText("");
				eSsnText.setText("");
			}
		});
		panel_2.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("Delete ");
		btnNewButton_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String id = eIDText.getText();
				if (id.isEmpty()) {
					JOptionPane.showMessageDialog(panel_2, "EID field is required.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
				}
				db.deleteEmployee(id, contentPane);
				db.loadEmployeeData(table, contentPane);
				eIDText.setText("");
				eNameText.setText("");
				eDeptNoText.setText("");
				eDOBText.setText("");
				eJobText.setText("");
				eSalaryText.setText("");
				eSsnText.setText("");
			}
		});
		panel_2.add(btnNewButton_8);
		
		department.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(10, 153, 389, 247);
		department.add(scrollPane_1);
		
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"DName", "DNumber", "Mgr_ssn", "Mgr_start_date"
			}
		));
		
		scrollPane_1.setViewportView(table_1);
		db.loadDepartmentData(table_1, contentPane);
		
		JLabel dName = new JLabel("DName : ");
		dName.setBounds(10, 6, 58, 17);
		dName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		department.add(dName);
		
		dNameText = new JTextField();
		dNameText.setBounds(139, 5, 159, 20);
		department.add(dNameText);
		dNameText.setColumns(10);
		
		JLabel dNumber = new JLabel("DNumber :");
		dNumber.setFont(new Font("Times New Roman", Font.BOLD, 14));
		dNumber.setBounds(10, 34, 74, 14);
		department.add(dNumber);
		
		dNumberText = new JTextField();
		dNumberText.setBounds(139, 32, 159, 20);
		department.add(dNumberText);
		dNumberText.setColumns(10);
		
		JLabel mgr_ssn = new JLabel("Mgr_ssn :");
		mgr_ssn.setFont(new Font("Times New Roman", Font.BOLD, 14));
		mgr_ssn.setBounds(10, 59, 67, 14);
		department.add(mgr_ssn);
		
		mgr_ssnText = new JTextField();
		mgr_ssnText.setBounds(139, 57, 159, 20);
		department.add(mgr_ssnText);
		mgr_ssnText.setColumns(10);
		
		JLabel mgr_start_date = new JLabel("Mgr_start_date :");
		mgr_start_date.setFont(new Font("Times New Roman", Font.BOLD, 14));
		mgr_start_date.setBounds(10, 84, 119, 14);
		department.add(mgr_start_date);
		
		mgr_start_dateText = new JTextField();
		mgr_start_dateText.setBounds(139, 82, 159, 20);
		department.add(mgr_start_dateText);
		mgr_start_dateText.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 109, 389, 33);
		department.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNewButton = new JButton("Insert");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String name = dNameText.getText();
                String numText = dNumberText.getText();
                String mgrSsnText = mgr_ssnText.getText();
                String dateString = mgr_start_dateText.getText();

                if (name.isEmpty() || numText.isEmpty() || mgrSsnText.isEmpty() || dateString.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "All fields must be filled out.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
				db.insertDepartment(name, numText, mgrSsnText, dateString, contentPane);
				db.loadDepartmentData(table_1, contentPane);
				dNameText.setText("");
				dNumberText.setText("");
				mgr_ssnText.setText("");
				mgr_start_dateText.setText("");
			}
		});
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String numText = dNumberText.getText();
				if (numText.isEmpty()) {
					JOptionPane.showMessageDialog(panel, "Dnumber field is required.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
				}
				String dname = db.getValueDepartment(numText, "Dname", contentPane);
				String mgrssn = db.getValueDepartment(numText, "Mgr_ssn", contentPane);
				String mgr_start_date = db.getValueDepartment(numText, "Mgr_start_date", contentPane);
				if (!dNameText.getText().isEmpty()) {
					dname = dNameText.getText();
				}
				if (!mgr_ssnText.getText().isEmpty()) {
					mgrssn = mgr_ssnText.getText();
				}
				if (!mgr_start_dateText.getText().isEmpty()) {
					mgr_start_date = mgr_start_dateText.getText();
				}
				db.updateDepartment(dname, numText, mgrssn, mgr_start_date, panel);
				db.loadDepartmentData(table_1, contentPane);
				dNameText.setText("");
				dNumberText.setText("");
				mgr_ssnText.setText("");
				mgr_start_dateText.setText("");
			}
		});
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String numText = dNumberText.getText();
				if (numText.isEmpty()) {
					JOptionPane.showMessageDialog(panel, "Dnumber field is required.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
				}
				db.deleteDepartment(numText, contentPane);
				db.loadDepartmentData(table_1, contentPane);
				dNameText.setText("");
				dNumberText.setText("");
				mgr_ssnText.setText("");
				mgr_start_dateText.setText("");
			}
		});
		panel.add(btnNewButton_2);
		dept_locations.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_2.setBounds(10, 109, 389, 291);
		dept_locations.add(scrollPane_2);
		
		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Dnumber", "Dlocation"
			}
		));
		scrollPane_2.setViewportView(table_2);
		db.loadDepartmentLocationData(table_2, contentPane);
		
		JLabel dNumberDept = new JLabel("DNumber :");
		dNumberDept.setFont(new Font("Times New Roman", Font.BOLD, 14));
		dNumberDept.setBounds(10, 11, 68, 17);
		dept_locations.add(dNumberDept); 
		
		dnumberdeptText = new JTextField();
		dnumberdeptText.setBounds(98, 10, 159, 20);
		dept_locations.add(dnumberdeptText);
		dnumberdeptText.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Dlocation :");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 39, 68, 14);
		dept_locations.add(lblNewLabel);
		
		dlocationdeptText = new JTextField();
		dlocationdeptText.setBounds(98, 37, 159, 20);
		dept_locations.add(dlocationdeptText);
		dlocationdeptText.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 65, 389, 33);
		dept_locations.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNewButton_3 = new JButton("Insert");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String dnum = dnumberdeptText.getText();
				String dlocation = dlocationdeptText.getText();
				if (dnum.isEmpty() || dlocation.isEmpty()) {
					JOptionPane.showMessageDialog(panel_1, "All fields must be filled out.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
				}
				db.insertDeptLocation(dnum, dlocation, contentPane);
				db.loadDepartmentLocationData(table_2, contentPane);
				dnumberdeptText.setText("");
				dlocationdeptText.setText("");
			}
		});
		panel_1.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Update");
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String dnum = dnumberdeptText.getText();
				String dlocation = dlocationdeptText.getText();
				if (dnum.isEmpty() || dlocation.isEmpty()) {
					JOptionPane.showMessageDialog(panel_1, "All fields must be filled out.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
				}
				db.updateDeptLocation(dnum, dlocation, contentPane);
				db.loadDepartmentLocationData(table_2, contentPane);
				dnumberdeptText.setText("");
				dlocationdeptText.setText("");
			}
		});
		panel_1.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Delete");
		btnNewButton_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String dnum = dnumberdeptText.getText();
				String dlocation = dlocationdeptText.getText();
				if (dnum.isEmpty() || dlocation.isEmpty()) {
					JOptionPane.showMessageDialog(panel_1, "All fields must be filled out.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
				}
				db.deleteDeptLocation(dnum, dlocation, contentPane);
				db.loadDepartmentLocationData(table_2, contentPane);
				dnumberdeptText.setText("");
				dlocationdeptText.setText("");
			}
		});
		panel_1.add(btnNewButton_5);	
	}
}
