<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Hello ${name}!</title>
<link href="/css/main.css" rel="stylesheet">
<script src="/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
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
	<center>
		<div>
			<c:forEach items="${board.getPlacez()}" var="row">

				<div class="row">

					<c:forEach items="${row}" var="place">

						<c:choose>
							<c:when test="${place != null}">

								<div class="square">

									<img src="../img/blank.png" width="40px" height="40px" />

									<c:choose>
										<c:when test="${place.isAttacked()}">
											<c:choose>
												<c:when test="${place.hasPiece()}">
													<img class="${place.getCssName()}"
														src="${place.getPiece().getPieceName()}" width="40px"
														height="40px" onclick="clickToServer('${place.getMd5()}')" />
												</c:when>
												<c:otherwise>
													<img class="${place.getCssName()}"
														src="../img/transparent.png" width="40px" height="40px"
														onclick="clickToServer('${place.getMd5()}')" />
												</c:otherwise>
											</c:choose>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${place.hasPiece()}">
													<img class="${place.getCssName()}"
														src="${place.getPiece().getPieceName()}" width="40px"
														height="40px"
														onclick="clickToServer('${place.getPiece().getMd5()}')" />
												</c:when>
												<c:otherwise>
													<img class="${place.getCssName()}"
														src="../img/transparent.png" width="40px" height="40px" />
												</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose>

								</div>
							</c:when>
							<c:otherwise>
								<div class="square">
									<img src="../img/blank.png" width="40px" height="40px" />
								</div>
							</c:otherwise>
						</c:choose>
					</c:forEach>

				</div>
			</c:forEach>
		</div>
	</center>
</body>
</html>