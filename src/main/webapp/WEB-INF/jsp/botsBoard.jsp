<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="/js/jquery-3.4.1.min.js"></script>

<script
	src="/${board.tomcatPath}/bootstrap-4.4.1-dist/js/bootstrap.bundle.js"></script>

<script type="text/javascript">
	var myId;

	$(document).ready(function() {
		myId = $('form').attr('myattribute');

		//$('#myModal').modal('show');
	});
</script>

<c:if
	test="${!board.isPlayerIsMoving() && board.getCurrentPlayer().checkCheck()}">
	<script type="text/javascript">
		var y = document.getElementById("check");

		y.play();

		alert('check!!!');
	</script>
</c:if>

<c:if test="${board.isWouldBeCheck()}">
	<script type="text/javascript">
		alert('You can\'t move that piece!!! (you would be check...)');
	</script>
</c:if>

<c:if
	test="${board.promote}">

	<script type="text/javascript">
		$(document).ready(function() {

			$('#promoteModal').modal('show');

		});
	</script>

</c:if>

<div class="container">

	<!-- The Promote Modal -->
	<div class="modal" id="promoteModal">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title text-center">Choose your vice...</h4>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<center>
						<img src="/${board.tomcatPath}/img/queen.jpg" height="200"
							width="200"
							onclick="clickToServer('${board.queenHash}', '${user.javaHash}')" />
						<img src="/${board.tomcatPath}/img/horse.jpg" height="200"
							width="200"
							onclick="clickToServer('${board.horseHash}', '${user.javaHash}')" /><br />
					</center>
					<br />
					<p>
						We only give the choice between a knight and a queen<br />
						because with a queen you can do any move of a rook or bishop...<br />
						<br />
					</p>

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

								<img src="../img/blank.png" width="35px" height="35px" />

								<c:choose>
									<c:when test="${place.isVisuallyAttacked()}">
										<c:choose>
											<c:when test="${place.hasPiece()}">
												<img class="${place.getCssName()}"
													src="../${place.getPiece().getPieceName()}" width="35px"
													height="35px"
													onclick="clickToServer('${place.getMd5WithBoard()}', '${user.javaHash}')" />
											</c:when>
											<c:otherwise>
												<img class="${place.getCssName()}"
													src="../img/transparent.png" width="35px" height="35px"
													onclick="clickToServer('${place.getMd5WithBoard()}', '${user.javaHash}')" />
											</c:otherwise>
										</c:choose>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${place.hasPiece()}">
												<img class="${place.getCssName()}"
													src="../${place.getPiece().getPieceName()}" width="35px"
													height="35px"
													onclick="clickToServer('${place.getPiece().getMd5WithBoard()}', '${user.javaHash}')" />
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