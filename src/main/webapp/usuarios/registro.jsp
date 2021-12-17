<%-- 
    Document   : resgistro
    Created on : 17 dic. 2021, 5:10:25
    Author     : gmelo
--%>

<!DOCTYPE html>
<html lang="es-419" dir="ltr">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>SAPYENTIA | Registro de cuenta</title>
        <link rel="shortcut icon" href="../recursos/ico/monogramaSAPYENTIA.ico" type="image/x-icon" />
        <link rel="stylesheet" href="../recursos/css/usuarios/estilosRegistro.css" />
        <script src="../recursos/js/usuarios/scriptDivOcultoLogin.js"></script>
        <script src="../recursos/js/usuarios/scriptRegistro.js"></script>
    </head>
    <body>
        <div id="divOculto">
            <div>
                <p class="tituloForma">Inicia sesi�n en SAPYENTIA</p>
                <p class="descForma">Usa los datos que proporcionaste para registrarte.</p>
                <form action="indefinido.html" method="post" target="_self">
                    <label for="campoLoginEmail">Correo electr�nico:</label>
                    <input type="text" name="titulo" id="campoLoginEmail" required="required" />
                    <label for="campoLoginContrasenia">Contrase�a:</label>
                    <input type="text" name="titulo" id="campoLoginContrasenia" required="required" />
                    <input type="submit" value="Iniciar sesi�n" />
                </form>
            </div>
        </div>
        <header>
            <nav>
                <a href="../index.html"><img src="../recursos/svg/logoSAPYENTIA.svg" alt="Ir a p�gina de inicio" title="Ir a p�gina de inicio" id="logoSAPYENTIA" /></a>
                <a class="vinculoSinImagen" href="registro.jsp">Registrarse</a>
                <button>Iniciar sesi�n</button>
            </nav>
        </header>
        <div id="cuerpo">
            <main>
                <h1>Reg�strate en SAPYENTIA</h1>
                <p>Solo llena el siguiente formulario y nosotros haremos el resto.</p>
                <div>
                    <img src="../recursos/svg/usuario.svg" alt="Usuario" title="Usuario" id="imgUsuario" />
                    <form action="AltaUsuario" method="post" target="_self" autocomplete="off">
                        <label for="comboCategoria">Quiero registrarme como:</label>
                        <select id="comboCategoria" name="valorClaveCategoria" required="required">
                            <option value="">Seleccione una opci�n...</option>
                            <option value="alumno">Alumno</option>
                            <option value="docente">Docente</option>
                            <option value="personalAdministrativo">Personal administrativo</option>
                            <option value="personalAcademico">Personal acad�mico</option>
                            <option value="personalBibliotecario">Personal bibliotecario</option>
                        </select>
                        <label for="campoNombres">Nombres:</label>
                        <input type="text" name="nombres" id="campoNombres" required="required" />
                        <label for="campoApellidos">Apellidos:</label>
                        <input type="text" name="apellidos" id="campoApellidos" required="required" />
                        <label for="campoInstitucion">Instituci�n:</label>
                        <input type="text" name="institucion" id="campoInstitucion" required="required" />
                        <label for="campoUnidadAcademica">Unidad acad�mica:</label>
                        <input type="text" name="unidadAcademica" id="campoUnidadAcademica" required="required" />
                        <label for="campoEmail">Correo electr�nico:</label>
                        <input type="email" name="email" id="campoEmail" required="required" />
                        <label for="campoContrasenia">Contrase�a:</label>
                        <input type="password" name="contrasenia" id="campoContrasenia" required="required" />
                        
                        <label for="campoCarrera" id="labelCarrera">Carrera:</label>
                        <input type="text" name="carrera" id="campoCarrera" required="required" />
                        
                        <label for="campoAcademia" id="labelAcademia">Academia:</label>
                        <input type="text" name="academia" id="campoAcademia" required="required" />
                        <label for="campoGrado" id="labelGrado">Grado:</label>
                        <input type="text" name="grado" id="campoGrado" required="required" />
                        <input type="submit" value="Registrar cuenta" />
                    </form>
<%
    String error = request.getParameter("error");
    if(error != null){
        if(error.equals("true")){
%>
                    <p>Ha ocurrido un error al registrar el usuario. Int�ntelo de nuevo.</p>
<%
        }
    }
%>
                </div>
            </main>
        </div>
        <footer>
            <p>� 2021 Paradoxoft.</p>
        </footer>
    </body>
</html>