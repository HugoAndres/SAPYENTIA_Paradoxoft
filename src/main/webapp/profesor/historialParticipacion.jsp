<%-- 
    Document   : historial
    Created on : 15/12/2021, 10:38:08 PM
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
        <title>Busqueda Historial</title>
        <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
        <link rel="shortcut icon" href="../recursos/ico/monogramaSAPYENTIA.ico" type="image/x-icon" />
        <link rel="stylesheet" href="../recursos/css/estiloHistorialParticipacion.css" />
    </head>
    <body>   
        <header>
            <nav>
                <a href="../../index.html"><img src="../recursos/svg/logoSAPYENTIA.svg" alt="Ir a página de inicio" title="Ir a página de inicio" id="logoSAPYENTIA" /></a>
            </nav>
        </header>
        <div id="cuerpo">
            <aside>
                <h1>Búsqueda por metadatos</h1>
                <ul>
                    <li>
                        <button class="tit-btn">
                            Titulo
                            <img src="../recursos/svg/flechaHaciaAbajo.svg" alt="flecha hacia abajo" class="first"/>
                        </button>
                        <div class="tit-show">
                            <form method="post" action="historialParticipacion.jsp">
                                <input type="text" name="text-tit" placeholder="Ingrese un titulo">
                                <input value="Aplicar" type="submit" id="btn-bmm-tit">
                            </form>
                        </div>
                    </li>
                    <li>
                        <button class="aut-btn">Autor(es)
                            <img src="../recursos/svg/flechaHaciaAbajo.svg" alt="flecha hacia abajo" class="second" />
                        </button>
                        <div class="aut-show">
                            <form method="post" action="historialParticipacion.jsp">
                                <input type="text" name="text-aut" placeholder="Ingrese los autores">
                                <input value="Aplicar" type="submit" id="btn-bmm-aut">
                            </form>
                        </div>
                    </li>
                    <li>
                        <button class="ins-btn">Institución
                            <img src="../recursos/svg/flechaHaciaAbajo.svg" alt="flecha hacia abajo" class="tres" />
                        </button>
                        <div class="ins-show">
                            <form method="post" action="historialParticipacion.jsp">
                                <input type="text" name="text-ins" placeholder="Ingrese la institución">
                                <input value="Aplicar" type="submit" id="btn-bmm-ins">
                            </form>
                        </div>
                    </li>
                    <li>
                        <button class="pc-btn">Palabra clave
                            <img src="../recursos/svg/flechaHaciaAbajo.svg" alt="flecha hacia abajo" class="cuatro" />
                        </button>
                        <div class="pc-show">
                            <form method="post" action="historialParticipacion.jsp">
                                <input type="text" name="text-pc" placeholder="Ingrese las palabras clave">
                                <input value="Aplicar" type="submit" id="btn-bmm-pc">
                            </form>
                        </div>
                    </li>
                    <li>
                        <button class="ano-btn">Año
                            <img src="../recursos/svg/flechaHaciaAbajo.svg" alt="flecha hacia abajo" class="siete" />
                        </button>
                        <div class="ano-show">
                            <form method="post" action="historialParticipacion.jsp">
                                <input type="text" name="text-ano" placeholder="Ingrese el año de publicación">
                                <input value="Aplicar" type="submit" id="btn-bmm-ano">
                            </form>
                        </div>
                    </li>
                </ul>
            </aside>
            <main>
                <table align="right" id="bmm-table">
                    <%! 
                        String imprimir(List<TrabajoTerminalDTO> ttd,int i){
                            String cad0="",cad="",cad2="",cad3="",cad4="",cad5="";                           
                            //String cad= ttd.get(i).getNombresAutores().toString()+" \" "+ttd.get(i).getTitulo().toString()+"\""+" M.S. thesis, "+ttd.get(i).getInstitucion().toString()+" Ciudad de México, "+ ttd.get(i).getFechaRealizacion().toString();
                            cad="<tr><td><h1>Titulo: " + ttd.get(i).getTitulo().toString() + "</h1>"+"<br><h2>Autor(es):  " + ttd.get(i).getNombresAutores().toString() + "  <br> Sinodales: " + ttd.get(i).getNombresSinodales().toString() + "   <br>  Director(es): " + ttd.get(i).getNombresDirectores().toString() + "</h2>";
                            cad2="<h2>Unidades Académicas: " + ttd.get(i).getInstitucion().toString() + "     Fecha de realización: " + ttd.get(i).getFechaRealizacion().toString() +  "</h2>";
                            //out.println("<h2> <a href='"+ttd.get(i).getRutaTrabajoTerminal().toString()+"' target='_blank'>Documento Completo</a></h2><br>");
                            cad3="<form method='post' action='../alumnos/consultaDeTesisJSP.jsp'><input type='hidden' value='"+ttd.get(i).getNumIdentificador()+"' id='numI' name='numI'>";
                            cad4="<h2> <input type='submit' class='sub' value='Ver'></form>";
                            //out.println("<h2><p id='p1'>"+cad+"</p> <button onclick='copiarAlPortapapeles(p1)'>Citar</button>");
                            cad5="<h2>Abstract:</h2>"+ ttd.get(i).getAbst().toString() + "<br>"+"'</td></tr>";
                            cad0=cad+cad2+cad3+cad4+cad5;
                            return cad0;
                        }
                        List<TrabajoTerminalDTO> regresaresultaante(List<TrabajoTerminalDTO> ttps,List<TrabajoTerminalDTO> ttpd){
                            List<TrabajoTerminalDTO> res;
                                if (ttps == null || ttpd == null){
                                    if(ttpd == null){
                                        res = new ArrayList<>(ttps);
                                    }else if(ttps == null){
                                       res = new ArrayList<>(ttpd);                                     
                                    }else{
                                        (res = new ArrayList<>(ttpd)).addAll(ttps);
                                    }                                 
                                }else{                                 
                                   (res = new ArrayList<>(ttpd)).addAll(ttps);
                                }                           
                            return res;
                        }
                        String comparador(List<TrabajoTerminalDTO> ttd, List<TrabajoTerminalDTO> res){
                        String cad0="";
                            if (ttd == null && res == null) {
                                cad0="<tr><td>No hay ningun elemento que coincida con la busqueda</td></tr>";
                            } else {
                                int tres=0, tbus=0;
                                tres=res.size();tbus=ttd.size();
                                //out.println("<tr><td>tres = "+tres+"  tbus = "+tbus+"</td></tr>");
                                if(tres>=tbus){
                                     String cad="";
                                     for(int j=0;j<tres;j++){
                                          for(int i=0;i<tbus;i++){
                                             String cad2="";
                                              if(res.get(j).getNumIdentificador().equals(ttd.get(i).getNumIdentificador())){
                                               cad2=imprimir(ttd,i);                                                
                                              }
                                               cad=cad+cad2;
                                          }                                         
                                     }
                                     if(cad.isEmpty()){
                                         cad0="<tr><td>No hay ningun elemento que coincida con la busqueda</td></tr>";
                                    }else{
                                       cad0=""+cad;                                        
                                    }
                                }else if(tbus>tres){
                                     String cad="";
                                     for(int j=0;j<tres;j++){
                                          for(int i=0;i<tbus;i++){
                                             String cad2="";
                                              if(res.get(j).getNumIdentificador().equals(ttd.get(i).getNumIdentificador())){
                                               cad2=imprimir(ttd,i);                                                
                                              }
                                               cad=cad+cad2;
                                          }                                         
                                     }
                                     if(cad.isEmpty()){
                                         cad0="<tr><td>No hay ningun elemento que coincida con la busqueda</td></tr>";
                                    }else{
                                        cad0=""+cad;                                        
                                    }
                                }
                        }
                        return cad0;
                    }
                    %>
                    <%
                        List<TrabajoTerminalDTO> ttd = new ArrayList<>();
                        TrabajoTerminalDAO t = new TrabajoTerminalDAO();                        
                        List<TrabajoTerminalDTO> ttps = new ArrayList<>();
                        TrabajoTerminalDAO tps = new TrabajoTerminalDAO();                        
                        List<TrabajoTerminalDTO> ttpd = new ArrayList<>();
                        TrabajoTerminalDAO tpd = new TrabajoTerminalDAO();   
                        List<TrabajoTerminalDTO> re; 
                        String ti = "" + request.getParameter("text-tit");
                        String aut = request.getParameter("text-aut");
                        String ins = request.getParameter("text-ins");
                        String pc = request.getParameter("text-pc");
                        String ano = request.getParameter("text-ano");
                        //String dir = request.getParameter("text-dir");
                        //String sin = request.getParameter("text-sin");
                        String sin ="señora puff";
                        String dir = "señora puff";
                        //out.println("<script>alert('"+ti+"');</script>");                
                        try {
                            ttd = t.buscaTrabajoTerminalPorTitulo(request.getParameter("text-tit").toString());
                            ttps=tps.buscaTrabajoTerminalPorSinodales(sin);
                            ttpd=tpd.buscaTrabajoTerminalPorDirectores(dir);                                                      
                            re= regresaresultaante(ttps,ttpd);
                            String cad=comparador(re,ttd);
                            out.println(""+cad);
                                                
                        } catch (NullPointerException e) {
                            System.out.println("NullPointerException thrown!");
                        }
                        try {
                            ttd = t.buscaTrabajoTerminalPorAutores(request.getParameter("text-aut").toString());
                            ttps=tps.buscaTrabajoTerminalPorSinodales(sin);
                            ttpd=tpd.buscaTrabajoTerminalPorDirectores(dir);                                                      
                            re= regresaresultaante(ttps,ttpd);
                            String cad=comparador(re,ttd);
                            out.println(""+cad);
                        } catch (NullPointerException e) {
                            System.out.println("NullPointerException thrown!");
                        }
                        try {
                            ttd = t.buscaTrabajoTerminalPorPalabrasClave(request.getParameter("text-pc").toString());
                            ttps=tps.buscaTrabajoTerminalPorSinodales(sin);
                            ttpd=tpd.buscaTrabajoTerminalPorDirectores(dir);                                                      
                            re= regresaresultaante(ttps,ttpd);
                            String cad=comparador(re,ttd);
                            out.println(""+cad);
                        } catch (NullPointerException e) {
                            System.out.println("NullPointerException thrown!");
                        }                        
                        try {
                            ttd = t.buscaTrabajoTerminalPorFechaRealizacion(request.getParameter("text-ano").toString());
                            ttps=tps.buscaTrabajoTerminalPorSinodales(sin);
                            ttpd=tpd.buscaTrabajoTerminalPorDirectores(dir);                                                      
                            re= regresaresultaante(ttps,ttpd);
                            String cad=comparador(re,ttd);
                            out.println(""+cad);
                        } catch (NullPointerException e) {
                            System.out.println("NullPointerException thrown!");
                        }                      
                        try {
                            ttd = t.buscaTrabajoTerminalPorInstitucion(request.getParameter("text-ins").toString());
                            ttps=tps.buscaTrabajoTerminalPorSinodales(sin);
                            ttpd=tpd.buscaTrabajoTerminalPorDirectores(dir);                                                      
                            re= regresaresultaante(ttps,ttpd);
                            String cad=comparador(re,ttd);
                            out.println(""+cad);
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