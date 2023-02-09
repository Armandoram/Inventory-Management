package ProjectC482.Model;

/**
 * @author
 * Armando Ramirez
 * Student ID: 010095862
 */

/** Outsourced class for he Outsourced object that extends Part*/
public class OutSourced extends Parts {
    private String companyName;


    public OutSourced(int partID, int stock, int min, int max, String name, double partCost, String companyName) {
        super(partID, stock, min, max, name, partCost);

        this.companyName = companyName;
    }
   /** FUTURE ENHANCEMENT (Gets the Company Name.)
     * In a future update, being able to transfer information through a website by being given an option to copy and paste a link/code.  Also get the name of the company.*/
    public String getCompanyName() {
        return companyName;
    }

    /** Sets the Company's name. */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}