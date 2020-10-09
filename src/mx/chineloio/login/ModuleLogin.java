/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.chineloio.login;

import iam.entidades.administracion.AdministradorEntidadBaseDatos;
import iam.entidades.administracion.AdministradorEntidadBaseDatosException;
import iam.entidades.modulos.ModeloModuloEntidades;
import iam.entidades.modulos.ModeloModuloEntidadesException;
import iam.entidades.modulos.ParametrosProcedimiento;
import iam.entidades.modulos.Procedimiento;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.sql.DataSource;

/**
 *
 * @author PCio
 */
public class ModuleLogin extends ModeloModuloEntidades {

    public ModuleLogin(boolean modoDebug) {
        super(modoDebug);
    }

    public static class Procedimientos {

        //<editor-fold defaultstate="collapsed" desc="Espacio de Trabajo Marco.">
        public static Procedimiento agregarDocumento = new Procedimiento() {

            @Override
            public Object procesarInstrucciones(Statement stmnt, AdministradorEntidadBaseDatos aebd, ParametrosProcedimiento pp) throws ModeloModuloEntidadesException, AdministradorEntidadBaseDatosException, SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        //</editor-fold>

    }

    public ArrayList<UserRoleFactory> findUserByEmailAndPassword(DataSource ds, User user) throws ModeloModuloEntidadesException {
        if (user == null) {
            throw new ModeloModuloEntidadesException("La información del Usuario no cuenta con una estructura correcta.");
        }
        if (user.getEmail() == null || user.getPassword() == null) {
            throw new ModeloModuloEntidadesException("Es necesario contar con el Email y Contraseña para realizar la busqueda del Usuario.");
        }
        if (user.getEmail().isEmpty() || user.getPassword().isEmpty()) {
            throw new ModeloModuloEntidadesException("Es necesario contar con el Email y Contraseña para realizar la busqueda del Usuario.");
        }
        String consulta = "";

        ArrayList<UserRoleFactory> userRoles = null;
        try {
            UserRoleFactory userSearch = new UserRoleFactory();
            userSearch.setOcultarParametrosEntidad(true);

        } catch (Exception e) {

        }
        return null;

    }

}
