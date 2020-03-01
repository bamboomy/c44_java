<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	var myId;

	$(document).ready(function() {
		myId = $('form').attr('myattribute');
	});
</script>
<c:if
	test="${!board.isPlayerIsMoving() && board.isRenderingCurrentPlayer(user.color) 
	&& board.getCurrentPlayer().checkCheck() }">
	<script type="text/javascript">
		if (!checkShown) {

			alert('check!!!');

			checkShown = true;
		}
	</script>
</c:if>
<c:if
	test="${!board.isPlayerIsMoving() && board.getCurrentPlayer().checkCheck() 
	&& board.isRenderingCurrentPlayer(user.color) && board.getCurrentPlayer().canPrevent()}">
	<script type="text/javascript">
		if (!cantMoveShown) {

			alert('... but you can prevent...');
			
			cantMoveShown = true;
		}
	</script>
</c:if>
<c:if
	test="${!board.isPlayerIsMoving() && board.getCurrentPlayer().checkCheck() 
	&& board.isRenderingCurrentPlayer(user.color) && !board.getCurrentPlayer().canPrevent()}">
	<script type="text/javascript">
		if (!cantPreventShown){
			
			alert("and you can't prevent...");
			
			var txt = "You are check mate but your king\n";
			txt += "can utter one final command...\n";
			txt += "Make it a worthy one!!!" 
			
			alert(txt);
			
			cantPreventShown = true;
		}
	</script>
</c:if>
<c:if test="${board.isWouldBeCheck() && board.isRenderingCurrentPlayer(user.color)}">
	<script type="text/javascript">
		if (!cantMoveShown) {

			alert('You can\'t move that piece!!! (you would be check...)');

			cantMoveShown = true;
		}
	</script>
</c:if>

<div id="messages">

	<div class="outer">
		<div class="middle">
			<div class="inner">
				<div style="text-align: center; font-size: larger;">Messages:</div>
				<p id="message">${board.statusMessages}</p>
			</div>
		</div>
	</div>

</div>


<div>
	<c:forEach items="${board.getRotatedPlacez(user.color)}" var="row">

		<div class="row">

			<c:forEach items="${row}" var="place">

				<c:choose>
					<c:when test="${place != null}">

						<div class="square">

							<img src="img/blank.png" width="35px" height="35px" />

							<c:choose>
								<c:when test="${place.isVisuallyAttacked()}">
									<c:choose>
										<c:when test="${place.hasPiece()}">
											<img class="${place.getCssName()}"
												src="${place.getPiece().getPieceName()}" width="35px"
												height="35px"
												onclick="clickToServer('${place.getMd5WithBoard()}', '${user.javaHash}')" />
										</c:when>
										<c:otherwise>
											<img class="${place.getCssName()}"
												src="img/transparent.png" width="35px" height="35px"
												onclick="clickToServer('${place.getMd5WithBoard()}', '${user.javaHash}')" />
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${place.hasPiece()}">
											<img class="${place.getCssName()}"
												src="${place.getPiece().getPieceName()}" width="35px"
												height="35px"
												onclick="clickToServer('${place.getPiece().getMd5WithBoard()}', '${user.javaHash}')" />
										</c:when>
										<c:otherwise>
											<img class="${place.getCssName()}"
												src="img/transparent.png" width="35px" height="35px" />
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>

						</div>
					</c:when>
					<c:otherwise>
						<div class="square">
							<img src="img/blank.png" width="35px" height="35px" />
						</div>
					</c:otherwise>
				</c:choose>
			</c:forEach>

		</div>
	</c:forEach>
</div>
