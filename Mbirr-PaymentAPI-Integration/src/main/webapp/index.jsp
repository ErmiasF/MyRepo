<%-- 
    Document   : index
    Created on : Jul 22, 2021, 10:09:08 AM
    Author     : ermias
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
                    <form class="form-container" method="POST" action="PaymentController">
                        <div class="form-group">
                            <label for="exampleInputEmail1">Full Name</label>
                            <input type="text" class="form-control"  required name="name">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">Phone Number</label>
                            <input type="tel" value="0905030507" class="form-control" readonly="" name="tel" required>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">Amount</label>
                            <input type="number" name="ammount" class="form-control" required="">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">Beneficiary Name</label>
                            <input type="text" value="test web portal" class="form-control" readonly="" required="">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">Beneficiary Mobile Number</label>
                            <input type="tel" class="form-control" value="0987786556" readonly="" required="">
                        </div>
                        <button type="submit" class="btn btn-primary btn-block">Submit</button>
                    </form>
                </section>
            </section>
        </section>
    </body>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
</html>
