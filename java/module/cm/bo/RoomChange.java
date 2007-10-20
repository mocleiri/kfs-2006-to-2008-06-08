package org.kuali.module.cams.bo;

import java.sql.Date;
import java.util.LinkedHashMap;

import org.kuali.core.bo.Campus;
import org.kuali.core.bo.PersistableBusinessObjectBase;
import org.kuali.kfs.bo.Building;
import org.kuali.kfs.bo.Room;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class RoomChange extends PersistableBusinessObjectBase {

	private String campusCode;
	private String buildingCode;
	private String oldBuildingRoomNumber;
	private Date changeTransactionRequestDate;
	private String oldBuildingSubRoomNumber;
	private String newBuildingRoomNumber;
	private String newBuildingSubRoomNumber;
	private Date changeTransactionExecuteDate;
	private Long changeTransactionRecordCount;

    private Campus campus;
    private Building building;
    private Room oldBuildingRoom;
    private Room newBuildingRoom;
   
	/**
	 * Default constructor.
	 */
	public RoomChange() {

	}

	/**
	 * Gets the campusCode attribute.
	 * 
	 * @return Returns the campusCode
	 * 
	 */
	public String getCampusCode() { 
		return campusCode;
	}

	/**
	 * Sets the campusCode attribute.
	 * 
	 * @param campusCode The campusCode to set.
	 * 
	 */
	public void setCampusCode(String campusCode) {
		this.campusCode = campusCode;
	}


	/**
	 * Gets the buildingCode attribute.
	 * 
	 * @return Returns the buildingCode
	 * 
	 */
	public String getBuildingCode() { 
		return buildingCode;
	}

	/**
	 * Sets the buildingCode attribute.
	 * 
	 * @param buildingCode The buildingCode to set.
	 * 
	 */
	public void setBuildingCode(String buildingCode) {
		this.buildingCode = buildingCode;
	}


	/**
	 * Gets the oldBuildingRoomNumber attribute.
	 * 
	 * @return Returns the oldBuildingRoomNumber
	 * 
	 */
	public String getOldBuildingRoomNumber() { 
		return oldBuildingRoomNumber;
	}

	/**
	 * Sets the oldBuildingRoomNumber attribute.
	 * 
	 * @param oldBuildingRoomNumber The oldBuildingRoomNumber to set.
	 * 
	 */
	public void setOldBuildingRoomNumber(String oldBuildingRoomNumber) {
		this.oldBuildingRoomNumber = oldBuildingRoomNumber;
	}


	/**
	 * Gets the changeTransactionRequestDate attribute.
	 * 
	 * @return Returns the changeTransactionRequestDate
	 * 
	 */
	public Date getChangeTransactionRequestDate() { 
		return changeTransactionRequestDate;
	}

	/**
	 * Sets the changeTransactionRequestDate attribute.
	 * 
	 * @param changeTransactionRequestDate The changeTransactionRequestDate to set.
	 * 
	 */
	public void setChangeTransactionRequestDate(Date changeTransactionRequestDate) {
		this.changeTransactionRequestDate = changeTransactionRequestDate;
	}


	/**
	 * Gets the oldBuildingSubRoomNumber attribute.
	 * 
	 * @return Returns the oldBuildingSubRoomNumber
	 * 
	 */
	public String getOldBuildingSubRoomNumber() { 
		return oldBuildingSubRoomNumber;
	}

	/**
	 * Sets the oldBuildingSubRoomNumber attribute.
	 * 
	 * @param oldBuildingSubRoomNumber The oldBuildingSubRoomNumber to set.
	 * 
	 */
	public void setOldBuildingSubRoomNumber(String oldBuildingSubRoomNumber) {
		this.oldBuildingSubRoomNumber = oldBuildingSubRoomNumber;
	}


	/**
	 * Gets the newBuildingRoomNumber attribute.
	 * 
	 * @return Returns the newBuildingRoomNumber
	 * 
	 */
	public String getNewBuildingRoomNumber() { 
		return newBuildingRoomNumber;
	}

	/**
	 * Sets the newBuildingRoomNumber attribute.
	 * 
	 * @param newBuildingRoomNumber The newBuildingRoomNumber to set.
	 * 
	 */
	public void setNewBuildingRoomNumber(String newBuildingRoomNumber) {
		this.newBuildingRoomNumber = newBuildingRoomNumber;
	}


	/**
	 * Gets the newBuildingSubRoomNumber attribute.
	 * 
	 * @return Returns the newBuildingSubRoomNumber
	 * 
	 */
	public String getNewBuildingSubRoomNumber() { 
		return newBuildingSubRoomNumber;
	}

	/**
	 * Sets the newBuildingSubRoomNumber attribute.
	 * 
	 * @param newBuildingSubRoomNumber The newBuildingSubRoomNumber to set.
	 * 
	 */
	public void setNewBuildingSubRoomNumber(String newBuildingSubRoomNumber) {
		this.newBuildingSubRoomNumber = newBuildingSubRoomNumber;
	}


	/**
	 * Gets the changeTransactionExecuteDate attribute.
	 * 
	 * @return Returns the changeTransactionExecuteDate
	 * 
	 */
	public Date getChangeTransactionExecuteDate() { 
		return changeTransactionExecuteDate;
	}

	/**
	 * Sets the changeTransactionExecuteDate attribute.
	 * 
	 * @param changeTransactionExecuteDate The changeTransactionExecuteDate to set.
	 * 
	 */
	public void setChangeTransactionExecuteDate(Date changeTransactionExecuteDate) {
		this.changeTransactionExecuteDate = changeTransactionExecuteDate;
	}


	/**
	 * Gets the changeTransactionRecordCount attribute.
	 * 
	 * @return Returns the changeTransactionRecordCount
	 * 
	 */
	public Long getChangeTransactionRecordCount() { 
		return changeTransactionRecordCount;
	}

	/**
	 * Sets the changeTransactionRecordCount attribute.
	 * 
	 * @param changeTransactionRecordCount The changeTransactionRecordCount to set.
	 * 
	 */
	public void setChangeTransactionRecordCount(Long changeTransactionRecordCount) {
		this.changeTransactionRecordCount = changeTransactionRecordCount;
	}


	/**
	 * Gets the campus attribute.
	 * 
	 * @return Returns the campus
	 * 
	 */
	public Campus getCampus() { 
		return campus;
	}

	/**
	 * Sets the campus attribute.
	 * 
	 * @param campus The campus to set.
	 * @deprecated
	 */
	public void setCampus(Campus campus) {
		this.campus = campus;
	}

    /**
     * Gets the building attribute. 
     * @return Returns the building.
     */
    public Building getBuilding() {
        return building;
    }

    /**
     * Sets the building attribute value.
     * @param building The building to set.
     * @deprecated
     */
    public void setBuilding(Building building) {
        this.building = building;
    }

    /**
     * Gets the newBuildingRoom attribute. 
     * @return Returns the newBuildingRoom.
     */
    public Room getNewBuildingRoom() {
        return newBuildingRoom;
    }

    /**
     * Sets the newBuildingRoom attribute value.
     * @param newBuildingRoom The newBuildingRoom to set.
     * @deprecated
     */
    public void setNewBuildingRoom(Room newBuildingRoom) {
        this.newBuildingRoom = newBuildingRoom;
    }

    /**
     * Gets the oldBuildingRoom attribute. 
     * @return Returns the oldBuildingRoom.
     */
    public Room getOldBuildingRoom() {
        return oldBuildingRoom;
    }

    /**
     * Sets the oldBuildingRoom attribute value.
     * @param oldBuildingRoom The oldBuildingRoom to set.
     * @deprecated
     */
    public void setOldBuildingRoom(Room oldBuildingRoom) {
        this.oldBuildingRoom = oldBuildingRoom;
    }

    /**
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap m = new LinkedHashMap();      
        m.put("campusCode", this.campusCode);
        m.put("buildingCode", this.buildingCode);
        m.put("oldBuildingRoomNumber", this.oldBuildingRoomNumber);
        if (this.changeTransactionRequestDate != null) {
            m.put("changeTransactionRequestDate", this.changeTransactionRequestDate.toString());
        }
        return m;
    }
}
