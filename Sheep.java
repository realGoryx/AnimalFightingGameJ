public class Sheep extends Animal {

    public Sheep() {
        this.strength = 4;
        this.initiative = 4;
        this.setSign('S');
        this.setName("Sheep");
    }

    public Sheep(World world, Position pos) {
        this.strength = 4;
        this.initiative = 4;
        this.setSign('S');
        this.setName("Sheep");
        this.setWorld(world);
        this.setPosition(pos.getX(), pos.getY());
    }
}
