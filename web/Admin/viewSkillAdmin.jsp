<%-- 
    Document   : viewSkill
    Created on : Sep 17, 2024, 4:47:52 PM
    Author     : tuong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

        <jsp:include page="headerAdmin.jsp" />

        <!--Main container start -->
        <main class="ttr-wrapper">
            <div class="container-fluid">
                <div class="db-breadcrumb">
                    <h4 class="breadcrumb-title">Skill</h4>
                    <ul class="db-breadcrumb-list">
                        <li><a href="#"><i class="fa fa-home"></i>Home</a></li>
                        <li>Skill</li>
                    </ul>
                </div>	
                <div class="row">
                    <!-- Your Profile Views Chart -->
                    <div class="col-lg-12 m-b30">
                        <div class="widget-box" >
                            <div class="wc-title" style="display: flex">
                                <div class="col-md-4">
                                    <h4>View Skill</h4>
                                </div>
                                <div class="mail-search-bar col-md-4">
                                    <form method="get" action="adminSearchSkill" style="display: flex; align-items: center;">
                                        <input type="text" name="search" placeholder="Search" class="form-control" style="flex: 1; margin-right: 10px;">
                                        <button type="submit" class="fa fa-search" style="padding: 10px;">
                                    </form>
                                </div>
                                <div class="col-md-4" style="text-align: right">
                                    <div class="btn-secondry add-item m-r5">
                                        <a href="addSkillAdmin" style="text-decoration: none; color: inherit;"><i class="fa fa-fw fa-plus-circle"></i>Add Skill</a>
                                    </div>
                                </div>

                            </div>

                            <div class="widget-inner">
                                <table class="table-bordered table-hover">

                                    <tr>
                                        <th>STT</th>
                                        <th>Skill ID</th>
                                        <th>Skill Image</th>
                                        <th>Skill Name</th>
                                        <th>Create date</th>
                                        <th>Status</th>
                                        <th style="text-align: center">Edit</th>
                                    </tr>
                                    <c:set value="${requestScope.stt}" var="stt"></c:set>
                                    <c:forEach items="${requestScope.list}" var="c">
                                        <form action="SkillListAdmin" method="post">
                                            <input type="hidden" name="page" value="${requestScope.indexPage}">
                                            <input type="hidden" name="numDis" value="${requestScope.numDis}">

                                            <tr>
                                                <c:set var="stt" value="${stt + 1}" />
                                                <td>${stt}</td>
                                                <td><a href="skillDetailAdmin?skillID=${c.skillId}">${c.skillId}</a></td>
                                            <input type="hidden" name="id" value="${c.skillId}">
                                            <td><img src="data:image/jpeg;base64,${c.base64ImageFile}" style="max-height: 100px; max-width: 100px"></td>
                                            <td>${c.skillName}</td>
                                            <td>${c.createDate}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${c.status eq 'Active'}">
                                                        <div class="btn-secondry add-item m-r5" style="background-color: #00a834">
                                                            <button type="submit" style="text-decoration: none; color: inherit; border: none; background: none; padding: 0; font: inherit; cursor: pointer;" >${c.status}</button>
                                                        </div>
                                                    </c:when>
                                                    <c:otherwise >
                                                        <div class="btn-secondry add-item m-r5">
                                                            <button type="submit" style="text-decoration: none; color: inherit; border: none; background: none; padding: 0; font: inherit; cursor: pointer;">${c.status}</button>
                                                        </div>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td style="text-align: center">
                                                <a href="updateSkillAdmin?updateId=${c.skillId}">Update</a>&nbsp;&nbsp;
                                               
                                            </tr>
                                        </form>

                                    </c:forEach>
                                </table>
                                <c:set var="page" value="${requestScope.indexPage}"/>
                                <div class="pagination" style="display: flex">
                                    <div class="col-md-6" >
                                        <div class="col-md-4">
                                            <form action="SkillListAdmin" method="get">
                                                <select name="numDis" id="numDis" onchange="this.form.submit()">
                                                    <option value="5" ${numDis == 5 ? 'selected' : ''}>5</option>
                                                    <option value="10" ${numDis == 10 ? 'selected' : ''}>10</option>
                                                    <option value="20" ${numDis == 20 ? 'selected' : ''}>20</option>
                                                </select>
                                                <noscript><input type="submit" value="Submit"></noscript>
                                            </form>
                                        </div>
                                    </div>
                                    <div class="col-md-6" style="text-align: right">
                                        <c:forEach begin="${1}" end="${requestScope.numOfPage}" var="i">
                                            <a href="SkillListAdmin?page=${i}&numDis=${numDis}">${i}</a>
                                        </c:forEach>
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
        <script type="text/javascript">
            function doDelete(id) {
                if (confirm("Are you sure want to delete skill with ID = " + id)) {
                    window.location = "deleteSkillAdmin?id=" + id;
                }
            }
        </script>
    </body>

    <!-- Mirrored from educhamp.themetrades.com/demo/admin/add-listing.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 22 Feb 2019 13:09:05 GMT -->
</html>
