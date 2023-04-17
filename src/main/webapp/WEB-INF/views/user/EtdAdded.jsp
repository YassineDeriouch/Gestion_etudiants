<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/header.jspf"%>
<%@include file="../common/navigation.jspf"%>

<div class="container my-5">
  <div class="card">
    <div class="card-header">
      <h1 class="card-title">Student Added Successfully</h1>
    </div>
    <div class="card-body">
      <p class="card-text">The student has been added successfully to the database.</p>
      <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-success">Back to Home Page</a>
    </div>
  </div>
</div>

<%@include file="../common/footer.jspf"%>
