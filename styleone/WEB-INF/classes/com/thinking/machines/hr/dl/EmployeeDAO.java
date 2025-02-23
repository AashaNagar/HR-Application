package com.thinking.machines.hr.dl;
import java.sql.*;
import java.util.*;
import java.math.*;

public class EmployeeDAO {

public List<EmployeeDTO> getAll() throws DAOException {
List<EmployeeDTO> employees = new LinkedList<>();
try {
Connection connection = DAOConnection.getConnection();
Statement statement = connection.createStatement();
ResultSet resultSet = statement.executeQuery("SELECT employee.id, employee.name, employee.designation_code, employee.date_of_birth, employee.gender, employee.is_indian, employee.basic_salary, employee.pan_number, employee.aadhar_card_number, designation.title FROM employee INNER JOIN designation ON employee.designation_code = designation.code ORDER BY employee.name");

EmployeeDTO employeeDTO;
int id;
String name;
int designationCode;
String title;
java.util.Date dateOfBirth;
String gender;
boolean isIndian;
BigDecimal basicSalary;
String panNumber;
String aadharCardNumber;

while (resultSet.next()) {
id = resultSet.getInt("id");
name = resultSet.getString("name").trim();
designationCode = resultSet.getInt("designation_code");
title = resultSet.getString("title").trim();
dateOfBirth = resultSet.getDate("date_of_birth");
gender = resultSet.getString("gender");
isIndian = resultSet.getBoolean("is_indian");
basicSalary = resultSet.getBigDecimal("basic_salary");
panNumber = resultSet.getString("pan_number").trim();
aadharCardNumber = resultSet.getString("aadhar_card_number").trim();

employeeDTO = new EmployeeDTO();
employeeDTO.setEmployeeId("A" + id);
employeeDTO.setName(name);
employeeDTO.setDesignationCode(designationCode);
employeeDTO.setTitle(title);
employeeDTO.setDateOfBirth(dateOfBirth);
employeeDTO.setGender(gender);
employeeDTO.setIsIndian(isIndian);
employeeDTO.setBasicSalary(basicSalary);
employeeDTO.setPanNumber(panNumber);
employeeDTO.setAadharCardNumber(aadharCardNumber);

employees.add(employeeDTO);
}
resultSet.close();
statement.close();
connection.close();
} catch (Exception exception) {
throw new DAOException(exception.getMessage());
}
return employees;
}
public void add(EmployeeDTO employee)throws DAOException {
try {
String panNumber=employee.getPanNumber();
String aAdharCardNumber=employee.getAadharCardNumber();
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select id from employee where pan_number=?");
preparedStatement.setString(1,panNumber);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next()) {
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("PAN Number :"+panNumber+"exist.");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select id from employee where aadhar_card_number=?");
preparedStatement.setString(1,aAdharCardNumber);
resultSet=preparedStatement.executeQuery();
if(resultSet.next()) {
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Aadhar card Number :"+aAdharCardNumber+"exist.");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("insert into employee (name,designation_code,date_of_birth,gender,is_indian,basic_salary,pan_number,aadhar_card_number)values(?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
preparedStatement.setString(1,employee.getName());
preparedStatement.setInt(2,employee.getDesignationCode());
java.util.Date dateOfBirth=employee.getDateOfBirth();
java.sql.Date sqlDate=new java.sql.Date(dateOfBirth.getTime());
preparedStatement.setDate(3,sqlDate);
preparedStatement.setString(4,employee.getGender());
preparedStatement.setBoolean(5,employee.getIsIndian());
preparedStatement.setBigDecimal(6,employee.getBasicSalary());
preparedStatement.setString(7,panNumber);
preparedStatement.setString(8,aAdharCardNumber);

preparedStatement.executeUpdate();
resultSet=preparedStatement.getGeneratedKeys();
resultSet.next();
int id=resultSet.getInt(1);
employee.setEmployeeId("A"+id);
resultSet.close();
preparedStatement.close();
connection.close();

}catch(SQLException sqlException) {
throw new DAOException(sqlException.getMessage());
}
}

public boolean panNumberExists(String panNumber) throws DAOException {
boolean exists=false;
try {
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select gender from employee where pan_number=?");
preparedStatement.setString(1,panNumber);
ResultSet resultSet=preparedStatement.executeQuery();
exists=resultSet.next();
resultSet.close();
preparedStatement.close();
connection.close();
}catch(Exception exception) {
throw new DAOException(exception.getMessage());
}
return exists;
}

public boolean aadharCardNumberExists(String aadharCardNumber) throws DAOException {
boolean exists=false;
try {
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select gender from employee where aadhar_card_number=?");
preparedStatement.setString(1,aadharCardNumber);
ResultSet resultSet=preparedStatement.executeQuery();
exists=resultSet.next();
resultSet.close();
preparedStatement.close();
connection.close();
}catch(Exception exception) {
throw new DAOException(exception.getMessage());
}
return exists;
}
public void deleteByEmployeeId(String employeeId)throws DAOException {
int actualEmployeeId=0;
try {
actualEmployeeId=Integer.parseInt(employeeId.substring(1));
}catch(Exception exception) {
throw new DAOException("invalid employee id:"+employeeId);
}
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select gender from employee where id=?");
preparedStatement.setInt(1,actualEmployeeId);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false) {
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("invalid employee id:"+employeeId);
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("delete from employee where id=?");
preparedStatement.setInt(1,actualEmployeeId);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(Exception exception) {
throw new DAOException(exception.getMessage());
}
}

public EmployeeDTO getByEmployeeId(String employeeId)throws DAOException {
EmployeeDTO employeeDTO=null;
int actualEmployeeId=0;
try
{
actualEmployeeId=Integer.parseInt(employeeId.substring(1));
}catch(Exception exception) {
throw new DAOException("invalid employee id:"+employeeId);
}
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select employee.id, employee.name, employee.designation_code, employee.date_of_birth, employee.gender, employee.is_indian, employee.basic_salary, employee.pan_number, employee.aadhar_card_number, designation.title from employee inner join designation on employee.designation_code=designation.code and employee.id=?");
preparedStatement.setInt(1,actualEmployeeId);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false) {
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("invalid employee id:"+employeeId);
}

int id;
String name;
int designationCode;
String title;
java.util.Date dateOfBirth;
String gender;
boolean isIndian;
BigDecimal basicSalary;
String panNumber;
String aadharCardNumber;

id = resultSet.getInt("id");
name = resultSet.getString("name").trim();
designationCode = resultSet.getInt("designation_code");
title = resultSet.getString("title").trim();
dateOfBirth = resultSet.getDate("date_of_birth");
gender = resultSet.getString("gender");
isIndian = resultSet.getBoolean("is_indian");
basicSalary = resultSet.getBigDecimal("basic_salary");
panNumber = resultSet.getString("pan_number").trim();
aadharCardNumber = resultSet.getString("aadhar_card_number").trim();

employeeDTO = new EmployeeDTO();
employeeDTO.setEmployeeId("A"+id);
employeeDTO.setName(name);
employeeDTO.setDesignationCode(designationCode);
employeeDTO.setTitle(title);
employeeDTO.setDateOfBirth(dateOfBirth);
employeeDTO.setGender(gender);
employeeDTO.setIsIndian(isIndian);
employeeDTO.setBasicSalary(basicSalary);
employeeDTO.setPanNumber(panNumber);
employeeDTO.setAadharCardNumber(aadharCardNumber);

resultSet.close();
preparedStatement.close();
connection.close();
}catch(Exception exception) {
throw new DAOException(exception.getMessage());
}
return employeeDTO;

}
}
