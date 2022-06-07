# Hexagonal Project Example
The following was discovered as part of building this project:
This is a example project built in hexagonal architecture, the requirements for development were:

* To create an application for customer base (CRUD);
* To provide API REST;
* To provide Command Line Interface (CLI);
* To have a schedule task to list all customers in console log one time per minute;
* To persist data in database.

# Getting Started

### Package organization
The base package for this application is 'com.gusrubin.lab.hexagonal', the sub-packages are:

* <em>application</em> - Contains implementation of application layer (API REST, CLI and Schedule task).<br><br>
* <em>domain</em> - Contains all business rules, primary ports (UseCase interfaces) and secondary ports (Port interfaces). It's infrastructure independent, no tecnology or framework annotation or implementations are permited here.<br><br>
* <em>infrastructure</em> - Contains port adapters (implementation of secondary ports) and others related framework/tecnology implementations.

### References
The following guides illustrate how to use some features concretely:

* [Building a Hexagonal Architecture, DDD, and Spring](https://www.baeldung.com/hexagonal-architecture-ddd-spring)
* [Building a Bank Account example application in Hexagonal](https://jivimberg.io/blog/2020/02/01/hexagonal-architecture-on-spring-boot/)
* [NetFlix use case](https://netflixtechblog.com/ready-for-changes-with-hexagonal-architecture-b315ec967749)

### Additional Links
These additional references should also help you:

* [SOLID](https://en.wikipedia.org/wiki/SOLID)
* [Hexagonal Archictecture](https://en.wikipedia.org/wiki/Hexagonal_architecture_(software))
* [Domain Driven Design](https://en.wikipedia.org/wiki/Domain-driven_design)
