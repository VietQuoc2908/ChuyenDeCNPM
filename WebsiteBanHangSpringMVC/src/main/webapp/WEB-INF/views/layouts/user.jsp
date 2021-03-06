<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Ustora Demo</title>

<!-- Google Fonts -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link
	href='http://fonts.googleapis.com/css?family=Titillium+Web:400,200,300,700,600'
	rel='stylesheet' type='text/css'>
<link
	href='http://fonts.googleapis.com/css?family=Roboto+Condensed:400,700,300'
	rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Raleway:400,100'
	rel='stylesheet' type='text/css'>

<!-- Bootstrap -->
<link rel="stylesheet"
	href="<c:url value="/assets/resources/css/bootstrap.min.css"/>">

<!-- Font Awesome -->
<link rel="stylesheet"
	href="<c:url value="/assets/resources/css/font-awesome.min.css"/>">

<!-- Custom CSS -->
<link rel="stylesheet"
	href="<c:url value="/assets/resources/css/owl.carousel.css"/>">
<link rel="stylesheet"
	href="<c:url value="/assets/resources/css/style.css"/>">
<link rel="stylesheet"
	href="<c:url value="/assets/resources/css/responsive.css"/>">
	
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

	<%@include file="/WEB-INF/views/layouts/user/header.jsp"%>

	<decorator:body />

	<%@include file="/WEB-INF/views/layouts/user/footer.jsp"%>
	
	
	
	<!-- Latest jQuery form server -->
	<script src="https://code.jquery.com/jquery.min.js"></script>

	<!-- Bootstrap JS form CDN -->
	<script
		src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

	<!-- jQuery sticky menu -->
	<script src="<c:url value="/assets/resources/js/owl.carousel.min.js"/>"></script>
	<script src="<c:url value="/assets/resources/js/jquery.sticky.js"/>"></script>

	<!-- jQuery easing -->
	<script
		src="<c:url value="/assets/resources/js/jquery.easing.1.3.min.js"/>"></script>

	<!-- Main Script -->
	<script src="<c:url value="/assets/resources/js/main.js"/>"></script>

	<!-- Slider -->
	<script type="text/javascript"
		src="<c:url value="/assets/resources/js/bxslider.min.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/assets/resources/js/script.slider.js"/>"></script>
</body>
</html>