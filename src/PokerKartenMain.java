import java.util.Arrays;
import java.util.Random;

public class PokerKartenMain {
	static final int ANZAHLFARBEN = 4; //final(nach static) bezweckt dass die const. nicht mehr verändert wird
	static final int ANZAHLKARTENSYMBOLE = 13;
	static final int KARTENDECK = ANZAHLKARTENSYMBOLE*ANZAHLFARBEN;
	static final int HANDGROESSE=5;
	static final int ANZAHLVERSUCHE = 1000000;
	static int zaehleronepair = 0;
	static int zaehlertwopair = 0;
	static int zaehlerthreeofakind = 0;
	static int zaehlerstraight = 0;
	static int zaehlerflush = 0;
	static int zaehlerfullhouse = 0;
	static int zaehlerfourofakind = 0;
	static int zaehlerstraightflush = 0;
	static int zaehlerroyalflush = 0;
	static int[] hand = new int[HANDGROESSE];

	public static void main(String[] args) {
		for(int i=0; i<ANZAHLVERSUCHE;i++)
		{
			kartenZiehen();
			Arrays.sort(hand);
//			for(int j=0;j<hand.length;j++)
//			{
//				System.out.print("Farbe "+j+": ");
//				int farbe = kartenfarbeBerechnen(hand[j]);
//				System.out.println(farbe);
//				System.out.print("Symbol "+j+": ");
//				int symbol =kartensymbolBerechnen(hand[j]);
//				System.out.println(symbol);
//				System.out.println("");
//			}
			if (onePair()) {zaehleronepair++;}
			if (twoPair()) {zaehlertwopair++;}
			if (threeOfAKind()) {zaehlerthreeofakind++;}
			if (straight()) {zaehlerstraight++;}
			if (flush()) {zaehlerflush++;}
			if (fullHouse()) {zaehlerfullhouse++;}
			if (fourOfAKind()) {zaehlerfourofakind++;}
			if (straightFlush()) {zaehlerstraightflush++;}
			if (royalFlush()) {zaehlerroyalflush++;}
		}
		arrayBefuellen();
		//arrayAusgeben();
		System.out.print("\n"+zaehleronepair+" Paar(e) bei "+ANZAHLVERSUCHE+" Versuchen und einer Warscheinlichkeit von:\t "+(zaehleronepair*100.0/ANZAHLVERSUCHE)+" %");
		System.out.print("\n"+zaehlertwopair+" zwei Paare bei "+ANZAHLVERSUCHE+" Versuchen und einer Warscheinlichkeit von:\t "+(zaehlertwopair*100.0/ANZAHLVERSUCHE)+" %");
		System.out.print("\n"+zaehlerthreeofakind+" Drilling(e) bei "+ANZAHLVERSUCHE+" Versuchen und einer Warscheinlichkeit von:\t "+(zaehlerthreeofakind*100.0/ANZAHLVERSUCHE)+" %");
		System.out.print("\n"+zaehlerstraight+" Straße(n) bei "+ANZAHLVERSUCHE+" Versuchen und einer Warscheinlichkeit von:\t "+(zaehlerstraight*100.0/ANZAHLVERSUCHE)+" %");
		System.out.print("\n"+zaehlerflush+" Flush bei "+ANZAHLVERSUCHE+" Versuchen und einer Warscheinlichkeit von:\t\t "+(zaehlerflush*100.0/ANZAHLVERSUCHE)+" %");
		System.out.print("\n"+zaehlerfullhouse+" Full House bei "+ANZAHLVERSUCHE+" Versuchen und einer Warscheinlichkeit von:\t "+(zaehlerfullhouse*100.0/ANZAHLVERSUCHE)+" %");
		System.out.print("\n"+zaehlerfourofakind+" Vierling(e) bei "+ANZAHLVERSUCHE+" Versuchen und einer Warscheinlichkeit von:\t "+(zaehlerfourofakind*100.0/ANZAHLVERSUCHE)+" %");
		System.out.print("\n"+zaehlerstraightflush+" Straight Flush bei "+ANZAHLVERSUCHE+" Versuchen und einer Warscheinlichkeit von:\t "+(zaehlerstraightflush*100.0/ANZAHLVERSUCHE)+" %");
		System.out.print("\n"+zaehlerroyalflush+" Royal Flush bei "+ANZAHLVERSUCHE+" Versuchen und einer Warscheinlichkeit von: \t "+(zaehlerroyalflush*100.0/ANZAHLVERSUCHE)+" %");
	}

