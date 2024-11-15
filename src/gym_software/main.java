/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gym_software;

import gym_software.db.Conexion;
import gym_software.vista.frmprincipal;
import java.sql.SQLException;

/**
 *
 * @author pc
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        frmprincipal f = new frmprincipal();
        f.setVisible(true);
        f.setLocationRelativeTo(null);
    }
    
}
