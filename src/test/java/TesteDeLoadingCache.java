import org.testng.Assert;
import org.testng.annotations.Test;
/*
 * Teste do cache guava - Ricardo Murad
 */
@Test
public class TesteDeLoadingCache {
	
	private CacheDePersonagens cache = new CacheDePersonagens();
	
	public void Testa_a_busca_do_Loading_cache(){
		
		PersonagemKey key1 = new PersonagemKey("Lisa");
		PersonagemKey key2 = new PersonagemKey("Homer");
		
		// Cache tem que estar zerado
		Assert.assertEquals(cache.size(), 0);
		
		String res1 = cache.porPersonagemKey(key1);
		
		Assert.assertEquals(res1, "Lisa");
		Assert.assertEquals(res1, "Lisa");
		Assert.assertEquals(res1, "Lisa");
		
		// Lisa foi chamada 3 vezes mas o cache Loader só foi
		// chamado na primeira quando o cache estava vazio
		System.err.println(cache.size());
		
		// Buscando Homer agora cacheLoader é usado novamente
		String res2 = cache.porPersonagemKey(key2);
		Assert.assertEquals(res2, "Homer");
		
		//Tamanho final do cache = 2
		System.err.println(cache.size());
		
		// Cache tem que ter 2 elementos
		Assert.assertEquals(cache.size(), 2);
	}

}