package entities;

import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;
import static utilz.Constaints.Itachi.ItaAni.*;
import static utilz.Constaints.Itachi.ItaSkill.*;
import static utilz.Constaints.Itachi.ItaIntro.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.sound.midi.VoiceStatus;

import UI.SoundButton;
import gameState.Playing;
import main.Game;
import utilz.LoadSave;

import static utilz.HelpMethods.CanMoveHere;
public class PlayerI extends Player {
	
	private boolean UPressed,IPressed,OPressed,doneBK=false; //doneBK la bien ma khi thuc hien xong before skill thi no se chuyen thanh true
	private BufferedImage[][] ItaAni,ItaSkill;
	private BufferedImage[] intro;
	private int ItaAction = beforeSkill,ItaSkillAct,IntroAni=beforeSkill;
	private int skillIndex=1,introIndex=1;
	private Rectangle2D.Float UAtkBox,IAtkBox,OAtkBox;

	public PlayerI(float x, float y, int width, int height,Playing playing) {
		super(x, y, width, height,playing);
		LoadAnimation();
		initUAttackBox();
	}

	private void initUAttackBox() {
		float x = hitbox.x + hitbox.width + (int)(10*Game.SCALE);
		UAtkBox  = new Rectangle2D.Float(x,y,(int)(290 * Game.SCALE),(int)(100 * Game.SCALE));
		
	}
	public void update(PlayerII playerII) {
		if(!OPressed && !IPressed && !UPressed && ItaAction!=hurt) {
			updatePos();	
		}
		updateManaBar();
		jumpUpdate();
		if(currentHealth<=0) {
			playing.setGameOver(true);
		}	
		updateHealthBar();
		updateAttackBox();
		checkAttack(playerII);	
	}

	
	public void LoadAnimation() {
		ItaAni = new BufferedImage[20][30];
		ItaSkill = new BufferedImage[10][30];
		intro = new BufferedImage[5];
		int[] ItaAniAmount= {0,4,6,5,6,13,14,12,6,13};
		for(int i=1;i<ItaAniAmount.length;i++) {
			for(int j=1;j<=ItaAniAmount[i];j++) {
				ItaAni[i][j]=LoadSave.GetSpriteAtlas("Itachi/ItaAni",i,j);
			}
		}
		
		int[] ItaSkillAmount= {0,17,24,19};
		for(int i=1;i<=3;i++) {
			for(int j=1;j<=ItaSkillAmount[i];j++) {
				ItaSkill[i][j]=LoadSave.GetSpriteAtlas("Itachi/ItaSkill",i,j);

			}
		}	
		statusBarImg =LoadSave.GetSpriteAtlas("status_bar", 1);
	}

	
	protected void checkAttack(PlayerII playerII) {
		if (attacking && aniIndex != 1) {
			checkEnemyPlayerHit(attackBox, playerII, 1,1);
		}else if(UPressed && skillIndex>=5 && skillIndex<=12) {
			checkEnemyPlayerHit(UAtkBox, playerII, 2,-1);
		}else if(IPressed && skillIndex>=11 && skillIndex<=18) {
			checkEnemyPlayerHit(IAtkBox, playerII, 2,-2);
		}else if(OPressed && skillIndex!=1) {
			checkEnemyPlayerHit(IAtkBox, playerII, 2,-2);
		}
		else {
			playerII.setDamage(false);
		}
	}
 	protected void updateAnimationTick() {
		aniTick++;
		if(aniTick>aniSpeed) {
			aniTick=0;
			aniIndex++;
			if(aniIndex>GetAniAmount(ItaAction) ) {
				aniIndex=1;
			}
		}
	}
 	protected void updateSkillAniTick() {
 		skillTick++;
		if(skillTick>aniSpeed) {
			skillTick=0;
			skillIndex++;
			if(skillIndex>GetSkillAmount(ItaSkillAct) ) {
				skillIndex=1;
			}
		}
	}
 	protected void updateIntroAniTick() {
		introTick++;
		if(introTick>=aniSpeed) {
			introTick=0;
			introIndex++;
			if(introIndex>getIntroAmount(IntroAni) ) {
				introIndex=1;
			}
		}
	}

