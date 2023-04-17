<%@ page import="java.util.LinkedList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/header.jspf"%>
<%@include file="../common/navigation.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="container">
  <main>
    <div class="head-title">
      <div class="left">
        <h1 class ="text-center" style="margin-top: 60px; font-weight: bold;">Modifier un role</h1>
        <ul class="breadcrumb">
          <li>
            <a href="index.jsp">Dashboard</a>
          </li>
          <li><i class='bx bx-chevron-right' ></i></li>
          <li>
            <a class="active" href="ajouterEtudiant.jsp" style="margin-top: 30px; font-weight: bold;">Modifier Role</a>
          </li>
        </ul>
      </div>
    </div>

    <div class="container">
      <form class="user" action="${pageContext.request.contextPath}/user/modifierRole.action" method="post">
        <div class="form-group">
          <label for="roleName" class="col-sm-4 col-form-label" style = "margin-bottom : 15px; color: black; font-weight: bold">Role</label>
          <div class="col-sm-10">
            <input type="text" name="roleName" id="roleName" placeholder="Role name..."
                   class="form-control" required minlength="2" maxlength="50"
                   pattern="[A-Za-zÀ-ÖØ-öø-ÿ\s]+" title="Le nom doit contenir uniquement des lettres et des espaces, entre 2 et 50 caractères." style="max-width: 50%;">
          </div>
        </div>
        <input type="submit" class="btn btn-success col-sm-2" value="Modify" id="modify" >
      </form>
    </div>
    <script lang="javascript" >
      function displayEdited() {
        const text = document.getElementById("modify");
        if (text.style.display === "none") {
          text.style.display = "block";
        } else {
          text.style.display = "none";
        }
      }
    </script>
    <p id = "saved" style="color:#4CAF50; display: none">edited successfully</p>
  </main>
</div>
<%--
    <div class="container">
    <form class="user" action="${pageContext.request.contextPath}/user/modifierRole.action" method="post">
      <div class="form-group">
        <label for="roleName">Role Name</label>
        <div>
          <input type="text" name="roleName" id="roleName" placeholder="roleName..." required class="form-control" style="width: 100%; max-width: 230px;">
        </div>
      </div>


      <input type="submit" class="btn btn-success" value="Modify">
    </form>
  </div>
    --%>
<%@include file="../common/footer.jspf"%>