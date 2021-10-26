<%-- 
    Document   : busquedaMetadatos
    Created on : 25/10/2021, 11:14:09 AM
    Author     : Rodrigo
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.io.*"%>
<%@page import="com.paradoxoft.mx.dao.*"%>
<%@page import="com.paradoxoft.mx.dto.*"%>


<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en" dir="ltr">
    <head>
        <title>Búsqueda metadatos</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
        <script src="https://kit.fontawesome.com/a076d05399.js"></script>
        <link rel="shortcut icon" href="../ico/monogramaSAPYENTIA.ico" type="image/x-icon" />
        <link rel="stylesheet" href="../css/estiloBusquedaMetadatos.css" />
    </head>
    <body>   
        <header>
            <nav>
                <a href="index.html"><img src="../../recursos/svg/logoSAPYENTIA.svg" alt="Ir a página de inicio" title="Ir a página de inicio" id="logoSAPYENTIA" /></a>
            </nav>
        </header>
        <div>
            <div class="sidebar">
                <p> Búsqueda por metadatos </p>
                <ul>
                    <li>
                        <a href="#" class="tit-btn">Titulo
                            <span class="fas fa-caret-down first"></span>
                        </a>
                        <ul class="tit-show">
                            <form method="post" action="busquedaMetadatos.jsp"><center><input type="text" name="text-tit" placeholder="Ingrese un titulo"><br><input value="Aplicar" type="submit" id="btn-bmm-tit"></center></form>
                        </ul>
                    </li>
                    <li>
                        <a href="#" class="aut-btn">Autor(es)
                            <span class="fas fa-caret-down second"></span>
                        </a>
                        <ul class="aut-show">
                            <form method="post" action="busquedaMetadatos.jsp"><center><input type="text" name="text-aut" placeholder="Ingrese un titulo"><br><input value="Aplicar" type="submit" id="btn-bmm-aut"></center></form>
                        </ul>
                    </li>
                    <li>
                        <a href="#" class="ins-btn">Institución
                            <span class="fas fa-caret-down tres"></span>
                        </a>
                        <ul class="ins-show">
                            <form method="post" action="busquedaMetadatos.jsp"><center><input type="text" name="text-ins" placeholder="Ingrese un titulo"><br><input value="Aplicar" type="submit" id="btn-bmm-ins"></center></form>
                        </ul>
                    </li>
                    <li>
                        <a href="#" class="pc-btn">Palabra clave
                            <span class="fas fa-caret-down cuatro"></span>
                        </a>
                        <ul class="pc-show">
                            <form method="post" action="busquedaMetadatos.jsp"><center><input type="text" name="text-pc" placeholder="Ingrese un titulo"><br><input value="Aplicar" type="submit" id="btn-bmm-pc"></center></form>
                        </ul>
                    </li>
                    <li>
                        <a href="#" class="dir-btn">Director(es)
                            <span class="fas fa-caret-down cinco"></span>
                        </a>
                        <ul class="dir-show">
                            <form method="post" action="busquedaMetadatos.jsp"><center><input type="text" name="text-dir" placeholder="Ingrese un titulo"><br><input value="Aplicar" type="submit" id="btn-bmm-dir"></center></form>
                        </ul>
                    </li>
                    <li>
                        <a href="#" class="sin-btn">Sinodal(es)
                            <span class="fas fa-caret-down seis"></span>
                        </a>
                        <ul class="sin-show">
                            <form method="post" action="busquedaMetadatos.jsp"><center><input type="text" name="text-sin" placeholder="Ingrese un titulo"><br><input value="Aplicar" type="submit" id="btn-bmm-sin"></center></form>
                        </ul>
                    </li>
                    <li>
                        <a href="#" class="ano-btn">Año
                            <span class="fas fa-caret-down siete"></span>
                        </a>
                        <ul class="ano-show">
                            <form method="post" action="busquedaMetadatos.jsp"><center><input type="text" name="text-ano" placeholder="Ingrese un titulo"><br><input value="Aplicar" type="submit" id="btn-bmm-ano"></center></form>
                        </ul>
                    </li>

                </ul>
            </div>
        </div>
        <main>
            <table align="right" id="bmm-table">
                <%
                    List<TrabajoTerminalDTO> ttd = new ArrayList<>();
                    TrabajoTerminalDAO t = new TrabajoTerminalDAO();
                    String ti = "" + request.getParameter("text-tit");
                    String aut = request.getParameter("text-aut");
                    String ins = request.getParameter("text-ins");
                    String pc = request.getParameter("text-pc");
                    String dir = request.getParameter("text-dir");
                    String sin = request.getParameter("text-sin");
                    String ano = request.getParameter("text-ano");
                    //out.println("<script>alert('"+ti+"');</script>");                
                    try {
                        ttd = t.buscaTrabajoTerminalPorTitulo(request.getParameter("text-tit").toString());
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
                    } catch (NullPointerException e) {
                        System.out.println("NullPointerException thrown!");
                    }
                    try {
                        ttd = t.buscaTrabajoTerminalPorAutores(request.getParameter("text-aut").toString());
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
                    } catch (NullPointerException e) {
                        System.out.println("NullPointerException thrown!");
                    }
                    try {
                        ttd = t.buscaTrabajoTerminalPorInstitucion(request.getParameter("text-ins").toString());
                        if (ttd == null || ttd.isEmpty()) {
                            out.println("<tr><td>No hay ningun elemento que coincida con la busqueda</td></tr>");
                        } else {
                            for (int i = 0; i < ttd.size(); i++) {
                                out.println("<tr><td><h1>Titulo: " + ttd.get(i).getTitulo().toString() + "</h1>");
                                out.println("<br><h2>Autor(es):  " + ttd.get(i).getNombresAutores().toString() + " <br>  Sinodales: " + ttd.get(i).getNombresSinodales().toString() + "   <br>  Director(es): " + ttd.get(i).getNombresDirectores().toString() + "</h2><br>");
                                out.println("<h2>Unidades Académicas: " + ttd.get(i).getInstitucion().toString() + "     Fecha de realización: " + ttd.get(i).getFechaRealizacion().toString() + "       </h2> <br>");
                                //out.println("<h2> <a href='"+ttd.get(i).getRutaTrabajoTerminal().toString()+"' target='_blank'>Documento Completo</a></h2><br>");
                                out.println("<h2> <a href='https://archivo.ucr.ac.cr/docum/tesis2.pdf' target='_blank'>Documento Completo</a></h2><br>");
                                out.println("<h2>Abstract</h2><br>");
                                out.println("" + ttd.get(i).getAbst().toString() + "<br>");
                                out.println("'</td></tr>");
                            }
                        }
                    } catch (NullPointerException e) {
                        System.out.println("NullPointerException thrown!");
                    }
                    try {
                        ttd = t.buscaTrabajoTerminalPorPalabrasClave(request.getParameter("text-pc").toString());
                        if (ttd == null || ttd.isEmpty()) {
                            out.println("<tr><td>No hay ningun elemento que coincida con la busqueda</td></tr>");
                        } else {
                            for (int i = 0; i < ttd.size(); i++) {
                                out.println("<tr><td><h1>Titulo: " + ttd.get(i).getTitulo().toString() + "</h1>");
                                out.println("<br><h2>Autor(es):  " + ttd.get(i).getNombresAutores().toString() + " <br>  Sinodales: " + ttd.get(i).getNombresSinodales().toString() + "   <br>  Director(es): " + ttd.get(i).getNombresDirectores().toString() + "</h2><br>");
                                out.println("<h2>Unidades Académicas: " + ttd.get(i).getInstitucion().toString() + "     Fecha de realización: " + ttd.get(i).getFechaRealizacion().toString() + "       </h2> <br>");
                                //out.println("<h2> <a href='"+ttd.get(i).getRutaTrabajoTerminal().toString()+"' target='_blank'>Documento Completo</a></h2><br>");
                                out.println("<h2> <a href='https://archivo.ucr.ac.cr/docum/tesis2.pdf' target='_blank'>Documento Completo</a></h2><br>");
                                out.println("<h2>Abstract</h2><br>");
                                out.println("" + ttd.get(i).getAbst().toString() + "<br>");
                                out.println("'</td></tr>");
                            }
                        }
                    } catch (NullPointerException e) {
                        System.out.println("NullPointerException thrown!");
                    }
                    try {
                        ttd = t.buscaTrabajoTerminalPorDirectores(request.getParameter("text-dir").toString());
                        if (ttd == null || ttd.isEmpty()) {
                            out.println("<tr><td>No hay ningun elemento que coincida con la busqueda</td></tr>");
                        } else {
                            for (int i = 0; i < ttd.size(); i++) {
                                out.println("<tr><td><h1>Titulo: " + ttd.get(i).getTitulo().toString() + "</h1>");
                                out.println("<br><h2>Autor(es):  " + ttd.get(i).getNombresAutores().toString() + " <br>  Sinodales: " + ttd.get(i).getNombresSinodales().toString() + "   <br>  Director(es): " + ttd.get(i).getNombresDirectores().toString() + "</h2><br>");
                                out.println("<h2>Unidades Académicas: " + ttd.get(i).getInstitucion().toString() + "     Fecha de realización: " + ttd.get(i).getFechaRealizacion().toString() + "       </h2> <br>");
                                //out.println("<h2> <a href='"+ttd.get(i).getRutaTrabajoTerminal().toString()+"' target='_blank'>Documento Completo</a></h2><br>");
                                out.println("<h2> <a href='https://archivo.ucr.ac.cr/docum/tesis2.pdf' target='_blank'>Documento Completo</a></h2><br>");
                                out.println("<h2>Abstract</h2><br>");
                                out.println("" + ttd.get(i).getAbst().toString() + "<br>");
                                out.println("'</td></tr>");
                            }
                        }
                    } catch (NullPointerException e) {
                        System.out.println("NullPointerException thrown!");
                    }
                    try {
                        ttd = t.buscaTrabajoTerminalPorSinodales(request.getParameter("text-sin").toString());
                        if (ttd == null || ttd.isEmpty()) {
                            out.println("<tr><td>No hay ningun elemento que coincida con la busqueda</td></tr>");
                        } else {
                            for (int i = 0; i < ttd.size(); i++) {
                                out.println("<tr><td><h1>Titulo: " + ttd.get(i).getTitulo().toString() + "</h1>");
                                out.println("<br><h2>Autor(es):  " + ttd.get(i).getNombresAutores().toString() + " <br>  Sinodales: " + ttd.get(i).getNombresSinodales().toString() + "   <br>  Director(es): " + ttd.get(i).getNombresDirectores().toString() + "</h2><br>");
                                out.println("<h2>Unidades Académicas: " + ttd.get(i).getInstitucion().toString() + "     Fecha de realización: " + ttd.get(i).getFechaRealizacion().toString() + "       </h2> <br>");
                                //out.println("<h2> <a href='"+ttd.get(i).getRutaTrabajoTerminal().toString()+"' target='_blank'>Documento Completo</a></h2><br>");
                                out.println("<h2> <a href='https://archivo.ucr.ac.cr/docum/tesis2.pdf' target='_blank'>Documento Completo</a></h2><br>");
                                out.println("<h2>Abstract</h2><br>");
                                out.println("" + ttd.get(i).getAbst().toString() + "<br>");
                                out.println("'</td></tr>");
                            }
                        }
                    } catch (NullPointerException e) {
                        System.out.println("NullPointerException thrown!");
                    }
                    try {
                        ttd = t.buscaTrabajoTerminalPorFechaRealizacion(request.getParameter("text-ano").toString());
                        if (ttd == null || ttd.isEmpty()) {
                            out.println("<tr><td>No hay ningun elemento que coincida con la busqueda</td></tr>");
                        } else {
                            for (int i = 0; i < ttd.size(); i++) {
                                out.println("<tr><td><h1>Titulo: " + ttd.get(i).getTitulo().toString() + "</h1>");
                                out.println("<br><h2>Autor(es):  " + ttd.get(i).getNombresAutores().toString() + " <br>  Sinodales: " + ttd.get(i).getNombresSinodales().toString() + "   <br>  Director(es): " + ttd.get(i).getNombresDirectores().toString() + "</h2><br>");
                                out.println("<h2>Unidades Académicas: " + ttd.get(i).getInstitucion().toString() + "     Fecha de realización: " + ttd.get(i).getFechaRealizacion().toString() + "       </h2> <br>");
                                //out.println("<h2> <a href='"+ttd.get(i).getRutaTrabajoTerminal().toString()+"' target='_blank'>Documento Completo</a></h2><br>");
                                out.println("<h2> <a href='https://archivo.ucr.ac.cr/docum/tesis2.pdf' target='_blank'>Documento Completo</a></h2><br>");
                                out.println("<h2>Abstract</h2><br>");
                                out.println("" + ttd.get(i).getAbst().toString() + "<br>");
                                out.println("'</td></tr>");
                            }
                        }
                    } catch (NullPointerException e) {
                        System.out.println("NullPointerException thrown!");
                    }
                %>
            </table>
        </main>
        <script>
            $('.tit-btn').click(function () {
                $('div ul .tit-show').toggleClass("show");
                $('div ul .first').toggleClass("rotate");
            });
            $('.aut-btn').click(function () {
                $('div ul .aut-show').toggleClass("show1");
                $('div ul .second').toggleClass("rotate");
            });
            $('.ins-btn').click(function () {
                $('div ul .ins-show').toggleClass("show2");
                $('div ul .tres').toggleClass("rotate");
            });
            $('.pc-btn').click(function () {
                $('div ul .pc-show').toggleClass("show3");
                $('div ul .cuatro').toggleClass("rotate");
            });
            $('.dir-btn').click(function () {
                $('div ul .dir-show').toggleClass("show4");
                $('div ul .cinco').toggleClass("rotate");
            });
            $('.sin-btn').click(function () {
                $('div ul .sin-show').toggleClass("show5");
                $('div ul .seis').toggleClass("rotate");
            });
            $('.ano-btn').click(function () {
                $('div ul .ano-show').toggleClass("show6");
                $('div ul .siete').toggleClass("rotate");
            });
            $('.abst-btn').click(function () {
                $('div table .abst-show').toggleClass("show7");
                $('div table .ocho').toggleClass("rotate");
            });
            $('div ul li').click(function () {
                $(this).addClass("active").siblings().removeClass("active");
            });
        </script>
    </body>
</html>