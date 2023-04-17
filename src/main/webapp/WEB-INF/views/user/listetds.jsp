<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.tparendre.model.Etudiant" %>
<%@include file="../common/header.jspf"%>
<%@include file="../common/navigation.jspf"%>

<div class="container">
<main class ="text-center">
    <h3 style="margin-top: 60px;">List Of Students</h3>
    <a type="button" class="btn btn-outline-dark float-left" style="margin-bottom: 20px" href="${pageContext.request.contextPath}/user/ajouterEtudiant.action">Ajouter un etudiant</a>

<% List<Etudiant> le=(List<Etudiant>) request.getAttribute("etdList");%>
    <table class="table table-condensedtable-striped table-bordered" style="table-layout: auto;">
        <thead style ="color: white; background-color: #212529;">
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Prenom</th>
            <th>Moyenne</th>
            <th>Specialite</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%for (Etudiant e : le){%>
        <tr>
            <td><%=e.getIdEtd()%></td>
            <td><%=e.getNomEtd()%></td>
            <td><%=e.getPrenomEtd()%></td>
            <td><%=e.getMoyenne()%></td>
            <td><%=e.getSpecialite().getDesignation()%></td>
            <td style="width: 250px;">
                <div class="row row">
                    <div class="col">
                    <form method="post" action="${pageContext.request.contextPath}/user/supprimerEtudiant.action">
                    <input type="hidden" name="id" value="<%=e.getIdEtd()%>"/>
                    <button type="submit" class="btn btn-primary">Supprimer</button>
                </form>
                </div>
                    <div class="col">
                <form method="post" action="${pageContext.request.contextPath}/user/FormModifierEtd.action">
                    <input type="hidden" name="id" value="<%=e.getIdEtd()%>"/>
                    <button type="submit" class="btn btn-success">Modifier</button>
                </form>
                    </div>
                </div>
            </td>
        </tr>
        <%}%>
        </tbody>
    </table>
    </main>

<a type="button" class="btn btn-info" href="${pageContext.request.contextPath}/user/ListerEtudiants.action">Actualiser</a>
<a type="button" class="btn btn-danger float-right"
   onclick="return confirm('Are you sure you want to delete all ?')"
   href="${pageContext.request.contextPath}/user/deleteAllStudents.action">Supprimer tous</a>
</div>
<%@include file="../common/footer.jspf"%>