<%-- 
    Document   : requestDetailAdmin
    Created on : Oct 16, 2024, 10:23:10 PM
    Author     : tuong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
        <title>Happy Programing </title>


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

        <!-- header start -->
        <jsp:include page="headerAdmin.jsp" />
        <!-- Left sidebar menu end -->

        <!--Main container start -->
        <main class="ttr-wrapper">
            <div class="container-fluid">
                <div class="db-breadcrumb">
                    <h4 class="breadcrumb-title">User</h4>
                    
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
                                <div class="breadcrumb-title"> Request ID: ${request.requestId}</div>
                                <div>Request Status: ${request.status}</div>
                                <div class="seperator"></div>
                                <br>
                                <hr class="hr" />
                                <div style="display: flex" class="row">
                                    <div class="col-md-6" style="display: flex">
                                        <h6>Mentor</h6>
                                        <div class="col-md-6">
                                            <p>Mentor ID: ${mentor.mentorId}</p>
                                            <p>Full name: ${mentor.fullName}</p>
                                            <p>Date of birth: ${mentor.dateOfBirth}</p>
                                            <p>Profession: ${cv.jobProfession}</p>
                                        </div>
                                        <div class="col-md-2">
                                            <img id="userAvatar" src="getCVimage?id=${requestScope.cv.cvId}" class="rounded-circle" >
                                        </div>

                                    </div>
                                    <div class="col-md-6" style="display: flex">
                                        <h6>Mentee</h6>
                                        <div class="col-md-6">
                                            <p>Mentee ID: ${mentee.menteeId}</p>
                                            <p>Full name: ${mentee.fullName}</p>
                                            <p>Date of birth: ${mentee.dateOfBirth}</p>
                                        </div>
                                        <div class="col-md-2">
                                            <img src="data:image/jpeg;base64,${mentee.base64FileImage}" class="rounded-circle">
                                        </div>
                                    </div>
                                </div>
                                <hr class="hr" />
                                <div class="row">
                                    <div class="col-12">
                                        <div class="ml-auto">
                                            <h6>Skill info</h6>
                                        </div>
                                    </div>
                                    <div class="form-group col-6">
                                        <p>Skill ID: ${skill.skillId}</p>
                                    </div>
                                    <div class="form-group col-6">
                                        <p>Skill Name: ${skill.skillName}</p>
                                    </div>
                                    <div class="form-group col-6">
                                        <p>Status: ${skill.status}</p>
                                    </div>
                                    <div>
                                        <label>Image: </label>&nbsp;
                                        <img src="data:image/jpeg;base64,${skill.base64ImageFile}" class="img-fluid" style="max-height: 100px; max-width: 100px">
                                    </div>
                                </div>
                                <hr class="hr" />
                                <div class="header" style="text-align: center"><h5>Schedule</h5></div>
                                <div>
                                    <p>Start date: ${request.startDate}</p>
                                    <p>End date: ${request.endDate}</p></div>


                                <div class="row">
                                    <form action="requestDetailAdmin" method="get">
                                        <input type="hidden" name="requestID" value="${request.requestId}">
                                        <label>Start date:</label>
                                        <input type="date" name="start" value="${requestScope.start}" onchange="this.form.submit()">
                                    </form>
                                    <table class="table table-hover" style="border-collapse: collapse;">
                                        <thead class="thead-light">
                                            <tr>
                                                <c:forEach items="${dateOfDay}" var="entry">
                                                    <th scope="col">${entry.key}</th> <!-- Day of the week -->
                                                    </c:forEach>
                                            </tr>
                                            <tr>
                                                <c:forEach items="${dateOfDay}" var="entry">
                                                    <th scope="col">${entry.value}</th> <!-- Corresponding date -->
                                                    </c:forEach>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <c:forEach var="day" items="${daysOfWeek}">
                                                    <td style="border: solid black 1px; padding: 10px;">
                                                        <c:if test="${not empty slotInWeek[day]}">
                                                            <c:forEach var="slot" items="${slotInWeek[day]}">
                                                                <form action="mentorSchedule" method="get" style="margin-bottom: 10px;">
                                                                    <div>
                                                                        <strong>Request ID:</strong> ${slot.requestID} - <strong>Title:</strong> ${slot.title}
                                                                    </div>
                                                                    <div><strong>Mentee ID:</strong> ${slot.menteeID}</div>

                                                                    <c:choose>
                                                                        <c:when test="${slot.status != null && slot.status eq 'Attended'}">
                                                                            <div style="display: flex; align-items: center;">
                                                                                <strong>Status:<p style="color: green; margin-left: 5px;">${slot.status}</p></strong>

                                                                            </div>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <div style="display: flex; align-items: center;">
                                                                                <strong>Status:<p style="color: red; margin-left: 5px;">${slot.status}</p></strong>

                                                                            </div>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                    <div><strong>Time:</strong> ${slot.startTime} - ${slot.endTime}</div>
                                                                    <input type="hidden" name="attendID" value="${slot.attendID}">
                                                                    <input type="hidden" name="start" value="${start}">
                                                                </form>
                                                            </c:forEach>
                                                        </c:if>  
                                                        <c:if test="${empty slotInWeek[day]}">
                                                            <p>No slot available</p>
                                                        </c:if> 
                                                    </td>
                                                </c:forEach>
                                            </tr>
                                        </tbody>
                                    </table>

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
        <script src='assets/vendors/switcher/switcher.js'></script>
        <script>
                                            // Pricing add
                                            function newMenuItem() {
                                                var newElem = $('tr.list-item').first().clone();
                                                newElem.find('input').val('');
                                                newElem.appendTo('table#item-add');
                                            }
                                            if ($("table#item-add").is('*')) {
                                                $('.add-item').on('click', function (e) {
                                                    e.preventDefault();
                                                    newMenuItem();
                                                });
                                                $(document).on("click", "#item-add .delete", function (e) {
                                                    e.preventDefault();
                                                    $(this).parent().parent().parent().parent().remove();
                                                });
                                            }

        </script>

    </body>

    <!-- Mirrored from educhamp.themetrades.com/demo/admin/add-listing.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 22 Feb 2019 13:09:05 GMT -->
</html>
