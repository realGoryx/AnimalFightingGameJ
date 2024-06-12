import java.util.Random;

public class Fox extends Animal {
    public Fox() {
        this.strength = 3;
        this.initiative = 7;
        this.setSign('F');
        this.setName("Fox");
    }

    public Fox(World world, Position pos) {
        this.strength = 3;
        this.initiative = 7;
        this.setSign('F');
        this.setName("Fox");
        this.setWorld(world);
        this.setPosition(pos.getX(), pos.getY());
    }

    @Override
    protected void action() {
        this.yearsOld += 1;

        int[] helping = new int[4];
        findSafe(helping);
        boolean quit = false;

        while (!quit) {
            int randomPosition = new Random().nextInt(4);

            if (helping[randomPosition] == 1) {
                move(randomPosition);
                quit = true;
            } else if (helping[randomPosition] == 2) {
                // Do nothing
            } else {
                int[] dy = {0, 0, 1, -1};
                int[] dx = {1, -1, 0, 0};

                int nextPosX = this.position.getX() + dx[randomPosition];
                int nextPosY = this.position.getY() + dy[randomPosition];

                if (world.getOrganism(nextPosX, nextPosY).getStrength() > this.getStrength()) {
                    world.writeText("Fox has sensed the danger and stayed in place!\n");
                    return;
                }

                int outcome = collision(world.getOrganism(nextPosX, nextPosY).getPosition());
                if (outcome == 6) {
                    move(randomPosition);
                    break;
                }
            }
        }
    }
}
