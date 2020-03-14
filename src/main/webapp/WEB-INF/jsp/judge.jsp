<%@page import="org.bamboomy.c44.board.Board"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="status">

	<h3>${board.gameName}</h3>

	Green: ${board.filter(board.greenName, user)}: ${board.getPlayerString(0, user)}<br /> <br /> 
	Blue: ${board.filter(board.blueName, user)}: ${board.getPlayerString(1, user)}<br /> <br /> 
	Red: ${board.filter(board.redName, user)}: ${board.getPlayerString(2, user)}<br />	<br /> 
	Yellow: ${board.filter(board.yellowName, user)}: ${board.getPlayerString(3, user)}<br /> <br />

</div>

<c:if test="${board.readSound(user.color)}">
	<script>
		var x = document.getElementById("tik");

		x.play();
		
		alert("It's your turn!");
	</script>
</c:if>

<c:if test="${board.isTimeOut(user.color)}">
	<script>
		alert("You timed out; you can do this 3 times max\nthe 4th time you automatically resign...");
	</script>
</c:if>

<c:if test="${!board.readResign(user.color)}">
	<script>
		alert('${board.resignText}');
	</script>
</c:if>
<c:if test="${board.isFinished()}">
	<script>
		finished = true;
	</script>
</c:if>

<%
	Board board = (Board) request.getAttribute("board");

	board.getLock().unlock();
%>
