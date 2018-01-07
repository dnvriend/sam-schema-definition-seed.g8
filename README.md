![Logo image](img/sbtscalasamlogo_small.png)

# sam-schema-definition-seed.g8
A template project for quickly creating schema definition projects. 

For more information see [sbt-sam](https://github.com/dnvriend/sbt-sam)

## Usage
Create a new template project by typing:

```
sbt new dnvriend/sam-schema-definition-seed.g8
```

## Introduction
sbt-sam provides the `sam-schema-plugin` that makes it possible to define schemas by means of annotating Scala 
case classes. Leveraging [avro4s](https://github.com/sksamuel/avro4s), [Apache Avro](http://avro.apache.org/) and
a design that envisions full data compatibility, zero-integration and data governance/architecture, sbt-sam provides
a workflow that provides this. 

## Defining schemas
Schemas can be defined by annotating case classes with `com.github.dnvriend.sam.serialization.annotation.SamSchema` that
marks a case class being the root of a schema definition:

```scala
@SamSchema
case class Person(
                   name: String = "",
                   age: Int = 0,
                   address: Address = Address(),
                 )

case class Address(
                    street: String = "",
                    houseNr: Int = 0,
                  )
```

## Schema repository
Schemas must be organized and stored in a central repository. Schemas have a `namespace-name` and a `schema-name` that
allows schemas - data shapes - to be organized and stored in a schema resolver. sbt-sam provides a schema repository in
the [dnvriend/sam-schema-repo-seed.g8](https://github.com/dnvriend/sam-schema-repo-seed.g8).

## Uploading schemas to the repository
Schemas must be shared because they allow us to reason about the possible shapes of data. The `sam-schema-plugin`, part
of `sbt-sam` provides a task for this: `schemaPush <schema-name>`. When the project has been configured with
the url and credentials to connect to the sam-schema-repo, it uploads a schema.

## Schema Resolvers
Schemas will be downloaded by means of schema-resolvers. Sbt-sam provides an interface  
`com.github.dnvriend.sam.serialization.resolver.SchemaResolver` so you can implement your own. The schema resolver that 
can be used is the `com.github.dnvriend.sam.serialization.HttpSchemaResolver` or the
`com.github.dnvriend.sam.serialization.HttpAuthSchemaResolver` to do authorization when the schema respository is secured.

Schema resolvers are used by `com.github.dnvriend.sam.serialization.serializer.SamSerializer` that uses the schema resolver
when a record must be deserialized. It serializes values to a `SamRecord` and deserializes a `SamRecord` back to a value.

## Serializing and Deserializing
sbt-sam defines a `SamRecord`, a type that will be available as JSON when serialized to a wire format and contains all 
information about the data that it contains. Effectively its an envelope and identifies data. It also contains information
about the compression format and encryption details.

## Schema Evolution
sbt-sam envisions full compatibility between the same schema. Between different schemas, a transform is necessary that can be
either simple, or very complex depending on the amount of mismatch between the schemas. Between the same schema, data is full
compatible and can also be read. 

## Schema dependencies
Projects - components - can be dependent on schemas - a certain data shape. These schemas will be downloaded, compiled,
and will be part of the component. Sbt-sam provides the task `schemaPull` and the setting `schemaDependency` to define
a way to download schemas, that the project/component depends upon.
    
