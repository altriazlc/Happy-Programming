<%-- 
    Document   : slot
    Created on : 15 thg 10, 2024, 18:47:37
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <!-- Mirrored from educhamp.themetrades.com/demo/admin/index.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 22 Feb 2019 13:08:15 GMT -->
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
        <title>Happy Programing : Schedule </title>

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
        <style>
            .fc-event .fc-title {
                font-size: 16px; /* Adjust font size if needed */
                font-weight: bold;
            }
            .fc-time{
                font-size: 16px;
                margin-right: 1px
            }
        </style>
    </head>
    <body class="ttr-opened-sidebar ttr-pinned-sidebar">

        <!-- header start -->
        <jsp:include page="headerMentee.jsp" />
        <!-- Left sidebar menu end -->

        <!--Main container start -->
        <main class="ttr-wrapper">
            <div class="container-fluid">
                <div class="db-breadcrumb">
                    <h4 class="breadcrumb-title">Schedule</h4>
                    <ul class="db-breadcrumb-list">
                        <li><a href="home"><i class="fa fa-home"></i>Home</a></li>
                        <li>Schedule</li>
                    </ul>
                </div>	
                <!-- Card -->
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
                <!-- Card END -->
                <div class="row">
                    <!-- Your Profile Views Chart -->

                    <!-- Your Profile Views Chart END-->



                    <div class="col-lg-12 m-b30">
                        <div class="widget-box">
                            <div class="wc-title">
                                <h4>Basic Calendar</h4>
                            </div>
                            <div class="widget-inner">
                                <div id="calendar"></div>
                            </div>
                        </div>
                    </div>
<!--                    <form id="dataForm" action="slotview" method="POST">
                        <input type="hidden" name="eventsArray" id="eventsArrayInput">
                        <button type="submit" class="btn">Save changes</button>
                    </form>-->
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

        <script>
            $(document).ready(function () {
                var status = JSON.parse('${requestScope.status}');
                var start = JSON.parse('${requestScope.start}');
                var end = JSON.parse('${requestScope.end}');
                var framework = JSON.parse('${requestScope.framework}');
                var eventsArray = [];
                for (var i = 0; i < start.length; i++) {
                    var eventColor = '';
                    if (status[i].toLowerCase() === 'absent') {
                        eventColor = '#c22d2d';
                    } else if (status[i].toLowerCase() === 'attended') {
                        eventColor = '#2dc22d';
                    } else {
                        eventColor = '#404040';
                    }
                    console.log(status[i]);
                    console.log(status[i].toLowerCase() === 'inactive');
                    eventsArray.push({
                        title: framework[i] + ' - ' + status[i],
                        start: start[i],
                        end: end[i],
                        color: eventColor
                    });
                }
                $('#calendar').fullCalendar({
                    header: {
                        left: 'prev,next today',
                        center: 'title',
                        right: 'month,agendaWeek,agendaDay,listWeek'
                    },

                    defaultView: 'month',
                    navLinks: true, // can click day/week names to navigate views

                    weekNumbers: true,
                    weekNumbersWithinDays: true,
                    weekNumberCalculation: 'ISO',

                    editable: true,
                    eventLimit: true, // allow "more" link when too many events
                    events: eventsArray,
                    height: 800
//                    eventClick: function (event, jsEvent, view) {
//                        // Hiển thị prompt để nhập tiêu đề mới
//                        var newTitle = 'absent';
//                        
//                        // Nếu có tiêu đề mới, cập nhật sự kiện
//                        if (newTitle) {
//                            event.title = newTitle;
//                            $('#calendar').fullCalendar('updateEvent', event);
//
//                            // Cập nhật `eventsArray` nếu cần lưu lại dữ liệu mới
//                            eventsArray = eventsArray.map(evt => {
//                                if (evt.start === event.start.format() && evt.end === event.end.format()) {
//                                    evt.title = newTitle;
//                                }
//                                return evt;
//                            });
//
//                            // Gán lại `eventsArray` vào input ẩn nếu cần gửi về Servlet
//                            document.getElementById("eventsArrayInput").value = JSON.stringify(eventsArray);
//                        }
//                    }
                });
// Convert eventsArray to JSON and set it to hidden input
//                document.getElementById("eventsArrayInput").value = JSON.stringify(eventsArray);

// Submit the form
            });

        </script>
    </body>


    <!-- Mirrored from educhamp.themetrades.com/demo/admin/index.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 22 Feb 2019 13:09:05 GMT -->
</html>
