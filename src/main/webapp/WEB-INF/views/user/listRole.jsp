<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.tparendre.model.Role" %>
<%@ page import="com.example.tparendre.model.User" %>
<%@include file="../common/header.jspf" %>
<%@include file="../common/navigation.jspf" %>
<div class="container">
    <main class="text-center">
        <h1 style="margin-top: 60px;"><b>List Of Roles </b></h1>
        <a type="button" class="btn btn-outline-dark float-left" style="margin-bottom: 20px"
           href="${pageContext.request.contextPath}/user/formAjouterRole.action">Ajouter un role</a>

        <table class="table table-condensedtable-striped table-bordered" style="table-layout: fixed;">
        <thead style="color: white; background-color: #212529;">
            <tr>
                <th>ID</th>
                <th>Role</th>
                <th>User Email</th>
                <th>Action</th>
            </tr>

            </thead>
            <% List<Role> lr = (List<Role>) request.getAttribute("listRole");
                System.out.println(lr);
            %>

            <tbody>
            <%for (Role r : lr) {%>
            <tr>
                <td><%=r.getIdRole()%>
                </td>
                <td><%=r.getRoleName()%>
                </td>
                <td>
                    <% List<User> users = r.getUsers(); %>
                    <% if (users != null && !users.isEmpty()) { %>
                    <% for (User user : users) { %>
                    <%= user.getEmail() %><% if (users.indexOf(user) != users.size() - 1) { %>, <% } %>
                    <% } %>
                    <% } else { %>
                    N/A
                    <% } %>
                </td>

                <td style="width: 150px;">
                    <div class="row row">

                        <div class="col">
                            <form method="post"
                                  action="${pageContext.request.contextPath}/user/FormModifierRole.action">
                                <input type="hidden" name="id" value="<%=r.getIdRole()%>"/>
                                <button type="submit" class="btn btn-primary">Modifier</button>
                            </form>
                        </div>

                        <div class="col">
                            <form method="post" action="${pageContext.request.contextPath}/user/supprimerRole.action">
                                <input type="hidden" name="id" value="<%=r.getIdRole()%>"/>
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
    <a type="button" class="btn btn-info" href="${pageContext.request.contextPath}/user/GetRoles.action">Actualiser</a>
    <a type="button" class="btn btn-danger float-right"
       onclick="return confirm('Are you sure you want to delete all ?')"
       href="${pageContext.request.contextPath}/user/deleteAllRoles.action">Supprimer tous</a>
</div>
<%@include file="../common/footer.jspf" %>