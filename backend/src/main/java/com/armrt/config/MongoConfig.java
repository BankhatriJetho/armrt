// MongoConfig.java
@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {
   @Value("${spring.data.mongodb.database}")
   private String databaseName;

   @Value("${spring.data.mongodb.host}")
   private String host;

   @Value("${spring.data.mongodb.port}")
   private int port;

   @Override
   protected String getDatabaseName() {
       return databaseName;
   }

   @Override
   public MongoClient mongoClient() {
       ConnectionString connectionString = new ConnectionString("mongodb://" + host + ":" + port + "/" + databaseName);
       MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
           .applyConnectionString(connectionString)
           .build();

       return MongoClients.create(mongoClientSettings);
   }
}