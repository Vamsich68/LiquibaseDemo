# Integrating liquibase with spring-boot

### WHAT IT IS

1. Liquibase is a database schema change management solution that enables you to revise and release database changes faster and safer from development to production.
2. Data Migration with liquibase, Database migration tools are designed to achieve the goal of making this process traceable, visible, convenient and more fault-tolerant.
3. Liquibase for making the database change process easy. Liquibase is an open source database independent library for tracking, managing and applying database schema changes.
4. Integrate Liquibase into your build process, So that we can eliminate manual database changes.
5. Source version control to database scripts/deployments.
6. It defines, what changes are necessary in what order.

### WHY

1. Database schema migrations are an essential task for every software project.
2. Additionally, we have to update the database table whenever you add a new property to the model class. To establish or modify the database schema to meet the application entities, we can utilize Hibernate to automatically fire the relevant DDL statements. However, running these statements may result in data loss.  At this stage, we require a database-agnostic tool that can efficiently make the data changes. What if you want to observe the database modifications from your previous database updates? Liquibase is a very effective and adaptable solution for database migration.
3. Liquibase works with 59 databases.
4. Easily define changes in SQL, XML, JSON, or YAML.


## Definitions

1. Changelogs :
   Change logs are an ordered list of changes to be deployed to the database,describes changes that need for database. We can have many changelog files for our DB changes throughout the time.

   4 different changelog formats: 1. SQL 2. YAML 3. XML 4. JSON

2. Changeset:
   A changeSet is the basic unit of change for a database. Changeset uniquely identified using author and id. Each database schema change you make with Liquibase is called a changeset. All changesets are tracked by Liquibase using changelogs. 

3. DATABASECHANGELOG and DATABASECHANGELOGLOCK:
   Liquibase creates two tables in your database, DATABASECHANGELOG and DATABASECHANGELOGLOCK. The DATABASECHANGELOG table tracks deployed changes so that you have a record. Liquibase compares the changesets in the changelog file with the DATABASECHANGELOG tracking table and deploys only new changesets. DATABASECHANGELOGLOCK prevents multiple instances of Liquibase from updating the database at the same time. The table manages access to the DATABASECHANGELOG table during deployment and ensures only one instance of Liquibase is updating the database.


8. once a change is introduced into the changelog file, it must never be altered, because it could mess up liquibase's expectations. This is because liquibase maintains a DATABASECHANGELOG table that record, what changes have already been run.

## How it works

1. Define database changes: 
Developers use changesets in Liquibase to describe alterations to the database. This can include creating tables, inserting data, and making other modifications.

2. Connection Check:
Liquibase validates database connection details such as credentials, database URL, and JDBC driver to ensure a secure and accurate connection.

3. Tracking Changes:
A tracking table is maintained by Liquibase to record the deployment status of changesets. This helps keep track of applied and pending changes.

4. Deployment Control:
Liquibase enables updating the database to the latest version, ensuring all changes are applied.

5. Script Execution and Validation:
Liquibase executes scripts (changelogs) to implement database changes. It also performs validation to catch errors early in the process, preventing potential issues. Build failure may occur if scripts contain errors.

## Setup environment
1.  Dependecies(Maven) to add in pom.xml
     ```
     <dependency>
        	 <groupId>org.liquibase</groupId>
        	 <artifactId>liquibase-core</artifactId>
    </dependency>
     ```

2. To set the connection between Liquibase with your database, you need the database connection information and parameters.
Add one more liquibase properties file along with application.properties or application.yml. This property file to store database connection information and parameters. Then add following configurations in pom.xml . 

        <plugin>
            <groupId>org.liquibase</groupId>
            <artifactId>liquibase-maven-plugin</artifactId>
            <configuration>
                <propertyFile>src/main/resources/liquibase.yml</propertyFile> 
            </configuration>
        </plugin>

3. If you want liquibase to handle creation of the tables (DDL) and not hibernate:
   you need to disable the hibernate auto-create flag.

set spring.jpa.hibernate.ddl-auto=none (or) remove this property from application.yml file.

4. Make a master changelog file, To organize all of the changelogs into a master changelog file.

## Disadvantages

1. Revisiting the scripts for this minor error is undoubtedly a headache for the developers
2. There is a very high probability of build failure if there is an error in at least one of the queries or even at a line or at a word. 

## Best Practices:

1. Try to validate sql scripts before applying it for build process. It helps us to identify naming mistakes, syntax errors, Dublicate changeset id's if any.

> mvn resources:resources liquibase:update

2. Always follow regualr naming conventions for different changelog files.

3. Always use new changelogs for different instances.
4. Don't modify changesets.

Sources:

1. https://contribute.liquibase.com/extensions-integrations/directory/integration-docs/springboot/using-springboot-with-maven/
2. Changelog formats
   https://docs.liquibase.com/start/home.html

3. https://medium.com/javarevisited/getting-started-with-spring-boot-and-liquibase-f559d4e38498

4. https://www.liquibase.org/get-started/best-practices
5. https://contribute.liquibase.com/extensions-integrations/directory/integration-docs/springboot/springboot/
