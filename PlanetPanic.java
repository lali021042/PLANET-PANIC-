import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.sound.sampled.*;

public class PlanetPanic extends JFrame {  //create frame game

    public PlanetPanic() {
        Frame();
    }

    private void Frame() {
        add(new Surface());
        setTitle("PLANET PANIC!");
        setSize(1024, 768);  //size frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //When click close button will closing game.
        setLocationRelativeTo(null);
        setResizable(false);
        setBackground (Color.black);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                PlanetPanic ex = new PlanetPanic();
                ex.setVisible(true);
            }
        });
    }
}

class Surface extends JPanel implements ActionListener {

	Timer timer;

	SWARM game;
  boolean menu = false;
	int move = 0;
	int active = 0;
	int sgx = 0; //coordinate x of meteorite
	int sgy = 0; //coordinate y of meteorite
	int corner = 0; //choice corner of meteorite
	Random rand; //random creation of meteorite position numbers
	String message ="Life!";
	int life = 10; //world number
	int crushes =0; //variable meteorite affected
	int mx=0; //coordinated meteorite hit
	int my=0; //coordinated meteorite hit
	int startgame = 0;  //presentation game
	int score =0; //Variable Collect score
	String sco = ("Score: ");
	int timeintro = 0;

//Variables of animation creation

//Variables of animation how to play
  int menunum = 4;  //Number of images
  int menudelay = 8; //delay for a moment
  int menucount = menudelay;
  int menumove = 1;
  int menurank = 0;

//Variables of animation world
  int earthnum = 7;
  int earthdelay = 20;
  int earthcount = earthdelay ;
  int earthmove = 1;
  int earthrank= 0;

//Variables of animation game page
  int planetdelay= 8;
  int planetnum = 4;
  int planetcount = planetdelay;
  int planetrank = 0;
  int planetmove = 1;

//Variables of animation meteorite
  int firedelay = 8;
  int firenum = 2;
  int firecount = firedelay ;
  int firerank = 0;
  int firemove = 1;

 //Variables of animation victory
  int victorydelay = 3;
  int victorynum = 3;
  int victorycount = victorydelay;
  int victoryrank = 0;
  int victorymove = 1;

 //Variables of animation game over
  int enddelay = 3;
  int endnum = 2;
  int endcount = enddelay;
  int endrank = 0;
  int endmove = 1;

//Variables of animation background
  int spacedelay = 5;
  int spacenum = 2;
  int spacecount = spacedelay;
  int spacemove = 1;
  int spacerank = 0;

  Audio sound;

	public Surface () {
		initSurface() ; //Pull another class to use
    sound = new Audio("intro.wav"); //calls sound
    sound.play(); //play sound
	}

	private void initSurface() {
		addKeyListener(new TAdapter()); // for keyboard events
		setFocusable (true); // recognizes obtaining focus from keyboard
		setBackground (Color.white);
		game = new SWARM ();
		timer= new Timer (50, this); //deley game
		timer.start();
	}

  public void motion() {
        menucount--;
        earthcount--;
        planetcount--;
        firecount--;
        victorycount--;
        endcount--;
        spacecount--;

        if (menucount <= 0) {
            menucount = menudelay;  //Image delay
            menurank = menurank +menumove;

            if (menurank == (menunum - 1) || menurank == 0) {  //Round trip picture
                menumove = -menumove;
            }
        }

        if (earthcount <= 0) {
            earthcount = earthdelay;
            earthrank = earthrank + earthmove;

            if (earthrank == (earthnum - 1) || earthrank == 0) {
                earthmove = -earthmove;
            }
        }
        if (planetcount <= 0) {
            planetcount = planetdelay;
            planetrank = planetrank + planetmove;

            if (planetrank == (planetnum - 1) || planetrank == 0) {
                planetmove = -planetmove;
            }
        }
        if (firecount <= 0) {
            firecount = firedelay;
            firerank = firerank + firemove;

            if (firerank == (firenum - 1) || firerank == 0) {
                firemove = -firemove;
            }
          }
        if (victorycount <= 0) {
            victorycount = victorydelay;
            victoryrank = victoryrank + victorymove;

            if (victoryrank == (victorynum - 1) || victoryrank == 0) {
                victorymove = -victorymove;
              }
            }
        if (endcount <= 0) {
            endcount = enddelay;
            endrank = endrank + endmove;

              if (endrank == (endnum - 1) || endrank == 0) {
                  endmove = -endmove;
                }
              }
        if (spacecount <= 0) {
            spacecount = spacedelay;
            spacerank = spacerank + spacemove;

              if (spacerank == (spacenum - 1) || spacerank == 0) {
                 spacemove = -spacemove;
                }
              }
    }

// JPanel method
	public void paintComponent (Graphics g) {  //This method, calls doDrawing
		super.paintComponent(g); //will called continuously by actionPerformed

    if (menu == false) {
        doDrawing(g);
		    stamscore (g);
    }
    if (menu == true) {
        menuGame(g);
    }
		if (life == 0 ){
      startgame = 0;
      gameover(g);
    }
	}

