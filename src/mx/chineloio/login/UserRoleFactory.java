/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.chineloio.login;

import iam.entidades.EntidadConsultaPersonalizada;

/**
 *
 * @author PCio
 */
public class UserRoleFactory extends EntidadConsultaPersonalizada {

    private String idUser;
    private String nombreUser;
    private String emailUser;
    private String idRole;
    private String nombreRole;
    private String factoryId;
    private String nombreFactory;

    @Override
    public EntidadConsultaPersonalizada getNuevaInstanciaEntidad() {
        return new UserRoleFactory();
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNombreUser() {
        return nombreUser;
    }

    public void setNombreUser(String nombreUser) {
        this.nombreUser = nombreUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getIdRole() {
        return idRole;
    }

    public void setIdRole(String idRole) {
        this.idRole = idRole;
    }

    public String getNombreRole() {
        return nombreRole;
    }

    public void setNombreRole(String nombreRole) {
        this.nombreRole = nombreRole;
    }

    public String getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(String factoryId) {
        this.factoryId = factoryId;
    }

    public String getNombreFactory() {
        return nombreFactory;
    }

    public void setNombreFactory(String nombreFactory) {
        this.nombreFactory = nombreFactory;
    }

}
