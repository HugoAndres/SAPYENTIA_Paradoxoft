/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paradoxoft.controlador;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.UploadBuilder;
import com.dropbox.core.v2.files.WriteMode;
import com.paradoxoft.mx.dao.TrabajoTerminalDAO;
import com.paradoxoft.mx.dto.TrabajoTerminalDTO;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author gmelo
 */
@MultipartConfig
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
        String miSinodal1 = request.getParameter("sinodal1");
        String miInstitucion = request.getParameter("institucion");
        String miUnidadAcademica = request.getParameter("unidadAcademica");
        String miFechaRealizacion = request.getParameter("fechaRealizacion");
        String miPalabraClave1 = request.getParameter("palabraClave1");
        
//        System.out.println("miTitulo :" +miTitulo );
//            System.out.println("miNumeroIdentificador :" +miNumeroIdentificador);
//            System.out.println("miAutor1 :" +miAutor1);
//            System.out.println("miDirector :" +miDirector);
//            System.out.println("miSinodal1 :" +miSinodal1);
//            System.out.println("miInstitucion :" +miInstitucion);
//            System.out.println("miUnidadAcademica :" +miUnidadAcademica);
//            System.out.println("miFechaRealizacion :" +miFechaRealizacion);
//            System.out.println("miPalabraClave1 :" +miPalabraClave1);
        
        
        if(miTitulo != null && miNumeroIdentificador != null && miAutor1 != null
                && miDirector != null && miSinodal1 != null && miInstitucion != null
                && miUnidadAcademica != null && miFechaRealizacion != null
                && miPalabraClave1 != null){
            
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
                    "C://propuestaejemplo3.pdf", "C://trabajoterminalejemplo3.pdf", misAutores, misDirectores, misSinodales);
            
            TrabajoTerminalDAO dao = new TrabajoTerminalDAO();
            try {
                dao.guardaTrabajoTerminalHistorico(dto);
                
                
                
                String token = "7NtqD0TadqUAAAAAAAAAAdRa4A-2AbNBQmG17els11xFV63byGviFPwEq5ighGeU";
                String rutaCarpeta = "/SAPYENTIA/";
                String identificador = miNumeroIdentificador;
                
                String seccionPropuesta = "Propuestas";
                String tipoPropuesta= "P";
                String rutaFinalPropuesta = rutaCarpeta + seccionPropuesta + "/" + identificador + "/" + tipoPropuesta + identificador;
                
                String seccionTT = "Trabajos_Terminales";
                String tipoTT= "TT";
                String rutaFinalTT = rutaCarpeta + seccionTT + "/" + identificador + "/" + tipoTT + identificador;
                

                DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
                DbxClientV2 client = new DbxClientV2(config, token);
                
                try {
                    System.out.println(client.users().getCurrentAccount().getEmail());

                    //File carpeta = new File(getServletConfig().getServletContext().getRealPath("/misRecursos"));
                    String[] validos = {".pdf"};
                    String rutaPropuesta = subirArchivo(request, response, rutaFinalPropuesta, validos, "rutaArchivoPropuesta", client);
                    
                    if(rutaPropuesta == null){
                        try {
                            request.setAttribute("mensaje", "Error al registrar el trabajo terminal previo. No se pudo subir el archivo de la propuesta de Trabajo Terminal");
                            RequestDispatcher rd = request.getRequestDispatcher("personalBiblioteca/registrarTTPrevio.jsp");
                            rd.forward(request, response);
                        } catch (ServletException | IOException ex){
                            Logger.getLogger(AltaTrabajoTerminalPrevio.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }else{
                        String rutaTT = subirArchivo(request, response, rutaFinalTT, validos, "rutaArchivoTT", client);
                        if(rutaTT == null){
                            try {
                                request.setAttribute("mensaje", "Error al registrar el trabajo terminal previo. No se pudo subir el archivo del Trabajo Terminal");
                                RequestDispatcher rd = request.getRequestDispatcher("personalBiblioteca/registrarTTPrevio.jsp");
                                rd.forward(request, response);
                            } catch (ServletException | IOException ex){
                                Logger.getLogger(AltaTrabajoTerminalPrevio.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }else{
                            try{
                                dao.actualizaRutaPropuesta(miNumeroIdentificador, rutaPropuesta);
                                dao.actualizaRutaTrabajoTerminal(miNumeroIdentificador, rutaTT);

                                request.setAttribute("mensaje", "Trabajo terminal registrado con éxito");
                                RequestDispatcher rd = request.getRequestDispatcher("personalBiblioteca/registrarTTPrevio.jsp");
                                rd.forward(request, response);
                            }catch(SQLException | ServletException | IOException ex){
                                Logger.getLogger(AltaTrabajoTerminalPrevio.class.getName()).log(Level.SEVERE, null, ex);
                                request.setAttribute("mensaje", "Error al registrar el trabajo terminal previo. No se pudieron asignar las rutas de archivos");
                                RequestDispatcher rd = request.getRequestDispatcher("personalBiblioteca/registrarTTPrevio.jsp");
                                rd.forward(request, response);
                            }
                        }
                    }

                } catch (DbxException ex) {
                    Logger.getLogger(AltaTrabajoTerminalPrevio.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (SQLException ex) {
                Logger.getLogger(AltaTrabajoTerminalPrevio.class.getName()).log(Level.SEVERE, null, ex);
                
                try {
                    TrabajoTerminalDTO prueba = new TrabajoTerminalDTO();
                    prueba = dao.buscaTrabajoTerminal(miNumeroIdentificador);
                    if(prueba != null){
                        request.setAttribute("mensaje", "Error al registrar el trabajo terminal previo. Ya existe un trabajo terminal con ese identificador");
                    }else{
                        request.setAttribute("mensaje", "Error al registrar el trabajo terminal previo.");
                    }
                    
                } catch (SQLException ex1) {
                    Logger.getLogger(AltaTrabajoTerminalPrevio.class.getName()).log(Level.SEVERE, null, ex1);
                    request.setAttribute("mensaje", "Error al registrar el trabajo terminal previo.");
                }
                
                RequestDispatcher rd = request.getRequestDispatcher("personalBiblioteca/registrarTTPrevio.jsp");
                rd.forward(request, response);
            }
        }
        else{
            request.setAttribute("mensaje", "Error al registrar el trabajo terminal previo. No debes mandar valores vacíos");
            RequestDispatcher rd = request.getRequestDispatcher("personalBiblioteca/registrarTTPrevio.jsp");
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
    
    private String subirArchivo(HttpServletRequest request, HttpServletResponse response, String rutaFinal, String[] extensiones, String nombreCampo, DbxClientV2 client){
        try {
            Part parte = request.getPart(nombreCampo);

            if(parte == null){
                System.out.println("NO SE PUEDE SUBIR EL ARCHIVO, ES NULO");
            }
            if(parte != null){
                if(extensionValida(parte.getSubmittedFileName(), extensiones)){
                    String ruta = guardaArchivo(parte, rutaFinal, client);
                    return ruta;
                }
            }
            
        } catch (IOException | ServletException ex) {
            Logger.getLogger(AltaTrabajoTerminalPrevio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    private boolean extensionValida(String nombreArchivo, String[] extensiones){
        for(String ext: extensiones){
            if(nombreArchivo.toLowerCase().endsWith(ext)){
                return true;
            }
        }
        return false;
    }
    
    
    private String guardaArchivo(Part part, String rutaFinal, DbxClientV2 client){
        
        String rutaAbsoluta = "";

        try {
            System.out.println("RUTA: " + rutaFinal);
            
            Path ruta = Paths.get(part.getSubmittedFileName());
            //System.out.println(ruta);
            int index = ruta.toString().lastIndexOf('.');
            String ext = "";
            if(index > 0){
                ext = ruta.toString().substring(index+1);
                System.out.println(ext);
            }
            rutaAbsoluta = rutaFinal + "."+ ext;
            
            InputStream input = part.getInputStream();
            if(input != null){
                //SE DEFINE EL OBJETO QUE PERMITE CARGAR EL ARCHIVO PDF EN LA RUTA RAIZ DE DROPBOX.
                UploadBuilder uploadB = client.files().uploadBuilder(rutaAbsoluta);
                uploadB.withClientModified(new Date());
                uploadB.withMode(WriteMode.OVERWRITE);
                uploadB.withAutorename(true);
                
                //FUNCION QUE REALIZA LA ARGA DEL ARCHIVO
                uploadB.uploadAndFinish(input);
                input.close();
                
            }
            
        } catch (IOException ex) {
            Logger.getLogger(AltaTrabajoTerminalPrevio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DbxException ex) {
            Logger.getLogger(AltaTrabajoTerminalPrevio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rutaAbsoluta;
    }
    
}
