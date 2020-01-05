<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="status">

	<h3>${board.gameName}</h3>

	Green: ${board.greenName} ${board.timeArray[2]} <span class="red">${board.timeOutzArray[2]}</span> <br /> <br /> Blue:
	${board.blueName} ${board.timeArray[3]} <span class="red">${board.timeOutzArray[3]}</span><br /> <br /> Red:
	${board.redName} ${board.timeArray[0]} <span class="red">${board.timeOutzArray[0]}</span><br /> <br /> Yellow:
	${board.yellowName} ${board.timeArray[1]} <span class="red">${board.timeOutzArray[1]}</span><br /> <br />

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
