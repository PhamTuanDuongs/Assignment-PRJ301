/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.instructor;

import controller.authentication.BaseRequiredAuthenticatedControllerForInstructor;
import dal.takeAttendanceDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import model.Attendance;
import model.User;

/**
 *
 * @author duong
 */
public class listStudentForTakeAttendanceController extends BaseRequiredAuthenticatedControllerForInstructor {

    protected void processrequest(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        String raw_instructor = request.getParameter("instructor");
        String raw_groupid = request.getParameter("groupid");
        String raw_slot = request.getParameter("slot");
        String raw_sessionid = request.getParameter("sessionid");
        try {
            int groupid = Integer.parseInt(raw_groupid);
            int instructor = Integer.parseInt(raw_instructor);
            int slot = Integer.parseInt(raw_slot);
            int lectureid = Integer.parseInt(raw_sessionid);
            LocalDate currentdate = LocalDate.now();
            takeAttendanceDBContext t = new takeAttendanceDBContext();
            ArrayList<Attendance> list = t.allStudentsBySlotGroupId(Date.valueOf("2023-03-20"), groupid, instructor, slot, lectureid);
            request.setAttribute("list", list);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        request.getRequestDispatcher("view/Instructor/takeAttendance.jsp").forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        processrequest(request, response, user);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        processrequest(request, response, user);
    }

}
