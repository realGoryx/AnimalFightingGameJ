import java.util.ArrayList;

public class Cybersheep extends Animal {
    public Cybersheep() {
        this.strength = 11;
        this.initiative = 4;
        this.sign = 'C';
        this.name = "Cybersheep";
    }

    public Cybersheep(World world, Position pos) {
        this.strength = 11;
        this.initiative = 4;
        this.sign = 'C';
        this.name = "Cybersheep";
        this.world = world;
        this.position = pos;
    }

    @Override
    public void action() {
        ArrayList<Position> sosnowskyPos = new ArrayList<>();
        int sosCount = 0;

        Position cyberPos = getPosition();
        int cyberX = cyberPos.getX();
        int cyberY = cyberPos.getY();

        sosCount = world.countSos();
        world.getSosnowskysPosition(sosnowskyPos);

        int minDistance = Integer.MAX_VALUE;
        int closestX = 0;
        int closestY = 0;

        for (Position pos : sosnowskyPos) {
            double distance = Math.sqrt(Math.pow(cyberX - pos.getX(), 2) + Math.pow(cyberY - pos.getY(), 2));
            if (distance < minDistance) {
                minDistance = (int) distance;
                closestX = pos.getX();
                closestY = pos.getY();
            }
        }

        int outcome;

        if (sosCount > 0) {
            if (cyberX != closestX) {
                if (cyberX > closestX) {
                    if (world.returnSymbol(cyberX - 1, cyberY) == '*')
                        move(1);
                    else {
                        Position attacking = new Position(cyberX - 1, cyberY);
                        outcome = collision(attacking);

                        if (outcome == 6)
                            move(1);
                    }
                }
                if (cyberX < closestX) {
                    if (world.returnSymbol(cyberX + 1, cyberY) == '*')
                        move(0);
                    else {
                        Position attacking = new Position(cyberX + 1, cyberY);
                        outcome = collision(attacking);

                        if (outcome == 6)
                            move(0);
                    }
                }
            } else if (cyberY != closestY) {
                if (cyberY > closestY) {
                    if (world.returnSymbol(cyberX, cyberY - 1) == '*')
                        move(3);
                    else {
                        Position attacking = new Position(cyberX, cyberY - 1);
                        outcome = collision(attacking);

                        if (outcome == 6)
                            move(3);
                    }
                }
                if (cyberY < closestY) {
                    if (world.returnSymbol(cyberX, cyberY + 1) == '*')
                        move(2);
                    else {
                        Position attacking = new Position(cyberX, cyberY + 1);
                        outcome = collision(attacking);

                        if (outcome == 6)
                            move(2);
                    }
                }
            }
        } else {
            super.action();
        }
    }
}
