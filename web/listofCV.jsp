<%-- 
    Document   : updateCV
    Created on : 17 thg 9, 2024, 12:33:22
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <!-- Mirrored from educhamp.themetrades.com/demo/admin/teacher-profile.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 22 Feb 2019 13:11:35 GMT -->
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
        <title>List CV of mentor</title>

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

        <!--Main container start -->
        <main class="ttr-wrapper">
            <div class="container-fluid">
                <div class="db-breadcrumb">
                    <h4 class="breadcrumb-title">List CV of mentor</h4>
                    <ul class="db-breadcrumb-list">
                        <li><a href="#"><i class="fa fa-home"></i>Home</a></li>
                        <li>List CV of mentor</li>
                    </ul>
                </div>	
                <div class="row">

                    <!-- Your Profile Views Chart -->
                    <div class="col-lg-12 m-b30">

                        <div class="widget-box">
                            <div class="wc-title">
                                <h4>List CV of mentor </h4>

                            </div>
                            <div class="widget-inner">
                                <a href="cvcreate?id=${requestScope.id}" class="btn btn-secondry" style="">Create CV+</a>
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
                                    <c:if test="${empty requestScope.listcv}">
                                        <a href="#" class="alert alert-primary text-center d-inline-block" role="alert" >
                                            You currently do not have any CVs. Please create a new CV to get started.
                                        </a>
                                    </c:if>
                                </div>
                                <div class="courses-filter">
                                    <div class="clearfix">
                                        <ul id="masonry" class="ttr-gallery-listing magnific-image row ">
                                            <c:forEach items="${requestScope.listcv}" var="c">
                                                <li class="action-card col-xl-2 col-lg-6 col-md-12 col-sm-6">
                                                    <div class="cours-bx">
                                                        <div class="action-box" style="height: 300px;">
                                                            <img src="getCVimage?id=${c.cvId}" alt="" style="width: 100%; height: 100%; object-fit: cover;">

                                                        </div>
                                                        <div class="info-bx text-center" >
                                                            <c:if test="${c.status eq'active'}">
                                                                <h5><a href="#">CV ID: ${c.cvId} </a><span class="text-green">${c.status}</span></h5>
                                                                </c:if>
                                                                <c:if test="${c.status eq'inactive'}">
                                                                <h5><a href="#">Draft: ${c.cvId} </a><span class="text-red">${c.status}</span></h5>
                                                                </c:if>
                                                            <span>Last change:</span>
                                                            <br/>
                                                            <span> ${c.createDate}</span>
                                                        </div>
                                                        <div class="cours-more-info" style="height: 50px">
                                                            <c:if test="${c.status eq'inactive'}">
                                                            <a href="cvupdate?id=${c.cvId}" class="review btn" style=" display: flex; align-items: center; justify-content: center; ">
                                                                Update
                                                            </a>
                                                                
                                                            <a href="#" onclick="delete1('${c.cvId}')" class="price btn" style=" display: flex; align-items: center; justify-content: center ">
                                                                Delete
                                                            </a>
                                                            </c:if>
                                                            <c:if test="${c.status eq'active'}">
                                                            <a href="cvupdate?id=${c.cvId}" class="review btn" style=" display: flex; align-items: center; justify-content: center; ">
                                                                Update
                                                            </a>
                                                                
                                                            <a href="#" onclick="delete1('${c.cvId}')" class="price btn" style=" display: flex; align-items: center; justify-content: center ">
                                                                Delete
                                                            </a>
                                                            </c:if>
                                                        </div>
                                                    </div>
                                                </li>

                                            </c:forEach>


                                        </ul>
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
        <!-- comment<script src='assets/vendors/switcher/switcher.js'></script> -->
        <script>
                                                                function delete1(id) {
                                                                    if (confirm('are u sure to delete id' + id + '?')) {
                                                                        window.location = '/happy_programming/cvdelete?id=' + id;
                                                                    }
                                                                }
        </script>
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
            //file path
            const input = document.getElementById('avatar');
            const filePathDisplay = document.getElementById('file-path');

            input.addEventListener('change', function () {
                const fileName = input.files[0].name; // Lấy tên file được chọn
                filePathDisplay.textContent = "File selected: " + fileName; // Hiển thị tên file
            });

        </script>
    </body>

    <!-- Mirrored from educhamp.themetrades.com/demo/admin/teacher-profile.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 22 Feb 2019 13:11:35 GMT -->
</html>