	private void doDrawing  (Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		if (startgame == 1) {
       motion();
      if (score <= 200){
        switch (spacerank) {
              case 1:
                  g2d.drawImage (game.getimmaginespace() ,0, 0, this);  //Position of the image and find  to the case
                  break;
              default:
                  g2d.drawImage (game.getimmaginespace12() ,0, 0, this);
                  break;
        }
      }

      if (score > 200){
        switch (spacerank) {
              case 1:
                  g2d.drawImage (game.getimmaginespace2() ,0, 0, this);
                  break;
              default:
                  g2d.drawImage (game.getimmaginespace22() ,0, 0, this);
                  break;
        }
      }

      if (score >= 600){
        switch (spacerank) {
              case 1:
                  g2d.drawImage (game.getimmaginespace3() ,0, 0, this);
                  break;
              default:
                  g2d.drawImage (game.getimmaginespace32() ,0, 0, this);
                  break;
        }
      }

      if (score >= 900){
        switch (spacerank) {
              case 1:
                  g2d.drawImage (game.getimmaginespace4() ,0, 0, this);
                  break;
              default:
                  g2d.drawImage (game.getimmaginespace42() ,0, 0, this);
                  break;
        }
      }

			g2d.drawImage (game.getguard(), game.getax(), game.getay(), this); //show spaceship

			if (crushes == 1){
        g2d.drawImage (game.getboomed(), mx, my, this); //show boom
      }

			if (active == 0 ){
        creoverme(); //control game
      }

      if (corner==1){
      switch (firerank) {
            case 1:
                g2d.drawImage (game.getswarmfireAX() , sgx, sgy,this);
                break;
            default:
                g2d.drawImage (game.getswarmfireA() , sgx, sgy,this);
                break;
        }
      }

      if (corner==2){
      switch (firerank) {
            case 1:
                g2d.drawImage (game.getswarmfireB() , sgx, sgy,this);
                break;
            default:
                g2d.drawImage (game.getswarmfireBX() , sgx, sgy,this);
                break;
        }
      }

      if (corner==3){
      switch (firerank) {
            case 1:
                g2d.drawImage (game.getswarmfireC() , sgx, sgy,this);
                break;
            default:
                g2d.drawImage (game.getswarmfireCX() , sgx, sgy,this);
                break;
        }
      }

      if (corner==4){
      switch (firerank) {
            case 1:
                g2d.drawImage (game.getswarmfireD() , sgx, sgy,this);
                break;
            default:
                g2d.drawImage (game.getswarmfireDX() , sgx, sgy,this);
                break;
        }
      }

      switch (earthrank) {
            case 1:
                g2d.drawImage (game.getworld1() , 410,260,this);
                break;
            case 2:
                g2d.drawImage (game.getworld2() , 410,260,this);
                break;
            case 3:
                g2d.drawImage (game.getworld3() , 410,260,this);
                break;
            case 4:
                g2d.drawImage (game.getworld4() , 410,260,this);
                break;
            case 5:
                g2d.drawImage (game.getworld5() , 410,260,this);
                break;
            case 6:
                g2d.drawImage (game.getworld6() , 410,260,this);
                break;
            default:
                g2d.drawImage (game.getworld() , 410,260,this);
                break;
        }
		}

		if(startgame == 0 )  {
       motion();
      if(life > 0){
      switch (planetrank) {
            case 1:
                g2d.drawImage (game.getplaneted2(),-9,-30,this);
                break;
            case 2:
                g2d.drawImage (game.getplaneted3(),-9,-30,this);
                break;
            case 3:
                g2d.drawImage (game.getplaneted4(),-9,-30,this);
                break;
            default:
                g2d.drawImage (game.getplaneted(),-9,-30,this);
                break;
        }
      }

      if(life == 0){
      switch (endrank) {
            case 1:
                g2d.drawImage (game.getended2 (),-9,-30,this);
                break;
            case 2:
                g2d.drawImage (game.getended3 (),-9,-30,this);
                break;
            default:
                g2d.drawImage (game.getended (),-9,-30,this);
                break;
        }
      }

      if(score  >= 1000){
      switch (victoryrank) {
            case 1:
                g2d.drawImage (game.getvictoryed2 (),-9,-30,this);
                break;
            case 2:
                g2d.drawImage (game.getvictoryed3 (),-9,-30,this);
                break;
            default:
                g2d.drawImage (game.getvictoryed (),-9,-30,this);
                break;
        }
      }
		}
	}

