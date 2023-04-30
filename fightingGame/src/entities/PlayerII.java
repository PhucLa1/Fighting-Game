package entities;

import static utilz.Constaints.Sasuke.saVer1.saVer1Ani.*;
import static utilz.Constaints.Sasuke.saVer1.saVer1Skill.*;
import static utilz.Constaints.Sasuke.saVer2.saVer2Ani.*;
import static utilz.Constaints.Sasuke.saVer2.saVer2Skill.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;


import gameState.Playing;
import main.Game;
import utilz.Constaints.Sasuke.saVer1;
import utilz.LoadSave;

public class PlayerII extends Player{
	
	private boolean numpad_4,numpad_5,numpad_6;
	private BufferedImage[][] saAni1,saAni2,saSkill1,saSkill2;
	private int saAction=1,saSkillAct;
	private int skillIndex = 1;
	private Rectangle2D.Float sa1numpad4,sa2numpad4,sa1numpad5,sa2numpad5;
	private int saMode = 1;
	//Chieu U la phun lua lam cho giong nhau ca 2 mode
	//Chieu I thi mode 1 se la - Susano
	//			  mode 2 se la - Chidori
	//Chieu O thi la chuyen doi giua 2 trang thai
	//Tong cong se co la 4 chieu la 1 chieu doi 
	
//	protected int statusBarX = (int) (620 * Game.SCALE);
//	
	public PlayerII(float x, float y, int width, int height, Playing playing) {
		super(x, y, width, height, playing);
		LoadAnimation();
		flipX = width;
		flipW =-1;
		
		initAttackBox();
		initsa2numpad5AttackBox();
	}
	protected void initAttackBox() {
		int width = (int)(20 * Game.SCALE);
		x = hitbox.x  - (int)(10*Game.SCALE) -width;
		attackBox  = new Rectangle2D.Float(x,y,width,(int)(20 * Game.SCALE));	
	}
	private void initsa2numpad5AttackBox() {
		int width =(int) (200 * Game.SCALE);
		float x = hitbox.x  - (int)(10*Game.SCALE) -width;
		sa2numpad5  = new Rectangle2D.Float(x,hitbox.y,width,(int)(100 * Game.SCALE));
		
	}
	private void drawsa2numpad4AttackBox(Graphics g, PlayerI playerI) {
		int width = (int)(100 * Game.SCALE);
		//Lam the nay de can giua 
		float x = playerI.getHitbox().x-width/2+ playerI.getHitbox().width/2;	
		float y = hitbox.y;
		//Chi co the tan cong khi ma doi phuong dang o tren mat dat
		sa2numpad4 = new Rectangle2D.Float(x,y,width,(int)(100 * Game.SCALE));
//		g.setColor(Color.black);
//		g.drawRect((int)sa2numpad4.x -lvlOffSet,(int) sa2numpad4.y,(int)sa2numpad4.width,(int) sa2numpad4.height);
		
	}

	private void drawsa2numpad5AttackBox(Graphics g) {

		if (rightPressed || saMode == 1 && numpad_4) {
			sa2numpad5.x = hitbox.x + hitbox.width + (int) (10 * Game.SCALE);
		} else if (leftPressed) {
			sa2numpad5.x = hitbox.x - (int) (10 * Game.SCALE) - sa2numpad5.width;
		}
		sa2numpad5.y = hitbox.y;

//		g.setColor(Color.red);
//		g.drawRect((int) sanumpad5.x - lvlOffSet, (int) sa2numpad5.y, (int) sa2numpad5.width, (int) sa2numpad5.height);
	}
	public void update(PlayerI playerI) {

		if(!numpad_4 && !numpad_5 && !numpad_6) {
			updatePos();
		}
		
		if(currentHealth<=0) {
			playing.setGameOver(true);
			System.out.println("Thua rooi");
		}
		updateManaBar();
		updateHealthBar();
		updateAttackBox();
		jumpUpdate();
		checkAttack(playerI);
	}

	
	protected void drawUI(Graphics g) {
		statusBarX = (int) (600 * Game.SCALE);
		g.drawImage(statusBarImg,statusBarX,statusBarY,statusBarWidth,statusBarHeight,null );
		g.setColor(Color.red);
		g.fillRect(healthBarXStart+statusBarX, healthBarYStart + statusBarY, healthWidth, healthBarHeight);
		
		g.setColor(Color.yellow);
		manaBarXStart = (int) (645 * Game.SCALE);
		g.fillRect(manaBarXStart, manaBarYStart+ statusBarY, manaWidth, manaBarHeight);
//		System.out.println(manaBarWidth);
	}

