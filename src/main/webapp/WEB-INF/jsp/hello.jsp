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
	
	function fill(){
		
		var response = '';
		$.ajax({ type: "GET",   
				 url: "http://chess4four.io:8080/board/?id=${board.playerHash}",    
				 async: false,
				 success : function(text)
				 {
					 response = text;
				 }
		});

		$('#board').html(response);
	}

	function again() {

		setTimeout(function(){

			fill();
			
			again();

		}, 1000);
	}

	again();
	
	fill();
	
	var checkShown = false;
	
	var cantMoveShown = false;

</script>

</head>
<body>

	<div id="status">

		<h3>${board.gameName}</h3>

		Green: ${board.greenName}<br /> <br /> Blue: ${board.blueName}<br /> <br />
		Red: ${board.redName}<br /> <br /> Yellow: ${board.yellowName}<br /> <br />

	</div>

	<center>

		<div class="outer">
			<div class="middle">
				<div class="inner">

					<div id="board"></div>

				</div>
			</div>
		</div>
	</center>
	
	<div id="chat">

		<h3>${board.gameName}</h3>

		Green: ${board.greenName}<br /> <br /> Blue: ${board.blueName}<br /> <br />
		Red: ${board.redName}<br /> <br /> Yellow: ${board.yellowName}<br /> <br />

	</div>
	
</body>
</html>