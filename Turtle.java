import java.util.Random;

public class Turtle extends Animal {

    public Turtle() {
        this.strength = 2;
        this.initiative = 1;
        this.setSign('T');
        this.setName("Turtle");
    }

    public Turtle(World world, Position pos) {
        this.strength = 2;
        this.initiative = 1;
        this.setSign('T');
        this.setName("Turtle");
        this.setWorld(world);
        this.setPosition(pos.getX(), pos.getY());
    }

    @Override
    public void action() {
        this.yearsOld += 1;

        Random rand = new Random();
        int chance = rand.nextInt(4);

        if (chance < 2) {
            super.action();
        }
    }

    @Override
    public boolean specialAttack(Organism attack, Organism defend) {
        if (this == defend && attack.getStrength() > 5) {
            return false;
        } else if (this == defend && attack.getStrength() < 5) {
            world.writeText("Turtle has blocked the attack!");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        } else {
            return false;
        }
    }
}
