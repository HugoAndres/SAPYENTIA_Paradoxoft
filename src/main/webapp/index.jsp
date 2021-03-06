<%-- 
    Document   : index
    Created on : 17 dic. 2021, 11:08:38
    Author     : gmelo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es-419" dir="ltr">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>SAPYENTIA | Inicia sesión o regístrate</title>
        <link rel="shortcut icon" href="recursos/ico/monogramaSAPYENTIA.ico" type="image/x-icon" />
        <link rel="stylesheet" href="recursos/css/estilosPaginaPrincipal.css" />
        <script src="recursos/js/usuarios/scriptDivOcultoLogin.js"></script>
        <script src="recursos/js/usuarios/scriptPaginaPrincipal.js"></script>
    </head>
    <body>
        <div id="divOculto">
            <div>
                <p class="tituloForma">Inicia sesión en SAPYENTIA</p>
                <p class="descForma">Usa los datos que proporcionaste para registrarte.</p>
                <form action="IniciarSesion" method="post" target="_self">
                    <label for="campoLoginEmail">Correo electrónico:</label>
                    <input type="text" name="emailLogin" id="campoLoginEmail" required="required" />
                    <label for="campoLoginContrasenia">Contraseña:</label>
                    <input type="password" name="contraseniaLogin" id="campoLoginContrasenia" required="required" />
                    <input type="submit" value="Iniciar sesión" />
                </form>
            </div>
        </div>
        <header>
            <nav>
                <a href="index.html"><img src="recursos/svg/logoSAPYENTIA.svg" alt="Ir a página de inicio" title="Ir a página de inicio" id="logoSAPYENTIA" /></a>
                <a class="vinculoSinImagen" href="usuarios/registro.jsp">Registrarse</a>
                <button>Iniciar sesión</button>
            </nav>
        </header>
        <div id="cuerpo">
            <main>
                <h1>Bienvenido a SAPYENTIA</h1>
                <p>SAPYENTIA es un repositorio en línea con tesis de diferentes instituciones y unidades académicas.</p>
                <p>Busca algo para comenzar y te mostraré algunos resultados que encuentre.</p>
                <form>
                    <input type="text" name="busqueda" placeholder="Busca por nombre, título, autor..." />
                    <button type="submit"><img src="recursos/svg/lupa.svg" alt="Lupa" title="Lupa" /></button>
                </form>
            </main>
        </div>
        <footer>
            <p>© 2021 Paradoxoft.</p>
        </footer>
    </body>
</html>
