package relatorio.java.relatorio.Generation.Service;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import relatorio.java.relatorio.Report.ReportData;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ReportService {
    public byte[] generateExcelReport(List <ReportData> data) throws IOException{

        Workbook workbook = new XSSFWorkbook(); 
        Sheet sheet = workbook.createSheet("User Report");

        // Criando cabeçalhos da planilha   
        Row headerRow = sheet.createRow(0); // Cria a linha 0
        String columns [] = {"Name", "Email", "Age", "Job"};

        for (int i =0; i < columns.length; i++){
            Cell cell = headerRow.createCell(i); // para cada coluna, cria uma célula
            cell.setCellValue(columns[i]); // para cada célula, insere o valor da coluna
            CellStyle style = workbook.createCellStyle(); // cria um estilo
            Font font = workbook.createFont(); // cria uma fonte
            font.setBold(true);
            style.setFont(font);
            cell.setCellStyle(style);
        }

        // Preencher os dados
        int rowNum = 1; // começa da linha 1
        for (ReportData reportData : data){
            Row row = sheet.createRow(rowNum++); // cria uma linha e incrementa o contador
            row.createCell(0).setCellValue(reportData.getName()); // na columa 0 mas na linha 1 insere o nome
            row.createCell(1).setCellValue(reportData.getEmail());
            row.createCell(2).setCellValue(reportData.getAge());
            row.createCell(3).setCellValue(reportData.getJob());
        }

        // Autoajustar colunas 

        for (int i = 0; i < columns.length; i++){
            sheet.autoSizeColumn(i); 
        }
        

        // Escrever para um array de bytes
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return outputStream.toByteArray();
    }
}
