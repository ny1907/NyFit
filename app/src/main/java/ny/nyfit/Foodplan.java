package ny.nyfit;

/**
 * Created by U820319 on 24.11.2016.
 */

public class Foodplan {
    private int planID;
    private int foodID;
    private float amount;

    public Foodplan(int planID, int foodID, float amount){
        this.planID = planID;
        this.foodID = foodID;
        this.amount = amount;
    }

    public int getPlanID(){
        return this.planID;
    }

    public void setPlanID(int planID){
        this.planID = planID;
    }

    public int getFoodID(){
        return this.foodID;
    }

    public void setFoodID(int foodID){
        this.foodID = foodID;
    }

    public float getAmount(){
        return this.amount;
    }

    public void setAmount(float amount){
        this.amount = amount;
    }

}
