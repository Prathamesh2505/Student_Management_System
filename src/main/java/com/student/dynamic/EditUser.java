package com.student.dynamic;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import studentDTO.Student;
import studentDAO.StudentDAO;
import studentDAO.StudentDAOImp;

@WebServlet("/editUser")
public class EditUser extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        StudentDAO dao = new StudentDAOImp();
        Student s = dao.getStudentById(id);

        req.setAttribute("student", s);
        RequestDispatcher rd = req.getRequestDispatcher("editUser.jsp");
        rd.forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        long phone = Long.parseLong(req.getParameter("phone"));
        String email = req.getParameter("email");
        String branch = req.getParameter("branch");
        String loc = req.getParameter("loc");

        Student s = new Student();
        s.setId(id);
        s.setName(name);
        s.setPhone(phone);
        s.setEmail(email);
        s.setBranch(branch);
        s.setLoc(loc);

        StudentDAO dao = new StudentDAOImp();
        boolean updated = dao.updateStudentData(s);

        if (updated) {
            req.setAttribute("success", "User updated successfully!");
        } else {
            req.setAttribute("error", "Failed to update user.");
        }

        RequestDispatcher rd = req.getRequestDispatcher("viewUser.jsp");
        rd.forward(req, resp);
    }
}
