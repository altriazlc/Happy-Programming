<%-- 
    Document   : CVmanager
    Created on : 11 thg 10, 2024, 11:53:23
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
        <title>Happy Programing : CV Management </title>

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

                <!-- Card END -->


                <div class="row">
                    <!-- Your Profile Views Chart -->



                    <div class="col-lg-6 m-b30">
                        <div class="widget-box">

                            <div class="wc-title d-flex align-items-center">
                                <h4 class="d-inline-block" >Mentor List</h4>
                                <form action="managersearch" class="d-inline-block ml-auto" style="width: 300px">

                                    <div>
                                        <input type="text" class="form-control" placeholder="Search" name="mentor">
                                    </div>

                                </form>
                            </div>
                            <div class="widget-inner">
                                <div class="table-vertical-scroll table-responsive" style="max-height: 600px; overflow-y: auto;">
                                    <table class="table table-hover" id="mentorTable">
                                        <thead class="thead-light">
                                            <tr>
                                                <th scope="col" onclick="sortTable(0)"><a href="#" >Id#</a></th>
                                                <th scope="col" onclick="sortTable(1)"><a href="#" >Account Name</a></th>
                                                <th scope="col" onclick="sortTable(2)"><a href="#" >Avatar</a></th>
                                                <th scope="col" onclick="sortTable(3)"><a href="#" >Email</a></th>
                                                <th scope="col">Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${requestScope.mentorList}" var="c">

                                                <tr>
                                                    <th class="align-middle" scope="row">${c.mentorId}</th>
                                                    <td class="align-middle" style="max-width: 20px;word-wrap: break-word;"><a href="viewprofilecv?id=${c.mentorId}" class="text-primary">${c.username}</a></td>
                                                    <td class="align-middle">
                                                        <c:forEach items="${requestScope.listActiveCV}" var="cv">
                                                            <c:if test="${c.mentorId==cv.mentorId}">                                                            ${u.email}
                                                                <img src="getCVimage?id=${cv.cvId}" alt="" class="" style=" width: 75px;height: 75px;object-fit: cover;"/>
                                                            </c:if>
                                                        </c:forEach>
                                                    </td>
                                                    <td class="align-middle" style="max-width: 200px;word-wrap: break-word;">
                                                        <c:forEach items="${requestScope.listUser}" var="u">
                                                            <c:if test="${c.username==u.username}">
                                                                ${u.email}
                                                            </c:if>
                                                        </c:forEach>
                                                    </td>                                                
                                                    <td class="align-middle">
                                                        <span class="new-users-btn">
                                                            <a href="cvmanagercate?id=${c.mentorId}" class="btn button-sm outline"><i class="ti-file"></i></a>
                                                            <a href="slotmanagercate?id=${c.mentorId}" class="btn button-sm outline"><i class="ti-calendar"></i></a>
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
                    <div class="col-lg-6 m-b30">



                        <div class="widget-box">
                            <div class="wc-title d-flex align-items-center">
                                <c:if test="${not empty requestScope.listCV}">
                                    <h4 class="d-inline-block" >CV List</h4>

                                </c:if>
                                <c:if test="${not empty requestScope.listActiveSlot || not empty requestScope.listInactiveSlot}">
                                    <h4 class="d-inline-block" >Slot List</h4>   
                                </c:if>


                                <div class="d-inline-block ml-auto">
                                    <a href="cvmanager" class="btn button-layout purple">Clear Filters</a>
                                </div>


                            </div>
                            <c:if test="${not empty requestScope.listCV}">
                                <div class="widget-inner">
                                    <div class="orders-list" style="max-height: 600px; overflow-y: auto;">
                                        <c:if test="${not empty requestScope.error}">
                                            <div class="alert alert-danger" role="alert">
                                                ${requestScope.error}
                                            </div>
                                        </c:if>
                                        <ul>
                                            <c:forEach items="${requestScope.listCV}" var="v">

                                                <li>
                                                    <span class="orders-title">
                                                        <c:forEach items="${requestScope.mentorList}" var="m">
                                                            <c:if test="${v.mentorId==m.mentorId}">

                                                                <a href="#" class="orders-title-name">${m.username}</a>
                                                            </c:if>
                                                        </c:forEach>
                                                        <span class="orders-info">CV # ${v.cvId} | Last change ${v.createDate}</span>
                                                    </span>
                                                    <c:if test="${v.status.equals('inactive')}">
                                                        <span class="orders-btn">
                                                            <a href="#" class="btn button-sm red">Inactive</a>
                                                            <a href="activecv?id=${v.cvId}" class="btn button-sm green"><i class="ti-check"></i></a>
                                                            <a data-toggle="modal" data-target="#exampleModal${v.cvId}" href="#" class="btn button-sm primary ">View</a>
                                                        </span>

                                                    </c:if>
                                                    <c:if test="${v.status.equals('active')}">
                                                        <span class="orders-btn">
                                                            <a href="#" class="btn button-sm green">Active</a>
                                                            <a href="activecv?id=${v.cvId}" class="btn button-sm red"><i class="ti-close"></i></a>
                                                            <a data-toggle="modal" data-target="#exampleModal${v.cvId}" href="#" class="btn button-sm primary ">View</a>
                                                        </span>
                                                    </c:if>

                                                </li>
                                                <!-- Modal -->
                                                <div class="modal fade" id="exampleModal${v.cvId}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                    <div class="modal-dialog modal-lg">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <c:forEach items="${requestScope.mentorList}" var="c">
                                                                    <c:if test="${c.mentorId==v.mentorId}">
                                                                        <h5 class="modal-title text-center" id="exampleModalLabel">CV of Mentor: ${c.username}</h5>
                                                                    </c:if>
                                                                </c:forEach>
                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                    <span aria-hidden="true">&times;</span>
                                                                </button>
                                                            </div>

                                                            <div class="modal-body">
                                                                <div class="widget-box">
                                                                    <div class="widget-inner">
                                                                        <form class="edit-profile m-b30" action="cvcreate" method="post" enctype="multipart/form-data">
                                                                            <div class="row">
                                                                                <div class="col-12">
                                                                                    <div class="ml-auto">
                                                                                        <h3>1. Personal Details</h3>
                                                                                    </div>
                                                                                </div>



                                                                                <c:forEach items="${requestScope.mentorList}" var="c">
                                                                                    <c:if test="${c.mentorId==v.mentorId}">
                                                                                        <div class="form-group col-12">
                                                                                            <div class=" text-center">

                                                                                                <img id="userAvatar" src="getCVimage?id=${v.cvId}" class="rounded-circle" alt="" style=" margin: 50px 10px;width: 150px;height: 150px;object-fit: cover;">
                                                                                                <div id="Imgstatus"></div>
                                                                                            </div>
                                                                                            <div style="text-align: center">
                                                                                            </div>
                                                                                        </div>
                                                                                        <div class="form-group col-6">
                                                                                            <label class="col-form-label font-weight-bold">Full Name</label>
                                                                                            <div>
                                                                                                ${c.fullName}

                                                                                            </div>
                                                                                        </div>
                                                                                        <div class="form-group col-6">
                                                                                            <label class="col-form-label font-weight-bold">Account name</label>
                                                                                            <div>
                                                                                                ${c.username}
                                                                                            </div>
                                                                                        </div>
                                                                                        <div class="form-group col-6">
                                                                                            <label class="col-form-label font-weight-bold">Date of Birth</label>
                                                                                            <div>
                                                                                                ${c.dateOfBirthFormatted}
                                                                                            </div>
                                                                                        </div>
                                                                                        <div class="form-group col-6">
                                                                                            <label class="col-form-label font-weight-bold">Email</label>
                                                                                            <div>
                                                                                                <c:forEach items="${requestScope.listUser}" var="u">
                                                                                                    <c:if test="${c.username==u.username}">
                                                                                                        ${u.email}
                                                                                                    </c:if>
                                                                                                </c:forEach>
                                                                                            </div>
                                                                                        </div>
                                                                                        <div class="form-group col-3">
                                                                                            <label class="col-form-label font-weight-bold">Phone number</label>
                                                                                            <div>
                                                                                                ${c.phone}
                                                                                            </div>
                                                                                        </div>
                                                                                        <div class="form-group col-3">
                                                                                            <label class="col-form-label font-weight-bold">Sex</label>
                                                                                            <div>
                                                                                                ${c.gender}

                                                                                            </div>
                                                                                        </div>
                                                                                        <div class="form-group col-6">
                                                                                            <label class="col-form-label font-weight-bold">Address</label>
                                                                                            <div>
                                                                                                ${c.address}
                                                                                            </div>
                                                                                        </div>
                                                                                    </c:if>
                                                                                </c:forEach>
                                                                                <div class="seperator"></div>

                                                                                <div class="col-12 m-t20">
                                                                                    <div class="ml-auto m-b5">
                                                                                        <h3>2. CV detail</h3>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="form-group col-6">
                                                                                    <label class="col-form-label font-weight-bold">Profession</label>
                                                                                    <div>
                                                                                        ${v.jobProfession}
                                                                                    </div>
                                                                                </div>
                                                                                <div class="form-group col-6">
                                                                                    <label class="col-form-label font-weight-bold">Framework</label>
                                                                                    <div>
                                                                                        ${v.framework}
                                                                                    </div>
                                                                                </div>
                                                                                <div class="form-group col-3">
                                                                                    <label class="col-form-label font-weight-bold">Education</label>
                                                                                    <div>
                                                                                        ${v.education}
                                                                                    </div>
                                                                                </div>
                                                                                <div class="form-group col-3">
                                                                                    <label class="col-form-label font-weight-bold">Price</label>
                                                                                    <div>
                                                                                        ${v.price}
                                                                                    </div>
                                                                                </div>
                                                                                <div class="form-group col-6">
                                                                                    <label class="col-form-label font-weight-bold">Activity</label>
                                                                                    <div>
                                                                                        ${v.activity}
                                                                                    </div>
                                                                                </div>

                                                                                <div class="form-group col-6">
                                                                                    <label class="col-form-label font-weight-bold">Profession Introduction</label>
                                                                                    <div>
                                                                                        ${v.professionIntroduction}
                                                                                    </div>
                                                                                </div>
                                                                                <div class="form-group col-6">
                                                                                    <label class="col-form-label font-weight-bold">Service description</label>
                                                                                    <div>
                                                                                        ${v.serviceDescription}
                                                                                    </div>
                                                                                </div>

                                                                                <div class="form-group col-6">
                                                                                    <label class="col-form-label font-weight-bold">Skills</label><br/>
                                                                                    <div>

                                                                                        <c:forEach items="${requestScope.listSkillList}" var="s">
                                                                                            <c:if test="${v.cvId==s.cvId}">
                                                                                                <c:forEach items="${requestScope.listSkill}" var="x">
                                                                                                    <c:if test="${s.skillId==x.skillId}">
                                                                                                        <label class="col-form-label" >
                                                                                                            ${x.skillName} &nbsp;&nbsp;&nbsp;&nbsp;         
                                                                                                        </label>
                                                                                                    </c:if>    
                                                                                                </c:forEach>

                                                                                            </c:if>
                                                                                        </c:forEach>

                                                                                    </div>
                                                                                </div>
                                                                                <div class="form-group col-6">
                                                                                    <label class="col-form-label font-weight-bold">Archivement description</label>
                                                                                    <div>
                                                                                        ${v.experience}
                                                                                    </div>
                                                                                </div>


                                                                                <div class="m-form__seperator m-form__seperator--dashed m-form__seperator--space-2x"></div>


                                                                                <div class="col-12">
                                                                                    <br/>

                                                                                </div>
                                                                            </div>
                                                                        </form>

                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${not empty requestScope.listActiveSlot || not empty requestScope.listInactiveSlot}">
                                <div class="widget-inner">
                                    <div class="orders-list" style="max-height: 600px; overflow-y: auto;">
                                        <c:if test="${not empty requestScope.error}">
                                            <div class="alert alert-danger" role="alert">
                                                ${requestScope.error}
                                            </div>
                                        </c:if>
                                        <ul>       
                                            <c:if test="${not empty requestScope.listInactiveSlot}">
                                                <li>
                                                    <span class="orders-title">
                                                        <a href="#" class="orders-title-name">Draft Slot</a>
                                                        <span class="orders-info">Mentor newest edit</span>
                                                    </span>
                                                    <span class="orders-btn">
                                                        <a href="#" class="btn button-sm red">Inactive</a>
                                                        <a href="slotstatus?action=active&id=${requestScope.id}" class="btn button-sm green"><i class="ti-check"></i></a>
                                                        <a data-toggle="modal" data-target="#inactive" href="#" class="btn button-sm primary ">View</a>
                                                    </span>
                                                </li>
                                            </c:if>
                                            <c:if test="${not empty requestScope.listActiveSlot}">
                                                <li>
                                                    <span class="orders-title">
                                                        <a href="#" class="orders-title-name">Curent Slot</a>
                                                        <span class="orders-info">Curent Slot</span>
                                                    </span>
                                                    <span class="orders-btn">
                                                        <a href="#" class="btn button-sm green">Active</a>
                                                        <a href="slotstatus?action=deactive&id=${requestScope.id}" class="btn button-sm red"><i class="ti-close"></i></a>
                                                        <a data-toggle="modal" data-target="#active" href="#" class="btn button-sm primary ">View</a>
                                                    </span>
                                                </li>
                                            </c:if>

                                            <!-- Modal -->
                                            <div class="modal fade" id="active" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                <div class="modal-dialog modal-lg">
                                                    <div class="modal-content">
                                                        <div class="modal-header">

                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                <span aria-hidden="true">&times;</span>
                                                            </button>
                                                        </div>

                                                        <div class="modal-body">
                                                            <div class="widget-box">
                                                                <div class="widget-inner">
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
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!--Second modal-->
                                            <div class="modal fade" id="inactive" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                <div class="modal-dialog modal-lg">
                                                    <div class="modal-content">
                                                        <div class="modal-header">

                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                <span aria-hidden="true">&times;</span>
                                                            </button>
                                                        </div>

                                                        <div class="modal-body">
                                                            <div class="widget-box">
                                                                <div class="widget-inner">
                                                                    <div class="col-lg-12 col-md-12 m-b30">
                                                                        <div class="widget-box">
                                                                            <div class="widget-box">
                                                                                <div class="wc-title">
                                                                                    <h4>Basic Calendar</h4>
                                                                                </div>
                                                                                <div class="widget-inner">
                                                                                    <div id="calendar2"></div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>

                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                        </ul>
                                    </div>
                                </div>
                            </c:if>
                        </div>


                    </div>

                </div>
            </div>
        </main>
        <div class="ttr-overlay"></div>



    </body>
    <script src="assets/js/jquery.min.js"></script>
    <script>
        $(document).ready(function () {
            var status1 = JSON.parse('${requestScope.status}');
            var start1 = JSON.parse('${requestScope.startTime}');
            var end1 = JSON.parse('${requestScope.endTime}');
            var eventsArray1 = [];
            for (var i = 0; i < start1.length; i++) {
                var eventColor = '';
                if (status1[i].toLowerCase() === 'inactive') {
                    eventColor = '#c22d2d';
                } else {
                    eventColor = '#2dc22d';
                }
                console.log('debug:');
                console.log(status1[i]);
                console.log(status1[i].toLowerCase() === 'inactive');
                eventsArray1.push({
                    title: status1[i],
                    start: start1[i],
                    end: end1[i],
                    color: eventColor
                });
            }
            var status = JSON.parse('${requestScope.status2}');
            var start = JSON.parse('${requestScope.startTime2}');
            var end = JSON.parse('${requestScope.endTime2}');
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
                events: eventsArray1,
                height: 500
            });
            $('#calendar2').fullCalendar({
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
            // Re-render the calendar when the modal is shown
            $('#active').on('shown.bs.modal', function () {
                $('#calendar').fullCalendar('render');
                $('#calendar').fullCalendar('refetchEvents');
            });
            $('#inactive').on('shown.bs.modal', function () {
                $('#calendar2').fullCalendar('render');
                $('#calendar2').fullCalendar('refetchEvents');
            });
        });

    </script>
    <script>
        function sortTable(columnIndex) {
            var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
            table = document.getElementById("mentorTable");
            switching = true;
            dir = "asc"; // Hướng sắp xếp ban đầu là tăng dần (asc)

            // Vòng lặp sẽ chạy cho đến khi không còn cần chuyển đổi vị trí các hàng
            while (switching) {
                switching = false;
                rows = table.rows;
                // Lặp qua tất cả các hàng, trừ hàng tiêu đề
                for (i = 1; i < (rows.length - 1); i++) {
                    shouldSwitch = false;
                    x = rows[i].getElementsByTagName("TD")[columnIndex];
                    y = rows[i + 1].getElementsByTagName("TD")[columnIndex];

                    // So sánh các hàng dựa trên hướng sắp xếp
                    if (dir === "asc") {
                        if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                            shouldSwitch = true;
                            break;
                        }
                    } else if (dir === "desc") {
                        if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                            shouldSwitch = true;
                            break;
                        }
                    }
                }

                if (shouldSwitch) {
                    // Đổi vị trí các hàng
                    rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                    switching = true;
                    switchcount++;
                } else {
                    // Nếu không có chuyển đổi nào, đổi hướng sắp xếp
                    if (switchcount === 0 && dir === "asc") {
                        dir = "desc";
                        switching = true;
                    }
                }
            }
        }

    </script>
    
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
    <!-- Mirrored from educhamp.themetrades.com/demo/admin/index.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 22 Feb 2019 13:09:05 GMT -->
</html>
