/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package proyectopoo.heladeria;
import Modelo.Local;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LocalService implements ILocalService {

    private static final String RUTA_LOCALES = "ruta/al/archivo/de/locales.txt"; 

    @Override
    public List<Local> loadLocales() {
        List<Local> locales = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_LOCALES))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(","); 
                Double ejex = Double.parseDouble(datos[0]);
                Double ejey = Double.parseDouble(datos[1]);
                String lugar = datos[2];
                String horario = datos[3];
                locales.add(new Local(ejex, ejey, lugar, horario));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return locales;
    }
}