	//Load animation
	public void LoadAnimation(BufferedImage[][] images,int [] amount,String actionString) {
		for(int i=1;i<=amount.length-1;i++) {
			for(int j=1;j<=amount[i];j++) {
				images[i][j] = LoadSave.GetSpriteAtlas(actionString, i,j);
			}
		}
	}
	public void LoadAnimation() {
		//Ver1 - animation
		saAni1 = new BufferedImage[10][25];
		int[] saAni1Amount = {0,4,4,4,14,9,12,20,4,8};
		LoadAnimation(saAni1,saAni1Amount,"sasuke/saVer1/sa_ver1Ani");
		//Ver1 - skill animation
		saSkill1 = new BufferedImage[10][20];
		int[] saAni1SkillAmount = {0,12};
		LoadAnimation(saSkill1,saAni1SkillAmount,"sasuke/saVer1/sa_ver1Skill");
		//Ver2 - animation
		saAni2 = new BufferedImage[10][20];
		int[] saAni2Amount = {0,4,4,4,14,9,6,4,4,8};
		LoadAnimation(saAni2,saAni2Amount,"sasuke/saVer2/sa_ver2Ani");
		saSkill2 = new BufferedImage[10][20];
		int[] saAni2SkillAmount = {0,7,10};
		LoadAnimation(saSkill2,saAni2SkillAmount,"sasuke/saVer2/sa_ver2Skill");
		statusBarImg =LoadSave.GetSpriteAtlas("status_bar", 1);
	}
	
	
	//Set animation
	public void setAnimation(Graphics g) {
		if(!numpad_4 && !numpad_5 && !numpad_6) {
			setAnimation();
			if(saMode==1) {
				drawAni(saAni1,g, saAction, aniIndex);
				updatesa1AnimationTick();
			}else {
				drawAni(saAni2,g, saAction, aniIndex);
				updatesa2AnimationTick();
			}
		}
	}
	
	public void setAnimation() {
		int startAni = saAction;
		if ((leftPressed || rightPressed ||notMoveAnimation) && !attacking && hitbox.y == 400) {
			saAction = moveVer1;
		} else if (attacking) {
			saAction = attackVer1;
			if (startAni != attackVer1) {
				aniIndex = 1;
				aniTick = 0;
				return;
			}
		} else if (hitbox.y < 400 || jumpPressed == true) {
			saAction = jumpVer1;
		} 
		else if (damage) {
			saAction = hurtVer1;
		}else {
			saAction = standVer1;
		}
		if (startAni != saAction) {
			resetAniTick();
		}
	}
	
	//Draw win animation
	public void drawWin(Graphics g) {
		if (saMode == 1) {
			saAction = winVer1;
			drawsa1Animation(g);
		} else {
			saAction = winVer2;
			drawsa2Animation(g);
		}
	}

	
	//O- draw skill change mode
	public void shownumpad_6Skill(Graphics g) {
		if (saMode == 1) {
			saAction = changeModeVer1;
			drawsa1Animation(g);
		} else {
			saAction = changeModeVer2;
			drawsa2Animation(g);
		}
		if (aniIndex == 9 && aniTick == aniSpeed) {
			aniIndex = 1;
			aniTick = 0;
			saMode = (saMode == 1) ? 2 : 1;
			numpad_6 = false;
		}
	}
	public void showsa2numpad_4Skill(Graphics g,PlayerI playerI) {
		saAction=numpad_4AniVer2;
//		System.out.println(aniIndex+" "+skillIndex+"");
		if(aniIndex<6) {
			drawsa2Animation(g);
		}
		else if(aniIndex==6) {
			drawAni(saAni2, g, saAction, aniIndex);
			drawsa2numpad_4Skill(g,playerI);
			if(skillIndex==7 && skillTick==aniSpeed) {
				reset();	
				numpad_4=false;
			}
		}
		
	}
	
