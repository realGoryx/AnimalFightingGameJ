import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.*;
import java.util.Scanner;

import static java.lang.Math.floor;


public class GameFrame extends JFrame implements KeyListener, MouseListener {
    private final World world;
    private final int cellSize = 75;
    private final JPanel worldPanel;

    public GameFrame(World world) {
        this.world = world;
        worldPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                world.drawWorld(g2d, cellSize);

            }
        };
        worldPanel.setPreferredSize(new Dimension(1300, 750));
        add(worldPanel);

        setTitle("Szymon Różycki 193390");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        addKeyListener(this);
        addMouseListener(this);
        setFocusable(true);
        requestFocusInWindow();

        startGameLoop();
    }

    private void startGameLoop() {
        new Thread(() -> {
            while (true) {

                worldPanel.addKeyListener(new KeyAdapter() {
                    public void keyPressed(KeyEvent e) {

                        Position humanPos = world.getHumanPos();
                        int x = humanPos.getX();
                        int y = humanPos.getY();

                        if(x == 99 && y == 99) {
                            System.exit(0);
                        }

                        Organism organism = world.getOrganism(x,y);
                        Human human = (Human) organism;

                        if (e.getKeyCode() == KeyEvent.VK_UP) {
                            human.MoveUp();
                        }
                        else if (e.getKeyCode() == KeyEvent.VK_DOWN){
                            human.MoveDown();
                        }
                        else if (e.getKeyCode() == KeyEvent.VK_LEFT){
                            human.MoveLeft();
                        }
                        else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
                            human.MoveRight();
                        }
                        else if (e.getKeyChar() == '1') {
                            world.setChosenAnimal('S');
                            world.writeText("Sheep has been chosen to be added!\n");
                        }
                        else if (e.getKeyChar() == '2') {
                            world.setChosenAnimal('W');
                            world.writeText("Wolf has been chosen to be added!\n");
                        }
                        else if (e.getKeyChar() == '3') {
                            world.setChosenAnimal('T');
                            world.writeText("Turtle has been chosen to be added!\n");
                        }
                        else if (e.getKeyChar() == '4') {
                            world.setChosenAnimal('F');
                            world.writeText("Fox has been chosen to be added!\n");
                        }
                        else if (e.getKeyChar() == '5') {
                            world.setChosenAnimal('A');
                            world.writeText("Antelope has been chosen to be added!\n");
                        }
                        else if (e.getKeyChar() == '6') {
                            world.setChosenAnimal('G');
                            world.writeText("Guarana has been chosen to be added!\n");
                        }
                        else if (e.getKeyChar() == '7') {
                            world.setChosenAnimal('g');
                            world.writeText("Grass has been chosen to be added!\n");
                        }
                        else if (e.getKeyChar() == '8') {
                            world.setChosenAnimal('+');
                            world.writeText("Sosnowsky Hogweed has been chosen to be added!\n");
                        }
                        else if (e.getKeyChar() == '9') {
                            world.setChosenAnimal('s');
                            world.writeText("Sow Thistle has been chosen to be added!\n");
                        }
                        else if (e.getKeyChar() == '0') {
                            world.setChosenAnimal('b');
                            world.writeText("Belladonna has been chosen to be added!\n");
                        }
                        else if (e.getKeyChar() == '-') {
                            world.setChosenAnimal('C');
                            world.writeText("Cybersheep has been chosen to be added!\n");
                        }
                        else if (e.getKeyCode() == KeyEvent.VK_E) {
                            human.specialAbility(human.position.getX(), human.position.getY());
                        }
                        else if (e.getKeyCode() == KeyEvent.VK_S){
                            world.setSaved(true);
                            world.writeText("Game has been saved!\n");
                        }
                        else if (e.getKeyCode() == KeyEvent.VK_L){
                            world.loadGame();
                        }

                        world.makeTurn();
                        repaint();
                        //world.drawWorld((Graphics2D) worldPanel.getGraphics(), cellSize);

                    }
                });

                world.makeTurn();
                repaint();
                world.drawWorld((Graphics2D) worldPanel.getGraphics(), cellSize);
            }
        }).start();
    }

    @Override
    public void keyPressed(KeyEvent e) {

        Position humanPos = world.getHumanPos();
        Human human = new Human(world, humanPos);

        if (e.getKeyCode() == KeyEvent.VK_N) {
            world.startGame();
            repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_M) {
            world.loadGame();
            repaint();
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP){
            human.MoveUp();
            world.makeTurn();
            repaint();
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN){
            human.MoveDown();
            world.makeTurn();
            repaint();
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT){
            human.MoveLeft();
            world.makeTurn();
            repaint();
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            human.MoveRight();
            world.makeTurn();
            repaint();
        }
        else if (e.getKeyChar() == '1') {
            world.setChosenAnimal('S');
            world.writeText("Sheep has been chosen to be added!\n");
        }
        else if (e.getKeyChar() == '2') {
            world.setChosenAnimal('W');
            world.writeText("Wolf has been chosen to be added!\n");
        }
        else if (e.getKeyChar() == '3') {
            world.setChosenAnimal('T');
            world.writeText("Turtle has been chosen to be added!\n");
        }
        else if (e.getKeyChar() == '4') {
            world.setChosenAnimal('F');
            world.writeText("Fox has been chosen to be added!\n");
        }
        else if (e.getKeyChar() == '5') {
            world.setChosenAnimal('A');
            world.writeText("Antelope has been chosen to be added!\n");
        }
        else if (e.getKeyChar() == '6') {
            world.setChosenAnimal('G');
            world.writeText("Guarana has been chosen to be added!\n");
        }
        else if (e.getKeyChar() == '7') {
            world.setChosenAnimal('g');
            world.writeText("Grass has been chosen to be added!\n");
        }
        else if (e.getKeyChar() == '8') {
            world.setChosenAnimal('+');
            world.writeText("Sosnowsky Hogweed has been chosen to be added!\n");
        }
        else if (e.getKeyChar() == '9') {
            world.setChosenAnimal('s');
            world.writeText("Sow Thistle has been chosen to be added!\n");
        }
        else if (e.getKeyChar() == '0') {
            world.setChosenAnimal('b');
            world.writeText("Belladonna has been chosen to be added!\n");
        }
        else if (e.getKeyChar() == '-') {
            world.setChosenAnimal('C');
            world.writeText("Cybersheep has been chosen to be added!\n");
        }
        else if (e.getKeyCode() == KeyEvent.VK_E) {
            human.specialAbility(human.position.getX(), human.position.getY());
        }
        else if (e.getKeyCode() == KeyEvent.VK_S){
            world.setSaved(true);
            world.writeText("Game has been saved!\n");
            repaint();
        }
        else if (e.getKeyCode() == KeyEvent.VK_L){
            world.loadGame();
        }
        else {
            world.makeTurn();
            repaint();
        }


    }

    public void mouseClicked(MouseEvent e) {
        double mouseX = e.getX();
        double mouseY = e.getY();

        mouseX = mouseX / 75;
        mouseY = mouseY / 75;

        double newY = Math.floor(mouseX);
        double newX = Math.floor(mouseY);


        char signThere = world.returnSymbol((int) newX, (int) newY);
        if(signThere == '*' && newX <= 10 && newY <= 10) {
            world.addOrganismToBoard((int) newX, (int) newY, world.getChosenAnimal());
            world.writeText("X" + newX + " X coordinate has been picked" + "\n");
            world.writeText("Y" + newY + " Y coordinate has been picked" + "\n");
            world.writeText("New organism has been added to the board!\n");
        }
        else if(signThere != '*' && signThere != '?' && newX <= 10 && newY <= 10){
            Organism organism = null;
            organism = world.getOrganism((int) newX, (int) newY);
            world.killOrganism(organism, (int) newX, (int) newY);
            world.addOrganismToBoard((int) newX, (int) newY, world.getChosenAnimal());
            world.writeText("X" + newX + " X coordinate has been picked" + "\n");
            world.writeText("Y" + newY + " Y coordinate has been picked" + "\n");
            world.writeText("New organism has been added to the board!\n");
        }

        worldPanel.repaint();
        worldPanel.requestFocusInWindow();
    }




    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Unused
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Unused
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            final int WORLD_HEIGHT = 10;
            final int WORLD_WIDTH = 10;

            Scanner scanner = new Scanner(System.in);
            System.out.println("+-------------------------+");
            System.out.println("|  Animal fighting game   |");
            System.out.println("|        193390           |");
            System.out.println("|                         |");
            System.out.println("|    n - New Game         |");
            System.out.println("|                         |");
            System.out.println("+-------------------------+");

            char key = scanner.next().charAt(0);

            World world;
            if (key == 'n') {
                world = new World(WORLD_HEIGHT, WORLD_WIDTH);
                world.startGame();
            } else {
                throw new IllegalArgumentException("Invalid input");
            }

            GameFrame gameFrame = new GameFrame(world);
        });
    }
}
