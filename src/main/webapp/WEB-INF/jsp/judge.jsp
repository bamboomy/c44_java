<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<div id="status">

		<h3>${board.gameName}</h3>

		Green: ${board.greenName} ${board.timeArray[2]}<br /> <br /> 
		Blue: ${board.blueName} ${board.timeArray[3]}<br />
		<br /> Red: ${board.redName} ${board.timeArray[0]}<br /> <br /> Yellow:
		${board.yellowName} ${board.timeArray[1]}<br /> <br />

	</div>
