<%
    if (session.getAttribute("logged") == null) {
        response.sendRedirect("LoginPage?msg=not_logged");
    }
%>
<%@page import="JavaProject.User"%>
<%@page import="JavaProject.UserDAO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    if (request.getParameter("id") == null) response.sendRedirect("Users?msg=unset_id");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>PokeProject - Usuários do sistema</title>
    <jsp:include page="html_head.jsp" />
</head>
<body>
    <main class="container">
        <jsp:include page="header.jsp" />
        <div class="row">
            <div class="col-md-3">
                <jsp:include page="menu.jsp" />
            </div>
            <div class="col-md-9">
                <%
                    UserDAO user_dao = new UserDAO();
                    User user = user_dao.get(Integer.parseInt(request.getParameter("id")));
                    if (!user.equals(null)) {
                %>
                <section class="user-profile">
                    <div class="panel panel-primary">
                        <div class="panel-heading text-center">Ficha do usuário</div>
                        <div class="panel-body">
                            <p>
                                <strong>Nome:</strong> <%= user.getName() %>
                            </p>
                            <p>
                                <strong>E-mail:</strong> <%= user.getEmail() %>
                            </p>
                            <p>
                                <strong>Nome de usuário:</strong> <%= user.getUsername() %>
                            </p>
                            <p>
                                <strong>Tipo:</strong> <%= user.getRole() %>
                                    </p>
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading text-center">Editar usuário</div>
                        <div class="panel-body">
                            <form action="UserService" method="post" class="form">
                                <input type="hidden" name="action" value="update">
                                <input type="hidden" name="id" value="<%= user.getId() %>">
                                <div class="form-group">
                                    <label for="name">Nome</label>
                                    <input value="<%= user.getName() %>" type="text" name="name" id="name" maxlength="64" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label for="email">E-mail</label>
                                    <input value="<%= user.getEmail() %>" type="text" name="email" id="email" max="32" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label for="username">Nome de usuário</label>
                                    <input value="<%= user.getUsername() %>" type="text" name="username" id="username" max="32" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label for="password">Senha</label>
                                    <input value="<%= user.getPassword() %>" type="password" name="password" id="password" maxlength="32" class="form-control">
                                </div>
                                <%
                                    if (session.getAttribute("logged_user_role").equals("super")) {
                                %>
                                <div class="form-group">
                                    <label for="role">Tipo de usuário</label>
                                    <select name="role" id="role" class="form-control">
                                        <option value="default">Usuário comum</option>
                                        <option value="super">Usuário administrador</option>
                                    </select>
                                </div>
                                <%
                                    } else {
                                %>
                                    <input type="hidden" name="role" value="default">
                                <%
                                    }
                                %>
                                <div class="form-group">
                                    <input type="submit" name="form_submit" value="Editar usuário" class="btn btn-success">
                                    <input type="reset" name="form_submit" value="Cancelar" class="btn btn-default pull-right">
                                </div>
                            </form>
                            <hr>
                            <form action="UserService" method="post" class="form">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="id" value="<%= user.getId() %>">
                                <div class="form-group">
                                    <input type="submit" name="form_submit" value="Deletar usuário" class="btn btn-danger">
                                </div>
                                <div class="form-group">
                                    <label for="confirm_delete"><input type="checkbox" name="confirm_delete" id="confirm_delete" value="ok"> Confirmar exclusão de usuário</label>
                                </div>
                            </form>
                        </div>
                    </div>
                </section>
                <%
                    }
                %>
            </div>
        </div>
        <footer>
            <p class="text-center">
                <small>Criado por Felipe Almeida - <a href="http://github.com/falmeidaco">Github</a></small>
            </p>
        </footer>
        
    </main>
</body>
</html>