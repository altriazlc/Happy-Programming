<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>PAYMENT RESULT</title>
        <link href="/vnpay_jsp/assets/bootstrap.min.css" rel="stylesheet"/>
    </head>
    <body>
        <header class="header rs-nav">
            <jsp:include page="header.jsp" />
        </header>
        <div class="page-banner ovbl-dark" style="background-image:url(assets/images/banner/banner3.jpg);">
            <div class="container">
                <div class="page-banner-entry">
                    <h1 class="text-white">Payment Result</h1>
                </div>
            </div>
        </div>
        <!-- Display Transaction Result -->
        <div class="container" style="margin-top: 50px">
      

            <div class="container">
                <div class="header clearfix">

                </div>
                <div class="table-responsive" style="border: 1px solid #ddd; padding: 20px; border-radius: 5px;">
                    <h4 style="text-align: center;">Transaction Details</h4>
                    <hr style="border-top: 2px solid #007BFF;">

                    <!-- Display Transaction Information -->
                    <div class="form-group">
                        <label style="font-weight: bold;">Transaction Reference:</label>
                        <label>${vnp_TxnRef}</label>
                    </div>    

                    <div class="form-group">
                        <label style="font-weight: bold;">Transaction Amount:</label>
                        <label id="amountDisplay"></label> <!-- Placeholder for transaction amount -->
                    </div>  


                    <div class="form-group">
                        <label style="font-weight: bold;">Balance:</label>
                        <label id="walletDisplay"></label> <!-- Placeholder for wallet balance -->
                    </div>  
                    <div class="form-group">
                        <label style="font-weight: bold;">Order Description:</label>
                        <label>Charge money</label>
                    </div> 

                    <div class="form-group">
                        <label style="font-weight: bold;">VNPAY Transaction No:</label>
                        <label>${vnp_TransactionNo}</label>
                    </div> 

                    <div class="form-group">
                        <label style="font-weight: bold;">Bank Code:</label>
                        <label>NCB</label>
                    </div> 

                    <div class="form-group">
                        <label style="font-weight: bold;">Payment Date:</label>
                        <label id="formattedPayDateLabel">${vnp_PayDate}</label>
                    </div> 

                    <hr style="border-top: 2px solid #007BFF;">

                    <!-- Display Transaction Status -->
                    <div class="form-group">
                        <label style="font-weight: bold;">Transaction Status:</label>
                        <label>
                            <%
                                String status = (String) request.getAttribute("status");
                                if ("success".equals(status)) {
                                    out.print("<span style='color: green; font-weight: bold;'>Success</span>");
                                } else if ("failed".equals(status)) {
                                    out.print("<span style='color: red; font-weight: bold;'>Failed</span>");
                                } else if ("error".equals(status)) {
                                    out.print("<span style='color: orange; font-weight: bold;'>" + request.getAttribute("message") + "</span>");
                                }
                            %>
                        </label>
                    </div> 
                </div>
            </div>
            <script>

                let amount = ${amount};
                let wallet = ${newBalance};

                document.getElementById("amountDisplay").innerText = amount.toLocaleString('vi-VN', {
                    style: 'currency',
                    currency: 'VND'
                });

                document.getElementById("walletDisplay").innerText = wallet.toLocaleString('vi-VN', {
                    style: 'currency',
                    currency: 'VND'
                });
            </script>
            <script>
                // Get the payment date from the label
                const payDateString = "${vnp_PayDate}";

                // Function to format the date
                function formatPaymentDate(payDateStr) {
                    // Check if payDateStr is valid
                    if (payDateStr && payDateStr.length === 14) {
                        // Extract year, month, day, hour, minute, second
                        const year = payDateStr.substring(0, 4);
                        const month = payDateStr.substring(4, 6) - 1;
                        const day = payDateStr.substring(6, 8);
                        const hour = payDateStr.substring(8, 10);
                        const minute = payDateStr.substring(10, 12);
                        const second = payDateStr.substring(12, 14);

                        // Create a new date object
                        const date = new Date(year, month, day, hour, minute, second);

                        // Format date to a more readable format (e.g., dd-mm-yyyy hh:mm:ss)
                        return date.toLocaleString('en-GB', {
                            day: '2-digit',
                            month: '2-digit',
                            year: 'numeric',
                            hour: '2-digit',
                            minute: '2-digit',
                            second: '2-digit'
                        });
                    }
                    return 'Invalid date';
                }

                // Set the formatted date to the label
                document.getElementById("formattedPayDateLabel").innerText = formatPaymentDate(payDateString);
            </script>
        </div>
        <footer class="footer" style="margin-top: 50px">
            <jsp:include  page="footer.jsp"/>
        </footer>
    </body>
</html>
