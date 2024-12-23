package relatorio.java.relatorio.Generation.Controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import relatorio.java.relatorio.Generation.Service.ReportService;
import relatorio.java.relatorio.Report.ReportData;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired 
    private ReportService reportService;

    @GetMapping("/excel")
    public ResponseEntity<byte[]> generateExcelReport() {
        try {
          // Inserir os dados no relatório 
          List<ReportData> data = Arrays.asList(
            new ReportData("cecilia", "ceciliaperet@gmail.com", 22, "developer"),
            new ReportData("victoria", "victoriaperet@gmail.com", 22, "promoter")
        );

        byte[] bytes = reportService.generateExcelReport(data);// Gera o relatório em excel


        // COfnigurar o cabeçalho para download
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=UserReport.csw");
        headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        // Configurar o cabeçalho para download

        return ResponseEntity.ok()
                    .headers(headers)
                    .body(bytes);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
