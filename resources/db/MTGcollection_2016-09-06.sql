# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.14)
# Database: MTGcollection
# Generation Time: 2016-09-06 22:12:02 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table card_colors
# ------------------------------------------------------------

DROP TABLE IF EXISTS `card_colors`;

CREATE TABLE `card_colors` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `card_id` int(11) unsigned NOT NULL,
  `color_1` varchar(16) DEFAULT NULL,
  `color_2` varchar(16) DEFAULT NULL,
  `color_3` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `card_color_relationship` (`card_id`),
  CONSTRAINT `card_color_relationship` FOREIGN KEY (`card_id`) REFERENCES `cards` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table cards
# ------------------------------------------------------------

DROP TABLE IF EXISTS `cards`;

CREATE TABLE `cards` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT '',
  `color` varchar(16) DEFAULT NULL,
  `mana_cost` int(2) DEFAULT NULL,
  `converted_mana_cost` int(2) DEFAULT NULL,
  `type` varchar(32) DEFAULT NULL,
  `sub_type` varchar(32) DEFAULT NULL,
  `text` mediumtext,
  `expansion` varchar(32) DEFAULT NULL,
  `power` int(11) DEFAULT NULL,
  `toughness` int(11) DEFAULT NULL,
  `rarity` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `cards` WRITE;
/*!40000 ALTER TABLE `cards` DISABLE KEYS */;