	public void showsa1numpad_4Skill(Graphics g,PlayerI playerI) {
		saAction = numpad_4AniVer1;
		if(aniIndex<12) {
			drawsa1Animation(g);
		}
		else if(aniIndex==12) {
			hitbox.x =(int) playerI.getHitbox().x-width+(int)(40*Game.SCALE);
			attackBox.x = hitbox.x + hitbox.width + (int)(10*Game.SCALE);
//			sa1numpad5.x=  hitbox.x + hitbox.width + (int)(10*Game.SCALE);
			drawsa1numpad_4Skill(g,playerI);
			if(skillIndex ==12 && skillTick==aniSpeed) {
				reset();
				numpad_4=false;
			}
		}
	}
	public void showsa1numpad_5Skill(Graphics g) {
		saAction = numpad_5AniVer1;
		updatesa1AnimationTick();
		g.drawImage(saAni1[saAction][aniIndex],
				(int)(hitbox.x-xDrawOffset+80*flipW)+flipX,
				(int)hitbox.y-120,
				300*flipW,400,null);
		if(aniIndex==20 && aniTick==aniSpeed) {
			reset();
			numpad_5=false;
		}
	}
	protected void checkEnemyPlayerHit(Rectangle2D.Float attackBox ,PlayerI playerI,int damage,int mana) {
		if (attackBox.intersects(playerI.hitbox)) {
			if(aniTick==aniSpeed || skillTick==aniSpeed) {
				playerI.changeHealth(-damage);
				changeMana(mana);
			}
			
			playerI.setDamage(true);
		}
	}
	protected void checkAttack(PlayerI playerI) {
		if(saMode==1) {
			if(numpad_4 && skillIndex>=2 && skillIndex<=11) {
				if(playerI.getHitbox().y>200) {
					if(skillTick==aniSpeed) {
						playerI.changeHealth(-2);
						changeMana(-2);
					}
					
					playerI.setDamage(true);
				}
			}
			else if (attacking && aniIndex != 1) {
				checkEnemyPlayerHit(attackBox, playerI, 1,2);
			}
			else {
				playerI.setDamage(false);
			}
		}
		else if(saMode==2) {
			if(numpad_4 && skillIndex!=1 ) {
//				System.out.println(1);
				checkEnemyPlayerHit(sa2numpad4, playerI, 2,-2);
			}
			else if(numpad_5 && skillIndex>=7 && skillIndex<=10) {
				checkEnemyPlayerHit(sa2numpad5, playerI, 2,-2);
			}
			else if (attacking && aniIndex != 1) {
				checkEnemyPlayerHit(attackBox, playerI, 2,2);
			}
			else {
				playerI.setDamage(false);
			}
		}
	}
	private void drawsa1numpad_4Skill(Graphics g, PlayerI playerI) {
		saSkillAct = numpad_4SkillVer1;
		updatesa1SkillAniTick();
		int x =(int) ((int) playerI.getHitbox().x-width+40*Game.SCALE);
		g.drawImage(saSkill1[saSkillAct][skillIndex],x,(int)hitbox.y,this.width,this.height, null);		
	}
	
	public void showsa2numpad_5Skill(Graphics g) {
		saAction = numpad_5AniVer2;
		if(aniIndex<4) {
			drawsa2Animation(g);
		}
		else {
			drawAni(saAni2, g, saAction, aniIndex);
			drawsa2numpad_5Skill(g);
			if(skillIndex ==10 && skillTick==aniSpeed) {
				reset();
				numpad_5=false;
			}
		}
	}

