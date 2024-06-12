import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.io.File;
import java.util.Scanner;

public class World {
    private static final int DEFAULT_HEIGHT = 20;
    private static final int DEFAULT_WIDTH = 20;

    private char chosenAnimal = 'S';

    private int height;
    private int width;
    private List<List<Organism>> board;
    private List<Organism> aliveOrganisms;
    private boolean saved;

    public World() {
        this(DEFAULT_HEIGHT, DEFAULT_WIDTH);
    }

    public World(int height, int width) {
        this.height = height;
        this.width = width;
        this.board = new ArrayList<>(height);
        for (int i = 0; i < height; i++) {
            List<Organism> row = new ArrayList<>(width);
            for (int j = 0; j < width; j++) {
                row.add(null);
            }
            this.board.add(row);
        }
        this.aliveOrganisms = new ArrayList<>();
    }

    public static void clearScreen() {
        for (int i = 0; i < 25; i++) {
            System.out.println();
        }
    }

    public void drawWorld(Graphics2D g2d, int cellSize) {

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, 1500, 1000);

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                Organism organism = board.get(i).get(j);
                if (organism == null)
                    g2d.drawImage(OrganismImages.getImage('.'), j * cellSize, i * cellSize, cellSize, cellSize, null);
                else {
                    g2d.drawImage(OrganismImages.getImage(organism.getSign()), j * cellSize, i * cellSize, cellSize, cellSize, null);
                    g2d.setColor(Color.GREEN);
                    g2d.setFont(new Font("Palatino Linotype", Font.BOLD, 12));
                    g2d.drawString(String.valueOf(organism.getStrength()), (j+1) * cellSize - 60, (i+1) * cellSize - 15);
                    g2d.drawString(String.valueOf(organism.getInitiative()), (j+1) * cellSize - 20, (i+1) * cellSize - 15);
                    g2d.setColor(Color.LIGHT_GRAY);
                    g2d.setFont(new Font("Palatino Linotype", Font.BOLD, 8));
                    g2d.drawString(organism.getName(), (j+1) * cellSize - 65, (i+1) * cellSize - 60);
                }
            }
        }

        g2d.setColor(Color.RED);
        g2d.setFont(new Font("Palatino Linotype", Font.BOLD, 40));
        g2d.drawString("GAME INFO - LOGS", 800, 40);

        addingInfo(g2d);
        readText(g2d);
    }


    public void addingInfo(Graphics2D g2d){
        g2d.setColor(Color.GREEN);
        g2d.setFont(new Font("Palatino Linotype", Font.BOLD, 20));


        g2d.drawString("1 - Sheep", 800, 600);
        g2d.drawString("2 - Wolf", 800, 625);
        g2d.drawString("3 - Turtle", 800, 650);
        g2d.drawString("4 - Fox", 800, 675);
        g2d.drawString("5 - Antelope", 800, 700);
        g2d.drawString("6 - Guarana", 800, 725);
        g2d.drawString("7 - Grass", 1000, 625);
        g2d.drawString("8 - Sosnowsky Hogweed", 1000, 650);
        g2d.drawString("9 - Sow Thistle", 1000, 675);
        g2d.drawString("0 - Belladona", 1000, 700);
        g2d.drawString("- - Cybersheep", 1000, 725);
    }

    public int countSos() {
        int counter = 0;
        char sign;
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                sign = returnSymbol(i, j);
                if (sign == '+')
                    counter++;
            }
        }
        return counter;
    }

    public void getSosnowskysPosition(List<Position> posVec) {
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                if (returnSymbol(i, j) == '+') {
                    posVec.add(new Position(i, j));
                }
            }
        }
    }


    public Position getHumanPos(){
        Position humanPos = null;
        int newX = 0;
        int newY = 0;

        for(int i = 0; i < height; ++i){
            for(int j = 0; j < width; ++j){
                if (returnSymbol(i, j) == '@'){
                    newX = i;
                    newY = j;
                    return new Position(newX, newY);
                }
                if (i == height - 1 && j == width - 1){
                    return new Position(99,99);
                }
            }
        }



        return new Position(newX, newY);
    }

    public void placeRandomly(Organism organism) {
        Position pos = checkFreePosition();
        if (board.get(pos.getX()).get(pos.getY()) == null) {
            organism.setWorld(this);
            organism.setPosition(pos.getX(), pos.getY());
            board.get(pos.getX()).set(pos.getY(), organism);
            aliveOrganisms.add(organism);
        }
    }

    public Position checkFreePosition() {
        Random rand = new Random();
        int i = height * width;
        while (i-- > 0) {
            int posX = rand.nextInt(height);
            int posY = rand.nextInt(width);
            if (board.get(posX).get(posY) == null) {
                return new Position(posX, posY);
            }
        }
        return new Position(1, 1);
    }

    public void startGame() {
        Sheep sheep = new Sheep();
        Sheep sheep2 = new Sheep();

        Wolf wolf = new Wolf();
        Wolf wolf2 = new Wolf();
        Wolf wolf3 = new Wolf();
        Wolf wolf4 = new Wolf();

        Turtle turtle = new Turtle();
        Turtle turtle2 = new Turtle();

        Antelope antelope = new Antelope();

        Human human = new Human(this, new Position(3, 3));

        Fox fox = new Fox();
        Fox fox2 = new Fox();

        Belladonna bella = new Belladonna();

        Grass grass = new Grass();
        Grass grass2 = new Grass();

        SowThistle sow = new SowThistle();

        Guarana guarana = new Guarana();
        Guarana guarana2 = new Guarana();

        SosnowskyHogweed sos = new SosnowskyHogweed();
        SosnowskyHogweed sos2 = new SosnowskyHogweed();
        SosnowskyHogweed sos3 = new SosnowskyHogweed();

        Cybersheep cybersheep = new Cybersheep();
        Cybersheep cybersheep2 = new Cybersheep();

        placeRandomly(human);

        //human.setWorld(this);
        //human.setPosition(2, 2);
       // board.get(human.position.getX()).set(human.position.getY(), human);
       // aliveOrganisms.add(human);

        placeRandomly(cybersheep);
        placeRandomly(cybersheep2);

        placeRandomly(sheep);
        placeRandomly(sheep2);

        placeRandomly(wolf);
        placeRandomly(wolf2);
        placeRandomly(wolf3);
        placeRandomly(wolf4);

        placeRandomly(turtle);
        placeRandomly(turtle2);

        placeRandomly(antelope);

        placeRandomly(fox);
        placeRandomly(fox2);

        placeRandomly(bella);

        placeRandomly(grass);
        placeRandomly(grass2);

        placeRandomly(sow);

        placeRandomly(guarana);
        placeRandomly(guarana2);

        placeRandomly(sos);
        placeRandomly(sos2);
        placeRandomly(sos3);

        resetText();
    }

    public void addOrganismToBoard(int posX, int posY, char sign) {
        Organism organism = null;

        switch (sign) {
            case 'W':
                organism = new Wolf();
                break;
            case 'A':
                organism = new Antelope();
                break;
            case 'T':
                organism = new Turtle();
                break;
            case 'S':
                organism = new Sheep();
                break;
            case 'g':
                organism = new Grass();
                break;
            case 's':
                organism = new SowThistle();
                break;
            case '+':
                organism = new SosnowskyHogweed();
                break;
            case 'G':
                organism = new Guarana();
                break;
            case 'b':
                organism = new Belladonna();
                break;
            case 'F':
                organism = new Fox();
                break;
            case 'C':
                organism = new Cybersheep();
                break;
        }

        if (organism != null) {
            organism.setWorld(this);
            organism.setPosition(posX, posY);
            board.get(posX).set(posY, organism);
            aliveOrganisms.add(organism);
        }
    }

    public void makeTurn() {
        if (checkEndGame()) {
            endGame();
        }

        for (int i = 0; i < aliveOrganisms.size(); ++i) {
            Organism org = aliveOrganisms.get(i);

            if (org.getMoved()) {
                continue;
            }

            int maxIndex = i;

            for (int j = i + 1; j < aliveOrganisms.size(); ++j) {
                Organism currentOrg = aliveOrganisms.get(j);

                if (currentOrg.getMoved()) {
                    continue;
                }

                if (currentOrg.getSign() == '@'){
                    currentOrg.setMoved(true);
                }

                if (currentOrg.getInitiative() > aliveOrganisms.get(maxIndex).getInitiative() ||
                        (currentOrg.getInitiative() == aliveOrganisms.get(maxIndex).getInitiative() &&
                                currentOrg.getYears() > aliveOrganisms.get(maxIndex).getYears())) {
                    maxIndex = j;
                }
            }

            if (maxIndex != i) {
                Collections.swap(aliveOrganisms, i, maxIndex);
            }

            org.action();
            org.setMoved(true);
        }

        for (Organism organism : aliveOrganisms) {
            organism.setMoved(false);
        }

        if (saved) {
            saveGameStatus();
            saved = false;
            writeText("Game state saved!\n");
        }
    }


    public void setSaved(boolean save) {
        this.saved = save;
    }

    public boolean returnSaved() {
        return saved;
    }

    public void eraseOrganism(int posX, int posY) {
        board.get(posX).set(posY, null);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public char getChosenAnimal(){ return chosenAnimal;}

    public void setChosenAnimal(char chosen){this.chosenAnimal = chosen;}

    public char returnSymbol(int h, int w) {
        if (h < this.height && h >= 0) {
            if (w < this.width && w >= 0) {
                if (board.get(h).get(w) == null) {
                    return '*';
                } else {
                    return board.get(h).get(w).getSign();
                }
            } else {
                return '?';
            }
        } else {
            return '?';
        }
    }

    public Organism getOrganism(int h, int w) {
        return board.get(h).get(w);
    }


    public void killOrganism(Organism organism, int h, int w) {
        eraseOrganism(h, w);
        board.get(h).set(w,null);
        aliveOrganisms.remove(organism);
    }

    public void writeText(String text) {
        try (FileWriter writer = new FileWriter("Log.txt", true)) {
            writer.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readText(Graphics2D g2d) {

        int cordCheck = 100;
        g2d.setColor(Color.WHITE); // Set text color
        g2d.setFont(new Font("Palatino Linotype", Font.PLAIN, 20)); // Set font

        try (BufferedReader reader = new BufferedReader(new FileReader("Log.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                g2d.drawString(line, 825, cordCheck);
                cordCheck += 25;

                if(cordCheck > 570)
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        resetText();
    }

    public void resetText() {
        try (FileWriter writer = new FileWriter("Log.txt", false)) {
            ;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void endGame() {
        endText();
        System.exit(0);
    }

    public void endText() {
        resetText();
        writeText("Game Over - Human died!\n");
    }

    public boolean checkEndGame() {
        for (Organism organism : aliveOrganisms) {
            if (organism.getName().equals("Human")) {
                return false;
            }
        }
        return true;
    }

    public void helpAnimalMove(Organism organism, int h, int w) {
        organism.setWorld(this);
        organism.setPosition(h, w);
        board.get(h).set(w, organism);
    }

    public void saveGameStatus() {
        try (FileWriter writer = new FileWriter("game.txt")) {
            writer.write(height + " " + width + "\n");
            writer.write(aliveOrganisms.size() + "\n");
            for (Organism organism : aliveOrganisms) {
                Position position = organism.getPosition();
                writer.write(organism.getSign() + " " + position.getX() + " " + position.getY() + " ");
                writer.write(organism.getStrength() + " " + organism.getInitiative() + " " + organism.getYears() + " ");
                if (organism.getSign() == '@') {
                    writer.write(((Human) organism).getRoundsSpecial() + " ");
                    writer.write(((Human) organism).getUsedSpecial() + " ");
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadGame() {
        try {
            File file = new File("game.txt");
            Scanner scanner = new Scanner(file);
            int height = scanner.nextInt();
            int width = scanner.nextInt();
            int numOrganisms = scanner.nextInt();


            this.board = new ArrayList<>(height);
            for(int i=0; i<height ;i++){
                ArrayList<Organism> a_height = new ArrayList<>(width);
                for(int j=0; j<width;j++){
                    a_height.add(null);
                }
                board.add(a_height);
            }
            this.height = height;
            this.width = width;
            aliveOrganisms.clear();

            for (int i = 0; i < numOrganisms; i++) {
                char symbol = scanner.next().charAt(0);
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                int strength = scanner.nextInt();
                int initiative = scanner.nextInt();
                int age = scanner.nextInt();
                Organism createOrganism;

                switch (symbol) {
                    case '@' -> {
                        createOrganism = new Human(this, new Position(x, y));
                        ((Human) createOrganism).setRoundsSpecial(scanner.nextInt());
                        ((Human) createOrganism).setUsedSpecial(scanner.nextBoolean());
                    }
                    case 'W' -> createOrganism = new Wolf(this, new Position(x, y));
                    case 'A' -> createOrganism = new Antelope(this, new Position(x, y));
                    case 'b' -> createOrganism = new Belladonna(this, new Position(x, y));
                    case 'F' -> createOrganism = new Fox(this, new Position(x, y));
                    case 'g' -> createOrganism = new Grass(this, new Position(x, y));
                    case 'G' -> createOrganism = new Guarana(this, new Position(x, y));
                    case 'S' -> createOrganism = new Sheep(this, new Position(x, y));
                    case '+' ->
                            createOrganism = new SosnowskyHogweed(this, new Position(x, y));
                    case 's' -> createOrganism = new SowThistle(this, new Position(x, y));
                    case 'T' -> createOrganism = new Turtle(this, new Position(x, y));
                    default -> {
                        continue;
                    }
                }
                createOrganism.setStrength(strength);
                createOrganism.setInitiative(initiative);
                createOrganism.setYears(age);
                createOrganism.setMoved(false);
                addOrganismToBoard(x, y, createOrganism.getSign());
                aliveOrganisms.add(createOrganism);

                if(symbol == '@') {
                    board.get(createOrganism.position.getX()).set(createOrganism.position.getY(), createOrganism);
                }
            }
            scanner.close();
            Logs.AddLogToRead("Game state loaded");
        } catch (IOException e) {
            Logs.AddLogToRead("Error. Could not open the file");
        }
    }

}