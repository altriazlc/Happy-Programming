<%-- 
    Document   : managerPayment
    Created on : 16 thg 10, 2024, 20:24:15
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
        <title>Happy Programing : Payment Management </title>

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
        <jsp:include page="headerManager.jsp" />
        <!-- header end -->

        <!-- Left sidebar menu end -->

        <!--Main container start -->
        <main class="ttr-wrapper">
            <div class="container-fluid">
                <div class="db-breadcrumb">
                    <h4 class="breadcrumb-title">Dashboard</h4>
                    <ul class="db-breadcrumb-list">
                        <li><a href="#"><i class="fa fa-home"></i>Home</a></li>
                        <li>Dashboard</li>
                    </ul>
                </div>	
                <!-- Card -->
                <div class="row">
                    <div class="col-md-0 col-lg-12 col-xl-12 col-sm-0 col-12">
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
                    </div>


                </div>
                <!-- Card END -->
                <div class="row">
                    <!-- Your Profile Views Chart -->
                    <div class="col-lg-9 m-b30">
                        <div class="widget-box">

                            <div class="wc-title d-flex align-items-center">
                                <h4 class="d-inline-block" >Mentee Request List</h4>
                                <form action="managersearch" class="d-inline-block ml-auto" style="width: 300px">

                                    <div>
                                        <input type="text" class="form-control" placeholder="Search" name="mentor">
                                    </div>

                                </form>
                            </div>
                            <div class="widget-inner">
                                <div class="table-vertical-scroll table-responsive" style="max-height: 300px; overflow-y: auto;" >
                                    <table class="table table-hover">
                                        <thead class="thead-light">
                                            <tr>
                                                <th scope="col">STT</th>
<!--                                                <th scope="col">Title</th>-->
                                                <th scope="col">Mentee Uname</th>
                                                <th scope="col">Email</th>
                                                <th scope="col">Start Time</th>
                                                <th scope="col">Framework</th>
                                                <th scope="col">Price</th>
                                                <th scope="col">Status</th>

                                                <th scope="col">Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>

                                            <c:forEach items="${requestScope.requestList}" var="c"  varStatus="status">

                                                <tr>
                                                    <th class="align-middle" scope="row">${status.index + 1}</th>
<!--                                                    <td class="align-middle">${c.title}</td>-->
                                                    <c:forEach items="${requestScope.menteeList}" var="v">
                                                        <c:if test="${c.menteeId==v.menteeId}">
                                                            <td class="align-middle" style="max-width: 20px;word-wrap: break-word;"><a href="#" class="text-primary">${v.username}</a></td>
                                                            <td class="align-middle" style="max-width: 150px;word-wrap: break-word;">${v.email}</td>
                                                        </c:if>
                                                    </c:forEach>

                                                    <td class="align-middle">${c.startDate}</td>
                                                    <td class="align-middle">${c.framework}</td>
                                                    <td class="align-middle"><b class="text-black-50"><fmt:formatNumber value="${c.price}" type="number" maxFractionDigits="2" /> ₫</b></td>
                                                    <td class="align-middle" style="max-width: 200px;word-wrap: break-word;">
                                                        <c:choose>
                                                            <c:when test="${c.status.equals('Processing')}">
                                                                <span class="orders-btn">
                                                                    <span href="#" class="text-red"><b>Processing</b></span>
                                                                    <a href="activementeerequest?id=${c.requestId}" class="btn button-sm orange"><i class="ti-check"></i></a>
                                                                </span>

                                                            </c:when>
                                                            <c:when test="${c.status.equals('Studying')}">
                                                                <span class="orders-btn">
                                                                    <span href="#" class="text-orange"><b>Studying</b></span>
