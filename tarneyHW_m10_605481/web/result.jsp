<%-- 
    Document   : webpage
    Created on : Jul 15, 2017, 4:46:36 PM
    Author     : Tarney
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Beartooth Hiking Company (BHC)</title>
        <jsp:useBean id="hike_info" class="com.brandontarney.model.HikeInfo" scope="session" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link rel="stylesheet" href="css/BeartoothHikingCompany.css" type="text/css" />
        <script type="text/javascript" src="javascript/BeartoothHikingCompany.js"></script>

        <!-- JQUERY MAGIC -->
        <script src="javascript/jquery-3.2.1.js"></script>
        <script src="javascript/jquery-ui-1.12.1/jquery-ui.js"></script>
        <script>
            $(function () {
                $("#datepicker").datepicker();
            });
        </script>
        <style>
            .ui-datepicker {
                background: #c6dbff;
            }
        </style>
    </head>
    <body>
        <div id="page_wrapper">
            <header>
                <h1 id="top_header">Beartooth Hiking Company</h1>
                <p id="slogan">Alaska's Lamest Hiking Company</p>
                <nav>
            </header>
            <section id="form" class="table">
                <h2 id="form_header">Choose Your Hike</h2>
                <!-- <form action="http://localhost:8084/tarneyHW_m10_605481/Controller" method=GET onSubmit=" return validatePartySize(1, 10)"> -->
                <form action="http://web6.jhuep.com:80/tarneyHW_m10_605481/TarneyServlet" method=GET onSubmit=" return validatePartySize(1, 10)"> 
                    <p class="excited_msg">Hike</p>
                    <select class="bigger_text" name="hike" size="1">
                        <option value="hellroaring">Hellroaring Plateau</option>
                        <option value="gardiner">Gardiner Lake</option>
                        <option value="beaten">Beaten Path</option>
                    </select>
                    <p class="excited_msg">Date (6/1 - 9/31)</p>
                    <input type="text" class="big_text" id="datepicker" name="datepicker" size="10" value="06/01/2017">
                    <p class="excited_msg">Duration (days, see below)</p>
                    <select class="bigger_text" name="duration">
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="7">7</option>
                    </select>
                    <p class="excited_msg">Party Size (Humans, 1 - 10)</p>
                    <input class="big_text" type="text" size="5" maxlength="2" name="people" id="people" onBlur="validatePartySize(1, 10)" value="1">
                    <br/><br/>
                    <input class="biggest_text" type="SUBMIT" name="submit" value="submit" />
                    <br/>
                    <p class="excited_msg">Cost Per Person:
                           <input class="bigger_text" type="text" name="costPerPerson" size="15" maxlength="15" value="$<%=hike_info.getRate().getCost()%> ($USD)"/>
                    </p>
                    <p class="excited_msg">Total Cost:
                           <input class="bigger_text" type="text" name="totalCost" size="15" maxlength="15" value="$<%=hike_info.getRate().getCost() * hike_info.getPartySzie()%> ($USD)"/>
                    </p>
                </form>
            </section>
            <!-- form types: text fields, text areas, buttons, checkboxes, password, file, radio, submit, text -->
            <section id="main_info">
                <table id="tours_table">
                    <thead>
                        <tr>
                            <th id="table_title" colspan="5">Tour Options</th>
                        </tr>
                        <tr>
                            <th>Tour</th>
                            <th>Duration (days)</th>
                            <th>Difficulty</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Hellroaring Plateau</td>
                            <td>2, 3, 4</td>
                            <td>easy</td>
                        </tr>
                        <tr>
                            <td>Gardiner Lake</td>
                            <td>3, 5</td>
                            <td>intermediate</td>
                        </tr>
                        <tr>
                            <td>The Beaten Path</td>
                            <td>5, 7</td>
                            <td>difficult</td>
                        </tr>

                    </tbody>
                </table>
            </section>
            <section id="more_info">
                <h2 id="more_info_header" class="more_info">More Information</h2>
                <p>For more information, checkout the <a href="http://www.wilderness.net/index.cfm?fuse=NWPS&sec=wildView&WID=1"><span id="wilderness_link">wilderness website</span></a></p>
            </section>
            <footer>
                <p>Contact: <a href="mailto:brandon.tarney@gmail.com">Brandon Tarney</a></p>
            </footer>
        </div>
    </body>
</html>

