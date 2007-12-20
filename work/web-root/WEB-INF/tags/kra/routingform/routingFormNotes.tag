<%--
 Copyright 2006-2007 The Kuali Foundation.
 
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
<%@ include file="/jsp/kfs/kfsTldHeader.jsp"%>

<div id="workarea" >

<div align="right">
	<kul:help documentTypeName="${DataDictionary.KualiRoutingFormDocument.documentTypeName}" pageName="${KraConstants.NOTES_HEADER_TAB}" altText="page help"/>
</div>

<kul:notes allowsNoteDelete="${DataDictionary.KualiRoutingFormDocument.allowsNoteDelete}" defaultOpen="true" attachmentTypesValuesFinderClass="${DataDictionary.KualiRoutingFormDocument.attachmentTypesValuesFinderClass}" displayTopicFieldInNotes="${DataDictionary.KualiRoutingFormDocument.displayTopicFieldInNotes}" transparentBackground="true" />
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="b3" summary="">
  <tr>
    <td align="left" class="footer"><img src="${ConfigProperties.kr.externalizable.images.url}pixel_clear.gif" alt="" width="12" height="14" class="bl3"></td>
    <td align="right" class="footer-right"><img src="${ConfigProperties.kr.externalizable.images.url}pixel_clear.gif" alt="" width="12" height="14" class="br3"></td>
  </tr>
</table>  

<br/>
<br/>
</div>