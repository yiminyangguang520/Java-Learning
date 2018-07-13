### II. SpringBoot & Couchbase

[Spring Boot](http://javasampleapproach.com/tag/spring-boot) provides auto-configuration for **Couchbase** and abstractions on top of it by **Spring Data Couchbase**. We can use it easily via `spring-boot-starter-data-couchbase` ‘Starter’ which had collected the needed dependencies.

![Spring JPA Couchbase - SpringBoot - Architecture](http://javasampleapproach.com/wp-content/uploads/2017/08/SpringBoot-Couchbase-Architecture-resize.png)

For connecting with Couchbase **Bucket** and **Cluster**, we can use `spring.couchbase.*` properties in **application.properties** file:

```
spring.couchbase.bootstrap-hosts=127.0.0.1
spring.couchbase.bucket.name=jsabucket
spring.couchbase.bucket.password=123456
spring.data.couchbase.auto-index=true
```

For working with **Couchbase** repository, we use `CouchbaseRepository` interface:

```
public interface CouchbaseRepository<T, ID extends Serializable> extends CrudRepository<T, ID> {
 
  /**
   * @return a reference to the underlying {@link CouchbaseOperations operation template}.
   */
  CouchbaseOperations getCouchbaseOperations();
 
}
```

***\**Note***** We can meet some problems when working with Couchbase database:

1. No **view** configuration => Exception:

   ```
   Caused by: org.springframework.dao.InvalidDataAccessResourceUsageException: View customer/all does not exist.; nested exception is com.couchbase.client.java.error.ViewDoesNotExistException: View customer/all does not exist.
   	at org.springframework.data.couchbase.core.CouchbaseExceptionTranslator.translateExceptionIfPossible(CouchbaseExceptionTranslator.java:113) ~[spring-data-couchbase-2.2.6.RELEASE.jar:na]
   	at org.springframework.data.couchbase.core.CouchbaseTemplate.execute(CouchbaseTemplate.java:539) ~[spring-data-couchbase-2.2.6.RELEASE.jar:na]
   ```

2. No **index** => Exception:

   ```
   Caused by: org.springframework.data.couchbase.core.CouchbaseQueryExecutionException: Unable to execute query due to the following n1ql errors: 
   {"msg":"No index available on keyspace jsabucket that matches your query. Use CREATE INDEX or CREATE PRIMARY INDEX to create an index, or check that your expected index is online.","code":4000}
   ```

**Solution**: – Manually setup Couchbase view and create index *or* – Using SpringBoot support: `@N1qlPrimaryIndexed` annotation & configure `spring.data.couchbase.auto-index=true` to create views and indexs. `spring.data.couchbase.auto-index` is used to automatically create views and indexes. Use the meta-data provided by `@ViewIndexed`, `@N1qlPrimaryIndexed` and `@N1qlSecondaryIndexed`. 

### III. Practice

In the tutorial, we setup **Couchbase 4.6.2** server and use **SpringToolSuite** to create SpringBoot project:

![spring jpa couchbase - project structure](http://javasampleapproach.com/wp-content/uploads/2017/08/springboot-couchbase-project-structure.png)

**Step to do**:
– Setup Couchbase 4.6.2 server
– Create SpringBoot project
– Create Couchbase model
– Create Couchbase repository
– Implement client
– Run & check results

##### 1. Setup Couchbase 4.6.2 server

Download **Couchbase** at <https://www.couchbase.com/downloads>

![couchbase download](http://javasampleapproach.com/wp-content/uploads/2017/08/couchbase-download.png)

Press **Download** -> **Fill Info** -> **Try it now**:

![couchbase download - fill info](http://javasampleapproach.com/wp-content/uploads/2017/08/couchbase-download-fill-info.png)

-> We got `couchbase-server-enterprise_4.6.2-windows_amd64.exe` file.

![couchbase installer](http://javasampleapproach.com/wp-content/uploads/2017/08/couchbase-installer.png)

**Note**: turn-off your virus protection programs before setup.

Right click on the installer for installing. Follow the installer prompts,

![couchbase install](http://javasampleapproach.com/wp-content/uploads/2017/08/couchbase-install.png)

![couchbase install-required](http://javasampleapproach.com/wp-content/uploads/2017/08/couchbase-install-required.png)

-> We got an **Web-UI** setup: at `http://localhost:8091`.

![install couchebase server - web setup](http://javasampleapproach.com/wp-content/uploads/2017/08/install-couchebase-server-web-setup.png)

Press **Setup** button.

![couchbase server setup - web ui - 1](http://javasampleapproach.com/wp-content/uploads/2017/08/couchbase-server-setup-web-ui-1.png)
![couchbase server setup - web ui - 2](http://javasampleapproach.com/wp-content/uploads/2017/08/couchbase-server-setup-web-ui-2.png)

Click **Next** to accept the default values for new cluster.

![couchbase server setup - web ui - sample buckets - sample bucket](http://javasampleapproach.com/wp-content/uploads/2017/08/couchbase-server-setup-web-ui-sample-buckets-sample-bucket.png)

On **Sample Buckets** screen, click choose **beer-sample** and **travel-sample** and click **Next**, we got:

![couchbase server setup - web ui - step 3-5](http://javasampleapproach.com/wp-content/uploads/2017/08/couchbase-server-setup-web-ui-step-3-5.png)

On above **Create Default Bucket**, under **Memory Size** set the **Per Node RAM Quota** to 100 MB and click **Next**, we got:

![couchbase server setup - web ui - step 4-5](http://javasampleapproach.com/wp-content/uploads/2017/08/couchbase-server-setup-web-ui-step-4-5-1.png)

On above **Notifications** screen, enter your registration information then click **Next**, we got:

![couchbase server setup - web ui - step 5-5](http://javasampleapproach.com/wp-content/uploads/2017/08/couchbase-server-setup-web-ui-step-5-5.png)

On the **Configure Server** screen, enter and verify a password for the administrator account, and click **Next**.
The **Couchbase Web Console** opens and displays the **Cluster Overview**:

![couchbase server setup - web ui - final](http://javasampleapproach.com/wp-content/uploads/2017/08/couchbase-server-setup-web-ui-final.png)

-> Now **Couchbase** server is ready for use.

– Create **jsabucket**:

![create jsa bucket](http://javasampleapproach.com/wp-content/uploads/2017/08/create-jsa-bucket.png)

![create jsa bucket - result](http://javasampleapproach.com/wp-content/uploads/2017/08/create-jsa-bucket-result.png)

##### 2. Create SpringBoot project

Using **SpringToolSuite**, create a SpringBoot project, then add needed dependency `spring-data-couchbase`:

```
<dependency>
	<groupId>org.springframework.data</groupId>
	<artifactId>spring-data-couchbase</artifactId>
</dependency>
```

##### 3. Create Couchbase model

Create a Couchbase model **Customer**:

```
package com.javasampleapproach.couchbase.model;

import org.springframework.data.couchbase.core.mapping.Document;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;

@Document
public class Customer {
	@Id
	private String id;

	@Field
	private String firstName;

	@Field
	private String lastName;
	
	public Customer(String id, String firstName, String lastName){
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return String.format("Customer[ id=%s, firstName=%s, lastName=%s]", this.id, this.firstName, this.lastName);
	}
}
```

##### 4. Create Couchbase repository

– Create `CustomerRepository` repository:

```
package com.javasampleapproach.couchbase.repo;

import java.util.List;

import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbaseRepository;

import com.javasampleapproach.couchbase.model.Customer;

@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "customer", viewName = "all")
public interface CustomerRepository extends CouchbaseRepository<Customer, String> {
	
	List<Customer> findByLastName(String name);
}
```

– Open **application.properties**, add connection config:

```
spring.couchbase.bootstrap-hosts=127.0.0.1
spring.couchbase.bucket.name=jsabucket
spring.couchbase.bucket.password=123456
spring.data.couchbase.auto-index=true
```

##### 5. Implement client

Using above `CustomerRepository` to implement client:

```
package com.javasampleapproach.couchbase;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.javasampleapproach.couchbase.model.Customer;
import com.javasampleapproach.couchbase.repo.CustomerRepository;

@SpringBootApplication
public class SpringBootCouchbaseApplication implements CommandLineRunner {
	
	@Autowired
	private CustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCouchbaseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// delete all documents
		customerRepository.deleteAll();
		
		// save Customer to Couchbase
		customerRepository.save(Arrays.asList(new Customer("01", "Jack", "Smith"),
												new Customer("02", "Adam", "Johnson"),
												new Customer("03", "Kim", "Smith"),
												new Customer("04", "David", "Williams"),
												new Customer("05", "Peter", "Davis")));
		
		System.out.println("=============Find All Customer=============");
		List<Customer> custs = (List<Customer>) customerRepository.findAll();
		custs.forEach(System.out::println);
		
		System.out.println("=============findByLastName('Smith')=============");
		custs = customerRepository.findByLastName("Smith");
		custs.forEach(System.out::println);

	}
}
```

##### 6. Run & check results

Build and Run the SpringBoot project with commandlines {`mvn clean install`, `mvn spring-boot:run`}.

– Results:

-> **Logs**:

```
=============Find All Customer=============
Customer[ id=01, firstName=Jack, lastName=Smith]
Customer[ id=02, firstName=Adam, lastName=Johnson]
Customer[ id=03, firstName=Kim, lastName=Smith]
Customer[ id=04, firstName=David, lastName=Williams]
Customer[ id=05, firstName=Peter, lastName=Davis]
=============findByLastName('Smith')=============
Customer[ id=01, firstName=Jack, lastName=Smith]
Customer[ id=03, firstName=Kim, lastName=Smith]
```

-> **all** view is auto-created by the SpringBoot application.

![couchbase productview - all](http://javasampleapproach.com/wp-content/uploads/2017/08/couchbase-productview-all.png)

![couchbase select all](http://javasampleapproach.com/wp-content/uploads/2017/08/couchbase-select-all.png) 