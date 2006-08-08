<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
   <xsl:template match="/">
      <html>
         <head>
            <!-- This is a generated file.  Do not edit. -->
            <link type="text/css" href="skin/tigris.css" rel="stylesheet" />
            <link type="text/css" href="skin/site.css" rel="stylesheet" />
            <link type="text/css" href="skin/print.css" rel="stylesheet" media="print" />

            <meta name="author" value="Avalon Documentation Team" />
            <meta name="email" value="avalon-dev@jakarta.apache.org" />

            <title>
            <xsl:value-of select="/site/document/title" />
            </title>
         </head>

         <body marginwidth="0" marginheight="0" class="composite" bgcolor="white">
<!-- TOP IMAGE -->
            <div id="banner">
               <table border="0" cellspacing="0" cellpadding="8" width="100%">
                  <tbody>
                     <tr>
                        <td align="left">
                          <a href="@group-logo.href@"><img src="@group-logo.src@" border="0" /></a>
                        </td>
                        <td align="right">
                           <a href="@project-logo.href@"><img src="@project-logo.src@" border="0" /></a>
                        </td>
                     </tr>
                  </tbody>
               </table>
            </div>

<!-- end header -->
<!-- breadcrumb trail (javascript-generated) -->
            <!-- don't do breadcrumbs for now as they apparantly break the skin in some browsers -->
            <table id="breadcrumbs" border="0" cellspacing="0" cellpadding="0" width="100%">
                        <td>
            <a href="http://jakarta.apache.org/">Jakarta Main</a> |
            <a href="http://jakarta.apache.org/avalon">Avalon Main</a> |
            <a href="../">Up</a>
                        </td>
                        <td align="right" style="text-align: right">
            <a href="http://jakarta.apache.org/avalon/framework/">Framework</a> |
            <a href="http://jakarta.apache.org/avalon/excalibur/">Excalibur</a> |
            <a href="http://jakarta.apache.org/avalon/cornerstone/">Cornerstone</a> |
            <a href="http://jakarta.apache.org/avalon/phoenix/">Phoenix</a> |
            <a href="http://jakarta.apache.org/avalon/apps/">Apps</a> |
            <a href="http://jakarta.apache.org/avalon/logkit/">Logkit</a>
                        </td>
            </table>

<!-- end breadcrumb trail -->

            <table border="0" cellspacing="0" cellpadding="8" width="100%" id="main">
               <tbody>
                  <tr valign="top">
<!-- LEFT SIDE NAVIGATION -->
                     <td id="leftcol">
                        <div id="navcolumn">
                           <xsl:copy-of select="/site/menu/node()|@*" />
                        </div>
                     </td>
<!-- BODY -->
                     <td>
                        <div id="bodycol">
                           <div class="app">
                              <div align="center">
                                <h1><xsl:value-of select="/site/document/title" /></h1>
                                <h2><xsl:value-of select="/site/document/subtitle" /></h2>
                               </div>

                              <div class="h3">
                                 <xsl:copy-of select="/site/document/body/node()|@*" />
                              </div>
                           </div>
                        </div>
                     </td>
                  </tr>
               </tbody>
            </table>

<!-- FOOTER -->
            <div id="footer">
               <table border="0" cellspacing="0" cellpadding="4" width="100%">
                  <tbody>
                     <tr>
                        <td align="left">Copyright &#x00A9; @year@ @vendor@. All Rights Reserved.</td>
                        <td></td>
                        <td align="right"><script language="JavaScript">
                          <![CDATA[<!--
                                  document.write("last modified: " + document.lastModified);
                          //  -->]]>

                           </script>
                        </td>
                     </tr>
                  </tbody>
               </table>
            </div>
         </body>
      </html>
   </xsl:template>
</xsl:stylesheet>

