import java.util.Random;

public class SowThistle extends Plant {

    public SowThistle() {
        this.strength = 0;
        this.initiative = 0;
        this.setSign('s');
        this.setName("Sow Thistle");
    }

    public SowThistle(World world, Position pos) {
        this.strength = 0;
        this.initiative = 0;
        this.setSign('s');
        this.setName("Sow Thistle");
        this.setWorld(world);
        this.setPosition(pos.getX(), pos.getY());
    }

    @Override
    public void action() {
        this.yearsOld -= 2;

        for (int i = 0; i < 3; ++i) {
            super.action();
        }
    }
}
