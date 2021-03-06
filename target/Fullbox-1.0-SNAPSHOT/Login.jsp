<%-- 
    Document   : Login
    Created on : 23/10/2018, 12:18:56 PM
    Author     : tanya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <link rel="icon" type="image/png" href="assets/img/favicon.ico">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <link rel="stylesheet" href="css/basic.min.css"/>
        <link rel="stylesheet" href="css/dropzone.min.css"/>
        <script type="text/javascript" src="js/dropzone.js"></script>

        <title>Welcome </title>

        <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
        <meta name="viewport" content="width=device-width" />


        <!-- Bootstrap core CSS     -->
        <link href="css/bootstrap.min.css" rel="stylesheet" />

        <!-- Animation library for notifications   -->
        <link href="css/animate.min.css" rel="stylesheet"/>

        <!--  Light Bootstrap Table core CSS    -->
        <link href="css/light-bootstrap-dashboard.css?v=1.4.0" rel="stylesheet"/>


        <!--  CSS for Demo Purpose, don't include it in your project     -->
        <link href="css/demo.css" rel="stylesheet" />


        <!--     Fonts and icons     -->
        <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
        <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">

        <!-- Main css -->
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>

        <div class="wrapper">
            <div class="sidebar" data-color="purple" data-image="assets/img/sidebar-5.jpg">

                <!--
            
                    Tip 1: you can change the color of the sidebar using: data-color="blue | azure | green | orange | red | purple"
                    Tip 2: you can also add an image using data-image tag
            
                -->

                <div class="sidebar-wrapper">
                    <div class="logo">
                        <h2>FullBox</h2>
                    </div>
                    <ul class="nav">
                        <li class="active">
                            <a href="<s:url action="newfile"/>">
                                <i class="pe-7s-graph"></i>
                                <p>New File</p>
                            </a>
                        </li>
                        <li class="active">
                            <a href="<s:url action="myfile"/>">
                                <i class="pe-7s-graph"></i>
                                <p>My Files</p>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>

            <div class="main-panel">
                <nav class="navbar navbar-default navbar-fixed">
                    <div class="container-fluid">
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navigation-example-2">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>
                        </div>
                        <div class="collapse navbar-collapse">
                            <ul class="nav navbar-nav navbar-right">
                                <li>
                                    <a href="<s:url action='regresa'/>">
                                        <i class="fa fa-user"></i>
                                        <i class="fa-user-circle">Log out</i>
                                    </a>
                                </li>
                                <li class="separator hidden-lg"></li>
                            </ul>
                        </div>
                    </div>
                </nav>


                <div class="content">
                    <div class="container-fluid">
                        <div class="row" >
                            <div class="col-md-12" >
                                <div class="card">
                                    <div class="header">
                                        <h4 class="title">New File</h4>
                                    </div>
                                    <div class="content">
                                        <form action="fileup" method="POST" class="dropzone" id="dropzone" enctype="multipart/form-data">
                                            <input type="hidden"  name="your_email" value="<s:property value="your_email"/>">
                                            <div class="fallback">
                                                <s:file name="file" label="Select a File to upload" size="40" />
                                            </div>
                                        </form>
                                          
                                    </div>
                                </div>
                            </div>              
                        </div>
                    </div>
                </div>

                <footer class="footer">
                    <div class="container-fluid">
                        <nav class="pull-left">
                            <ul>
                                <li>
                                    <a href="#">
                                        Home
                                    </a>
                                </li>
                            </ul>
                        </nav>
                        <p class="copyright pull-right">
                            &copy; <script>document.write(new Date().getFullYear())</script> Fullbox
                        </p>
                    </div>
                </footer>

            </div>
        </div>
    </body>

    <!--   Core JS Files   -->
    <script src="js/jquery.3.2.1.min.js" type="text/javascript"></script>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>

    <!--  Charts Plugin -->
    <script src="js/chartist.min.js"></script>

    <!--  Notifications Plugin    -->
    <script src="js/bootstrap-notify.js"></script>

    <!--  Google Maps Plugin    -->
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE"></script>

    <!-- Light Bootstrap Table Core javascript and methods for Demo purpose -->
    <script src="js/light-bootstrap-dashboard.js?v=1.4.0"></script>

    <!-- Light Bootstrap Table DEMO methods, don't include it in your project! -->
    <script src="js/demo.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            demo.initChartist();
            $.notify({
                icon: 'pe-7s-gift',
                message: "Welcome to your <b>Fullbox Drive</b>."

            }, {
                type: 'info',
                timer: 4000
            });

        });
        function addMoreRows() {
            var rows = "";

        rows += "<tr><td>" + '<s:property value="value1"/>' + "</td><td>" + 'hola' + "</td><td>" + 'hola' + "</td></tr>";
            $(rows).appendTo("#fileTable tbody");

        }

    </script>


</html>

