<%-- 
    Document   : header
    Created on : 26/01/2017, 18:07:25
    Author     : Felipe Almeida
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<header class="page-header">
    <h1>PokeProject <small class="pull-right"><span class="fa fa-user"></span> Usuário</small></h1>
    <div>Olá, <strong><%= session.getAttribute("logged_user_name") %></strong> - <a href="LoginService?action=logout">Sair</a></div>
</header>