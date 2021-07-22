<%-- 
    Document   : response
    Created on : Jul 22, 2021, 10:09:33 AM
    Author     : ermias
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    if (session.getAttribute("statusCode") == null) {
        response.sendRedirect("index.jsp");
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
        <link rel="stylesheet" href="asset/style.css" type="text/css">
    </head>
    <body>
        <section class="container-fluid bg">
            <section class="row justify-content-center">
                <section class="col-12 col-sm-6 col-md-3">
                    <div class="card form-container">
                        <div class="card-header">
                            Payment Status
                        </div>
                        <div class="card-body">
                            <h5 class="card-title">Server Response</h5>
                            <p class="card-text"><%out.print(session.getAttribute("server_responce"));%></p>
                            <br>
                            <h5 class="card-title">status Code</h5>
                            <p class="card-text"><%out.print(session.getAttribute("statusCode"));%></p>
                            <br>
                            <h5 class="card-title">Json Response</h5>
                            <p class="card-text"><%out.print(session.getAttribute("jsonResponse"));%></p>
                        </div>
                    </div>
                </section>
            </section>
        </section>
        <%
            session.removeAttribute("server_responce");
            session.removeAttribute("statusCode");
            session.removeAttribute("jsonResponse");
        %>

    </body>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
</html>
