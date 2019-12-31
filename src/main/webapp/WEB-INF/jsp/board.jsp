<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		if (!checkShown) {

			alert('check!!!');

			checkShown = true;
		}
	</script>
</c:if>
<c:if
	test="${!board.isPlayerIsMoving() && board.getCurrentPlayer().checkCheck() && board.getCurrentPlayer().canPrevent()}">
	<script type="text/javascript">
		if (!checkShown) {

			alert('... but you can prevent...');
		}
	</script>
</c:if>
<c:if test="${board.isWouldBeCheck()}">
	<script type="text/javascript">
		if (!cantMoveShown) {

			alert('You can\'t move that piece!!! (you would be check...)');

			cantMoveShown = true;
		}
	</script>
</c:if>
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