<!--                                                                    <a href="activementeerequest?id=${c.requestId}" class="btn button-sm red"><i class="ti-close"></i></a>-->

                                                                </span>
                                                            </c:when>
                                                            <c:otherwise>
                                                                ${c.status}
                                                            </c:otherwise>
                                                        </c:choose>


                                                    </td>     

                                                    <td class="align-middle">
                                                        <span class="new-users-btn">
                                                            <a href="paymentmanagercate?id=${c.requestId}" class="btn button-sm outline">Detail</a>
                                                        </span>
                                                    </td>
                                                </tr>
                                            </c:forEach>



                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Your Profile Views Chart END-->
                    <div class="col-lg-3 m-b30">
                        <div class="widget-card widget-bg4">					 
                            <div class="wc-item">
                                <h4 class="wc-title">
                                    Wallet 
                                </h4>
                                <span class="wc-item">                                    
                                    <fmt:formatNumber value="${wallet.balance}" type="number" maxFractionDigits="2" /> VND
                                </span>
                                
                                
                            </div>				      
                        </div>
                        <div class="widget-box" style="height: auto">
                            <div class="wc-title">
                                <h4>Step 1: Mentee to Manager</h4>
                            </div>
                            <div class="widget-inner">
                                <div class="orders-list">
                                    <ul>
                                        <c:forEach items="${requestScope.paymentList1}" var="p">

                                            <c:choose>
                                                <c:when test="${p.status.equals('2')}">
                                                    <li>
                                                        <span class="orders-title">
                                                            <a href="#" class=""><b>${p.sender}</b></a>
                                                            to <a href="#" class="text-black-50"><b>You</b></a> <br/>
                                                            <span class="text-black"><b class="text-green">+<fmt:formatNumber value="${p.totalAmount}" type="number" maxFractionDigits="2" /> ₫ </b>
                                                               <br/> ${p.formattedPaymentDate} at ${p.formattedPaymentTime} </span>
                                                        </span>
                                                        <span class="orders-btn text-success">
                                                            <b>Received</b>
                                                        </span>
                                                        <!--
                                                           
                                                                <span class="orders-btn">
                                                                    <a href="#" class="btn button-sm red">Unpaid</a>
                                                                </span>
                                                            
                                                        -->
                                                    </li>
                                                </c:when>
                                            </c:choose>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-9 m-b30">
                        <div class="widget-box">

                            <div class="wc-title d-flex align-items-center">
                                <h4 class="d-inline-block" >Completed Request List</h4>
                                <form action="managersearch" class="d-inline-block ml-auto" style="width: 300px">

                                    <div>
                                        <input type="text" class="form-control" placeholder="Search" name="mentor">
                                    </div>

                                </form>
                            </div>
                            <div class="widget-inner">
                                <div class="table-vertical-scroll table-responsive" style="max-height: 300px; overflow-y: auto;">
                                    <table class="table table-hover">
                                        <thead class="thead-light">
                                            <tr>
                                                <th scope="col">STT</th>
<!--                                                <th scope="col">Title</th>-->
                                                <th scope="col">Mentor Uname</th>
                                                <th scope="col">Email</th>
                                                <th scope="col">End Time</th>
                                                <th scope="col">Framework</th>
                                                <th scope="col">Price</th>
                                                <th scope="col">Status</th>

                                                <th scope="col">Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>

                                            <c:forEach items="${requestScope.completeRequestList}" var="c"  varStatus="status">

                                                <tr>
                                                    <th class="align-middle" scope="row">${status.index + 1}</th>
