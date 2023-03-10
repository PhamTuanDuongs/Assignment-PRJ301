<%-- 
    Document   : viewAttendance
    Created on : Mar 7, 2023, 4:13:59 PM
    Author     : duong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ViewAttendance</title>
        <style>
            table{
                border: 1px solid black;
                width: 70rem;
                height: 30rem;
            }
            thead{
                background-color: #655DBB;
            }
            thead,tbody,tr,td{
                border: 1px solid black;
            }
            .timetable{
                width: 70rem;
                margin-left:300px;
                border: 1px solid red;
            }

            .title{
                padding-left: 19rem;
                height: 100px;
                display:flex;
                align-items: center;
            }
            .titel2{
                padding-left: 19rem;
                height: 100px;
                display: flex;
                align-items: center;
            }
        </style> 
    </head>
    <body>
        <%@ include file = "sideBar.jsp" %>
        <div class="title"><h1>FPT University Academic Portal</h1></div>  
        <div  class="titel2">
            <h2>ViewAttendance</h1>
        </div>
        <div class="timetable">
            <table>
                <thead>
                <th>NO</th>
                <th>Group</th>
                <th>Name</th>
                <th>Rollnumber</th>
                <th>Absent</th>
                <th>Present</th>
                <th>Comment</th>
                <!--<th>Image</th>-->
                </thead>
                <tbody>
                    <c:set var="index" value="0"/>
                    <c:forEach items="${requestScope.liststudent}" var="l">
                        <tr>
                            <c:set var="index" value="${index+1}"/>
                            <td>${index}</td>
                            <td>${l.groupName}</td>
                            <td>${l.lastName}${' '}${l.firstName}</td>
                            <td>${l.rollnumber}</td>
                            <td><input readonly="" ${l.status eq "absent" ? 'checked':'disabled'}  type="radio" value="absent" >absent</td>
                            <td><input readonly="" ${l.status eq "attended" ? 'checked':'disabled'}  type="radio"  value="present">present</td>
                            <td><input style="border: 1px solid black; height: 100%; width: 98%" type="text" readonly="" value="${l.comment}"> </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
