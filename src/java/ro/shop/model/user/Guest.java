package ro.shop.model.user;

import ro.shop.model.User;

public class Guest extends User {
    private int trialDays;

    public Guest(int id, String email, String password, String fullName, String billAddress, int trialDays) {
        super(id, email, password, fullName, billAddress);
        this.trialDays = trialDays;
    }

    public Guest(String email, String password, String fullName, String billAddress, int trialDays) {
        super(email, password, fullName, billAddress);
        this.trialDays = trialDays;
    }

    public int getTrialDays() {
        return trialDays;
    }

    public void setTrialDays(int trialDays) {
        this.trialDays = trialDays;
    }

    @Override
    public int compareTo(User guest){
        if(guest.getId()==this.getId()){
            return 0;
        }else if(guest.getId()<this.getId()){
            return 1;
        }
        return -1;
    }

    @Override
    public boolean equals(Object obj){
        Guest guest = (Guest) obj;
        return guest.getId()==this.getId();
    }

    @Override
    public String toString(){
        String prod = super.toString();
        return prod+","+this.trialDays;
    }
}
