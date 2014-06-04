## Spring cOntent negoTiation And maRshalling tOy [![Build Status](https://travis-ci.org/gv0tch0/sotaro.png?branch=master)](https://travis-ci.org/gv0tch0/sotaro)

### Motivation

I have been having a hard time trying to get XML negotiation and marshalling over HTTP working for a service that uses [Spring Web MVC](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html). This project contains a toy example that exemplifies the problem paired with an ask on StackOverflow (coming up) hoping that someone can solve the problem for me.

When this toy example is built and run (see below for HOWTO) the "service" responds with a 406 Not Acceptable to requests that ask for XML content back. I have tried a number of different Spring configurations (using Spring 4, 4.0.5 at the time of writing) as evident by the number of tryX.ctx.xml files [here](ihttps://github.com/gv0tch0/sotaro/tree/master/src/main/resources/io/github/gv0tch0/sotaro).

Here is a example of a request/response cycle (some of the output has been omitted for brevity).
```
  [...]$ curl -v -X GET -H "Accept: application/xml" http://localhost:8080/sotaro/say/boo | tidy -ashtml -utf8 --indent yes
  * Connected to localhost (::1) port 8080 (#0)
  > GET /sotaro/say/boo HTTP/1.1
  > User-Agent: curl/7.30.0
  > Host: localhost:8080
  > Accept: application/xml
  >
  < HTTP/1.1 406 Not Acceptable
  * Server Apache-Coyote/1.1 is not blacklisted
  < Server: Apache-Coyote/1.1
  < Content-Type: text/html;charset=utf-8
  < Content-Language: en
  < Content-Length: 1067
  < Date: Wed, 04 Jun 2014 12:48:44 GMT
  <
  <html>
    <body>
      <h1>HTTP Status 406 -</h1>
      <p><b>message</b></p>
      <p><b>description</b> <u>The resource identified by this request is only capable of generating responses with characteristics not acceptable according to the request "accept" headers.</u></p>
    </body>
  </html>
```

### Solution Constraints

- Spring configuration should stay in XML.
- The response object which is marshalled back should stay generated by JAXB2 based on [schema](). As such the object is missing the @XmlRootElement annotation, but JAXB2 generates an `ObjectFactory` which has the means to wrap it in a `JAXBElement` which makes XML marshallers happy.
- Success configuration should allow for other content types to be added later on, e.g. `JSON`.

### Building

The implementation is in java. To build please use [maven](http://maven.apache.org/ "Maven Home"). Pull the repository and type `mvn` at the top-level repository directory.

#### Build Dependencies

- Internet connection. This is necessary to pull the project's dependencies expressed in the project's pom (and any transitive dependencies that those pull). The central maven repository contains all of them.
- Maven 2.2.1+.
- JDK 1.6+.

### Running

Running the project has been tested in [Tomcat 7](http://tomcat.apache.org/download-70.cgi) (more specifically 7.0.54). After the project has been successfully built, copy the `${project.basedir}/target/sotaro.war` file under the `${tomcat.basedir}/webapps` directory, fire up tomcat, and `curl -v -X GET -H "Accept: application/xml" http://localhost:8080/sotaro/say/boo` away (assuming tomcat is running locally and is listening on its default port, `8080`).

