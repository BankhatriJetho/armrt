// ReportController.java
@RestController
@RequestMapping("/api/reports")
public class ReportController {
   private final ReportGenerationService reportService;

   public ReportController(ReportGenerationService reportService) {
       this.reportService = reportService;
   }

   @GetMapping("/comprehensive")
   public ResponseEntity<byte[]> generateComprehensiveReport() {
       ByteArrayOutputStream report = reportService.generateComprehensiveReport();
       return ResponseEntity.ok()
           .contentType(MediaType.APPLICATION_PDF)
           .body(report.toByteArray());
   }
}