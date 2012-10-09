import java.util.List;

import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableList;


public class Simpsons {

	private static final ImmutableList<String> personagens = ImmutableList.<String>builder()
			.add("Homer")
			.add("Bart")
			.add("Lisa")
			.add("Marge")
			.add("Moe")
			.add("Krusty")
			.add("Maggie")
			.add("Barney")
			.add("Burns")
			.add("Milhouse")
			.add("Flanders")
			.build();
	
	private static HashBiMap<PersonagemKey, String> keyMap = HashBiMap.create();
	
	static{
		for (String nome : personagens) {
			PersonagemKey personagemKey = new PersonagemKey(nome);
			keyMap.put(personagemKey, nome);
		}
	}
	
	public static List<String> getNames(){
		return personagens.asList();
	}
	
	public static String porPersonagemKey(PersonagemKey key){
		return keyMap.get(key);
	}
	
}
