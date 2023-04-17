<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.tparendre.model.Etudiant" %>
<%@ page import="com.example.tparendre.model.Specialite" %>
<%@ page import="com.example.tparendre.Service.SpecialiteService" %>
<%@ page import="java.util.LinkedList" %>
<%@include file="../common/header.jspf" %>
<%@include file="../common/navigation.jspf" %>
<div class="container">
    <main class="text-center">
        <h3 style="margin-top: 60px"> Specialities listing </h3>
        <a type="button" class="btn btn-outline-dark float-left" style="margin-bottom: 20px"
           href="${pageContext.request.contextPath}/user/formAjouterSpecialite.action">Ajouter une specialite</a>

        <table class="table table-condensedtable-striped table-bordered" style="table-layout: fixed;">
            <thead style="color: white; background-color: #212529;">
            <tr>
                <th>ID</th>
                <th>Designation</th>
                <th>Actions</th>
            </tr>
            </thead>
            <% List<Specialite> ls = (List<Specialite>) request.getAttribute("listSpe");
                System.out.println(ls);
            %>

            <tbody>
            <%for (Specialite s : ls) {%>
            <tr>
                <td><%=s.getIdSpec()%>
                </td>
                <td><%=s.getDesignation()%>
                </td>
                <td style="width: 150px;">
                    <div class="row row">
                        <div class="col">
                            <form method="post"
                                  action="${pageContext.request.contextPath}/user/FormModifierSpec.action">
                                <input type="hidden" name="id" value="<%=s.getIdSpec()%>"/>
                                <button type="submit" class="btn btn-primary">Modifier</button>
                            </form>
                        </div>

                        <div class="col">
                            <form method="post"
                                  action="${pageContext.request.contextPath}/user/supprimerSpecialite.action">
                                <input type="hidden" name="id" value="<%=s.getIdSpec()%>"/>
                                <button type="submit" class="btn btn-warning">Supprimer</button>
                            </form>
                        </div>
                    </div>
                </td>
            </tr>
            <%}%>
            </tbody>
        </table>
    </main>
    <a type="button" class="btn btn-info" href="${pageContext.request.contextPath}/user/listAllSpecialites.action">Actualiser</a>
    <a type="button" class="btn btn-danger float-right"
       onclick="return confirm('Are you sure you want to delete all ?')"
       href="${pageContext.request.contextPath}/user/deleteAllSpecialities.action">Supprimer tous</a>
</div>

<%@include file="../common/footer.jspf" %>