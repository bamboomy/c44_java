<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Hello ${name}!</title>
<link href="/${board.tomcatPath}/css/main.css" rel="stylesheet">

<link href="/${board.tomcatPath}/bootstrap-4.4.1-dist/css/bootstrap.css"
	rel="stylesheet">

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


<script src="/${board.tomcatPath}/js/jquery-3.4.1.min.js"></script>

<script
	src="/${board.tomcatPath}/bootstrap-4.4.1-dist/js/bootstrap.bundle.js"></script>

<script type="text/javascript">
	var checkShown = false;

	var cantMoveShown = false;

	var cantPreventShown = false;

	var myId;

	var finished = false;

	var waitingCounter = 7;

	$(document).ready(function() {
		myId = $('form').attr('myattribute');
	});

	function clickToServer(md5, userHash) {

		$.ajax({
			type : "GET",
			'url' : '${board.piecePath}' + md5 + "/" + userHash,
			success : function(text) {
				fill();

				checkShown = false;

				cantMoveShown = false;

				cantPreventShown = false;
			}
		});
	}

	function fill() {

		$('#myModal').modal('hide');

		$('#clockStartModal').modal('hide');

		$('#clockStopModal').modal('hide');

		$('#promoteModal').modal('hide');

		$('#finishModal').modal('hide');

		$
				.ajax({
					type : "GET",
					url : "https://chess4four.org/${board.tomcatPath}/board/?id=${user.javaHash}",
					async : false,
					success : function(text) {
						$('#board').html(text);
					}
				});
	}

	function again() {

		setTimeout(function() {

			fill();

			again();

		}, 4000);
	}

	again();

	fill();

	function fillChat() {

		$
				.ajax({
					type : "get",
					xhrFields : {
						withCredentials : true
					},
					url : "https://chess4four.org/${board.javaPath}/chatText.php?board=${user.javaHash}",
					success : function(text) {

						if (text != "clean") {

							$('#chatText').html(text);

							var objDiv = document.getElementById("chatText");
							objDiv.scrollTop = objDiv.scrollHeight;
						}
					}
				});
	}

	function sendFeedback() {

		$
				.ajax({
					type : "POST",
					xhrFields : {
						withCredentials : true
					},
					url : "https://chess4four.org/${board.javaPath}/bad.php?user=${user.javaHash}",
					data : {
						bad : $('#bad').val()
					},
					success : function(text) {

						if(text != 'fail'){
							
							hideModal();			

						} else {
							
							alert('we couldnt save your wows...');
						}
					}
				});
	}
	
	function hideModal(){
	    $("#feedbackModal").removeClass("in");
	    $(".modal-backdrop").remove();
	    $("#feedbackModal").hide();
	}

	function sendMessage() {

		var chat = $("#chatField${board.chatId}").val();

		$("#chatField${board.chatId}").val("");

		$
				.ajax({
					type : "POST",
					xhrFields : {
						withCredentials : true
					},
					url : "https://chess4four.org/${board.javaPath}/chat.php?board=${user.javaHash}",
					data : {
						text : chat
					},
					success : function(text) {
						//alert(text);

						fillChat();
					}
				});
	}

	function showChat() {

		setTimeout(function() {

			fillChat();

			showChat();

		}, 1000);
	}

	showChat();

	$(document).ready(function() {

		$("#chatField${board.chatId}").on("keydown", function(e) {

			if (e.which == 13) {

				sendMessage();
			}
		});

	});

	function callJudge() {

		$
				.ajax({
					type : "GET",
					url : "https://chess4four.org/${board.tomcatPath}/judge/?id=${user.javaHash}",
					async : false,
					success : function(text) {
						$('#judge').html(text);
					}
				});
	}

	function judge() {

		if (finished) {

			return;
		}

		setTimeout(function() {

			callJudge();

			judge();

		}, 1000);
	}

	judge();

	callJudge();

	function resign() {

		if (window.confirm("Are you sure?")) {

			$
					.ajax({
						type : "GET",
						url : "https://chess4four.org/${board.tomcatPath}/resign/?id=${user.javaHash}",
						async : false,
						success : function(text) {

							if (confirm("You resigned...\nDo you want to stick around?\nClick 'ok' to stay,\nclick 'cancel' to leave.") != true) {

								window.location
										.assign("https://chess4four.org${board.PHPPath}/pagez/lobby.php");

							} else {

								$('#feedbackModal').modal('show');
							}
						}
					});
		}
	}
</script>

</head>
<body>


	<audio id="tik">
		<source src="https://chess4four.org/bin/tik.mp3" type="audio/mpeg">
		Your browser does not support the audio element.
	</audio>

	<audio id="check">
		<source src="https://chess4four.org/bin/check.mp3" type="audio/mpeg">
	</audio>

	<audio id="end">
		<source src="https://chess4four.org/bin/end.mp3" type="audio/mpeg">
	</audio>

	<center>
		<h1>${board.profile}</h1>
	</center>

	<div id="judge"></div>

	<!-- The Waiting Modal -->
	<div class="modal" id="feedbackModal">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">We value your opinion!</h4>
				</div>

				<!-- Modal body -->
				<div class="modal-body">

					What can we do to improve the app?

					<textarea id="bad" name="bad" rows="5" cols="50"></textarea>

					<br /> <br /> <input type="submit" onclick='sendFeedback();'
						value="Send">
					</form>
				</div>
			</div>
		</div>
	</div>

	<div class="outer">
		<div class="middle">
			<div class="inner center">
				<center>
					<div id="board"></div>
				</center>
			</div>
		</div>
	</div>

	<div id="resign">

		<div class="outer">
			<div class="middle">
				<div class="inner">
					<input type="button" onclick="resign();" value="resign" />
				</div>
			</div>
		</div>

	</div>

	<div id="chat">

		<div style="text-align: center; font-size: larger;">Chat</div>
		<div id="chatText"></div>

		<div class="bottom">
			<input id="chatField${board.chatId}" type="text" class="fill"
				autocomplete="false" /><input type="button" value="Send"
				onclick="sendMessage();" />
		</div>

	</div>



</body>
</html>