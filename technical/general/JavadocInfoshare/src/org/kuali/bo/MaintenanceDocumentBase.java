package org.kuali.bo;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.StringUtils;
import org.kuali.exceptions.IllegalObjectStateException;
import org.kuali.maintenance.Maintainable;
import org.kuali.util.SpringServiceLocator;
import org.kuali.validation.ValidationErrorList;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import edu.iu.uis.eden.EdenConstants;

/**
 * 
 * @author bmcgough
 * 
 * The maintenance xml structure will be: <maintainableDocumentContents maintainableImplClass="className">
 * <oldMaintainableObject>... </oldMaintainableObject> <newMaintainableObject>... </newMaintainableObject>
 * </maintainableDocumentContents>
 */

public final class MaintenanceDocumentBase extends DocumentBase implements MaintenanceDocument {

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(MaintenanceDocumentBase.class);

    protected String documentContents;
    protected String lockingRepresentation;
    protected boolean locked;

    protected transient Maintainable oldMaintainableObject;
    protected transient Maintainable newMaintainableObject;
    public static final String MAINTAINABLE_IMPL_CLASS = "maintainableImplClass";
    public static final String OLD_MAINTAINABLE_TAG_NAME = "oldMaintainableObject";
    public static final String NEW_MAINTAINABLE_TAG_NAME = "newMaintainableObject";


    public MaintenanceDocumentBase() {
        super();
    }


    /**
     * Initializies the maintainables.
     */
    public MaintenanceDocumentBase(String documentTypeName) {
        super();
        Class clazz = SpringServiceLocator.getMaintenanceDocumentDictionaryService().getMaintainableClass(documentTypeName);
        try {
            oldMaintainableObject = (Maintainable) clazz.newInstance();
            newMaintainableObject = (Maintainable) clazz.newInstance();
        }
        catch (InstantiationException e) {
            LOG.error("Unable to initialize maintainables of type " + clazz.getName());
            throw new RuntimeException("Unable to initialize maintainables of type " + clazz.getName());
        }
        catch (IllegalAccessException e) {
            LOG.error("Unable to initialize maintainables of type " + clazz.getName());
            throw new RuntimeException("Unable to initialize maintainables of type " + clazz.getName());
        }
    }

    /**
     * Builds out the document title for maintenance documents - this will get loaded into the flex doc and passed into workflow. It
     * will be searchable.
     */
    public String getDocumentTitle() {
        String documentTitle = "";
        //TODO - build out with bo label once we get the data dictionary stuff in place
        //build out the right classname
        String className = newMaintainableObject.getBusinessObject().getClass().getName();
        String truncatedClassName = className.substring(className.lastIndexOf('.') + 1);
        if (isOldBusinessObjectInDocument()) {
            documentTitle = "Edit ";
        }
        else {
            documentTitle = "New ";
        }
        documentTitle += truncatedClassName + " ";
        // TODO: talk with Aaron about the getKeysName replacement
        //	HashMap keyVals = (HashMap) newMaintainableObject.getKeysNameAndValuePairs();
        //    	Set list = keyVals.keySet();
        //    	Iterator i = list.iterator();
        //    	int idx = 0;
        //    	while(i.hasNext()) {
        //    		String key = (String) i.next();
        //    		String value = (String) keyVals.get(key);
        //    		if(idx != 0) {
        //    			documentTitle += ", ";
        //    		}
        //    		documentTitle += key;
        //    		documentTitle += " = ";
        //    		documentTitle += value;
        //    		idx++;
        //    	}
        //    	documentTitle += " - ";
        //    	documentTitle += this.getDocumentHeader().getDocumentDescription();
        return documentTitle;
    }

    public String getXmlDocumentContents() {
        return documentContents;
    }


    public void setXmlDocumentContents(String documentContents) {
        this.documentContents = documentContents;
    }


    /**
     * @param document
     */
    private boolean isOldMaintainableInDocument(Document xmlDocument) {
        boolean isOldMaintainableInExistence = false;
        if (xmlDocument.getElementsByTagName(OLD_MAINTAINABLE_TAG_NAME).getLength() > 0) {
            isOldMaintainableInExistence = true;
        }
        return isOldMaintainableInExistence;
    }


