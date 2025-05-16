
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
    Document   : Home
    Created on : 11 thg 9, 2024, 10:41:32
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">

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
        <meta property="og:title" content="EduChamp : Education HTML Template" />
        <meta property="og:description" content="EduChamp : Education HTML Template" />
        <meta property="og:image" content="" />
        <meta name="format-detection" content="telephone=no">

        <!-- FAVICONS ICON ============================================= -->
        <link rel="icon" href="assets/images/favicon.ico" type="image/x-icon" />
        <link rel="shortcut icon" type="image/x-icon" href="assets/images/favicon.png" />

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

        <!-- TYPOGRAPHY ============================================= -->
        <link rel="stylesheet" type="text/css" href="assets/css/typography.css">

        <!-- SHORTCODES ============================================= -->
        <link rel="stylesheet" type="text/css" href="assets/css/shortcodes/shortcodes.css">

        <!-- STYLESHEETS ============================================= -->
        <link rel="stylesheet" type="text/css" href="assets/css/style.css">
        <link class="skin" rel="stylesheet" type="text/css" href="assets/css/color/color-1.css">

        <!-- REVOLUTION SLIDER CSS ============================================= -->
        <link rel="stylesheet" type="text/css" href="assets/vendors/revolution/css/layers.css">
        <link rel="stylesheet" type="text/css" href="assets/vendors/revolution/css/settings.css">
        <link rel="stylesheet" type="text/css" href="assets/vendors/revolution/css/navigation.css">
        <!-- REVOLUTION SLIDER END -->	
    </head>
    <body id="bg">
        <div class="page-wraper">

            <!-- Header Top ==== -->
            <header class="header rs-nav">
                <div class="top-bar">
                    <div class="container">
                        <div class="row d-flex justify-content-between">
                            <div class="topbar-left">
                                <ul>
                                    <li><a href="faq.jsp"><i class="fa fa-question-circle"></i>Ask a Question</a></li>
                                    <li><a href="javascript:;"><i class="fa fa-envelope-o"></i>elearningfptedu@gmail.com</a></li>
                                </ul>
                            </div>
                            <div class="topbar-right">
                                <ul>

                                    <li>
                                        <select class="header-lang-bx">
                                            <option data-icon="flag flag-uk">English</option>
                                            <option data-icon="flag flag-us">Vietnamese</option>
                                        </select>
                                    </li>
                                    <c:if test="${sessionScope.acc == null}">
                                        <li><a href="signin">Sign In</a></li>
                                        <li><a href="signup">Sign Up</a></li>
                                        </c:if>
                                        <c:if test="${sessionScope.acc != null}">
                                        <li>Welcome ${sessionScope.acc.username} !</li>
                                        </c:if>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="sticky-header navbar-expand-lg">
                    <div class="menu-bar clearfix">
                        <div class="container clearfix">
                            <!-- Header Logo ==== -->
                            <div class="menu-logo">
                                <a href="home"><img src="https://daihoc.fpt.edu.vn/wp-content/uploads/2023/04/cropped-cropped-2021-FPTU-Long.png" alt=""></a>
                            </div>
                            <!-- Mobile Nav Button ==== -->
                            <button class="navbar-toggler collapsed menuicon justify-content-end" type="button" data-toggle="collapse" data-target="#menuDropdown" aria-controls="menuDropdown" aria-expanded="false" aria-label="Toggle navigation">
                                <span></span>
                                <span></span>
                                <span></span>
                            </button>
                            <!-- Author Nav ==== -->
                            <div class="secondary-menu">
                                <div class="secondary-inner">
                                    <ul>
                                        <c:if test="${sessionScope.acc == null}">
