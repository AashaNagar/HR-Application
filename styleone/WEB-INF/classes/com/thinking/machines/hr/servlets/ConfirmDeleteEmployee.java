package com.thinking.machines.hr.servlets;
import com.thinking.machines.hr.dl.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.text.*;

public class ConfirmDeleteEmployee extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
try
{
String employeeId;
employeeId=request.getParameter("employeeId");


EmployeeDAO employeeDAO=new EmployeeDAO();
try
{
EmployeeDTO employeeDTO=employeeDAO.getByEmployeeId(employeeId);


PrintWriter pw=response.getWriter();
response.setContentType("text/html");

pw.println("<!DOCTYPE HTML>");
pw.println("<html lang='en'>");
pw.println("<head>");
pw.println("<title>HR Application</title>");
pw.println("<script>");
pw.println("function cancelDeletion()");
pw.println("{");
pw.println("document.getElementById('cancelDeletionForm').submit();");
pw.println("}");
pw.println("</script>");
pw.println("</head>");
pw.println("<body>");
pw.println("<!-- Main container starts here -->");
pw.println("<div style='width:90hw;height:auto;border:1px solid black'> ");
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


pw.println("<b>Employees</b	><br><br>");
pw.println("<a href='/styleone/index.html'>Home</a>");
pw.println("</div>");
pw.println("<!-- left panel ends here -->");
pw.println("<!-- right panel starts here -->");
pw.println("<div style='height:60vh;margin-left:105px;margin-right:5px;margin-bottom:5px;margin-top:5px;padding:5px;border:1px solid black'>");
pw.println("<h2>Employee(Delete Module)</h2>");
pw.println("<form method='post' action='/styleone/deleteEmployee'>");
pw.println("<input type='hidden' id='employeeId' name='employeeId' value='"+employeeId+"'>");


pw.println("Name:");
pw.println("<b>"+employeeDTO.getName()+"</b><br>");

pw.println("Designation:");
pw.println("<b>"+employeeDTO.getTitle()+"</b><br>");


SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
String stringDateOfBirth=simpleDateFormat.format(employeeDTO.getDateOfBirth());

pw.println("Date Of Birth:");
pw.println("<b>"+stringDateOfBirth+"</b><br>");



pw.println("Gender:");
if(employeeDTO.getGender().equals("M"))
{
pw.println("<b>Male</b><br><br>");
}
else
{
pw.println("<b>Female</b><br><br>");
}




pw.println("Nationality:");
if(employeeDTO.getIsIndian())
{
pw.println("<b>Indian</b><br>");
}
else
{
pw.println("<b>Not an Indian</b><br>");
}


pw.println("Basic Salary:");
pw.println("<b>"+employeeDTO.getBasicSalary().toPlainString()+"</b><br>");


pw.println("Pan Number:");
pw.println("<b>"+employeeDTO.getPanNumber()+"</b><br>");



pw.println("Aadhar Card Number:");
pw.println("<b>"+employeeDTO.getAadharCardNumber()+"</b><br>");








pw.println("Are you sure you want to delete this employee ?<br>");


pw.println("<button type='submit'>Yes</button>");
pw.println("<button type='button' onclick='cancelDeletion()'>No</button>");
pw.println("</form>");
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
pw.println("<form id='cancelDeletionForm' action='/styleone/employeesView'>");
pw.println("</form>");
pw.println("</body>");
pw.println("</html>");
}catch(DAOException daoException)
{
sendBackView(response);
}
}catch(Exception exception)
{
System.out.println(exception.getMessage());//remove after testing
}
}

public void doPost(HttpServletRequest request,HttpServletResponse response)
{
doGet(request,response);
}

