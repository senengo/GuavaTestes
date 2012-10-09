import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;


public class CacheDePersonagens {

	private final LoadingCache <PersonagemKey,String> cache;
	
	public CacheDePersonagens() {
		this.cache = CacheBuilder.newBuilder()
				.build(new LoaderPersonagens());
	}
	
	
	public String porPersonagemKey(PersonagemKey key){
		return cache.getUnchecked(key);
	}
	
	public long size(){
		return cache.size();
	}
	
	public void limparCache(){
		cache.cleanUp();
	}
	
	// Esta classe é chamada toda vez que algo é solicitado ao cache e ele não encontra
	// a ocorrência. Então ele precisa carregar isso da Base de Dados
	
	private class LoaderPersonagens extends CacheLoader<PersonagemKey, String>{
		@Override
		public String load(PersonagemKey key) throws Exception {
			
			System.err.println("Buscando pelo cache Loader...");
			
			Thread.sleep(2000);
			
			return Simpsons.porPersonagemKey(key);
		}
	}
	
}
