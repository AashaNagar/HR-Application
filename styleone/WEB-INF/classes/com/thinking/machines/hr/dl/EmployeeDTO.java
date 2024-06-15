package com.thinking.machines.hr.dl;
import java.util.*;
import java.math.*;

public class EmployeeDTO implements java.io.Serializable, Comparable<EmployeeDTO> {
private String employeeId;
private String name;
private int designationCode;
private String designation;
private String title;
private java.util.Date dateOfBirth;
private String gender;
private boolean isIndian;
private BigDecimal basicSalary;
private String panNumber;
private String aadharCardNumber;

// Constructor
public EmployeeDTO() {
this.employeeId = "";
this.name = "";
this.designationCode = 0;
this.designation = "";
this.title = "";
this.dateOfBirth = null;
this.gender = "";
this.isIndian = false;
this.basicSalary = null;
this.panNumber = "";
this.aadharCardNumber = "";
}

// Setter methods
public void setEmployeeId(String employeeId) {
this.employeeId = employeeId;
}

public void setName(String name) {
this.name = name;
}

public void setDesignationCode(int designationCode) {
this.designationCode = designationCode;
}

public void setDesignation(String designation) {
this.designation = designation;
}

public void setTitle(String title) {
this.title = title;
}

public void setDateOfBirth(Date dateOfBirth) {
this.dateOfBirth = dateOfBirth;
}

public void setGender(String gender) {
this.gender = gender;
}

public void setIsIndian(boolean isIndian) {
this.isIndian = isIndian;
}

public void setBasicSalary(BigDecimal basicSalary) {
this.basicSalary = basicSalary;
}

public void setPanNumber(String panNumber) {
this.panNumber = panNumber;
}

public void setAadharCardNumber(String aadharCardNumber) {
this.aadharCardNumber = aadharCardNumber;
}

// Getter methods
public String getEmployeeId() {
return employeeId;
}

public String getName() {
return name;
}

public int getDesignationCode() {
return designationCode;
}

public String getDesignation() {
return designation;
}

public String getTitle() {
return title;
}

public Date getDateOfBirth() {
return dateOfBirth;
}

public String getGender() {
return gender;
}

public boolean getIsIndian() {
return isIndian;
}

public BigDecimal getBasicSalary() {
return basicSalary;
}

public String getPanNumber() {
return panNumber;
}

public String getAadharCardNumber() {
return aadharCardNumber;
}

// Overriding hashCode, equals, and compareTo methods
public int hashCode() {
return employeeId.hashCode();
}

public boolean equals(Object object) {
if (!(object instanceof EmployeeDTO)) return false;
EmployeeDTO employee = (EmployeeDTO) object;
return this.employeeId.equalsIgnoreCase(employee.employeeId);
}

public int compareTo(EmployeeDTO employee) {
return this.employeeId.compareTo(employee.employeeId);
}
}
