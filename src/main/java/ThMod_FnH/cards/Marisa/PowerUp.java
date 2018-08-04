package ThMod_FnH.cards.Marisa;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import ThMod_FnH.ThMod;
import ThMod_FnH.patches.AbstractCardEnum;

public class PowerUp extends CustomCard {
	public static final String ID = "PowerUp";
	public static final String IMG_PATH = "img/cards/Defend.png";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 0;
	private static final int STC = 2;
	private static final int UPG_STC = 1;
	
	public PowerUp() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.MARISA_COLOR, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF);

		this.baseMagicNumber = this.magicNumber = STC;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		for (AbstractCard c:AbstractDungeon.player.hand.group) {
			if (c.type == CardType.ATTACK){
				ThMod.logger.info(("PowerUp : add "+this.magicNumber+" damage to "+c.cardID));
				c.baseDamage += this.magicNumber;
				c.applyPowers();
				}
	    	}
	}

	public AbstractCard makeCopy() {
		return new PowerUp();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(UPG_STC);
		}
	}
}