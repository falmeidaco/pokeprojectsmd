<%
    if (session.getAttribute("logged") == null) {
        response.sendRedirect("LoginPage?msg=not_logged");
    }
%>
<%@page import="java.util.Vector"%>
<%@page import="JavaProject.Trainer"%>
<%@page import="JavaProject.TrainerDAO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>PokeProject - Trainadores</title>
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
                        TrainerDAO trainer_dao = new TrainerDAO();
                        Trainer[] trainers = trainer_dao.getAll();
                    %>
                    <div class="panel panel-primary">
                        <div class="panel-heading text-center">
                            <strong>Lista de trainadores cadastrados (<%= trainers.length %>))</strong>
                        </div>
                        <div class="panel-body">
                            <table class="table table-striped table-bordered">
                                <thead>
                                    <tr>
                                        <th>#ID</th>
                                        <th>Nome (nome de usuário)</th>
                                        <th>Email</th>
                                        <th>Cidade</th>
                                        <th>Dinheiro</th>
                                        <th class="text-center">Opções</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        for (int i = 0; i < trainers.length; i = i + 1) {
                                    %>
                                    <tr>
                                        <td><%= trainers[i].getId() %></td>
                                        <td><%= trainers[i].getName() %> (<%= trainers[i].getUsername() %>)</td>
                                        <td><%= trainers[i].getEmail() %></td>
                                        <td><%= trainers[i].getCity_name() %></td>
                                        <td><%= trainers[i].getMoney() %></td>
                                        <td class="text-center">
                                            <a href="Trainer?id=<%= trainers[i].getId() %>">Editar</a>
                                        </td>
                                    </tr>
                                    <%
                                        }
                                    %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                        
                    <div class="panel panel-primary">
                        <div class="panel-heading text-center">
                            <strong>Cadastrar novo trainador</strong>
                        </div>
                        <div class="panel-body">
                            <form action="TrainerService" method="post" class="form">
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
                                    <label for="city_name">Cidade</label>
                                    <input type="text" name="city_name" id="city_name" max="32" class="form-control">
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
                                    <label for="money">Dinheiro</label>
                                    <input type="text" name="money" id="money" max="32" class="form-control">
                                </div>
                                <div class="form-group">
                                    <input type="submit" name="form_submit" value="Cadastrar treinador" class="btn btn-success">
                                    <input type="reset" name="form_submit" value="Cancelar" class="btn btn-default pull-right">
                                </div>
                            </form>
                        </div>
                    </div>
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