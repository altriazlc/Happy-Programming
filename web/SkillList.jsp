<%-- 
    Document   : SkillList
    Created on : Oct 1, 2024, 3:12:48 PM
    Author     : nhhag
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
        <title>Happy Programing : Our Courses </title>

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

            <!-- Header Top ==== -->
            <header class="header rs-nav">
                <jsp:include page="header.jsp" />
            </header>
            <!-- header END ==== -->
            <!-- Content -->
            <div class="page-content bg-white">
                <!-- inner page banner -->
                <div class="page-banner ovbl-dark" style="background-image:url(https://i0.wp.com/hanoi.fpt.edu.vn/wp-content/uploads/2023/08/bg-why.jpg?fit=1920&ssl=1);">
                    <div class="container">
                        <div class="page-banner-entry">
                            <h1 class="text-white">Our Courses</h1>
                        </div>
                    </div>
                </div>
                <!-- Breadcrumb row -->
                <div class="breadcrumb-row">
                    <div class="container">
                        <ul class="list-inline">
                            <li>${notify}</li>
                        </ul>
                    </div>
                </div>
                <!-- Breadcrumb row END -->
                <!-- inner page banner END -->
                <div class="content-block">
                    <!-- About Us -->
                    <div class="section-area section-sp1">
                        <div class="container">
                            <div class="row">
                                <div class="col-lg-3 col-md-4 col-sm-12 m-b30">
                                    <div class="widget courses-search-bx placeani">
                                        <form action="searchskill">
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <label>Search Courses</label>
                                                    <input name="skill" type="text" class="form-control">
                                                    <button class="btn btn-lights" type="submit">
                                                        Search skill
                                                    </button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="widget recent-posts-entry widget-courses">
                                        <h5 class="widget-title style-1">Recent Courses</h5>
                                        <div class="widget-post-bx">
                                            <c:forEach items="${list2}" var="list2">
                                                <div class="widget-post clearfix">
                                                    <div class="ttr-post-media"> <img src="data:image/jpeg;base64,${list2.base64ImageFile}" width="220" height="160" alt=""> </div>
                                                    <div class="ttr-post-info">
                                                        <div class="ttr-post-header">
                                                            <h6 class="post-title"><a href="skilldetail?id=${list2.skillId}&name=${list2.skillName}">${list2.skillName}</a></h6>
                                                        </div>
                                                        <div class="ttr-post-meta">
                                                            <div style="display: flex; align-items: center;">
                                                                <a href="skilldetail?id=${list2.skillId}&name=${list2.skillName}" class="btn-outline-primary btn-success">Read More</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </div>
                                        <div class="filter-block">
                                            <form action="skillhome" method="get">
                                                <div class="form-group">
                                                    <label for="filterCriteria">Filter By:</label>
                                                    <select id="filterCriteria" name="filter" class="form-control">
                                                        <option value="mostRequests">Most Requests</option>
                                                        <option value="leastRequests">Least Requests</option>
                                                        <option value="mostMentors">Most Mentors</option>
                                                        <option value="leastMentors">Least Mentors</option>
                                                        <option value="newestSkill">Newest Skill</option>
                                                        <option value="oldestSkill">Oldest Skill</option>
                                                        <option value="">Show all</option>
                                                    </select>
                                                </div>
                                                <button class="btn btn-lights" type="submit">Apply Filter</button>
                                            </form>
                                        </div>

                                    </div>
                                </div>
                                <div class="col-lg-9 col-md-8 col-sm-12">
                                    <div class="row">
                                        <c:set var="stt" value="1"/>
                                        <c:forEach items="${list}" var="skill">
                                            <div class="col-md-6 col-lg-4 col-sm-6 m-b30" >
                                                <div class="cours-bx">
                                                    <div class="action-box">
                                                        <img src="data:image/jpeg;base64,${skill.base64ImageFile}" alt="${skill.skillName}" style="width: 272px; height: 200px" >

                                                    </div>
                                                    <div class="info-bx text-center">
                                                        <h5><a href="skilldetail?id=${skill.skillId}&name=${skill.skillName}">
                                                                ${((pageIndex - 1) * 9) + stt}. ${skill.skillName}
                                                            </a></h5>
                                                            <c:set var="stt" value="${stt + 1}"/>
                                                        <span class="description" style=" display: -webkit-box; -webkit-line-clamp: 3; -webkit-box-orient: vertical; overflow: hidden; text-overflow: ellipsis; max-height: 4.5em;  line-height: 1.5em; height: 4.5em; ">
                                                            ${skill.description}
                                                        </span>
                                                    </div>
                                                    <div class="cours-more-info">
                                                        <div class="review">
                                                            <span style="text-transform: capitalize">Number mentor</span>
                                                            <span>${number[stt - 2]}</span>
                                                        </div>
                                                        <div style="display: flex; align-items: center; padding-left: 10px">
                                                            <a href="skilldetail?id=${skill.skillId}&name=${skill.skillName}" class="btn">Read More</a>
                                                        </div>
                                                    </div>
                                                </div>     
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>


                                <div class="col-lg-12 m-b20">
                                    <div class="pagination-bx rounded-sm gray clearfix">
                                        <ul class="pagination">
                                            <li class="page-item ${pageIndex == 1 ? 'disabled' : ''}">
                                                <a href="?index=${pageIndex - 1}" class="page-link">Previous</a>
                                            </li>
                                            <c:forEach begin="1" end="${endP}" var="i">
                                                <li class="page-item ${pageIndex == i ? 'active' : ''}">
                                                    <c:if test="${url != null}">
                                                        <a href="${url}&index=${i}" class="page-link">${i}</a>
                                                    </c:if>
                                                    <c:if test="${url == null}">
                                                        <a href="skillhome?index=${i}" class="page-link">${i}</a>
                                                    </c:if>

                                                </li>
                                            </c:forEach>
                                            <li class="page-item ${pageIndex == endP ? 'disabled' : ''}">
                                                <a href="skillhome?index=${pageIndex + 1}" class="page-link">Next</a>
                                            </li>
                                        </ul>
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
