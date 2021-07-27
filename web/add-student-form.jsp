<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/add-student-style.css">
    <title>Add Student</title>
</head>
<body>

<div id="wrapper">
    <div id="header">
        <h2>FooBarUniversity</h2>
    </div>

    <div id="container">
        <h3>Add Student</h3>

        <form action="StudentControllerServlet" method="GET">
            <input type="hidden" name="command" value="ADD">

            <table>
                <tbody>
                <tr>
                    <td><label>First name:</label></td>
                    <td><input type="text" name="firstName"></td>
                </tr>
                <tr>
                    <td><label>Last name:</label></td>
                    <td><input type="text" name="lastName"></td>
                </tr>
                <tr>
                    <td><label>Email:</label></td>
                    <td><input type="text" name="email"></td>
                </tr>
                <tr>
                    <td><label></label></td>
                    <td><input type="submit" value="Save" class="save"></td>
                </tr>
                </tbody>
            </table>
        </form>

        <div style="clear: both;"></div>

        <p>
            <a href="StudentControllerServlet">Back to List</a>
        </p>

    </div>
</div>
</body>
</html>