<!--                                            <li><a href="javascript:;" class="btn-link"><i class="fa fa-facebook"></i></a></li>
                                            <li><a href="javascript:;" class="btn-link"><i class="fa fa-google-plus"></i></a></li>
                                            <li><a href="javascript:;" class="btn-link"><i class="fa fa-linkedin"></i></a></li>-->
                                            <!-- Search Button ==== -->
                                            <li class="search-btn"><button id="quik-search-btn" type="button" class="btn-link"><i class="fa fa-search"></i></button></li>
                                                </c:if>
                                                <c:if test="${sessionScope.acc != null}">
                                            <li>

                                                <div data-toggle="dropdown" aria-expanded="false">
                                                    <c:choose>
                                                        <c:when test="${sessionScope.acc.roleId == 1}">
                                                            <c:if test="${sessionScope.cvactive.cvId==null}">
                                                                <span  class="ttr-user-avatar"><img class="rounded-circle" src="assets\images\userprofile.png" style="width: 45px;height: 45px;object-fit: cover;"></span>
                                                                </c:if>
                                                                <c:if test="${sessionScope.cvactive.cvId!=null}">
                                                                <span  class="ttr-user-avatar"><img class="rounded-circle" src="getCVimage?id=${sessionScope.cvactive.cvId}" style="width: 45px;height: 45px;object-fit: cover;"></span>
                                                                </c:if>
                                                            </c:when>
                                                            <c:when test="${sessionScope.acc.roleId == 2}">
                                                                <c:if test="${mentee.base64FileImage==null}">
                                                                <span  class="ttr-user-avatar"><img class="rounded-circle" src="assets\images\userprofile.png" style="width: 45px;height: 45px;object-fit: cover;"></span>
                                                                </c:if>
                                                                <c:if test="${mentee.base64FileImage!=null}">
                                                                <span  class="ttr-user-avatar"><img class="rounded-circle" src="data:image/jpeg;base64,${mentee.base64FileImage}" style="width: 45px;height: 45px;object-fit: cover;"></span>
                                                                </c:if>
                                                            </c:when>
                                                            <c:when test="${sessionScope.acc.roleId == 3}">
                                                            <span  class="ttr-user-avatar"><img class="rounded-circle" src="assets\images\userprofile.png" style="width: 45px;height: 45px;object-fit: cover;"></span>
                                                            </c:when>
                                                            <c:when test="${sessionScope.acc.roleId == 4}">
                                                            <span  class="ttr-user-avatar"><img class="rounded-circle" src="assets\images\userprofile.png" style="width: 45px;height: 45px;object-fit: cover;"></span>
                                                            </c:when>
                                                            <c:otherwise>
                                                            exception!!
                                                        </c:otherwise>
                                                    </c:choose>

                                                    &nbsp;&nbsp;<span>${sessionScope.acc.username}</span>
                                                </div>

                                                <div class="dropdown-menu">
                                                    <c:if test="${sessionScope.acc.roleId == 1}">
                                                        <a class="dropdown-item" href="viewprofilecv?id=${sessionScope.mentor.mentorId}">My profile</a>
                                                    </c:if>
                                                    <c:if test="${sessionScope.acc.roleId == 2}">
                                                        <a class="dropdown-item" href="updateProfile">My profile</a>
                                                    </c:if>
                                                    <a class="dropdown-item" href="changeloggingpassword">Change Password</a>
                                                    <a class="dropdown-item" href="payment">Balance: <span class="text-green"><fmt:formatNumber value="${sessionScope.wallet.balance}" type="number" maxFractionDigits="2" /> ₫</span> </a>
                                                    <a class="dropdown-item" href="logout">Logout</a>

                                                </div>

                                            </li>
                                        </c:if>

                                    </ul>
                                </div>
                            </div>
                            <!-- Search Box ==== -->
                            <div class="nav-search-bar">
                                <form action="searchskill">
                                    <input name="skill" value="" type="text" class="form-control" placeholder="Type to search">
                                    <span><i class="ti-search"></i></span>
                                </form>
                                <span id="search-remove"><i class="ti-close"></i></span>
                            </div>
                            <!-- Navigation Menu ==== -->



                            <div class="menu-links navbar-collapse collapse justify-content-start" id="menuDropdown">
                                <div class="menu-logo">
                                    <a href="home"><img src="https://daihoc.fpt.edu.vn/wp-content/uploads/2023/04/cropped-cropped-2021-FPTU-Long.png" alt=""></a>
                                </div>
                                <ul class="nav navbar-nav">
                                    
                                       
                                            <li class="active"><a href="home;">Home</a>

                                            </li>
                                            <li><a href="javascript:;">Pages <i class="fa fa-chevron-down"></i></a>
                                                <ul class="sub-menu">
                                                    <li><a href="contact.jsp">About</a></li>
                                                    <li><a href="javascript:;">Event<i class="fa fa-angle-right"></i></a>
                                                        <ul class="sub-menu">
                                                            <li><a href="events.jsp">Event</a></li>
                                                        </ul>
                                                    </li>

                                                    <li><a href="contact.jsp">Contact Us</a></li>
                                                    <li><a href="faq.jsp">FAQ's</a></li>
                                                </ul>
                                            </li>
                                            <li class="add-mega-menu"><a href="javascript:;">Our Course <i class="fa fa-chevron-down"></i></a>
                                                <ul class="sub-menu add-menu">
                                                    <li class="add-menu-left">
                                                        <h5 class="menu-adv-title">Our Courses</h5>
                                                        <ul>
                                                            <li><a href="skillhome">Courses </a></li>
                                                                <c:if test="${sessionScope.acc.roleId == 2}">

                                                                <li><a href="suggestMentor">Suggest mentor</a></li>
                                                                </c:if>
                                                        </ul>
                                                    </li>
                                                    <li class="add-menu-right">
                                                        <a href="https://daihoc.fpt.edu.vn/">

                                                            <img src="assets\images\fptuavatar.jpg" alt=""/>
                                                        </a>
                                                    </li>
                                                </ul>
                                            </li>
                                        
                                    <c:choose>


                                        <c:when test="${sessionScope.acc.roleId == 4}">
                                            <li class="nav-dashboard"><a href="javascript:;">Dashboard <i class="fa fa-chevron-down"></i></a>
                                                <ul class="sub-menu">
                                                    <li><a href="paymentmanager">Dashboard</a></li>
                                                    <li><a href="cvmanager">CV manager</a></li>
                                                    <li><a href="paymentmanager">Payment Manager</a></li>
                                                </ul>
                                            </li>
                                        </c:when>
                                        <c:when test="${sessionScope.acc.roleId == 3}">
                                            <li class="nav-dashboard"><a href="javascript:;">Dashboard <i class="fa fa-chevron-down"></i></a>
                                                <ul class="sub-menu">
                                                    <li><a href="admindashboard">Dashboard</a></li>
                                                    <li><a href="mentorListAdmin">Mentor List</a></li>
                                                    <li><a href="SkillListAdmin">Skill</a></li>
                                                    <li><a href="menteeListAdmin">Mentee List</a></li>
                                                    <li><a href="requestListAdmin">Request List</a></li>
                                                </ul>
                                            </li>
                                        </c:when>

                                        <c:when test="${sessionScope.acc.roleId == 2}">
                                            <li><a href="javascript:;">My Course <i class="fa fa-chevron-down"></i></a>
                                                <ul class="sub-menu">
                                                    <li><a href="slotview">Schedule</a></li>
                                                    <li><a href="listmentor">Rate & Comment</a></li>



                                                </ul>
                                            </li>
                                            <!-- Mentee Dashboard -->
                                            <li class="nav-dashboard"><a href="javascript:;">Dashboard <i class="fa fa-chevron-down"></i></a>
                                                <ul class="sub-menu">
                                                    <li><a href="statisticrequestbymentee">Statistic Request</a></li>
                                                    <li><a href="listrequestbymentee">List Request</a></li>
                                                    <li><a href="updateProfile">Update Profile</a></li>

                                                    <!--                                                    <li><a href="#">Wishlist</a></li>-->
                                                    <li><a href="payment">Deposit</a></li>


                                                </ul>
                                            </li>
                                        </c:when>




                                        <c:when test="${sessionScope.acc.roleId == 1}">
                                            <!-- Mentor Dashboard -->
                                            <li class="nav-dashboard"><a href="javascript:;">Dashboard <i class="fa fa-chevron-down"></i></a>
                                                <ul class="sub-menu">
                                                    <li><a href="mentorStatisticRequest?id=${sessionScope.mentor.mentorId}">Statistic Request</a></li>
                                                    <li><a href="listrequestofmentor">Inviting Request</a></li>
                                                    <li><a href="cvlist?id=${sessionScope.mentor.mentorId}">CV</a></li>
                                                    <li><a href="createslot?id=${sessionScope.mentor.mentorId}">Weekly Slot</a></li>
                                                    <li><a href="viewprofilecv?id=${sessionScope.mentor.mentorId}">My Profile</a></li>
                                                </ul>
                                            </li>
                                        </c:when>
                                        <c:otherwise>

                                        </c:otherwise>
                                    </c:choose>
                                </ul>
                                <div class="nav-social-link">
                                    <a href="javascript:;"><i class="fa fa-facebook"></i></a>
                                    <a href="javascript:;"><i class="fa fa-google-plus"></i></a>
                                    <a href="javascript:;"><i class="fa fa-linkedin"></i></a>
                                </div>
                            </div>
                            <!-- Navigation Menu END ==== -->
                        </div>
                    </div>
                </div>
            </header>
            <!-- Header Top END ==== -->


            <button class="back-to-top fa fa-chevron-up" ></button>
        </div>


    </body>
    <!-- External JavaScripts -->

</html>


