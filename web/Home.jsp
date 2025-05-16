
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
    Document   : Home
    Created on : 11 thg 9, 2024, 10:41:32
    Author     : ADMIN
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
        <title>Happy Programing </title>

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

        <!-- REVOLUTION SLIDER CSS ============================================= -->
        <link rel="stylesheet" type="text/css" href="assets/vendors/revolution/css/layers.css">
        <link rel="stylesheet" type="text/css" href="assets/vendors/revolution/css/settings.css">
        <link rel="stylesheet" type="text/css" href="assets/vendors/revolution/css/navigation.css">
        <!-- REVOLUTION SLIDER END -->	
    </head>
    <body id="bg">
        <div class="page-wraper">
            <div id="loading-icon-bx"></div>
            <!-- Header Top ==== -->
            <header class="header rs-nav">
                <jsp:include page="header.jsp" />
            </header>

            <!-- Header Top END ==== -->
            <!-- Content -->
            <div class="page-content bg-white" style="overflow: visible;">
                <!-- Main Slider -->
                <div class="section-area section-sp1 ovbl-dark bg-fix online-cours">
                    <video autoplay muted loop style="position: absolute;top: 50%;
                           left: 50%;
                           width: 100%;
                           height: 100%;object-fit: cover;transform: translate(-50%, -50%);">
                        <source src="https://www.harvard.edu/wp-content/uploads/2024/08/215303_small.mp4" type="video/mp4">
                    </video>

                    <div class="container">
                        <div class="row">
                            <div class="col-md-12 text-center text-white">
                                <h2>Find a Hiring mentor</h2>
                                <h5>Struggling to mastering Hiring on your own?</h5>
                                <form class="cours-search" action="searchskill">
                                    <div class="input-group">
                                        <input type="text" class="form-control" placeholder="What do you want to learn today?	" name="skill">
                                        <div class="input-group-append">
                                            <button class="btn" type="submit">Search</button> 
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="mw800 m-auto">
                            <div class="row">
                                <div class="col-md-4 col-sm-6">
                                    <div class="cours-search-bx m-b30">
                                        <div class="icon-box">
                                            <h3><i class="ti-user"></i><span class="counter">${requestScope.userNum}</span>+</h3>
                                        </div>
                                        <span class="cours-search-text">Clients</span>
                                    </div>
                                </div>
                                <div class="col-md-4 col-sm-6">
                                    <div class="cours-search-bx m-b30">
                                        <div class="icon-box">
                                            <h3><i class="ti-book"></i><span class="counter">${requestScope.skillCount}</span>+</h3>
                                        </div>
                                        <span class="cours-search-text">Courses</span>
                                    </div>
                                </div>
                                <div class="col-md-4 col-sm-12">
                                    <div class="cours-search-bx m-b30">
                                        <div class="icon-box">
                                            <h3><i class="ti-layout-list-post"></i><span class="counter">${Math.round(requestScope.rateAve/5*100)}</span>%</h3>
                                        </div>
                                        <span class="cours-search-text">Satisfaction </span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Main Slider -->
                <div class="content-block">
                    <!-- Popular Courses -->
                    <div class="section-area section-sp2 popular-courses-bx">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-8 heading-bx left">
                                    <h2 class="title-head">Top <span>Course</span></h2>
                                    <p>Get mentored by industry-leading Hiring experts to mentor you towards your Hiring skill goals.</p>
                                </div>
                                <div class="col-md-4 feature-filters style1 ml-auto" style="text-align: center;align-self: end">
                                    <ul class="filters" data-toggle="buttons">
                                        <li data-filter="toprate" class="btn active">
                                            <input type="radio">
                                            <a href="#"><span>Top Rate</span></a> 
                                        </li>
                                        <li data-filter="publish" class="btn">
                                            <input type="radio">
                                            <a href="#"><span>Newest</span></a> 
                                        </li>
                                        <li data-filter="pending" class="btn">
                                            <input type="radio">
                                            <a href="#"><span>Most Learn</span></a> 
                                        </li>
                                    </ul>
                                </div>
                            </div>


                            <div class="courses-filter">
                                <div class="clearfix">
                                    <ul id="masonry" class="ttr-gallery-listing magnific-image row ">
                                        <c:forEach items="${requestScope.skill}" var="s"  >


                                            <li class="action-card col-xl-3 col-lg-6 col-md-12 col-sm-6 toprate">
                                                <div class="cours-bx">
                                                    <div class="action-box">
                                                        <img src="data:image/jpeg;base64,${s.base64ImageFile}" alt="" style="height: 300px;object-fit: cover;object-position: left;">
                                                        <a href="skilldetail?id=${s.skillId}&name=${s.skillName}" class="btn">Read More</a>
                                                    </div>
                                                    <div class="info-bx text-center" style="height: 130px">
                                                        <h5><a href="#" "
                                                               >${s.skillName}</a></h5>
                                                        <span class="description" style="display: -webkit-box; -webkit-line-clamp: 3; -webkit-box-orient: vertical; overflow: hidden; text-overflow: ellipsis; max-height: 4.5em;  line-height: 1.5em; height: 4.5em;">
                                                            ${s.description}</span>
                                                    </div>

                                                </div>
                                            </li>
                                        </c:forEach>
                                        <c:forEach items="${requestScope.skillListNewest}" var="s"  >


                                            <li class="action-card col-xl-3 col-lg-6 col-md-12 col-sm-6 publish">
                                                <div class="cours-bx">
                                                    <div class="action-box">
                                                        <img src="data:image/jpeg;base64,${s.base64ImageFile}" alt="" style="height: 300px;object-fit: cover;object-position: left;">
                                                        <a href="skilldetail?id=${s.skillId}&name=${s.skillName}" class="btn">Read More</a>
                                                    </div>
                                                    <div class="info-bx text-center" style="height: 140px">
                                                        <h5><a href="#">${s.skillName}</a></h5>
                                                        <span class="description" style="display: -webkit-box; -webkit-line-clamp: 3; -webkit-box-orient: vertical; overflow: hidden; text-overflow: ellipsis; max-height: 4.5em;  line-height: 1.5em; height: 4.5em;">
                                                            ${s.description}</span>
                                                    </div>

                                                </div>
                                            </li>
                                        </c:forEach>
                                        <c:forEach items="${requestScope.skillListMostLearn}" var="s"  >


                                            <li class="action-card col-xl-3 col-lg-6 col-md-12 col-sm-6 pending">
                                                <div class="cours-bx">
                                                    <div class="action-box">
                                                        <img src="data:image/jpeg;base64,${s.base64ImageFile}" alt="" style="height: 300px;object-fit: cover;object-position: left;">
                                                        <a href="skilldetail?id=${s.skillId}&name=${s.skillName}" class="btn">Read More</a>
                                                    </div>
                                                    <div class="info-bx text-center" style="height: 140px">
                                                        <h5><a href="#">${s.skillName}</a></h5>
                                                        <span class="description" style="display: -webkit-box; -webkit-line-clamp: 3; -webkit-box-orient: vertical; overflow: hidden; text-overflow: ellipsis; max-height: 4.5em;  line-height: 1.5em; height: 4.5em;">
                                                            ${s.description}</span>
                                                    </div>

                                                </div>
                                            </li>
                                        </c:forEach>




                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Popular Courses END -->
                    <div class="section-area section-sp2 bg-fix ovbl-dark join-bx text-center" style="background-image:url(https://daihoc.fpt.edu.vn/templates/fpt-university/images/header.jpg);">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="join-content-bx text-white">
                                        <h2>At your fingertips: <br>  dedicated career coach</h2>
                                        <h4><span class="counter">${requestScope.mentorNum}</span>+ mentors available</h4>
                                        <p>Want to start a new dream career? Successfully build your startup? Itching to learn high-demand skills? Work smart with an online mentor by your side to offer expert advice and guidance to match your zeal. Become unstoppable using HappyPrograming.</p>
                                        <a href="skillhome" class="btn button-md">Join Now</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Form END -->
                    <div class="section-area section-sp1">
                        <div class="container">
                            <div class="row">
                                <div class="col-lg-6 m-b30">
                                    <h2 class="title-head ">Achieve your goals with <br><span class="text-primary"> the help of a Hiring mentor</span></h2>
                                    <h4><span class="counter">${requestScope.menteeCount}</span>+ Mentee</h4>
                                    <p>Reaching new heights is easier when you do so with some help from qualified mentors. Know everything that there is to know about one of the fastest-growing languages out there today.</p>
                                    <a href="skillhome" class="btn button-md">Join Now</a>
                                </div>
                                <div class="col-lg-6">
                                    <div class="row">
                                        <div class="col-lg-6 col-md-6 col-sm-6 m-b30">
                                            <div class="feature-container">
                                                <div class="feature-md text-white m-b20">
                                                    <a href="#" class="icon-cell"><img src="assets/images/icon/icon1.png" alt=""></a> 
                                                </div>
                                                <div class="icon-content">
                                                    <h5 class="ttr-tilte">Learn under a team of mentors</h5>
                                                    <p>Why learn from 1 mentor when you can learn from 2? Sharpen your Hiring skills with the guidance of multiple mentors.</p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-lg-6 col-md-6 col-sm-6 m-b30">
                                            <div class="feature-container">
                                                <div class="feature-md text-white m-b20">
                                                    <a href="#" class="icon-cell"><img src="assets/images/icon/icon2.png" alt=""></a> 
                                                </div>
                                                <div class="icon-content">
                                                    <h5 class="ttr-tilte">Hand-picked online Hiring mentors</h5>
                                                    <p>Get pros to make you a pro. We mandate the highest standards for competency and communication.</p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-lg-6 col-md-6 col-sm-6 m-b30">
                                            <div class="feature-container">
                                                <div class="feature-md text-white m-b20">
                                                    <a href="#" class="icon-cell"><img src="assets/images/icon/icon3.png" alt=""></a> 
                                                </div>
                                                <div class="icon-content">
                                                    <h5 class="ttr-tilte">Real Hiring industry experience</h5>
                                                    <p>Master Hiring, no fluff. Only expert advice to help you hone your skills.</p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-lg-6 col-md-6 col-sm-6 m-b30">
                                            <div class="feature-container">
                                                <div class="feature-md text-white m-b20">
                                                    <a href="#" class="icon-cell"><img src="assets/images/icon/icon4.png" alt=""></a> 
                                                </div>
                                                <div class="icon-content">
                                                    <h5 class="ttr-tilte">Cancel anytime</h5>
                                                    <p>Hiring expert by retaining the ability to end, pause, and continue your mentorship subscription as you please.</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Testimonials -->
                    <div class="section-area section-sp1 bg-fix ovbl-dark text-white" style="background-image:url(https://daihoc.fpt.edu.vn/templates/fpt-university/images/header.jpg);">
                        <div class="container">
                            <div class="row">
                                <div class="col-lg-3 col-md-6 col-sm-6 col-6 m-b30">
                                    <div class="counter-style-1">
                                        <div class="text-white">
                                            <span class="counter">${requestScope.completeRequestCount}</span><span>+</span>
                                        </div>
                                        <span class="counter-text">Completed Request</span>
                                    </div>
                                </div>
                                <div class="col-lg-3 col-md-6 col-sm-6 col-6 m-b30">
                                    <div class="counter-style-1">
                                        <div class="text-white">
                                            <span class="counter">${requestScope.userNum}</span><span>+</span>
                                        </div>
                                        <span class="counter-text">Happy Clients</span>
                                    </div>
                                </div>
                                <div class="col-lg-3 col-md-6 col-sm-6 col-6 m-b30">
                                    <div class="counter-style-1">
                                        <div class="text-white">
                                            <span class="counter">${requestScope.rateCount}</span><span>+</span>
                                        </div>
                                        <span class="counter-text">Questions Answered</span>
                                    </div>
                                </div>
                                <div class="col-lg-3 col-md-6 col-sm-6 col-6 m-b30">
                                    <div class="counter-style-1">
                                        <div class="text-white">
                                            <span class="counter">${requestScope.requestCount}</span><span>+</span>
                                        </div>
                                        <span class="counter-text">Request Total</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Testimonials END -->
                    <!-- Testimonials ==== -->

                    <div class="section-area section-sp2">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-12 heading-bx left">
                                    <h2 class="title-head text-uppercase">what people <span>say</span></h2>
                                    <p>We’ve already delivered 1-on-1 mentorship to thousands of students, professionals, managers and executives</p>
                                </div>
                            </div>
                            <div class="testimonial-carousel owl-carousel owl-btn-1 col-12 p-lr0">
                                <c:if test="${not empty requestScope.rate}">
                                    <c:forEach items="${requestScope.rate}" var="r">

                                        <a href="viewprofilecv?id=${r.mentorId}">
                                            <div class="item">

                                                <div class="testimonial-bx">
                                                    <div class="testimonial-thumb">
                                                        <c:forEach items="${requestScope.menteeList}" var="m">
                                                            <c:if test="${r.menteeId==m.menteeId}">

                                                                <c:if test="${m.base64FileImage!=null}">
                                                                    <img src="data:image/jpeg;base64,${m.base64FileImage}" alt="" style="width: 75px; height: 75px; object-fit: cover; border-radius: 50%;">
                                                                </c:if>
                                                                <c:if test="${m.base64FileImage==null}">
                                                                    <img src="assets/images/userprofile.png" alt="">
                                                                </c:if>

                                                            </c:if>
                                                        </c:forEach>
                                                    </div>
                                                    <div class="testimonial-info">
                                                        <c:forEach items="${requestScope.menteeList}" var="c">
                                                            <c:if test="${c.menteeId==r.menteeId}">

                                                                <h5 class="name">${c.username}</h5>
                                                            </c:if>
                                                        </c:forEach>
                                                        <c:forEach items="${requestScope.mentorList}" var="c">
                                                            <c:if test="${c.mentorId==r.mentorId}">

                                                                <p>Rating: ${c.username}</p>
                                                            </c:if>
                                                        </c:forEach>
                                                        <ul class="cours-star">
                                                            <c:forEach var="i" begin="1" end="${r.rate}">
                                                                <li class="active"><i class="fa fa-star"></i></li>
                                                                </c:forEach>
                                                                <c:forEach var="j" begin="1" end="${5-r.rate}">
                                                                <li><i class="fa fa-star"></i></li>
                                                                </c:forEach>

                                                        </ul>
                                                    </div>
                                                    <div class="testimonial-content" style="height: 130px">
                                                        <p>${r.comment}</p>
                                                    </div>
                                                </div>
                                            </div>
                                        </a>
                                    </c:forEach>
                                </c:if>
                            </div>
                        </div>
                    </div>

                    <!-- Testimonials END ==== -->
                </div>
                <!-- contact area END -->
            </div>
            <!-- Content END-->
            <!-- Footer ==== -->
            <jsp:include page="footer.jsp" />
            <!-- Footer END ==== -->
            <button class="back-to-top fa fa-chevron-up"  ></button>
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
        <script src="assets/vendors/masonry/filter.js"></script>
        <script src="assets/js/functions.js"></script>
        <script src="assets/js/contact.js"></script>
        <!-- comment <script src='assets/vendors/switcher/switcher.js'></script>-->
    </body>
    <script>
        $(document).ready(function () {
            // Kích hoạt masonry cho phần tử có id 'masonry'
            var $masonryContainer = $('#masonry').masonry({
                itemSelector: '.action-card',
                percentPosition: true,
                columnWidth: '.action-card'
            });

            // Thiết lập bộ lọc ban đầu (toprate)
            $masonryContainer.masonryFilter({
                filter: function () {
                    return $(this).hasClass('toprate');
                }
            });

            // Xử lý khi người dùng nhấn vào các bộ lọc
            $('.filters li').on('click', function () {
                // Xóa class 'active' và thêm cho phần tử hiện tại
                $('.filters li').removeClass('active');
                $(this).addClass('active');

                // Lấy giá trị filter từ 'data-filter'
                var filterValue = $(this).data('filter');

                // Cập nhật bộ lọc cho Masonry
                $masonryContainer.masonryFilter({
                    filter: function () {
                        return $(this).hasClass(filterValue);
                    }
                });
            });
        });

    </script>
    <script>
        window.embeddedChatbotConfig = {
            chatbotId: "u5Jo3ydYkfiHuR6MaNT93",
            domain: "www.chatbase.co",
        }
    </script>
    <script
        src="https://www.chatbase.co/embed.min.js"
        chatbotId="u5Jo3ydYkfiHuR6MaNT93"
        domain="www.chatbase.co"
        defer>
    </script>

</html>


