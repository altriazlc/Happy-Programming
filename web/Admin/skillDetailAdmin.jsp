<%-- 
    Document   : skillDetailAdmin
    Created on : Nov 8, 2024, 2:05:04 PM
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
                    <ul class="db-breadcrumb-list">
                        <li><a href="#"><i class="fa fa-home"></i>Home</a></li>
                        <li>User</li>
                        <li>Skill</li>

                    </ul>
                </div>	
                <div class="row">
                    <!-- Your Profile Views Chart -->
                    <div class="col-lg-12 m-b30">
                        <div class="widget-box" >
                            
                            <div class="widget-inner">
                                <form method="post" action="skillDetailAdmin">
                                    <div class="row">
                                        <div class="col-12">
                                            <div class="ml-auto">
                                                <h5>1. Skill info</h3>
                                            </div>
                                        </div>

                                        <div class="form-group col-6">
                                            <label class="col-form-label">Skill ID</label>
                                            <div>
                                                <input class="form-control" type="text"  name="skillID" value="${curSkill.skillId}" readonly>
                                            </div>
                                        </div>
                                        <div class="form-group col-6">
                                            <label class="col-form-label">Skill Name</label>
                                            <div>
                                                <input class="form-control" type="text"  name="name" value="${curSkill.skillName}" readonly>
                                            </div>
                                            <div><p style="color: red">${requestScope.error}</p></div>
                                        </div>
                                        <div class="form-group col-6">
                                            <label class="col-form-label">Skill Status</label>
                                            <div>
                                                <input type="submit" value="${curSkill.status}">
                                            </div>
                                            <div><label>Image:</label></div>
                                            <img src="data:image/jpeg;base64,${curSkill.base64ImageFile}" class="img-fluid" style="max-height: 200px; max-width: 200px">
                                        </div>
                                        <div class="form-group col-6">
                                            <label class="col-form-label">Skill description</label>
                                            <div>
                                                <textarea class="form-control" name="description" readonly>${curSkill.description}</textarea>
                                            </div>
                                        </div>
                                        <div>
                                            <a href="updateSkillAdmin?updateId=${curSkill.skillId}">Update skill</a>
                                        </div>
                                    </div>
                                </form>
                                <hr class="hr" />
                                <div class="row">
                                    <div class="col-6">
                                        <div class="col-12">
                                            <div class="ml-auto">
                                                <h5>2. Request</h3>
                                            </div>
                                        </div>
                                        <div class="table-vertical-scroll table-responsive" style="max-height: 600px; overflow-y: auto;">
                                            <table class="table table-hover" id="mentorTable">
                                                <thead class="thead-light">
                                                    <tr>
                                                        <th scope="col" onclick="sortTable(0)"><a href="#" >STT</a></th>
                                                        <th scope="col" onclick="sortTable(1)"><a href="#" >ID</a></th>
                                                        <th scope="col" onclick="sortTable(2)"><a href="#" >Title</a></th>
                                                        <th scope="col" onclick="sortTable(3)"><a href="#" >Status</a></th>

                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:set var="sttReq" value="1" />
                                                    <c:forEach items="${requestScope.liReq}" var="req">
                                                        <tr>
                                                            <td class="align-middle">${sttReq}</td>
                                                            <c:set var="sttReq" value="${sttReq + 1}" />
                                                            <td class="align-middle"><a href="requestDetailAdmin?requestID=${req.requestId}">${req.requestId}</a></td>
                                                            <td class="align-middle">${req.title}</td>                                                
                                                            <td class="align-middle">${req.status}</td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                    <div class="col-6">
                                        <div class="col-12">
                                            <div class="ml-auto">
                                                <h5>3. Mentor</h3>
                                            </div>
                                        </div>
                                        <div class="table-vertical-scroll table-responsive" style="max-height: 600px; overflow-y: auto;">
                                            <table class="table table-hover" id="mentorTable">
                                                <thead class="thead-light">
                                                    <tr>
                                                        <th scope="col" onclick="sortTable(0)"><a href="#" >STT</a></th>
                                                        <th scope="col" onclick="sortTable(1)"><a href="#" >ID</a></th>
                                                        <th scope="col" onclick="sortTable(2)"><a href="#" >AccoutName</a></th>
                                                        <th scope="col" onclick="sortTable(3)"><a href="#" >FullName</a></th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:set var="sttMen" value="1" />
                                                    <c:forEach items="${requestScope.liMentor}" var="mentor">
                                                        <tr>
                                                            <td class="align-middle">${sttMen}</td>
                                                            <c:set var="sttMen" value="${sttMen+1}" />
                                                            <td class="align-middle">${mentor.mentorId}</td>
                                                            <td class="align-middle">${mentor.username}</td>                                                
                                                            <td class="align-middle">${mentor.fullName}</td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
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



