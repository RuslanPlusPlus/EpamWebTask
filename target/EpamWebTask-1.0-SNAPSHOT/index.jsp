<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1>Epam task</h1>
<br/>
<div>
    <form action="main-servlet" method="get">
        <input type="hidden" name="command" value="display_user_list">
        <input type="submit" name="submit" value="Display user list">
    </form>
</div>
<br/>
<div>
    <h3>Users from age range</h3>
    <form action="main-servlet" method="post" name="Users from age range">
        <input type="hidden" name="command" value="display_by_age_range">
        lower range value: <input type="number" name="lowerValue" value=""> <br/>
        higher range value: <input type="number" name="higherValue" value=""> <br/>
        <input type="submit" name="submit" value="Display">
    </form>
</div>
<br/>
<div>
    <form action="main-servlet" method="get">
        <input type="hidden" name="command" value="sort_user_list">
        <input type="submit" name="submit" value="Sort users by age">
    </form>
</div>
</body>
</html>