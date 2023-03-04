/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.report;

import controller.authentication.BaseRequiredAuthenticatedControllerForStudent;
import dal.CourseDBContext;
import dal.reportAttendanceForStudentsDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Course;
import model.User;
import model.reportAttendanceForStudents;

/**
 *
 * @author duong
 */
public class attendancReportForStudentsController extends BaseRequiredAuthenticatedControllerForStudent {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User u = (User) request.getSession().getAttribute("user");
        CourseDBContext c = new CourseDBContext();
        User user = (User)request.getSession().getAttribute("user");
        ArrayList<Course> course = c.allCourseByStudentId(user.getStudentId());
        request.setAttribute("course", course);

        String raw_std = request.getParameter("studenId");
        String raw_course = request.getParameter("courseId");
        if (raw_std != null && raw_course != null) {
            reportAttendanceForStudentsDBContext r = new reportAttendanceForStudentsDBContext();
            ArrayList<reportAttendanceForStudents> attendance = r.allAttendanceByStidCoid(Integer.parseInt(raw_std), Integer.parseInt(raw_course));
            request.setAttribute("courseid", Integer.parseInt(raw_course));
            request.setAttribute("attendance", attendance);
        }else{
            reportAttendanceForStudentsDBContext r = new reportAttendanceForStudentsDBContext();
            ArrayList<reportAttendanceForStudents> attendance = r.allAttendanceByStidCoid(user.getStudentId(), course.get(0).getCourseId());
            request.setAttribute("attendance", attendance);
        }
        request.getRequestDispatcher("view/student/attendancereport.jsp").forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        processRequest(request, response);
    }

}
