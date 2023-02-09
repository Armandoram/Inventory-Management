package ProjectC482.Model;
/**
 * @author
 * Armando Ramirez
 * Student ID: 010095862
 */
/** Creates an InHouse class for the InHouse object which extends Part */
public class InHouse extends Parts {
    private int machineID;

    public InHouse(int partID, int stock, int min, int max, String name, double partCost, int machineID) {
        super(partID, stock, min, max, name, partCost);

        this.machineID = machineID;
    }
    /** @return the machineID
     * */
    /** FUTURE ENHANCEMENT
     * In case you need to be more specific, being able to get more than 1 ID at a time or giving more than one ID to s single machine.
     */
    public int getMachineID() {
        return machineID;
    }

    /**  The MachineID that is to be set.*/
    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }
}
