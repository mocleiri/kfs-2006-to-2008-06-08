<%--
 Copyright 2008 The Kuali Foundation.
 
 Licensed under the Educational Community License, Version 1.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.opensource.org/licenses/ecl1.php
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ page language="java"%>
<%@ taglib uri="/kr/WEB-INF/tlds/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="/kr/WEB-INF/tlds/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="/kr/WEB-INF/tlds/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="/kr/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/kr/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/kr/WEB-INF/tlds/displaytag.tld" prefix="display-el" %>

<html>
	<head>
		<title>Not Authorized</title>
		<link href="css/screen.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<table width="100%" border=0 cellpadding=0 cellspacing=0
			class="headercell1">
			<tr>
				<td>
					<img src="images/wf-logo.gif" alt="OneStart Workflow" width=150
						height=21 hspace=5 vspace=5>
				</td>
			</tr>
		</table>
		<br>
		<table align="center">
			<tr>
				<td>
					<font color="red">You are not authorized to access this portion of the application.</font>
				</td>
			</tr>
		</table>
	</body>
</html>


