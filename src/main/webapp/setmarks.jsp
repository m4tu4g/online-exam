
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <sql:setDataSource driver="com.mysql.cj.jdbc.Driver"
                           url="jdbc:mysql://lcpbq9az4jklobvq.cbetxkdyhwsb.us-east-1.rds.amazonaws.com/ehjn4n6t5lh0zafz"
                           user="vt6q8mzm255je024"
                           password="xgabcm5xp3gan7n0"></sql:setDataSource>
        <sql:query var="userscore">
            select marks from login where email='${email}'  
        </sql:query>
        <c:set var="uscore" value="${param.uscore}"/>
      
     <c:forEach  var="user" items="${userscore.rows}">
 
      
 <c:set var="newscore" value="${uscore + user.score}"/>
       <sql:update>
          Update login set marks='${newscore}' where email='${email}'
      </sql:update>
        <c:redirect url="examscreen.jsp"></c:redirect>    
      </c:forEach>
      
    </body>
</html>
