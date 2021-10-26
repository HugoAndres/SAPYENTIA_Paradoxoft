/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paradoxoft.controlador;

import com.paradoxoft.mx.dao.TrabajoTerminalDAO;
import com.paradoxoft.mx.dto.TrabajoTerminalDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gmelo
 */
@WebServlet(name = "AltaTrabajoTerminalPrevio", urlPatterns = {"/AltaTrabajoTerminalPrevio"})
public class AltaTrabajoTerminalPrevio extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getCharacterEncoding() == null)
            request.setCharacterEncoding("UTF-8");
        
        String miTitulo = request.getParameter("titulo");
        String miNumeroIdentificador = request.getParameter("numId");
        String miAutor1 = request.getParameter("autor1");
        String miDirector = request.getParameter("director");
        String miCodirector1 = request.getParameter("codirector1");
        String miSinodal1 = request.getParameter("sinodal1");
        String miInstitucion = request.getParameter("institucion");
        String miUnidadAcademica = request.getParameter("unidadAcademica");
        String miFechaRealizacion = request.getParameter("fechaRealizacion");
        String miPalabraClave1 = request.getParameter("palabraClave1");
        String miRutaArchivoPropuesta = "C:\\\\users\\desktop\\miPropuesta.pdf";
        String miRutaArchivoTT = "C:\\\\users\\desktop\\miTT.pdf";
        
        if(miTitulo != null && miNumeroIdentificador != null && miAutor1 != null
                && miDirector != null && miSinodal1 != null && miInstitucion != null
                && miUnidadAcademica != null && miFechaRealizacion != null
                && miPalabraClave1 != null && miRutaArchivoPropuesta != null
                && miRutaArchivoTT != null){
            
            String misAutores = miAutor1;
            int i = 2;
            String miSiguienteAutor = request.getParameter("autor" + i);
            while(miSiguienteAutor != null){
                misAutores += ", ";
                misAutores += miSiguienteAutor;
                ++i;
                miSiguienteAutor = request.getParameter("autor" + i);
            }
            
            String misDirectores = miDirector;
            i = 1;
            String miSiguienteCodirector = request.getParameter("codirector" + i);
            while(miSiguienteCodirector != null){
                misDirectores += ", ";
                misDirectores += miSiguienteCodirector;
                ++i;
                miSiguienteCodirector = request.getParameter("codirector" + i);
            }
            
            String misSinodales = miSinodal1;
            i = 2;
            String miSiguienteSinodal = request.getParameter("sinodal" + i);
            while(miSiguienteSinodal != null){
                misSinodales += ", ";
                misSinodales += miSiguienteSinodal;
                ++i;
                miSiguienteSinodal = request.getParameter("sinodal" + i);
            }
            
            String misPalabrasClave = miPalabraClave1;
            i = 2;
            String miSiguientePalabraClave = request.getParameter("palabraClave" + i);
            while(miSiguientePalabraClave != null){
                misPalabrasClave += ", ";
                misPalabrasClave += miSiguientePalabraClave;
                ++i;
                miSiguientePalabraClave = request.getParameter("palabraClave" + i);
            }
            
            TrabajoTerminalDTO dto = new TrabajoTerminalDTO(miNumeroIdentificador, miTitulo,
                    miFechaRealizacion, miInstitucion, miUnidadAcademica, misPalabrasClave, null,
                    miRutaArchivoPropuesta, miRutaArchivoTT, misAutores, misDirectores, misSinodales);
            
            TrabajoTerminalDAO dao = new TrabajoTerminalDAO();
            try {
                dao.guardaTrabajoTerminalHistorico(dto);
                RequestDispatcher rd = request.getRequestDispatcher("recursos/jsp/busquedaMetadatos.jsp");
                rd.forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(AltaTrabajoTerminalPrevio.class.getName()).log(Level.SEVERE, null, ex);
                RequestDispatcher rd = request.getRequestDispatcher("personalBiblioteca/registrarTTPrevio.html");
                rd.forward(request, response);
            }
        }
        else{
            RequestDispatcher rd = request.getRequestDispatcher("personalBiblioteca/registrarTTPrevio.html");
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
