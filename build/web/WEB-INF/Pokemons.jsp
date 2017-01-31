<%
    if (session.getAttribute("logged") == null) {
        response.sendRedirect("LoginPage?msg=not_logged");
    }
%>
<%@page import="JavaProject.Pokemon"%>
<%@page import="JavaProject.PokemonDAO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>PokeProject - Pokemons</title>
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
                    <section class="pokemons">
                        <%
                            PokemonDAO pokemon_dao = new PokemonDAO();
                            Pokemon[] pokemons = pokemon_dao.getAll();
                        %>
                        <h3>Lista de pokemons cadastrados</h3>
                        <ul class="list-group">
                            <%
                                if (!pokemons.equals(null)) {
                                    for (int i = 0; i < pokemons.length; i = i + 1) {
                            %>
                            <li class="list-group-item">
                                <div class="row">
                                    <div class="col-md-3">
                                        <img class="img-responsive img-thumbnail" src="static/uploads/<%= pokemons[i].getImage_url() %>" alt="Foto do pokemon">
                                    </div>
                                    <div class="col-md-9">
                                        <h3><a href="Pokemon?id=<%= pokemons[i].getId() %>"><%= pokemons[i].getName() %></a></h3>
                                        <p><%= pokemons[i].getDescription() %></p>
                                        <p>EXP padrão: <strong><%= pokemons[i].getDefault_exp() %></strong></p>
                                    </div>
                                </div>
                            </li>
                            <%
                                    }
                                }
                            %>
                        </ul>
                        <div class="panel panel-primary">
                            <div class="panel-heading text-center">Cadastrar novo pokemon</div>
                            <div class="panel-body">
                                <form action="PokemonService" method="post" class="form" enctype="multipart/form-data">
                                    <input type="hidden" name="action" value="add" />
                                    <input type="hidden" name="image_url" value="default" />
                                    <div class="form-group">
                                        <label for="name">Nome</label>
                                        <input type="text" name="name" id="name" maxlength="64" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="description">Descrição</label>
                                        <textarea name="description" class="form-control" id="description" cols="30" rows="3"></textarea>
                                    </div>
                                    <div class="form-group">
                                        <label for="image">Foto do pokemon</label>
                                        <input type="file" name="image" id="image_url" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="default_exp">EXP padrão</label>
                                        <input type="number" min="1" name="default_exp" id="default_exp" value="10" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <input type="submit" name="form_submit" value="Cadastrar pokemon" class="btn btn-success">
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