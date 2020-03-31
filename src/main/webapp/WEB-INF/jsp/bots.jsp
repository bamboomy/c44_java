<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Hello ${name}!</title>
<link href="/${board.tomcatPath}/css/main.css" rel="stylesheet">

<link href="/${board.tomcatPath}/bootstrap-4.4.1-dist/css/bootstrap.css" rel="stylesheet">

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

<script src="/${board.tomcatPath}/bootstrap-4.4.1-dist/js/bootstrap.bundle.js"></script>

<script type="text/javascript">

	var listen = true;

	$(document).ready(function() {
		
		myId = $('form').attr('myattribute');
		
		fill();
	});

	function clickToServer(md5, userHash) {
		
		$('#promoteModal').modal('hide');
		
		if (!listen) {

			return;
		}

		listen = false;

		$.ajax({
			type : "GET",
			'url' : '${board.piecePath}' + md5 + "/" + userHash,
			success : function(text) {

				fill();
			}
		});
		
		setTimeout(function() {

			listen = true;

		}, 1000);
	}

	function fill() {

		$
				.ajax({
					type : "GET",
					url : "https://chess4four.org/${board.tomcatPath}/botsBoard/?id=${user.javaHash}",
					async : false,
					success : function(text) {
						$('#board').html(text);
					}
				});
	}

</script>

</head>
<body>

	<audio id="check">
		<source src="https://chess4four.org/bin/check.mp3" type="audio/mpeg">
	</audio>

	<audio id="end">
		<source src="https://chess4four.org/bin/end.mp3" type="audio/mpeg">
	</audio>

	<center>
		<h1>${board.profile}</h1>
	</center>

	<div class="outer">
		<div class="middle">
			<div class="inner center">
				<center>
					<div id="board"></div>
				</center>
			</div>
		</div>
	</div>

</body>
</html>