package Model;

public class Tower {
    private User owner;

    public Tower(User owner, boolean middle){
        this.owner = owner;
        damage = 500*owner.getLevel();
        if(middle){
            hitPoint = 3600*owner.getLevel();
        }else {
            hitPoint = 2500*owner.getLevel();
        }
    }

    private int hitPoint;

    private int damage;

    public int getHitPoint() {
        return hitPoint;
    }

    public void setHitPoint(int hitPoint) {
        this.hitPoint = hitPoint;
    }

    public int getDamage() {
        return damage;
    }

    public void takeDamage(int damage){
        hitPoint -= damage;
    }
}