    /*
     * Checks old maintainable bo has key values
     */
    public boolean isOldBusinessObjectInDocument() {
        boolean isOldBusinessObjectInExistence = false;
        if (oldMaintainableObject == null || oldMaintainableObject.getBusinessObject() == null) {
            isOldBusinessObjectInExistence = false;
        }
        else {
            isOldBusinessObjectInExistence = SpringServiceLocator.getPersistenceService().hasPrimaryKeyFieldValues(
                    oldMaintainableObject.getBusinessObject());
        }
        return isOldBusinessObjectInExistence;
    }


    /*
     * Populates the maintainables from the xml document contents.
     */
    public void populateMaintainablesFromXmlDocumentContents() {
        // get a hold of the parsed xml document, then read the classname,
        // then instantiate one to two instances depending on content
        // then populate those instances
        if (!StringUtils.isEmpty(documentContents)) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            try {
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document xmlDocument = builder.parse(new InputSource(new StringReader(documentContents)));
                String clazz = xmlDocument.getDocumentElement().getAttribute(MAINTAINABLE_IMPL_CLASS);
                if (isOldMaintainableInDocument(xmlDocument)) {
                    oldMaintainableObject = (Maintainable) Class.forName(clazz).newInstance();
                    oldMaintainableObject.setBusinessObject(getBusinessObjectFromXML(documentContents, OLD_MAINTAINABLE_TAG_NAME));
                }
                newMaintainableObject = (Maintainable) Class.forName(clazz).newInstance();
                newMaintainableObject.setBusinessObject(getBusinessObjectFromXML(documentContents, NEW_MAINTAINABLE_TAG_NAME));
            }
            catch (ParserConfigurationException e) {
                LOG.error("Error while parsing document contents", e);
                throw new RuntimeException("Could not load document contents from xml", e);
            }
            catch (SAXException e) {
                LOG.error("Error while parsing document contents", e);
                throw new RuntimeException("Could not load document contents from xml", e);
            }
            catch (IOException e) {
                LOG.error("Error while parsing document contents", e);
                throw new RuntimeException("Could not load document contents from xml", e);
            }
            catch (InstantiationException e) {
                LOG.error("Error while parsing document contents", e);
                throw new RuntimeException("Could not load document contents from xml", e);
            }
            catch (IllegalAccessException e) {
                LOG.error("Error while parsing document contents", e);
                throw new RuntimeException("Could not load document contents from xml", e);
            }
            catch (ClassNotFoundException e) {
                LOG.error("Error while parsing document contents", e);
                throw new RuntimeException("Could not load document contents from xml", e);
            }

        }
    }


    /*
     * Retrieves substring of document contents from maintainable tag name. Then use xml service to translate xml into a business
     * object.
     */
    private BusinessObject getBusinessObjectFromXML(String xmlDocumentContents, String maintainableTagName) {
        String maintXml = StringUtils.substringBetween(documentContents, "<" + maintainableTagName + ">", "</"
                + maintainableTagName + ">");
        BusinessObject businessObject = (BusinessObject) SpringServiceLocator.getXmlObjectSerializerService().fromXml(maintXml);
        return businessObject;
    }


    /*
     * Populates the xml document contents from the maintainables.
     * @see org.kuali.bo.MaintenanceDocument#populateXmlDocumentContentsFromMaintainables()
     */
    public void populateXmlDocumentContentsFromMaintainables() {
        StringBuffer docContentBuffer = new StringBuffer();
        docContentBuffer.append("<maintainableDocumentContents maintainableImplClass=\"").append(
                newMaintainableObject.getClass().getName()).append("\">");
        if (oldMaintainableObject != null && oldMaintainableObject.getBusinessObject() != null) {
            // TODO: refactor this out into a method
            docContentBuffer.append("<" + OLD_MAINTAINABLE_TAG_NAME + ">");
            docContentBuffer.append(SpringServiceLocator.getXmlObjectSerializerService().toXml(
                    oldMaintainableObject.getBusinessObject()));
            docContentBuffer.append("</" + OLD_MAINTAINABLE_TAG_NAME + ">");
        }
        docContentBuffer.append("<" + NEW_MAINTAINABLE_TAG_NAME + ">");
        docContentBuffer.append(SpringServiceLocator.getXmlObjectSerializerService().toXml(
                newMaintainableObject.getBusinessObject()));
        docContentBuffer.append("</" + NEW_MAINTAINABLE_TAG_NAME + ">");
        docContentBuffer.append("</maintainableDocumentContents>");
        documentContents = docContentBuffer.toString();
    }


    public void populateDocumentForRouting() {
        // TODO Auto-generated method stub
        // can we have a simple way to do this that would be applicable for all
        // maint/ref document types?
        newMaintainableObject.populateDocumentForRouting(this);
    }


    public void validateDocument(ValidationErrorList errors) throws ValidationErrorList, IllegalObjectStateException {
        super.validateDocument(errors);
        // TODO Auto-generated method stub
        // can we have a simple way to do this that would be applicable for all
        // maint/ref document types?
        performMaintenanceValidation(errors);
    }


    /**
     * @param errors
     * @throws IllegalObjectStateException
     */
    private void performMaintenanceValidation(ValidationErrorList errors) throws IllegalObjectStateException {
        if (newMaintainableObject == null) {
            throw new IllegalObjectStateException("New maintainable is null.");
        }

        newMaintainableObject.validateForMaintenance(errors);
    }


    public void validateForRouting(ValidationErrorList errors) throws ValidationErrorList, IllegalObjectStateException {
        super.validateForRouting(errors);
        // TODO Auto-generated method stub
        // can we have a simple way to do this that would be applicable for all
        // maint/ref document types?
        performMaintenanceValidation(errors);
    }


    public String getLockingRepresentation() {
        return lockingRepresentation;
    }


    public void setLockingRepresentation(String lockingRepresentation) {
        this.lockingRepresentation = lockingRepresentation;
    }


    public boolean isLocked() {
        return locked;
    }


    public void setLocked(boolean isLocked) {
        this.locked = isLocked;
    }


    public Maintainable getNewMaintainableObject() {
        return newMaintainableObject;
    }


    public void setNewMaintainableObject(Maintainable newMaintainableObject) {
        this.newMaintainableObject = newMaintainableObject;
    }


    public Maintainable getOldMaintainableObject() {
        return oldMaintainableObject;
    }


    public void setOldMaintainableObject(Maintainable oldMaintainableObject) {
        this.oldMaintainableObject = oldMaintainableObject;
    }

    public void handleRouteStatusChange(String newRouteStatus) {
        if (EdenConstants.ROUTE_HEADER_PROCESSED_CD.equals(newRouteStatus)) {
            SpringServiceLocator.getDocumentService().saveMaintainableBusinessObject(newMaintainableObject.getBusinessObject());
            this.setLocked(false);
            this.getDocumentHeader().setKualiDocumentStatusCode(EdenConstants.ROUTE_HEADER_PROCESSED_CD);
            SpringServiceLocator.getDocumentService().updateDocument(this);
        }
        else if (EdenConstants.ROUTE_HEADER_CANCEL_CD.equals(newRouteStatus)) {
            this.setLocked(false);
            this.getDocumentHeader().setKualiDocumentStatusCode(EdenConstants.ROUTE_HEADER_CANCEL_CD);
            SpringServiceLocator.getDocumentService().updateDocument(this);
        }
        else if (EdenConstants.ROUTE_HEADER_DISAPPROVED_CD.equals(newRouteStatus)) {
            this.setLocked(false);
            this.getDocumentHeader().setKualiDocumentStatusCode(EdenConstants.ROUTE_HEADER_DISAPPROVED_CD);
            SpringServiceLocator.getDocumentService().updateDocument(this);
        }
        else if (EdenConstants.ROUTE_HEADER_ENROUTE_CD.equals(newRouteStatus)) {
            // we dont unlock for this status change, just update the document
            this.getDocumentHeader().setKualiDocumentStatusCode(EdenConstants.ROUTE_HEADER_ENROUTE_CD);
            SpringServiceLocator.getDocumentService().updateDocument(this);
        }
        else {
            throw new IllegalArgumentException("The new route status: " + newRouteStatus
                    + " can not be handled by this application");
        }
    }
}