  	private void menuGame (Graphics g) {
      Graphics2D g2d = (Graphics2D) g;
       motion();
      switch (menurank) {
            case 1:
                g2d.drawImage (game.getmenu2() , -9, -30,this);
                break;
            case 2:
                g2d.drawImage (game.getmenu3() , -9, -30,this);
                break;
            case 3:
                g2d.drawImage (game.getmenu4() , -9, -30,this);
                break;

            default:
                g2d.drawImage (game.getmenu1() , -9, -30,this);
                break;
        }
    }

	public void stamscore (Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Font font = new Font("Verdana", Font.BOLD, 18);
		g2d.setColor(Color.WHITE);
        g2d.setFont(font);
        if (startgame == 1) {
      			g2d.drawString (message , 60, 670); //life message
            g2d.drawString (sco,750,670 ); //score message
            String Total = Integer.toString(score);
            g2d.drawString (Total, 825,670); //play score
      			int ga = life;
      			while (ga > 0 ) {
      				g2d.drawImage (game.getgiglobe() ,(105+(ga*35)),645,this); //x's position in life
              ga -= 1;
      	     }
		    }
  }

    public void gameover (Graphics g) {

		    Graphics2D g2d = (Graphics2D) g;
		    Font font = new Font("Verdana", Font.BOLD, 50);
		    FontMetrics metr = this.getFontMetrics(font);
		    g2d.setColor(Color.WHITE);
		    g2d.drawString (sco, 430,325 );
		    String Total = Integer.toString(score);
	      g2d.drawString (Total, 500,325);
	  }

    public void actionPerformed(ActionEvent e) {  //handle the game
    		if (life >0) {
    			if (startgame ==0  ){

         }
    			if (startgame ==1) {
        			movebig(); //direction of meteorite
        			moveblow(); // routine for meteorite movement
        			controllacollisioniA(); //check meteorite and world
        			controllocollisioniB (); // control meteorite and spaceship
    		 }
      }
    		repaint();
    }

  private void initGame() {  // is every time you press the space bar you enter this method and reset method
     move = 0;
     active = 0;
     sgx = 0;
     sgy = 0;
     corner = 0;
     life = 10;
     crushes =0;
   	 mx=0;
   	 my=0;
   	 score =0;
     timeintro = 0;
  }

class TAdapter extends KeyAdapter {

	public void keyPressed (KeyEvent ke) {  //move spaceship
		int key = ke.getKeyCode();

    if (startgame == 1) {

        if (key == KeyEvent.VK_LEFT) {
           move=1;
        }

        if (key == KeyEvent.VK_RIGHT) {
           move=2;
        }

        if (key == KeyEvent.VK_UP) {
            move=3;
        }

        if (key == KeyEvent.VK_DOWN) {
            move=4;
        }

        else if (key == KeyEvent.VK_ESCAPE && timer.isRunning()) {
            startgame = 0;
        }

        else if (key == KeyEvent.VK_PAUSE) {
                      if (timer.isRunning()) {
                          timer.stop();
                      }
                      else {
                          timer.start();
                      }
                  }
    }

    else {
      if (key == KeyEvent.VK_SPACE) {
        menu = false;
        startgame = 1;
        initGame();
      }
      if(key == KeyEvent.VK_ENTER){
        menu = true;
      }
    }
   }
	}

	public void movebig () {
		if (move==1) {
      game.moveleft();
    }
		else if (move==2) {
      game.moveright();
    }
		else if (move==3) {
      game.moveup ();
    }
		else if (move==4) {
      game.movedown ();
    }
	}