INSERT INTO `cards` (`id`, `name`, `color`, `mana_cost`, `converted_mana_cost`, `type`, `sub_type`, `text`, `expansion`, `power`, `toughness`, `rarity`)
VALUES
	(1,'Press For Answers','Blue',1,2,'Sorcery',NULL,'Tap target creature. It doesn\'t untap during its controller\'s next untap step.\n\nInvestigate. (Put a colorless Clue artifact token onto the battlefield with \"2, Sacrifice this artifact: Draw a card.\")','Shadows Over Innistrad',NULL,NULL,'Common'),
	(2,'Inquisitor\'s Ox','White',3,4,'Creature','Ox','Delurium - Inquisitor\'s Ox gets +1/+0 and has vigilance as long as there are four or more card types among cards in your graveyard.','Shadows Over Innistrad',2,5,'Common'),
	(3,'Just The Wind','Blue',1,2,'Instant',NULL,'Return target creature to it\'s owner\'s hand.\n\nMadness (Blue card) (If you discard this card, discard it into exile. When you do, cast it for its madness cost or put it into your graveyard.)','Shadows Over Innistrad',NULL,NULL,'Common'),
	(4,'Apothecary Geist','White',3,4,'Creature','Spirit','Flying\n\nWhen Apothecary Geist enters the battlefield, if you control another Spirit, you gain 3 life.','Shadows Over Innistrad',2,3,'Common'),
	(5,'Ghoulcaller\'s Accomplice','Black',1,2,'Creature','Human Rouge','3 {Black}, Exile Ghoulcaller\'s Accomplice from your graveyard: Put a 2/2 black Zombie creature token onto the battlefield. Activate this ability only any time you could cast a sorcery.','Shadows Over Innistrad',2,2,'Common'),
	(6,'Kessig Dire Swine','Green',4,6,'Creature','Boar Horror','Delirium - Kessig Dire Swine has trample as long as there are four or more card types among cards in your graveyard.','Shadows Over Innistrad',6,6,'Common'),
	(7,'Dead Weight','Black',0,1,'Enchantment','Aura','Enchantment Creature\n\nEnchanted Creature gets -2/-2.','Shadows Over Innistrad',NULL,NULL,'Common'),
	(8,'Ember-Eye Wolf','Red',1,2,'Creature','Wolf','Haste\n\n1 {Red}: Ember-Eye Wolf gets +2/+0 until end of turn.','Shadows Over Innistrad',1,2,'Common'),
	(9,'Equestrian Skill','Green',3,4,'Enchantment','Aura','Enchant Creature\n\nEnchanted creature gets +3/+3.\n\nAs long as enchanted creature is a Human, it has trample.','Shadows Over Innistrad',NULL,NULL,'Common'),
	(10,'Gisa\'s Bidding','Black',2,4,'Sorcery',NULL,'Madness 2 {Black} (If you discard this cars, discard it into exile. When you do, cast it fo its madness cost or put in into your graveyard.)','Shadows Over Innistrad',NULL,NULL,'Uncommon'),
	(11,'Trail Of Evidence','Blue',2,3,'Enchantment',NULL,'Whenever you cast an instant or sorcery spell, investigate. (Put a colorless Clue artifact token onto the battlefield with \"2, Sacrifice this artifact: Draw a card.\")','Shadows Over Innistrad',NULL,NULL,'Uncommon'),
	(12,'Biting Rain','Black',2,4,'Sorcery',NULL,'All creatures get -2/-2 until end of turn.\n\nMadness 2 Black (If you discard this card, discard it into exile. When you do, cast it for its madness cost or put it into your graveyard.)','Shadows Over Innistrad',NULL,NULL,'Uncommon'),
	(13,'Fortified Village','Green, White',NULL,NULL,'Land',NULL,'As Fortified Village enters the battlefield, you may reveal a Forest or Plains card from your hand. If you don\'t, Fortified Village enters the battlefield tapped.\n\n(tap): Add Green or White to your mana pool.','Shadows Over Innistrad',NULL,NULL,'Rare'),
	(14,'Accursed Witch - DFC','Black',3,4,'Creature','Human Shaman','Spells your opponents cast that target Accursed Witch cost 1 less to cast.\n\nWhen Accursed Witch dies, return it to the battlefield transformed under your control attached to target opponent.','Shadows Over Innistrad',4,2,'Uncommon'),
	(15,'Infectious Curse - DFC',NULL,NULL,NULL,'Enchantment','Aura Curse','Enchant player\n\nSpells you cast that target enchanted player costs 1 less cast.\n\nAt the beginning of enchanted player\'s upkeep, that player loses 1 life and you gain 1 life.','Shadows Over Innistrad',NULL,NULL,'Uncommon'),
	(16,'Spirit',NULL,NULL,NULL,'Token Creature','Spirit','Flying','Shadows Over Innistrad',NULL,NULL,'Common'),
	(17,'Niblis Of Dusk','Blue',2,3,'Creature','Spirit','Flying\n\nProwess (Whenever you cast a noncreature spell, this creature gets +1/+1 until end of turn.)','Shadows Over Innistrad',2,1,'Common'),
	(18,'Unruly Mob','White',1,2,'Creature','Human','Whenever aother creature you control dies, put a +1/+1 counter on Unruly Mob.','Shadows Over Innistrad',1,1,'Common'),
	(19,'Pieces Of The Puzzle','Blue',2,3,'Sorcery',NULL,'Reveal the top five cards of your library. Put up to two instant and/or sorcery cards from among them into your hand and the rest into your graveyard.','Shadows Over Innistrad',NULL,NULL,'Common'),
	(20,'Sanitarium Skeleton','Black',0,1,'Creature','Skeleton','2 {Black}: Return Sanitarium Skeleton from your graveyard to your hand.','Shadows Over Innistrad',1,2,'Common'),
	(21,'Moorland Drifter','White',1,2,'Creature','Spirit','Delirium - Moorland Drifter has flying as long as there are four or more card types among cards in you graveyard.','Shadows Over Innistrad',2,2,'Common'),
	(22,'Root Out','Green',2,3,'Sorcery',NULL,'Destroy target artifact or enchantment.\n\nInvestigate. (Put a colorless Clue artifact token onto the battlefield with \"2, Sacrifice this artifact: Draw a card.\")','Shadows Over Innistrad',NULL,NULL,'Common'),
	(23,'Rottenheart Ghoul','Black',3,4,'Creature','Zombie','When Rottenheart Ghoul dies, target player discards a card.','Shadows Over Innistrad',2,4,'Common'),
	(24,'Reduce to Ashes','Red',4,5,'Sorcery',NULL,'Reduce Tot Ashes deals 5 damage to target creature. If that creature would die this turn, exile it instead.','Shadows Over Innistrad',NULL,NULL,'Common'),
	(25,'Rabid Bite','Green',1,2,'Sorcery',NULL,'Target creature you control deals damage equal to its power to target creature you don\'t control.','Shadows Over Innistrad',NULL,NULL,'Common'),
	(26,'Reaper of Flight Moonsilver','White',3,5,'Creature','Angel','Flying\n\nDelirium - Sacrifice another creature: Reper of Flight Moonsilver gets +2/+1 until the end of turn. Activate this ability only if there are four or more card types among cards in your graveyard.','Shadows Over Innistrad',3,3,'Uncommon'),
	(27,'Rise from the Tides','Blue',5,6,'Sorcery',NULL,'Put a 2/2 black Zombie creature token onto the battlefield tapped for each instant and socery card in your graveyard.','Shadows Over Innistrad',NULL,NULL,'Uncommon'),
	(28,'Woodland Stream','Green, Blue',NULL,NULL,'Land',NULL,'Woodland Stream enters the battlefield tapped.\n\n(tap): Add Green or Blue to your mana pool.','Shadows Over Innistrad',NULL,NULL,'Uncommon'),
	(29,'Avacyn\'s Judgement','Red',1,2,'Sorcery',NULL,'Madness X {Red} (If you discard this card, discard it into exile. When you do, cast it for its madness cost or out it into your graveyard.)\n\nAvacyn\'s Judgement deals 2 damage divided as you choose among any number of target creatures and/or players. If Avacyn\'s Judgement\'s madness cost was paid, it deals X damage divided as you choose among those creatures and/or players instead.','Shadows Over Innistrad',NULL,NULL,'Rare'),
	(30,'Gatstaf Arsonists - DFC','Red',4,5,'Creature','Human Werewolf','At the beginning of each upkeep, if no spells were cast last turn, transform Gatstaf Arsonists.','Shadows Over Innistrad',5,4,'Common'),
	(31,'Gatstaf Ravagers - DFC','Red',NULL,NULL,'Creature','Werewolf','Menace (This creature can\'t be blocked except by two or more creatures.)\n\nAt the beginning of each upkeep, if a player casts cast two or more spells last turn, transform Gatstaf Ravagers.','Shadows Over Innistrad',6,5,'Common'),
	(32,'Clue',NULL,NULL,NULL,'Token Artifact','Clue','2, Sacrifice this artifact: Draw a card.','Shadows Over Innistrad',NULL,NULL,'Common');

/*!40000 ALTER TABLE `cards` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
