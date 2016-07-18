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
		System.out.println(name + " alphabetical symbol: " + genSymbol(name));
		System.out.println(name + " has " + enumValues(name) + " Splurthian symbol candidates.");
		/*for(String s : enumBlurthValues(name))
		{
			System.out.println(s);
		}*/
		System.out.println(name + " has " + enumBlurthValues(name).size() + " Blurthian symbol candidates.");
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

	public static LinkedList<String> enumBlurthValues(String name)
	{
		//Part of the bonus
		name = name.toLowerCase();
		LinkedList<String> list = new LinkedList<String>();
		String prefix = ""+name.charAt(0);
		if(name.length() == 1){list.add(name);}
		else
		{
			list.addAll(enumBlurthValues(name.substring(1)));
			int size = list.size();
			for(int i = 0; i<size; i++)
			{
				list.add(prefix + list.get(i));
			}
			list.add(prefix);
		}
		list = prune(list);
		return list;
	}

	public static LinkedList<String> prune(LinkedList<String> x)
	{
		String str;
		for(int i = 0; i < x.size(); i++)
		{
			str = x.get(i);
			while(x.indexOf(str) != x.lastIndexOf(str))
			{
				x.removeLastOccurrence(str);
			}
		}
		return x;
	}
}