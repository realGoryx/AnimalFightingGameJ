import java.util.Random;

public class SosnowskyHogweed extends Plant {

    public SosnowskyHogweed() {
        this.strength = 10;
        this.initiative = 0;
        this.setSign('+');
        this.setName("Sosnowsky");
    }

    public SosnowskyHogweed(World world, Position pos) {
        this.strength = 10;
        this.initiative = 0;
        this.setSign('+');
        this.setName("Sosnowsky");
        this.setWorld(world);
        this.setPosition(pos.getX(), pos.getY());
    }

    @Override
    public void action() {
        int[] dy = {0, 0, -1, 1};
        int[] dx = {1, -1, 0, 0};

        Organism prey = null;

        char[] animal = {'T', 'F', '@', 'W', 'S', 'A'};

        for (int i = 0; i < 4; ++i) {
            char testSign = world.returnSymbol(getPosition().getX() + dx[i], getPosition().getY() + dy[i]);

            for (int j = 0; j < 6; ++j) {
                if (testSign == animal[j]) {
                    int posX = getPosition().getX() + dx[i];
                    int posY = getPosition().getY() + dy[i];

                    prey = world.getOrganism(posX, posY);
                    world.killOrganism(prey, posX, posY);
                    world.writeText("Sosnowsky Hogweed has poisoned and killed " + prey.getName() + "\n");
                    break;
                }
            }
        }

        super.action();
    }
}
