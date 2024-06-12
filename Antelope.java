import java.util.Random;

public class Antelope extends Animal {
    public Antelope() {
        this.strength = 4;
        this.initiative = 4;
        this.movementSpeed = 1;
        this.setSign('A');
        this.setName("Antelope");
    }

    public Antelope(World world, Position pos) {
        this.strength = 4;
        this.initiative = 4;
        this.movementSpeed = 1;
        this.setSign('A');
        this.setName("Antelope");
        this.setWorld(world);
        this.setPosition(pos.getX(), pos.getY());
    }

    @Override
    public boolean specialAttack(Organism attack, Organism defend) {
        Random rand = new Random();
        int dodgeChance = rand.nextInt(4);

        if (dodgeChance < 2) {
            return false;
        } else {
            int[] helping = new int[4];
            findSafe(helping);

            int counter = 0;

            for (int i = 0; i < 4; ++i) {
                if (helping[i] == 1)
                    counter++;
            }

            if (counter == 0)
                return false;
            else {
                while (true) {
                    int randomPosition = rand.nextInt(4);

                    if (helping[randomPosition] == 1) {
                        this.setMovementSpeed(0);
                        move(randomPosition);
                        this.setMovementSpeed(1);
                        this.setMoved(true);
                        world.writeText("Antelope has escaped from the fight!\n");
                        break;
                    }
                }

                return true;
            }
        }
    }
}
