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
<html lang="en" dir="ltr">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Búsqueda metadatos</title>
        <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
        <link rel="shortcut icon" href="../ico/monogramaSAPYENTIA.ico" type="image/x-icon" />
        <link rel="stylesheet" href="../css/estiloBusquedaMetadatosActual.css" />
    </head>
    <body>   
        <header>
            <nav>
                <a href="../../index.html"><img src="../../recursos/svg/logoSAPYENTIA.svg" alt="Ir a página de inicio" title="Ir a página de inicio" id="logoSAPYENTIA" /></a>
            </nav>
        </header>
        <div id="cuerpo">
            <aside>
                <h1>Búsqueda por metadatos</h1>
                <ul>
                    <li>
                        <button class="tit-btn">
                            Titulo
                            <img src="../svg/flechaHaciaAbajo.svg" alt="flecha hacia abajo" class="first"/>
                        </button>
                        <div class="tit-show">
                            <form method="post" action="busquedaMetadatos.jsp">
                                <input type="text" name="text-tit" placeholder="Ingrese un titulo">
                                <input value="Aplicar" type="submit" id="btn-bmm-tit">
                            </form>
                        </div>
                    </li>
                    <li>
                        <button class="aut-btn">Autor(es)
                            <img src="../svg/flechaHaciaAbajo.svg" alt="flecha hacia abajo" class="second" />
                        </button>
                        <div class="aut-show">
                            <form method="post" action="busquedaMetadatos.jsp">
                                <input type="text" name="text-aut" placeholder="Ingrese los autores">
                                <input value="Aplicar" type="submit" id="btn-bmm-aut">
                            </form>
                        </div>
                    </li>
                    <li>
                        <button class="ins-btn">Institución
                            <img src="../svg/flechaHaciaAbajo.svg" alt="flecha hacia abajo" class="tres" />
                        </button>
                        <div class="ins-show">
                            <form method="post" action="busquedaMetadatos.jsp">
                                <input type="text" name="text-ins" placeholder="Ingrese la institución">
                                <input value="Aplicar" type="submit" id="btn-bmm-ins">
                            </form>
                        </div>
                    </li>
                    <li>
                        <button class="pc-btn">Palabra clave
                            <img src="../svg/flechaHaciaAbajo.svg" alt="flecha hacia abajo" class="cuatro" />
                        </button>
                        <div class="pc-show">
                            <form method="post" action="busquedaMetadatos.jsp">
                                <input type="text" name="text-pc" placeholder="Ingrese las palabras clave">
                                <input value="Aplicar" type="submit" id="btn-bmm-pc">
                            </form>
                        </div>
                    </li>
                    <li>
                        <button class="dir-btn">Director(es)
                            <img src="../svg/flechaHaciaAbajo.svg" alt="flecha hacia abajo" class="cinco" />
                        </button>
                        <div class="dir-show">
                            <form method="post" action="busquedaMetadatos.jsp">
                                <input type="text" name="text-dir" placeholder="Ingrese algún director">
                                <input value="Aplicar" type="submit" id="btn-bmm-dir">
                            </form>
                        </div>
                    </li>
                    <li>
                        <button class="sin-btn">Sinodal(es)
                            <img src="../svg/flechaHaciaAbajo.svg" alt="flecha hacia abajo" class="seis" />
                        </button>
                        <div class="sin-show">
                            <form method="post" action="busquedaMetadatos.jsp">
                                <input type="text" name="text-sin" placeholder="Ingrese algún sinodal">
                                <input value="Aplicar" type="submit" id="btn-bmm-sin">
                            </form>
                        </div>
                    </li>
                    <li>
                        <button class="ano-btn">Año
                            <img src="../svg/flechaHaciaAbajo.svg" alt="flecha hacia abajo" class="siete" />
                        </button>
                        <div class="ano-show">
                            <form method="post" action="busquedaMetadatos.jsp">
                                <input type="text" name="text-ano" placeholder="Ingrese el año de publicación">
                                <input value="Aplicar" type="submit" id="btn-bmm-ano">
                            </form>
                        </div>
                    </li>
                </ul>
            </aside>
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
                                    String cad= ttd.get(i).getNombresAutores().toString()+" \" "+ttd.get(i).getTitulo().toString()+"\""+" M.S. thesis, "+ttd.get(i).getInstitucion().toString()+" Ciudad de México, "+ ttd.get(i).getFechaRealizacion().toString();
                                    out.println("<tr><td><h1>Titulo: " + ttd.get(i).getTitulo().toString() + "</h1>");
                                    out.println("<br><h2>Autor(es):  " + ttd.get(i).getNombresAutores().toString() + "  <br> Sinodales: " + ttd.get(i).getNombresSinodales().toString() + "   <br>  Director(es): " + ttd.get(i).getNombresDirectores().toString() + "</h2>");
                                    out.println("<h2>Unidades Académicas: " + ttd.get(i).getInstitucion().toString() + "     Fecha de realización: " + ttd.get(i).getFechaRealizacion().toString() + "       </h2>");
                                    //out.println("<h2> <a href='"+ttd.get(i).getRutaTrabajoTerminal().toString()+"' target='_blank'>Documento Completo</a></h2><br>");
                                    out.println("<form method='post' action='../../alumnos/consultaDeTesisJSP.jsp'><input type='hidden' value='"+ttd.get(i).getNumIdentificador()+"' id='numI' name='numI'>");
                                    out.println("<h2> <input type='submit' class='sub' value='Ver'></form>");
                                    //out.println("<h2><p id='p1' >"+cad+"</p> <button onclick='copiarAlPortapapeles(p1)'>Citar</button>");
                                    out.println("<h2>Abstract:</h2>");
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
                                    out.println("<br><h2>Autor(es):  " + ttd.get(i).getNombresAutores().toString() + "  <br> Sinodales: " + ttd.get(i).getNombresSinodales().toString() + "   <br>  Director(es): " + ttd.get(i).getNombresDirectores().toString() + "</h2>");
                                    out.println("<h2>Unidades Académicas: " + ttd.get(i).getInstitucion().toString() + "     Fecha de realización: " + ttd.get(i).getFechaRealizacion().toString() + "       </h2> ");
                                    //out.println("<h2> <a href='"+ttd.get(i).getRutaTrabajoTerminal().toString()+"' target='_blank'>Documento Completo</a></h2><br>");
                                    out.println("<form method='post' action='../../alumnos/consultaDeTesisJSP.jsp'><input type='hidden' value='"+ttd.get(i).getNumIdentificador()+"' id='numI' name='numI'>");
                                    out.println("<h2> <input type='submit' class='sub' value='Ver'></form><br>");
                                    out.println("<h2>Abstract:</h2>");
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
                                    out.println("<br><h2>Autor(es):  " + ttd.get(i).getNombresAutores().toString() + " <br>  Sinodales: " + ttd.get(i).getNombresSinodales().toString() + "   <br>  Director(es): " + ttd.get(i).getNombresDirectores().toString() + "</h2>");
                                    out.println("<h2>Unidades Académicas: " + ttd.get(i).getInstitucion().toString() + "     Fecha de realización: " + ttd.get(i).getFechaRealizacion().toString() + "       </h2> ");
                                    //out.println("<h2> <a href='"+ttd.get(i).getRutaTrabajoTerminal().toString()+"' target='_blank'>Documento Completo</a></h2><br>");
                                    out.println("<form method='post' action='../../alumnos/consultaDeTesisJSP.jsp'><input type='hidden' value='"+ttd.get(i).getNumIdentificador()+"' id='numI' name='numI'>");
                                    out.println("<h2> <input type='submit' class='sub' value='Ver'></form><br>");
                                    out.println("<h2>Abstract:</h2>");
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
                                    out.println("<br><h2>Autor(es):  " + ttd.get(i).getNombresAutores().toString() + " <br>  Sinodales: " + ttd.get(i).getNombresSinodales().toString() + "   <br>  Director(es): " + ttd.get(i).getNombresDirectores().toString() + "</h2>");
                                    out.println("<h2>Unidades Académicas: " + ttd.get(i).getInstitucion().toString() + "     Fecha de realización: " + ttd.get(i).getFechaRealizacion().toString() + "       </h2> ");
                                    //out.println("<h2> <a href='"+ttd.get(i).getRutaTrabajoTerminal().toString()+"' target='_blank'>Documento Completo</a></h2><br>");
                                    out.println("<form method='post' action='../../alumnos/consultaDeTesisJSP.jsp'><input type='hidden' value='"+ttd.get(i).getNumIdentificador()+"' id='numI' name='numI'>");
                                    out.println("<h2> <input type='submit' class='sub' value='Ver'></form><br>");
                                    out.println("<h2>Abstract:</h2>");
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
                                    out.println("<br><h2>Autor(es):  " + ttd.get(i).getNombresAutores().toString() + " <br>  Sinodales: " + ttd.get(i).getNombresSinodales().toString() + "   <br>  Director(es): " + ttd.get(i).getNombresDirectores().toString() + "</h2>");
                                    out.println("<h2>Unidades Académicas: " + ttd.get(i).getInstitucion().toString() + "     Fecha de realización: " + ttd.get(i).getFechaRealizacion().toString() + "       </h2>");
                                    //out.println("<h2> <a href='"+ttd.get(i).getRutaTrabajoTerminal().toString()+"' target='_blank'>Documento Completo</a></h2><br>");
                                    out.println("<form method='post' action='../../alumnos/consultaDeTesisJSP.jsp'><input type='hidden' value='"+ttd.get(i).getNumIdentificador()+"' id='numI' name='numI'>");
                                    out.println("<h2> <input type='submit' class='sub' value='Ver'></form><br>");
                                    out.println("<h2>Abstract:</h2>");
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
                                    out.println("<br><h2>Autor(es):  " + ttd.get(i).getNombresAutores().toString() + " <br>  Sinodales: " + ttd.get(i).getNombresSinodales().toString() + "   <br>  Director(es): " + ttd.get(i).getNombresDirectores().toString() + "</h2>");
                                    out.println("<h2>Unidades Académicas: " + ttd.get(i).getInstitucion().toString() + "     Fecha de realización: " + ttd.get(i).getFechaRealizacion().toString() + "       </h2> ");
                                    //out.println("<h2> <a href='"+ttd.get(i).getRutaTrabajoTerminal().toString()+"' target='_blank'>Documento Completo</a></h2><br>");
                                    out.println("<form method='post' action='../../alumnos/consultaDeTesisJSP.jsp'><input type='hidden' value='"+ttd.get(i).getNumIdentificador()+"' id='numI' name='numI'>");
                                    out.println("<h2> <input type='submit' class='sub' value='Ver'></form><br>");
                                    out.println("<h2>Abstract:</h2>");
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
                                    out.println("<br><h2>Autor(es):  " + ttd.get(i).getNombresAutores().toString() + " <br>  Sinodales: " + ttd.get(i).getNombresSinodales().toString() + "   <br>  Director(es): " + ttd.get(i).getNombresDirectores().toString() + "</h2>");
                                    out.println("<h2>Unidades Académicas: " + ttd.get(i).getInstitucion().toString() + "     Fecha de realización: " + ttd.get(i).getFechaRealizacion().toString() + "       </h2> ");
                                    //out.println("<h2> <a href='"+ttd.get(i).getRutaTrabajoTerminal().toString()+"' target='_blank'>Documento Completo</a></h2><br>");
                                    out.println("<form method='post' action='../../alumnos/consultaDeTesisJSP.jsp'><input type='hidden' value='"+ttd.get(i).getNumIdentificador()+"' id='numI' name='numI'>");
                                    out.println("<h2> <input type='submit' class='sub' value='Ver'></form><br>");
                                    out.println("<h2>Abstract:</h2>");
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
        </div>
        <footer>
            <p>© 2021 Paradoxoft.</p>
        </footer>
        <script>
            $('.tit-btn').click(function () {
                $('div ul .tit-show').toggleClass("show1");
                $('ul button .first').toggleClass("rotate");
            });
            $('.aut-btn').click(function () {
                $('div ul .aut-show').toggleClass("show2");
                $('ul button .second').toggleClass("rotate");
            });
            $('.ins-btn').click(function () {
                $('div ul .ins-show').toggleClass("show3");
                $('ul button .tres').toggleClass("rotate");
            });
            $('.pc-btn').click(function () {
                $('div ul .pc-show').toggleClass("show4");
                $('ul button .cuatro').toggleClass("rotate");
            });
            $('.dir-btn').click(function () {
                $('div ul .dir-show').toggleClass("show5");
                $('ul button .cinco').toggleClass("rotate");
            });
            $('.sin-btn').click(function () {
                $('div ul .sin-show').toggleClass("show6");
                $('ul button .seis').toggleClass("rotate");
            });
            $('.ano-btn').click(function () {
                $('div ul .ano-show').toggleClass("show7");
                $('ul button .siete').toggleClass("rotate");
            });
            /*$('div ul li').click(function () {
                $(this).addClass("active").siblings().removeClass("active");
            });*/
        function copiarAlPortapapeles(id_elemento) {
          var aux = document.createElement("input");
          aux.setAttribute("value", document.getElementById(id_elemento).innerHTML);
          document.body.appendChild(aux);
          aux.select();
          document.execCommand("copy");
          document.body.removeChild(aux);
        }
        </script>
    </body>
</html>