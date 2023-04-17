<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.tparendre.model.Etudiant" %>
<%@ page import="com.example.tparendre.Service.EtudiantService" %>
<%@ page import="com.example.tparendre.model.User" %>
<%@ page import="com.example.tparendre.model.Role" %>
<%@include file="../common/header.jspf" %>
<%@include file="../common/navigation.jspf" %>

<div class="container">
    <main class="text-center">
        <h3 style="margin-top: 60px"> Users listing </h3>
        <a type="button" class="btn btn-outline-dark float-left" style="margin-bottom: 20px"
           href="${pageContext.request.contextPath}/user/formAjouterUserToRole.action">Ajouter un utilisateur</a>
        <% List<User> lu = (List<User>) request.getAttribute("listUser");%>
        <table class="table table-condensedtable-striped table-bordered" style="table-layout: auto;">
            <thead style="color: white; background-color: #212529;">
            <tr>
                <th>ID</th>
                <th>Email</th>
                <th>Password</th>
                <th>Role ID</th>
                <th>Role</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <%for (User u : lu) {%>
            <tr>
                <td><%=u.getId()%>
                </td>
                <td style="width: 300px"><%=u.getEmail()%>
                </td>
                <td><input type="password" class="form-control" value="<%=u.getPassword()%>" readonly>
                </td>
                <td>
                    <% List<Role> roles = u.getRoles(); %>
                    <% if (roles != null && !roles.isEmpty()) { %>
                    <% for (Role r : roles) { %>
                    <%= r.getIdRole()%><% if (roles.indexOf(r) != roles.size() - 1) { %>, <% } %>
                    <% } %>
                    <% } else { %>
                    N/A
                    <% } %>
                </td>
                <td>
                    <% if (roles != null && !roles.isEmpty()) { %>
                    <% for (Role r : roles) { %>
                    <%= r.getRoleName()%><% if (roles.indexOf(r) != roles.size() - 1) { %>, <% } %>
                    <% } %>
                    <% } else { %>
                    N/A
                    <% } %>
                </td>
                <td style="width: 250px;">
                    <div class="row row">
                        <div class="col">
                        <form method="post"
                              action="${pageContext.request.contextPath}/user/FormModifierUser.action">
                            <input type="hidden" name="id" value="<%=u.getId()%>"/>
                            <button type="submit" class="btn btn-primary">Modifier</button>
                        </form>
                    </div>
                    <div class="col">
                            <form method="post" action="${pageContext.request.contextPath}/user/supprimerUser.action">
                                <input type="hidden" name="id" value="<%=u.getId()%>"/>
                                <button type="submit" class="btn btn-warning" style="border: none;">Supprimer</button>
                            </form>
                        </div>
                    </div>
                </td>
            </tr>
            <%}%>
            </tbody>
        </table>
    </main>
    <a type="button" class="btn btn-info" href="${pageContext.request.contextPath}/user/GetUsers.action">Actualiser</a>
    <a type="button" class="btn btn-danger float-right"
       onclick="return confirm('Are you sure you want to delete all ?')"
       href="${pageContext.request.contextPath}/user/deleteAllUsers.action">Supprimer tous</a>
</div>

<%@include file="../common/footer.jspf" %>