	public void creoverme () {  //random meteorite
		rand = new Random ();
		corner = rand.nextInt(4);
		corner += 1;
    //Lateral positioning of the meteorite
		if (corner==1 || corner ==3) {
      sgx = rand.nextInt(1000); //Is the position meteorite it came out
    }
    if (corner==4){
      sgx = 1;
    }
		if (corner ==2 ){
      sgx = 1000;
    }
		if (corner == 2 || corner == 4 ){
      sgy = rand.nextInt(700);
    }
    if (corner ==1 ){
      sgy = 1;
    }
		if (corner ==3){
      sgy = 700;
    }
		active=1;
	}

	public void moveblow () {
  if (score <= 200){
    if (active >0 && sgx< 480){
      sgx += 5;  // speed boom
    }
		if (active >0 && sgx> 500){
      sgx -=5;
    }
		if (active >0 && sgy< 340){
      sgy += 5;
    }
		if (active >0 && sgy> 340){
      sgy -= 5;
    }
  }

  if (score > 200){
    if (active >0 && sgx< 480){
      sgx +=7;
    }
		if (active >0 && sgx> 500){
      sgx -=7;
    }
		if (active >0 && sgy< 340){
      sgy +=7;
    }
		if (active >0 && sgy> 340){
      sgy -=7;
    }
  }

  if (score >= 600){
    if (active >0 && sgx< 480){
      sgx += 8;
    }
		if (active >0 && sgx> 500){
      sgx -=8;
    }
		if (active >0 && sgy< 340){
      sgy +=8;
    }
		if (active >0 && sgy> 340){
      sgy -=8;
    }
  }

  if (score >= 900){
    if (active >0 && sgx< 480){
      sgx += 9;
    }
		if (active >0 && sgx> 500){
      sgx -=9;
    }
		if (active >0 && sgy< 340){
      sgy +=9;
    }
		if (active >0 && sgy> 340){
      sgy -= 9;
    }
  }

  active += 1;
  if (active == 10)
     active = 1;
     crushes = 0;
}

	public Rectangle getBoundblow() {  //create a rectangle around a sprite
		game.getImageDimensionsblow();
		return new Rectangle(sgx, sgy, game.widthblow, game.heightblow);
	}

public void controllacollisioniA () {	 //check meteorite intersection with world
		Rectangle r1 = getBoundblow();
		Rectangle r2 = game.getBoundworld();
		if (active == 1 ) {
			if (r1.intersects(r2)){
          active = 0;
          life -= 1;
          if (life == 0) {
              sound = new Audio("over.wav");
              sound.play();
              startgame = 0;
          }
      }
		}
	}

	public void controllocollisioniB () { //meteorite intersection with spaceship
		Rectangle r1 = getBoundblow ();
		Rectangle r2 = game.getBoundsguard ();

		if (crushes ==0 && r1.intersects (r2) && active >1 ){
      crushes = 1;
      mx = sgx;
      my = sgy;
      sound = new Audio("boom.wav");  //call sounnd
      sound.play();
    }

  		if (r1.intersects (r2)){
        crushes =1;
        active = 0 ;
        if (score <= 200){
          score += 5;
        }
        if (score > 200){
          score += 10;
        }
        if (score >= 600){
          score += 15;
        }
        if (score >= 900){
          score += 20;
        }

        if(score >= 1000){
           sound = new Audio("win.wav"); //call sounnd
           sound.play();
          startgame = 0;
        }

      sound = new Audio("boom.wav"); //call sounnd
      sound.play();
      }
	 }
 }

class SWARM {
  // image variables.
	Image space,space2,space3,space4,space12,space22,space32,space42;
  Image menu1 ,menu2,menu3,menu4;
	Image guard;
	Image swarmfireA;
	Image swarmfireAX;
	Image swarmfireB;
	Image swarmfireBX;
	Image swarmfireC;
	Image swarmfireCX;
	Image swarmfireD;
	Image swarmfireDX;
	Image world,world1,world2,world3,world4,world5,world6;
  Image victory,victory2,victory3;
  Image end,end2,end3;
	Image boom;
	Image planet,planet2,planet3,planet4;
  Image globe;

//variables of squares of images
	int widthguard ;
	int heightguard ;
	int widthblow ;
	int heightblow ;
	int widthworld ;
	int heightworld ;

	SWARM () {
		initswarm() ;
	}

