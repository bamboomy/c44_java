<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hello ${name}!</title>
    <link href="/css/main.css" rel="stylesheet">
</head>
<body>
<center>
	<c:forEach items="${board.getPlacez()}" var="row">
		<c:forEach items="${row}" var="place">
  			<c:if test="${place != null}">
  				<img src="${place.getImageName()}" width="40px" height="40px" />
  			</c:if>
        </c:forEach>
        <br/>
    </c:forEach>
</center>   
</body>
</html>