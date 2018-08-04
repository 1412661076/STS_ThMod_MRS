package ThMod_FnH.cards.Marisa;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ThMod_FnH.action.DarkSparkUpgAction;
import ThMod_FnH.action.SparkCostAction;
import ThMod_FnH.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;

public class DarkSpark 
	extends CustomCard {
	
	public static final String ID = "DarkSpark";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Strike.png";
	
	private static final int COST = 1;
	private static final int ATK_DMG = 4;
	private static final int UPG_DMG = 2;
	private static final int DMG_ADD = 2;

	public DarkSpark() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
				AbstractCardEnum.MARISA_COLOR, AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.ENEMY);

		this.baseDamage = ATK_DMG;
		this.baseMagicNumber = this.magicNumber = DMG_ADD;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
				new DamageInfo(p, this.baseDamage, this.damageTypeForTurn),
					AbstractGameAction.AttackEffect.SLASH_DIAGONAL));

    	AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 2));
    	
    	AbstractDungeon.actionManager.addToBottom(new DarkSparkUpgAction(this.upgraded));

		AbstractDungeon.actionManager.addToBottom(new SparkCostAction());
	}

	public AbstractCard makeCopy() {
		return new DarkSpark();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPG_DMG);
			upgradeMagicNumber(1);
		}
	}
}