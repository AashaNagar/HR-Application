package com.thinking.machines.hr.servlets;

import com.thinking.machines.hr.dl.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class UpdateDesignation extends HttpServlet {
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
PrintWriter pw = null;
String title = "";
int code = 0;
try {
pw = response.getWriter();
response.setContentType("text/html");           
title = request.getParameter("title");
if (title == null || title.trim().isEmpty()) {
sendBackView(response);
return;
}
try {
code = Integer.parseInt(request.getParameter("code"));
} catch (NumberFormatException nfe) {
sendBackView(response);
return;
}
DesignationDTO designation = new DesignationDTO();
designation.setCode(code);
designation.setTitle(title);
DesignationDAO designationDAO = new DesignationDAO();
designationDAO.update(designation);
// Generate success response
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
pw.println("Designation updated<br><br>");
pw.println("<form action='/styleone/designationsView'>");
pw.println("<button type='submit'>Ok </button>");
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
pw.println("</body>");
pw.println("</html>");
}catch (DAOException daoException) {
// Generate error response
pw.println("<!DOCTYPE HTML>");
pw.println("<html lang='en'>");
pw.println("<head>");
pw.println("<title>HR Application</title>");
pw.println("<script>");
pw.println("function validateForm(frm) {");
pw.println("var title = frm.title.value.trim();");
pw.println("var titleErrorSection = document.getElementById('titleErrorSection');");
pw.println("titleErrorSection.innerHTML = '';");
pw.println("if (title.length == 0) {");
pw.println("titleErrorSection.innerHTML = 'Required';");
pw.println("frm.title.focus();");
pw.println("return false;");
pw.println("}");
pw.println("return true;");
pw.println("}");
pw.println("function cancelEditing() {");
pw.println("document.getElementById('cancelEditingForm').submit();");
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
pw.println("<b>Designations</b><br>");
pw.println("<a href='/styleone/employeesView'>Employees</a><br><br>");
pw.println("<a href='/styleone/index.html'>Home</a>");
pw.println("</div>");
pw.println("<!-- left panel ends here -->");
pw.println("<!-- right panel starts here -->");
pw.println("<div style='height:60vh;margin-left:105px;margin-right:5px;margin-bottom:5px;margin-top:5px;padding:5px;border:1px solid black'>");
pw.println("<h2>Designation(Edit Module)</h2>");
pw.println("<div style='color:red'>" + daoException.getMessage() + "</div>");
pw.println("<form method='post' action='/styleone/updateDesignation' onsubmit='return validateForm(this)'>");
pw.println("Designation");
pw.println("<input type='hidden' id='code' name='code' value='" + code + "'>");
pw.println("<input type='text' id='title' name='title' maxlength='35' size='36' value='" + title + "'>");
pw.println("<span id='titleErrorSection' style='color:red'></span><br>");
pw.println("<button type='submit'>update</button>");
pw.println("<button type='button' onclick='cancelEditing()'>cancel</button>");
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
pw.println("<form id='cancelEditingForm' action='/styleone/designationsView'>");
pw.println("</form>");
pw.println("</body>");
pw.println("</html>");

} catch (Exception exception) {
System.out.println(exception);
} 
}

public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doGet(request, response);
}

private void sendBackView(HttpServletResponse response) {
PrintWriter pw = null;
try {
pw = response.getWriter();
response.setContentType("text/html");

DesignationDAO designationDAO = new DesignationDAO();
List<DesignationDTO> designations = designationDAO.getAll();

pw.println("<!DOCTYPE HTML>");
pw.println("<html lang='en'>");
pw.println("<head>");
pw.println("<title>HR Application</title>");
pw.println("</head>");
pw.println("<body>");
pw.println("<!-- Main container starts here -->");
pw.println("<div style='width:90hw;height:auto;border:1px solid black'> ");
pw.println("<!-- header starts here -->");
pw.println("<div style='margin:5px;width:90hw;height:auto;border:1px solid black'>");
pw.println("<a href='/styleone/index.html'><img src='/styleone/images/logo.png' style='float:left;padding:9px'></a><div style='margin-top:9px;margin-bottom:9px;font-size:20pt'>Thinking Machines</div>");
pw.println("</div>");
pw.println("<!-- header ends here -->");
pw.println("<!-- content starts here -->");
pw.println("<div style='width:90hw;height:70vh;margin:5px;border:1px solid white'>");
pw.println("<!-- left panel starts here -->");
pw.println("<div style='height:60vh;margin:5px;float:left;padding:5px;border:1px solid black'>");
pw.println("<a href='/styleone/designationsView'>Designations</a><br>");
pw.println("<a href='/styleone/employeesView'>Employees</a>");
pw.println("<a href='/styleone/index.html'>Home</a>");
pw.println("</div>");
pw.println("<!-- left panel ends here -->");
pw.println("<!-- right panel starts here -->");
pw.println("<div style='height:60vh;margin-left:105px;margin-right:5px;margin-bottom:5px;margin-top:5px;padding:5px;overflow:scroll;border:1px solid black'>");
pw.println("<h2>Designations</h2>");
pw.println("<table border='1'>");
pw.println("<thead>");
pw.println("<tr>");
pw.println("<th colspan='4' style='text-align:right;padding:5px'>");
pw.println("<a href='/styleone/AddDesignation.html'>Add new designation </a>");
pw.println("</th>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<th style='width:60px;text-align:center'>S.No.</th>");
pw.println("<th style='Width:200px;text-align:center'>Designations</th>");
pw.println("<th style='width:100px;text-align:center'>Edit</th>");
pw.println("<th style='width:100px;text-align:center'>Delete</th>");
pw.println("</tr>");
pw.println("</thead>");
pw.println("<tbody>");

int sno = 0;
for (DesignationDTO designationDTO : designations) {
sno++;
int code = designationDTO.getCode();
String title = designationDTO.getTitle();
pw.println("<tr>");
pw.println("<td style='text-align:right'>" + sno + ".</td>");
pw.println("<td>" + title + "</td>");
pw.println("<td style='text-align:center'><a href='/styleone/editDesignation?code=" + code + "'>Edit</a></td>");
pw.println("<td style='text-align:center'><a href='/styleone/confirmDeleteDesignation?code=" + code + "'>Delete</a></td>");
pw.println("</tr>");
}
pw.println("</tbody>");
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

} catch (DAOException daoException) {
System.out.println(daoException.getMessage());
} catch (Exception exception) {
System.out.println(exception.getMessage());
} finally {
if (pw != null) pw.close();
}
}
}
           