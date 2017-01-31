<%@page import="JavaProject.Pokemon"%>
<%@page import="JavaProject.PokemonDAO"%>
<%@page import="JavaProject.TrainerPokemon"%>
<%@page import="JavaProject.Trainer"%>
<%@page import="JavaProject.TrainerDAO"%>
<%
    if (session.getAttribute("logged") == null) {
        response.sendRedirect("LoginPage?msg=not_logged");
    }  
    if (request.getParameter("id") == null) {
        response.sendRedirect("Trainers?error=unset_id");
    }
%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>PokeProject - Treinadores</title>
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
                    TrainerDAO trainer_dao = new TrainerDAO();
                    Trainer trainer = trainer_dao.getWithPokemons(Integer.parseInt(request.getParameter("id")));
                    if (trainer != null) {
                %>
                <section class="user-prfile">
                    <div class="panel panel-primary">
                        <div class="panel-heading text-center">Ficha do Treinador</div>
                        <div class="panel-body">
                            <p>
                                <strong>Nome:</strong> <%= trainer.getName() %>
                            </p>
                            <p>
                                <strong>E-mail:</strong> <%= trainer.getEmail() %>
                            </p>
                            <p>
                                <strong>Cidade</strong> <%= trainer.getCity_name() %>
                            </p>
                            <p>
                                <strong>Nome de usuário:</strong> <%=trainer.getUsername() %>
                            </p>
                            <p>
                                <strong>Dinheiro</strong> $ <%= trainer.getMoney() %>
                            </p>
                        </div>
                    </div>
                    <div class="panel panel-primary">
                        <div class="panel-heading text-center">Lista de Pokemons</div>
                        <div class="panel-body">
                            <table class="table table-striped table-condensed">
                                <thead>
                                    <tr>
                                        <th>#ID</th>
                                        <th>Nome</th>
                                        <th>EXP</th>
                                        <th class="text-center">Opções</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                    for (int i = 0; i < trainer.getPokemons().length; i++) {
                                    %>
                                    <tr>
                                        <td><%= trainer.getPokemons()[i].getId() %></td>
                                        <td><%= trainer.getPokemons()[i].getPokemon().getName() %></td>
                                        <td><%= trainer.getPokemons()[i].getExp() %></td>
                                        <td class="text-center">
                                            <form action="TrainerService" method="post" class="form">
                                                <input type="hidden" name="action" value="delete_pokemon" />
                                                <input type="hidden" name="trainer_id" value="<%= trainer.getId() %>" />
                                                <input type="hidden" name="pokemon_id" value="<%= trainer.getPokemons()[i].getId() %>" />
                                                <input type="submit" name="form_submit" value="Remover" class="btn btn-danger btn-xs" />
                                            </form>
                                        </td>
                                    </tr>
                                    <%
                                    }
                                    %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <h3>Adicionar pokemon ao treinador</h3>
                    <form action="TrainerService" method="POST" class="form">
                        <input type="hidden" name="action" value="add_pokemon" />
                        <input type="hidden" name="trainer_id" value="<%= trainer.getId() %>" />
                        <input type="hidden" name="exp" value="10" />
                        <div class="input-group">
                            <select class="form-control" name="pokemon_id">
                                <%
                                    PokemonDAO pokemon_dao = new PokemonDAO();
                                    Pokemon[] pokemons = pokemon_dao.getAll();
                                    for (int i = 0; i < pokemons.length; i++) {
                                %>
                                <option value="<%= pokemons[i].getId() %>"><%= pokemons[i].getName() %></option>
                                <%
                                    }
                                %>
                            </select> 
                            <span class="input-group-btn">
                                <input type="submit" class="btn btn-success" name="form_submit" value="Adicionar" />
                            </span>
                        </div>
                        
                    </form>
                    <hr>
                    <div class="panel panel-default">
                        <div class="panel-heading text-center">
                            <strong>Editar treinador</strong>
                        </div>
                        <div class="panel-body">
                            <form action="TrainerService" method="post" class="form">
                                <input type="hidden" name="action" value="update">
                                <input type="hidden" name="id" value="<%= trainer.getId() %>">
                                <div class="form-group">
                                    <label for="name">Nome</label>
                                    <input value="<%= trainer.getName() %>" type="text" name="name" id="name" maxlength="64" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label for="email">E-mail</label>
                                    <input value="<%= trainer.getEmail() %>" type="text" name="email" id="email" max="32" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label for="city_name">Cidade</label>
                                    <input value="<%= trainer.getCity_name() %>" type="text" name="city_name" id="city_name" max="32" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label for="username">Nome de usuário</label>
                                    <input value="<%= trainer.getUsername() %>" type="text" name="username" id="username" max="32" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label for="password">Senha</label>
                                    <input value="<%= trainer.getPassword() %>" type="password" name="password" id="password" maxlength="32" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label for="money">Dinheiro</label>
                                    <input value="<%= trainer.getMoney() %>" type="money" name="money" id="money" maxlength="32" class="form-control">
                                </div>
                                <div class="form-group">
                                    <input type="submit" name="form_submit" value="Editar usuário" class="btn btn-success">
                                    <input type="reset" name="form_submit" value="Cancelar" class="btn btn-default pull-right">
                                </div>
                            </form>
                            <hr>
                            <form action="TrainerService" method="post" class="form">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="id" value="<%= trainer.getId() %>">
                                <div class="form-group">
                                    <input type="submit" name="form_submit" value="Deletar trainador" class="btn btn-danger">
                                </div>
                                <div class="form-group">
                                    <label for="confirm_delete"><input type="checkbox" name="confirm_delete" id="confirm_delete" value="ok"> Confirmar exclusão de treinador</label>
                                </div>
                            </form>
                        </div>
                    </div>
                </section>
                <%
                    } else {
                        response.sendRedirect("Trainers?msg=trainer_not_found");
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