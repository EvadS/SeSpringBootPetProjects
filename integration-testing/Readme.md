 


 @DataJpaTest

 disable full full auto-configuration and instead apply only configuration relevant to JPA tests. By default, it will use an embedded, in-memory H2 database instead of the one declared in the configuration file, for faster test running time as compared to disk file database.

 @AutoConfigureTestDatabase
  disabled H2 in-memory database support

@AutoConfigureMockMvc

that can be applied to a test class to enable and configure auto-configuration of MockMvc.
