package orm.northwind;

import java.sql.SQLException;

public class EmployeeVirtualProxy extends Employee{
	private Employee wrapped;
	private final EmployeesDataMapper empMapper;
	private final int reportsToId;
	
	
	
	public EmployeeVirtualProxy(EmployeesDataMapper empMapper, int reportsToId) {
		this.empMapper = empMapper;
		this.reportsToId = reportsToId;
	}
	private Employee initEmployee(){
		if(wrapped == null)
			try {
				wrapped = empMapper.loadById(reportsToId);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		return  wrapped;
	}
	public java.util.Date getBirthDate() {return initEmployee().getBirthDate();}
	public String getFirstName() {return initEmployee().getFirstName();}
	//... the rest of the properties
}
