<% System.out.println("page of Complaint List"); %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--
<!doctype html>
--%>
<html lang="en">
    <head>
       <title>Advice Form</title>
       <meta name="viewport" content="width=device-width, initial-scale=1.0">
       <link href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet">
       <%-- <link href="http://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">     --%>

       <link href="/test14/public/css/style.css" rel="stylesheet">

    </head>
    <body>
        <div id="header" class="header">
            <jsp:include page="/views/layouts/header.jsp"/>
        </div>
         <div id="main" class="col-md-8 col-md-offset-2">
                <div class="ex">
                <form name="adviceForm" action="${pageContext.request.contextPath}/advice/insert" method="post">

                    <table style="with: 50%">
                       <tr>
                            <td>What is a name of advice?</td>
                            <td><input type="text" name="adviceName" /></td>
                        </tr>
                        <tr>
                            <td>What is a scope of your advice?</td>
                            <td>
                                <select class="form-control" id="adviceType" name="adviceType" onchange="return setValue();">
                                    <option value="dropdown">Please select type
                                    <option value="parasites">Certain infectious and parasitic Problems</option>
                                    <option value="neoplasms">Neoplasms</option>
                                    <option value="blood">Problems of the blood and blood-forming organs and certain disorders involving the immune mechanism</option>
                                    <option value="metabolic">Endocrine, nutritional and metabolic Problems</option>
                                    <option value="mental">Mental and behavioural disorders</option>
                                    <option value="nervous">Problems of the nervous system</option>
                                    <option value="eye">Problems of the eye and adnexa</option>
                                    <option value="ear">Problems of the ear and mastoid process</option>
                                    <option value="circulatory">Problems of the circulatory system</option>
                                    <option value="respiratory">Problems of the respiratory system</option>
                                    <option value="digestive">Problems of the digestive system</option>
                                    <option value="skin">Problems of the skin and subcutaneous tissue</option>
                                    <option value="musculoskeletal">Problems of the musculoskeletal system and connective tissue</option>
                                    <option value="genitourinary">Problems of the genitourinary system</option>
                                    <option value="pregnancy">Pregnancy, childbirth and the puerperium</option>
                                    <option value="perinatal">Certain conditions originating in the perinatal period</option>
                                    <option value="chromosomal">Congenital malformations, deformations and chromosomal abnormalities</option>
                                    <option value="notClassified">Symptoms, signs and abnormal clinical and laboratory findings, not elsewhere classified</option>
                                    <option value="injury">Injury, poisoning and certain other consequences of external causes</option>
                                    <option value="externalCauses">External causes of morbidity and mortality</option>
                                    <option value="factors">Factors influencing health status and contact with health services</option>
                                    <option value="goToHospital">Go to the hospital</option>
                                </select>

                            </td>
                        </tr>
                        <tr>

                            <td>What part of the advice</td>
                            <td><input type="text" name="advicePart" /></td>
                        </tr>
                        <tr>
                            <td>Description</td>
                            <td><textarea class="form-control" type="text" name="adviceDescription" cols="60" rows="6"/></textarea>
                            </td>
                        </tr>
                    </table>
                    <input type="submit" value="Advice" />
                </form>
                </div>
        <%--
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
        --%>
        </div>
        <div id="footer" class="footer text-center">
            <jsp:include page="/views/layouts/footer.jsp"/>
        </div>
    </body>
</html>