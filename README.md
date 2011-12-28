# Commander IM

An Instant Messaging interface for the [Commander](http://www.github.com/willmeyer/commander) command-pattern Java
application framework.

This implementation of Commander's `CmdInputInterface` makes it easy for Java applications to expose their
command-line interface over a Jabber channel, including Google Talk.  For example, here's an IM session with the
command-line daemon that runs in my car ([car-d](http://www.github.com/willmeyer/car-d)):

![An example IM session](http://farm4.staticflickr.com/3549/3835551326_d76b41d050.jpg "An example IM session")

### Requirements & Setup

The library depends on the [Smack Jabber library](http://www.igniterealtime.org/projects/smack/), redistributed
in /externallibs.  Install the JARs like so:

- `mvn install:install-file -DgroupId=org.jivesoftware.smack -DartifactId=smack -Dversion=3.1.0 -Dpackaging=jar -Dfile=smack.jar`
- `mvn install:install-file -DgroupId=org.jivesoftware.smack -DartifactId=smackx -Dversion=3.1.0 -Dpackaging=jar -Dfile=smackx.jar`

### Usage

This is an implementation of `CmdInputInterface`, and can be instantiated in the context of a
[Commander](http://www.github.com/willmeyer/commander) application.

