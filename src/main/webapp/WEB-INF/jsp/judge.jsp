<%@page import="org.bamboomy.c44.board.Board"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="status">

	<h3>${board.gameName}</h3>

	Green: ${board.greenName} ${board.getTimeArray(2)} <span class="red">${board.timeOutzArray[2]}</span>
	<br /> <br /> Blue: ${board.blueName} ${board.getTimeArray(3)} <span
		class="red">${board.timeOutzArray[3]}</span><br /> <br /> Red:
	${board.redName} ${board.getTimeArray(0)} <span class="red">${board.timeOutzArray[0]}</span><br />
	<br /> Yellow: ${board.yellowName} ${board.getTimeArray(1)} <span
		class="red">${board.timeOutzArray[1]}</span><br /> <br />

</div>

<c:if test="${board.playSound}">
	<script>
		var x = document.getElementById("myAudio");

		//x.play();
	</script>
</c:if>

<c:if test="${board.isTimeOut(user.color)}">
	<script>
		alert("You timed out; you can do this 5 times max\nthe 6th time you automatically resign...");
	</script>
</c:if>

<c:if test="${!board.readResign(user.color)}">
	<script>
		alert('${board.resignText}');
	</script>
</c:if>

<c:if test="${board.isNewGUIDead()}">
	<script>
		if (confirm("You resigned...\nDo you want to stick around?\nClick 'ok' to stay,\nclick 'cancel' to leave.") != true) {
			//delete king
			window.location.assign("https://www.google.be");
		}
	</script>
</c:if>

<c:if test="${board.isFinished()}">
	<script>
		finished = true;
	</script>
</c:if>

<%
	board.getLock().unlock();
%>