	public void initswarm () {  //upload pictures for game.

		    ImageIcon sp = new ImageIcon("space.png");
        ImageIcon sp12 = new ImageIcon("space12.png");
        ImageIcon sp2 = new ImageIcon("space2.png");
        ImageIcon sp22 = new ImageIcon("space22.png");
        ImageIcon sp3 = new ImageIcon("space3.png");
        ImageIcon sp32 = new ImageIcon("space32.png");
        ImageIcon sp4 = new ImageIcon("space4.png");
        ImageIcon sp42 = new ImageIcon("space42.png");

        ImageIcon nu1 = new ImageIcon ("menu1.png");
        ImageIcon nu2 = new ImageIcon ("menu2.png");
        ImageIcon nu3 = new ImageIcon ("menu3.png");
        ImageIcon nu4 = new ImageIcon ("menu4.png");

        ImageIcon gua = new ImageIcon("guard1.png");

        ImageIcon SGA = new ImageIcon ("FireA.png");
        ImageIcon SGAX = new ImageIcon ("FireAX.png");
        ImageIcon SGB = new ImageIcon ("FireB.png");
        ImageIcon SGBX = new ImageIcon ("FireBX.png");
        ImageIcon SGC = new ImageIcon ("FireC.png");
        ImageIcon SGCX = new ImageIcon ("FireCX.png");
        ImageIcon SGD = new ImageIcon ("FireD.png");
        ImageIcon SGDX = new ImageIcon ("FireDX.png");

        ImageIcon wor = new ImageIcon ("w1.png");
        ImageIcon wor1 = new ImageIcon ("w2.png");
        ImageIcon wor2 = new ImageIcon ("w3.png");
        ImageIcon wor3 = new ImageIcon ("w4.png");
        ImageIcon wor4 = new ImageIcon ("w5.png");
        ImageIcon wor5 = new ImageIcon ("w6.png");
        ImageIcon wor6 = new ImageIcon ("w7.png");

        ImageIcon bom = new ImageIcon ("boom.png");

        ImageIcon pla = new ImageIcon ("planet.png");
        ImageIcon pla2 = new ImageIcon ("planet2.png");
        ImageIcon pla3 = new ImageIcon ("planet3.png");
        ImageIcon pla4 = new ImageIcon ("planet4.png");

        ImageIcon glo = new ImageIcon ("globe.png");

        ImageIcon vic = new ImageIcon ("victory.png");
        ImageIcon vic2 = new ImageIcon ("victory2.png");
        ImageIcon vic3 = new ImageIcon ("victory3.png");

        ImageIcon en = new ImageIcon ("end.png");
        ImageIcon en2 = new ImageIcon ("end2.png");
        ImageIcon en3 = new ImageIcon ("end3.png");

		space = sp.getImage();
    space2 = sp2.getImage();
    space3 = sp3.getImage();
    space4 = sp4.getImage();
    space12 = sp12.getImage();
    space22 = sp22.getImage();
    space32 = sp32.getImage();
    space42 = sp42.getImage();

    menu1 = nu1.getImage();
    menu2 = nu2.getImage();
    menu3 = nu3.getImage();
    menu4 = nu4.getImage();

		guard = gua.getImage();

		swarmfireA = SGA.getImage();
		swarmfireAX = SGAX.getImage();

		swarmfireB = SGB.getImage();
		swarmfireBX = SGBX.getImage();

		swarmfireC = SGC.getImage();
		swarmfireCX = SGCX.getImage();

		swarmfireD = SGD.getImage();
		swarmfireDX = SGDX.getImage();

		world = wor.getImage();
    world1 = wor1.getImage();
    world2 = wor2.getImage();
    world3 = wor3.getImage();
    world4 = wor4.getImage();
    world5 = wor5.getImage();
    world6 = wor6.getImage();

		boom = bom.getImage();

    globe = glo.getImage();

		planet = pla.getImage();
    planet2 = pla2.getImage();
    planet3 = pla3.getImage();
    planet4 = pla4.getImage();

    victory = vic.getImage();
    victory2 = vic2.getImage();
    victory3 = vic3.getImage();

    end = en.getImage();
    end2 = en2.getImage();
    end3 = en3.getImage();

	}

//methods return images
	public Image getimmaginespace () {
		return space;
	}
  public Image getimmaginespace2 () {
    return space2;
  }
  public Image getimmaginespace3 () {
    return space3;
  }
  public Image getimmaginespace4 () {
    return space4;
  }
  public Image getimmaginespace12 () {
    return space12;
  }
  public Image getimmaginespace22 () {
    return space22;
  }
  public Image getimmaginespace32 () {
    return space32;
  }
  public Image getimmaginespace42 () {
    return space42;
  }

