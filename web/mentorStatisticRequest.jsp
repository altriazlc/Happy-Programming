<%-- 
    Document   : mentorDashboard
    Created on : 14 thg 10, 2024, 15:17:42
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
        <title>Happy Programing : Statistic Request Mentor </title>

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

        <jsp:include page="headerMentor.jsp" />
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
                    <div class="col-md-6 col-lg-3 col-xl-3 col-sm-6 col-12">
                        <div class="widget-card widget-bg1">					 
                            <div class="wc-item">
                                <h4 class="wc-title">
                                    Currently Open Request
                                </h4>
                                <span class="wc-des">
                                    Number of currently Open request
                                </span>
                                <span class="wc-stats">
                                    <span class="counter">${requestScope.invitedRequest}</span> 
                                </span>		
                                <div class="progress wc-progress">
                                    <div class="progress-bar" role="progressbar" style="width: 100%;" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"></div>
                                </div>

                            </div>				      
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-3 col-xl-3 col-sm-6 col-12">
                        <div class="widget-card widget-bg2">					 
                            <div class="wc-item">
                                <h4 class="wc-title">
                                    Currently Accepted Request
                                </h4>
                                <span class="wc-des">
                                    Number of currently accepted request
                                </span>
                                <span class="wc-stats counter">
                                    ${requestScope.acceptedRequest} 
                                </span>		
                                <div class="progress wc-progress">
                                    <div class="progress-bar" role="progressbar" style="width: 100%;" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"></div>
                                </div>
                            </div>				      
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-3 col-xl-3 col-sm-6 col-12">
                        <div class="widget-card widget-bg3">					 
                            <div class="wc-item">
                                <h4 class="wc-title">
                                    Canceled Request
                                </h4>
                                <span class="wc-des">
                                    Number of canceled request 
                                </span>
                                <span class="wc-stats counter">
                                    ${requestScope.canceledRequest} 
                                </span>		
                                <div class="progress wc-progress">
                                    <div class="progress-bar" role="progressbar" style="width: 100%;" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"></div>
                                </div>
                            </div>				      
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-3 col-xl-3 col-sm-6 col-12">
                        <div class="widget-card widget-bg4">					 
                            <div class="wc-item">
                                <h4 class="wc-title">
                                    Rating Star 
                                </h4>
                                <span class="wc-des">
                                    Rating Star By Mentees
                                </span>
                                <span class="wc-stats">
                                    <span class="counter">${requestScope.rateAve}</span>/5
                                </span>	

                                <ul class="cours-star">
                                    <c:forEach var="i" begin="1" end="${requestScope.rateAve}">
                                        <li class="active"><i class="fa fa-star"></i></li>
                                        </c:forEach>
                                        <c:forEach var="j" begin="1" end="${5-(requestScope.rateAve)}">
                                        <li><i class="fa fa-star"></i></li>
                                        </c:forEach>

                                </ul>


                            </div>				      
                        </div>
                    </div>
                </div>
                <!-- Card END -->
                <div class="row">
                    <!-- Your Profile Views Chart -->
                    <div class="col-lg-8 m-b30">
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
                                                <th scope="col">Title</th>
                                                <th scope="col">Mentee</th>
                                                <th scope="col">Email</th>
                                                <th scope="col">Start Time</th>
                                                <th scope="col">Framework</th>
                                                <th scope="col">Price</th>
                                                <th scope="col">Status</th>
<!--                                                <th scope="col">Action</th>-->
                                            </tr>
                                        </thead>
                                        <tbody>

                                            <c:forEach items="${requestScope.requestList}" var="c"  varStatus="status">

                                                <tr>
                                                    <th class="align-middle" scope="row">${status.index + 1}</th>
                                                    <td class="align-middle">${c.title}</td>
                                                    <c:forEach items="${requestScope.menteeList}" var="v">
                                                        <c:if test="${c.menteeId==v.menteeId}">
                                                            <td class="align-middle" style="max-width: 20px;word-wrap: break-word;"><a href="#" class="text-primary">${v.username}</a></td>
                                                            <td class="align-middle" style="max-width: 200px;word-wrap: break-word;">${v.email}</td>
                                                        </c:if>
                                                    </c:forEach>

                                                    <td class="align-middle">${c.startDate}</td>
                                                    <td class="align-middle">${c.framework}</td>
                                                    <td class="align-middle"><b class="text-black-50">₫${c.price}</b></td>
                                                    <td class="align-middle" style="max-width: 200px;word-wrap: break-word;">${c.status}</td>     

