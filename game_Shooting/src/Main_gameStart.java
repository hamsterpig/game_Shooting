

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class GraphicObject{
	BufferedImage img = null;
	int x=0, y=0;
	
	public GraphicObject(String name){
		try{
			img = ImageIO.read(new File(name));
		}catch(IOException e){
			System.out.println(e.getMessage());
			//System.exit();
		}
	}
	public void update(){}
	public void draw(Graphics g){
		g.drawImage(img,x,y,null);
	}
	public void keyPressed(KeyEvent event){}
}
class Missile extends GraphicObject{
	boolean launched = false;
	
	
		public Missile(String name){
		super(name);
		y = -200;
	}
	public void update(){
		if(launched) y-=25;
		if(y<-100) launched = false;
	}
	public void keyPressed(KeyEvent event, int x , int y){
		if( event.getKeyCode() == KeyEvent.VK_SPACE){
			launched =true;
			this.x=x+45;
			this.y=y;
		}
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
}

class Enermy extends GraphicObject{
	int dx= -10;
	public Enermy(String name){
		super(name);
		x = 500;
		y = 0;
	}
	public void update(){ x+=dx;
		if(x<0) dx= +3; if(x>500) dx = -3;}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
}

class SpaceShip extends GraphicObject{
	public SpaceShip(String name){
		super(name);
		x = 400;
		y = 600;
		
	}
	public void keyPressed(KeyEvent event){
		if( event.getKeyCode()== KeyEvent.VK_LEFT){ x-=10;}
		if( event.getKeyCode()== KeyEvent.VK_RIGHT){ x+=10;}
		if( event.getKeyCode()== KeyEvent.VK_UP){ y-=10;}
		if( event.getKeyCode()== KeyEvent.VK_DOWN){ y+=10;}
	}
}


@SuppressWarnings("serial")
class MyPanel extends JPanel implements KeyListener{
	int mCNT = 0;
	Enermy enermy;
	SpaceShip spaceship;
	Missile[] missile;
	
	int enermy_X, enermy_Y, missile_X, missle_Y;
	
	
	public MyPanel(){
		super();
		
		this.setBackground(Color.black);
		missile = new Missile[20];
		enermy_X = 80; 
		enermy_Y = 85; 
		missile_X = 20;
		missle_Y = 25;

		
		this.addKeyListener(this);
		this.requestFocus();
		setFocusable((true));
		
		
		enermy = new Enermy("D:/PGH/Workspace/imgs/enemy.png");
		spaceship = new SpaceShip("D:/PGH/Workspace/imgs/space.png");
		for(int i=0; i<missile.length; i++){
			missile[i] = new Missile("D:/PGH/Workspace/imgs/missile.png");
		}
		
		
		class MyThread extends Thread{
			public void run(){
				while(true){
					enermy.update();
					spaceship.update();
					for(int i=0; i<missile.length; i++){
						missile[i].update();
					}
					
					repaint();
					hitAction();
					
					
					
					try{
						Thread.sleep(10);
					}catch(InterruptedException e){}
						
					}
				}

			
			
			
			private void hitAction() {
				// TODO Auto-generated method stub
				for(int i=0; i<missile.length; i++){
					if(missile[i].getX()+12 >= enermy.getX()-25 && missile[i].getX()+12 < enermy.getX()+enermy_X+12 ){ // x Á¶°Ç
						if(missile[i].getY() >= enermy.getY()-10 && missile[i].getY() < enermy.getY()+enermy_Y+25){
							System.out.println("hit!");
							missile[i].y = -200;
							Main_gameStart.bossHitEvent();
							
						}
						
					}
				}
				
				
				
			}
			}
			Thread t = new MyThread();
			t.start();
			
			
		}
		public void paint(Graphics g){
			super.paint(g);
			enermy.draw(g);
			spaceship.draw(g);
			for(int i=0; i<missile.length; i++){
				missile[i].draw(g);
			}
			
		}
		
		
	@Override
	public void keyPressed(KeyEvent event) {
		// TODO Auto-generated method stub
		spaceship.keyPressed(event);
		
		switch(mCNT){
		case 0: missile[0].keyPressed(event, spaceship.x, spaceship.y); mCNT++; break;
		case 1: missile[1].keyPressed(event, spaceship.x, spaceship.y); mCNT++; break;
		case 2: missile[2].keyPressed(event, spaceship.x, spaceship.y); mCNT++; break;
		case 3: missile[3].keyPressed(event, spaceship.x, spaceship.y); mCNT++; break;
		case 4: missile[4].keyPressed(event, spaceship.x, spaceship.y); mCNT++; break;
		case 5: missile[0].keyPressed(event, spaceship.x, spaceship.y); mCNT++; break;
		case 6: missile[1].keyPressed(event, spaceship.x, spaceship.y); mCNT++; break;
		case 7: missile[2].keyPressed(event, spaceship.x, spaceship.y); mCNT++; break;
		case 8: missile[3].keyPressed(event, spaceship.x, spaceship.y); mCNT++; break;
		case 9: missile[4].keyPressed(event, spaceship.x, spaceship.y); mCNT++; break;
		case 10: missile[10].keyPressed(event, spaceship.x, spaceship.y); mCNT++; break;
		case 11: missile[11].keyPressed(event, spaceship.x, spaceship.y); mCNT++; break;
		case 12: missile[12].keyPressed(event, spaceship.x, spaceship.y); mCNT++; break;
		case 13: missile[13].keyPressed(event, spaceship.x, spaceship.y); mCNT++; break;
		case 14: missile[14].keyPressed(event, spaceship.x, spaceship.y); mCNT++; break;
		case 15: missile[15].keyPressed(event, spaceship.x, spaceship.y); mCNT++; break;
		case 16: missile[16].keyPressed(event, spaceship.x, spaceship.y); mCNT++; break;
		case 17: missile[17].keyPressed(event, spaceship.x, spaceship.y); mCNT++; break;
		case 18: missile[18].keyPressed(event, spaceship.x, spaceship.y); mCNT++; break;
		case 19: missile[19].keyPressed(event, spaceship.x, spaceship.y); mCNT++; break;
		default : mCNT = 0;
		}
		


	}
	@Override
	public void keyReleased(KeyEvent event) {
		// TODO Auto-generated method stub
		spaceship.keyPressed(event);
	}
	@Override
	public void keyTyped(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}
	
}

@SuppressWarnings("serial")
public class Main_gameStart extends JFrame{
	static JLabel hp = new JLabel();
	static int hpInt = 800;
	JPanel pA = new JPanel(new BorderLayout());
	JPanel pA_n = new JPanel();

	public Main_gameStart(){
		
		
		setTitle("My Game");
		setSize(800,800);
		setLayout(new BorderLayout());
		
		pA.add(new MyPanel(), BorderLayout.CENTER);
		
		hp.setBackground(Color.green);
		hp.setPreferredSize(new Dimension(800,20));
		hp.setOpaque(true);
		pA_n.add(hp);
		pA_n.setLayout(new FlowLayout(FlowLayout.LEADING));
		pA.add(pA_n, BorderLayout.PAGE_START);
		this.add(pA);
		setVisible(true);

		
	} 
	
	public static void bossHitEvent(){
		hpInt -= 50;
		
		Dimension damage = new Dimension(hpInt, 20);
		hp.setPreferredSize(damage);
		hp.setBackground(Color.red);
		System.out.println(hpInt);
		hp.repaint();
		hp.revalidate();
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main_gameStart();
	}

}