<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>PokeProject - Login</title>
    <jsp:include page="html_head.jsp" />
</head>
<body>
    <main class="container">
        <section class="login">
            <h1 class="text-center">PokeProject</h1>
            <div class="panel panel-primary">
                <div class="panel-heading text-center">
                    Acessar o sistema
                </div>
                <div class="panel-body">
                    <form action="LoginService" class="form" method="POST" id="login_service_form">
                        <div class="form-group">
                            <label for="username">Usuário</label>
                            <input type="text" name="username" id="username" placeholder="admin" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="username">Senha</label>
                            <input type="text" name="password" id="password" placeholder="admin" class="form-control">
                        </div>
                        <div class="form-group">
                            <input type="checkbox" name="user_type" id="user_type" value="trainer">
                            <label for="user_type">Sou <span>treinador</span></label>
                        </div>
                        <div class="form-group">
                            <input type="submit" name="form_submit" value="Acessar sistema" class="btn btn-block btn-primary">
                        </div>
                    </form>
                </div>
            </div>
            <footer>
                <p class="text-center">
                    <small>Criado por Felipe Almeida - <a href="http://github.com/falmeidaco">Github</a></small>
                </p>
            </footer>
        </section>
    </main>
    <script>
        $(document).ready(function() {
            $("#login_service_form").submit(function() {
               if ($("#username").val().trim() === "") {
                   window.alert("Necessário informar o nome de usuário");
                   $("#username").focus();
                   return false;
               } else if ($("#password").val().trim() === "") {
                   window.alert("Necessário informar a senha");
                   $("#password").focus();
                   return false;
               } else {
                   return true;
               }
            });
        });
    </script>
</body>
</html>