	static int randomZahlen(int maxBereich)
	{
		Random rnd = new Random();
		int rnd1 = rnd.nextInt(maxBereich);
		//System.out.println(""+rnd1);
		return rnd1;
	}
	static void arrayBefuellen()
	{
		for(int i=0;i<hand.length;i++)
		{
			hand[i]=i;
			Arrays.sort(hand);
		}
		//System.out.println("_______________1");
	}
	static void arrayAusgeben()
	{
		for(int i=0; i<hand.length; i++)
		{
			System.out.println(hand[i]);
		}
		//System.out.println("_______________");
	}	
	static void kartenZiehen()
	{
		for(int i=0; i<hand.length;i++)
		{
			int rndZahl = randomZahlen(KARTENDECK-1-i);
			for(int j=0;j<hand.length;j++)
			{
				if(rndZahl==hand[j]) 
				{
					rndZahl=randomZahlen(KARTENDECK-1);
					j--;
				}
			}
			hand[i] = rndZahl;
//			System.out.println(hand[i]);
		}
	}	
	static public int kartenfarbeBerechnen(int karte)
	{
		return karte/ANZAHLKARTENSYMBOLE;		
	}
	static public int kartensymbolBerechnen(int karte)
	{
		return karte%ANZAHLKARTENSYMBOLE;
	}
	//-----------------------------------------------------------------Kombinationen abfragen-------------------------------------------------
	static int checkPair() 
	{
		int checkpair = 0;
		for(int i=0;i<hand.length;i++)
		{
			for(int j=0;j<hand.length;j++)
			{
				if(kartensymbolBerechnen(hand[i])==kartensymbolBerechnen(hand[j]) && i!=j) {checkpair++;}
			}
		}
		return checkpair;
	}
	static boolean onePair() //ein Paar
	{
		return checkPair() == 2;
//		macht das gleiche:
//		int checkonepair = checkPair();
//		if(checkonepair == 2)
//		{
//			//System.out.println("Sie haben ein Paar!");
//			return true;
//		}
//		return false;
	}
	static boolean twoPair() //zwei Paare
	{
		return checkPair() == 4;
//		macht das gleiche:
//		int checkonepair = checkPair();
//		if(checkonepair == 4)
//		{
//			//System.out.println("Sie haben zwei Paare!");
//			return true;
//		}
//		return false;
	}
	static boolean threeOfAKind() //drilling
	{
		return checkPair() == 6;
	}
	static boolean straight() //Fünf aufeinanderfolgende Karten 
	{	
		// um die Ecke
		if(kartensymbolBerechnen(hand[0]) == 9
				&& kartensymbolBerechnen(hand[1]) == 10
				&& kartensymbolBerechnen(hand[2]) == 11
				&& kartensymbolBerechnen(hand[3]) == 12
				&& kartensymbolBerechnen(hand[4]) == 0) {
			return true;
		}
		int ersteKarte = kartensymbolBerechnen(hand[0]);
		for(int i = 1; i < hand.length; i++) 
		{
			if (kartensymbolBerechnen(hand[i]) != ersteKarte + 1) 
			{
				return false;
			}
			ersteKarte = kartensymbolBerechnen(hand[i]);
		}
		return true;
	}
	static boolean flush() //5 beliebige Karten derselben Farbe
	{
		int farbe = kartenfarbeBerechnen(hand[0]);
		for(int i=0; i<hand.length; i++)
		{
			if(farbe != kartenfarbeBerechnen(hand[i]))
			{
				return false;
			}
		}
		return true;
	}
	static boolean fullHouse() //drei gleichwertige Karten und paar von anderer farbe
	{
		return checkPair() == 8;
	}
	static boolean fourOfAKind() //Vier Karten des gleichen Werts
	{
		return checkPair() == 12;
	}
	static boolean straightFlush() //fünf aufeinanderfolgende Karten gleicher farbe
	{
		return flush() && straight();
	}
	static boolean royalFlush() //fünf aufeinander folgende Karten derselben farbe
	{
		return straightFlush() && kartensymbolBerechnen(hand[hand.length - 1]) == 0;
	}
}