	protected void checkEnemyPlayerHit(Rectangle2D.Float attackBox ,PlayerII playerII,int damage,int mana) {
		if (attackBox.intersects(playerII.hitbox)) {	
			if(aniTick==aniSpeed || skillTick==aniSpeed) {
				playerII.changeHealth(-damage);
				changeMana(mana);
			}
			playerII.setDamage(true);
		}
	}
	
	public void render(Graphics g,PlayerII playerII) {
		setAnimation(g);
		drawHitbox(g);
		drawAttackBox(g);
		drawUI(g);
		drawUAttackBox(g);
		drawIOAttackBox(g, playerII);
		showSkill(g, playerII);
	}
	protected void setAnimation(Graphics g) {
		if (!UPressed && !IPressed && !OPressed) {
			int startAni = ItaAction;
			if ((leftPressed || rightPressed ||notMoveAnimation) && !attacking && hitbox.y == 400) {
				ItaAction = move;
			} else if (attacking) {
				ItaAction = attack;
				if (startAni != attack) {
					aniIndex = 1;
					aniTick = 0;
					return;
				}
			} else if (hitbox.y < 400 || jumpPressed == true) {
				ItaAction = jump;
			} else if (damage) {
				ItaAction = hurt;
			}else {
				ItaAction = stand;
			}

			if (startAni != ItaAction) {
				resetAniTick();
			}
			
			drawAni(g, ItaAction, aniIndex);
			updateAnimationTick();
		}
	}
	
	public void drawWin(Graphics g) {
		ItaAction=win;
		drawAnimation(g);
	}

	public boolean isUPressed() {
		return UPressed;
	}
	public void setUPressed(boolean uPressed) {
		UPressed = uPressed;
	}
	public boolean isIPressed() {
		return IPressed;
	}
	public void setIPressed(boolean iPressed) {
		IPressed = iPressed;
	}
	public boolean isOPressed() {
		return OPressed;
	}
	public void setOPressed(boolean oPressed) {
		OPressed = oPressed;
	}
	
	
	public void showSkill(Graphics g,PlayerII playerII) {
			if(UPressed) {
				showUSkill(g);
			}
			else if(OPressed) {
				showOSkill(g, playerII);
			}
			else if(IPressed) {
				showISkill(g, playerII);
			}
		}
	
