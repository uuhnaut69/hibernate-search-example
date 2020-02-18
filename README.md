# Hibernate Search: full-text search for domain model

Full text search for Java objects

This project provides synchronization between entities managed by Hibernate ORM and full-text indexing backends like Apache Lucene and Elasticsearch.

It will automatically apply changes to indexes, which is tedious and error prone coding work, while leaving you full control on the query aspects. The development community constantly researches and refines the index writing techniques to improve performance.

Mapping your objects to the indexes is declarative, using a combination of Hibernate Search specific annotations and the knowledge it can gather from your existing Hibernate/JPA mapping.

Queries can be defined by any combination of:

passing "native" queries directly (org.apache.lucene.Query for the Lucene backend, JSON for the Elasticsearch backend)
using our backend-agnostic DSL which generates the appropriate native queries based on the available schema metadata
Query results can include projections to be loaded directly from the index, or can materialize fully managed Hibernate entities loaded from the database within the current transactional scope.