<!--                                                    <td class="align-middle">
                                                        <span class="new-users-btn">
                                                            <a href="#" class="btn button-sm outline">Detail</a>
                                                        </span>
                                                    </td>-->
                                                </tr>
                                            </c:forEach>



                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 m-b30">
                        <div class="widget-box">
                            <div class="wc-title">
                                <h4>Percentage of requests %</h4>
                            </div>
                            <div class="widget-inner" style="height: 100%">
                                <canvas id="PieChart"></canvas>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-8 m-b30">
                        <div class="widget-box">
                            <div class="wc-title">
                                <h4>Statistic of Rating Skills</h4>
                            </div>
                            <div class="widget-inner" style="height: 100%">
                                <canvas id="LineChart"></canvas>
                            </div>
                        </div> 
                    </div>
                    <!-- Your Profile Views Chart END-->
                    <div class="col-lg-4 m-b30">
<!--                        <div class="widget-box">
                            <div class="wc-title">
                                <h4>Notifications</h4>
                            </div>
                            <div class="widget-inner">
                                <div class="noti-box-list">
                                    <ul>
                                        <li>
                                            <span class="notification-icon dashbg-gray">
                                                <i class="fa fa-check"></i>
                                            </span>
                                            <span class="notification-text">
                                                <span>Sneha Jogi</span> sent you a message.
                                            </span>
                                            <span class="notification-time">
                                                <a href="#" class="fa fa-close"></a>
                                                <span> 02:14</span>
                                            </span>
                                        </li>
                                        <li>
                                            <span class="notification-icon dashbg-yellow">
                                                <i class="fa fa-shopping-cart"></i>
                                            </span>
                                            <span class="notification-text">
                                                <a href="#">Your order is placed</a> sent you a message.
                                            </span>
                                            <span class="notification-time">
                                                <a href="#" class="fa fa-close"></a>
                                                <span> 7 Min</span>
                                            </span>
                                        </li>
                                        <li>
                                            <span class="notification-icon dashbg-red">
                                                <i class="fa fa-bullhorn"></i>
                                            </span>
                                            <span class="notification-text">
                                                <span>Your item is shipped</span> sent you a message.
                                            </span>
                                            <span class="notification-time">
                                                <a href="#" class="fa fa-close"></a>
                                                <span> 2 May</span>
                                            </span>
                                        </li>
                                        <li>
                                            <span class="notification-icon dashbg-green">
                                                <i class="fa fa-comments-o"></i>
                                            </span>
                                            <span class="notification-text">
                                                <a href="#">Sneha Jogi</a> sent you a message.
                                            </span>
                                            <span class="notification-time">
                                                <a href="#" class="fa fa-close"></a>
                                                <span> 14 July</span>
                                            </span>
                                        </li>
                                        <li>
                                            <span class="notification-icon dashbg-primary">
                                                <i class="fa fa-file-word-o"></i>
                                            </span>
                                            <span class="notification-text">
                                                <span>Sneha Jogi</span> sent you a message.
                                            </span>
                                            <span class="notification-time">
                                                <a href="#" class="fa fa-close"></a>
                                                <span> 15 Min</span>
                                            </span>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>-->
                    </div>
                    
                </div>
            </div>
        </main>
        <div class="ttr-overlay"></div>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

        <script>
            const ctx = document.getElementById('PieChart');
            const accept = '${(requestScope.acceptedRequest)/requestScope.countRequest*100}';
            const invited = '${(requestScope.invitedRequest)/requestScope.countRequest*100}';
            const canceled = '${(requestScope.canceledRequest)/requestScope.countRequest*100}';
            new Chart(ctx, {
                type: 'doughnut',
                data: {
                    labels: ['Completed Request', 'Canceled Request', 'Open Request'],
                    datasets: [{
                            label: ' %',
                            data: [accept, canceled, invited],
                            borderWidth: 1
                        }], hoverOffset: 4
                }, options: {
                    plugins: {
                        legend: {
                            display: true,
                            position: 'bottom'
                        }
                    }
                }


            });
        </script>
        <script>
            const ctxt = document.getElementById('LineChart');
            const labels = JSON.parse('${requestScope.key}');
            const data = JSON.parse('${requestScope.values}');

            new Chart(ctxt, {
                type: 'radar',
                data: {
                    labels: labels,
                    datasets: [{
                            label: 'Rating Skills',
                            data: data,
                            fill: false,
                            borderColor: 'rgb(75, 192, 192)'
                        }], hoverOffset: 4
                },
                options: {
                    scales: {
                        r: {
                            pointLabels: {
                                font: {
                                    size: 16
                                }
                            },
                            suggestedMin: 0, // Giới hạn tối thiểu
                            suggestedMax: 5, // Giới hạn tối đa
                            ticks: {
                                stepSize: 1 // Chỉ lấy số nguyên, bước nhảy là 1
                            }
                        }
                    }
                }


            });
        </script>

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

    <!-- Mirrored from educhamp.themetrades.com/demo/admin/index.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 22 Feb 2019 13:09:05 GMT -->
</html>
