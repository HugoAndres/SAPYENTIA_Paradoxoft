<%-- 
    Document   : verSolicitudesDeProyecto
    Created on : 24 nov. 2021, 1:08:14
    Author     : gmelo
--%>

<%@page import="com.paradoxoft.mx.dto.DirectorDTO"%>
<%@page import="com.paradoxoft.mx.modelo.Alumno"%>
<%@page import="com.paradoxoft.mx.modelo.SolicitudProyecto"%>
<%@page import="java.util.List"%>
<%@page import="com.paradoxoft.mx.dao.SolicitudProyectoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es-419" dir="ltr">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Solicitudes de proyecto de trabajo terminal | SAPYENTIA</title>
        <link rel="shortcut icon" href="../recursos/ico/monogramaSAPYENTIA.ico" type="image/x-icon" />
        <link rel="stylesheet" href="../recursos/css/estilosVerSolicitudesDeProyecto.css" />
        <script src="../recursos/js/scriptVerSolicitudesDeProyecto.js"></script>
    </head>
    <body>
        <div id="divOculto">
            <div id="divFormaDeAltaDeProyectoMedianteSolicitud"></div>
        </div>
        <header>
            <nav>
                <a href="../index.html"><img src="../recursos/svg/logoSAPYENTIA.svg" alt="Ir a página de inicio" title="Ir a página de inicio" id="logoSAPYENTIA" /></a>
            </nav>
        </header>
        <div id="cuerpo">
            <main>
                <h1>Solicitudes de proyecto de trabajo terminal</h1>
                <p>
                    Estas son las solicitudes de proyecto de trabajo terminal ingresadas por los alumnos.
                    Para iniciar el proyecto de trabajo terminal relacionado a ellas, asígneles un número de
                    identificación después de dar clic en el botón correspondiente.
                </p>
                <div id="contenedorSolicitudes">
<%
    SolicitudProyectoDAO dao = new SolicitudProyectoDAO();
    List<SolicitudProyecto> solicitudesDeProyecto = dao.traeSolicitudesProyecto();
    if(solicitudesDeProyecto == null){
%>
                    <p>No hay solicitudes de proyecto para mostrar</p>
<%
        
    }
    else{
        List<Alumno> listaDeAutores;
        List<DirectorDTO> listaDeDirectores;
        for(SolicitudProyecto solicitudDeProyecto : solicitudesDeProyecto) {
            int idSolicitudProyecto = solicitudDeProyecto.getIdSolicitudProyecto();
            
            listaDeAutores = dao.traeAutoresSolicitudProyecto(idSolicitudProyecto);
            StringBuilder nombresDeAutores = new StringBuilder();
            if(listaDeAutores == null){
                nombresDeAutores.append("Sin autores");
            }
            else{
                int numAutores = listaDeAutores.size();
                for(int i = 0; i < numAutores - 1; i++){
                    Alumno autorActual = listaDeAutores.get(i);
                    nombresDeAutores.append(autorActual.getNombres());
                    nombresDeAutores.append(" ");
                    nombresDeAutores.append(autorActual.getApellidos());
                    nombresDeAutores.append(", ");
                }
                Alumno ultimoAutor = listaDeAutores.get(numAutores - 1);
                nombresDeAutores.append(ultimoAutor.getNombres());
                nombresDeAutores.append(" ");
                nombresDeAutores.append(ultimoAutor.getApellidos());
            }

            listaDeDirectores = dao.traeDirectoresSolicitudProyecto(idSolicitudProyecto);
            StringBuilder nombreDeDirector = new StringBuilder();
            StringBuilder nombresDeCodirectores = new StringBuilder();
            if(listaDeDirectores == null){
                nombreDeDirector.append("Sin director");
                nombresDeCodirectores.append("Sin codirectores");
            }
            else{
                int numDirectores = listaDeDirectores.size();
                DirectorDTO director = listaDeDirectores.get(0);
                nombreDeDirector.append(director.getNombres());
                nombreDeDirector.append(" ");
                nombreDeDirector.append(director.getApellidos());
                if(numDirectores == 1)
                    nombresDeCodirectores.append("Sin codirectores");
                else{
                    for(int i = 1; i < numDirectores - 1; i++){
                        DirectorDTO codirectorActual = listaDeDirectores.get(i);
                        nombresDeCodirectores.append(codirectorActual.getNombres());
                        nombresDeCodirectores.append(" ");
                        nombresDeCodirectores.append(codirectorActual.getApellidos());
                        nombresDeCodirectores.append(", ");
                    }
                    DirectorDTO ultimoCodirector = listaDeDirectores.get(numDirectores - 1);
                    nombresDeCodirectores.append(ultimoCodirector.getNombres());
                    nombresDeCodirectores.append(" ");
                    nombresDeCodirectores.append(ultimoCodirector.getApellidos());
                }
            }
%>
                    <div class="contenedorSolicitud">
                        <p class="tituloSolicitud"><%=solicitudDeProyecto.getTitulo()%></p>
                        <p class="atributosSolicitud">
                            <span><span class="atributoSolicitudNegrita">Autores:</span> <%=nombresDeAutores%></span>
                            <span><span class="atributoSolicitudNegrita">Director:</span> <%=nombreDeDirector%></span>
                            <span><span class="atributoSolicitudNegrita">Codirectores:</span> <%=nombresDeCodirectores%></span>
                        </p>
                        <p class="atributosExtraSolicitud"></p>
                        <button type="button" class="botonVerMas" id="verSolicitud<%=idSolicitudProyecto%>">Ver más...</button>
                        <button type="button" class="botonVerPropuesta" id="verPropuesta<%=idSolicitudProyecto%>">Ver propuesta</button>
                        <button type="button" class="botonIniciarProyecto" id="iniciarProyectoConSolicitud<%=idSolicitudProyecto%>">Iniciar proyecto</button>
                    </div>
<%
        }
    }
%>
                </div>
            </main>
            <object type="application/pdf" title="documentoAsociado" data="https://archivo.ucr.ac.cr/docum/tesis2.pdf" id="contenedorPDF"></object>
        </div>
        <footer>
            <p>© 2021 Paradoxoft.</p>
        </footer>
    </body>
</html>
