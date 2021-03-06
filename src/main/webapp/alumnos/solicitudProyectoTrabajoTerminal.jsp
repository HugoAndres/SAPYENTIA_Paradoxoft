<%-- 
    Document   : solicitudProyectoTrabajoTerminal
    Created on : 17 dic. 2021, 11:23:31
    Author     : gmelo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es-419" dir="ltr">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Generar solicitud de proyecto de trabajo terminal | SAPYENTIA</title>
        <link rel="shortcut icon" href="../recursos/ico/monogramaSAPYENTIA.ico" type="image/x-icon" />
        <link rel="stylesheet" href="../recursos/css/estilosFormaSolicitud.css" />
        <script src="../recursos/js/scriptFormaSolicitud.js"></script>
    </head>
    <body>
        <header>
            <nav>
                <a href="../index.html"><img src="../recursos/svg/logoSAPYENTIA.svg" alt="Ir a página de inicio" title="Ir a página de inicio" id="logoSAPYENTIA" /></a>
            </nav>
        </header>
        <div id="cuerpo">
            <main>
                <h1>Generar solicitud de proyecto de trabajo terminal</h1>
                <p>
                    ¿Ya tienes una propuesta de trabajo terminal para iniciar a desarrollar tu proyecto?
                    Envíala para que sea revisada por los profesores sinodales que te acompañarán durante el resto del proceso.
                    Llena los datos del formulario siguiente para iniciar tu solicitud de creación de proyecto de trabajo terminal.
                    Recuerda que debes haber conseguido un profesor director de tu trabajo terminal y, opcionalmente, profesores codirectores,
                    los cuales han de haber dado visto bueno a la propuesta que adjuntarás.
                </p>
                <div>
                    <form action="GenerarSolicitudProyectoTrabajoTerminal" method="post" target="_self">
                        <label for="campoTitulo">Título del trabajo terminal:</label>
                        <input type="text" name="titulo" id="campoTitulo" class="inputNormal" required="required" />
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
                        <label for="campoInstitucion">Institución de origen:</label>
                        <input type="text" name="institucion" id="campoInstitucion" class="inputNormal" required="required" />
                        <label for="campoUnidadAcademica">Unidad académica:</label>
                        <input type="text" name="unidadAcademica" id="campoUnidadAcademica" class="inputNormal" required="required" />
                        <label for="campoPalabraClave1">Palabras clave:</label>
                        <div id="camposPalabrasClave">
                            <div class="campoConBoton">
                                <input type="text" name="palabraClave1" id="campoPalabraClave1" required="required" />
                                <button type="button" id="botonMasPalabrasClave">+</button>
                            </div>
                        </div>
                        <label for="campoRutaArchivoPropuesta">Archivo de propuesta:</label>
                        <input type="file" name="rutaArchivoPropuesta" id="campoRutaArchivoPropuesta" accept="application/pdf" class="inputNormal" required="required" />
                        <input type="submit" id="botonSubmit" value="Generar solicitud" />
                    </form>
                    <img src="../recursos/svg/sobreDeMensaje.svg" alt="Sobre de mensaje" title="Solicitud de proyecto de trabajo terminal" id="sobreDeMensaje" />
                </div>
            </main>
        </div>
        <footer>
            <p>© 2021 Paradoxoft.</p>
        </footer>
    </body>
</html>
