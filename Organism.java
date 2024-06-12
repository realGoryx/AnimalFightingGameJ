public abstract class Organism {
    protected int strength = 0;
    protected int initiative = 0;
    protected int movementSpeed = 0;
    protected int yearsOld = 0;
    protected Position position = new Position();
    protected char sign;
    protected String name;
    protected World world;
    protected boolean movedFlag = false;

    Organism(){

    }

    Organism(int strength, int initiative, Position pos, World world){
        this.strength = strength;
        this.initiative = initiative;
        this.position = pos;
        this.world = world;
    }

    protected abstract void action();
    protected abstract int collision(Position attacking);


    public boolean specialAttack(Organism attack, Organism defend){
        return false;
    }

    public char getSign(){
        return sign;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public void setSign(char sign) {
        this.sign = sign;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(int x, int y) {
        this.position.setX(x);
        this.position.setY(y);
    }

    public int getYears() {
        return yearsOld;
    }

    public void setYears(int yearsOld) {
        this.yearsOld = yearsOld;
    }

    public boolean getMoved() {
        return movedFlag;
    }

    public void setMoved(boolean movedFlag) {
        this.movedFlag = movedFlag;
    }

    public int getMovementSpeed() {
        return movementSpeed;
    }

    public void setMovementSpeed(int movementSpeed) {
        this.movementSpeed = movementSpeed;
    }
}
