<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Hello ${name}!</title>
<link href="/tomcat/css/main.css" rel="stylesheet">

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

	var checkShown = false;
	
	var cantMoveShown = false;
	
	var cantPreventShown = false;

	var myId;

	var finished = false;

	$(document).ready(function() {
		myId = $('form').attr('myattribute');
	});

	function clickToServer(md5, userHash) {

		$.ajax({
			type : "GET",
			'url' : '/peace/' + md5 + "/" + userHash,
			success : function(text) {
				fill();
				
				checkShown = false;
				
				cantMoveShown = false;
				
				cantPreventShown = false;
			}
		});
	}

	function fill() {

		$.ajax({
			type : "GET",
			url : "https://chess4four.io/tomcat/board/?id=${board.playerHash}",
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

		}, 1000);
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
					url : "https://chess4four.io/java/chatText.php?board=${board.playerHash}",
					success : function(text) {

						if (text != "clean") {

							$('#chatText').html(text);

							var objDiv = document.getElementById("chatText");
							objDiv.scrollTop = objDiv.scrollHeight;
						}
					}
				});
	}

	function sendMessage() {

		var chat = $("#chatField").val();

		$("#chatField").val("");

		$
				.ajax({
					type : "POST",
					xhrFields : {
						withCredentials : true
					},
					url : "https://chess4four.io/java/chat.php?board=${board.playerHash}",
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

		$("#chatField").on("keydown", function(e) {

			if (e.which == 13) {

				sendMessage();
			}
		});
	});

	function callJudge() {

		$.ajax({
			type : "GET",
			url : "https://chess4four.io/tomcat/judge/?id=${board.playerHash}",
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

		if (window
				.confirm("Are you sure?\n(This is an irriversible action\nwhich will change your life forever!!!)")) {

			$
					.ajax({
						type : "GET",
						url : "https://chess4four.io/tomcat/resign/?id=${board.playerHash}",
						async : false,
						success : function(text) {
						
							if (confirm("You resigned...\nDo you want to stick around?\nClick 'ok' to stay,\nclick 'cancel' to leave.") != true) {
								
								//TODO: delete king
								
								window.location.assign("https://www.google.be");
							}
						}
					});
		}
	}
</script>

</head>
<body>

	<audio id="myAudio">
		<source src="/soundz/tik.mp3" type="audio/mpeg">
		Your browser does not support the audio element.
	</audio>

	<div id="judge"></div>

	<center>
		<div class="outer">
			<div class="middle">
				<div class="inner">
					<div id="board"></div>
				</div>
			</div>
		</div>
	</center>

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
			<input id="chatField" type="text" class="fill" /><input
				type="button" value="Send" onclick="sendMessage();" />
		</div>

	</div>

</body>
</html>