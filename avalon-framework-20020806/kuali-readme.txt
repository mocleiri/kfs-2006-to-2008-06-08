This is the avalon subdirectory from:

svn co -r 14455 http://svn.apache.org/repos/asf/avalon/trunk

which is hopefully similar to the source for avalon-framework-cvs-20020806.jar.
That jar is in the fop-0.20.5 src distribution, but it doesn't include the
src for avalon.  The Avalon repository is in SVN now.

I tried svn co -r {2002-08-06} but somehow that checked out stuff from 2004.
So I looked at the svn log and tried the revision after 2002-08-05.

There seem to be 3 copies of the src: under src, framework/src/{api,impl},
and framework/{api,impl}/src.  I don't know which one was used by FOP,
or if they even differ, so I included them all.  Maybe when Apache
converted this repository from CVS to SVN it left behind some echos
of moved directories?