  public Image getmenu1() {
    return menu1;
  }
  public Image getmenu2() {
    return menu2;
  }
  public Image getmenu3() {
    return menu3;
  }
  public Image getmenu4() {
    return menu4;
  }

	public Image getguard () {
		return guard;
	}

  public Image getgiglobe () {
		return globe;
	}

	public Image getswarmfireA (){
		return swarmfireA;
	}
	public Image getswarmfireAX (){
		return swarmfireAX;
	}

	public Image getswarmfireB (){
		return swarmfireB;
	}
	public Image getswarmfireBX (){
		return swarmfireBX;
	}

	public Image getswarmfireC (){
		return swarmfireC;
	}
	public Image getswarmfireCX (){
		return swarmfireCX;
	}

	public Image getswarmfireD (){
		return swarmfireD;
	}
	public Image getswarmfireDX (){
		return swarmfireDX;
	}
	public Image getworld () {
		return world;
	}
  public Image getworld1 () {
    return world1;
  }
  public Image getworld2 () {
    return world2;
  }
  public Image getworld3 () {
    return world3;
  }
  public Image getworld4 () {
    return world4;
  }
  public Image getworld5 () {
    return world5;
  }
  public Image getworld6 () {
    return world6;
  }

	public Image getboomed () {
		return boom;
	}

	public Image getplaneted () {
		return planet;
	}
  public Image getplaneted2 () {
		return planet2;
	}
  public Image getplaneted3 () {
		return planet3;
	}
  public Image getplaneted4 () {
		return planet4;
	}

  public Image getvictoryed () {
		return victory;
	}

  public Image getvictoryed2 () {
		return victory2;
	}

  public Image getvictoryed3 () {
		return victory3;
	}

  public Image getended () {
		return end;
	}

  public Image getended2 () {
		return end2;
	}

  public Image getended3 () {
		return end3;
	}

	public int getax (){
		return ax;
	}
	public int getay (){
		return ay;
	}

  int ax= 480; //coordinate variables spaceship
  int ay = 534;

	public void moveup () { //Sliding point position
		if (ay>0){
      ay -= 30;
    }
	}

	public void movedown () {
		if (ay<600){
      ay+=30; //Scrolling speed of the spaceship
    }
	}

	public void moveleft () {
		if(ax>0){
      ax-=30;
    }
	}

	public void moveright () {
		if (ax<880){
      ax +=30;
    }
	}

  public void getImageDimensionsguard() {  // square extraction from spaceship
		widthguard = guard.getWidth(null);
    heightguard = guard.getHeight(null);
  }


	public Rectangle getBoundsguard() {  //method that creates a rectangle around the sprite.
		getImageDimensionsguard ();
		return new Rectangle(ax, ay, widthguard, heightguard);
	}


  public void getImageDimensionsblow() {  // square meteorite extraction
    widthblow = swarmfireA.getWidth(null);
    heightblow = swarmfireA.getHeight(null);
  }

  public void getImageDimensionsworld() { // square extraction world
    widthworld = world.getWidth(null);
    heightworld = world.getHeight(null);
  }


	public Rectangle getBoundworld() {  //creates a rectangle around the sprite.
	  getImageDimensionsworld();
		return new Rectangle(410, 260, widthworld, heightworld);
	}
}

class Audio {  //class sound
	private Clip player;
	public Audio(String s) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(s));
			AudioFormat bformat = ais.getFormat();
			AudioFormat decodeFormat = new AudioFormat(
				AudioFormat.Encoding.PCM_SIGNED,
				bformat.getSampleRate(),
				16,
				bformat.getChannels(),
				bformat.getChannels() * 2,
			  bformat.getSampleRate(),
				false
			);

			AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, ais);
			player = AudioSystem.getClip();
			player.open(dais);
		}

		catch(Exception so){
			so.printStackTrace();
		}
	}

	public void play() {
		if(player == null) return;
		stop();
		player.setFramePosition(0);
		player.start();
	}

	public void stop() {
		if(player.isRunning()) player.stop();
	}

}
