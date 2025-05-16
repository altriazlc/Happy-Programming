<%-- 
    Document   : SkillDetail
    Created on : Oct 1, 2024, 3:13:51 PM
    Author     : nhhag
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
        <title>Skill Detail </title>

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
            .instructor-bx {
                display: flex;
                flex-direction: column;
                justify-content: space-between;
                height: 100%; /* Ensure cards stretch to fill the space */
                border: 1px solid #ddd; /* Optional: add a border for clarity */
                border-radius: 5px; /* Optional: round the corners */
                padding: 15px; /* Add some padding */
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); /* Optional: shadow for depth */
            }

            .instructor-author img {
                max-height: 150px; /* Set a max height for images */
                object-fit: cover; /* Ensure images maintain their aspect ratio */
                width: 100%; /* Ensure images fill the container */
                border-radius: 5px; /* Round the corners of the image */
            }

            .instructor-info {
                flex-grow: 1; /* Allow info section to grow and take available space */
            }

            .instructor-info h6 {
                margin-top: 10px; /* Add some margin to the title */
                margin-bottom: 10px; /* Add some margin to the title */
            }

            .instructor-info p {
                margin-bottom: 5px; /* Reduce spacing between paragraphs */
            }

            .btn {
                margin-right: 5px; /* Space between buttons */

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
                <div class="page-banner ovbl-dark" style="background-image:url(assets/images/banner/banner2.jpg);">
                    <div class="container">
                        <div class="page-banner-entry">
                            <h1 class="text-white">Skill Details</h1>
                        </div>
                    </div>
                </div>


                <div class="section-area section-sp1">
                    <div class="container">
                        <div class="row d-flex flex-row-reverse">


                            <div class="col-lg-12 col-md-8 col-sm-12">
                                <div class="courses-post">
                                    <div class="ttr-post-media media-effect">
                                        <a href="#"><img src="data:image/jpeg;base64,${detail.base64ImageFile}" alt="" ></a>
                                    </div>
                                    <div class="ttr-post-info">
                                        <div class="ttr-post-title ">
                                            <h2 class="post-title">${detail.skillName}</h2>
                                        </div>
                                        <div class="ttr-post-text">
                                            <p>${detail.description}</p>
                                        </div>
                                    </div>
                                </div>



                                <div class="" id="instructor">
                                    <h4>Mentor</h4>
                                    <c:if test="${empty mentor}">
                                        <p style="color : red">There are no mentors available with this skill</p>
                                    </c:if>

                                    <div id="mentorCarousel" class="carousel slide" data-ride="carousel">
                                        <div class="carousel-inner" >

                                            <c:forEach items="${requestScope.mentor}" var="mentors" varStatus="loop">

                                                <c:forEach items="${requestScope.cv}" var="cvs">
                                                    <c:if test="${mentors.mentorId == cvs.mentorId}">
                                                        <c:if test="${loop.index % 2 == 0}">
                                                            <div class="carousel-item ${loop.index == 0 ? 'active' : ''}" >
                                                                <div class="row">
                                                                </c:if>
                                                                <div class="col-md-6">
                                                                    <div class="instructor-bx " >
                                                                        <div class="instructor-author ">
                                                                            <img src="getCVimage?id=${cvs.cvId}" alt="mentor image" class="d-block w-100" style="width: 100%; height: 100%; object-fit: cover;">
                                                                        </div>
                                                                        <div class="instructor-info">
                                                                            <h6>${mentors.fullName}</h6>
                                                                            <p class="m-b0">Education: ${cvs.education}</p>
                                                                            <p class="m-b0">Description: ${cvs.professionIntroduction}</p>
                                                                            <p class="m-b0">Year of experience: ${cvs.yearOfExperience} years</p>
                                                                            <p class="m-b0">Price: <fmt:formatNumber value="${cvs.price}" type="number" maxFractionDigits="2" />₫ VND</p>
                                                                            <div class="instructor-info" style="margin-left: 60px">
                                                                                <a href="viewprofilecv?id=${mentors.mentorId}" class="btn green radius-xl outline">View CV</a>
                                                                                <c:if test="${acc.roleId != 1}">  
                                                                                    <a href="createrequest?id=${mentors.mentorId}" class="btn red outline radius-xl">Rent mentor</a>
                                                                                </c:if>

                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <c:if test="${loop.index % 2 == 1 || loop.index == mentor.size() - 1}">
                                                                </div> 
                                                            </div> 
                                                        </c:if>
                                                    </c:if>
                                                </c:forEach>
                                            </c:forEach>
                                        </div>
                                        <div class="custom-carousel-controls">
                                            <a class="carousel-control-prev" href="#mentorCarousel" role="button" data-slide="prev">
                                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                                <span class="sr-only">Previous</span>
                                            </a>
                                            <a class="carousel-control-next" href="#mentorCarousel" role="button" data-slide="next">
                                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                                <span class="sr-only">Next</span>
                                            </a>
                                        </div>


                                    </div>
                                </div>                   



                                <div id="instructor">
                                    <h5 class="widget-title style-1">Other Skills</h5>
                                    <div id="recentCoursesCarousel" class="carousel slide" data-ride="carousel">
                                        <div class="carousel-inner">
                                            <c:forEach items="${skill}" var="list2" varStatus="status">
                                                <!-- Start a new carousel item for every 4 items -->
                                                <c:if test="${status.index % 4 == 0}">
                                                    <div class="carousel-item ${status.index == 0 ? 'active' : ''}">
                                                        <div class="row">
                                                        </c:if>

                                                        <div class="col-6 col-md-3">
                                                            <div class="widget-post clearfix">
                                                                <div class="ttr-post-media">
                                                                    <a href="skilldetail?id=${list2.skillId}&name=${list2.skillName}">
                                                                        <img src="data:image/jpeg;base64,${list2.base64ImageFile}" style="width: 200px; height: 200px" alt="${list2.skillName}">
                                                                    </a>
                                                                </div>
                                                                <div class="ttr-post-info">
                                                                    <div class="ttr-post-header">
                                                                        <h6 class="post-title">
                                                                            <a href="skilldetail?id=${list2.skillId}&name=${list2.skillName}">${list2.skillName}</a>
                                                                        </h6>
                                                                    </div>
                                                                    <div class="ttr-post-meta">
                                                                        <div style="display: flex; align-items: center;">
                                                                            <a href="skilldetail?id=${list2.skillId}&name=${list2.skillName}" class="btn-outline-primary btn-success">Read More</a>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <!-- Close the carousel item after every 4 items or if it's the last item -->
                                                        <c:if test="${(status.index + 1) % 4 == 0 || status.index == skill.size() - 1}">
                                                        </div> <!-- Close row -->
                                                    </div> <!-- Close carousel-item -->
                                                </c:if>
                                            </c:forEach>
                                        </div> <!-- Close carousel-inner -->

                                        <div class="custom-carousel-controls">
                                            <a class="carousel-control-prev" href="#recentCoursesCarousel" role="button" data-slide="prev">
                                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                                <span class="sr-only">Previous</span>
                                            </a>
                                            <a class="carousel-control-next" href="#recentCoursesCarousel" role="button" data-slide="next">
                                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                                <span class="sr-only">Next</span>
                                            </a>
                                        </div>
                                    </div>
                                </div>


                            </div>

                        </div>
                    </div>
                </div>
            </div>
            <!-- contact area END -->

        </div>
        <!-- Content END-->
        <!-- Footer ==== -->
        <jsp:include page="footer.jsp"/>
        <!-- Footer END ==== -->
        <button class="back-to-top fa fa-chevron-up" ></button>
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
    <script src="assets/js/jquery.scroller.js"></script>
    <script src="assets/js/functions.js"></script>
    <script src="assets/js/contact.js"></script>

</body>

</html>

