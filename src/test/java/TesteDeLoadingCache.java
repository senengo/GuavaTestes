import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.base.Optional;
import com.google.common.cache.CacheLoader.InvalidCacheLoadException;
import com.google.common.cache.CacheStats;
/*
 * Teste do cache guava - Ricardo Murad
 */
@Test
public class TesteDeLoadingCache {
	
	private CacheDePersonagens cache = new CacheDePersonagens();
	
	public void Testa_a_busca_do_Loading_cache(){
		
		
		// Cache tem que estar zerado
		Assert.assertEquals(cache.size(), 0); 
		
		
		PersonagemKey key1 = new PersonagemKey("Lisa");
		
		// 2 chamadas ao mesmo key só uma chamada ao loader
		String res1 = cache.porPersonagemKey(key1);
		String res2 = cache.porPersonagemKey(key1);
		
		Assert.assertEquals(res1, res2);
		Assert.assertEquals(cache.size(), 1);
		
		CacheStats stats = cache.getStats();
		Assert.assertEquals(stats.loadCount(), 1);
		
		
		// Buscando Homer agora cacheLoader é usado novamente
		PersonagemKey key2 = new PersonagemKey("Homer");
		String res3 = cache.porPersonagemKey(key2);
		String res4 = cache.porPersonagemKey(key2);
		Assert.assertEquals(res3, res4);
		
		stats = cache.getStats();
		Assert.assertEquals(stats.loadCount(), 2);
		
//		Para testar retorno null		
//		PersonagemKey inexistente = new PersonagemKey("inexistente");
//		String res = cache.porPersonagemKey(inexistente);
		
		stats = cache.getStats();
		
		Assert.assertEquals(stats.requestCount(), 4);
		Assert.assertEquals(stats.loadCount(), 2);
		
		System.err.println("\n# ###########################");
		System.err.println("# Estastísticas do cache");
		System.err.println("# Chamadas ao Cache: " + stats.requestCount());
		System.err.println("# Chamadas ao Lodader: " + stats.loadCount());
		System.err.println("# Tempo gasto carregando novos valores: " + stats.totalLoadTime());

	}

}