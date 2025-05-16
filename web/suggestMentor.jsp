<%-- 
    Document   : suggestMentor
    Created on : Oct 15, 2024, 2:28:59 PM
    Author     : nhhag
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">

    <!-- Mirrored from educhamp.themetrades.com/demo/admin/review.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 22 Feb 2019 13:11:35 GMT -->
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
        <title>Suggest mentor</title>

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
        <link rel="stylesheet" type="text/css" href="assets/css/dashboard.css">
        <link class="skin" rel="stylesheet" type="text/css" href="assets/css/color/color-1.css">
        <!-- REVOLUTION SLIDER CSS ============================================= -->
        <link rel="stylesheet" type="text/css" href="assets/vendors/revolution/css/layers.css">
        <link rel="stylesheet" type="text/css" href="assets/vendors/revolution/css/settings.css">
        <link rel="stylesheet" type="text/css" href="assets/vendors/revolution/css/navigation.css">
        <!-- REVOLUTION SLIDER END -->	

    </head>
    <body class="ttr-opened-sidebar ttr-pinned-sidebar">

        <!-- header start -->
        <header class="header rs-nav" >
            <jsp:include page="header.jsp" />
        </header >
        <!-- header end -->
        <!-- Left sidebar menu start -->
        <div class="ttr-sidebar" style="margin-top: 100px; margin-left: 10px; padding: 20px; background-color: #f9f9f9; border-radius: 8px; box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);">
            <h4 style="color: #333; margin-bottom: 20px;">Filter & Sort Mentors</h4>
            <form action="suggestMentor" method="GET" style="margin-left: 10px;">
                <!-- Filter by Experience -->
                <div class="form-group" style="margin-bottom: 15px;">
                    <label for="experience" style="font-weight: bold; color: #555;">Experience</label>
                    <select id="experience" name="experience" class="form-control" style="width: 100%; padding: 10px; border-radius: 4px; border: 1px solid #ccc;">
                        <option value="">Select Experience</option>
                        <option value="1">1-2 years</option>
                        <option value="2">3-5 years</option>
                        <option value="3">5+ years</option>
                    </select>
                </div>

                <!-- Filter by Price Range -->
                <div class="form-group" style="margin-bottom: 15px;">
                    <label for="priceRange" style="font-weight: bold; color: #555;">Price Range</label>
                    <select id="priceRange" name="priceRange" class="form-control" style="width: 100%; padding: 10px; border-radius: 4px; border: 1px solid #ccc;">
                        <option value="">Select Price Range</option>
                        <option value="1">0-500,000 VND</option>
                        <option value="2">500,000-1,000,000 VND</option>
                        <option value="3">1,000,000+ VND</option>
                    </select>
                </div>

                <!-- Sort Options -->
                <div class="form-group" style="margin-bottom: 15px;">
                    <label for="sortBy" style="font-weight: bold; color: #555;">Sort By</label>
                    <select id="sortBy" name="sortBy" class="form-control" style="width: 100%; padding: 10px; border-radius: 4px; border: 1px solid #ccc;">
                        <option value="">Select Sort Option</option>
                        <option value="ratingAsc">Rating (Ascending)</option>
                        <option value="ratingDesc">Rating (Descending)</option>
                        <option value="priceAsc">Price (Ascending)</option>
                        <option value="priceDesc">Price (Descending)</option>
                    </select>
                </div>

                <!-- Submit Button --> 
                <button type="submit" class="btn green radius" >
                    Filter
                </button>

                <a href="suggestMentor" style="text-decoration: none;">
                    <button type="button" class="btn red radius">
                        Show All
                    </button>
                </a>
            </form>
        </div>




        <!-- Left sidebar menu end -->
        <!--Main container start -->
        <main class="ttr-wrapper">
            <div class="container-fluid">
                <div class="db-breadcrumb">
                    <h4 class="breadcrumb-title">Suggest mentor</h4>
                </div>	
                <div class="row">
                    <!-- Your Profile Views Chart -->
                    <div class="col-lg-12 m-b30">
                        <div class="widget-box">
                            <div class="wc-title">
                                <h4>List suggest mentor</h4>
                                <h4 style="color: red">${error}</h4>
                                
                            </div>
                            <div class="widget-inner">
                                <c:forEach items="${mentorList}" var="mentors">
                                    <div class="card-courses-list admin-review" style="border: 2px solid #ddd; border-radius: 8px; padding: 15px; margin-bottom: 20px;">
                                        <div class="card-courses-full-dec">
                                            <div class="card-courses-list-bx">
                                                <ul class="card-courses-view">

                                                    <!-- Mentor Profile Image and Name -->
                                                    <li class="card-courses-user">
                                                        <div class="card-courses-user-pic">
                                                            <img src="getCVimage?id=${mentors.cv.cvId}" style="width: 100%;height: 100%;object-fit: cover;" alt="Mentor Picture" />
                                                        </div>
                                                        <div class="card-courses-user-info">
                                                            <h5>Mentor Name</h5>
                                                            <h4>${mentors.mentor.fullName}</h4>
                                                        </div>
                                                    </li>

                                                    <!-- Mentor Rating and Reviews -->
                                                    <li class="card-courses-review">
                                                        <h5>${mentors.rate} Reviews</h5>
                                                        <ul class="cours-star">
                                                            <!-- Display filled stars based on rating -->
                                                            <c:forEach begin="1" end="${mentors.rating}" var="i">
                                                                <li class="active">
                                                                    <i class="fa fa-star"></i>
                                                                </li>
                                                            </c:forEach>
                                                            <!-- Display empty stars for the remaining -->
                                                            <c:forEach begin="1" end="${5 - mentors.rating}" var="i">
                                                                <li>
                                                                    <i class="fa fa-star"></i>
                                                                </li>
                                                            </c:forEach>
                                                        </ul>
                                                    </li>

                                                    <!-- Total Requests -->
                                                    <li class="card-courses-categories">
                                                        <h5>Total Requests</h5>
                                                        <h4>${mentors.numReq} requests</h4>
                                                    </li>
                                                    <li class="card-courses-categories">
                                                        <h5>Price</h5>
                                                        <h4><fmt:formatNumber value="${mentors.cv.price}" type="number" maxFractionDigits="2" /> VND</h4>
                                                    </li>

                                                    <!-- Mentor Account Username -->
                                                    <li class="card-courses-categories">
                                                        <h5>Years of experience</h5>
                                                        <h4>${mentors.cv.yearOfExperience} years</h4>
                                                    </li>
                                                </ul>
                                            </div>

                                            <!-- Mentor's CV Details Section -->
                                          <div class="col-md-12 mt-3">
                                                <h6 class="text-dark mb-2">Mentor skills</h6>
                                                <div class="row">
                                                    <c:forEach items="${mentors.skills}" var="skill">
                                                        <a href="skilldetail?id=${skill.skillId}" class="btn green radius-xl outline">
                                                            ${skill.skillName}
                                                        </a>
                                                    </c:forEach>
                                                </div>
                                            </div>


                                            <div class="card-body">   
                                                <div class="col-md-12 mt-3">
                                                    <a href="viewprofilecv?id=${mentors.mentor.mentorId}" class="btn green radius">
                                                        View CV
                                                    </a>
                                                    <a href="createrequest?id=${mentors.mentor.mentorId}" class="btn red  radius">
                                                        Rent Mentor
                                                    </a>
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                </c:forEach>
                            </div>



                        </div>
                    </div>
                    <div class="modal fade review-bx-reply" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Reply to review</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <textarea class="form-control" placeholder="Type text"></textarea>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn mr-auto">Reply</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Your Profile Views Chart END-->
      
</main>
<div class="ttr-overlay"></div>

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
        <script src="assets/vendors/masonry/filter.js"></script>
        <script src="assets/js/functions.js"></script>
        <script src="assets/js/contact.js"></script>
</body>

<!-- Mirrored from educhamp.themetrades.com/demo/admin/review.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 22 Feb 2019 13:11:35 GMT -->
</html>
