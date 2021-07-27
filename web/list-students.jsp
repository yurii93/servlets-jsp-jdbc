<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="css/style.css">
    <title>Students Tracker App</title>
</head>
<body>
<div id="wrapper">
    <div id="header">
        <h2>FooBarUniversity</h2>
    </div>

    <div id="container">
        <div id="content">


            <input type="button" value="Add Student"
                   onclick="window.location.href='add-student-form.jsp'; return false;"
                   class="add-student-button">

            <table>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Action</th>
                </tr>
                <c:forEach var="tempStudent" items="${STUDENT_LIST}">
                    <c:url var="updateLink" value="StudentControllerServlet">
                        <c:param name="command" value="LOAD"/>
                        <c:param name="studentId" value="${tempStudent.id}"/>
                    </c:url>
                    <c:url var="deleteLink" value="StudentControllerServlet">
                        <c:param name="command" value="DELETE"/>
                        <c:param name="studentId" value="${tempStudent.id}"/>
                    </c:url>
                    <tr>
                        <td>${tempStudent.firstName}</td>
                        <td>${tempStudent.lastName}</td>
                        <td>${tempStudent.email}</td>
                        <td>
                            <a href="${updateLink}">Update</a>
                            |
                            <a href="${deleteLink}"
                               onclick="if(!confirm('Are you sure you want to delete this student?')) return false;">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
</body>
</html>