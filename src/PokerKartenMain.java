import java.util.Arrays;
import java.util.Random;

public class PokerKartenMain {
	static final int ANZAHLFARBEN = 4; //final(nach static) bezweckt dass die const. nicht mehr verändert wird
	static final int ANZAHLKARTENSYMBOLE = 13;
	static final int KARTENDECK = ANZAHLKARTENSYMBOLE*ANZAHLFARBEN;
	static final int HANDGROESSE=5;
	static final int ANZAHLVERSUCHE = 100000;
	static int zaehleronepair = 0;
	static int zaehlertwopair = 0;
	static int zaehlerthreeofakind = 0;
	static int zaehlerstraight = 0;
	static int zaehlerflush = 0;
	static int zaehlerfullhouse = 0;
	static int zaehlerfourofakind = 0;
	static int zaehlerstraightflush = 0;
	static int zaehlerroyalflush = 0;	
	static int[] kartenstapel = new int [KARTENDECK];
	static int[] kartenfarbe = new int [ANZAHLFARBEN];
	static int[] kartensymbol = new int [ANZAHLKARTENSYMBOLE];

	public static void main(String[] args) {
		for(int i=0; i<ANZAHLVERSUCHE;i++)
		{
			kartenZiehen();
			for(int j=0;j<HANDGROESSE;j++)
			{
				kartenstapel[j]=farbeBerechnen(j);
				kartenstapel[j]=kartensymbolBerechnen(j);
			}
			if (onePair()) {zaehleronepair++;}
//		twoPair();
//		threeOfAKind();
//		straight();
//		flush();
//		fullHouse();
//		fourOfAKind();
//		straightFlush();
//		royalFlush();
		}
		System.out.print("\n"+zaehleronepair+" Paar(e) bei "+ANZAHLVERSUCHE+" Versuchen und einer Warscheinlichkeit von:\t\t "+(zaehleronepair*100.0/ANZAHLVERSUCHE)+" %");
		System.out.print("\n"+zaehlertwopair+" zwei Paare bei "+ANZAHLVERSUCHE+" Versuchen und einer Warscheinlichkeit von:\t "+(zaehlertwopair*100.0/ANZAHLVERSUCHE)+" %");
		System.out.print("\n"+zaehlerthreeofakind+" Drilling(e) bei "+ANZAHLVERSUCHE+" Versuchen und einer Warscheinlichkeit von:\t "+(zaehlerthreeofakind*100.0/ANZAHLVERSUCHE)+" %");
		System.out.print("\n"+zaehlerstraight+" Straße(n) bei "+ANZAHLVERSUCHE+" Versuchen und einer Warscheinlichkeit von:\t "+(zaehlerstraight*100.0/ANZAHLVERSUCHE)+" %");
		System.out.print("\n"+zaehlerflush+" Flush bei "+ANZAHLVERSUCHE+" Versuchen und einer Warscheinlichkeit von:\t\t "+(zaehlerflush*100.0/ANZAHLVERSUCHE)+" %");
		System.out.print("\n"+zaehlerfullhouse+" Full House bei "+ANZAHLVERSUCHE+" Versuchen und einer Warscheinlichkeit von:\t "+(zaehlerfullhouse*100.0/ANZAHLVERSUCHE)+" %");
		System.out.print("\n"+zaehlerfourofakind+" Vierling(e) bei "+ANZAHLVERSUCHE+" Versuchen und einer Warscheinlichkeit von:\t "+(zaehlerfourofakind*100.0/ANZAHLVERSUCHE)+" %");
		System.out.print("\n"+zaehlerstraightflush+" Straight Flush bei "+ANZAHLVERSUCHE+" Versuchen und einer Warscheinlichkeit von:\t "+(zaehlerstraightflush*100.0/ANZAHLVERSUCHE)+" %");
		System.out.print("\n"+zaehlerroyalflush+" Royal Flush bei "+ANZAHLVERSUCHE+" Versuchen und einer Warscheinlichkeit von: \t "+(zaehlerroyalflush*100.0/ANZAHLVERSUCHE)+" %");
	}