<!--                                                    <td class="align-middle">${c.title}</td>-->
                                                    <c:forEach items="${requestScope.mentorList}" var="v">
                                                        <c:if test="${c.mentorId==v.mentorId}">
                                                            <td class="align-middle" style="max-width: 20px;word-wrap: break-word;"><a href="#" class="text-primary">${v.username}</a></td>
                                                                <c:forEach items="${requestScope.listUser}" var="u">
                                                                    <c:if test="${v.username==u.username}">                                                                
                                                                    <td class="align-middle" style="max-width: 150px;word-wrap: break-word;">${u.email}</td>
                                                                </c:if>
                                                            </c:forEach>
                                                        </c:if>
                                                    </c:forEach>

                                                    <td class="align-middle">${c.endDate}</td>
                                                    <td class="align-middle">${c.framework}</td>
                                                    <td class="align-middle"><b class="text-black-50"><fmt:formatNumber value="${c.price}" type="number" maxFractionDigits="2" /> ₫</b></td>
                                                    <td class="align-middle" style="max-width: 200px;word-wrap: break-word;">
                                                        <c:choose>
                                                            <c:when test="${c.status.equals('Completed')}">
                                                                <span href="paymentmanagercate?id=${c.requestId}" class="text-success"><b>Completed</b></span>
                                                            </c:when>
                                                            <c:when test="${c.status.equals('Paid')}">
                                                                <span href="paymentmanagercate?id=${c.requestId}" class="text-primary"><b>Paid</b></span>
                                                            </c:when>
                                                            <c:otherwise>
                                                                ${c.status}
                                                            </c:otherwise>
                                                        </c:choose>


                                                    </td>     

                                                    <td class="align-middle">
                                                        <span class="new-users-btn">
                                                            <a href="paymentmanagercate?id=${c.requestId}" class="btn button-sm outline">Detail</a>
                                                        </span>
                                                    </td>
                                                </tr>
                                            </c:forEach>



                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Your Profile Views Chart END-->
                    <div class="col-lg-3 m-b30">
                        <div class="widget-box">
                            <div class="wc-title">
                                <h4>Step 2: Manager to Mentor </h4>
                            </div>
                            <div class="widget-inner">
                                <div class="orders-list">
                                    <ul>

                                        <c:choose>
                                            <c:when test="${empty requestScope.lastStatusPayment}">
                                                <c:forEach items="${requestScope.paymentList1}" var="p">
                                                    <c:forEach items="${requestScope.completeRequestList}" var="c">
                                                        <c:if test="${p.requestId==c.requestId}">
                                                            <c:forEach items="${requestScope.mentorList}" var="v">
                                                                <c:if test="${c.mentorId==v.mentorId}">
                                                                    <li>


                                                                        <span class="orders-title">
                                                                            <a href="#" class="text-black-50"><b>You</b></a> 
                                                                            to <a href="#" class=""><b>${v.username}</b></a> <br/>
                                                                            <span class="text-black"><b class="text-red">Total: -<fmt:formatNumber value="${c.price}" type="number" maxFractionDigits="2" /> ₫ </b>
                                                                            </span>
                                                                        </span>
                                                                        <span class="orders-btn">
                                                                            <a href="paymentmanagerchange?id=${v.username}&ballance=${c.price}&requestId=${c.requestId}" class="btn button-sm">Confirm Payment</a>
                                                                        </span>
                                                                    </li>
                                                                </c:if>
                                                            </c:forEach>
                                                        </c:if>
                                                    </c:forEach>
                                                </c:forEach>

                                            </c:when>
                                            <c:when test="${not empty requestScope.lastStatusPayment}">
                                                <c:forEach items="${requestScope.lastStatusPayment}" var="p">
                                                    
                                                    <c:forEach items="${requestScope.completeRequestList}" var="c">
                                                        <c:if test="${p.requestId==c.requestId}">
                                                         
                                                            
                                                            <c:forEach items="${requestScope.mentorList}" var="v">
                                                                <c:if test="${c.mentorId==v.mentorId}">
                                                                     <li>


                                                                        <span class="orders-title">
                                                                            <a href="#" class="text-black-50"><b>You</b></a> 
                                                                            to <a href="#" class=""><b>${v.username}</b></a> <br/>
                                                                            <span class="text-black"><b class="text-red">Total: -<fmt:formatNumber value="${c.price}" type="number" maxFractionDigits="2" /> ₫ </b>
                                                                            </span>
                                                                        </span>
                                                                        <span class="orders-btn text-primary">
                                                                            <b>Paid</b>
                                                                        </span>
                                                                    </li>
                                                                </c:if>
                                                            </c:forEach>
                                                        </c:if>
                                                    </c:forEach>
                                                                    
                                                </c:forEach>

                                            </c:when>
                                            
                                        </c:choose>
                                    </ul>

                                </div>
                            </div>
                        </div>
                    </div>
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

                $('#calendar').fullCalendar({
                    header: {
                        left: 'prev,next today',
                        center: 'title',
                        right: 'month,agendaWeek,agendaDay,listWeek'
                    },
                    defaultDate: '2019-03-12',
                    navLinks: true, // can click day/week names to navigate views

                    weekNumbers: true,
                    weekNumbersWithinDays: true,
                    weekNumberCalculation: 'ISO',

                    editable: true,
                    eventLimit: true, // allow "more" link when too many events
                    events: [
                        {
                            title: 'All Day Event',
                            start: '2019-03-01'
                        },
                        {
                            title: 'Long Event',
                            start: '2019-03-07',
                            end: '2019-03-10'
                        },
                        {
                            id: 999,
                            title: 'Repeating Event',
                            start: '2019-03-09T16:00:00'
                        },
                        {
                            id: 999,
                            title: 'Repeating Event',
                            start: '2019-03-16T16:00:00'
                        },
                        {
                            title: 'Conference',
                            start: '2019-03-11',
                            end: '2019-03-13'
                        },
                        {
                            title: 'Meeting',
                            start: '2019-03-12T10:30:00',
                            end: '2019-03-12T12:30:00'
                        },
                        {
                            title: 'Lunch',
                            start: '2019-03-12T12:00:00'
                        },
                        {
                            title: 'Meeting',
                            start: '2019-03-12T14:30:00'
                        },
                        {
                            title: 'Happy Hour',
                            start: '2019-03-12T17:30:00'
                        },
                        {
                            title: 'Dinner',
                            start: '2019-03-12T20:00:00'
                        },
                        {
                            title: 'Birthday Party',
                            start: '2019-03-13T07:00:00'
                        },
                        {
                            title: 'Click for Google',
                            url: 'http://google.com/',
                            start: '2019-03-28'
                        }
                    ]
                });

            });

        </script>
    </body>

    <!-- Mirrored from educhamp.themetrades.com/demo/admin/index.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 22 Feb 2019 13:09:05 GMT -->
</html>
