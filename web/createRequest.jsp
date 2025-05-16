<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <meta name="description" content="EduChamp : Education HTML Template" />

        <!-- OG -->
        <meta property="og:title" content="EduChamp : Education HTML Template" />
        <meta property="og:description" content="EduChamp : Education HTML Template" />
        <meta property="og:image" content="" />
        <meta name="format-detection" content="telephone=no">

        <!-- FAVICONS ICON ============================================= -->
        <link rel="icon" href="../error-404.html" type="image/x-icon" />
        <link rel="shortcut icon" type="image/x-icon" href="assets/images/favicon.png" />

        <!-- PAGE TITLE HERE ============================================= -->
        <title>Create request</title>

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

    <body class="ttr-opened-sidebar ttr-pinned-sidebar">

        <!-- header start -->
        <header class="header rs-nav" >
            <jsp:include page="headerMentee.jsp" />
        </header >
        <!-- header end -->
        <!-- Left sidebar menu start -->

        <!-- Left sidebar menu end -->

        <!--Main container start -->
        <main class="ttr-wrapper">
            <div class="container mt-4">


                <div class="row mb-4">
                    <div class="col-auto" style="display: flex;">
                        <!-- User Balance Section -->
                        <div class="balance-box border border-primary rounded p-2" style="max-width: 150px;">
                            <h6 class="text-primary mb-1">Your Balance</h6>
                            <p id="userBalance" class="text-success mb-0"></p>
                        </div>
                        <div class="balance-box border border-primary rounded p-2" style="max-width: 150px; margin-left: 50px">
                            <h6 class="text-primary mb-1">Your Hold</h6>
                            <p id="userHold" class="text-red mb-0"></p> 
                        </div>
                    </div>
                </div>

            </div>

            <div class="container mt-4">
                <div class="card p-4 shadow-sm border-light">
                    <h4 class="text-primary text-center mb-4">Create new request to mentor to get support</h4>
                    <div class="list-group">
                        <div class=" text-center">
                            <img id="userAvatar" src="getCVimage?id=${requestScope.cv.cvId}" class="rounded-circle" alt="" style=" margin: 50px 10px;width: 150px;height: 150px;object-fit: cover;">
                        </div>
                        <div class="list-group-item d-flex justify-content-between align-items-center bg-light border-0 rounded-top">
                            <span class="font-weight-bold">Mentor:</span>
                            <span class="font-weight-bold text-info">${mentor.fullName}</span>
                        </div>
                        <div class="list-group-item d-flex justify-content-between align-items-center bg-light border-0">
                            <span class="font-weight-bold">Price per Slot:</span>
                            <span class="font-weight-bold text-info" id="pricePerSlot"></span>
                        </div>


                    </div>
                    <div class="text-muted mt-3 text-center">
                        <c:if test="${wallet.balance < cv.price}">
                            <p style="color: red">Your balance is not enough, click <a href="payment" target="_blank" rel="noopener noreferrer"><strong><u>here</u></strong></a> to charge money</p>
                                        </c:if>
                    </div>
                </div>
            </div>  
            <div class="row">
                <!-- Your Profile Views Chart -->
                <div class="col-lg-12 m-b30">
                    <div class="widget-box">
                        <div class="wc-title">
                            <p style="color: red"> ${error}</p>
                            <c:if test="${pay != null}">
                                <p style="color: red">Your balance is not enough, click <a href="payment" target="_blank" rel="noopener noreferrer"><strong><u>here</u></strong></a> to charge money</p>
                            </c:if>
                            <p style="color: blue"> ${notify}</p>
                        </div>
                        <div class="widget-inner">
                            <form class="edit-profile m-b30" action="createrequest" method="post" id="slotForm">
                                <div class="row">
                                    <div class="col-12">
                                        <div class="ml-auto">
                                            <h3>1. Content</h3>
                                        </div>
                                    </div>
                                    <div class="form-group col-6">
                                        <label class="col-form-label">Title of request</label>
                                        <div>
                                            <input class="form-control" type="text" value="${sessionScope.title != null ? sessionScope.content : ''}" name="title" required>
                                        </div>
                                    </div>
                                    <div class="form-group col-6">
                                        <label class="col-form-label">Content of request</label>
                                        <div>
                                            <input class="form-control" type="text" value="${sessionScope.content != null ? sessionScope.content : ''}" name="content" required>
                                        </div>
                                    </div>
                                    <div class="form-group col-6">
                                        <label class="col-form-label">Start Date</label>
                                        <div>
                                            <input class="form-control" type="date" value="${sessionScope.start}" name="start" id="start" required>
                                        </div>
                                    </div>
                                    <div class="form-group col-6">
                                        <label class="col-form-label">End Date</label>
                                        <div>
                                            <input class="form-control" type="date"  value="${sessionScope.end}" name="end" id="end" required>
                                        </div>
                                    </div>
                                    <div class="form-group col-6">
                                        <label class="col-form-label">Framework</label>
                                        <div>
                                            <input class="form-control" type="text" value="${sessionScope.framework != null ? sessionScope.content : ''}" name="framework" required>
                                        </div>
                                    </div>
                                    <div class="form-group col-6">
                                        <div>
                                            <input class="form-control" type="hidden" value="${mid}" name="id">
                                        </div>
                                    </div>

                                    <div class="col-12 m-t20">
                                        <div class="ml-auto m-b5">
                                            <h3>2. Select skill</h3>
                                        </div>
                                    </div>
                                    <div class="form-group col-6">
                                        <label class="col-form-label">Select 1 skill</label><br/>
                                        <div>
                                            <ul class="border p-3 rounded mr-3 mb-3 category" style="list-style-type: none; display: flex; flex-wrap: wrap; gap: 10px;">
                                                <c:forEach items="${skillList}" var="c">
                                                    <li>
                                                        <div class="check-box">
                                                            <label class="col-form-label">
                                                                <input type="radio" class="checkbox" name="addSkills" value="${c.skillId}" 
                                                                       <c:if test="${c.skillId == sessionScope.skillId}">checked</c:if> required>
                                                                ${c.skillName}
                                                            </label>
                                                        </div>
                                                    </li>
                                                </c:forEach>
                                            </ul>
                                        </div>
                                    </div>

                                    <div class="col-12 m-t20">
                                        <div class="ml-auto">
                                            <h3 class="m-form__section">3. Select slot</h3>
                                        </div>
                                    </div>
                                    <div class="col-12"> 
                                        <div class="form-group col-6">
                                            <label class="col-form-label">Select Slots</label><br/>
                                            <div>
                                                <table class="table border rounded">
                                                    <thead>
                                                        <tr>
                                                            <th scope="col">Select</th>
                                                            <th scope="col">Day in week</th>
                                                            <th scope="col">Start time</th>
                                                            <th scope="col">End time</th>
                                                            <th scope="col">Status</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${slotList}" var="s">
                                                            <!-- Initialize isDisabled and isSelected for each slot -->
                                                            <c:set var="isDisabled" value="false" />
                                                            <c:set var="isSelected" value="" />

                                                            <!-- Check if status is 'Unavailable' -->
                                                            <c:if test="${s.status == 'unavailable'}">
                                                                <c:set var="isDisabled" value="true" />
                                                            </c:if>

                                                            <!-- Check if slot is selected in another request -->
                                                            <c:forEach items="${selectedSlot}" var="sl">
                                                                <c:if test="${s.slotID == sl.slotId}">
                                                                    <c:set var="isDisabled" value="true" />
                                                                    <c:set var="isSelected" value="selected" />
                                                                </c:if>
                                                            </c:forEach>

                                                            <!-- Render the input with the disabled attribute based on isDisabled -->
                                                            <tr>
                                                                <td>
                                                                    <input type="checkbox" class="checkbox slot-checkbox" name="addSlot" value="${s.slotID}" data-day="${s.dayInWeek}"
                                                                           ${isDisabled ? 'disabled' : ''} />
                                                                </td>
                                                                <td>${s.dayInWeek}</td>
                                                                <td>${s.startTime}</td>
                                                                <td>${s.endTime}</td>
                                                                <td>
                                                                    <c:choose>
                                                                        <c:when test="${not empty isSelected}">
                                                                            ${isSelected}
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            ${s.status}
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>



                                                    </tbody> 
                                                </table>
                                            </div>
                                        </div>
                                        <div class="summary-section border rounded p-3 mt-3 bg-light">
                                            <div class="row mb-2">
                                                <div class="col-6">
                                                    <p class="mb-1"><strong>Slot Price per Day:</strong></p>
                                                </div>
                                                <div class="col-6 text-end">
                                                    <p id="pricePerSlot1" class="text-success">$0</p>
                                                </div>
                                            </div>
                                            <div class="row mb-2">
                                                <div class="col-6">
                                                    <p class="mb-1"><strong>Selected Slot Count:</strong></p>
                                                </div>
                                                <div class="col-6 text-end">
                                                    <p id="daysCount">0</p>
                                                </div>
                                            </div>
                                            <div class="row mb-3">
                                                <div class="col-6">
                                                    <p class="mb-1"><strong>Total Price:</strong></p>
                                                </div>
                                                <div class="col-6 text-end">
                                                    <p id="totalPrice" class="text-success">$0</p>
                                                </div>
                                            </div>
                                            <input type="hidden" id="totalPriceInput" name="totalPrice" value="">
                                        </div>
                                        <button type="submit" class="btn btn-primary w-100">
                                            <i class="fa fa-plus-circle me-2"></i>Create Request
                                        </button>

                                    </div>

                                </div>
                            </form>



                        </div>
                    </div>
                </div>
                <!-- Your Profile Views Chart END-->
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
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

        <script>
     
            const price = ${cv.price}; 
            const balance = ${wallet.balance};
            const hold = ${wallet.hold};

            function formatVND(value) {
                return new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'}).format(value);
            }

            // Update the spans with formatted values
            document.getElementById('userHold').textContent = formatVND(hold);
            document.getElementById('pricePerSlot').textContent = formatVND(price);
            document.getElementById('userBalance').textContent = formatVND(balance);
            document.getElementById('pricePerSlot1').textContent = formatVND(price);
            document.getElementById('yourBalance').textContent = formatVND(balance);
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
        </script>
        <script>
            // Price per selected day 
            document.addEventListener('DOMContentLoaded', function () {
                // Price per selected day
                const pricePerDay = ${cv.price};

                // Function to calculate the price based on selected days and date range
                function calculatePrice() {
                    const startDateInput = document.getElementById('start');
                    const endDateInput = document.getElementById('end');
                    const startDate = new Date(startDateInput.value);
                    const endDate = new Date(endDateInput.value);
                    const selectedDays = Array.from(document.querySelectorAll('.slot-checkbox:checked'))
                            .map(el => el.getAttribute('data-day'));
                    const dayNames = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];

                    // Validate date inputs
                    if (isNaN(startDate) || isNaN(endDate) || startDate > endDate || selectedDays.length === 0) {
                        document.getElementById('totalPrice').textContent = '0';
                        document.getElementById('daysCount').textContent = '0';
                        return;
                    }

                    let totalDays = 0;
                    let currentDate = new Date(startDate);

                    // Count occurrences of selected days within the date range
                    while (currentDate <= endDate) {
                        const dayName = dayNames[currentDate.getDay()];
                        // Count each selected day
                        totalDays += selectedDays.filter(day => day === dayName).length;
                        currentDate.setDate(currentDate.getDate() + 1);
                    }

                    // Calculate total price
                    const totalPrice = totalDays * pricePerDay;
                    document.getElementById('totalPrice').textContent = totalPrice;
                    document.getElementById('totalPriceInput').value = totalPrice; // Set hidden input value
                    document.getElementById('daysCount').textContent = totalDays;
                }

                // Event listeners for date inputs
                document.getElementById('start').addEventListener('change', calculatePrice);
                document.getElementById('end').addEventListener('change', calculatePrice);

                // Event listeners for slot checkboxes
                document.querySelectorAll('.slot-checkbox').forEach(checkbox => {
                    checkbox.addEventListener('change', calculatePrice);
                });
            });
        </script>




    </body>

    <!-- Mirrored from educhamp.themetrades.com/demo/admin/add-listing.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 22 Feb 2019 13:09:05 GMT -->
</html>