<%-- 
    Document   : rateSkill
    Created on : Nov 3, 2024, 2:32:17 PM
    Author     : asus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <title>Happy Programing : Rate Skill </title>

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

        <style>
            .star-rating {
                direction: rtl;
                display: inline-block;
                font-size: 20px;
            }
            .star-rating input[type="radio"] {
                display: none;
            }
            .star-rating label {
                color: #ddd;
                font-size: 30px;
                cursor: pointer;
            }
            .star-rating input:checked ~ label {
                color: #f5b301;
            }
            .star-rating label:hover,
            .star-rating label:hover ~ label {
                color: #f5b301;
            }
        </style>
    </head>
    <body id="bg">
        <div class="page-wraper">
            <div id="loading-icon-bx"></div>

            <!-- Header Top ==== -->
            <header class="header rs-nav">
                <jsp:include page="header.jsp" />
            </header>
            <!-- header END ==== -->
            <!-- Content -->
            <div class="page-content bg-white">
                <!-- inner page banner -->
                <div class="page-banner ovbl-dark" style="background-image:url(assets/images/banner/banner3.jpg);">
                    <div class="container">
                        <div class="page-banner-entry">
                            <h1 class="text-white">Rate Skill</h1>
                        </div>
                    </div>
                </div>
                <!-- inner page banner END -->
                <div class="content-block">
                    <!-- About Us -->
                    <div class="section-area section-sp1 align-items-center justify-content-center">
                        <div class="container">
                            <div class="row">
                                <div class="db-breadcrumb col-lg-4">
                                </div>  
                                <div class="row justify-content-center">
                                    <div class="col-lg-20 m-b30">
                                        <div class="widget-box p-4 shadow">
                                            <div class="widget-inner text-center">
                                                <form action="rateskill" method="POST">
                                                    <input type="hidden" name="menteeId" value="${mentee.menteeId}" />
                                                    <input type="hidden" name="skillId" value="${skill.skillId}" />
                                                    <input type="hidden" name="requestId" value="${param.requestId}" />

                                                    <!-- Rating Section -->

                                                    <div class="text-center">
                                                        <img src="data:image/jpeg;base64,${skill.base64ImageFile}" class="rounded-circle" alt="${skill.skillName}" style="margin: 50px 10px;width: 150px;height: 150px;object-fit: cover;">
                                                    </div>
                                                    <h5>Framework: ${skill.skillName}</h5>
                                                    <c:forEach items="${requestScope.requestlist}" var="r">
                                                        <c:if test="${r.requestId==param.requestId}">
                                                            <span>Title: ${r.title}</span><br/>
                                                        </c:if>
                                                    </c:forEach>

                                                    <!-- Star Rating -->
                                                    <div class="star-rating">
                                                        <input type="radio" id="5-stars" name="rating" value="5" required><label for="5-stars">★</label>
                                                        <input type="radio" id="4-stars" name="rating" value="4"><label for="4-stars">★</label>
                                                        <input type="radio" id="3-stars" name="rating" value="3"><label for="3-stars">★</label>
                                                        <input type="radio" id="2-stars" name="rating" value="2"><label for="2-stars">★</label>
                                                        <input type="radio" id="1-star" name="rating" value="1"><label for="1-star">★</label>
                                                    </div>
                                                    <br>
                                                    <input type="submit" class="btn button-m green" value="Submit Rating">
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- contact area END -->
        <!-- Content END-->
        <!-- Footer ==== -->

        <jsp:include page="footer.jsp" />

        <!-- Footer END ==== -->
        <button class="back-to-top fa fa-chevron-up" ></button>
        <!-- External JavaScripts -->
        <script>
            document.querySelectorAll('.description').forEach(function (element) {
                let maxLength = 50; // Set the character limit
                let text = element.innerText;
                if (text.length > maxLength) {
                    element.innerText = text.substring(0, maxLength) + '...';
                }
            });
        </script>
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
