/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paradoxoft.controlador;

import com.paradoxoft.mx.dao.AlumnoDAO;
import com.paradoxoft.mx.dao.DocenteDAO;
import com.paradoxoft.mx.dao.PersonalAcademicoDAO;
import com.paradoxoft.mx.dao.PersonalAdministrativoDAO;
import com.paradoxoft.mx.dao.UsuarioDAO;
import com.paradoxoft.mx.dto.DocenteDTO;
import com.paradoxoft.mx.modelo.Alumno;
import com.paradoxoft.mx.modelo.PersonalAcademico;
import com.paradoxoft.mx.modelo.PersonalAdministrativo;
import com.paradoxoft.mx.modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "IniciarSesion", urlPatterns = {"/IniciarSesion"})
public class IniciarSesion extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            if (request.getCharacterEncoding() == null)
                request.setCharacterEncoding("UTF-8");
            
            String email = request.getParameter("emailLogin");
            String contrasenia = request.getParameter("contraseniaLogin");
            
            if(email != null && contrasenia != null){
                try{
                    UsuarioDAO dao = new UsuarioDAO();
                    Usuario usuarioBuscado = dao.buscaUsuarioParaLogin(email, contrasenia);
                    if(usuarioBuscado != null){
                        String identificadorUsuario = usuarioBuscado.getIdentificador();
                        AlumnoDAO daoAlumno = new AlumnoDAO();
                        Alumno alumnoBuscado = daoAlumno.buscaAlumno(identificadorUsuario);
                        if(alumnoBuscado != null){
                            redirigirExito(request, response, "alumno");
                        }
                        else{
                            DocenteDAO daoDocente = new DocenteDAO();
                            DocenteDTO docenteBuscado = daoDocente.buscaDocente(identificadorUsuario);
                            if(docenteBuscado != null){
                                redirigirExito(request, response, "docente");
                            }
                            else{
                                PersonalAdministrativoDAO daoPersonalAdministrativo = new PersonalAdministrativoDAO();
                                PersonalAdministrativo personalAdministrativoBuscado = daoPersonalAdministrativo.buscaPersonalAdministrativo(identificadorUsuario);
                                if(personalAdministrativoBuscado != null){
                                    redirigirExito(request, response, "personalAdministrativo");
                                }
                                else{
                                    PersonalAcademicoDAO daoPersonalAcademico = new PersonalAcademicoDAO();
                                    PersonalAcademico personalAcademicoBuscado = daoPersonalAcademico.buscaPersonalAcademico(identificadorUsuario);
                                    if(personalAcademicoBuscado != null){
                                        redirigirExito(request, response, "personalAcademico");
                                    }
                                    else
                                        redirigirExito(request, response, "personalBibliotecario");
                                }
                            }
                        }
                    }
                    else
                        mostrarError(request, response);
                } catch (SQLException ex){
                    Logger.getLogger(IniciarSesion.class.getName()).log(Level.SEVERE, null, ex);
                    mostrarError(request, response);
                }
            }
            else
                mostrarError(request, response);
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
    private void mostrarError(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.sendRedirect("index.jsp");
    }
    
    private void redirigirExito(HttpServletRequest request, HttpServletResponse response, String tipoUsuario) throws ServletException, IOException{
        switch(tipoUsuario){
            case "alumno":
                response.sendRedirect("alumnos/solicitudProyectoTrabajoTerminal.jsp");
                break;
            case "docente":
                response.sendRedirect("profesor/consultaParticipacionTT.jsp");
                break;
            case "personalAdministrativo":
                response.sendRedirect("personalAdministrativo/verSolicitudesDeProyecto.jsp");
                break;
            case "personalAcademico":
                response.sendRedirect("index.jsp");
                break;
            case "personalBibliotecario":
                response.sendRedirect("personalBiblioteca/registrarTTPrevio.jsp");
                break;
            default:
                mostrarError(request, response);
        }
    }
}
