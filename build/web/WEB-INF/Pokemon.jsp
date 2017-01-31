<%
    if (session.getAttribute("logged") == null) {
        response.sendRedirect("LoginPage?msg=not_logged");
    }
%>
<%@page import="JavaProject.Pokemon"%>
<%@page import="JavaProject.PokemonDAO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    if (request.getParameter("id") == null) response.sendRedirect("Pokemons?error=unset_id");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>PokeProject - Pokemon</title>
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
                    PokemonDAO pokemon_dao = new PokemonDAO();
                    Pokemon pokemon = pokemon_dao.get(Integer.parseInt(request.getParameter("id")));
                    if (!pokemon.equals(null)) {
                %>
                <section class="pokemon-profile">
                    <div class="panel panel-primary">
                        <div class="panel-heading text-center">
                            <strong>Ficha do Pokemon</strong>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-8">
                                    <h2><%= pokemon.getName() %></h2>
                                    <p>
                                        <strong>Descrição:</strong> <br />
                                        <%= pokemon.getDescription() %>
                                    </p>
                                    <p>EXP padrão: <strong><%= pokemon.getDefault_exp() %></strong></p>
                                </div>
                                <div class="col-md-4">
                                    <img class="img-responsive img-thumbnail" src="static/uploads/<%= pokemon.getImage_url() %>" alt="Foto do pokemon">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-primary">
                        <div class="panel-heading text-center">
                            <strong>Editar pokemon</strong>
                        </div>
                        <div class="panel-body">
                            <form action="PokemonService" method="post" class="form" enctype="multipart/form-data">
                                <input type="hidden" name="action" value="update" />
                                <input type="hidden" name="id" value="<%= pokemon.getId() %>" />
                                <div class="form-group">
                                    <label for="name">Nome</label>
                                    <input type="text" name="name" value="<%= pokemon.getName() %>" id="name" maxlength="64" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label for="description">Descrição</label>
                                    <textarea name="description" class="form-control" id="description" cols="30" rows="3"><%= pokemon.getDescription() %></textarea>
                                </div>
                                <div class="form-group">
                                    <label for="image_url">Foto do pokemon</label>
                                    <input type="file" name="image_url" id="image_url" class="form-control" >
                                </div>
                                <div class="form-group">
                                    <label for="default_exp">EXP padrão</label>
                                    <input type="number" min="1" name="default_exp" id="default_exp" value="<%= pokemon.getDefault_exp() %>" class="form-control">
                                </div>
                                <div class="form-group">
                                    <input type="submit" name="form_submit" value="Editar pokemon" class="btn btn-success">
                                    <input type="reset" name="form_submit" value="Cancelar" class="btn btn-default pull-right">
                                </div>
                            </form>
                            <hr>
                            <form action="PokemonService" method="post" class="form">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="id" value="<%= pokemon.getId() %>">
                                <div class="form-group">
                                    <label for="confirm_delete"><input type="checkbox" name="confirm_delete" id="confirm_delete" value="ok"> Confirmar exclusão de pokemon</label>
                                </div>
                                <div class="form-group">
                                    <input type="submit" name="form_submit" value="Deletar pokemon" class="btn btn-danger">
                                    <input type="reset" name="form_submit" value="Cancelar" class="btn btn-default pull-right">
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