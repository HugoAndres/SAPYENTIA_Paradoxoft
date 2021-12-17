<%-- 
    Document   : resgistro
    Created on : 17 dic. 2021, 5:10:25
    Author     : gmelo
--%>

<%@page import="com.paradoxoft.mx.modelo.Academia"%>
<%@page import="java.util.List"%>
<%@page import="com.paradoxoft.mx.dao.AcademiaDAO"%>
<!DOCTYPE html>
<html lang="es-419" dir="ltr">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>SAPYENTIA | Registro de cuenta</title>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/recursos/ico/monogramaSAPYENTIA.ico" type="image/x-icon" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/recursos/css/usuarios/estilosRegistro.css" />
        <script src="${pageContext.request.contextPath}/recursos/js/usuarios/scriptDivOcultoLogin.js"></script>
        <script src="${pageContext.request.contextPath}/recursos/js/usuarios/scriptRegistro.js"></script>
    </head>
    <body>
        <div id="divOculto">
            <div>
                <p class="tituloForma">Inicia sesión en SAPYENTIA</p>
                <p class="descForma">Usa los datos que proporcionaste para registrarte.</p>
                <form action="${pageContext.request.contextPath}/IniciarSesion" method="post" target="_self">
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
                <a href="${pageContext.request.contextPath}/index.html"><img src="${pageContext.request.contextPath}/recursos/svg/logoSAPYENTIA.svg" alt="Ir a página de inicio" title="Ir a página de inicio" id="logoSAPYENTIA" /></a>
                <a class="vinculoSinImagen" href="${pageContext.request.contextPath}/usuarios/registro.jsp">Registrarse</a>
                <button>Iniciar sesión</button>
            </nav>
        </header>
        <div id="cuerpo">
            <main>
                <h1>Regístrate en SAPYENTIA</h1>
                <p>Solo llena el siguiente formulario y nosotros haremos el resto.</p>
<%
    String exito = request.getParameter("exito");
    if(exito != null){
        if(exito.equals("true")){
%>
                <p class="mensajeExito">Usuario registrado.</p>
<%
        }
    }
    String error = request.getParameter("error");
    if(error != null){
        if(error.equals("true")){
%>
                <p class="mensajeError">Ha ocurrido un error al registrar el usuario. Inténtelo de nuevo.</p>
<%
        }
    }
%>
                <div>
                    <img src="${pageContext.request.contextPath}/recursos/svg/usuario.svg" alt="Usuario" title="Usuario" id="imgUsuario" />
                    <form action="${pageContext.request.contextPath}/AltaUsuario" method="post" target="_self" autocomplete="off">
                        <label for="comboCategoria">Quiero registrarme como:</label>
                        <select id="comboCategoria" name="valorComboCategoria" required="required">
                            <option value="">Seleccione una opción...</option>
                            <option value="alumno">Alumno</option>
                            <option value="docente">Docente</option>
                            <option value="personalAdministrativo">Personal administrativo</option>
                            <option value="personalAcademico">Personal académico</option>
                            <option value="personalBibliotecario">Personal bibliotecario</option>
                        </select>
                        <label for="campoIdentificador">Matrícula:</label>
                        <input type="text" name="identificador" id="campoIdentificador" required="required" />
                        <label for="campoNombres">Nombres:</label>
                        <input type="text" name="nombres" id="campoNombres" required="required" />
                        <label for="campoApellidos">Apellidos:</label>
                        <input type="text" name="apellidos" id="campoApellidos" required="required" />
                        <label for="campoInstitucion">Institución:</label>
                        <input type="text" name="institucion" id="campoInstitucion" required="required" />
                        <label for="campoUnidadAcademica">Unidad académica:</label>
                        <input type="text" name="unidadAcademica" id="campoUnidadAcademica" required="required" />
                        <label for="campoEmail">Correo electrónico:</label>
                        <input type="email" name="email" id="campoEmail" required="required" />
                        <label for="campoContrasenia">Contraseña:</label>
                        <input type="password" name="contrasenia" id="campoContrasenia" required="required" />
                        
                        <label for="campoCarrera" id="labelCarrera">Carrera:</label>
                        <input type="text" name="carrera" id="campoCarrera" required="required" />
                        
                        <label for="comboAcademia" id="labelAcademia">Academia:</label>
                        <select id="comboAcademia" name="valorComboAcademia" required="required">
                            <option value="">Seleccione una opción...</option>
<%
    AcademiaDAO dao = new AcademiaDAO();
    List<Academia> listaDeAcademias = dao.traeAcademias();
    if(listaDeAcademias != null){
        int numAcademias =  listaDeAcademias.size();
        for (int i = 0; i < numAcademias; i++){
%>
                            <option value="<%=listaDeAcademias.get(i).getIdAcademia()%>"><%=listaDeAcademias.get(i).getNombre()%></option>
<%
        }
    }
%>
                        </select>
                        <label for="campoGrado" id="labelGrado">Grado:</label>
                        <input type="text" name="grado" id="campoGrado" required="required" />
                        <input type="submit" value="Registrar cuenta" />
                    </form>
                </div>
            </main>
        </div>
        <footer>
            <p>© 2021 Paradoxoft.</p>
        </footer>
    </body>
</html>