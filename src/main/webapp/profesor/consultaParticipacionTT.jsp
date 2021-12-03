<%-- 
    1793 - Valoración de un protocolo de trabajo terminal
--%>

<%@page import="com.paradoxoft.mx.comparadores.ComparadoresTrabajoTerminalDTO"%>
<%@page import="com.paradoxoft.mx.modelo.SolicitudProyecto"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.io.*"%>
<%@page import="com.paradoxoft.mx.dao.*"%>
<%@page import="com.paradoxoft.mx.dto.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Participacion previa</title>
         <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
         <link rel="stylesheet" href="../recursos/css/estiloconsultaParticipacionTT.css" />
         <link rel="shortcut icon" href="../ico/recursos/monogramaSAPYENTIA.ico" type="image/x-icon" />

    </head>
   <body>   
        <header>
            <nav>
                <a href="../index.html"><img src="../recursos/svg/logoSAPYENTIA.svg" alt="Ir a página de inicio" title="Ir a página de inicio" id="logoSAPYENTIA" /></a>
            </nav>
        </header>
        <div id="cuerpo">
            <aside>
                <center><nav class="fil">Filtros</nav></center>
                
                    <div>
                        
                       <form method="post" action="consultaParticipacionTT.jsp">                         
                           <h1>Ordenar por titulo</h1>
                           <h3><input type="radio" id="html" name="ord-tit" value="titasc"><label for="titasc">Ascendente</label></h3>
                           <h3><input type="radio" id="html" name="ord-tit" value="titdsc"><label for="titdsc">Descendente</label></h3>                         
                         <br><br><br>
                         <h1>Ordenar por fecha</h1>
                         <h3><input type="radio" id="html" name="ord-tit" value="fecasc"><label for="fecasc">Ascendente</label></h3> 
                         <h3><input type="radio" id="html" name="ord-tit" value="fecdsc"><label for="fecdsc">Descendente</label></h3> 
                                 <br><br><br>
                         <h1>Ordenar por rol</h1>
                         <h3><input type="radio" id="html" name="ord-tit" value="rolsin"><label for="rolsin">Sinodal</label></h3> 
                         <h3><input type="radio" id="html" name="ord-tit" value="roldir"><label for="roldir">Director</label></h3> 
                                 <br><br><br>
                                <center>
                               <input value="Aplicar" type="submit" id="btn-bmm-ap">
                               </center>
                        </form>
                                     
                    </div>
            
            </aside>
            <main>
                <table align="right" id="bmm-table">
                    <%
                        try {
                        String opc=request.getParameter("ord-tit");
                        if(opc == null){
                            opc="nada";
                        } 
                        TrabajoTerminalDAO t = new TrabajoTerminalDAO();
                        List<TrabajoTerminalDTO> ttd = new ArrayList<>();
                            ttd = t.buscaTrabajoTerminalPorDirectores("señora puff");
                        List<TrabajoTerminalDTO> ds = new ArrayList<>();
                            ds = t.buscaTrabajoTerminalPorSinodales("señora puff");
                            List<TrabajoTerminalDTO> res;
                             if (ttd == null || ds == null){
                                 if(ttd == null){
                                     res = new ArrayList<>(ds);
                                 }else if(ds == null){
                                    res = new ArrayList<>(ttd);                                     
                                 }else{
                                     (res = new ArrayList<>(ttd)).addAll(ds);
                                 }                                 
                             }else{                                 
                                (res = new ArrayList<>(ttd)).addAll(ds);
                             }                          
                           
                         if(opc.equalsIgnoreCase("titasc")){                                                                      
                              if (res == null || res.isEmpty()) {
                                out.println("<tr><td>No hay ningun elemento que coincida con la busqueda</td></tr>");
                            } else {
                                ComparadoresTrabajoTerminalDTO comp =new ComparadoresTrabajoTerminalDTO();
                                comp.ordenaPorTituloAscendente(res);
                                for (int i = 0; i < res.size(); i++) {
                                    out.println("<tr><td><h1>Titulo: " + res.get(i).getTitulo().toString() + "</h1>");
                                    out.println("<br><h2>Autor(es):  " + res.get(i).getNombresAutores().toString() + "  <br> Sinodales: " + res.get(i).getNombresSinodales().toString() + "   <br>  Director(es): " + res.get(i).getNombresDirectores().toString() + "</h2><br>");
                                    out.println("<h2>Unidades Académicas: " + res.get(i).getInstitucion().toString() + "     Fecha de realización: " + res.get(i).getFechaRealizacion().toString() + "       </h2> <br>");
                                    //out.println("<h2> <a href='"+ttd.get(i).getRutaTrabajoTerminal().toString()+"' target='_blank'>Documento Completo</a></h2><br>");
                                    out.println("<h2> <a href='https://archivo.ucr.ac.cr/docum/tesis2.pdf' target='_blank'>Documento Completo</a></h2><br>");
                                    out.println("<h2>Abstract</h2><br>");
                                    out.println("" + res.get(i).getAbst().toString() + "<br>");
                                    out.println("'</td></tr>");
                                }
                            }
                         }else if(opc.equalsIgnoreCase("titdsc")){                                                                            
                              if (res == null || res.isEmpty()) {
                                out.println("<tr><td>No hay ningun elemento que coincida con la busqueda</td></tr>");
                            } else {
                                ComparadoresTrabajoTerminalDTO comp =new ComparadoresTrabajoTerminalDTO();
                                comp.ordenaPorTituloDescendente(res);
                                for (int i = 0; i < res.size(); i++) {
                                    out.println("<tr><td><h1>Titulo: " + res.get(i).getTitulo().toString() + "</h1>");
                                    out.println("<br><h2>Autor(es):  " + res.get(i).getNombresAutores().toString() + "  <br> Sinodales: " + res.get(i).getNombresSinodales().toString() + "   <br>  Director(es): " + res.get(i).getNombresDirectores().toString() + "</h2><br>");
                                    out.println("<h2>Unidades Académicas: " + res.get(i).getInstitucion().toString() + "     Fecha de realización: " + res.get(i).getFechaRealizacion().toString() + "       </h2> <br>");
                                    //out.println("<h2> <a href='"+ttd.get(i).getRutaTrabajoTerminal().toString()+"' target='_blank'>Documento Completo</a></h2><br>");
                                    out.println("<h2> <a href='https://archivo.ucr.ac.cr/docum/tesis2.pdf' target='_blank'>Documento Completo</a></h2><br>");
                                    out.println("<h2>Abstract</h2><br>");
                                    out.println("" + res.get(i).getAbst().toString() + "<br>");
                                    out.println("'</td></tr>");
                                }
                            }
                         }else if(opc.equalsIgnoreCase("fecasc")){                            
                              if (res == null || res.isEmpty()) {
                                out.println("<tr><td>No hay ningun elemento que coincida con la busqueda</td></tr>");
                            } else {
                             ComparadoresTrabajoTerminalDTO comp =new ComparadoresTrabajoTerminalDTO();
                             comp.ordenaPorFechaAscendente(res);
                             for (int i = 0; i < res.size(); i++) {
                                    out.println("<tr><td><h1>Titulo: " + res.get(i).getTitulo().toString() + "</h1>");
                                    out.println("<br><h2>Autor(es):  " + res.get(i).getNombresAutores().toString() + "  <br> Sinodales: " + res.get(i).getNombresSinodales().toString() + "   <br>  Director(es): " + res.get(i).getNombresDirectores().toString() + "</h2><br>");
                                    out.println("<h2>Unidades Académicas: " + res.get(i).getInstitucion().toString() + "     Fecha de realización: " + res.get(i).getFechaRealizacion().toString() + "       </h2> <br>");
                                    //out.println("<h2> <a href='"+ttd.get(i).getRutaTrabajoTerminal().toString()+"' target='_blank'>Documento Completo</a></h2><br>");
                                    out.println("<h2> <a href='https://archivo.ucr.ac.cr/docum/tesis2.pdf' target='_blank'>Documento Completo</a></h2><br>");
                                    out.println("<h2>Abstract</h2><br>");
                                    out.println("" + res.get(i).getAbst().toString() + "<br>");
                                    out.println("'</td></tr>");
                                }
                              }
                         }else if(opc.equalsIgnoreCase("fecdsc")){                           
                              if (res == null || res.isEmpty()) {
                                out.println("<tr><td>No hay ningun elemento que coincida con la busqueda</td></tr>");
                            } else {
                                ComparadoresTrabajoTerminalDTO comp =new ComparadoresTrabajoTerminalDTO();
                                comp.ordenaPorFechaDescendente(res);
                                for (int i = 0; i < res.size(); i++) {
                                    out.println("<tr><td><h1>Titulo: " + res.get(i).getTitulo().toString() + "</h1>");
                                    out.println("<br><h2>Autor(es):  " + res.get(i).getNombresAutores().toString() + "  <br> Sinodales: " + res.get(i).getNombresSinodales().toString() + "   <br>  Director(es): " + res.get(i).getNombresDirectores().toString() + "</h2><br>");
                                    out.println("<h2>Unidades Académicas: " + res.get(i).getInstitucion().toString() + "     Fecha de realización: " + res.get(i).getFechaRealizacion().toString() + "       </h2> <br>");
                                    //out.println("<h2> <a href='"+ttd.get(i).getRutaTrabajoTerminal().toString()+"' target='_blank'>Documento Completo</a></h2><br>");
                                    out.println("<h2> <a href='https://archivo.ucr.ac.cr/docum/tesis2.pdf' target='_blank'>Documento Completo</a></h2><br>");
                                    out.println("<h2>Abstract</h2><br>");
                                    out.println("" + res.get(i).getAbst().toString() + "<br>");
                                    out.println("'</td></tr>");
                                }
                            }
                         }else if(opc.equalsIgnoreCase("roldir")){
                             
                             if (ttd == null || ttd.isEmpty()) {
                                out.println("<tr><td>No hay ningun elemento que coincida con la busqueda</td></tr>");

                            } else {
                                for (int i = 0; i < ttd.size(); i++) {
                                    out.println("<tr><td><h1>Titulo: " + ttd.get(i).getTitulo().toString() + "</h1>");
                                    out.println("<br><h2>Autor(es):  " + ttd.get(i).getNombresAutores().toString() + "  <br> Sinodales: " + ttd.get(i).getNombresSinodales().toString() + "   <br>  Director(es): " + ttd.get(i).getNombresDirectores().toString() + "</h2><br>");
                                    out.println("<h2>Unidades Académicas: " + ttd.get(i).getInstitucion().toString() + "     Fecha de realización: " + ttd.get(i).getFechaRealizacion().toString() + "       </h2> <br>");
                                    //out.println("<h2> <a href='"+ttd.get(i).getRutaTrabajoTerminal().toString()+"' target='_blank'>Documento Completo</a></h2><br>");
                                    out.println("<h2> <a href='https://archivo.ucr.ac.cr/docum/tesis2.pdf' target='_blank'>Documento Completo</a></h2><br>");
                                    out.println("<h2>Abstract</h2><br>");
                                    out.println("" + ttd.get(i).getAbst().toString() + "<br>");
                                    out.println("'</td></tr>");
                                }
                            }             
                         }else if(opc.equalsIgnoreCase("rolsin")){
                             
                            if (ds == null || ds.isEmpty()) {
                                out.println("<tr><td>No hay ningun elemento que coincida con la busqueda</td></tr>");

                            } else {
                                for (int i = 0; i < ds.size(); i++) {
                                    out.println("<tr><td><h1>Titulo: " + ds.get(i).getTitulo().toString() + "</h1>");
                                    out.println("<br><h2>Autor(es):  " + ds.get(i).getNombresAutores().toString() + "  <br> Sinodales: " + ds.get(i).getNombresSinodales().toString() + "   <br>  Director(es): " + ds.get(i).getNombresDirectores().toString() + "</h2><br>");
                                    out.println("<h2>Unidades Académicas: " + ds.get(i).getInstitucion().toString() + "     Fecha de realización: " + ds.get(i).getFechaRealizacion().toString() + "       </h2> <br>");
                                    //out.println("<h2> <a href='"+ttd.get(i).getRutaTrabajoTerminal().toString()+"' target='_blank'>Documento Completo</a></h2><br>");
                                    out.println("<h2> <a href='https://archivo.ucr.ac.cr/docum/tesis2.pdf' target='_blank'>Documento Completo</a></h2><br>");
                                    out.println("<h2>Abstract</h2><br>");
                                    out.println("" + ds.get(i).getAbst().toString() + "<br>");
                                    out.println("'</td></tr>");
                                }
                            }
                         }else if(opc.equalsIgnoreCase("nada")){   
                            if (res == null || res.isEmpty()) {
                                out.println("<tr><td>No hay ningun elemento que coincida con la busqueda</td></tr>");

                            } else {
                                for (int i = 0; i < res.size(); i++) {
                                    out.println("<tr><td><h1>Titulo: " + res.get(i).getTitulo().toString() + "</h1>");
                                    out.println("<br><h2>Autor(es):  " + res.get(i).getNombresAutores().toString() + "  <br> Sinodales: " + res.get(i).getNombresSinodales().toString() + "   <br>  Director(es): " + res.get(i).getNombresDirectores().toString() + "</h2><br>");
                                    out.println("<h2>Unidades Académicas: " + res.get(i).getInstitucion().toString() + "     Fecha de realización: " + res.get(i).getFechaRealizacion().toString() + "       </h2> <br>");
                                    //out.println("<h2> <a href='"+ttd.get(i).getRutaTrabajoTerminal().toString()+"' target='_blank'>Documento Completo</a></h2><br>");
                                    out.println("<h2> <a href='https://archivo.ucr.ac.cr/docum/tesis2.pdf' target='_blank'>Documento Completo</a></h2><br>");
                                    out.println("<h2>Abstract</h2><br>");
                                    out.println("" + res.get(i).getAbst().toString() + "<br>");
                                    out.println("'</td></tr>");
                                }
                            }
                         }   
                        } catch (NullPointerException e) {
                            System.out.println("NullPointerException thrown!");
                        }
                %>
                </table>
            </main>
        </div>
        <footer>
            <p>© 2021 Paradoxoft.</p>
        </footer>       
    </body>
</html>
