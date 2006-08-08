VERSION 1.0.1
November 15, 2004

Welcome
Contents
Requirements
Bugs
Support
Documentation
Acknowledgements


Welcome to Internet2's OpenSAML
-------------------------------

OpenSAML is a nearly complete implementation of SAML 1.1
(http://www.oasis-open.org/committees/security/).
Java and C++ APIs are provided.

Please review the terms described in the LICENSE file before using this
code. It is similar to the Apache 1.1 license.

Finally, be aware that RSA Security Inc. has asserted a patent claim
against all implementations of SAML. Their terms for licensing
can be found at http://www.rsasecurity.com/solutions/standards/saml/

As a SAML toolkit, OpenSAML may be subject to this claim and developers
may obtain a royalty-free license from RSA directly. Internet2, UCAID,
and the OpenSAML developers are not responsible for anyone's failure to
do so, and take no position on the validity of this claim.


Contents:
---------

OpenSAML can be obtained directly from anonymous CVS as described in
http://www.opensaml.org/cvs.html

Separate source distributions for Java and C++ also exist and can be found
at http://wayf.internet2.edu/shibboleth/

The CVS layout is as follows:

opensaml/
    java/
        ant                 (Ant shell script)
        build.xml           (Ant build file)
        src/
        	conf/			(Default properties file)
            org/opensaml/   (OpenSAML source)
            schemas/        (XML schemas used)
        lib/                (required jar files)
        endorsed/			(jar files that must override JDK 1.4 classes)
        dist/               (OpenSAML binary)
        data/               (stuff for unit testing)
        doc/               	(Note JavaDocs must be built, they're not in cvs)
        tests/              (JUnit tests)

The Java distribution is a tarball of the java tree and related build
scripts.


Requirements:
-------------

Building the Java code is supported on Sun's 1.4.1 JDK and may be
possible with other 1.4 compatible Java compilers. There is a known
issue with JDK 1.4 and some of the dependent jarfiles OpenSAML uses.
The workaround for the issue is to copy the DOM3 Xerces jarfiles from
the lib folder into your JDK's "endorsed" class library location, typically
"$JAVA_HOME/jre/lib/endorsed". You may need to create this directory:

$ mkdir $JAVA_HOME/jre/lib/endorsed
$ cp <opensaml root>/java/lib/dom3-*.jar $JAVA_HOME/jre/lib/endorsed

Note that many servlet containers such as Tomcat override this location
and set a different one, and the jar files must also be copied there.

Numerous XML and related Java class libraries are required, and are
included in the package for the time being. Versions newer than those
included may or may not work.


Bugs:
-----

A bugzilla is now available at http://bugzilla.internet2.edu/


Support:
--------

A mailing list for users is available. Subscription instructions are
provided at http://www.opensaml.org/index.html#mailing

Support will likely be informal and ad hoc for the time being. The
authors' focus is on completing Shibboleth. Most bugs are likely to
affect that goal and will be addressed quickly, however, at least for
now. Please do not e-mail the author directly. Use the mailing list
or bugzilla (preferably use that for bug reports).


Documentation:
--------------

Javadocs can be built using ant. There are no explicit samples yet,
but there are test programs and higher level code in the Shibboleth
codebase that would help a novice see what some of the classes do.
An understanding of SAML also helps.


Acknowledgements:
-----------------

We wish to acknowledge the following copyrighted works that make up
portions of this software:

This product includes software developed by the Apache Software
Foundation (http://www.apache.org/).
