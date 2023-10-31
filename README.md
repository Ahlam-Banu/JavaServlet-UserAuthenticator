# JavaServlet-UserAuthenticator
Java web project showcasing a robust user authentication system implemented using servlets and a MariaDB database.
## Highlights
- Establish a secure connection to a MariaDB database by utilizing ServletContext parameters.
- Verify user identities based on their provided email and password.
- Seamlessly guide users to the home page after a successful login or back to the login page in case of authentication failure.
- Showcase effective resource management and error handling practices.
- Harness the power of Jakarta EE (formerly known as Java EE) and the MariaDB JDBC driver for your web development needs.
## Getting Started
1. **Setting Up the Database:** Begin by configuring a MariaDB database and setting up the connection parameters within the web.xml file.

2. **Deploying on a Server:** Deploy the project on a server compatible with Jakarta EE, such as Apache Tomcat.

3. **User Verification:** Users can initiate the authentication process by visiting the project's login page (Login.html) and providing their email and password.

4. **Welcome to the Home Page:** Upon successful authentication, users will be seamlessly directed to the home page (HomeServlet) and greeted with a welcoming message.
