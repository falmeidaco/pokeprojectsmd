<%
    if (session.getAttribute("logged") == null) {
        response.sendRedirect("LoginPage?msg=not_logged");
    }
%>
<%@page import="JavaProject.User"%>
<%@page import="JavaProject.UserDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
                <section class="users">
                    <%
                        UserDAO user_dao = new UserDAO();
                        User[] users = user_dao.getAll();
                    %>
                    <div class="panel panel-primary">
                        <div class="panel-heading text-center">
                            <strong>Lista de usuários cadastrados (<%= users.length %>)</strong>
                        </div>
                        <div class="panel-body">
                            <table class="table table-striped table-bordered">
                                <thead>
                                    <tr>
                                        <th>#ID</th>
                                        <th>Nome (nome de usuário)</th>
                                        <th>Email</th>
                                        <th>Tipo</th>
                                        <th class="text-center">Opções</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        for (int i = 0; i < users.length; i = i + 1) {
                                    %>
                                    <tr>
                                        <td><%= users[i].getId() %></td>
                                        <td><%= users[i].getName() %> (<%= users[i].getUsername() %>)</td>
                                        <td><%= users[i].getEmail() %></td>
                                        <td><%= users[i].getRole() %></td>
                                        <td class="text-center"><a href="User?id=<%= users[i].getId() %>">Editar</a></td>
                                    </tr>
                                    <%
                                        }
                                    %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <%
                        if (session.getAttribute("logged_user_role").equals("super")) {
                    %>
                    <div class="panel panel-primary">
                        <div class="panel-heading text-center">
                            <strong>Cadastrar novo usuário</strong>
                        </div>
                        <div class="panel-body">
                            <form action="UserService" method="post" class="form">
                                <input type="hidden" name="action" value="add" />
                                <div class="form-group">
                                    <label for="name">Nome</label>
                                    <input type="text" name="name" id="name" maxlength="64" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label for="email">E-mail</label>
                                    <input type="text" name="email" id="email" max="32" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label for="username">Nome de usuário</label>
                                    <input type="text" name="username" id="username" max="32" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label for="password">Senha</label>
                                    <input type="password" name="password" id="password" maxlength="32" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label for="role">Tipo de usuário</label>
                                    <select name="role" id="role" class="form-control">
                                        <option value="default">Usuário comum</option>
                                        <option value="super">Usuário administrador</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <input type="submit" name="form_submit" value="Cadastrar usuário" class="btn btn-success">
                                    <input type="reset" name="form_submit" value="Cancelar" class="btn btn-default pull-right">
                                </div>
                            </form>
                        </div>
                    </div>
                    <%
                        }
                    %>
                </section>
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