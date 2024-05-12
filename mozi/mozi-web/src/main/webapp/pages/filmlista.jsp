<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<html>
<head>
</head>
<body>
<div>
  <jsp:include page="/FilmekServlet"/>
  <table>
    <tr>
      <th scope="col">Cím</th>
      <th scope="col">Hossz</th>
      <th scope="col">Korhatár</th>
      <th scope="col">Rendező</th>
      <th scope="col">Szereplők</th>
      <th scope="col">Leírás</th>
      <th scope="col">Borítókép</th>
      <th scope="col">Vetítések</th>
    </tr>
    <tbody>
    <c:forEach var="movie" items="${requestScope.films}">
    <tr>
      <td>${movie.film_id}</td>
      <td>${movie.hossz}</td>
      <td>${movie.korhatar}</td>
      <td>${movie.rendezo}</td>
      <td>${movie.szereplok}</td>
      <td>${movie.leiras}</td>
      <td class="cover-col">
        <img src='<c:url value="${movie.cover}"></c:url>' width="90%" height="auto"/>
      </td>

      <td>
          <%-- <a href="${pageContext.request.contextPath}/ScreeningController?movieID=${movie.film_id}">
               <span>Megnézem</span>
           </a>--%>
      </td>
    </tr>
  </table>
  </c:forEach>
  </tbody>
</div>
</body>
</html>
