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
        <title>Update CV of mentor</title>

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
                    <h4 class="breadcrumb-title">Update CV of mentor</h4>
                    <ul class="db-breadcrumb-list">
                        <li><a href="#"><i class="fa fa-home"></i>Home</a></li>
                        <li>Update CV of mentor</li>
                    </ul>
                </div>	
                <div class="row">
                    <!-- Your Profile Views Chart -->
                    <div class="col-lg-12 m-b30">
                        <div class="widget-box">
                            <div class="wc-title">
                                <h4>Update CV of mentor</h4>
                            </div>
                            <div class="widget-inner">
                                <form class="edit-profile m-b30" action="cvupdate" method="post" enctype="multipart/form-data">
                                    <div class="row">
                                        <div class="col-12">
                                            <div class="ml-auto">
                                                <h3>1. Personal Details</h3>
                                            </div>
                                        </div>


                                        <div>
                                            <input class="form-control" type="hidden" name="mentorId" value="${requestScope.uFound.mentorId}" >
                                            <input class="form-control" type="hidden" name="cvId" value="${requestScope.cvId}" >

                                        </div>
                                        <div class="form-group col-12">
                                            <div class=" text-center">

                                                <img id="userAvatar" src="getCVimage?id=${requestScope.cvFound.cvId}" class="rounded-circle" alt="" style=" margin: 50px 10px;width: 150px;height: 150px;object-fit: cover;">
                                            </div>
                                            <div>
                                                <div style="text-align: center">
                                                    <label for="avatar" class="btn-secondry">Avatar file</label>
                                                    <span id="file-path"></span>
                                                    <input id="avatar" class="form-control" type="file" name="avatar" value="${requestScope.cvFound.avatar}" accept=".jpg, .jpeg, .png, .gif" hidden>
                                                    <% String errorMessage = request.getParameter("errorMessage"); %>
                                                    <% if (errorMessage != null) { %>
                                                    <div class="alert alert-danger"><%= errorMessage %></div>
                                                    <% } %>
                                                    <c:if test="${requestScope.error2!=null}">
                                                        <span class="help" style="color: red">Your file is too big, please choose file smaller than 5MB.</span>
                                                    </c:if>


                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group col-6">
                                            <label class="col-form-label">Full Name</label>
                                            <div>
                                                <input class="form-control" type="text" name="fullname" value="${requestScope.uFound.fullName}" required >
                                            </div>
                                        </div>
                                        <div class="form-group col-6">
                                            <label class="col-form-label">Account name</label>
                                            <div>
                                                <input class="form-control" type="text" name="username" value="${requestScope.uFound.username}" readonly>
                                            </div>
                                        </div>
                                        <div class="form-group col-6">
                                            <label class="col-form-label">Date of Birth</label>
                                            <div>
                                                <input class="form-control" type="date" name="dob" value="${requestScope.uFound.dateOfBirth}"required>
                                            </div>
                                        </div>
                                        <div class="form-group col-6">
                                            <label class="col-form-label">Email</label>
                                            <div>
                                                <c:if test="${requestScope.error==email_exists}">
                                                    <input class="form-control" type="email" name="email" value="${requestScope.email}" readonly>
                                                </c:if>
                                                <c:if test="${requestScope.error!=email_exists}">
                                                    <input class="form-control" type="email" name="email" value="" readonly>
                                                    <span class="help" style="color: red">Your email used by another user !</span>
                                                </c:if>
                                            </div>
                                        </div>
                                        <div class="form-group col-3">
                                            <label class="col-form-label">Phone number</label>
                                            <div>
                                                <input class="form-control" type="number" name="phone" value="${requestScope.uFound.phone}">
                                            </div>
                                        </div>
                                        <div class="form-group col-3">
                                            <label class="col-form-label">Sex</label>
                                            <div>
                                                <select name="gender" class="">
                                                    <option value="Male"<c:if test="${requestScope.uFound.gender == 'Male'}">selected</c:if>>Male</option>
                                                    <option value="Female"<c:if test="${requestScope.uFound.gender == 'Female'}">selected</c:if>>Female</option>
                                                    <option value="Other"<c:if test="${requestScope.uFound.gender == 'Other'}">selected</c:if>>Other</option>
                                                    </select>

                                                </div>
                                            </div>
                                            <div class="form-group col-6">
                                                <label class="col-form-label">Address</label>
                                                <div>
                                                    <input class="form-control" type="text" name="address" value="${requestScope.uFound.address}">
                                            </div>
                                        </div>

                                        <div class="seperator"></div>

                                        <div class="col-12 m-t20">
                                            <div class="ml-auto m-b5">
                                                <h3>2. CV detail</h3>
                                            </div>
                                        </div>
                                        <div class="form-group col-4">
                                            <label class="col-form-label">Profession</label>
                                            <div>
                                                <input class="form-control" type="text" name="profession" value="${requestScope.cvFound.jobProfession}">
                                            </div>
                                        </div>
                                        <div class="form-group col-2">
                                            <label class="col-form-label">Year of experience</label>
                                            <div>
                                                <input class="form-control" type="number" step="1" name="year" min="0" max="65" value="${requestScope.cvFound.yearOfExperience}" required>
                                            </div>
                                        </div>
                                        <div class="form-group col-6">
                                            <label class="col-form-label">Framework</label>
                                            <div>
                                                <input class="form-control" type="text" name="framework" value="${requestScope.cvFound.framework}">
                                            </div>
                                        </div>
                                        <div class="form-group col-3">
                                            <label class="col-form-label">Education</label>
                                            <div>
                                                <input class="form-control" type="text" name="education" value="${requestScope.cvFound.education}">
                                            </div>
                                        </div>
                                        <div class="form-group col-3">
                                            <label class="col-form-label">Price</label>
                                            <div>
                                                <input class="form-control" type="number" name="price" min="1000" max="100000000" step="500" value="${requestScope.cvFound.price}"required>
                                            </div>
                                        </div>
                                        <div class="form-group col-6">
                                            <label class="col-form-label">Activity</label>
                                            <div>
                                                <input class="form-control" type="text" name="activity" value="${requestScope.cvFound.activity}">
                                            </div>
                                        </div>

                                        <div class="form-group col-6">
                                            <label class="col-form-label">Profession Introduction</label>
                                            <div>
                                                <textarea class="form-control" name="professionIntroduction" >${requestScope.cvFound.professionIntroduction}</textarea>
                                            </div>
                                        </div>
                                        <div class="form-group col-6">
                                            <label class="col-form-label">Service description</label>
                                            <div>
                                                <textarea class="form-control" name="serviceDescription">${requestScope.cvFound.serviceDescription}</textarea>
                                            </div>
                                        </div>
                                        <div class="form-group col-3">
                                            <label class="col-form-label">Select skills</label><br/>
                                            <label class="col-form-label"><i>Delete Your Skills</i></label>
                                            <div>
                                                <ul class="border p-3 rounded mr-3 mb-3" class="category" style="list-style-type: none;display: flex; flex-wrap: wrap; gap: 10px;">
                                                    <c:forEach items="${requestScope.skillMentor}" var="c">
                                                        <li>
                                                            <div class="check-box">
                                                                <label class="col-form-label">
                                                                    <input type="checkbox" class="checkbox" name="deleteSkills" value="${c.skillId}">
                                                                    ${c.skillName} </label>
                                                            </div>
                                                        </li>
                                                    </c:forEach>
                                                </ul>
                                            </div>
                                        </div>
                                        <div class="form-group col-3">
                                            <br/>
                                            <label class="col-form-label"><i>Add Skills</i></label>
                                            <div>
                                                <ul class="border p-3 rounded mr-3 mb-3" class="category" style="list-style-type: none;display: flex; flex-wrap: wrap; gap: 10px;">
                                                    <c:forEach items="${requestScope.skillList}" var="c">
                                                        <li>
                                                            <div class="check-box">
                                                                <label class="col-form-label">
                                                                    <input type="checkbox" class="checkbox" name="addSkills" value="${c.skillId}">
                                                                    ${c.skillName}</label>
                                                            </div>
                                                        </li>
                                                    </c:forEach>
                                                </ul>
                                            </div>
                                        </div>
                                        <div class="form-group col-6">
                                            <label class="col-form-label">Archivement description</label>
                                            <div>
                                                <textarea class="form-control" name="experience">${requestScope.cvFound.experience}</textarea>
                                            </div>
                                        </div>


                                        <div class="m-form__seperator m-form__seperator--dashed m-form__seperator--space-2x"></div>


                                        <div class="col-12">
                                            <button type="submit" class="btn">Save changes</button>
                                            <a href="cvlist?id=${requestScope.cvFound.mentorId}" type="reset" style=" color: white" class="btn-secondry">Cancel</a>
                                        </div>
                                    </div>
                                </form>

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
