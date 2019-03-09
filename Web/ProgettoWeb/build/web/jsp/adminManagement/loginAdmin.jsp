<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="bootstrap-4.3.1-dist/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="bootstrap-4.3.1-dist/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="bootstrap-4.3.1-dist/css/bootstrap-grid.css">
        <link rel="stylesheet" type="text/css" href="bootstrap-4.3.1-dist/css/bootstrap-reboot.css">
        <link rel="stylesheet" type="text/css" href="bootstrap-4.3.1-dist/css/bootstrap-grid.min.css">
        <link rel="stylesheet" type="text/css" href="bootstrap-4.3.1-dist/css/bootstrap-reboot.min.css">
        <title>Dronazon-Admin</title>
    </head>
    <body>
    <div>
        <form class="px-4 py-3" name="login" action="Dispatcher" method="post">
            <div class="form-group">
                <label for="exampleDropdownFormEmail1">Username </label>
                <input type="text" class="form-control" id="exampleDropdownFormEmail1" placeholder="Username">
            </div>
            <div class="form-group">
                <label for="exampleDropdownFormPassword1">Password</label>
                <input type="password" class="form-control" id="exampleDropdownFormPassword1" placeholder="Password">
            </div>
            <input class="btn btn-primary my-2 my-sm-0 mr-sm-2" type="submit">
            <input type="hidden" name="controllerAction" value="AdminManagement.login">
        </form>
    </div>
    </body>
</html>
