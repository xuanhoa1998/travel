<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="checkLoginUrl" value="/login"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login V12</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="<c:url value='/resources/login/images/icons/favicon.ico'/>"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="<c:url value='/resources/login/vendor/bootstrap/css/bootstrap.min.css'/>">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="<c:url value='/resources/login/fonts/font-awesome-4.7.0/css/font-awesome.min.css'/>">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="<c:url value='/resources/login/fonts/Linearicons-Free-v1.0.0/icon-font.min.css'/>">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/login/vendor/animate/animate.css'/>">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="<c:url value='/resources/login/vendor/css-hamburgers/hamburgers.min.css'/>">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/login/vendor/select2/select2.min.css'/>">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/login/css/util.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/login/css/main.css'/>">
    <!--===============================================================================================-->
</head>
<body>

<div class="limiter">
    <div class="container-login100"
         style="background-image: url('<c:url value='/resources/login/images/img-01.jpg'/>');">
        <div class="wrap-login100 p-t-190 p-b-30">
            <spring:form cssClass="login100-form validate-form" method="post" action="${checkLoginUrl}"
                         modelAttribute="userDto">
                <div class="login100-form-avatar">
                    <img src="<c:url value='/resources/login/images/avatar-01.jpg'/>" alt="AVATAR">
                </div>

                <span class="login100-form-title p-t-20 p-b-45">
						John Doe
					</span>

                <c:if test="${not empty error}">
                    <span class="text-danger" style="margin-bottom: 20px;padding: 20px;background-color: #FFF">
                            ${error}
                    </span>
                </c:if>

                <div class="wrap-input100 validate-input m-b-10" data-validate="Username is required">
                    <spring:input cssClass="input100" path="username"/>
                    <span class="focus-input100"></span>
                    <span class="symbol-input100">
							<i class="fa fa-user"></i>
						</span>
                </div>

                <div class="wrap-input100 validate-input m-b-10" data-validate="Password is required">
                    <spring:password cssClass="input100" path="password"/>
                    <span class="focus-input100"></span>
                    <span class="symbol-input100">
							<i class="fa fa-lock"></i>
						</span>
                </div>

                <div class="container-login100-form-btn p-t-10">
                    <input class="login100-form-btn" value="Login" type="submit" style="cursor: pointer">
                </div>
            </spring:form>
        </div>
    </div>
</div>
<!--===============================================================================================-->
<script src="<c:url value='/resources/login/vendor/jquery/jquery-3.2.1.min.js'/>"></script>
<!--===============================================================================================-->
<script src="<c:url value='/resources/login/vendor/bootstrap/js/popper.js'/>"></script>
<script src="<c:url value='/resources/login/vendor/bootstrap/js/bootstrap.min.js'/>"></script>
<!--===============================================================================================-->
<script src="<c:url value='/resources/login/vendor/select2/select2.min.js'/>"></script>
<!--===============================================================================================-->
<script src="<c:url value='/resources/login/js/main.js'/>"></script>

</body>
</html>