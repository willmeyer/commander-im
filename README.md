# Commander IM

An Instant Messaging interface for the Command framework.

This allows Command-based Java applications to support their command-line interface over a Jabber channel,
including Google Talk.

## Setup 

Depends on the Smack Jabber library from Ignite Software.  Install the JARs from externallibs like so:

- mvn install:install-file -DgroupId=org.jivesoftware.smack -DartifactId=smack -Dversion=3.1.0 -Dpackaging=jar -Dfile=smack.jar
- mvn install:install-file -DgroupId=org.jivesoftware.smack -DartifactId=smackx -Dversion=3.1.0 -Dpackaging=jar -Dfile=smackx.jar
