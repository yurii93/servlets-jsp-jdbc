package com.luv2code.web.jdbc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDbUtil {
    private DataSource dataSource;

    public StudentDbUtil(DataSource theDataSource) {
        this.dataSource = theDataSource;
    }

    public List<Student> getStudents() throws Exception {
        List<Student> students = new ArrayList<>();

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myConn = dataSource.getConnection();
            String sql = "SELECT * FROM student ORDER BY last_name";
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery(sql);

            while (myRs.next()) {
                int id = myRs.getInt("id");
                String firstName = myRs.getString("first_name");
                String lastName = myRs.getString("last_name");
                String email = myRs.getString("email");

                Student tempStudent = new Student(id, firstName, lastName, email);
                students.add(tempStudent);
            }

            return students;
        } finally {
            close(myConn, myStmt, myRs);
        }

    }

    public void addStudent(Student theStudent) throws Exception {
        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            String sql = "INSERT INTO student (first_name, last_name, email) value(?, ?, ?)";

            myConn = dataSource.getConnection();
            myStmt = myConn.prepareStatement(sql);

            myStmt.setString(1, theStudent.getFirstName());
            myStmt.setString(2, theStudent.getLastName());
            myStmt.setString(3, theStudent.getEmail());

            myStmt.execute();
        } finally {
            close(myConn, myStmt, null);
        }
    }

    private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
        try {
            if (myRs != null) {
                myRs.close();
            }

            if (myStmt != null) {
                myStmt.close();
            }

            if (myConn != null) {
                myConn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Student getStudent(String theStudentId) throws Exception {
        Student theStudent = null;

        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            int studentId = Integer.parseInt(theStudentId);

            String sql = "SELECT * FROM student WHERE id = ?";
            myConn = dataSource.getConnection();
            myStmt = myConn.prepareStatement(sql);
            myStmt.setInt(1, studentId);
            myRs = myStmt.executeQuery();

            if (myRs.next()) {
                String firstName = myRs.getString("first_name");
                String lastName = myRs.getString("last_name");
                String email = myRs.getString("email");

                theStudent = new Student(studentId, firstName, lastName, email);
            } else {
                throw new Exception("Could not find student id: " + studentId);
            }

            return theStudent;

        } finally {
            close(myConn, myStmt, myRs);
        }
    }

    public void updateStudent(Student theStudent) throws Exception {
        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            String sql = "UPDATE student SET first_name = ?, last_name = ?, email = ? WHERE id = ?";

            myConn = dataSource.getConnection();
            myStmt = myConn.prepareStatement(sql);

            myStmt.setString(1, theStudent.getFirstName());
            myStmt.setString(2, theStudent.getLastName());
            myStmt.setString(3, theStudent.getEmail());
            myStmt.setInt(4, theStudent.getId());

            myStmt.execute();
        } finally {
            close(myConn, myStmt, null);
        }
    }

    public void deleteStudent(String theStudentId) throws Exception {
        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            int studentId = Integer.parseInt(theStudentId);

            String sql = "DELETE FROM student WHERE id = ?";
            myConn = dataSource.getConnection();
            myStmt = myConn.prepareStatement(sql);
            myStmt.setInt(1, studentId);
            myStmt.execute();
        } finally {
            close(myConn, myStmt, null);
        }
    }
}
