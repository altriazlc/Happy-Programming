<%-- 
    Document   : requestDetailMentorMentee
    Created on : Oct 24, 2024, 8:54:15 PM
    Author     : asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">

    <!-- Mirrored from educhamp.themetrades.com/demo/admin/add-listing.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 22 Feb 2019 13:09:05 GMT -->
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
        <title>Request Detail</title>

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
        <link rel="stylesheet" type="text/css" href="assets/css/dashboard.css">
        <link class="skin" rel="stylesheet" type="text/css" href="assets/css/color/color-1.css">

    </head>
    <body class="ttr-opened-sidebar ttr-pinned-sidebar">

        <c:choose>
            <c:when test="${sessionScope.acc.roleId == 1}">
                <jsp:include page="headerMentor.jsp" />
            </c:when>
            <c:when test="${sessionScope.acc.roleId == 2}">
                <jsp:include page="headerMentee.jsp" />
            </c:when>
            <c:when test="${sessionScope.acc.roleId == 3}">
                <jsp:include page="headerAdmin.jsp" />
            </c:when>
            <c:when test="${sessionScope.acc.roleId == 4}">
                <jsp:include page="headerManager.jsp" />
            </c:when>
        </c:choose>

        <!--Main container start -->
        <main class="ttr-wrapper">
            <div class="container-fluid">
                <div class="db-breadcrumb">
                    <h4 class="breadcrumb-title">User</h4>
                    <ul class="db-breadcrumb-list">
                        <li><a href="#"><i class="fa fa-home"></i>Home</a></li>
                        <li>User</li>
                        <li>Request Detail</li>

                    </ul>
                </div>	
                <div class="row">
                    <!-- Your Profile Views Chart -->
                    <div class="col-lg-12 m-b30">
                        <div class="widget-box" >
                            <div class="wc-title" style="display: flex">
                                <div class="col-md-4">
                                    <h4>Request Detail</h4>
                                </div>
                            </div>

                            <div class="widget-inner">
                                <div>Course Process: ${request.status}</div><br>
                                <div style="display: flex" class="row">
                                    <div class="col-md-7">
                                        <h6>Mentor</h6>
                                        <table class="table table-hover">
                                            <thead class="thead-light">
                                                <tr>
                                                    <th scope="col">Mentor ID</th>
                                                    <th scope="col">Full name</th>
                                                    <th scope="col">Date of birth</th>                                               
                                                    <th scope="col">Profession</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>${mentor.mentorId}</td>
                                                    <td>${mentor.fullName}</td>
                                                    <td>${mentor.dateOfBirth}</td>
                                                    <td>${cv.jobProfession}</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="col-md-5">
                                        <h6>Mentee</h6>
                                        <table class="table table-hover">
                                            <thead class="thead-light">
                                                <tr>
                                                    <th scope="col">Mentee ID</th>
                                                    <th scope="col">Full name</th>
                                                    <th scope="col">Date of birth</th>                                               
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>${mentee.menteeId}</td>
                                                    <td>${mentee.fullName}</td>
                                                    <td>${mentee.dateOfBirth}</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <div class="seperator"></div><br>
                                <div class="row">
                                    <div class="col-7">
                                        <div class="ml-auto">
                                            <h6>Skill info</h6>
                                        </div>
                                        <table class="table table-hover">
                                            <thead class="thead-light">
                                                <tr>
                                                    <th scope="col">Skill Name</th>
                                                    <th scope="col">Image</th>
                                                    <th scope="col">Start date</th>
                                                    <th scope="col">End date</th>  
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>${skill.skillName}</td>
                                                    <td><img src="data:image/jpeg;base64,${skill.base64ImageFile}" class="img-fluid" style="max-height: 100px; max-width: 100px"></td>
                                                    <td>${request.startDate}</td>
                                                    <td>${request.endDate}</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>

                                <div class="row">                                    
                                <!-- Your Profile Views Chart END-->
                                <div class="col-lg-12 col-md-12 m-b30">
                                    <div class="widget-box">
                                        <div class="widget-box">
                                            <div class="wc-title">
                                                <h4>Basic Calendar</h4>
                                            </div>
                                            <div class="widget-inner">
                                                <div id="calendar"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Your Profile Views Chart END-->
                </div>
            </div>
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
        <script src='assets/vendors/scroll/scrollbar.min.js'></script>
        <script src="assets/js/functions.js"></script>
        <script src="assets/vendors/chart/chart.min.js"></script>
        <script src="assets/js/admin.js"></script>
        <script src='assets/vendors/calendar/moment.min.js'></script>
        <script src='assets/vendors/calendar/fullcalendar.js'></script>
    </body>
    <script>
            $(document).ready(function () {
                var status = JSON.parse('${requestScope.status}');
                var start = JSON.parse('${requestScope.values}');
                var end = JSON.parse('${requestScope.endValues}');
                var eventsArray = [];
                for (var i = 0; i < start.length; i++) {
                    var eventColor = '';
                    if (status[i].toLowerCase() === 'inactive') {
                        eventColor = '#c22d2d';
                    } else {
                        eventColor = '#2dc22d';
                    }
                    console.log('debug:');
                    console.log(status[i]);
                    console.log(status[i].toLowerCase() === 'inactive');
                    eventsArray.push({
                        title: status[i],
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

    <!-- Mirrored from educhamp.themetrades.com/demo/admin/add-listing.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 22 Feb 2019 13:09:05 GMT -->
</html>
