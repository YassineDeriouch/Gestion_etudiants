<%@ page import="com.example.tparendre.model.Specialite" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="com.example.tparendre.Service.SpecialiteService" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/header.jspf"%>
<%@include file="../common/navigation.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="container">
<main>
  <div class="head-title">
    <div class="left">
      <h1 style="font-weight: bold; margin-top: 60px">Ajouter un etudiant</h1>
      <ul class="breadcrumb">
        <li>
          <a href="index.jsp">Dashboard</a>
        </li>
        <li><i class='bx bx-chevron-right' ></i></li>
        <li>
          <a class="active" href="ajouterEtudiant.jsp">Ajouter Etudiant</a>
        </li>
      </ul>
    </div>
  </div>
  <div class="container d-flex justify-content-center ">
    <form class="user" style="width: 100%;max-width: 300px;" action="${pageContext.request.contextPath}/user/saveETD.action" method="post" >
      <div class="form-group" >
        <label for="nom" style="width: 100%;max-width: 300px;" >Nom
          <input type="text" class="form-control" name="nom" id="nom" placeholder="Nom ..." required >
        </label>
      </div>
      <div class="form-group" >
        <label for="prenom" style="width: 100%;max-width: 300px;">Prenom
          <input type="text" class="form-control" name="prenom" id="prenom" placeholder="Prenom ..." required >
        </label>
      </div>
      <div class="form-group" >
        <label for="Moyenne" style="width: 100%;max-width: 300px;">Moyenne
          <input type="Moyenne" class="form-control" name="Moyenne" id="Moyenne" placeholder="Moyenne ..." required>
        </label>
      </div>

      <div class="form-group">
        <label for="designation">Spécialite</label>
        <select name="designation" id="designation" class="form-control" required style="width: 100%; max-width: 300px;">
          <%
            List<Specialite> specs = SpecialiteService.getAllSpecialite();
            System.out.println("specs---------------------->" + specs);
            for (int i = 0; i < specs.size(); i++) { %>
          <% Specialite spec = specs.get(i); %>
          <option value="<%= spec.getDesignation() %>"><%= spec.getDesignation() %></option>
          <% } %>
        </select>
      </div>

      <input type="submit" class="btn btn-success" style="width: 130px" value="Save">
    </form>
  </div>

</main>
</div>
<%@include file="../common/footer.jspf"%>