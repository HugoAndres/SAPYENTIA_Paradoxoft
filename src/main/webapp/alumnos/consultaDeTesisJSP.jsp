<%-- 
    Document   : consultaDeTesisJSP
    Created on : 14/12/2021, 10:03:34 PM
    Author     : Rodrigo
--%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.io.*"%>
<%@page import="com.paradoxoft.mx.dao.*"%>
<%@page import="com.paradoxoft.mx.dto.*"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Consulta de tesis | SAPYENTIA</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="../recursos/ico/monogramaSAPYENTIA.ico" type="image/x-icon" />
        <link rel="stylesheet" href="../recursos/css/estiloConsultas.css" />
    </head>
    
    <body>  
          <header>
            <nav>
                <a href="../index.html"><img src="../recursos/svg/logoSAPYENTIA.svg" alt="Ir a página de inicio" title="Ir a página de inicio" id="logoSAPYENTIA" /></a>
            </nav>
        </header>
        <div id="cuerpo">
            <main>
            <div class="contenedor">
               
                    <%
                   out.println(" <div  id='informacionMetadatos'>");
                   List<TrabajoTerminalDTO> ttd = new ArrayList<>();
                   TrabajoTerminalDAO t = new TrabajoTerminalDAO();  
                   TrabajoTerminalDTO ta = new TrabajoTerminalDTO();
                   String numI = request.getParameter("numI");
                   ta = t.buscaTrabajoTerminal(numI);
                   out.println("<div class='metadatos' id='titulo'> <p>TITULO:</p> ");
                   out.println("<p>"+ta.getTitulo()+"</p></div>");
                   out.println("<div class='metadatos' id='autor'> <p>Autor(es):</p> ");
                   out.println("<p>"+ta.getNombresAutores()+"</p></div>");
                   out.println("<div class='metadatos' id='institucion'> <p>Institucion:</p> ");
                   out.println("<p>"+ta.getInstitucion()+"</p></div>");
                   out.println("<div class='metadatos' id='director'> <p>Director(es):</p> ");
                   out.println("<p>"+ta.getNombresDirectores()+"</p></div>");
                   out.println("<div class='metadatos' id='sinodal'> <p>Sinodal(es):</p> ");
                   out.println("<p>"+ta.getNombresSinodales()+"</p></div>");
                   out.println("<div class='metadatos' id='ano'> <p>Año:</p> ");
                   out.println("<p>"+ta.getFechaRealizacion()+"</p></div>");
               
                out.println("</div>");
                out.println("<div id='visorpdf'><object type='application/pdf' data='https://archivo.ucr.ac.cr/docum/tesis2.pdf'></object><a href='' download='https://archivo.ucr.ac.cr/docum/tesis2.pdf'>Descargar PDF</a>");
                out.println("</div>");

                %>            
            </main>
        </div>       
        
        <footer>
            <p>© 2021 Paradoxoft.</p>
        </footer>
    </body>
</html>
