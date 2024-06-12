import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class OrganismImages {
    private static final Map<Character, Image> organismImages = new HashMap<>();

    static {
        organismImages.put('S', loadImage("Sheep.png"));
        organismImages.put('T', loadImage("Turtle.png"));
        organismImages.put('@', loadImage("Bezi.png"));
        organismImages.put('g', loadImage("Grass.png"));
        organismImages.put('b', loadImage("Belladonna.png"));
        organismImages.put('+', loadImage("Sosnowsky.png"));
        organismImages.put('C', loadImage("Cybersheep.png"));
        organismImages.put('G', loadImage("Dragon.png"));
        organismImages.put('A', loadImage("Antelope.png"));
        organismImages.put('F', loadImage("Fox.png"));
        organismImages.put('s', loadImage("Sow.png"));
        organismImages.put('.', loadImage("Blank.png"));
        organismImages.put('W', loadImage("Wolf.png"));
    }

    private static Image loadImage(String filename) {
        try {
            return ImageIO.read(OrganismImages.class.getResource(filename));
        } catch (IOException e) {
            System.err.println("Error loading image: " + filename);
            e.printStackTrace();
            return null;
        }
    }

    public static Image getImage(char organismSign) {
        return organismImages.get(organismSign);
    }
}
