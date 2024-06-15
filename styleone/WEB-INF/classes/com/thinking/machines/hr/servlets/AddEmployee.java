 package com.thinking.machines.hr.servlets;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import com.thinking.machines.hr.dl.*;
import java.util.*;
import java.text.*;
import java.math.*;

public class AddEmployee extends HttpServlet {
public void doGet(HttpServletRequest request, HttpServletResponse response)
{
try
{
response.setContentType("text/html");
PrintWriter pw=response.getWriter();
SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
String name=request.getParameter("name");
int designationCode=Integer.parseInt(request.getParameter("designationCode"));
Date dateOfBirth=simpleDateFormat.parse(request.getParameter("dateOfBirth"));
String gender=request.getParameter("gender");
String isIndian=request.getParameter("isIndian");
if(isIndian==null)isIndian="N";
BigDecimal basicSalary=new BigDecimal(request.getParameter("basicSalary"));
String panNumber=request.getParameter("panNumber");
String aadharCardNumber=request.getParameter("aadharCardNumber");
EmployeeDAO employeeDAO=new EmployeeDAO();
DesignationDAO designationDAO=new DesignationDAO();
try
{
boolean designationCodeExists=designationDAO.designationCodeExists(designationCode);
boolean panNumberExists=employeeDAO.panNumberExists(panNumber);
boolean aadharCardNumberExists=employeeDAO.aadharCardNumberExists(aadharCardNumber);
if(designationCodeExists==false||panNumberExists==true||aadharCardNumberExists==true)
{
		
pw.println("<!DOCTYPE HTML>");
pw.println("<html lang='en'>");
pw.println("<head>");
pw.println("<title>HR Application</title>");
pw.println("<script>");
pw.println("function validateForm(frm) {");
pw.println("var valid = true;");
pw.println("var firstInvalidComponent = null;");
pw.println("var name = frm.name.value.trim();");
pw.println("var nameErrorSection = document.getElementById('nameErrorSection');");
pw.println("nameErrorSection.innerHTML = '';");
pw.println("if (name.length == 0) {");
pw.println("nameErrorSection.innerHTML = 'Name Required';");
pw.println("valid = false;");
pw.println("firstInvalidComponent = frm.name;");
pw.println("}");
pw.println("var designationCode = frm.designationCode.value;");
pw.println("var designationCodeErrorSection = document.getElementById('designationCodeErrorSection');");
pw.println("designationCodeErrorSection.innerHTML = '';");
pw.println("if (designationCode == -1) {");
pw.println("designationCodeErrorSection.innerHTML = 'Select designation';");
pw.println("valid = false;");
pw.println("if (firstInvalidComponent == null) firstInvalidComponent = frm.designationCode;");
pw.println("}");
pw.println("var dateOfBirth = frm.dateOfBirth.value;");
pw.println("var dateOfBirthErrorSection = document.getElementById('dateOfBirthErrorSection');");
pw.println("dateOfBirthErrorSection.innerHTML = '';");
pw.println("if (dateOfBirth.length == 0) {");
pw.println("dateOfBirthErrorSection.innerHTML = 'Select date Of Birth';");
pw.println("valid = false;");
pw.println("if (firstInvalidComponent == null) firstInvalidComponent = frm.dateOfBirth;");
pw.println("}");
pw.println("var genderErrorSection = document.getElementById('genderErrorSection');");
pw.println("genderErrorSection.innerHTML = '';");
pw.println("if (frm.gender[0].checked == false && frm.gender[1].checked == false) {");
pw.println("genderErrorSection.innerHTML = 'Select gender';");
pw.println("valid = false;");
pw.println("}");
pw.println("var basicSalary = frm.basicSalary.value.trim();");
pw.println("var basicSalaryErrorSection = document.getElementById('basicSalaryErrorSection');");
pw.println("basicSalaryErrorSection.innerHTML = '';");
pw.println("if (basicSalary.length == 0) {");
pw.println("basicSalaryErrorSection.innerHTML = 'basic salary required';");
pw.println("valid = false;");
pw.println("if (firstInvalidComponent == null) firstInvalidComponent = frm.basicSalary;");
pw.println("} else {");
pw.println("var v = '0123456789.';");
pw.println("var e = 0;");
pw.println("var isBasicSalaryValid = true;");
pw.println("while (e < basicSalary.length) {");
pw.println("if (v.indexOf(basicSalary.charAt(e)) == -1) {");
pw.println("basicSalaryErrorSection.innerHTML = 'Invalid salary';");
pw.println("valid = false;");
pw.println("if (firstInvalidComponent == null) firstInvalidComponent = frm.basicSalary;");
pw.println("isBasicSalaryValid = false;");
pw.println("break;");
pw.println("}");
pw.println("e++;");
pw.println("}");
pw.println("if (isBasicSalaryValid) {");
pw.println("var dot = basicSalary.indexOf('.');");
pw.println("if (dot != -1) {");
pw.println("var numberOfFractions = basicSalary.length - (dot + 1);");
pw.println("if (numberOfFractions > 2) {");
pw.println("valid = false;");
pw.println("if (firstInvalidComponent == null) firstInvalidComponent = frm.basicSalary;");
pw.println("basicSalaryErrorSection.innerHTML = 'Invalid salary';");
pw.println("}");
pw.println("}");
pw.println("}");
pw.println("}");
pw.println("var panNumber = frm.panNumber.value.trim();");
pw.println("var panNumberErrorSection = document.getElementById('panNumberErrorSection');");
pw.println("panNumberErrorSection.innerHTML = '';");
pw.println("if (panNumber.length == 0) {");
pw.println("panNumberErrorSection.innerHTML = 'Pan Number Required';");
pw.println("valid = false;");
pw.println("if (firstInvalidComponent == null) firstInvalidComponent = frm.panNumber;");
pw.println("}");
pw.println("var aadharCardNumber = frm.aadharCardNumber.value.trim();");
pw.println("var aadharCardNumberErrorSection = document.getElementById('aadharCardNumberErrorSection');");
pw.println("aadharCardNumberErrorSection.innerHTML = '';");
pw.println("if (aadharCardNumber.length == 0) {");
pw.println("aadharCardNumberErrorSection.innerHTML = 'Aadhar Card Number Required';");
pw.println("valid = false;");
pw.println("if (firstInvalidComponent == null) firstInvalidComponent = frm.aadharCardNumber;");
pw.println("}");
pw.println("if (!valid) firstInvalidComponent.focus();");
pw.println("return valid;");
pw.println("}");
pw.println("function cancelAddition() {");
pw.println("document.getElementById('cancelAdditionForm').submit();");
pw.println("}");
pw.println("</script>");
pw.println("</head>");
pw.println("<body>");
pw.println("<div style='width:90hw;height:auto;border:1px solid black'>");
pw.println("<div style='margin:5px;width:90hw;height:auto;border:1px solid black'>");
pw.println("<img src='/styleone/images/logo.png' style='float:left;padding:9px'>");
pw.println("<div style='margin-top:9px;margin-bottom:9px;font-size:20pt'>Thinking Machines</div>");
pw.println("</div>");
pw.println("<div style='width:90hw;height:70vh;margin:5px;border:1px solid white'>");
pw.println("<div style='height:60vh;margin:5px;float:left;padding:5px;border:1px solid black'>");
pw.println("<a href='/styleone/designationsView'>Designations</a><br>");
pw.println("<b>Employees</b><br>");
pw.println("<a href='/styleone/index.html'>Home</a>");
pw.println("</div>");
pw.println("<div style='height:60vh;margin-left:105px;margin-right:5px;margin-bottom:5px;margin-top:5px;padding:5px;border:1px solid black'>");
pw.println("<b>Employee(add Module)</b>");
pw.println("<form method='post' action='/styleone/addEmployee' onsubmit='return validateForm(this)'>");
pw.println("<table>");
pw.println("<tr>");
pw.println("<td>Name</td>");
pw.println("<td><input type='text' id='name' name='name' maxlength='50' size='51' value='"+name+"'>");
pw.println("<span id='nameErrorSection' style='color:red'></span><br></td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Designation</td>");
pw.println("<td><select id='designationCode' name='designationCode'>");
pw.println("<option value='-1'>&lt;select Designation&gt;</option>");


//DesignationDAO designationDAO;
//designationDAO=new DesignationDAO();
List<DesignationDTO>designations=designationDAO.getAll();
int code;
String title;
for(DesignationDTO designation:designations)
{	
code=designation.getCode();
title=designation.getTitle();
if(code!=designationCode)
{
pw.println("<option value='"+code+"'>"+title+"</option>");
}
else
{
pw.println("<option selected value='"+code+"'>"+title+"</option>");
}
}
pw.println("</select>");
if(designationCodeExists==false) 
{
pw.println("<span id='designationCodeErrorSection' style='color:red'>invalid Designation</span>");
}
else
{
pw.println("<span id='designationCodeErrorSection' style='color:red'></span>");
}
pw.println("</td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Date Of Birth</td>");
pw.println("<td><input type='date' id='dateOfBirth' name='dateOfBirth' value='"+simpleDateFormat.format(dateOfBirth)+"'>");
pw.println("<span id='dateOfBirthErrorSection' style='color:red'></span>");
pw.println("</td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Gender</td>");

pw.println("<td>");
if(gender.equals("M")==false)
{
pw.println("<input type='radio' id='male' name='gender' value='M'>Male");
}
else
{
pw.println("<input checked type='radio' id='male' name='gender' value='M'>Male");
}
pw.println("&nbsp;");
if(gender.equals("F")==false)
{
pw.println("<input type='radio' id='female' name='gender' value='F'>Female");
}
else
{
pw.println("<input checked type='radio' id='female' name='gender' value='F'>Female");
}
pw.println("<span id='genderErrorSection' style='color:red'></span></td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Indian ?</td>");
pw.println("<td>");	
if(isIndian.equals("Y"))
{
pw.println("<input checked type='checkbox' name='isIndian' id='isIndian' value='Y'></td>"); 
}
else
{
pw.println("<input type='checkbox' name='isIndian' id='isIndian' value='Y'></td>"); 
}
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Basic Salary</td>");
pw.println("<td><input type='text' style='text-align:right' name='basicSalary' id='basicSalary' maxLength='13' value='"+basicSalary.toPlainString()+"'>");
pw.println("<span id='basicSalaryErrorSection' style='color:red'></span></td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Pan Number</td>");
pw.println("<td><input type='text' id='panNumber' name='panNumber' maxLength='15' size='16' value='"+panNumber+"'>");
if(panNumberExists)
{
pw.println("<span id='panNumberErrorSection' style='color:red'>pan number exists</span>");
}
else
{
pw.println("<span id='panNumberErrorSection' style='color:red'></span>");
}
pw.println("</td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Aadhar Card Number</td>");
pw.println("<td><input type='text' id='aadharCardNumber' name='aadharCardNumber' maxLength='15' size='16' value='"+aadharCardNumber+"'>");
if(aadharCardNumberExists)
{
pw.println("<span id='aadharCardNumberErrorSection' style='color:red'>aadhar card number exists</span>");
}
else
{
pw.println("<span id='aadharCardNumberErrorSection' style='color:red'></span>");
}
pw.println("</td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td colspan='2'><button type='submit'>Add</button>");
pw.println("&nbsp;&nbsp;<button type='Button' onclick='cancelAddition()'>Cancel</button></td>");
pw.println("</tr>");
pw.println("</table>");
pw.println("</form>");
pw.println("</div>");
pw.println("</div>");
pw.println("<div style='width:90hw;height:auto;margin:5px;text-align:center;border:1px solid white'>");
pw.println("&copy; Thinking Machines 2020");
pw.println("</div>");
pw.println("</div>");
pw.println("<form id='cancelAdditionForm' action='/styleone/employeesView'></form>");
pw.println("</body>");
pw.println("</html>");
return;
}
}catch(DAOException daoException)
{
System.out.println(daoException);
}
EmployeeDTO employee=new EmployeeDTO();
employee.setName(name);
employee.setDesignationCode(designationCode);
employee.setDateOfBirth(dateOfBirth);
employee.setGender(gender);
employee.setIsIndian(isIndian.equals("Y"));
employee.setBasicSalary(basicSalary);
employee.setPanNumber(panNumber);
employee.setAadharCardNumber(aadharCardNumber);
//EmployeeDAO employeeDAO=new EmployeeDAO();
try
{
employeeDAO.add(employee);
pw.println("<!DOCTYPE HTML>");
pw.println("<html lang='en'>");
pw.println("<head>");
pw.println("<title>HR Application</title>");
pw.println("</head>");
pw.println("<body>");
pw.println("<!-- Main container starts here -->");
pw.println("<div style='width:90hw;height:auto;border:1px solid black'>");       
pw.println("<!-- header starts here -->");
pw.println("<div style='margin:5px;width:90hw;height:auto;border:1px solid black'>");
pw.println("<img src='/styleone/images/logo.png' style='float:left;padding:9px'>");
pw.println("<div style='margin-top:9px;margin-bottom:9px;font-size:20pt'>Thinking Machines</div>");
pw.println("</div>");
pw.println("<!-- header ends here -->");
pw.println("<!-- content starts here -->");
pw.println("<div style='width:90hw;height:70vh;margin:5px;border:1px solid white'>");
pw.println("<!-- left panel starts here -->");
pw.println("<div style='height:60vh;margin:5px;float:left;padding:5px;border:1px solid black'>");
pw.println("<a href='/styleone/designationsView'>Designations</a><br>");
pw.println("<a href='/styleone/employeesView'>Employees</a>");
pw.println("</div>");
pw.println("<!-- left panel ends here -->");
pw.println("<!-- right panel starts here -->");
pw.println("<div style='height:60vh;margin-left:105px;margin-right:5px;margin-bottom:5px;margin-top:5px;padding:5px;border:1px solid black'>");
pw.println("<h3>Notification</h3>");
pw.println("Employee added<br>Add more employees?<br>");

pw.println("<table>");
pw.println("<tr>");
pw.println("<td>");

pw.println("<form action='/styleone/getEmployeeAddForm'>");
pw.println("<button type='submit'>Yes</button>");
pw.println("</form>");

pw.println("</td>");
pw.println("<td>");
pw.println("<form action='/styleone/employeesView'>");
pw.println("<button type='submit'>No</button>");
pw.println("</form>");
pw.println("</td>");
pw.println("</tr>");
pw.println("</table>");
pw.println("</div>");
pw.println("<!-- right panel ends here -->");
pw.println("</div>");
pw.println("<!-- content ends here -->");
pw.println("<!-- footer starts here -->");
pw.println("<div style='width:90hw;height:auto;margin:5px;text-align:center;border:1px solid white'>");
pw.println("&copy; Thinking Machines 2020");
pw.println("</div>");
pw.println("<!-- footer ends here -->");
pw.println("</div>");
pw.println("<!-- Main container ends here -->");
pw.println("</body>");
pw.println("</html>");
}catch(DAOException daoException)
{
System.out.println("error:"+daoException.getMessage());
}
}catch(Exception exception)
{
System.out.println("error:"+exception.getMessage());
}
}
public void doPost(HttpServletRequest request, HttpServletResponse response)
{
doGet(request,response);
}
}
