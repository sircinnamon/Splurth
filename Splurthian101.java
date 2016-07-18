import java.util.LinkedList;
public class Splurthian101
{
	//Daily programmer easy challenge 275
	//takes two strings, Element name and Element symbol Xy
	public static void main(String[] args)
	{
		if(args.length < 1){System.exit(0);}
		String name = args[0];
		if(args.length > 1)
		{
			String symbol = args[1];
			System.out.println(name + ", " + symbol + " -> " + valid(name, symbol));
		}
		System.out.println(genSymbol(name));
		System.out.println(enumValues(name));
	}

	public static boolean valid(String name, String symbol)
	{
		if(symbol.length() != 2){return false;}
		if(!name.matches("[A-Z][a-z]+") || !symbol.matches("[A-Z][a-z]+")){return false;}
		name = name.toLowerCase();
		symbol = symbol.toLowerCase();
		String regex = ".*";
		for(char c : symbol.toCharArray())
		{
			regex = regex + c + ".*";
		}
		return name.matches(regex);
	}

	public static String genSymbol(String name)
	{
		//for bonus 1: generate the alphabetically first 2-symbol that is valid
		char x = 'a'-1;
		char y = 'a'-1;
		if(!name.matches("[A-Z][a-z]+")){return "ERR";}
		name = name.toLowerCase();
		int xIndex = -1;
		while(xIndex < 0 || xIndex >= name.length()-1)
		{
			x++;
			xIndex = name.indexOf(x);
		}
		//System.out.println(xIndex + " " + x + " " +name.substring(xIndex+1));
		String sub = name.substring(xIndex+1);
		while(sub.indexOf(y) == -1)
		{
			y++;
		}
		return "" + (char)(x-32) + y;
	}

	public static int enumValues(String name)
	{
		//Part of the bonus
		LinkedList<String> list = new LinkedList<String>();
		String symbol = "";
		if(!name.matches("[A-Z][a-z]+")){return 0;}
		name = name.toLowerCase();
		for(int x = 0; x < name.length(); x++)
		{
			for(int y = x+1; y < name.length(); y++)
			{
				symbol = name.charAt(x) + "" + name.charAt(y);
				if(!list.contains(symbol))
				{
					//System.out.println(list.size() + ": " + symbol);
					list.add(symbol);
				}
			}
		}
		return list.size();
	}
}