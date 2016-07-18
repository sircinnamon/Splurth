import java.util.LinkedList;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
public class Splurthian102
{
	//Daily programmer Intermediate challenge 275
	//takes list of elements and assigns prefered symbol
	public static void main(String[] args)
	{
		//takes element list filename
		if(args.length != 1){System.exit(0);}
		String filename = args[0];
		LinkedList<String> elements = new LinkedList<String>();
		try{elements = getElementList(filename);}catch(IOException e){e.printStackTrace();}
		LinkedList<String> symbols = new LinkedList<String>();
		symbols = assignSymbols(elements);
		for(int i = 0; i < elements.size(); i++)
		{
			System.out.println(elements.get(i) + " -> " + symbols.get(i));
		}
	}

	public static LinkedList<String> getElementList(String file) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		LinkedList<String> list = new LinkedList<String>();
		String x = br.readLine();
		while(x != null)
		{
			list.add(x);
			x = br.readLine();
		}
		return list;
	}

	public static String genSymbol(String name, LinkedList<String> symbols)
	{
		int i, j = 0;
		String symbol;
		boolean found = false;
		name = name.toLowerCase();
		loop:
		for(i = 0; i < name.length(); i++)
		{
			for(j = i+1; j < name.length(); j++)
			{
				symbol = "" + (char)(name.charAt(i)-32) + "" + name.charAt(j);
				if(!symbols.contains(symbol))
				{
					found = true;
					break loop;
				}
			}
		}
		if(found){return "" + (char)(name.charAt(i)-32) + name.charAt(j);}
		return "";
	}

	public static LinkedList<String> assignSymbols(LinkedList<String> elements)
	{
		String symbol;
		LinkedList<String> symbols = new LinkedList<String>();
		for(int i = 0; i < elements.size(); i++)
		{
			symbol = genSymbol(elements.get(i), symbols);
			if(symbol.equals(""))
			{
				String element = elements.remove(i);
				elements.addFirst(element);
				System.out.println("Moving " + element);
				return assignSymbols(elements);
			}
			else
			{
				symbols.add(symbol);
			}
		}
		return symbols;
	}
}