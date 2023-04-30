package utilz;


import main.Game;

public class Constaints {

	public static class UI {
		public static class Button {
			public static final int B_WIDTH_DEFAULT = 140;
			public static final int B_HEIGHT_DEFAULT = 56;
			public static final int B_WIDTH = (int) (B_WIDTH_DEFAULT * Game.SCALE);
			public static final int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * Game.SCALE);
		}

		public static class PauseButton {
			public static final int SOUND_SIZE_DEFAULT = 42;
			public static final int SOUND_SIZE = (int) (SOUND_SIZE_DEFAULT * Game.SCALE);
		}

		public static class URMButton {
			public static final int URM_DEFAULT_SIZE = 56;
			public static final int URM_SIZE = (int) (URM_DEFAULT_SIZE * Game.SCALE);
		}

		public static class VolumeButton {
			public static final int VOLUME_DEFAULT_WIDTH = 28;
			public static final int VOLUME_DEFAULT_HEIGHT = 44;
			public static final int SLIDER_DEFAULT_WIDTH = 215;

			public static final int VOLUME_WIDTH = (int) (VOLUME_DEFAULT_WIDTH * Game.SCALE);
			public static final int VOLUME_HEIGHT = (int) (VOLUME_DEFAULT_HEIGHT * Game.SCALE);
			public static final int SLIDER_WIDTH = (int) (SLIDER_DEFAULT_WIDTH * Game.SCALE);

		}
	}

	public static class Itachi {
		public static class ItaAni {
			public static final int stand = 1;
			public static final int move = 2;
			public static final int jump = 3;
			public static final int hurt = 4;
			public static final int attack = 5;
			public static final int UAni = 6;
			public static final int IAni = 7;
			public static final int beforeSkill = 8;
			public static final int win = 9;

			public static int GetAniAmount(int ItaAction) {
				switch (ItaAction) {
				case stand:
					return 4;
				case move:
					return 6;
				case jump:
					return 5;
				case hurt:
					return 6;
				case UAni:
					return 14;
				case IAni:
					return 12;
				case attack:
				case win:
					return 13;
				case beforeSkill:
					return 6;
				default:
					return 0;
				}
			}
		}

		public static class ItaSkill {
			public static final int USkill = 1;
			public static final int ISkill = 2;
			public static final int OSkill = 3;

			public static int GetSkillAmount(int ItaSkillAct) {
				switch (ItaSkillAct) {
				case USkill:
					return 17;
				case ISkill:
					return 24;
				case OSkill:
					return 19;
				default:
					return 0;
				}
			}
		}

		public static class ItaIntro {
			public static final int UIntro = 1;
			public static final int IIntro = 2;

			public static int getIntroAmount(int introAni) {
				switch (introAni) {
				case UIntro:
				case IIntro:
					return 2;
				default:
					return 0;
				}
			}
		}
	}

	public static class Sasuke {

		public static class saVer1 {
			public static class saVer1Ani {

				public static final int standVer1 = 1;
				public static final int moveVer1 = 2;
				public static final int jumpVer1 = 3;
				public static final int attackVer1 = 4;
				public static final int changeModeVer1 = 5;
				public static final int numpad_4AniVer1 = 6;
				public static final int numpad_5AniVer1 = 7;
				public static final int hurtVer1 = 8;
				public static final int winVer1 = 9;

				public static int GetSa1AniAmount(int saAction) {
					switch (saAction) {
					case standVer1:
					case moveVer1:
					case jumpVer1:
					case hurtVer1:
						return 4;
					case numpad_4AniVer1:
						return 12;
					case numpad_5AniVer1:
						return 20;
					case attackVer1:
						return 14;
					case changeModeVer1:
						return 9;
					case winVer1:
						return 8;
					default:
						return 0;
					}
				}
			}
			public static class saVer1Skill {
				public static final int numpad_4SkillVer1 = 1;
				public static final int numpad_5SkillVer1 = 2;

				public static int getSa1SkillAmount(int saSkillAct) {
					switch (saSkillAct) {
					case numpad_4SkillVer1:
						return 12;
					case numpad_5SkillVer1:
						return 8;
					default:
						return 0;
					}
				}
			}
		}
		
		public static class saVer2{
			public static class saVer2Ani{
				public static final int standVer2 = 1;
				public static final int moveVer2 = 2;
				public static final int jumpVer2 = 3;
				public static final int attackVer2 = 4;
				public static final int changeModeVer2 = 5;
				public static final int numpad_4AniVer2 = 6;
				public static final int numpad_5AniVer2 = 7;
				public static final int hurtVer2 = 8;
				public static final int winVer2 = 9;
				
				public static int GetSa2AniAmount(int saAction) {
					switch (saAction) {
					case standVer2:
					case moveVer2:
					case jumpVer2:
					case hurtVer2:
						return 4;
					case numpad_4AniVer2:
						return 6;
					case attackVer2:
						return 14;
					case numpad_5AniVer2:
						return 4;
					case changeModeVer2:
						return 9;
					case winVer2:
						return 8;
					default:
						return 0;
					}
				}
			}
			
			public static class saVer2Skill {
				public static final int numpad_4SkillVer2 = 1;
				public static final int numpad_5SkillVer2 = 2;

				public static int getSa2SkillAmount(int saSkillAct) {
					switch (saSkillAct) {
					case numpad_4SkillVer2:
						return 7;
					case numpad_5SkillVer2:
						return 10;
					default:
						return 0;
					}
				}
			}
		}
	}
}
