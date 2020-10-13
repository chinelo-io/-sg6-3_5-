/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.chineloio.login;

import $sg6.pkg3_5.CryptoUtil;
import iam.entidades.administracion.AdministradorEntidadBaseDatos;
import iam.entidades.administracion.AdministradorEntidadBaseDatosException;
import iam.entidades.modulos.ModeloModuloEntidades;
import iam.entidades.modulos.ModeloModuloEntidadesException;
import iam.entidades.modulos.ParametrosProcedimiento;
import iam.entidades.modulos.Procedimiento;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
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
        try {
            user.setPassword(new CryptoUtil().encrypt(user.getPassword()));
        } catch (Exception e) {
            throw new ModeloModuloEntidadesException("Ocurrió un error al cifrar la contraseña.", e);
        }
        String consulta = "SELECT\n"
                + "	u.ID AS id_user,\n"
                + "	u.nombre AS nombre_user,\n"
                + "	r.id AS id_role,\n"
                + "	r.nombre AS nombre_role,\n"
                + "	f.id AS id_factory,\n"
                + "	f.nombre AS nombre_factory\n"
                + "FROM\n"
                + "	main.USER AS u\n"
                + "	JOIN main.user_has_role AS uhr ON uhr.user_id = u.\n"
                + "	ID JOIN main.ROLE AS r ON r.ID = uhr.role_id\n"
                + "	JOIN main.factory AS f ON f.ID = u.factory_id \n"
                + "WHERE u.email = LOWER('" + user.getEmail() + "') AND u.password = '" + user.getPassword() + "';";
        ArrayList<UserRoleFactory> userRoles = null;
        try {
            UserRoleFactory userSearch = new UserRoleFactory();
            userSearch.setOcultarParametrosEntidad(true);
            userSearch.setConsultaPersonalizada(consulta);
            userRoles = (ArrayList<UserRoleFactory>) (ArrayList<?>) this.obtenerEntidades(ds, user);
        } catch (ModeloModuloEntidadesException exception) {
            if (exception.getCadenaSeguimiento().contains("SQLException")) {
                throw new ModeloModuloEntidadesException("Ocurrió un error en la Base de Datos al obtener el Usuario.", exception);
            } else {
                throw new ModeloModuloEntidadesException("Ocurrió un error durante el proceso de obtener el Usuario.", exception);
            }
        }
        return userRoles;
    }

    public void cambiarCaracteresRarosToNormales(String cadenaRara) throws Exception {
        if (cadenaRara == null) {
            throw new Exception("La cadena a modificar no puede ser null");
        }
        if (cadenaRara.isEmpty()) {
            throw new Exception("La cadena a modificar no puede ser null");
        }
        HashMap<String, String> mapCaracteresChange = new HashMap<String, String>();
        mapCaracteresChange.put("à", "a");
        mapCaracteresChange.put("á", "a");
        mapCaracteresChange.put("â", "a");
        mapCaracteresChange.put("ã", "a");
        mapCaracteresChange.put("ä", "a");
        mapCaracteresChange.put("å", "a");
        mapCaracteresChange.put("å", "a");
        mapCaracteresChange.put("Á", "A");
        mapCaracteresChange.put("Ã", "A");
        mapCaracteresChange.put("Ä", "A");
        mapCaracteresChange.put("Å", "A");
        mapCaracteresChange.put("è", "e");
        mapCaracteresChange.put("é", "e");
        mapCaracteresChange.put("é", "e");
        mapCaracteresChange.put("ê", "e");
        mapCaracteresChange.put("ë", "e");
        mapCaracteresChange.put("ì", "i");
        mapCaracteresChange.put("í", "i");
        mapCaracteresChange.put("ï", "i");
        mapCaracteresChange.put("ì", "i");
        mapCaracteresChange.put("Ì", "I");
        mapCaracteresChange.put("Í", "I");
        mapCaracteresChange.put("Î", "I");
        mapCaracteresChange.put("Ï", "I");
        mapCaracteresChange.put("Ì", "I");
        mapCaracteresChange.put("ó", "o");
        mapCaracteresChange.put("ô", "o");
        mapCaracteresChange.put("õ", "o");
        mapCaracteresChange.put("ö", "o");
        mapCaracteresChange.put("Ò", "O");
        mapCaracteresChange.put("Ó", "O");
        mapCaracteresChange.put("Ô", "O");
        mapCaracteresChange.put("Õ", "O");
        mapCaracteresChange.put("Ö", "O");
        mapCaracteresChange.put("ù", "u");
        mapCaracteresChange.put("ú", "u");
        mapCaracteresChange.put("û", "u");
        mapCaracteresChange.put("ü", "u");
        mapCaracteresChange.put("Ü", "U");
        mapCaracteresChange.put("Û", "U");
        mapCaracteresChange.put("Ú", "U");
        mapCaracteresChange.put("Ù", "U");

        for (int i = 0; i < cadenaRara.length(); i++) {
            char viejaLetra = cadenaRara.charAt(i);
            String nuevaLetra = mapCaracteresChange.get(String.valueOf(cadenaRara.charAt(i)));
            if (nuevaLetra != null ) {
                cadenaRara = cadenaRara.replace(viejaLetra, nuevaLetra.charAt(0));
            }
        }
        System.out.println(cadenaRara);

    }

    public static void main(String[] args) throws Exception {
        ModuleLogin m = new ModuleLogin(true);
        String a = "bási";
        m.cambiarCaracteresRarosToNormales(a);
    }
    
}
