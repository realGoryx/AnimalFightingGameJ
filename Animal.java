import java.util.Random;

public class Animal extends Organism {
    @Override
    protected void action() {
        this.yearsOld += 1;

        int[] helping = new int[4];
        findSafe(helping);
        boolean quit = false;

        Random rand = new Random();

        while (!quit) {
            int randomPosition = rand.nextInt(4);

            if (helping[randomPosition] == 1) {
                move(randomPosition);
                quit = true;
            } else if (helping[randomPosition] == 2) {
                // Out of map!
                // quit = true;
            } else {
                int[] dy = {0, 0, 1 + this.movementSpeed, -1 - this.movementSpeed};
                int[] dx = {1 + this.movementSpeed, -1 - this.movementSpeed, 0, 0};

                int outcome = collision(world.getOrganism(this.position.getX() + dx[randomPosition],
                        this.position.getY() + dy[randomPosition]).getPosition());

                if (outcome == 6) {
                    move(randomPosition);
                    break;
                } else {
                    break;
                }
            }
        }
    }

    @Override
    public int collision(Position attackingPos) {
        Organism defender = world.getOrganism(attackingPos.getX(), attackingPos.getY());

        if (defender != null) {
            if (defender.getSign() == this.getSign() && (this.getYears() > 3) && (defender.getYears() > 3)) {
                int[] helping = new int[8];
                findSafe(helping);
                findSafeTwo(helping, attackingPos);
                int counter = 0;

                for (int i = 0; i < 8; ++i) {
                    if (helping[i] == 1)
                        counter++;
                }

                if (counter == 0) {
                    return 99;
                }

                while (true) {
                    Random rand = new Random();
                    int randomPosition = rand.nextInt(8);

                    if (helping[randomPosition] == 1) {

                        if (randomPosition < 4) {
                            breed(randomPosition, getPosition(), this.getSign());
                            return 99;
                        } else {
                            randomPosition -= 4;
                            breed(randomPosition, attackingPos, this.getSign());
                            return 99;
                        }
                    }
                }
            } else if (defender.getSign() != this.getSign()) {
                if (defender.getSign() == '+' && this.getSign() == 'C') {
                    world.killOrganism(defender, attackingPos.getX(), attackingPos.getY());
                    world.writeText(this.getName() + " has killed " + defender.getName() + "\n");
                    return 6;
                }

                if (defender.specialAttack(this, defender)) {
                    if (defender.getSign() == 'G')
                        return 6;
                    if (defender.getSign() == 'A')
                        return 6;
                    return 5;
                }
                if (this.specialAttack(this, defender)) {
                    return 4;
                }
                if (defender.getStrength() > getStrength()) {
                    world.killOrganism(this, position.getX(), position.getY());
                    world.writeText(defender.getName() + " has killed " + this.getName() + "\n");
                    return 1;
                } else if (defender.getStrength() <= getStrength()) {
                    world.killOrganism(defender, attackingPos.getX(), attackingPos.getY());
                    world.writeText(this.getName() + " has killed " + defender.getName() + "\n");
                    return 6;
                }
            }
        }
        return 0;
    }

    public void move(int direction) {
        int posX = this.position.getX();
        int posY = this.position.getY();

        int movementSpeed = this.movementSpeed;

        int[] dy = {0, 0, 1 + movementSpeed, -1 - movementSpeed};
        int[] dx = {1 + movementSpeed, -1 - movementSpeed, 0, 0};

        int direct = direction;

        this.position.setX(posX + dx[direct]);
        this.position.setY(posY + dy[direct]);

        world.helpAnimalMove(this, posX + dx[direct], posY + dy[direct]);
        world.eraseOrganism(posX, posY);

        world.writeText(this.getName() + " has moved to " + this.position.getX() + ", " + this.position.getY() + "\n");
    }

    private void breed(int direction, Position pos, char sign) {
        int posX = this.position.getX();
        int posY = this.position.getY();

        int direct = direction;

        int[] dy = {0, 0, 1, -1};
        int[] dx = {1, -1, 0, 0};

        int newPosX = posX + dx[direct];
        int newPosY = posY + dy[direct];

        if (newPosX >= world.getHeight() || newPosX < 0 || newPosY >= world.getWidth() || newPosY < 0)
            return;
        else if (world.returnSymbol(newPosX, newPosY) != '*')
            return;

        world.addOrganismToBoard(newPosY, newPosX, sign);
        world.writeText(this.getName() + " has been bred!\n");
    }

    public void findSafe(int[] helpArray) {
        int[] dy = {0, 0, 1 + this.movementSpeed, -1 - this.movementSpeed};
        int[] dx = {1 + this.movementSpeed, -1 - this.movementSpeed, 0, 0};

        int posX = position.getX();
        int posY = position.getY();

        for (int i = 0; i < 4; ++i) {
            if (world.returnSymbol(posX + dx[i], posY + dy[i]) == '*')
                helpArray[i] = 1;
            else if (world.returnSymbol(posX + dx[i], posY + dy[i]) == '?')
                helpArray[i] = 2;
        }
    }

    public void findSafeTwo(int[] helpArray, Position attackingPos) {
        int[] dy = {0, 0, 1, -1};
        int[] dx = {1, -1, 0, 0};

        int posX = attackingPos.getX();
        int posY = attackingPos.getY();

        for (int i = 0; i < 4; ++i) {
            if (world.returnSymbol(posX + dx[i], posY + dy[i]) == '*')
                helpArray[i] = 1;
            else if (world.returnSymbol(posX + dx[i], posY + dy[i]) == '?')
                helpArray[i] = 2;
        }
    }
}
