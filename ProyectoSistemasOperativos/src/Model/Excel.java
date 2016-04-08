package Model;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class Excel {

    public void CrearExcel(String[][] laBitacora) throws IOException {
        SimpleDateFormat simpleFormat = new SimpleDateFormat("YYYY_MM_DD HH_MM_SS"); 
        String rutaArchivo = "Bitacora/Bitacora" + simpleFormat.format(new Date()) + ".xls";

        File archivoXLS = new File(rutaArchivo);
        if (!archivoXLS.exists()) {
            archivoXLS.createNewFile();
        }
        Workbook libro = new HSSFWorkbook();
        FileOutputStream archivo = new FileOutputStream(archivoXLS);
        Sheet hoja = libro.createSheet("Bitacora");
        String[]  NombresEncabezados = ObtengaEncabezados();
        for (int f = 0; f < laBitacora.length + 1; f++) {
            Row fila = hoja.createRow(f);
            for (int c = 0; c < laBitacora[0].length; c++) {
                Cell celda = fila.createCell(c);
                if (f == 0) {
                    celda.setCellValue(NombresEncabezados[c]);
                } else {
                    celda.setCellValue(laBitacora[f-1][c]);
                }
            }
        }
        libro.write(archivo);
        archivo.close();
        Desktop.getDesktop().open(archivoXLS);
    }
    
    private String[] ObtengaEncabezados(){
        String[] NombresEncabezados = new String[9];
        NombresEncabezados[0] = "Fecha";
        NombresEncabezados[1] = "Descripcion";
        NombresEncabezados[2] = "Monto Cargo Automatico";
        NombresEncabezados[3] = "Validacion Cargo Automatico";
        NombresEncabezados[4] = "Cedula Juricica Institucion";
        NombresEncabezados[5] = "Nombre Institucion";
        NombresEncabezados[6] = "Cedula Cliente";
        NombresEncabezados[7] = "Nombre Cliente";
        NombresEncabezados[8] = "Apellido Cliente";
        return NombresEncabezados;
    }

}
