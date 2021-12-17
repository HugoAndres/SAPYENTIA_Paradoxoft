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
import com.paradoxoft.mx.dao.PersonalBibliotecarioDAO;
import com.paradoxoft.mx.modelo.Alumno;
import com.paradoxoft.mx.modelo.Docente;
import com.paradoxoft.mx.modelo.PersonalAcademico;
import com.paradoxoft.mx.modelo.PersonalAdministrativo;
import com.paradoxoft.mx.modelo.PersonalBibliotecario;
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
@WebServlet(name = "AltaUsuario", urlPatterns = {"/AltaUsuario"})
public class AltaUsuario extends HttpServlet {

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
            String nombre = request.getParameter("campoNombres");
            String apellidos = request.getParameter("campoApellidos");
            String institucion = request.getParameter("campoInstitucion");
            String unidadAcademica = request.getParameter("campoUnidadAcademica");
            String email = request.getParameter("email");
            String contrasenia = request.getParameter("campoContrasenia");
            String tipoUsuario = request.getParameter("comboCategoria");
            
            if(nombre != null && apellidos != null && institucion != null && unidadAcademica != null &&
                email != null && contrasenia != null && tipoUsuario != null){
                try{
                    Usuario nuevoUsuario =  new Usuario();
                    nuevoUsuario.setNombres(nombre);
                    nuevoUsuario.setApellidos(apellidos);
                    nuevoUsuario.setInstitucion(institucion);
                    nuevoUsuario.setUnidadAcademica(unidadAcademica);
                    nuevoUsuario.setCorreo(email);
                    nuevoUsuario.setContra(contrasenia);
                    switch(tipoUsuario){
                        case "alumno":
                            String carrera = request.getParameter("campoCarrera");
                            if(carrera != null){
                                AlumnoDAO daoAlumno = new AlumnoDAO();
                                Alumno nuevoAlumno = (Alumno) nuevoUsuario;
                                nuevoAlumno.setCarrera(carrera);
                                daoAlumno.guardaAlumno(nuevoAlumno);
                            }
                            else
                                mostrarError(request, response);
                            break;
                        case "docente":
                            int academia = Integer.parseInt(request.getParameter("campoAcademia"));
                            String grado = request.getParameter("campoGrado");
                            if(grado != null){
                                DocenteDAO daoDocente = new DocenteDAO();
                                Docente nuevoDocente = (Docente) nuevoUsuario;
                                nuevoDocente.setAcademiaDocente(academia);
                                nuevoDocente.setGrado(grado);
                                daoDocente.guardaDocente(nuevoDocente);
                            }
                            else
                                mostrarError(request, response);
                            break;
                        case "personalAdministrativo":
                            PersonalAdministrativoDAO daoPersonalAdministrativo = new PersonalAdministrativoDAO();
                            daoPersonalAdministrativo.guardaPersonalAdministrativo((PersonalAdministrativo) nuevoUsuario);
                            break;
                        case "personalAcademico":
                            PersonalAcademicoDAO daoPersonalAcademico = new PersonalAcademicoDAO();
                            daoPersonalAcademico.guardaPersonalAcademico((PersonalAcademico) nuevoUsuario);
                            break;
                        case "personalBibliotecario":
                            PersonalBibliotecarioDAO daoPersonalBibliotecario = new PersonalBibliotecarioDAO();
                            daoPersonalBibliotecario.guardaPersonalBibliotecario((PersonalBibliotecario) nuevoUsuario);
                            break;
                        default:
                            mostrarError(request, response);
                            break;
                    }
                } catch (SQLException | NumberFormatException ex) {
                    Logger.getLogger(AltaUsuario.class.getName()).log(Level.SEVERE, null, ex);
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
        RequestDispatcher rd = request.getRequestDispatcher("usuarios/registro.jsp?error=true");
        rd.forward(request, response);
    }
}
