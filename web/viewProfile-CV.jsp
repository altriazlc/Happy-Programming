<%-- 
    Document   : viewProfile-CV
    Created on : 2 thg 10, 2024, 01:58:37
    Author     : ADMIN
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
        <title>Happy Programing: CV of Mentor</title>

        <!-- MOBILE SPECIFIC ============================================= -->
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!--[if lt IE 9]>
        <script src="assets/js/html5shiv.min.js"></script>
        <script src="assets/js/respond.min.js"></script>
        <![endif]-->

        <!-- All PLUGINS CSS ============================================= -->
        <link rel="stylesheet" type="text/css" href="assets/css/assets.css">
        <link rel="stylesheet" type="text/css" href="assets/vendors/calendar/fullcalendar.css">
        <!-- TYPOGRAPHY ============================================= -->
        <link rel="stylesheet" type="text/css" href="assets/css/typography.css">

        <!-- SHORTCODES ============================================= -->
        <link rel="stylesheet" type="text/css" href="assets/css/shortcodes/shortcodes.css">

        <!-- STYLESHEETS ============================================= -->
        <link rel="stylesheet" type="text/css" href="assets/css/style.css">
        <link class="skin" rel="stylesheet" type="text/css" href="assets/css/color/color-1.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

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
                <div class="page-banner ovbl-dark" style="background-image:url(https://daihoc.fpt.edu.vn/templates/fpt-university/images/header.jpg);">
                    <div class="container">
                        <div class="page-banner-entry">
                            <h1 class="text-white">Profile</h1>
                        </div>
                    </div>
                </div>
                <!-- Breadcrumb row -->
                <div class="breadcrumb-row">
                    <div class="container">
                        <ul class="list-inline">
                            <li><a href="#">Home</a></li>
                            <li>Profile</li>
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
                                    <div class="profile-bx text-center">
                                        <div class="user-profile-thumb">
                                            <c:if test="${requestScope.cvFound.cvId==null}">
                                                <img src="assets\images\userprofile.png" class="rounded-circle" alt="" style=" width: 100%;height: 100%;object-fit: cover;"/>
                                            </c:if>
                                            <c:if test="${requestScope.cvFound.cvId!=null}">
                                                <img src="getCVimage?id=${requestScope.cvFound.cvId}" class="rounded-circle" alt="" style=" width: 100%;height: 100%;object-fit: cover;"/>
                                            </c:if>
                                        </div>
                                        <div class="profile-info">

                                            <h4>${requestScope.uFound.username}</h4>
                                            <span>${requestScope.uFound.fullName}</span>
                                            <br>
                                            <ul class="cours-star">
                                                <c:forEach var="i" begin="1" end="${requestScope.rateAve}">
                                                    <li class="active"><i class="fa fa-star"></i></li>
                                                    </c:forEach>
                                                    <c:forEach var="j" begin="1" end="${5-(requestScope.rateAve)}">
                                                    <li><i class="fa fa-star"></i></li>
                                                    </c:forEach>

                                            </ul>
                                        </div>
                                        <div class="profile-social">
                                            <ul class="list-inline m-a0">
                                                <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                                                <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                                                <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
                                                <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                                            </ul>
                                        </div>
                                        <div class="profile-tabnav">
                                            <ul class="nav nav-tabs">
                                                <li class="nav-item">
                                                    <a class="nav-link active" data-toggle="tab" href="#edit-profile"><i class="ti-pencil-alt"></i>Profile</a>
                                                </li>

                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-9 col-md-8 col-sm-12 m-b30">
                                    <div class="profile-content-bx">
                                        <div class="tab-content">

                                            <div class="tab-pane active" id="edit-profile">
                                                <div class="profile-head">
                                                    <h3>Profile</h3>
                                                </div>
                                                <form class="edit-profile">
                                                    <div class="">
                                                        <div class="form-group row">
                                                        </div>
                                                        <div class="form-group row">
                                                            <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label font-weight-bold">Full Name:</label>
                                                            <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                                <p class="font-weight-normal" style="margin-bottom: 0px">${requestScope.uFound.fullName}</p>
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label font-weight-bold">Account:</label>
                                                            <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                                <p class="font-weight-normal" style="margin-bottom: 0px">${requestScope.uFound.username}</p>
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label font-weight-bold">Job:</label>
                                                            <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                                <p class="font-weight-normal" style="margin-bottom: 0px">${requestScope.cvFound.jobProfession}</p>
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label font-weight-bold">Introduction:</label>
                                                            <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                                <p class="font-weight-normal" style="margin-bottom: 0px">${requestScope.cvFound.professionIntroduction}</p>
                                                            </div>
                                                        </div>


                                                        <div class="seperator"></div>


                                                        <div class="form-group row">
                                                            <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label font-weight-bold">Service:</label>
                                                            <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                                <p class="font-weight-normal" style="margin-bottom: 0px">${requestScope.cvFound.serviceDescription}</p>
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label font-weight-bold">Achievement:</label>
                                                            <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                                <p class="font-weight-normal" style="margin-bottom: 0px">${requestScope.cvFound.experience}</p>
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <label class="col-12 col-sm-12 col-md-12 col-lg-2 col-form-label font-weight-bold">Statistic rating of each skill:</label>
                                                            <div class="col-12 col-sm-9 col-md-9 col-lg-7" class="table-vertical-scroll table-responsive" style="max-height: 400px; overflow-y: auto;">
                                                                <c:forEach items="${requestScope.skillMentor}" var="c">
                                                                    <label class="col-form-label">
                                                                        <span class="font-weight-normal" style="margin-bottom: 0px">${c.skillName}</span>
                                                                    </label>
                                                                    <div class="progress" style=" height: 8px">
                                                                        <div class="progress-bar" role="progressbar" style="width: ${c.rating/5*100}%;" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
                                                                    </div>
                                                                    <br/>


                                                                </c:forEach>
                                                                <br/>
                                                            </div>

                                                        </div>

                                                    </div>

                                                </form>

                                            </div>

                                        </div> 
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-lg-8 col-md-12 col-sm-12 m-b30" style="margin: 0 auto;">
                    <div class="widget-box">
                        <div class="wc-title">
                            <h4>Avaiable Slot</h4>
                        </div>
                        <div class="widget-inner">
                            <div id="calendar"></div>
                        </div>
                    </div>
                </div>



                <div class="section-area section-sp2">
                    <div class="container">
                        <div class="row">
                            <c:if test="${!empty requestScope.rate}">
                                <div class="col-md-12 heading-bx left">
                                    <h2 class="title-head text-uppercase">Rating & comment</h2>
                                </div>
                            </div>

                            <div class="" id="instructor">


                                <c:forEach items="${requestScope.rate}" var="c">

                                    <div class="instructor-bx">
                                        <div class="instructor-author">
                                            <c:forEach items="${requestScope.menteeList}" var="m">
                                                <c:if test="${c.menteeId==m.menteeId}">

                                                    <c:if test="${m.base64FileImage!=null}">
                                                        <img src="data:image/jpeg;base64,${m.base64FileImage}" alt="" style="width: 75px; height: 75px; object-fit: cover; border-radius: 50%;">
                                                    </c:if>
                                                    <c:if test="${m.base64FileImage==null}">
                                                        <img src="assets/images/userprofile.png" alt="">
                                                    </c:if>

                                                </c:if>
                                            </c:forEach>
                                            
                                        </div>
                                        <div class="instructor-info">
                                            <c:forEach items="${requestScope.menteeList}" var="r">
                                                <c:if test="${c.menteeId==r.menteeId}">

                                                    <h6>${r.username}</h6>
                                                </c:if>
                                            </c:forEach>
                                            <span>${c.createDate}</span>
                                            <ul class="cours-star">
                                                <c:forEach var="i" begin="1" end="${c.rate}">
                                                    <li class="active"><i class="fa fa-star"></i></li>
                                                    </c:forEach>
                                                    <c:forEach var="j" begin="1" end="${5-c.rate}">
                                                    <li><i class="fa fa-star"></i></li>
                                                    </c:forEach>

                                            </ul>
                                            <p class="m-b0">${c.comment}</p>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:if>
                            <c:if test="${empty requestScope.rate}">

                            </c:if>

                        </div>


                    </div>

                </div>

                <!-- contact area END -->
            </div>
            <!-- Content END-->
            <!-- Footer ==== -->
            <footer>
                <div class="footer-top">
                    <div class="pt-exebar">
                        <div class="container">
                            <div class="d-flex align-items-stretch">
                                <div class="pt-logo mr-auto">
                                    <a href="index.html"><img src="assets/images/logo-white.png" alt=""/></a>
                                </div>
                                <div class="pt-social-link">
                                    <ul class="list-inline m-a0">
                                        <li><a href="#" class="btn-link"><i class="fa fa-facebook"></i></a></li>
                                        <li><a href="#" class="btn-link"><i class="fa fa-twitter"></i></a></li>
                                        <li><a href="#" class="btn-link"><i class="fa fa-linkedin"></i></a></li>
                                        <li><a href="#" class="btn-link"><i class="fa fa-google-plus"></i></a></li>
                                    </ul>
                                </div>
                                <div class="pt-btn-join">
                                    <a href="#" class="btn ">Join Now</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-4 col-md-12 col-sm-12 footer-col-4">
                                <div class="widget">
                                    <h5 class="footer-title">Sign Up For A Newsletter</h5>
                                    <p class="text-capitalize m-b20">Weekly Breaking news analysis and cutting edge advices on job searching.</p>
                                    <div class="subscribe-form m-b20">
                                        <form class="subscription-form" action="http://educhamp.themetrades.com/demo/assets/script/mailchamp.php" method="post">
                                            <div class="ajax-message"></div>
                                            <div class="input-group">
                                                <input name="email" required="required"  class="form-control" placeholder="Your Email Address" type="email">
                                                <span class="input-group-btn">
                                                    <button name="submit" value="Submit" type="submit" class="btn"><i class="fa fa-arrow-right"></i></button>
                                                </span> 
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12 col-lg-5 col-md-7 col-sm-12">
                                <div class="row">
                                    <div class="col-4 col-lg-4 col-md-4 col-sm-4">
                                        <div class="widget footer_widget">
                                            <h5 class="footer-title">Company</h5>
                                            <ul>
                                                <li><a href="index.html">Home</a></li>
                                                <li><a href="about-1.html">About</a></li>
                                                <li><a href="faq-1.html">FAQs</a></li>
                                                <li><a href="contact-1.html">Contact</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="col-4 col-lg-4 col-md-4 col-sm-4">
                                        <div class="widget footer_widget">
                                            <h5 class="footer-title">Get In Touch</h5>
                                            <ul>
                                                <li><a href="http://educhamp.themetrades.com/admin/index.html">Dashboard</a></li>
                                                <li><a href="blog-classic-grid.html">Blog</a></li>
                                                <li><a href="portfolio.html">Portfolio</a></li>
                                                <li><a href="event.html">Event</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="col-4 col-lg-4 col-md-4 col-sm-4">
                                        <div class="widget footer_widget">
                                            <h5 class="footer-title">Courses</h5>
                                            <ul>
                                                <li><a href="courses.html">Courses</a></li>
                                                <li><a href="courses-details.html">Details</a></li>
                                                <li><a href="membership.html">Membership</a></li>
                                                <li><a href="profile.html">Profile</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12 col-lg-3 col-md-5 col-sm-12 footer-col-4">
                                <div class="widget widget_gallery gallery-grid-4">
                                    <h5 class="footer-title">Our Gallery</h5>
                                    <ul class="magnific-image">
                                        <li><a href="assets/images/gallery/pic1.jpg" class="magnific-anchor"><img src="assets/images/gallery/pic1.jpg" alt=""></a></li>
                                        <li><a href="assets/images/gallery/pic2.jpg" class="magnific-anchor"><img src="assets/images/gallery/pic2.jpg" alt=""></a></li>
                                        <li><a href="assets/images/gallery/pic3.jpg" class="magnific-anchor"><img src="assets/images/gallery/pic3.jpg" alt=""></a></li>
                                        <li><a href="assets/images/gallery/pic4.jpg" class="magnific-anchor"><img src="assets/images/gallery/pic4.jpg" alt=""></a></li>
                                        <li><a href="assets/images/gallery/pic5.jpg" class="magnific-anchor"><img src="assets/images/gallery/pic5.jpg" alt=""></a></li>
                                        <li><a href="assets/images/gallery/pic6.jpg" class="magnific-anchor"><img src="assets/images/gallery/pic6.jpg" alt=""></a></li>
                                        <li><a href="assets/images/gallery/pic7.jpg" class="magnific-anchor"><img src="assets/images/gallery/pic7.jpg" alt=""></a></li>
                                        <li><a href="assets/images/gallery/pic8.jpg" class="magnific-anchor"><img src="assets/images/gallery/pic8.jpg" alt=""></a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="footer-bottom">
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-12 col-md-12 col-sm-12 text-center"> <a target="_blank" href="https://www.templateshub.net">Templates Hub</a></div>
                        </div>
                    </div>
                </div>
            </footer>
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
        <script src="assets/js/functions.js"></script>
        <script src="assets/js/contact.js"></script>
        <script src='assets/vendors/scroll/scrollbar.min.js'></script>
        <script src="assets/js/functions.js"></script>
        <script src="assets/vendors/chart/chart.min.js"></script>
        <script src="assets/js/admin.js"></script>
        <script src='assets/vendors/calendar/moment.min.js'></script>
        <script src='assets/vendors/calendar/fullcalendar.js'></script>
        <script>
            $(document).ready(function () {
                var titleClass = JSON.parse('${requestScope.status}');
                var start = JSON.parse('${requestScope.values}');
                var end = JSON.parse('${requestScope.endValues}');
                var eventsArray = [];
                for (var i = 0; i < start.length; i++) {
                    var eventColor = '';
                    if (titleClass[i].toLowerCase() === 'inavaiable') {
                        eventColor = '#c22d2d';
                    } else {
                        eventColor = '#2dc22d';
                    }
                    console.log(titleClass[i]);
                    console.log(titleClass[i].toLowerCase() === 'inactive');
                    eventsArray.push({
                        title: titleClass[i],
                        start: start[i],
                        end: end[i],
                        color: eventColor
                    });
                }
                $('#calendar').fullCalendar({
                    header: {
                        left: 'prev,next today',
                        center: 'title',
                        right: 'agendaWeek,listWeek,agendaDay'
                    },

                    defaultView: 'agendaWeek',
                    navLinks: true, // can click day/week names to navigate views

                    weekNumbers: true,
                    weekNumbersWithinDays: true,
                    weekNumberCalculation: 'ISO',

                    editable: true,
                    eventLimit: true, // allow "more" link when too many events
                    events: eventsArray,
                    height: 500
                });

            });

        </script>
    </body>

</html>

