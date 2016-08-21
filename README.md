# Cards Micro Resource

## Scope

This micro resource is used to manage a Magic The Gathering card collection.
This service will allow returning a collection of stored cards, with the option
to filter, as well as to add, update, and delete cards from the collection.

## Setup

1.  Copy example config file:

    ```shell
      $> cp conf/application.conf.example conf/application.conf
    ```

2.  Run activator to fetch dependencies and compile, then wait while you
download the internet:

    ```shell
      $> activator update compile
    ```

Upon downloading the internet you should be able to access
your application at http://localhost:9000/

3. Run activator to generate your IDE files:

    ```shell
      $> activator ${eclipse|idea}
    ```

For more information, see the [Play IDE setup page](https://www.playframework.com/documentation/2.1.x/IDE).

4. Open the project in your IDE.

## Configuration

Copy conf/application.conf.example to conf/application.conf

Update the config to use your mock or real data store. The default is to use the mock data source.
```
    datastore.datasource="mock"
```

The application.conf file _MUST_ be present for the application to run.

## RESTful Interface

Documentation for each RESTful endpoint.

<br />
<br />
<br />

## GET Card Collection

Get zero or more cards resources in the collection. An optional filter may be
applied to return specific collections, i.e., by color, rarity, etc.

##### HTTP Verb + Route:
```
    GET /cards
```

##### Required inputs/Query strings

  *  (**optional**) **filter** An optional filter argument may be added to
  return a subset of the collection. The following are the allowed filter
  arguments:

    * color
    * rarity
    * type

##### Example request
```shell
    $> curl http://localhost:9000/cards?filter[color]='red'
```

##### Example response
```json
{
  "hello": "world"
}
```

##### Resource Data
The main MySQL database (magic_the_gathering) will supply all data for the
response.

MySQL Tables:

  * cards

<br />
<br />




## Appendix
-----------
**Assumptions**

It should be assumed that authentication and configuration related tasks are handled prior to performing any request to these resources. Each resource will receive configuration values that are used to provide the response.

Assumed configuration values may include, but are not limited to:

  * Content Language
  * Member/OrgUnit Configuration
  * System Request ID


<br />
<br />

**Testing Notes**

JUnit is the testing framework used and the defacto standard for Java projects, we utilize it here.

To do mocking, we use Mockito and PowerMock. For assertions, we utilize AssertJ.

Unit tests can be run from within your IDE, which is preferred for correct classpath setup.