	static void arrayBefuellen()
	{
		for(int i=0; i<kartenstapel.length; i++)
		{
			kartenstapel[i]= i;
		}
		//System.out.println("_______________1");
	}
	static void arrayAusgeben()
	{
		for(int i=0; i<kartenstapel.length; i++)
		{
			System.out.println(kartenstapel[i]);
		}
		//System.out.println("_______________");
	}	
	static int randomZahlen(int maxBereich)
	{
		Random rnd = new Random();
		int rnd1 = rnd.nextInt(maxBereich);
		//System.out.println(""+rnd1);
		return rnd1;
	}
	static void kartenZiehen()
	{
		for(int i=0; i<HANDGROESSE;i++)
		{
			int randomZahl = randomZahlen(KARTENDECK-1-i);
			int temp  = kartenstapel[randomZahl];
			kartenstapel[randomZahl] = kartenstapel[kartenstapel.length-1-i];
			kartenstapel[kartenstapel.length-1-i] = temp;
		}
	}	
	static public int farbeBerechnen(int i)
	{
		int farbe = kartenstapel[i]%ANZAHLFARBEN;
		farbe = kartenfarbe[i];
		return farbe;
	}
	static public int kartensymbolBerechnen(int i)
	{
		int symbol = kartenstapel[i]/ANZAHLKARTENSYMBOLE;
		symbol = kartensymbol[i];
		return symbol;
	}
	//-----------------------------------------------------------------Kombinationen abfragen-------------------------------------------------
	static int checkPair() 
	{
		int checkpair = 0;
		for(int i=0;i<(HANDGROESSE-1);i++)
		{
			for(int j=i+1;j<HANDGROESSE;j++)
			{
				if(kartensymbol[i]==kartensymbol[j]) {checkpair++;}
			}
		}
		return checkpair;
	}
	static boolean onePair() //ein Paar
	{
		int checkonepair = checkPair();
		if(checkonepair == 1)
		{
			
			//System.out.println("Sie haben ein Paar!");
			return true;
		}
		return false;
	}
	static boolean twoPair() //zwei Paare
	{
		int checkonepair = checkPair();
		if(checkonepair == 2)
		{
			zaehlertwopair++;
			//System.out.println("Sie haben zwei Paare!");
			return true;
		}
		return false;
	}
	static boolean threeOfAKind() //drilling
	{
		for(int i=0; i<(HANDGROESSE-1); i++)
		{
			for(int j=i+1; j<HANDGROESSE; j++)
			{
				for(int k=i+2; k<HANDGROESSE; k++)
				{
					if(kartensymbol[i]==kartensymbol[j] || kartensymbol[j] == kartensymbol[k])
					{
						zaehlerthreeofakind++;
						//System.out.println("Sie haben einen Drilling!");
						return true;
					}
				}
			}
		}
		return false;
	}
	static boolean straight() //Fünf aufeinanderfolgende Karten 
	{
		int temp[] = new int [HANDGROESSE];
		for(int i=0;i <HANDGROESSE;i++)
		{
			temp[i]=kartensymbol[i];
			Arrays.sort(temp);
			if(temp[i] == 0 && temp[i] == 1 && temp[i] == 2 && temp[i] == 63 && temp[i] == 4) 
			{
				zaehlerstraight++;
				//System.out.println("Sie haben eine Straße!");
				return true;
			}
			else if(temp[i] == 1 && temp[i] == 2 && temp[i] == 3 && temp[i] == 4 && temp[i] == 5)
			{
				zaehlerstraight++;
				//System.out.println("Sie haben eine Straße!");
				return true;
			}
			else if(temp[i] == 2 && temp[i] == 3 && temp[i] == 4 && temp[i] == 5 && temp[i] == 6) 
			{
				zaehlerstraight++;
				//System.out.println("Sie haben eine Straße!");
				return true;
			}
			else if(temp[i] == 3 && temp[i] == 4 && temp[i] == 5 && temp[i] == 6 && temp[i] == 7) 
			{
				zaehlerstraight++;
				//System.out.println("Sie haben eine Straße!");
				return true;
			}
			else if(temp[i] == 4 && temp[i] == 5 && temp[i] == 6 && temp[i] == 7 && temp[i] == 8) 
			{
				zaehlerstraight++;
				//System.out.println("Sie haben eine Straße!");
				return true;
			}
			else if(temp[i] == 5 && temp[i] == 6 && temp[i] == 7 && temp[i] == 8 && temp[i] == 9) 
			{
				zaehlerstraight++;
				//System.out.println("Sie haben eine Straße!");
				return true;
			}
			else if(temp[i] == 6 && temp[i] == 7 && temp[i] == 8 && temp[i] == 9 && temp[i] == 10)
			{
				zaehlerstraight++;
				//System.out.println("Sie haben eine Straße!");
				return true;
			}
			else if(temp[i] == 7 && temp[i] == 8 && temp[i] == 9 && temp[i] == 10 && temp[i] == 11)
			{
				zaehlerstraight++;
				//System.out.println("Sie haben eine Straße!");
				return true;
			}
			else if(temp[i] == 8 && temp[i] == 9 && temp[i] == 10 && temp[i] == 11 && temp[i] == 12)
			{
				zaehlerstraight++;
				//System.out.println("Sie haben eine Straße!");
				return true;
			}
		}
		return false;
	}
	static boolean flush() //5 beliebige Karten derselben Farbe
	{
		int farbe = kartenfarbe[0];
		for(int i=0; i<HANDGROESSE; i++)
		{
			if(farbe == kartenfarbe[i])
			{
				zaehlerflush++;
				//System.out.println("Sie haben einen Flush!");
				return true;
			}
		}
		return false;
	}
	static boolean fullHouse() //drei gleichwertige Karten und paar von anderer farbe
	{
		if(zaehlerthreeofakind == 1 && zaehlertwopair == 1)
		{
			zaehlerflush++;
			//System.out.println("Sie haben ein Full House!");
			return true;
		}
		return false;
	}
	static boolean fourOfAKind() //Vier Karten des gleichen Werts
	{
		for(int i=0;i<HANDGROESSE-1; i++)
		{
			for(int j=i+1;j<HANDGROESSE; j++)
			{
				for(int k=i+2;k<HANDGROESSE; k++)
				{
					for(int l=i+3;l<HANDGROESSE; l++)
					{
						if(kartenfarbe[i]==kartenfarbe[j] &&kartenfarbe[k]==kartenfarbe[l])
						{
							zaehlerfourofakind++;
							//System.out.println("Sie haben einen Vierling!");
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	static boolean straightFlush() //fünf aufeinanderfolgende Karten gleicher farbe
	{
		boolean straight = straight();
		boolean flush = flush();
		if(straight && flush)
		{
			zaehlerstraightflush++;
			//System.out.println("Sie haben einen Straight Flush!");
			return true;
		}
		return false;
	}
	static boolean royalFlush() //fünf aufeinander folgende Karten derselben farbe
	{
		boolean straightflush = straightFlush();
		int temp[] = new int[HANDGROESSE];
		for(int i=0;i<HANDGROESSE;i++)
		{
			temp[i] = kartensymbol[i];
			Arrays.sort(temp);
			if(straightflush && temp[1]==9 && temp[1]==10 && temp[1]==11 && temp[1]==12 && temp[1]==13)
			{
				zaehlerroyalflush++;
				//System.out.println("Sie haben einen Royal Flush!");
				return true;
			}
		}
		return false;
	}
}