	private void drawsa2numpad_5Skill(Graphics g) {
		saSkillAct = numpad_5SkillVer2;
		updatesa2SkillAniTick();
		g.drawImage(saSkill2[saSkillAct][skillIndex],(int)(hitbox.x-xDrawOffset+120*flipW)+flipX,
				(int)(hitbox.y-120),
				300*flipW,400,null);
		
	}

	private void reset() {
		aniIndex=1; skillIndex=1; aniTick=0; skillTick=0;
	}

	private void drawsa2numpad_4Skill(Graphics g, PlayerI playerI) {
		saSkillAct = numpad_4SkillVer2;
		updatesa2SkillAniTick();
		int x =(int) playerI.getHitbox().x-150;
		g.drawImage(saSkill2[saSkillAct][skillIndex],x,(int)hitbox.y-120,300,400, null);	
	}

	private void drawsa2Animation(Graphics g) {
		
		updatesa2AnimationTick();
		drawAni(saAni2, g, saAction, aniIndex);
	}
	private void drawsa1Animation(Graphics g) {
		
		updatesa1AnimationTick();
		drawAni(saAni1, g, saAction, aniIndex);
	}

	private void drawAni(BufferedImage[][] saAni, Graphics g, int saAction,int aniIndex) {
		g.drawImage(saAni[saAction][aniIndex],
				(int)(hitbox.x-xDrawOffset)+flipX,
				(int)(hitbox.y),
				width*flipW,height,null);
	}
	//Update animation and skill Tick
 	protected void updatesa1AnimationTick() {
		aniTick++;
		if(aniTick>aniSpeed) {
			aniTick=0;
			aniIndex++;
			if(aniIndex>GetSa1AniAmount(saAction) ) {
				aniIndex=1;
			}
		}
	}
 	protected void updatesa2AnimationTick() {
		aniTick++;
		if(aniTick>aniSpeed) {
			aniTick=0;
			aniIndex++;
			if(aniIndex>GetSa2AniAmount(saAction) ) {
				aniIndex=1;
			}
		}
	}
 	protected void updatesa1SkillAniTick() {
 		skillTick++;
		if(skillTick>aniSpeed) {
			skillTick=0;
			skillIndex++;
			if(skillIndex>getSa1SkillAmount(saSkillAct) ) {
				skillIndex=1;
			}
		}
	}
 	protected void updatesa2SkillAniTick() {
 		skillTick++;
		if(skillTick>aniSpeed) {
			skillTick=0;
			skillIndex++;
			if(skillIndex>getSa2SkillAmount(saSkillAct) ) {
				skillIndex=1;
			}
		}
	}
	//Render
 	public void showSkill(Graphics g,PlayerI playerI) {
 		if(numpad_4) {
 			if(saMode==1) {
 				showsa1numpad_4Skill(g,playerI);
 			}else {
 				showsa2numpad_4Skill(g, playerI);
 			}
 		}
 		else if(numpad_5) {
 			if(saMode==1) {
 				showsa1numpad_5Skill(g);
 			}else {
 				showsa2numpad_5Skill(g);
 			}
 		}
 		else if(numpad_6) {
 			shownumpad_6Skill(g);
 		}
 	}
	
	public void render(Graphics g,PlayerI playerI) {

		showSkill(g,playerI);
		setAnimation(g);
		drawHitbox(g);
		drawsa2numpad4AttackBox(g,playerI);
		drawAttackBox(g);
		drawsa2numpad5AttackBox(g);
		drawUI(g);
	}
	
	//GEtter and setter
	
	public boolean isNumpad_4() {
		return numpad_4;
	}
	public void setNumpad_4(boolean numpad_4) {
		this.numpad_4 = numpad_4;
	}
	public boolean isNumpad_5() {
		return numpad_5;
	}
	public void setNumpad_5(boolean numpad_5) {
		this.numpad_5 = numpad_5;
	}
	public boolean isNumpad_6() {
		return numpad_6;
	}
	public void setNumpad_6(boolean numpad_6) {
		this.numpad_6 = numpad_6;
	}
	
}
