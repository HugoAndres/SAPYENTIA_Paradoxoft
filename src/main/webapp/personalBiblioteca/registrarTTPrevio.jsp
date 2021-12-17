<%-- 
    Document   : registrarTTPrevio
    Created on : 17 dic. 2021, 05:33:37
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es-419" dir="ltr">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Registrar un trabajo terminal previo | SAPYENTIA</title>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/recursos/ico/monogramaSAPYENTIA.ico" type="image/x-icon" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/recursos/css/estilosFormaSolicitud.css" />
        <script src="${pageContext.request.contextPath}/recursos/js/scriptFormaTTPrevio.js"></script>
    </head>
    <body>
        <header>
            <nav>
                <a href="${pageContext.request.contextPath}/index.html"><img src="${pageContext.request.contextPath}/recursos/svg/logoSAPYENTIA.svg" alt="Ir a página de inicio" title="Ir a página de inicio" id="logoSAPYENTIA" /></a>
            </nav>
        </header>
        <div id="cuerpo">
            <main>
                <h1>Registrar trabajo terminal previo</h1>
                <p>
                    Este formulario te ayudará a enviar un archivo de trabajo terminal histórico al repositorio para
                    consulta por los usuarios de SAPYENTIA. Deberás incluir todos los metadatos que identifican al trabajo
                    terminal y asegurarte de que el archivo que envíes sea del formato PDF.
                </p>
                <p>${mensaje}</p>
                <div>
                    <form action="${pageContext.request.contextPath}/AltaTrabajoTerminalPrevio" method="post" target="_self" enctype="multipart/form-data">
                        <label for="campoTitulo">Título del trabajo terminal:</label>
                        <input type="text" name="titulo" id="campoTitulo" class="inputNormal" required="required" />
                        <label for="campoNumId">Número identificador:</label>
                        <input type="text" name="numId" id="campoNumId" class="inputNormal" placeholder="Inserte un número para este trabajo en la base de datos" required="required" />
                        <label for="campoAutor1">Autores:</label>
                        <div id="camposAutores">
                            <div class="campoConBoton">
                                <input type="text" name="autor1" id="campoAutor1" required="required" />
                                <button type="button" id="botonMasAutores">+</button>
                            </div>
                        </div>
                        <label for="campoDirector">Director:</label>
                        <input type="text" name="director" id="campoDirector" placeholder="Inserte un nombre" class="inputNormal" required="required" />
                        <label for="campoCodirector1">Codirectores:</label>
                        <div id="camposCodirectores">
                            <div class="campoConBoton">
                                <input type="text" name="codirector1" id="campoCodirector1" required="required" />
                                <button type="button" id="botonMasCodirectores">+</button>
                            </div>
                        </div>
                        <label for="campoSinodal1">Sinodales:</label>
                        <div id="camposSinodales">
                            <div class="campoConBoton">
                                <input type="text" name="sinodal1" id="campoSinodal1" required="required" />
                                <button type="button" id="botonMasSinodales">+</button>
                            </div>
                        </div>
                        <label for="campoInstitucion">Institución de origen:</label>
                        <input type="text" name="institucion" id="campoInstitucion" class="inputNormal" required="required" />
                        <label for="campoUnidadAcademica">Unidad académica:</label>
                        <input type="text" name="unidadAcademica" id="campoUnidadAcademica" class="inputNormal" required="required" />
                        <label for="campoFechaRealizacion">Fecha de realización:</label>
                        <input type="date" name="fechaRealizacion" id="campoFechaRealizacion" class="inputNormal" required="required" />
                        <label for="campoPalabraClave1">Palabras clave:</label>
                        <div id="camposPalabrasClave">
                            <div class="campoConBoton">
                                <input type="text" name="palabraClave1" id="campoPalabraClave1" required="required" />
                                <button type="button" id="botonMasPalabrasClave">+</button>
                            </div>
                        </div>
                        <label for="campoRutaArchivoPropuesta">Archivo de propuesta:</label>
                        <input type="file" name="rutaArchivoPropuesta" id="campoRutaArchivoPropuesta" accept=".pdf" class="inputNormal" required="required" />
                        <label for="campoRutaArchivoTT">Archivo de trabajo terminal:</label>
                        <input type="file" name="rutaArchivoTT" id="campoRutaArchivoTT" accept=".pdf" class="inputNormal" required="required" />
                        <input type="submit" id="botonSubmit" value="Enviar" />
                    </form>
                    <img src="${pageContext.request.contextPath}/recursos/svg/libro.svg" alt="Sobre de mensaje" title="Solicitud de proyecto de trabajo terminal" id="sobreDeMensaje" />
                </div>
            </main>
        </div>
        <footer>
            <p>© 2021 Paradoxoft.</p>
        </footer>
    </body>
</html>