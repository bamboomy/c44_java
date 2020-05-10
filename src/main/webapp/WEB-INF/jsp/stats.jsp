<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<style>
.outer {
	display: table;
	position: absolute;
	top: 0;
	left: 0;
	height: 100%;
	width: 100%;
}

.middle {
	display: table-cell;
	vertical-align: middle;
}

.inner {
	margin-left: auto;
	margin-right: auto;
	width: 100%;
}

.center {
	margin: 0 auto;
	text-align: center;
}
</style>
</head>
<body>

	<div class="outer">
		<div class="middle">
			<div class="inner center">
				<center>
					<c:forEach items="${GAMEZ}" var="game">
    					${game.key}, players: ${game.value.playerz[0].robot}<br>
    					<br/>
					</c:forEach>
				</center>
			</div>
		</div>
	</div>

</body>
</html>