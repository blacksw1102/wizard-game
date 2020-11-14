import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Enemy extends GameObject {

	private Handler handler;
	Random r = new Random();
	int choose = 0;
	int hp = 100;
	
	private BufferedImage enemy_image;
	
	public Enemy(int x, int y, ID id, Handler handler, SpriteSheet ss) {
		super(x, y, id, ss);
		this.handler = handler;
		
		enemy_image = ss.grabImage(4, 1, 32, 32);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		choose = r.nextInt(10);
		
		for(int i = 0; i <handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Block) {
				if(getBounds().intersects(tempObject.getBounds())) {
					x -= velX;
					y -= velY;
				}
			}
			
			if(tempObject.getId() == ID.Bullet) {
				if(getBounds().intersects(tempObject.getBounds())) {
					hp -= 50;
					if(hp <= 0) handler.removeObject(this);
				}
			}
		}
		
		if(choose == 0) {
			velX = (r.nextInt(4 - -4) + -4);
			velY = (r.nextInt(4 - -4) + -4);
		}
	}

	@Override
	public void render(Graphics g) {
		//g.setColor(Color.yellow);
		//g.fillRect(x, y, 32, 32);
		g.drawImage(enemy_image, x, y, null);
		
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.green);
		g2d.draw(getBoundsBig());
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}
	
	public Rectangle getBoundsBig() {
		return new Rectangle(x-16, y-16, 64, 64);
	}
	
}
