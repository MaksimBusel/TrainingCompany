<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
    <head>
        <title>Login</title>
    </head>
    <body>
        <form action="controller" method="POST">
            <p>Login:</p>
            <input type="text" name="login">
            <p>Password</p>
            <input type="password" name="password">
            <input type="hidden" name="command" value="login">
            <br/><br/>
            <input type="submit" value="Log in">
            <br/>
        </form>
    </body>
</html>
