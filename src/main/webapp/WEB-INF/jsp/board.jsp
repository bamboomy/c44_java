<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="/js/jquery-3.4.1.min.js"></script>

<script
	src="/${board.tomcatPath}/bootstrap-4.4.1-dist/js/bootstrap.bundle.js"></script>

<script type="text/javascript">
	var myId;

	$(document).ready(function() {
		myId = $('form').attr('myattribute');

		$('#myModal').modal('show');
		
		<c:if test="${board.isClockStarting() || board.isClockRunning() || board.clockStopped}">

			$('#myModal').modal('hide');

			<c:if test="${!board.isClockRunning() && !board.clockStopped}">
		
				$('#clockStartModal').modal('show');
		
			</c:if>

			<c:if test="${board.clockStopped}">
		
				$('#clockStopModal').modal('show');
		
			</c:if>
		
		</c:if>
		
	});

</script>

<c:if test="${!board.isPlayerIsMoving() && board.isRenderingCurrentPlayer(user.color) 
	&& board.getCurrentPlayer().checkCheck() }">
	<script type="text/javascript">
		if (!checkShown) {
			alert('check!!!');
			checkShown = true;
		}
	</script>
</c:if>

<c:if
	test="${board.isWouldBeCheck() && board.isRenderingCurrentPlayer(user.color)}">
	<script type="text/javascript">
		if (!cantMoveShown) {

			alert('You can\'t move that piece!!! (you would be check...)');

			cantMoveShown = true;
		}
	</script>
</c:if>

<c:if test="${board.promote && board.isRenderingCurrentPlayer(user.color)}">

	<script type="text/javascript">
	
		$(document).ready(function() {

			$('#promoteModal').modal('show');
			
		});

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

<div class="container">

	<!-- The Waiting Modal -->
	<div class="modal" id="myModal">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Waiting until everyone is there...</h4>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					${board.greenName}&nbsp;
					<c:choose>
						<c:when test="${board.playerz[0].timestamp == -1}">
							<img src="/${board.tomcatPath}/img/spinner.gif" alt="Smiley face"
								height="42" width="42">
							<br />
						</c:when>
						<c:otherwise>
							<img src="/${board.tomcatPath}/img/check.png" alt="Smiley face"
								height="42" width="42">
							<br />
						</c:otherwise>
					</c:choose>
					<br /> ${board.blueName}&nbsp;
					<c:choose>
						<c:when test="${board.playerz[1].timestamp == -1}">
							<img src="/${board.tomcatPath}/img/spinner.gif" alt="Smiley face"
								height="42" width="42">
							<br />
						</c:when>
						<c:otherwise>
							<img src="/${board.tomcatPath}/img/check.png" alt="Smiley face"
								height="42" width="42">
							<br />
						</c:otherwise>
					</c:choose>
					<br /> ${board.redName}&nbsp;
					<c:choose>
						<c:when test="${board.playerz[2].timestamp == -1}">
							<img src="/${board.tomcatPath}/img/spinner.gif" alt="Smiley face"
								height="42" width="42">
							<br />
						</c:when>
						<c:otherwise>
							<img src="/${board.tomcatPath}/img/check.png" alt="Smiley face"
								height="42" width="42">
							<br />
						</c:otherwise>
					</c:choose>
					<br /> ${board.yellowName}&nbsp;
					<c:choose>
						<c:when test="${board.playerz[3].timestamp == -1}">
							<img src="/${board.tomcatPath}/img/spinner.gif" alt="Smiley face"
								height="42" width="42">
							<br />
						</c:when>
						<c:otherwise>
							<img src="/${board.tomcatPath}/img/check.png" alt="Smiley face"
								height="42" width="42">
							<br />
						</c:otherwise>
					</c:choose>
					<br />
				</div>
			</div>
		</div>
	</div>

	<!-- The Start the Clock Modal -->
	<div class="modal" id="clockStartModal">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title text-center">Start the clock!</h4>
				</div>

			</div>
		</div>
	</div>

	<!-- The Stop the Clock Modal -->
	<div class="modal" id="clockStopModal">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title text-center">Stop the clock!</h4>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					
					<p>
					Someone has left (our lost connectivity),<br/>
					<br/>
					(s)he has 50s to return...<br/>
					<br/>
					If too late, his/hers place will be taken by an available chatter<br/>
					<br/>
					or a Random bot.
					</p>
					
					<p>
					${board.getRemainingStopSecs()}s remaining...
					</p>
					
				</div>

			</div>
		</div>
	</div>

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
				
					<img src="queen.jpg" height="200" width="200" onclick="clickToServer('${board.queenHash}', '${user.javaHash}')" />
					<img src="horse.jpg" height="200" width="200" onclick="clickToServer('${board.horseHash}', '${user.javaHash}')" /><br/>
					<br/>
					<p>
					We only give the choice between a knight and a queen<br/>
					because with a queen you can do any move of a rook or bishop...<br/>
					<br/>
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
												<img class="${place.getCssName()}" src="img/transparent.png"
													width="35px" height="35px"
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
												<img class="${place.getCssName()}" src="img/transparent.png"
													width="35px" height="35px" />
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

</div>