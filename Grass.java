public class Grass extends Plant {
    public Grass() {
        this.strength = 0;
        this.initiative = 0;
        this.setSign('g');
        this.setName("Grass");
    }

    public Grass(World world, Position pos) {
        this.strength = 0;
        this.initiative = 0;
        this.setWorld(world);
        this.setSign('g');
        this.setName("Grass");
        this.setPosition(pos.getX(), pos.getY());
    }
}
