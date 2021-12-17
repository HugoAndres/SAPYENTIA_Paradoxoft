/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paradoxoft.mx.modelo;

/**
 *
 * @author user
 */
public class PersonalAdministrativo extends Usuario{
    
    public PersonalAdministrativo(){
        super();
    }
    
    public PersonalAdministrativo(Usuario usuario){
        super(usuario.getIdentificador(), usuario.getNombres(), usuario.getApellidos(), usuario.getInstitucion(), usuario.getUnidadAcademica(), usuario.getCorreo(), usuario.getContra(), usuario.getImagen());
    }
    
    @Override
    public String toString() {
        return super.toString();
    }
}
