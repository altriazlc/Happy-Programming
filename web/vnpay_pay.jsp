<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta name="description" content="Happy Programing" />

        <!-- OG -->
        <meta property="og:title" content="Happy Programing" />
        <meta property="og:description" content="Happy Programing" />
        <meta property="og:image" content="" />
        <meta name="format-detection" content="telephone=no">

        <!-- FAVICONS ICON ============================================= -->
        <link rel="icon" href="assets/images/faviconV2.png" type="image/x-icon" />
        <link rel="shortcut icon" type="image/x-icon" href="assets/images/faviconV2.png" />
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />

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
        <style>
            .important-balance {
                font-size: 1.0em;
                font-weight: bold;
                color: #2c3e50;
                background-color: #ecf0f1;
                padding: 15px;
                border: 2px solid #3498db;
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                text-align: center;
                margin-top: 10px;
            }

            .important-balance label {
                font-size: 1.2em;
                color: #34495e;
                display: block;
                margin-bottom: 8px;
            }
        </style>

    </head>

    <body>
        <header class="header rs-nav">
            <jsp:include page="header.jsp" />
        </header>
        <div class="page-banner ovbl-dark" style="background-image:url(assets/images/banner/banner3.jpg);">
            <div class="container">
                <div class="page-banner-entry">
                    <h1 class="text-white">Payment</h1>
                </div>
            </div>
        </div>

        <div class="container" style="margin-top: 100px">
            <div class="header clearfix"></div>

            <div class="row">
                <!-- User Details Column -->
                <div class="col-md-6">
                    <div class="col-lg-7 col-md-7">
                        <form class="contact-bx ajax-form">
                            <div class="ajax-message"></div>
                            <div class="heading-bx left">
                                <h2 class="title-head">Wallet<span> Detail</span></h2>
                            </div>
                            <div class="row placeani">
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <label>Your Username</label>
                                        <input  class="form-control valid-character" value="${sessionScope.acc.username}" disabled/>
                                    </div>
                                </div>
                                <c:if test="${sessionScope.acc.roleId == 2}">
                                    <div class="col-lg-12">
                                        <div class="form-group">
                                            <label for="holdDisplay">Hold</label>
                                            <div id="holdDisplay" class="important-balance" disabled>
                                            </div>
                                        </div>
                                    </div>
                                </c:if>
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <label for="walletDisplay">Balance</label>
                                        <div id="walletDisplay" class="important-balance" disabled>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </form>
                    </div>
                </div>


                <!-- Charge Money Column -->
                <c:if test="${sessionScope.acc.roleId == 2}">
                    <div class="col-md-6"> 

                        <h3>Charge Money</h3>
                        <div class="table-responsive">
                            <form action="vnpayajax" id="frmCreateOrder" method="post">
                                <div class="form-group">
                                    <label for="amount">Amount</label>
                                    <input 
                                        class="form-control" 
                                        data-val="true" 
                                        data-val-number="The field Amount must be a number." 
                                        data-val-required="The Amount field is required." 
                                        id="amount"  
                                        name="amount" 
                                        type="number" 
                                        required 
                                        />
                                </div>

                                <h4>Pay through VNPAY</h4>
                                <img src="https://vnpay.vn/s1/statics.vnpay.vn/2023/6/0oxhzjmxbksr1686814746087.png" style="width: 100px; height: 100px" alt="vnpay">

                                <div class="form-group" hidden>
                                    <h5>Cách 1: Chuyển hướng sang Cổng VNPAY chọn phương thức thanh toán</h5>
                                    <input type="radio" id="bankCode" name="bankCode" value="VNP">
                                    <label for="bankCode">Cổng thanh toán VNPAYQR</label><br>

                                    <h5>Cách 2: Tách phương thức tại site của đơn vị kết nối</h5>
                                    <input type="radio" id="bankCode" name="bankCode" value="VNPAYQR">
                                    <label for="bankCode">Thanh toán bằng ứng dụng hỗ trợ VNPAYQR</label><br>

                                    <input type="radio" id="bankCode" name="bankCode" value="VNBANK">
                                    <label for="bankCode">Thanh toán qua thẻ ATM/Tài khoản nội địa</label><br>

                                    <input type="radio" id="bankCode" name="bankCode" value="INTCARD">
                                    <label for="bankCode">Thanh toán qua thẻ quốc tế</label><br>
                                </div>

                                <div class="form-group" hidden>
                                    <h5>Chọn ngôn ngữ giao diện thanh toán:</h5>
                                    <input type="radio" id="language" name="language" value="vn">
                                    <label for="language">Tiếng Việt</label><br>

                                    <input type="radio" id="language" name="language" value="en">
                                    <label for="language">Tiếng Anh</label><br>
                                </div>

                                <button type="submit" class="btn btn-default">Pay</button>
                            </form>   
                        </div>   

                    </div> 
                </c:if>
            </div>


            <div class="container mt-5">
                <div class="row">
                    <div class="col-12 mb-4">
                        <div class="card shadow-sm">
                            <div class="card-header bg-primary text-white text-center">
                                <h5 class="mb-0">Payment History</h5>
                            </div>
                            <div class="card-body p-0">
                                <div class="table-responsive">
                                    <table class="table table-striped mb-0">
                                        <thead class="table-light text-uppercase small">
                                            <tr>
                                                <th class="sortable" >Payment ID</th>
                                                <th class="sortable" >Payment Date</th>
                                                <th class="sortable" >Payment Type</th>
                                                <th class="sortable" >Amount</th>
                                            </tr>
                                        </thead>
                                        <tbody id="paymentTableBody">
                                            <c:forEach var="payments" items="${list}">
                                                <tr> 
                                                    <td>${payments.paymentId}</td>
                                                    <td data-date="${payments.paymentDate}" class="date-cell">${payments.paymentDate}</td>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${sessionScope.acc.roleId == 2}">
                                                                <c:choose>
                                                                    <c:when test="${payments.receiver == 'admin'}">Charging Money</c:when>
                                                                    <c:otherwise>Paying for Mentor</c:otherwise>
                                                                </c:choose>
                                                            </c:when>
                                                            <c:when test="${sessionScope.acc.roleId == 1}">Mentee Payment</c:when>
                                                        </c:choose>
                                                    </td>
                                                    <td>
                                                        <div class="d-flex align-items-center">
                                                            <c:choose>
                                                                <c:when test="${sessionScope.acc.roleId == 2}">
                                                                    <c:choose>
                                                                        <c:when test="${payments.receiver == 'admin'}">
                                                                            <span class="text-success"><i class="ti-arrow-up"></i></span>
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                            <span class="text-danger"><i class="ti-arrow-down"></i></span>
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                    </c:when>
                                                                    <c:when test="${sessionScope.acc.roleId == 1}">
                                                                    <span class="text-success"><i class="ti-arrow-up"></i></span>
                                                                    </c:when>
                                                                </c:choose>
                                                            <span class="ms-2"><fmt:formatNumber value="${payments.totalAmount}" type="number" maxFractionDigits="2" />₫</span>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="card-footer d-flex justify-content-center">
                                <nav>
                                    <ul class="pagination mb-0">
                                        <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                                            <a class="page-link" href="payment?page=${currentPage - 1}">Previous</a>
                                        </li>
                                        <c:forEach begin="1" end="${totalPages}" var="i">
                                            <li class="page-item ${currentPage == i ? 'active' : ''}">
                                                <a class="page-link" href="payment?page=${i}">${i}</a>
                                            </li>
                                        </c:forEach>
                                        <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                                            <a class="page-link" href="payment?page=${currentPage + 1}">Next</a>
                                        </li>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


        </div>


    </body>
    <script src="https://pay.vnpay.vn/lib/vnpay/vnpay.min.js"></script>
    <jsp:include page="footer.jsp" />
    <script type="text/javascript">
        $("#frmCreateOrder").submit(function () {
            var postData = $("#frmCreateOrder").serialize();
            var submitUrl = $("#frmCreateOrder").attr("action");
            $.ajax({
                type: "POST",
                url: submitUrl,
                data: postData,
                dataType: 'JSON',
                success: function (x) {
                    if (x.code === '00') {
                        if (window.vnpay) {
                            vnpay.open({width: 768, height: 600, url: x.data});
                        } else {
                            location.href = x.data;
                        }
                        return false;
                    } else {
                        alert(x.Message);
                    }
                }
            });
            return false;
        });
    </script>
    <script>
        // JavaScript to format numbers as VND
        const hold = ${wallet.hold}; // Example: Replace with `cv.price` value
        const balance = ${wallet.balance}; // Example: Replace with `wallet.balance` value

        // Function to format as VND with commas
        function formatVND(value) {
            return new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'}).format(value);
        }

        // Update the spans with formatted values
        document.getElementById('walletDisplay').textContent = formatVND(balance);
        document.getElementById('holdDisplay').textContent = formatVND(hold);

    </script>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            const dateCells = document.querySelectorAll(".date-cell");

            dateCells.forEach(cell => {
                // Get the raw date string from the data attribute
                const rawDate = cell.getAttribute("data-date");

                // Convert to a Date object (assuming the date is in ISO format)
                const date = new Date(rawDate);

                // Format the date (customize as needed)
                const formattedDate = date.toLocaleDateString("en-GB") + ' ' + date.toLocaleTimeString("en-GB", {
                    hour: '2-digit',
                    minute: '2-digit'
                });

                // Set the formatted date as the cell's text content
                cell.textContent = formattedDate;
            });
        });
    </script>


    <script>
        $(document).ready(function () {
            // Kích hoạt masonry cho phần tử có id 'masonry'
            var $masonryContainer = $('#masonry').masonry({
                itemSelector: '.action-card',
                percentPosition: true,
                columnWidth: '.action-card'
            });

            // Thiết lập bộ lọc ban đầu (toprate)
            $masonryContainer.masonryFilter({
                filter: function () {
                    return $(this).hasClass('toprate');
                }
            });

            // Xử lý khi người dùng nhấn vào các bộ lọc
            $('.filters li').on('click', function () {
                // Xóa class 'active' và thêm cho phần tử hiện tại
                $('.filters li').removeClass('active');
                $(this).addClass('active');

                // Lấy giá trị filter từ 'data-filter'
                var filterValue = $(this).data('filter');

                // Cập nhật bộ lọc cho Masonry
                $masonryContainer.masonryFilter({
                    filter: function () {
                        return $(this).hasClass(filterValue);
                    }
                });
            });
        });

    </script>

</html>
