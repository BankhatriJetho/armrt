// ReportGenerationService.java
@Service
public class ReportGenerationService {
   private final RoleRecommendationRepository repository;

   public ReportGenerationService(RoleRecommendationRepository repository) {
       this.repository = repository;
   }

   public ByteArrayOutputStream generateComprehensiveReport() {
       List<RoleInsight> insights = repository.findRedundantRoles();
       try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
           Document document = new Document();
           PdfWriter.getInstance(document, baos);
           document.open();
           addReportHeader(document);
           addRedundantRolesSection(document, insights);
           addUnusedEntitlementsSection(document);
           addRecommendationSummary(document);
           document.close();
           return baos;
       } catch (DocumentException | IOException e) {
           throw new ReportGenerationException("Failed to generate report", e);
       }
   }

   private void addReportHeader(Document document) throws DocumentException {
       Paragraph title = new Paragraph("Role Optimization Report", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18));
       title.setAlignment(Element.ALIGN_CENTER);
       document.add(title);
   }

   // Additional report generation methods
}