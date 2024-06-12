import java.util.Scanner;

public class Human extends Animal {
    private int roundsAfterSpecial = 0;
    private boolean usedSpecial = false;

    public Human(World world, Position pos) {
        this.strength = 5;
        this.initiative = 4;
        this.setName("Human");
        this.setWorld(world);
        this.setPosition(pos.getX(), pos.getY());
        this.setSign('@');
    }

    public void MoveUp(){
        int checkCollision;
        setMoved(true);


        if (world.returnSymbol(this.getPosition().getX() - 1, this.getPosition().getY()) == '?')
            return;
        else if (world.returnSymbol(this.getPosition().getX() - 1, this.getPosition().getY()) == '*') {
            countSpecial();
            eraseTraces(this.getPosition().getX() - 1, this.getPosition().getY());
            return;
        } else {
            checkCollision = super.collision(world.getOrganism(this.position.getX() - 1, this.position.getY()).getPosition());
            if (checkCollision == 6) {
                eraseTraces(this.getPosition().getX() - 1, this.getPosition().getY());
            }
            return;
        }
    }

    public void MoveDown(){
        int checkCollision;
        if (world.returnSymbol(this.getPosition().getX() + 1, this.getPosition().getY()) == '?')
            return;
        else if (world.returnSymbol(this.getPosition().getX() + 1, this.getPosition().getY()) == '*') {
            countSpecial();
            eraseTraces(this.getPosition().getX() + 1, this.getPosition().getY());
            return;
        } else {
            checkCollision = super.collision(world.getOrganism(this.position.getX() + 1, this.position.getY()).getPosition());
            if (checkCollision == 6) {
                eraseTraces(this.getPosition().getX() + 1, this.getPosition().getY());
            }
            return;
        }
    }

    public void MoveLeft(){
        int checkCollision;
        if (world.returnSymbol(this.getPosition().getX(), this.getPosition().getY() - 1) == '?')
            return;
        else if (world.returnSymbol(this.getPosition().getX(), this.getPosition().getY() - 1) == '*') {
            countSpecial();
            eraseTraces(this.getPosition().getX(), this.getPosition().getY() - 1);
            return;
        } else {
            checkCollision = super.collision(world.getOrganism(this.position.getX(), this.position.getY() - 1).getPosition());
            if (checkCollision == 6) {
                eraseTraces(this.getPosition().getX(), this.getPosition().getY() - 1);
            }
            return;
        }
    }

    public void MoveRight(){
        int checkCollision;
        if (world.returnSymbol(this.getPosition().getX(), this.getPosition().getY() + 1) == '?')
            return;
        else if (world.returnSymbol(this.getPosition().getX(), this.getPosition().getY() + 1) == '*') {
            countSpecial();
            eraseTraces(this.getPosition().getX(), this.getPosition().getY() + 1);
            return;
        } else {
            checkCollision = super.collision(world.getOrganism(this.position.getX(), this.position.getY() + 1).getPosition());
            if (checkCollision == 6) {
                eraseTraces(this.getPosition().getX(), this.getPosition().getY() + 1);
            }
            return;
        }
    }


