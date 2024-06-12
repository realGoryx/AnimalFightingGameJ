public class Wolf extends Animal {
    public Wolf() {
        this.strength = 9;
        this.initiative = 5;
        this.setSign('W');
        this.setName("Wolf");
        this.setWorld(null);
    }

    public Wolf(World world, Position pos) {
        this.strength = 9;
        this.initiative = 5;
        this.setSign('W');
        this.setName("Wolf");
        this.setWorld(world);
        this.setPosition(pos.getX(), pos.getY());
    }
}