private void sendBackView(HttpServletResponse response)
{
try
{
List<EmployeeDTO>employees=new EmployeeDAO().getAll();
response.setContentType("text/html");
PrintWriter pw = response.getWriter();
pw.println("<!DOCTYPE HTML>");
pw.println("<html lang='en'>");
pw.println("<head>");
pw.println("<title>HR Application</title>");
pw.println("<script>");
pw.println("function Employee() {");
pw.println("this.employeeId = '';");
pw.println("this.name = '';");
pw.println("this.designationCode = 0;");
pw.println("this.designation = '';");
pw.println("this.dateOfBirth = '';");
pw.println("this.gender = '';");
pw.println("this.isIndian = true;");
pw.println("this.basicSalary = 0;");
pw.println("this.panNumber = '';");
pw.println("this.aadharCardNumber = '';");
pw.println("}");
pw.println("var selectedRow = null;");
pw.println("var employees = [];");
SimpleDateFormat simpleDateFormat;
simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
int i=0;
for(EmployeeDTO employee:employees)
{
pw.println("var employee = new Employee();");
pw.println("employee.employeeId = '"+employee.getEmployeeId()+"';");
pw.println("employee.name = '"+employee.getName()+"';");
pw.println("employee.designationCode = "+employee.getDesignationCode()+";");
pw.println("employee.designation = '"+employee.getTitle()+"';");
pw.println("employee.dateOfBirth = '"+simpleDateFormat.format(employee.getDateOfBirth())+"';");
pw.println("employee.gender = '"+employee.getGender()+"';");
pw.println("employee.isIndian ="+employee.getIsIndian()+";");
pw.println("employee.basicSalary = "+employee.getBasicSalary().toPlainString()+";");
pw.println("employee.panNumber = '"+employee.getPanNumber()+"';");
pw.println("employee.aadharCardNumber = '"+employee.getAadharCardNumber()+"';");
pw.println("employees["+i+"] = employee;");
i++;
}
pw.println("function selectEmployee(row, employeeId) {");
pw.println("if (row == selectedRow) return;");
pw.println("if (selectedRow != null) {");
pw.println("selectedRow.style.background = 'white';");
pw.println("selectedRow.style.color = 'black';");
pw.println("}");
pw.println("row.style.background = '#7C7B7B';");
pw.println("row.style.color = 'white';");
pw.println("selectedRow = row;");
pw.println("var i;");
pw.println("for (i = 0; i < employees.length; i++) {");
pw.println("if (employees[i].employeeId == employeeId) {");
pw.println("break;");
pw.println("}");
pw.println("}");
pw.println("var emp = employees[i];");
pw.println("document.getElementById('detailPanel_employeeId').innerHTML = emp.employeeId;");
pw.println("document.getElementById('detailPanel_name').innerHTML = emp.name;");
pw.println("document.getElementById('detailPanel_designation').innerHTML = emp.designation;");
pw.println("document.getElementById('detailPanel_dateOfBirth').innerHTML = emp.dateOfBirth;");
pw.println("document.getElementById('detailPanel_gender').innerHTML = emp.gender;");
pw.println("if (emp.isIndian) {");
pw.println("document.getElementById('detailPanel_isIndian').innerHTML = 'YES';");
pw.println("} else {");
pw.println("document.getElementById('detailPanel_isIndian').innerHTML = 'NO';");
pw.println("}");
pw.println("document.getElementById('detailPanel_basicSalary').innerHTML = emp.basicSalary;");
pw.println("document.getElementById('detailPanel_panNumber').innerHTML = emp.panNumber;");
pw.println("document.getElementById('detailPanel_aadharCardNumber').innerHTML = emp.aadharCardNumber;");
pw.println("}");
pw.println("</script>");
pw.println("</head>");
pw.println("<body>");
pw.println("<!-- Main container starts here -->");
pw.println("<div style='width:90hw;height:auto;border:1px solid black'>");
pw.println("<!-- header starts here -->");
pw.println("<div style='margin:5px;width:90hw;height:auto;border:1px solid black'>");
pw.println("<a href='/styleone/index.html'><img src='/styleone/images/logo.png' style='float:left;padding:9px'></a>");
pw.println("<div style='margin-top:9px;margin-bottom:9px;font-size:20pt'>Thinking Machines</div>");
pw.println("</div>");
pw.println("<!-- header ends here -->");
pw.println("<!-- content starts here -->");
pw.println("<div style='width:90hw;height:70vh;margin:5px;border:1px solid white'>");
pw.println("<!-- left panel starts here -->");
pw.println("<div style='height:65vh;margin:5px;float:left;padding:5px;border:1px solid black'>");
pw.println("<a href='/styleone/designationsView'>Designations</a><br>");
pw.println("<b>Employees</b><br><br>");
pw.println("<a href='/styleone/index.html'>Home</a>");
pw.println("</div>");
pw.println("<!-- left panel ends here -->");
pw.println("<!-- right panel starts here -->");
pw.println("<div style='height:65vh;margin-left:105px;margin-right:5px;margin-bottom:5px;margin-top:5px;padding:5px;border:1px solid black'>");
pw.println("<h4>Employees</h4>");
pw.println("<div style='height:30vh;margin-left:5px;margin-right:5px;margin-bottom:5px;margin-top:5px;padding:5px;border:1px solid black;overflow:scroll'>");
pw.println("<table border='1'>");
pw.println("<thead>");
pw.println("<tr>");
pw.println("<th colspan='6' style='text-align:right;padding:5px'>");
pw.println("<a href='/styleone/getEmployeeAddForm '>Add Employee </a>");
pw.println("</th>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<th style='width:80px;text-align:center'>S.No.</th>");
pw.println("<th style='width:200px;text-align:center'>Id.</th>");
pw.println("<th style='width:230px;text-align:center'>Name</th>");
pw.println("<th style='Width:200px;text-align:center'>Designations</th>");
pw.println("<th style='width:120px;text-align:center'>Edit</th>");
pw.println("<th style='width:120px;text-align:center'>Delete</th>");
pw.println("</tr>");
pw.println("</thead>");
pw.println("<tbody>");
int sno=0;
for(EmployeeDTO employee:employees)
{
sno++;
pw.println("<tr style='cursor:pointer' onclick='selectEmployee(this,\""+employee.getEmployeeId()+"\")'>");
pw.println("<td style='text-align:right'>"+sno+".</td>");
pw.println("<td>"+employee.getEmployeeId()+"</td>");
pw.println("<td>"+employee.getName()+"</td>");
pw.println("<td>"+employee.getTitle()+"</td>");
pw.println("<td style='text-align:center'><a href='/styleone/editEmployee?employeeId="+employee.getEmployeeId()+"'>Edit</a></td>");
pw.println("<td style='text-align:center'><a href='/styleone/confirmDeleteEmployee?employeeId="+employee.getEmployeeId()+"'>Delete</a></td>");
pw.println("</tr>");
} 
pw.println("</tbody>");
pw.println("</table>");
pw.println("</div>");
pw.println("<div style='height:18vh;margin-left:5px;margin-right:5px;margin-bottom:5px;margin-top:5px;padding:5px;border:1px solid black'>");
pw.println("<label style='background:grey;color:white;padding-left:5px;padding-right:5px'>Details</label>");
pw.println("<table border='0' width='100%'>");
pw.println("<tr>");
pw.println("<td>EmployeeId:<span id='detailPanel_employeeId' style='margin-right:30px'></span></td>");
pw.println("<td>Name:<span id='detailPanel_name' style='margin-right:30px'></span></td>");
pw.println("<td>Designation:<span id='detailPanel_designation' style='margin-right:30px'></span></td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Date Of Birth:<span id='detailPanel_dateOfBirth' style='margin-right:30px'></span></td>");
pw.println("<td>Gender:<span id='detailPanel_gender' style='margin-right:30px'></span></td>");
pw.println("<td>is Indian:<span id='detailPanel_isIndian' style='margin-right:30px'></span></td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Basic Salary:<span id='detailPanel_basicSalary' style='margin-right:30px'></span></td>");
pw.println("<td>PAN Number:<span id='detailPanel_panNumber' style='margin-right:30px'></span></td>");
pw.println("<td>Aadhar Card Number:<span id='detailPanel_aadharCardNumber' style='margin-right:30px'></span></td>");
pw.println("</tr>");
pw.println("</table>");
pw.println("</div>");
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
pw.close();
}catch(Exception exception)
{
System.out.println(exception.getMessage());
}

}
}	 
