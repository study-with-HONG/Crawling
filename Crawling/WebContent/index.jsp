<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<button type="button" id="movieBtn">영화정보</button>
	
	<script type="text/javascript">
		$("#movieBtn").click(function(){
			location.href = "movie.jsp"
		});
	</script>
</body>
</html>