    @Override
    public void action() {
        int posX = position.getX();
        int posY = position.getY();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            char arrow = scanner.next().charAt(0);
            int checkCollision;

            switch (arrow) {
                case 'w': // Up
                    if (world.returnSymbol(this.getPosition().getX() - 1, this.getPosition().getY()) == '?')
                        break;
                    else if (world.returnSymbol(this.getPosition().getX() - 1, this.getPosition().getY()) == '*') {
                        countSpecial();
                        eraseTraces(this.getPosition().getX() - 1, this.getPosition().getY());
                        return;
                    } else {
                        checkCollision = super.collision(world.getOrganism(this.position.getX() - 1, this.position.getY()).getPosition());
                        if (checkCollision == 6) {
                            eraseTraces(this.getPosition().getX() - 1, this.getPosition().getY());
                        }
                        return;
                    }
                case 's': // Down
                    if (world.returnSymbol(this.getPosition().getX() + 1, this.getPosition().getY()) == '?')
                        break;
                    else if (world.returnSymbol(this.getPosition().getX() + 1, this.getPosition().getY()) == '*') {
                        countSpecial();
                        eraseTraces(this.getPosition().getX() + 1, this.getPosition().getY());
                        return;
                    } else {
                        checkCollision = super.collision(world.getOrganism(this.position.getX() + 1, this.position.getY()).getPosition());
                        if (checkCollision == 6) {
                            eraseTraces(this.getPosition().getX() + 1, this.getPosition().getY());
                        }
                        return;
                    }
                case 'a': // Left
                    if (world.returnSymbol(this.getPosition().getX(), this.getPosition().getY() - 1) == '?')
                        break;
                    else if (world.returnSymbol(this.getPosition().getX(), this.getPosition().getY() - 1) == '*') {
                        countSpecial();
                        eraseTraces(this.getPosition().getX(), this.getPosition().getY() - 1);
                        return;
                    } else {
                        checkCollision = super.collision(world.getOrganism(this.position.getX(), this.position.getY() - 1).getPosition());
                        if (checkCollision == 6) {
                            eraseTraces(this.getPosition().getX(), this.getPosition().getY() - 1);
                        }
                        return;
                    }
                case 'd': // Right
                    if (world.returnSymbol(this.getPosition().getX(), this.getPosition().getY() + 1) == '?')
                        break;
                    else if (world.returnSymbol(this.getPosition().getX(), this.getPosition().getY() + 1) == '*') {
                        countSpecial();
                        eraseTraces(this.getPosition().getX(), this.getPosition().getY() + 1);
                        return;
                    } else {
                        checkCollision = super.collision(world.getOrganism(this.position.getX(), this.position.getY() + 1).getPosition());
                        if (checkCollision == 6) {
                            eraseTraces(this.getPosition().getX(), this.getPosition().getY() + 1);
                        }
                        return;
                    }
                case 'e':
                    if (!this.usedSpecial)
                        specialAbility(this.getPosition().getX(), this.getPosition().getY());
                    return;
                case 'q':
                    world.setSaved(true);
                    return;
                case 'l':
                    world.loadGame();
                    return;
                default:
                    break;
            }
        }
    }

    public void countSpecial() {
        if (this.usedSpecial) {
            this.roundsAfterSpecial++;
            if (this.roundsAfterSpecial < 5)
                world.writeText((5 - roundsAfterSpecial) + " more rounds for Human's special ability to regenerate\n");

            if (this.roundsAfterSpecial == 5) {
                world.writeText("Human special ability is ready to be used!\n");
                this.roundsAfterSpecial = -1;
                this.usedSpecial = false;
            }
        }
    }

    public void eraseTraces(int h, int w) {
        world.eraseOrganism(this.getPosition().getX(), this.getPosition().getY());
        world.helpAnimalMove(this, h, w);
        this.position.setX(h);
        this.position.setY(w);
        world.writeText("Human has moved to " + this.position.getX() + ", " + this.position.getY() + "\n");
    }

    public void specialAbility(int h, int w) {

        if (this.usedSpecial)
            return;

        int[] dx = {0, 0, 1, 1, 1, -1, -1, -1};
        int[] dy = {1, -1, 0, 1, -1, 0, 1, -1};

        world.writeText("Special ability has been used!\n");

        for (int i = 0; i < 8; ++i) {
            char symbol = world.returnSymbol(h + dx[i], w + dy[i]);
            Organism org = null;
            if (symbol != '?')
                org = world.getOrganism(h + dx[i], w + dy[i]);
            if (org != null) {
                world.killOrganism(org, h + dx[i], w + dy[i]);
                world.writeText(org.getName() + " has been killed by Human's special ability!\n");
            }
        }

        this.usedSpecial = true;
    }

    public int getRoundsSpecial() {
        return roundsAfterSpecial;
    }

    public void setRoundsSpecial(int rounds) {
        this.roundsAfterSpecial = rounds;
    }

    public boolean getUsedSpecial() {
        return usedSpecial;
    }

    public void setUsedSpecial(boolean used) {
        this.usedSpecial = used;
    }
}
