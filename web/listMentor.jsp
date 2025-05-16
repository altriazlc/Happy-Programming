<%-- 
    Document   : listMentor
    Created on : Oct 6, 2024, 8:11:15 AM
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
        <title>Happy Programing : Mentor List </title>

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
            <!-- Header Top ==== -->
            <jsp:include  page="header.jsp"/>

            <!-- Content -->
            <div class="page-content bg-white">
                <!-- inner page banner -->
                <div class="page-banner ovbl-dark" style="background-image:url(assets/images/banner/banner3.jpg);">
                    <div class="container">
                        <div class="page-banner-entry">
                            <h1 class="text-white">Rate & Comment</h1>
                        </div>
                    </div>
                </div>
                <!-- inner page banner END -->
                <!-- Mentor List Section -->
                <div class="courses-filter">
                    <div class="container">
                        <div style="display: flex;justify-content: center">
                            <c:if test="${not empty requestScope.error}">
                                <div class="alert alert-danger text-center d-inline-block" role="alert" >
                                    ${requestScope.error}
                                </div>
                            </c:if>
                            <c:if test="${not empty requestScope.mess}">
                                <a href="#" class="alert alert-success text-center d-inline-block" role="alert" >
                                    ${requestScope.mess}
                                </a>
                            </c:if>
                        </div>
                        <div class="list-inline">
                            <c:if test="${empty mentorlist}">
                                <div class="alert alert-warning" style="width: 425px">
                                    <strong>You need to complete at least a course before reviewing!</strong>
                                </div>
                            </c:if>
                        </div>
                        <div class="row">
                            <div class="col-lg-9 col-md-8 col-sm-12">
                                <div class="container">
                                    <ul class="list-inline">
                                        <h3>Mentor List</h3>
                                    </ul>
                                </div>
                                <div class="clearfix">
                                    <div class="container"></div>
                                    <ul id="masonry" class="ttr-gallery-listing magnific-image row">
                                        <c:forEach items="${requestScope.mentorlist}" var="m">
                                            <div class="col-md-6 col-lg-4 col-sm-6 m-b30">
                                                <div class="cours-bx">
                                                    <div class="info-bx text-center">
                                                        <div class="action-box">
                                                            <c:forEach items="${requestScope.cvlist}" var="c"> 
                                                                <c:if test="${c.mentorId==m.mentorId}">
                                                                    <img src="getCVimage?id=${c.cvId}" alt="${cv.fullName}" style="width: 100%; height: 250px; object-fit: cover;">
                                                                </div>
                                                                <div style="margin: 15px 0px">
                                                                    <h5>${m.fullName}</h5>
                                                                        <c:forEach items="${requestScope.requestlist}" var="r">
                                                                            <c:if test="${r.requestId==m.requestId}">
                                                                            <span>Title: ${r.title}</span>
                                                                            <br/>
                                                                            <span>Framework: ${r.framework}</span>
                                                                        </c:if>
                                                                    </c:forEach>
                                                                </div>
                                                                <div>                                                                  
                                                                    <c:choose>
                                                                        <c:when test="${ratedMap[m.requestId]}">
                                                                            <div><a href="viewprofilecv?id=${c.mentorId}" class="btn" style="display: flex; align-items: center; justify-content: center;">View Review</a></div>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <div><a href="ratementor?mentorId=${c.mentorId}&requestId=${m.requestId}" class="btn" style="display: flex; align-items: center; justify-content: center;">Review Mentor</a></div>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </div>
                                                            </div>
                                                        </c:if>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </c:forEach>                                      
                                    </ul>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Mentor List Section END-->
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
