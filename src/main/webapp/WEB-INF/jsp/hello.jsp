<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Hello ${name}!</title>
<link href="/css/main.css" rel="stylesheet">

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

</style>


<script src="/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	var myId;

	$(document).ready(function() {
		myId = $('form').attr('myattribute');
	});

	function clickToServer(md5) {

		$.ajax({
			type : "GET",
			'url' : '/peace/' + md5,
			success : function(text) {
				location.reload();
			}
		});
	}
</script>
<c:if
	test="${!board.isPlayerIsMoving() && board.getCurrentPlayer().checkCheck() }">
	<script type="text/javascript">
		alert('check!!!');
	</script>
</c:if>
<c:if
	test="${!board.isPlayerIsMoving() && board.getCurrentPlayer().checkCheck() && board.getCurrentPlayer().canPrevent()}">
	<script type="text/javascript">
		alert('... but you can prevent...');
	</script>
</c:if>
<c:if test="${board.isWouldBeCheck()}">
	<script type="text/javascript">
		alert('You can\'t move that piece!!! (you would be check...)');
	</script>
</c:if>


</head>
<body>

<div id="status">

	<h3>"A green letter outside of Indiana Jones."</h3>
	
	Green: ${board.greenName}<br/>
	<br/>
	Blue: ${board.blueName}<br/>
	<br/>
	Red: ${board.redName}<br/>
	<br/>
	Yellow: ${board.yellowName}<br/>
	<br/>

</div>

	<center>
	
<div class="outer">
  <div class="middle">
    <div class="inner">
	
		<div>
			<c:forEach items="${board.getRotatedPlacez()}" var="row">

				<div class="row">

					<c:forEach items="${row}" var="place">

						<c:choose>
							<c:when test="${place != null}">

								<div class="square">

									<img src="../img/blank.png" width="35px" height="35px" />

									<c:choose>
										<c:when test="${place.isVisuallyAttacked()}">
											<c:choose>
												<c:when test="${place.hasPiece()}">
													<img class="${place.getCssName()}"
														src="${place.getPiece().getPieceName()}" width="35px"
														height="35px"
														onclick="clickToServer('${place.getMd5WithBoard()}')" />
												</c:when>
												<c:otherwise>
													<img class="${place.getCssName()}"
														src="../img/transparent.png" width="35px" height="35px"
														onclick="clickToServer('${place.getMd5WithBoard()}')" />
												</c:otherwise>
											</c:choose>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${place.hasPiece()}">
													<img class="${place.getCssName()}"
														src="${place.getPiece().getPieceName()}" width="35px"
														height="35px"
														onclick="clickToServer('${place.getPiece().getMd5WithBoard()}')" />
												</c:when>
												<c:otherwise>
													<img class="${place.getCssName()}"
														src="../img/transparent.png" width="35px" height="35px" />
												</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose>

								</div>
							</c:when>
							<c:otherwise>
								<div class="square">
									<img src="../img/blank.png" width="35px" height="35px" />
								</div>
							</c:otherwise>
						</c:choose>
					</c:forEach>

				</div>
			</c:forEach>
		</div>
		
	</div>
  </div>
</div>		
	</center>
</body>
</html>