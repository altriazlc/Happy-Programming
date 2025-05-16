<%-- 
    Document   : Signup
    Created on : Sep 13, 2024, 11:26:33 PM
    Author     : nhhag
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <!-- META ============================================= -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="keywords" content="" />
        <meta name="author" content="" />
        <meta name="robots" content="" />

       <!-- DESCRIPTION -->
	<meta name="description" content="Happy Programing" />
	
	<!-- OG -->
	<meta property="og:title" content="Happy Programing" />
        <meta property="og:description" content="Happy Programing" />
        <meta property="og:image" content="" />
        <meta name="format-detection" content="telephone=no">
	
	<!-- FAVICONS ICON ============================================= -->
	<link rel="icon" href="assets/images/faviconV2.png" type="image/x-icon" />
        <link rel="shortcut icon" type="image/x-icon" href="assets/images/faviconV2.png" />
	
	<!-- PAGE TITLE HERE ============================================= -->
	<title>Happy Programming</title>

        <!-- MOBILE SPECIFIC ============================================= -->
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!--[if lt IE 9]>
        <script src="assets/js/html5shiv.min.js"></script>
        <script src="assets/js/respond.min.js"></script>
        <![endif]-->

        <!-- All PLUGINS CSS ============================================= -->
        <link rel="stylesheet" type="text/css" href="assets/css/assets.css">

        <!-- TYPOGRAPHY ============================================= -->
        <link rel="stylesheet" type="text/css" href="assets/css/typography.css">

        <!-- SHORTCODES ============================================= -->
        <link rel="stylesheet" type="text/css" href="assets/css/shortcodes/shortcodes.css">

        <!-- STYLESHEETS ============================================= -->
        <link rel="stylesheet" type="text/css" href="assets/css/style.css">
        <link class="skin" rel="stylesheet" type="text/css" href="assets/css/color/color-1.css">

    </head>
    <body id="bg">
        <div class="page-wraper">
            <div id="loading-icon-bx"></div>
            <div class="account-form">
                <div class="account-head" style="background-image:url(https://hanoi.fpt.edu.vn/wp-content/uploads/2024/10/STA02949-scaled.jpg);">
			<a href="home"><img src="https://daihoc.fpt.edu.vn/wp-content/uploads/2023/04/cropped-cropped-2021-FPTU-Long.png" alt="" style="width: 50%"></a>
		</div>
                <div class="account-form-inner">
                    <div class="account-container" >

                        <div class="heading-bx left">
                            <h2 class="title-head">Sign Up <span>Now</span></h2> 

                            <p>Login Your Account <a href="signin">Click here</a></p>
                            <p style="color: red">${notify}</p>
                        </div>	
                        <form class="contact-bx" action="signup" method="post" id="signupForm" > 
                            <div class="row placeani">
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label>Your username</label>
                                            <input name="username" type="text" required="" class="form-control" value="${username}">
                                            <small style="color: red">${uerror}</small>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label for="user-mail">Email address</label>
                                            <input name="email" type="email" id="user-mail" class="form-control"  value="${email}" required>
                                            <small style="color: red">${eerror}</small>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label for="user-pass">Password</label>
                                            <input name="pass" type="password" class="form-control" required>                                         
                                            <small>Password requirements (6-18 characters, containing a number, uppercase letter)</small>
                                            <small style="color: red">${perror1}</small>
                                            
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label for="user-repeatpass">Repeat Password</label>
                                            <input name="repass" type="password"  class="form-control"  required>
                                           
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <div class="input-group"> 
                                            <label>Full name</label>
                                            <input name="fullname" type="text" class="form-control" required="" value="${fullname}">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-12"> 
                                    I am
                                    <div class="form-group">
                                        <div class="input-group">
                                            <select name="role" >
                                                <option value="1">Mentor</option>
                                                <option value="2">Mentee</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <div class="input-group"> 
                                            <label>Phone number</label>
                                            <input name="phone" type="text" class="form-control" required="" value="${phone}">
                                            <small style="color: red">${pherror1}</small>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <div class="input-group"> 
                                            <label>Address</label>
                                            <input name="address" type="text" class="form-control" required="" value="${address}">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        Date of birth
                                        <div class="input-group">
                                            <input name="dob" type="date" class="form-control" required="" value="">
                                            <small style="color: red">${aerror}</small>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-lg-12"> 
                                    Sex
                                    <div class="form-group">
                                        <div class="form" style="width: 60%"> 

                                            <input type="radio" name="sex" value="male" checked>
                                            Male


                                            <input type="radio" name="sex" value="female">
                                            Female

                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-12 m-b30">
                                    <button name="submit" type="submit" value="Submit" class="btn button-md">Sign Up</button>
                                </div>

                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!-- External JavaScripts -->
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/vendors/bootstrap/js/popper.min.js"></script>
        <script src="assets/vendors/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/vendors/bootstrap-select/bootstrap-select.min.js"></script>
        <script src="assets/vendors/bootstrap-touchspin/jquery.bootstrap-touchspin.js"></script>
        <script src="assets/vendors/magnific-popup/magnific-popup.js"></script>
        <script src="assets/vendors/counter/waypoints-min.js"></script>
        <script src="assets/vendors/counter/counterup.min.js"></script>
        <script src="assets/vendors/imagesloaded/imagesloaded.js"></script>
        <script src="assets/vendors/masonry/masonry.js"></script>
        <script src="assets/vendors/masonry/filter.js"></script>
        <script src="assets/vendors/owl-carousel/owl.carousel.js"></script>
        <script src="assets/js/functions.js"></script>
        <script src="assets/js/contact.js"></script>

    </body>

</html>

