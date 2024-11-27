import java.awt.*;
import java.util.*;
import java.util.List;

public class AlienBullets {
    private final int FRAME_HEIGHT;
    private List<AlienAmmo> alienAmmo;
    public AlienBullets(int frameHeight) {
        FRAME_HEIGHT = frameHeight;
        alienAmmo = new ArrayList<>();
    }
    public void add(AlienAmmo al) {
        alienAmmo.add(al);
    }
    public void drawEmAll(Graphics window)
    {
        for (AlienAmmo bullet : alienAmmo) {
            bullet.draw(window);
        }
    }

    public void moveEmAll()
    {
        for(AlienAmmo bullet : alienAmmo) {
            bullet.move("DOWN");
        }
    }
    public void cleanEmUp()
    {
        alienAmmo.removeIf(bullet -> bullet.getY() > FRAME_HEIGHT);
    }
    public List<AlienAmmo> getShots()
    {
        return alienAmmo;
    }
}
