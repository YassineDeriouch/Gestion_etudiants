<%@ page import="com.example.tparendre.model.User" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="com.example.tparendre.Service.UserService" %>
<%@ page import="com.example.tparendre.model.Role" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.tparendre.Service.RoleService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/header.jspf"%>
<%@include file="../common/navigation.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="container">
<main>
  <div class="head-title">
    <div class="left">
      <h1 style="font-weight: bold; margin-top: 60px">Ajouter un utilisateur</h1>
      <ul class="breadcrumb">
        <li>
          <a href="index.jsp">Dashboard</a>
        </li>
        <li><i class='bx bx-chevron-right' ></i></li>
        <li>
          <a class="active" href="ajouterEtudiant.jsp">Ajouter utilisateur</a>
        </li>
      </ul>
    </div>
  </div>
  <div class="container">
    <form style="width: 100%;max-width: 300px;" class="user" action="${pageContext.request.contextPath}/user/AddUserToRole.action" method="post">
      <div class="form-group">
        <label for="email" style="width: 100%;max-width: 300px;">Email
          <input type="email" class="form-control" name="email" id="email" placeholder="email ..." required>
        </label>
      </div>
      <div class="form-group">
        <label for="password" style="width: 100%;max-width: 300px;">Password
          <input type="password" class="form-control" name="password" id="password" placeholder="password ..." required>
        </label>
      </div>
      <div class="form-group">
        <label for="roleName" style="width: 100%;max-width: 300px;">Role Name</label>
        <select multiple name="roleName" id="roleName" class="form-control" required style="width: 100%; max-width: 300px;">
          <%
            List<Role> roles = RoleService.getAllRoles();
            System.out.println("Roles---------------------->" + roles);
            for (int i = 0; i < roles.size(); i++) { %>
          <% Role role = roles.get(i); %>
          <option value="<%= role.getRoleName() %>"><%= role.getRoleName() %></option>
          <% } %>
        </select>
      </div>
      <input type="submit" class="btn btn-success" style="width: 130px" value="save">
  </form>
  </div>

</main>
<%@include file="../common/footer.jspf"%>