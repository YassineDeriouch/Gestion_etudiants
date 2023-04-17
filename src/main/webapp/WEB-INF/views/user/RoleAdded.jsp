<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/header.jspf"%>
<%@include file="../common/navigation.jspf"%>

<div class="container my-5">
  <div class="card">
    <div class="card-header">
      <h1 class="card-title">Role Added Successfully</h1>
    </div>
    <div class="card-body">
      <p class="card-text">The role has been added successfully to the database.</p>
      <a href="${pageContext.request.contextPath}/user/GetRoles.action" class="btn btn-success">Back to Roles listing</a>
    </div>
  </div>
</div>

<%@include file="../common/footer.jspf"%>