	//OSkill - Lien hoan chieu ket hop
	public void showOSkill(Graphics g,PlayerII playerII) {
		ItaAction=beforeSkill;
		ItaSkillAct=OSkill;

		if(aniIndex<6) {
			drawAnimation(g);
		}
		else if(aniIndex==6) {

			drawAni(g, ItaAction, 6);
			drawOSkillAnimation(g,playerII);
			if(skillIndex==19 && skillTick==aniSpeed) {
				aniIndex=1; skillIndex=1; aniTick=0; skillTick=0;
				OPressed=false;
			}
		}
		drawIOAttackBox(g, playerII);
	}
	
	
	private void drawOSkillAnimation(Graphics g,PlayerII playerII) {
		updateSkillAniTick();

		float leftX = playerII.getHitbox().x-playerII.getHitbox().width;
		float rightX = playerII.getHitbox().x+playerII.getHitbox().width;
		if(skillIndex<=4 || skillIndex>=9 && skillIndex<=12) {
			g.drawImage(ItaSkill[3][skillIndex],(int)leftX-60,(int)hitbox.y,150,200,null);
		}else {
			g.drawImage(ItaSkill[3][skillIndex],(int)rightX-40,(int)hitbox.y,150,200,null);
		}
	}
	//ISkill - Amaterasu
	public void showISkill(Graphics g,PlayerII playerII) {

		ItaSkillAct=ISkill;
		IntroAni=beforeSkill;
		if(IntroAni==beforeSkill && !doneBK) {
			drawBeforeSkill(g);
			if(aniIndex==6 && aniTick==aniSpeed) {
				aniTick=0;
				ItaAction=IAni;
				doneBK=true;
			}
		}
		else if(ItaAction==IAni && doneBK) {
			drawAnimation(g);
			drawISkillAnimation(g,playerII);
			if(skillIndex==24 && skillTick==aniSpeed) {
				ItaAction=beforeSkill;
				aniTick=0; skillTick=0;
				skillIndex=1; aniIndex=1;
				IPressed=false;
				doneBK=false;
			}
		}	
		drawIOAttackBox(g,playerII);
	}	
	private void drawISkillAnimation(Graphics g,PlayerII playerII) {
		updateSkillAniTick();
		int width = (int)(150 * Game.SCALE);
		//Lam the nay de can giua 
		float x = playerII.getHitbox().x-width/2+ playerII.getHitbox().width/2;	
		g.drawImage(ItaSkill[ItaSkillAct][skillIndex],
				(int)x,
				(int)hitbox.y-120,
				width,400,null);
		
	}
	private void drawIOAttackBox(Graphics g, PlayerII playerII) {
		int width = (int)(100 * Game.SCALE);
		//Lam the nay de can giua 
		float x = playerII.getHitbox().x-width/2+ playerII.getHitbox().width/2;	
		//Chi co the tan cong khi ma doi phuong dang o tren mat dat
		IAtkBox = new Rectangle2D.Float(x,(int)hitbox.y,width,(int)(100 * Game.SCALE));
		g.setColor(Color.black);
		g.drawRect((int)IAtkBox.x,(int) IAtkBox.y,(int)IAtkBox.width,(int) IAtkBox.height);
		
	}
	private void drawBeforeSkill(Graphics g) {
		ItaAction=beforeSkill;
		updateAnimationTick();

		drawAni(g, ItaAction, aniIndex);
		
	}
	
	
	//USkill - Hoa don hoa cau chi thuat
	public void showUSkill(Graphics g) {
		ItaAction=UAni;
		ItaSkillAct=USkill;
		IntroAni=UIntro;

		if(aniIndex<13 || aniIndex==14) {
			drawAnimation(g);
		}
		else{
			drawAni(g, ItaAction, 13);
			drawUSkillAnimation(g);
			//aniTick chi chay den aniSpeed - 1 mac du khong hieu li do lam
			if (skillIndex == 17 && skillTick==aniSpeed) {
				aniIndex=14;skillIndex=1;skillTick=0;aniTick=0;
				//Den frame cuoi cung cua 13 thi chuyen no sang frame sang 14 va reset het cac thanh phan khacs
			}
		}
		
		//Thuc hien xong thi chuyen Upressed ve false
		if(aniIndex==14 && aniTick==aniSpeed) {
			aniIndex=1; skillIndex=1; aniTick=0; skillTick=0;
			UPressed=false;
			ItaAction=beforeSkill;  //Phai chuyen ve beforeSKill de cac chieu I,O co the co hoat anh truoc dung skill
		}
	}
	private void drawUAttackBox(Graphics g) {
			if(rightPressed) {
				UAtkBox.x = hitbox.x  + hitbox.width + (int)(10*Game.SCALE);
			}else if(leftPressed) {
				UAtkBox.x = hitbox.x  - (int)(10*Game.SCALE) - UAtkBox.width;
			}
			UAtkBox.y =hitbox.y;

		
//		g.setColor(Color.red);
//		g.drawRect((int)UAtkBox.x -lvlOffSet,(int) UAtkBox.y,(int)UAtkBox.width,(int) UAtkBox.height);	
	}
	private void drawUSkillAnimation(Graphics g) {
		updateSkillAniTick();
		g.drawImage(ItaSkill[ItaSkillAct][skillIndex],
				(int)(hitbox.x-xDrawOffset+80*flipW)+flipX,
				(int)hitbox.y-120,
				500*flipW,400,null);
		
	}
	private void drawIntroduce(Graphics g) {
		updateIntroAniTick();
		g.drawImage(intro[introIndex], 0, 0,GAME_WIDTH,GAME_HEIGHT, null);
		
		

		
	}
	private void drawAnimation(Graphics g) {
		updateAnimationTick();
		drawAni(g, ItaAction, aniIndex);
	}
	private void drawAni(Graphics g, int ItaAction,int aniIndex) {
		g.drawImage(ItaAni[ItaAction][aniIndex],
				(int)(hitbox.x-xDrawOffset)+flipX,
				(int)(hitbox.y),
				width*flipW,height,null);
	}
	
	

	
	
}
